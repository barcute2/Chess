package tests;

import static org.junit.Assert.*;

import main.Board;
import main.Piece;

import org.junit.Before;
import org.junit.Test;


public class BoardMoveAndCaptureTest {
	private Board board;
	final int PLAYER1 = 0;
	final int PLAYER2 = 1;
	
	@Before
	public void initializeBoard(){
		board = new Board(8, 8, 0);
	}
	
	
	@Test
	public void testQueenMove() {
		//coordinates for the queen are 1, 4, player1
		Piece queen = board.getPieceNumber(11);
		board.movePiece(1, 4, 4, 7);
		assertEquals(1, queen.getX());
		queen.setCoordinates(3, 3);
		board.setSquareTaken(1,4,0);
		board.setSquareTaken(3,3,1);
		assertEquals(true, board.isThereAPath(queen, 5, 3));
		assertEquals(3, board.pieceAt(3, 3).getX());
		assertEquals(1, board.movePiece(3,  3,  5,  3));		
		assertEquals(5, queen.getX());
		queen.setCoordinates(0, 0);
		board.setSquareTaken(5,3,0);
		board.setTakenSquares();
	}
		
	@Test
	public void testRookMove(){
		//now test the rook
		Piece rook = board.pieceAt(1,1);
		rook.setCoordinates(3, 3);
		board.setSquareTaken(1,1,0);
		board.setSquareTaken(3,3,1);
		board.movePiece(3, 3, 5, 3);
		assertEquals(5, rook.getX());
		assertEquals(0, board.getSquareStatus(3, 3));
		board.movePiece(5, 3, 5, 5);
		assertEquals(5, rook.getY());
		rook.setCoordinates(0, 0);
		board.setTakenSquares();
	}
	
	@Test
	public void testKnightMove(){		
		//coordinates for the knight are 1, 2, player1
		Piece knight = board.getPieceNumber(9);
		assertEquals(1, knight.getX());
		assertEquals(2, knight.getY());
		assertEquals(true, board.isThereAPath(knight, 2, 4));
		board.movePiece(1,  2,  3,  3);
		assertEquals(3, knight.getX());
		board.movePiece(3, 3, 2, 5);
		knight.setCoordinates(0,  0);
		board.setTakenSquares();
	}
	
	@Test
	public void testBishopMove(){
		//now we test the bishop
		Piece bishop = board.pieceAt(1,3);
		bishop.setCoordinates(3, 3);
		board.setSquareTaken(1,3,0);
		board.setSquareTaken(3,3,1);
		board.movePiece(3,  3, 5, 5);
		assertEquals(5, bishop.getX());
		board.movePiece(5, 5, 3, 4);
		assertEquals(5, bishop.getY());
		bishop.setCoordinates(0, 0);
		board.setTakenSquares();
	}
	
	@Test
	public void testKingMove(){
		//now test the king
		Piece king = board.getPieceNumber(12);
		king.setCoordinates(3, 3);
		board.setSquareTaken(1,5,0);
		board.setSquareTaken(3,3,1);
		board.movePiece(3, 3, 4, 4);
		assertEquals(4, king.getX());
		board.movePiece(4, 4, 6, 5);
		assertEquals(4, king.getX());
		board.setTakenSquares();
	}
	
	@Test
	public void testPawnMove(){
		//now test the pawn
		Piece pawn = board.pieceAt(2,1);
		pawn.setCoordinates(3, 3);
		board.setSquareTaken(2,1,0);
		board.setSquareTaken(3,3,1);
		board.movePiece(3, 3, 4, 4);
		assertEquals(3, pawn.getX());
		board.movePiece(3, 3, 4, 3);
		assertEquals(4, pawn.getX());
		board.setTakenSquares();
	}
	
	@Test
	public void testQueenCapture(){
		//the queen will have to capture a piece
		Piece queen = board.pieceAt(1, 4);
		queen.setCoordinates(3, 3);
		board.setSquareTaken(1,4,0);
		board.setSquareTaken(3,3,1);
		board.movePiece(3, 3, 7, 3);
		assertEquals(7, queen.getX());
		board.movePiece(7, 3, 2, 3);
		assertEquals(7, queen.getX());
		board.setTakenSquares();
	}
	
	@Test
	public void testRookCapture(){
		Piece rook = board.pieceAt(1, 1);
		rook.setCoordinates(3, 3);
		board.setSquareTaken(1,1,0);
		board.setSquareTaken(3,3,1);
		board.movePiece(3, 3, 7, 3);
		assertEquals(7, rook.getX());
		board.movePiece(7, 3, 2, 3);
		assertEquals(7, rook.getX());
		board.setTakenSquares();
	}
	
	@Test
	public void testBishopCapture(){
		Piece bishop = board.pieceAt(1, 3);
		bishop.setCoordinates(3,3);
		board.setSquareTaken(1,3,0);
		board.setSquareTaken(3,3,1);
		board.movePiece(3, 3, 7, 7);
		assertEquals(7, bishop.getX());
		board.movePiece(7, 7, 2, 2);
		assertEquals(7, bishop.getX());
	}
	
	@Test
	public void testKnightCapture(){
		Piece knight = board.pieceAt(1, 2);
		knight.setCoordinates(5,3);
		board.setSquareTaken(1,2,0);
		board.setSquareTaken(5,3,1);
		assertEquals(5, knight.getX());
		assertEquals(3, knight.getY());		
		assertEquals(1, board.movePiece(5, 3, 7, 4));
		
		assertEquals(7, knight.getX());
		knight.setCoordinates(3, 3);
		board.setSquareTaken(7,4,0);
		board.setSquareTaken(3,3,1);
		board.movePiece(3, 3, 2, 4);
		assertEquals(3, knight.getX());
	}
	
	@Test
	public void testKingCapture(){
		Piece king = board.pieceAt(1, 5);
		board.movePiece(1, 5, 2, 5);
		assertEquals(1, king.getX());
		king.setCoordinates(6,5);
		board.setSquareTaken(1,5,0);
		board.setSquareTaken(6,5,1);
		board.movePiece(6, 5, 7, 5);
		assertEquals(6, king.getX());		
	}
	
	@Test
	public void testPawnCapture(){
		Piece pawn = board.pieceAt(2,1);		
		pawn.setCoordinates(6,5);
		board.setSquareTaken(2,1,0);
		board.setSquareTaken(6,5,1);
		board.movePiece(6, 5, 7, 5);
		assertEquals(6, pawn.getX());
		board.movePiece(6, 5, 7, 6);
		assertEquals(7, pawn.getX());
	}
	
	@Test
	public void testGameCapture(){
		board.movePiece(2, 4, 4, 4);
		board.movePiece(7, 4, 5, 4);
		board.movePiece(2, 3, 4, 3);
		board.movePiece(7, 3, 6, 3);
		//first take here
		board.movePiece(4, 3, 5, 4);
		assertEquals(0, board.pieceAt(5,4).getPlayer());
		assertEquals(2, board.pieceAtWithIndex(5, 4));
		//second take
		assertEquals(1, board.movePiece(6, 3, 5, 4));
		assertEquals(2, board.pieceAtWithIndex(5, 4));
		assertEquals(1, board.pieceAt(5,4).getPlayer());
		assertEquals(1, board.movePiece(2,5,4,5));
		assertEquals(1, board.pieceAt(5,4).getPlayer());
		assertEquals(true, board.pieceAt(5,4).getPlayer() != board.pieceAt(4, 5).getPlayer());
		assertEquals(1, board.tryCapture(board.pieceAt(5, 4), 4, 5));
		assertEquals(1, board.movePiece(5, 4, 4, 5));
	}
}
