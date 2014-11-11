package tests;

import static org.junit.Assert.*;

import main.Board;
import main.Piece;

import org.junit.Before;
import org.junit.Test;


public class CheckTest {
	private Board board;
	final int PLAYER1 = 0;
	final int PLAYER2 = 1;
	
	@Before
	public void initializeBoard(){
		board = new Board(8, 8, 0);
	}
	
	@Test
	public void testIfInCheckByPawn() {
		Piece king = board.pieceAt(8, 5);
		king.setCoordinates(5, 5);
		Piece pawn = board.pieceAt(2, 1);
		pawn.setCoordinates(4, 4);
		assertEquals(true, board.isTheKingInCheck(PLAYER2));
		pawn.setCoordinates(4, 5);
		assertEquals(false, board.isTheKingInCheck(PLAYER2));
	}
	
	@Test
	public void testIfInCheckByQueen() {
		Piece king = board.pieceAt(8, 5);
		king.setCoordinates(5, 5);
		Piece queen = board.pieceAt(1, 4);
		queen.setCoordinates(3, 5);
		assertEquals(true, board.isTheKingInCheck(PLAYER2));
		queen.setCoordinates(5, 7);
		assertEquals(true, board.isTheKingInCheck(PLAYER2));
	}
	
	@Test
	public void testIfInCheckByRook() {
		Piece king = board.pieceAt(8, 5);
		king.setCoordinates(5, 5);
		Piece rook = board.pieceAt(1, 1);
		rook.setCoordinates(3, 5);
		assertEquals(true, board.isTheKingInCheck(PLAYER2));
		rook.setCoordinates(5, 7);
		assertEquals(true, board.isTheKingInCheck(PLAYER2));
	}
	
	@Test
	public void testIfInCheckByBishop() {
		Piece king = board.pieceAt(8, 5);
		king.setCoordinates(5, 5);
		Piece bishop = board.pieceAt(1, 3);
		bishop.setCoordinates(3, 3);
		assertEquals(true, board.isTheKingInCheck(PLAYER2));		
	}
	
	@Test
	public void testIfInCheckByKnight() {
		Piece king = board.pieceAt(8, 5);
		king.setCoordinates(5, 5);
		Piece knight = board.pieceAt(1, 2);
		knight.setCoordinates(3, 4);
		assertEquals(true, board.isTheKingInCheck(PLAYER2));
		knight.setCoordinates(3, 7);
		assertEquals(false, board.isTheKingInCheck(PLAYER2));
	}

}
