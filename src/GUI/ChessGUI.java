package GUI;


/**
 * @author zeno
 *
 */

import java.awt.*;

import java.awt.event.*;
import javax.swing.*;

import main.Board;
import main.Pawn;

import java.util.*;
public class ChessGUI extends JFrame{
	Board board = new Board(8,8, 0);
	JButton[][] squares = new JButton[board.getRows()][ board.getColumns()];
	GridLayout chessBoardLayout = new GridLayout(board.getRows(), board.getColumns());		
	JLabel[] pieces = new JLabel[32];
	JPanel    leftPanel, rightPanel;
	JButton	  forfeit, reset, undo;
	Image     wKing, bKing, wQueen, bQueen, wKnight, bKnight,
			  wBishop, bBishop, wRook, bRook, wPawn, bPawn;
	ImageIcon wKingIcon, bKingIcon, wQueenIcon, bQueenIcon, wKnightIcon, bKnightIcon,
			  wBishopIcon, bBishopIcon, wRookIcon, bRookIcon, wPawnIcon, bPawnIcon;
	boolean   firstClick, player1Reset = false, player2Reset = false;
	int 	  currentX, currentY, nextX, nextY, player = 0;
	ArrayList <MovePerformed> movesList = new ArrayList();
	int       player1Score = 0, player2Score = 0, theGameIsOver = 0;
	String	  player1Name, player2Name;
	JTextField    player1NameField, player2NameField;
	JLabel    player1ScoreLabel, player2ScoreLabel;
	JPanel    scorePanel;
	String[]  gameModes = {"Standard", "Ping", "Nuke"};
	JComboBox gameModeBox = new JComboBox(gameModes);
	int       gameMode = 0;
	final int PLAYER1 = 0;
	final int PLAYER2 = 1;
	private final int STANDARD = 0;
	private final int PING = 1;
	private final int NUKE = 2;
	
	/**
	 * Constructor for the class
	 * Will set up the chess board
	 */
	public ChessGUI(ActionListener mListener){		
		leftPanel = new JPanel();
		leftPanel.setPreferredSize(new Dimension(800, 800));
		rightPanel = new JPanel();
		leftPanel.setLayout(chessBoardLayout);
		firstClick = true;
		colorTheBoard(mListener);		
		setUpThePieces();
		setUpTheOptionButtons();
		setLayout(new FlowLayout());
		addPiecesToBoard();
		add(leftPanel);
		add(rightPanel);
		leftPanel.setVisible(true);
		rightPanel.setVisible(true);
	}
	
	public Board getBoard(){
		return board;
	}
	
	public JButton[][] getSquares(){
		return squares;
	}
	
	/**
	 * Helper method that will color the board in black and white
	 */
	private void colorTheBoard(ActionListener mListener){
		int row, column;		
		for(row = 0; row < board.getRows(); row++)
			for(column = 0; column < board.getColumns(); column++){
				squares[row][column] = new JButton();
				squares[row][column].addActionListener(mListener);
				squares[row][column].setPreferredSize(new Dimension(100, 100));
				if(row % 2 == 0)
					if(column % 2 == 1)
						squares[row][column].setBackground(Color.red);
					else
						squares[row][column].setBackground(Color.yellow);
				else
					if(column % 2 == 1)
						squares[row][column].setBackground(Color.yellow);
					else
						squares[row][column].setBackground(Color.red);
				leftPanel.add(squares[row][column]);
			}
	}
	

