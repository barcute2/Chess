package main;
/**
 * 
 */

/**
 * @author zeno
 *
 */
public class TelePortahPotty extends Piece{	
		/**
		 * Constructor for the TelePortahPotty class
		 * @param x the initial x coordinate
		 * @param y	the initial y coordinate
		 * @param p the player
		 */
		public TelePortahPotty(int x, int y, int p){
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
			if(firstMove)
				return 1;
			else
				return 0;
		}

}
