package tests;

import static org.junit.Assert.*;

import main.Board;

import org.junit.Before;
import org.junit.Test;


public class CheckmateTest {
	private Board board;
	final int PLAYER1 = 0;
	final int PLAYER2 = 1;
	
	@Before
	public void initializeBoard(){
		board = new Board(8, 8, 0);
	}
	@Test
	public void testCheckmate() {
		board.movePiece(2, 7, 3, 7);
		board.movePiece(7, 5, 6, 5);
		board.movePiece(3,  7,  4,  7);
		board.movePiece(2, 6, 3, 6);
		board.movePiece(3, 6, 4, 6);
		board.movePiece(8, 4, 4, 8);		
		assertEquals(true, board.isTheKingInCheck(PLAYER1));
		assertEquals(1, board.gameOver(PLAYER1));
	}

}
