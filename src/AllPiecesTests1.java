import static org.junit.Assert.*;

import org.junit.Test;


public class AllPiecesTests {

	final int PLAYER1 = 0;
	final int PLAYER2 = 1;
	@Test
	public void testSquare() {
		//Square class is being tested
		Square square = new Square(1, 1, 1);
		assertEquals("Taken must be 1", 1, square.isTaken());
		assertEquals("X coordinate must be 1", 1, square.getX());
		assertEquals("Y coordinate must be 1", 1, square.getY());
		square.setCoordinates(2, 3);
		assertEquals("X coordinate must be 2", 2, square.getX());
		assertEquals("Y coordinate must be 3", 3, square.getY());
	}
	
	@Test
	public void testPawn(){
		//Pawn class is being tested
		//pawn for player 1
		Pawn pawn = new Pawn(1, 1, 0);
		assertEquals("Must return 0", 0, pawn.legalMovePiece(3, 1));
		assertEquals("Must return 1", 1, pawn.legalMovePiece(2, 1));
		assertEquals("Must return 2", 2, pawn.legalMovePiece(2, 2));
		assertEquals("Must return 0", 0, pawn.legalMovePiece(1, 2));
		assertEquals("Must return 0", 0, pawn.legalMovePiece(0, 1));
		//pawn for player 2
		Pawn secondPawn = new Pawn(2, 2, 1);
		assertEquals("Must return 1", 1, secondPawn.legalMovePiece(1, 2));
		assertEquals("Must return 2", 2, secondPawn.legalMovePiece(1, 1));
		
	}
	
	@Test
	public void testBishop(){
		//Bishop class is being tested
		Bishop bishop = new Bishop(3, 2, PLAYER1);
		assertEquals("Must return 0", 0, bishop.legalMovePiece(3,  3));
		assertEquals("Must return 0", 0, bishop.legalMovePiece(4,  5));
		assertEquals("Must return 1", 1, bishop.legalMovePiece(5,  4));
		assertEquals("Must return 1", 1, bishop.legalMovePiece(1,  0));
	}
	
	@Test
	public void testRook(){
		//Rook class is being tested
		Rook rook = new Rook(1, 1, PLAYER1);
		assertEquals("Must return 0", 0, rook.legalMovePiece(2,  2));
		assertEquals("Must return 0", 0, rook.legalMovePiece(2,  4));
		assertEquals("Must return 1", 1, rook.legalMovePiece(1,  4));
		assertEquals("Must return 1", 1, rook.legalMovePiece(4,  1));
	}
	
	@Test
	public void testQueen(){
		//Queen class is being tested
		Queen queen = new Queen(1, 4, PLAYER1);
		assertEquals("Must return 0", 0, queen.legalMovePiece(3, 3));
		assertEquals("Must return 1", 1, queen.legalMovePiece(2, 3));
		assertEquals("Must return 1", 1, queen.legalMovePiece(3, 4));
		assertEquals("Must return 1", 1, queen.legalMovePiece(1, 8));
	}
	
	@Test
	public void testKnight(){
		//Knight class is being tested
		Knight knight = new Knight(1, 2, PLAYER1);
		assertEquals("Must return 0", 0, knight.legalMovePiece(3, 2));
		assertEquals("Must return 0", 0, knight.legalMovePiece(1, 4));
		assertEquals("Must return 0", 0, knight.legalMovePiece(3, 4));
		assertEquals("Must return 1", 1, knight.legalMovePiece(2, 4));
		assertEquals("Must return 1", 1, knight.legalMovePiece(2, 0));
		assertEquals("Must return 1", 1, knight.legalMovePiece(3, 1));
		assertEquals("Must return 1", 1, knight.legalMovePiece(3, 3));
	}
	
	@Test
	public void testKing(){
		//King class is being tested
		King king = new King(1, 5, PLAYER1);
		assertEquals("Must return 0", 0, king.legalMovePiece(1, 3));
		assertEquals("Must return 0", 0, king.legalMovePiece(3, 5));
		assertEquals("Must return 0", 0, king.legalMovePiece(3, 7));
		assertEquals("Must return 0", 0, king.legalMovePiece(3, 6));
		assertEquals("Must return 1", 1, king.legalMovePiece(2, 5));
		assertEquals("Must return 1", 1, king.legalMovePiece(1, 6));
		assertEquals("Must return 1", 1, king.legalMovePiece(2, 6));
		assertEquals("Must return 1", 1, king.legalMovePiece(0, 4));
		assertEquals("Must return 1", 1, king.legalMovePiece(0, 6));
	}
	
	@Test
	public void testPiece(){
		//the piece class is being tested
		//since it's abstract, the Queen sub class will be used
		Piece queen = new Queen(1, 4, PLAYER1);
		assertEquals("Must return 0", 0, queen.getPlayer());
		assertEquals("Must return 1", 1, queen.getX());
		assertEquals("Must return 4", 4, queen.getY());
		queen.setCoordinates(3,  6);
		assertEquals("Must return 3", 3, queen.getX());
		assertEquals("Must return 6", 6, queen.getY());
		assertEquals("Must return 1", 1, queen.isAt(3, 6));
		assertEquals("Must return 0", 0, queen.isAt(1, 4));
		assertEquals("Must return 0", 0, queen.isTaken());
		queen.setTaken();
		assertEquals("Must return 1", 1, queen.isTaken());
	}
	
	
	@Test
	public void testBoard(){
		//the board class is being 
		//tested on its functionality
		Board board = new Board(8, 8);
		
	}

}