	/**
	 * Helper method that will assign to each JLabel
	 * an image corresponding to the piece's place on the board
	 */
	private void setUpThePieces(){		
		int i;		
		for(i = 0; i < 8; i++){			
			pieces[i + 16] = new JLabel(new ImageIcon("black_pawn.png"));
			pieces[i] = new JLabel(new ImageIcon("chess_piece_white_pawn.png"));
		}
		//initialize the rooks 8 15 24 31
			pieces[24] = new JLabel(new ImageIcon("black_rook.png"));
			pieces[31] = new JLabel(new ImageIcon("black_rook.png"));
			pieces[8] = new JLabel(new ImageIcon("white_rook.png"));
			pieces[15] = new JLabel(new ImageIcon("white_rook.png"));
			//initialize the knights 9 14 25 30			
			pieces[25] = new JLabel(new ImageIcon("black_knight.png"));
			pieces[30] = new JLabel(new ImageIcon("black_knight.png"));
			pieces[9] = new JLabel(new ImageIcon("white_knight.png"));
			pieces[14] = new JLabel(new ImageIcon("white_knight.png"));			
			//initialize the bishops 10 13 26 29
			pieces[26] = new JLabel(new ImageIcon("black_bishop.png"));
			pieces[29] = new JLabel(new ImageIcon("black_bishop.png"));
			pieces[10] = new JLabel(new ImageIcon("white_bishop.png"));
			pieces[13] = new JLabel(new ImageIcon("white_bishop.png"));
			//initialize the queens 11 27
			pieces[27] = new JLabel(new ImageIcon("black_queen.png"));
			pieces[11] = new JLabel(new ImageIcon("white_queen.png"));
			//initialize the kings 12 28
			pieces[28] = new JLabel(new ImageIcon("black_king.png"));
			pieces[12] = new JLabel(new ImageIcon("white_king.png"));
			for(i = 0; i < 32; i ++)
				pieces[i].setVisible(true);
	}
	
	
	/**
	 * Method that will add the labels to the frame
	 */
	void addPiecesToBoard(){
		int i;
		//add the pawns first
		for(i = 0; i < board.getColumns(); i++){
			squares[6][i].add(pieces[i]);
			squares[1][i].add(pieces[16+i]);
		}
		//add the rooks
		squares[7][0].add(pieces[8]);
		squares[7][7].add(pieces[15]);
		squares[0][0].add(pieces[24]);
		squares[0][7].add(pieces[31]);
		//add the knights, pings, nukes
		squares[7][1].add(pieces[9]);
		squares[7][6].add(pieces[14]);
		squares[0][1].add(pieces[25]);
		squares[0][6].add(pieces[30]);
		//add the bishops
		squares[7][2].add(pieces[10]);
		squares[7][5].add(pieces[13]);
		squares[0][2].add(pieces[26]);
		squares[0][5].add(pieces[29]);
		//add the queens
		squares[7][3].add(pieces[11]);
		squares[0][3].add(pieces[27]);
		//add the kings
		squares[7][4].add(pieces[12]);
		squares[0][4].add(pieces[28]);
	}
	
	/**
	 * Private method that will add the three separate buttons
	 * to the frame
	 */
	private void setUpTheOptionButtons(){
		forfeit = new JButton("Forfeit");
		forfeit.setBackground(Color.white);
		forfeit.addActionListener(new ForfeitListener());
		
		reset = new JButton("Reset");
		reset.setBackground(Color.white);
		reset.addActionListener(new ResetListener());
		
		undo = new JButton("Undo");
		undo.setBackground(Color.white);
		undo.addActionListener(new UndoListener());
		
		scorePanel = new JPanel();
		player1NameField = new JTextField("Player1");
		player1NameField.addActionListener(new TextFieldListener());		
		player2NameField = new JTextField("Player2");
		player2NameField.addActionListener(new TextFieldListener());
		player1ScoreLabel = new JLabel();
		player2ScoreLabel = new JLabel();
		player1ScoreLabel.setText(Integer.toString(player1Score));
		player2ScoreLabel.setText(Integer.toString(player2Score));
		scorePanel.setLayout(new GridLayout(2,2));
		scorePanel.add(player1NameField);
		scorePanel.add(player2NameField);
		scorePanel.add(player1ScoreLabel);
		scorePanel.add(player2ScoreLabel);	
		
		gameModeBox.setSelectedIndex(0);
		gameModeBox.addActionListener(new GameModeBoxListener());
		
		rightPanel.setLayout(new BorderLayout());
		rightPanel.add(forfeit, BorderLayout.LINE_START);
		rightPanel.add(reset, BorderLayout.CENTER);
		rightPanel.add(undo, BorderLayout.LINE_END);
		rightPanel.add(gameModeBox, BorderLayout.PAGE_START);
		rightPanel.add(scorePanel, BorderLayout.PAGE_END);
	}
	
