package main;
/**
 * 
 */

/**
 * @author zeno
 *
 */
public class Knight extends Piece{
	
	/**
	 * Constructor for the Knight class
	 * @param x the initial x coordinate
	 * @param y	the initial y coordinate
	 * @param p the player
	 */
	public Knight(int x, int y, int p){
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
		if((absoluteValueDifference(xCoord, x) == 1) && (absoluteValueDifference(yCoord, y) == 2)){
			return 1;
		}
		if((absoluteValueDifference(xCoord, x) == 2) && (absoluteValueDifference(yCoord, y) == 1)){
			return 1;
		}
		return 0;
	}
}
