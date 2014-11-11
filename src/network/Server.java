package network;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;

import javax.swing.JButton;
import java.util.*;

import main.BasicMove;
import GUI.ChessGUI;


public class Server{
    ServerSocket serverSocket;
    Socket connection = null;
    ObjectOutputStream out;
    ObjectInputStream in;
    BasicMove moveServer, moveClient;
    private final int PLAYER1 = 0;
	private final int PLAYER2 = 1;
	ChessGUI m;
	boolean sent;
	Stack<BasicMove> serverStack;
	MoveListener mListener;
    
    Server(){
    	mListener = new MoveListener();
    	m = new ChessGUI(mListener);
    	mListener.setUp(m.getSquares());
    	serverStack = new Stack();
    }
    void run()
    {
        try{
            // http://docs.oracle.com/javase/tutorial/networking/sockets/clientServer.html
        	//1. creating a server socket
           serverSocket = new ServerSocket(2004, 10);
            //2. Wait for connection            
            connection = serverSocket.accept();           
            //3. get Input and Output streams
            out = new ObjectOutputStream(connection.getOutputStream());
            out.flush();
            in = new ObjectInputStream(connection.getInputStream());            
            //4. The two parts communicate via the input and output streams                      
            do{
            	//perform the server's move first
            	boolean performedServerMove = false; 
            	do{           
                	if(!serverStack.empty()){
                		BasicMove tempMove = serverStack.peek();
                		if(m.performTheMove(tempMove.getPreviousX(), tempMove.getPreviousY(), tempMove.getCurrentX(), tempMove.getCurrentY()) == 1){
                			sendMove(serverStack.pop());
                			performedServerMove = true;
                		}
                		else{
                			serverStack.pop();
                		}
                	}
                }while((!performedServerMove) && (m.getBoard().gameOver(PLAYER2) == 0) && (m.getBoard().gameOver(PLAYER1) == 0));
            	//then the move from the client
            	boolean performedClientMove = false; 
            	do{
                	try{  
                		//get the move from the client and attempt to move it
                		moveClient = (BasicMove)in.readObject();                  
                		//if the move is okay, get a new move from the server, otherwise send the old one again
                		if(m.performTheMove(moveClient.getPreviousX(), moveClient.getPreviousY(), moveClient.getCurrentX(), moveClient.getCurrentY()) == 1){                    	
                			performedClientMove = true;   
                			sendMove(moveClient);
                		}  
                	
                	}
                	catch(ClassNotFoundException classnot){
                		System.err.println("Data received in unknown format");
                	}
            	}while((!performedClientMove) && (m.getBoard().gameOver(PLAYER2) == 0) && (m.getBoard().gameOver(PLAYER1) == 0));
            }while((m.getBoard().gameOver(PLAYER2) == 0) && (m.getBoard().gameOver(PLAYER1) == 0));
        }
        catch(IOException ioException){
            ioException.printStackTrace();
        }
        finally{
            //4: Closing connection
            try{
                in.close();
                out.close();
                serverSocket.close();
            }
            catch(IOException ioException){
                ioException.printStackTrace();
            }
        }
    }   
    
    /**
     *  Function which will send to the server a move performed type of 
     *  object and then the server will figure out whether the move is right or not  
     */
    void sendMove(BasicMove move){
    	try{
            out.writeObject(move);
            out.flush();            
        }
        catch(IOException ioException){
            ioException.printStackTrace();
        }
    }       
  
    
    /**
     * Class that implements ActionListener,
     * will be used for determining when to move 
     * a piece on the board
     * @author zeno
     * @version 1.0
     */
    class MoveListener implements ActionListener{
    	JButton button;
    	int x, y;
    	boolean firstClick = true, ready = false;
    	int currentX, currentY, nextX, nextY;
    	JButton squares[][];
    	public void setUp(JButton s[][]){
    		squares = s;
    	}
    	public void actionPerformed(ActionEvent e){
    		button = (JButton)e.getSource();
    		for(int i = 0; i < 8; i++)
    			for(int j = 0; j < 8; j++)
    				if(button == squares[i][j]){
    					x = 8 - i;
    					y = j + 1;
    				}
    		if(firstClick == true){
    			firstClick = false;
    			ready = false;
    			currentX = x;
    			currentY = y;
    		}
    		else{
    			firstClick = true;
    			ready = true;
    			nextX = x;
    			nextY = y;
    			BasicMove move = new BasicMove(nextX, nextY, currentX, currentY);
    			serverStack.push(move);
    		}
    	}	
    }
    
    public static void main(String args[])
    {
        Server server = new Server();
        while(true){
            server.run();
        }
    }
}    