	/**
	 * Method that will attempt the move selected by the player
	 * @param cX the current x selected
	 * @param cY the current y selected
	 * @param nX the new x selected
	 * @param nY the new y selected
	 */
	public int performTheMove(int cX, int cY, int nX, int nY){		
		int indexCaptured = board.pieceAtWithIndex(nX, nY);
		if(board.pieceAt(cX, cY) == null)
			return 0;
		int result = board.turnOfPlayer(cX, cY, nX, nY, player);
		if(result == 1){	
			if(indexCaptured >= 0){
				squares[8 - nX][nY - 1].remove(pieces[indexCaptured]);
				repaint();
			}
			addMoveToTheList(cX, cY, nX, nY, board.pieceAtWithIndex(nX, nY), indexCaptured, player);		
			squares[8 - cX][cY - 1].remove(pieces[board.pieceAtWithIndex(nX, nY)]);				
			squares[8 - nX][nY - 1].add(pieces[board.pieceAtWithIndex(nX, nY)]);
			repaint();
			if(player == PLAYER1)
				player = PLAYER2;
			else
				player = PLAYER1;	
		}
		else{
			JOptionPane.showMessageDialog(null, "Invalid move");
		}
		
		theGameIsOver = board.gameOver(player);
		if(board.gameOver(player) == 1){
			if(player == PLAYER1)
				player2Score++;
			else
				player1Score++;
			player1ScoreLabel.setText(Integer.toString(player1Score));
			player2ScoreLabel.setText(Integer.toString(player2Score));
			repaint();
			JOptionPane.showMessageDialog(null, "Checkmate");
			return 0;
		}
		else if (board.gameOver(player) == 2){
			JOptionPane.showMessageDialog(null, "Stalemate");
			return 0;
		}
		return 1;
	}
	
	/**
	 * Private method, it will add the move to the list of total moves
	 * @param cX   the current x
	 * @param cY   the current y
	 * @param nX   the new x
	 * @param nY   the new y
	 * @param imp  the index of the moved piece
	 * @param icp  the index of the captured piece
	 * @param p    the player who performed the move
	 */
	private void addMoveToTheList(int cX, int cY, int nX, int nY,
			                      int imp, int icp, int p){
		MovePerformed lastMove = new MovePerformed(cX, cY, nX, nY, imp, icp, p);
		movesList.add(lastMove);
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
			public void actionPerformed(ActionEvent e){
				button = (JButton)e.getSource();
				for(int i = 0; i < board.getRows(); i++)
					for(int j = 0; j < board.getColumns(); j++)
						if(button == squares[i][j]){
							x = 8 - i;
							y = j + 1;
						}
				if(firstClick == true){
					firstClick = false;
					currentX = x;
					currentY = y;
				}
				else{
					firstClick = true;
					nextX = x;
					nextY = y;
					performTheMove(currentX, currentY, nextX, nextY);
					
				}
			}
			
		}
		
		/**
		 * Class that implements ActionListener,
		 * will be used when a player hits the "forfeit button"
		 * @author zeno
		 * @version 1.0
		 */
		class ForfeitListener implements ActionListener{
			public void actionPerformed(ActionEvent e){
				player1Reset = true;
				player2Reset = true;
				if(player == PLAYER1)
					JOptionPane.showMessageDialog(null, "Player 1 has quit the game");
				else
					JOptionPane.showMessageDialog(null, "Player 2 has quit the game");
			}
		}
		
		
		/**
		 * Class that implements ActionListener,
		 * will be used to reset the game
		 * @author zeno
		 * @version 1.0
		 */
		class ResetListener implements ActionListener{
			public void actionPerformed(ActionEvent e){
				if(player == PLAYER1)
					player1Reset = true;
				else
					player2Reset = true;
				if(((player1Reset) && (player2Reset)) || (theGameIsOver > 0)){
					board = new Board(8,8, STANDARD);
					player = PLAYER1;
					player1Reset = false;
					player2Reset = false;
					movesList.clear();
					addPiecesToBoard();
					repaint();
				}
			}
		}
		
