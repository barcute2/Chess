package tests;

import static org.junit.Assert.*;

import main.Board;
import main.Piece;

import org.junit.Test;
import org.junit.Before;


public class BoardPathTest {
	private Board board;
	final int PLAYER1 = 0;
	final int PLAYER2 = 1;
	
	@Before
	public void initializeBoard(){
		board = new Board(8, 8, 0);
	}
	@Test
	public void testPawns() {		
		//pawn at position 2, 2
		Piece pawnPlayer1 = board.getPieceNumber(1);
		//pawn at position 7, 2
		Piece pawnPlayer2 = board.getPieceNumber(17);
		//tests for player1's pawn		
		assertEquals(false, board.isThereAPath(pawnPlayer1, 3, 4));
		assertEquals(true, board.isThereAPath(pawnPlayer1, 4, 2));
		assertEquals(false, board.isThereAPath(pawnPlayer1, 1, 2));
		assertEquals(true, board.isThereAPath(pawnPlayer1, 3, 2));
		//tests for player2's pawn
		assertEquals(false, board.isThereAPath(pawnPlayer2, 6, 3));
		assertEquals(true, board.isThereAPath(pawnPlayer2, 5, 2));
		assertEquals(false, board.isThereAPath(pawnPlayer2, 8, 2));
		assertEquals(true, board.isThereAPath(pawnPlayer2, 6, 2));
	}
	
	@Test
	public void testKnights(){
		//knight at position 1, 2
		Piece knight = board.getPieceNumber(9);
		assertEquals(true, board.isThereAPath(knight, 2, 4));
		assertEquals(false, board.isThereAPath(knight, 1, 4));
		assertEquals(true, board.isThereAPath(knight, 3, 3));
		assertEquals(true, board.isThereAPath(knight,  3, 1));
	}
	
	@Test
	public void testKing(){
		//king is at position 1, 5
		Piece king = board.getPieceNumber(12);
		assertEquals(true, board.isThereAPath(king, 2, 4));
		assertEquals(true, board.isThereAPath(king, 1, 4));
		assertEquals(true, board.isThereAPath(king, 0, 4));
		assertEquals(true, board.isThereAPath(king,  2, 6));
		assertEquals(false, board.isThereAPath(king,  3, 6));
	}
	
	
	@Test
	public void testRooks(){
		//rook is at position 1, 1
		Piece rook = board.getPieceNumber(8);
		assertEquals(false, board.isThereAPath(rook, 3, 1));
		assertEquals(false, board.isThereAPath(rook, 3, 3));		
	}
	
	@Test
	public void testBishops(){		
		Piece bishop = board.getPieceNumber(10);
		bishop.setCoordinates(5, 5);
		assertEquals(true, board.isThereAPath(bishop, 6, 4));
		assertEquals(true, board.isThereAPath(bishop, 3, 3));
		assertEquals(true, board.isThereAPath(bishop, 6, 6));
		assertEquals(true, board.isThereAPath(bishop, 3, 7));
		assertEquals(true, board.isThereAPath(bishop, 2, 2));
		assertEquals(false, board.isThereAPath(bishop, 1, 1));
		assertEquals(false, board.isThereAPath(bishop, 1, 6));
		bishop.setCoordinates(4, 2);
		assertEquals(true, board.isThereAPath(bishop,  3, 1));
	}
	
	@Test
	public void testQueen(){
		Piece queen = board.getPieceNumber(11);
		queen.setCoordinates(5, 5);
		assertEquals(true, board.isThereAPath(queen, 6, 4));
		assertEquals(true, board.isThereAPath(queen, 3, 3));
		assertEquals(true, board.isThereAPath(queen, 6, 6));
		assertEquals(true, board.isThereAPath(queen, 3, 7));
		assertEquals(true, board.isThereAPath(queen, 2, 2));
		assertEquals(false, board.isThereAPath(queen, 1, 1));
		assertEquals(false, board.isThereAPath(queen, 1, 6));
		assertEquals(true, board.isThereAPath(queen, 5, 1));
	}
}
