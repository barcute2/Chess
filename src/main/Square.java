package main;

/**
 * 
 * @author Zeno
 * inside class which defines a square 
 * to be used on the chess board
 */

public class Square{
	private int taken;
	private int xCoord;
	private int yCoord;
	
	
	/**
	 * Default constructor for the class
	 * sets 'taken' to 0, since the square is considered free
	 */
	public Square(){
		taken = 0;
		xCoord = 0;
		yCoord = 0;
	}
	
	/** 
	 * Constructor for the class with parameters
	 * @param t	1 if the square is taken, 0 if not taken
	 * @param x the x coordinate of the square
	 * @param y	the y coordinate of the square
	 */
	public Square(int t, int x, int y){
		taken = t;
		xCoord = x;
		yCoord = y;		
	}
	
	/**
	 * Method that will set the coordinates for 
	 * a square on the board
	 * @param x which row on the board we are at	 * 
	 * @param y which column on the board we are at
	 */
	public void setCoordinates(int x, int y){
		xCoord = x;
		yCoord = y;		
	}
	
	/**
	 * Method that will be used each time 
	 * the 'taken' status of a square changes
	 * @param t 1 if the square is taken, 0 if not taken
	 */
	public void setTaken(int t){
		taken = t;		
	}
	
	/**
	 * Method that will return whether a square 
	 * is taken or not
	 * @return 1 if taken, 0 if not taken
	 */
	public int isTaken(){
		return taken;		
	}	
	
	/**
	 * Getter for the x coordinate
	 * @return the row where the square is located
	 */
	public int getX(){
		return xCoord;
	}
	
	/**
	 * Getter for the y coordinate
	 * @return the column where the square is located
	 */
	public int getY(){
		return yCoord;
	}
}