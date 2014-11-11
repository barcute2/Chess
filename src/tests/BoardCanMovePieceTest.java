package tests;

import static org.junit.Assert.*;

import main.Board;
import main.Pawn;
import main.Piece;

import org.junit.Before;
import org.junit.Test;


public class BoardCanMovePieceTest {
	private Board board;
	final int PLAYER1 = 0;
	final int PLAYER2 = 1;
	
	@Before
	public void initializeBoard(){
		board = new Board(8, 8, 0);
	}
	
	@Test
	public void testPieceInTheWay() {
		Piece queen = board.pieceAt(1,4);
		assertEquals(false, board.canMovePiece(queen, 3, 4));
		assertEquals(1, queen.getX());	
		board.setTakenSquares();
	}
	
	@Test
	public void testCapture(){
		Piece queen = board.pieceAt(1, 4);
		board.setTakenSquares();
		queen.setCoordinates(3, 3);
		assertEquals(true, board.pieceAt(7, 3) instanceof Pawn);
		assertEquals(PLAYER1, queen.getPlayer());
		assertEquals(PLAYER2, board.pieceAt(7, 3).getPlayer());
		assertEquals(1, queen.legalMovePiece(7, 3));
		assertEquals(true, board.isThereAPath(queen, 7, 3));
		assertEquals(true, board.canMovePiece(queen, 7, 3));
		assertEquals(3, queen.getX());
		assertEquals(true, board.pieceAt(7, 3) instanceof Pawn);
	}
	
	@Test
	public void testPawn(){
		Piece pawn = board.pieceAt(2, 1);
		assertEquals(false, board.canMovePiece(pawn, 3, 2));
		pawn.setCoordinates(6, 5);
		assertEquals(false, board.canMovePiece(pawn, 7, 5));
		assertEquals(true, board.canMovePiece(pawn, 7, 6));
	}

}
