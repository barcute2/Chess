package main;
/**
 * 
 */

/**
 * @author zeno
 *
 */
public class Pawn extends Piece {
	
	/**
	 * Constructor for the Pawn class,
	 * makes a call to the super class
	 * @param x the initial x coordinate
	 * @param y	the initial y coordinate
	 * @param p the player
	 */
	public Pawn(int x, int y, int p){
		super(x, y, p);			
	}

	
	/**
	 * Inherited method, checks if the move is right for 
	 * this type of piece
	 * @param x the x coordinate of the destination
	 * @param y the y coordinate of the destination
	 * @return  1 if the move is legal, 0 otherwise  
	 */
	public int legalMovePiece(int x, int y){
		boolean playerDependent = false;
		if(((player == PLAYER1) && (x > xCoord))||((player == PLAYER2) && (x < xCoord)))
			playerDependent = true;
		if((absoluteValueDifference(xCoord, x) == 1) && (playerDependent)){
			if(yCoord == y)
				return 1;
			else if(absoluteValueDifference(yCoord, y) == 1)
				return 2;
		}
		if((absoluteValueDifference(xCoord, x) == 2) && (playerDependent))
			if((yCoord == y) && (firstMove == true))
				return 1;
		return 0;
	}

}
