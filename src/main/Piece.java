package main;
/**
 * 
 */

/**
 * @author zeno
 *
 */
public abstract class Piece {
	protected int xCoord, yCoord;
	protected int wasTaken = 0;
	protected int player;
	protected final int PLAYER1 = 0;
	protected final int PLAYER2 = 1;
	protected boolean firstMove;

	/**
	 * Constructor for the abstract class
	 * @param x the initial x coordinate
	 * @param y	the initial y coordinate
	 * @param p the player
	 */
	public Piece(int x, int y, int p){
		xCoord = x;
		yCoord = y;
		wasTaken = 0;
		player = p;
		firstMove = true;
	}
	
	

	/**
	 * Abstract method, will be implemented
	 * to check and see if the move is corresponding
	 * to each piece
	 * @param x the x coordinate of the destination
	 * @param y the y coordinate of the destination
	 * @return  1 if the move is legal, 0 otherwise
	 */
	public abstract int legalMovePiece(int x, int y);
	

	
	/**
	 * Method will set coordinates for a given piece
	 * @param x the new x coordinate
	 * @param y	the new y coordinate
	 */
	public void setCoordinates(int x, int y){
		xCoord = x;
		yCoord = y;		
	}
	
	/**
	 *  Method will be called when the piece is taken
	 *  @param t 1 if taken, 0 if not taken
	 */
	public void setTaken(int t){
		wasTaken = t;		
	}
	
	
	/**
	 * Method will be called after a piece has been moved
	 * for the first time
	 */
	public void setMoved(){
		firstMove = false;
	}
	
	
	/**
	 * Will give us the player number
	 * @return 0 for player1 or 1 for player2
	 */
	public int getPlayer(){
		return player;
	}
	
	/**
	 * Getter for x coordinate
	 * @return the x coordinate
	 */
	public int getX(){
		return xCoord;		
	}
	
	/**
	 * Getter for y coordinate
	 * @return the y coordinate
	 */
	public int getY(){
		return yCoord;
	}
	
	/**
	 * Method checks to see if the piece was taken or not
	 * @return 1 if it was taken or 0 if it wasn't taken
	 */
	public int isTaken(){
		return wasTaken;
	}
	
	/**
	 * Method will tell us whether the piece
	 * is at a certain location or not
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @return 1 if it is, 0 if it isn't
	 */
	public int isAt(int x, int y){
		if((xCoord == x) && (yCoord == y))
			return 1;
		else return 0;
	}
	
	/**
	 * This is for the case when a player selects undo
	 */
	public void takeBackFirstMove(){
		firstMove = true;
	}
	
	/**
	 * Method that will give the absolute value
	 * of the difference of two numbers
	 * To be used for the coordinates of the pieces
	 * @param a the first number
	 * @param b the second number
	 * @return  |a-b|
	 */
	protected int absoluteValueDifference(int a, int b){
		int diff = a - b;
		if(diff < 0){
			diff = 0 - diff;
		}
		return diff;
	}
}