		/**
		 * Class that implements ActionListener,
		 * will be used each time a player
		 * wants to undo a move
		 * @author zeno
		 * @version 1.0
		 */
		class UndoListener implements ActionListener{
			public void actionPerformed(ActionEvent e){
				if(movesList.isEmpty())
					JOptionPane.showMessageDialog(null, "No moves were performed");
				else{
					MovePerformed move = movesList.get(movesList.size() - 1);
					if(move.getPlayer() == player)
						JOptionPane.showMessageDialog(null, "You cannot undo other player's move");
					else{
						//perform the move on the board
						board.pieceFromIndex(move.getMovedPieceIndex()).setCoordinates(move.getPreviousX(), move.getPreviousY());						
						board.setSquareTaken(move.getCurrentX(), move.getCurrentY(), 0);
						board.setSquareTaken(move.getPreviousX(), move.getPreviousY(), 1);
						if(board.pieceFromIndex(move.getMovedPieceIndex()) instanceof Pawn)
							board.pieceFromIndex(move.getMovedPieceIndex()).takeBackFirstMove();
						//and on the GUI
						squares[8 - move.getPreviousX()][move.getPreviousY() - 1].add(pieces[move.getMovedPieceIndex()]);
						//bring back the taken piece
						if(move.getCapturedPieceIndex() >= 0){
							//perform the move on the board
							board.pieceFromIndex(move.getCapturedPieceIndex()).setCoordinates(move.getCurrentX(), move.getCurrentY());
							board.pieceFromIndex(move.getCapturedPieceIndex()).setTaken(0);
							board.setSquareTaken(move.getCurrentX(), move.getCurrentY(), 1);
							//and on the GUI
							squares[8 - move.getCurrentX()][move.getCurrentY() - 1].add(pieces[move.getCapturedPieceIndex()]);
						}	
						repaint();
						if(player == PLAYER1)
							player = PLAYER2;
						else
							player = PLAYER1;
					}
				}
			}
		}
		
		/**
		 * Class that implements ActionListener,
		 * will be used when the players enter they names
		 * @author zeno
		 * @version 1.0
		 */
		class TextFieldListener implements ActionListener{
			public void actionPerformed(ActionEvent e){
				if((!player1NameField.getText().equals(player1Name)) || (!player2NameField.getText().equals(player2Name))){
					player1Name = player1NameField.getText();
					player2Name = player2NameField.getText();
					player1Score = 0;
					player2Score = 0;
					player1ScoreLabel.setText(Integer.toString(player1Score));
					player2ScoreLabel.setText(Integer.toString(player2Score));
					repaint();
				}
			}
		}
		
		
		/**
		 * Class that implements ActionListener,
		 * will be used for the combo box
		 * and see if the players want a standard
		 * version of chess or with special pieces
		 * @author zeno
		 * @version 1.0
		 */
		class GameModeBoxListener implements ActionListener{
			public void actionPerformed(ActionEvent e){
				JComboBox cb = (JComboBox)e.getSource();
		        String howToPlay = (String)cb.getSelectedItem();
		        if(howToPlay.equals("Standard"))
		        	gameMode = STANDARD;
		        if(howToPlay.equals("Ping"))
		        	gameMode = PING;
		        if(howToPlay.equals("Nuke"))
		        	gameMode = NUKE;
		        if(movesList.isEmpty()){
		        	board = new Board(8,8,gameMode);		        	
		        }
			}
		}
}

