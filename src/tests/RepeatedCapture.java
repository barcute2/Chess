package tests;

import static org.junit.Assert.*;

import main.Board;
import main.Piece;

import org.junit.Before;
import org.junit.Test;

public class RepeatedCapture {
	private Board board;
	final int PLAYER1 = 0;
	final int PLAYER2 = 1;
	
	@Before
	public void initializeBoard(){
		board = new Board(8, 8, 0);
	}
	
	@Test
	public void testSingle() {
		board.turnOfPlayer(2, 4, 4, 4, PLAYER1);
		board.turnOfPlayer(7, 3, 5, 3, PLAYER2);
		board.simulateMove(board.pieceAt(4, 4), 5, 3);
		assertEquals(3, board.pieceAtWithIndex(5, 3));
		board.bringPieceBack(board.pieceAt(5, 3), 4, 4, 5, 3);
		assertEquals(3, board.pieceAtWithIndex(4, 4));		
	}
	
	@Test
	public void testSingleMoveWithCapture(){
		Piece pawn = board.pieceAt(7, 3);
		board.turnOfPlayer(2, 4, 4, 4, PLAYER1);
		assertEquals(1, board.getSquareStatus(4, 4));
		board.turnOfPlayer(7, 3, 5, 3, PLAYER2);
		assertEquals(true, board.canMovePiece(board.pieceAt(5, 3), 4, 4));
		assertEquals(1, board.getSquareStatus(4, 4));
		assertEquals(18, board.pieceAtWithIndex(5, 3));
		//assertEquals(1, board.tryCapture(board.pieceAt(5, 3), 4, 4));
		assertEquals(2, pawn.legalMovePiece(4, 4));
		assertEquals(1, board.getSquareStatus(4, 4));
		assertEquals(false, board.thereAreProblems(pawn, 4, 4));
		assertEquals(true, board.canMovePiece(board.pieceAt(5, 3), 4, 4));
		assertEquals(1, board.movePiece(5, 3, 4, 4));		
	}
	
	@Test
	public void testDouble(){
		board.turnOfPlayer(2, 4, 4, 4, PLAYER1);
		board.turnOfPlayer(7, 3, 5, 3, PLAYER2);
		board.turnOfPlayer(7, 2, 6, 2, PLAYER2);
		board.turnOfPlayer(4, 4, 5, 3, PLAYER1);
		assertEquals(3, board.pieceAtWithIndex(5, 3));
		assertEquals(true, board.canMovePiece(board.pieceAt(5, 3), 6, 2));
	}

}
