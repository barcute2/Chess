package main;
/**
 * 
 */

/**
 * @author zeno
 *
 */
public class Queen extends Piece{
	/**
	 * Constructor for the Queen class,
	 * makes a call to the super class
	 * @param x the initial x coordinate
	 * @param y	the initial y coordinate
	 * @param p the player
	 */
	public Queen(int x, int y, int p){
		super(x, y, p);
	}

	
	/**
	 * Inherited method, checks if the move is right for 
	 * this type of piece
	 * @param x the x coordinate of the destination
	 * @param y the y coordinate of the destination
	 * @return  1 if the move is lega, 0 otherwise
	 */
	public int legalMovePiece(int x, int y){
		if((xCoord == x) || (yCoord == y))
			return 1;
		else if(absoluteValueDifference(xCoord, x) == absoluteValueDifference(yCoord, y))
			return 1;
		return 0;
	}
}
