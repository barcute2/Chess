package tests;

import static org.junit.Assert.*;

import main.Board;
import main.Piece;

import org.junit.Test;


public class BoardBasicTests {

	@Test
	public void testTheConstructorRightWay() {		
		Board board = new Board(8, 8, 0);
		assertEquals(8, board.getRows());
		assertEquals(8, board.getColumns());		
	}

	@Test
	public void testTheConstructorCustomWay(){
		Board board = new Board(12, 10, 0);
		assertEquals(12, board.getRows());
		assertEquals(10, board.getColumns());
	}
	
	@Test
	public void testTheConstructorWrongWay(){
		Board board = new Board(10, 0, 0);
		assertEquals(8, board.getRows());
		assertEquals(8, board.getColumns());
	}
	
	@Test
	public void testIfGettingAPieceWorks(){
		Board board = new Board(8, 8, 0);
		Piece piece = board.getPieceNumber(17);
		assertEquals(7, piece.getX());
		assertEquals(2, piece.getY());
	}
	
	@Test
	public void testIfGettingIndexWorks(){
		Board board = new Board(8,8,0);
		assertEquals(10, board.pieceAtWithIndex(1,3));
		assertEquals(28, board.pieceAtWithIndex(8, 5));
	}
}

