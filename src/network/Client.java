package network;

import java.net.*;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.swing.JButton;

import GUI.ChessGUI;

import main.BasicMove;

/**
 *  This is the client class for the chess game
 * @author zeno
 *
 */
public class Client{
    Socket clientSocket;
    ObjectOutputStream out;
    ObjectInputStream in;
    BasicMove moveClient, moveServer;
    ChessGUI m;
    private final int PLAYER1 = 0;
	private final int PLAYER2 = 1;
	int counter;
	MoveListener mListener;
	Stack<BasicMove> clientStack;
    
    /**
     * Default constructor for the class
     */
    Client(){  
    	mListener = new MoveListener();    	
    	m = new ChessGUI(mListener); 
    	mListener.setUp(m.getSquares());
    	counter = 0;
    	clientStack = new Stack();
    }

    void run(){
    	 try{
             // http://docs.oracle.com/javase/tutorial/networking/sockets/clientServer.html
    		 //1. creating a socket to connect to the server
             clientSocket = new Socket("localhost", 2004);
             System.out.println("Connected to localhost in port 2004");
             //2. get Input and Output streams
             out = new ObjectOutputStream(clientSocket.getOutputStream());
             out.flush();
             in = new ObjectInputStream(clientSocket.getInputStream());
             //3: Communicating with the server
             do{
            	 try{
            		 moveServer = (BasicMove)in.readObject();            		 
            		 m.performTheMove(moveServer.getPreviousX(), moveServer.getPreviousY(), moveServer.getCurrentX(), moveServer.getCurrentY());
            		 //this is to check whether the server only validated the client's move or it sent a new move
            		 boolean performedClientMove = false;
            		 do{
            			 //get the start location and end location
            			 if(!clientStack.empty()){
            				 sendMove(clientStack.pop());
            				 moveServer = (BasicMove)in.readObject();
                    		 if(moveServer != null){
                    			 if(m.performTheMove(moveServer.getPreviousX(), moveServer.getPreviousY(), moveServer.getCurrentX(), moveServer.getCurrentY()) == 1){
                    				 performedClientMove = true;
                    			 }
                    		 }
            			 }
            		 }while((!performedClientMove) && (m.getBoard().gameOver(PLAYER2) == 0) && (m.getBoard().gameOver(PLAYER1) == 0));
            	 }            	 
            	 catch(ClassNotFoundException classNot){
                     System.err.println("data received in unknown format");
                 }
             }while((m.getBoard().gameOver(PLAYER2) == 0) && (m.getBoard().gameOver(PLAYER1) == 0));
    	 }
         catch(UnknownHostException unknownHost){
             System.err.println("You are trying to connect to an unknown host!");
         }
         catch(IOException ioException){
             ioException.printStackTrace();
         }
         finally{
             //4: Closing connection
             try{
                 in.close();
                 out.close();
                 clientSocket.close();
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
			clientStack.push(move);
		}
	}	
}

public static void main(String args[])
{
    Client client = new Client();
    client.run();
}
}