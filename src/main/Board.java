package main;
/**
 * 
 */

/**
 * @author Zeno Barcutean
 * public class which defines a chess board
 * and all the rules associated with it
 */
public class Board {
	private Square[][] squares;
	private int rows, columns, gameMode;
	//private int nextMoveX, nextMoveY;
	private Piece[] pieces;
	private final int PLAYER1 = 0;
	private final int PLAYER2 = 1;
	private final int STANDARD = 0;
	private final int PING = 1;	

	
	/**
	 * Constructor for the class
	 * @param r	the number of rows	 
	 * @param c	the number of columns
	 */
	public Board(int r, int c, int g){		
		if((r <= 0) || (c <= 0)){
			rows = 8;
			columns = 8;
		}
		else{
			rows = r;
			columns = c;
		}
		gameMode = g;
		squares = new Square[rows][columns];	
		pieces = new Piece[4*columns];
		initializeSquares();
		initializePieces();
	}
	
	/**
	 * Method that will initialize the squares with their 
	 * coordinates and whether they're taken or not
	 */
	public void initializeSquares(){
		int i, j;
		for(i = 0; i < rows; i++)
			for(j = 0; j < columns; j++){
				squares[i][j] = new Square();
				squares[i][j].setCoordinates(i+1, j+1);
			}
		setTakenSquares();		
	}
	
	/**
	 * Method that will initialize the pieces with their coordinates and
	 * each belonging to which player
	 */
	public void initializePieces(){
		int i;		
		//initialize pawns		
		for(i = 1; i <= 8; i++){
			pieces[i-1] = new Pawn(2, i, PLAYER1);
			pieces[15+i] = new Pawn(7, i, PLAYER2);
		}
		//initialize the rooks
		pieces[8] = new Rook(1, 1, PLAYER1);
		pieces[15] = new Rook(1, 8, PLAYER1);
		pieces[24] = new Rook(8, 1, PLAYER2);
		pieces[31] = new Rook(8, 8, PLAYER2);
		//initialize the knights, pings or nukes
		if(gameMode == STANDARD){	
			pieces[9] = new Knight(1, 2, PLAYER1);
			pieces[14] = new Knight(1, 7, PLAYER1);
			pieces[25] = new Knight(8, 2, PLAYER2);
			pieces[30] = new Knight(8, 7, PLAYER2);
		}
		else if(gameMode == PING){
			pieces[9] = new Ping(1, 2, PLAYER1);
			pieces[14] = new Ping(1, 7, PLAYER1);
			pieces[25] = new Ping(8, 2, PLAYER2);
			pieces[30] = new Ping(8, 7, PLAYER2);
		}
		else{
			pieces[9] = new TelePortahPotty(1, 2, PLAYER1);
			pieces[14] = new TelePortahPotty(1, 7, PLAYER1);
			pieces[25] = new TelePortahPotty(8, 2, PLAYER2);
			pieces[30] = new TelePortahPotty(8, 7, PLAYER2);
		}
		//initialize the bishops
		pieces[10] = new Bishop(1, 3, PLAYER1);
		pieces[13] = new Bishop(1, 6, PLAYER1);
		pieces[26] = new Bishop(8, 3, PLAYER2);
		pieces[29] = new Bishop(8, 6, PLAYER2);
		//initialize the queens
		pieces[11] = new Queen(1, 4, PLAYER1);
		pieces[27] = new Queen(8, 4, PLAYER2);
		//initialize the kings
		pieces[12] = new King(1, 5, PLAYER1);
		pieces[28] = new King(8, 5, PLAYER2);
	}
	
	/**
	 * The squares in the upper two rows and bottom two rows
	 * get set as taken, for the beginning of the game
	 */
	public void setTakenSquares(){
		int j;
		for(j = 0; j < columns; j++){
			squares[0][j].setTaken(1);
			squares[1][j].setTaken(1);
			squares[rows - 2][j].setTaken(1);
			squares[rows - 1][j].setTaken(1);
		}
	}
	
	/**
	 * Setter for the squares
	 * @param x	the x coordinate of the square
	 * @param y	the y coordinate of the square
	 * @param t	value of 1 or 0 if the square is taken or not taken
	 */
	public void setSquareTaken(int x, int y, int t){
		squares[x-1][y-1].setTaken(t);
	}
	
	
	/**
	 * Will return whether a square is taken or not
	 * @param x	the x coordinate of the square
	 * @param y	the y coordinate of the square
	 * @return 1 if the square is taken and 0 otherwise
	 */
	public int getSquareStatus(int x, int y){
		return squares[x-1][y-1].isTaken();
	}
	
	/**
	 * Getter for the number of rows
	 * @return	number of rows
	 */
	public int getRows(){
		return rows;
	}
	
	/**
	 * Getter for the number of columns
	 * @return	number of columns
	 */
	public int getColumns(){
		return columns;
	}
	
	/**
	 * Getter for the piece at position i
	 * @param i	index of the piece
	 * @return	the piece located at that index
	 */
	public Piece getPieceNumber(int i){
		return pieces[i];
	}
	
	/**
	 * Returns the piece at a certain location
	 * @param x	the x coordinate of the piece
	 * @param y	the y coordinate of the piece
	 * @return	the piece located at position (x,y)
	 */
	public Piece pieceAt(int x, int y){
		int i;
		for(i = 0; i < 4 * columns; i++)
			if((pieces[i].getX() == x) && (pieces[i].getY() == y))
				return pieces[i];
		return null;
	}
	
	/**
	 * Returns the piece at a certain location
	 * @param x	the x coordinate of the piece
	 * @param y	the y coordinate of the piece
	 * @return	the index of the piece or -1 in case of failure
	 */
	public int pieceAtWithIndex(int x, int y){
		int i;
		for(i = 0; i < 4 * columns; i++)
			if((pieces[i].getX() == x) && (pieces[i].getY() == y))
				return i;
		return -1;
	}
	
	public Piece pieceFromIndex(int index){
		return pieces[index];
	}

	/**
	 * Method to be called in the main loop of the game
	 * @param pieceX	the x coordinate of the piece
	 * @param pieceY	the y coordinate of the piece
	 * @param newX		the x coordinate of where the piece will move
	 * @param newY		the y coordinate of where the piece will move
	 * @param player	the player whose turn is to move
	 * @return			0 if the player cannot perform the move, 1 otherwise
	 */
	public int turnOfPlayer(int pieceX, int pieceY, int newX, int newY, int player){
		
		//checkmate or stalemate?
		if(gameOver(pieceAt(pieceX, pieceY).getPlayer()) > 0)
			return 0;
		//if there is no such piece, return 0
		int pieceIndex = pieceAtWithIndex(pieceX, pieceY);
		if(pieceIndex == -1)
			return 0;
		
		//if attempt to move the piece of the other player, return 0
		if((pieceIndex < 16) && (player == PLAYER2))
			return 0;
		if((pieceIndex >= 16) && (player == PLAYER1))
			return 0;
		
		//made it so far, move the piece
		int result;
		result = movePiece(pieceX, pieceY, newX, newY);	
		if(result == 1)
			pieceAt(newX, newY).setMoved();
		return result;
	}
	
	
	/**
	 * Method will perform the move if it is possible
	 * @param x		the x coordinate of the piece
	 * @param y		the y coordinate of the piece
	 * @param newX	the x coordinate of where the piece will move
	 * @param newY	the y coordinate of where the piece will move
	 * @return		0 in case the move failed, 1 if the move was successful
	 */
	public int movePiece(int x, int y, int newX, int newY){		
		//validate the move, make sure it doesn't go off the board
		if((newX < 1) || (newY < 1))
			return 0;
		if((newX > rows) || (newY > columns))
			return 0;
		//move is within the board
		Piece piece = pieceAt(x, y);
		if(piece == null)
			return 0;
		if(piece.isTaken() == 1)
			return 0;
		//if we didn't find one, do nothing
		//if found, check if the move is legal		
		if(canMovePiece(piece, newX, newY)){
			//if the destination is free, go!
			if(squares[newX - 1][newY - 1].isTaken() == 0){
				squares[x-1][y-1].setTaken(0);
				piece.setCoordinates(newX, newY);
				squares[newX - 1][newY - 1].setTaken(1);				
				return 1;
			}
			else{
				return tryCapture(piece, newX, newY);
			}			
		}
		return 0;
	}
	
	

	
	
	/**
	 * Method which will determine if the capture
	 * of the other player's piece is possible
	 * @param piece	the piece that has to move
	 * @param newX	x coordinate of the piece that will be captured
	 * @param newY	y coordinate of the piece that will be captured
	 * @return		0 if failed to capture, 1 on success
	 */
	public int tryCapture(Piece piece, int newX, int newY){			
		Piece destination = pieceAt(newX, newY);	
		if(destination instanceof King)
			return 0;
		if(piece.getPlayer() != destination.getPlayer()){	
			destination.setTaken(1);
			destination.setCoordinates(-1, -1);
			squares[piece.getX() - 1][piece.getY() - 1].setTaken(0);
			piece.setCoordinates(newX, newY);			
			return 1;
		}
		return 0;
	}

	

	
	/**
	 * Method that will find whether the king is in check or not
	 * @param player	the player whose king we're checking
	 * @return			true if check, false if not
	 */
	public boolean isTheKingInCheck(int player){
		int i, j;
		int kingX, kingY;
		if(player == PLAYER1){
			i = 16;
			kingX = pieces[12].getX();
			kingY = pieces[12].getY();
		}
		else{
			i = 0;
			kingX = pieces[28].getX();
			kingY = pieces[28].getY();
		}
		//check the pawns first
		for(j = 0; j < 8; j++)
			if(pieces[i+j].legalMovePiece(kingX, kingY) == 2)
				return true;
		//now check the rest of the pieces
		for(j = 8; j < 16; j++){
			if((isThereAPath(pieces[i+j], kingX, kingY)) && (pieces[i+j].isTaken() == 0))
				return true;			
		}
		return false;
	}
	
	
	/**
	 * Will determine if the game is over
	 * @param player	the player whose turn is to move
	 * @return			0 if the game can continue
	 * 					1 in case of checkmate
	 * 					2 in case of stalemate
	 */
	public int gameOver(int player){
		int i, j;
		int rowCounter, columnCounter;		
		if(player == PLAYER1){
			i = 0;			
		}
		else{
			i = 16;			
		}
		for(rowCounter = 1; rowCounter <= rows; rowCounter++)
			for(columnCounter = 1; columnCounter <= columns; columnCounter++)
					for(j = 0; j < 16; j++)
						if(canMovePiece(pieces[i+j], rowCounter, columnCounter))
							return 0;
		if(isTheKingInCheck(player))
			return 1;
		else
			return 2;
	}
	
	/**
	 * Method to be used in determining 
	 * possible moves in case of checkmate/stalemate
	 * @param piece	the piece that will move
	 * @param x		the x coordinate of the destination
	 * @param y		the y coordinate of the destination
	 * @return		true if the piece can be moved there, false otherwise
	 */
	public boolean canMovePiece(Piece piece, int x, int y){
		if(thereAreProblems(piece, x, y))
			return false;
		//we made it this far, let's simulate the move and
		//see if we will be in check
		int currentX, currentY;
		currentX = piece.getX();
		currentY = piece.getY();
		boolean check;
		if(squares[x-1][y-1].isTaken() == 0){
			simulateMove(piece, x, y);
			check = isTheKingInCheck(piece.getPlayer());			
			bringPieceBack(piece, currentX, currentY, x, y);			
			if(check)
				return false;
		}
		//we need to also simulate capturing a piece
		else{			
			Piece capturedPiece = pieceAt(x, y);			
			capturedPiece.setCoordinates(0,  0);
			capturedPiece.setTaken(1);						
			simulateMove(piece, x, y);
			check = isTheKingInCheck(piece.getPlayer());
			capturedPiece.setCoordinates(x, y);
			capturedPiece.setTaken(0);
			bringPieceBack(piece, currentX, currentY, x, y);
			squares[x-1][y-1].setTaken(1);
			if(check)
				return false;
		}		
		return true;
	}
	
	/**
	 * 
	 * @param piece piece we are checking
	 * @param x		new x coordinate
	 * @param y		new y coordinate
	 * @return		whether there are any problems with the move
	 */
	public boolean thereAreProblems(Piece piece, int x, int y){
		if((squares[x-1][y-1].isTaken() == 1) && (pieceAt(x, y).getPlayer() == piece.getPlayer()))
			return true;
		if(piece.legalMovePiece(x, y) == 0)
			return true;
		if((piece instanceof Rook) || (piece instanceof Bishop) || (piece instanceof Queen))	
			if(isThereAPath(piece, x, y) == false)
				return true;
		if(piece instanceof Pawn){
			if((piece.legalMovePiece(x,y) == 2) && (piece.getY() == y))
				return true;
			if((piece.legalMovePiece(x, y) == 2) && (squares[x-1][y-1].isTaken() == 0)) 
				return true;
			if((piece.legalMovePiece(x, y) == 1) && (squares[x-1][y-1].isTaken() == 1))
				return true;
		}
		return false;
	}
	
	/**
	 * Simulates move to a certain square
	 * @param piece the piece selected by player
	 * @param x		new x coordinate
	 * @param y		new y coordinate
	 */
	public void simulateMove(Piece piece, int x, int y){
		int currentX, currentY;
		currentX = piece.getX();
		currentY = piece.getY();
		squares[x-1][y-1].setTaken(1);
		squares[currentX-1][currentY-1].setTaken(0);
		piece.setCoordinates(x, y);
	}
	
	/**
	 * Bring the piece back after simulating the move
	 * @param piece		selected piece
	 * @param currentX	where the piece was prior to simulation, in x
	 * @param currentY	where the piece was prior to simulation, in y
	 * @param x			new x
	 * @param y			new y
	 */
	public void bringPieceBack(Piece piece, int currentX, int currentY, int x, int y){
		squares[x-1][y-1].setTaken(0);		
		squares[currentX-1][currentY-1].setTaken(1);
		piece.setCoordinates(currentX, currentY);
	}
	
	/**
	 * Method will determine if there is a 
	 * path for a piece from its original position
	 * to the new position.
	 * @param piece	piece that will have to move
	 * @param x		the x coordinate of where the piece has to move
	 * @param y		the y coordinate of where the piece has to move
	 * @return		false if there are pieces in the way, true otherwise
	 */
	public boolean isThereAPath(Piece piece, int x, int y){
		if(piece.isTaken() == 1)
			return false;
		if((piece instanceof Pawn) || (piece instanceof King) || (piece instanceof Knight))
			if(piece.legalMovePiece(x, y) == 1)
				return true;
		if((piece instanceof Rook) && (pathForRook(piece, x, y)))
				return true;
		if((piece instanceof Bishop) && (pathForBishop(piece, x, y)))
				return true;
		if(piece instanceof Queen)
			if((piece.getX() == x) || (piece.getY() == y))
				return pathForRook(piece, x, y);
			else
				return pathForBishop(piece, x, y);
		//the two custom pieces get their own stuff
		if((piece instanceof TelePortahPotty) && (pieceAt(x,y) instanceof King))
			return false;
		if((piece instanceof Ping || (piece instanceof TelePortahPotty)))
			if(piece.legalMovePiece(x, y) == 1)
				return true;
		return false;
	}
	
	/**
	 * Method which will check if a rook
	 * can move on a certain row or column
	 * @param piece	rook that will move
	 * @param x		the x coordinate of where the rook will move
	 * @param y		the y coordinate of where the rook will move
	 * @return		false if there are pieces in the way of the rook, true otherwise
	 */
	public boolean pathForRook(Piece piece, int x, int y){
		int i, distance, start;
		if(piece.legalMovePiece(x, y) == 0)
			return false;
		//if we are moving on the same row
		if(piece.getX() == x){
			distance = piece.absoluteValueDifference(piece.getY(), y);
			start = rookMoveStartLocation(piece.getY(), y);					
			for(i = 1; i < distance; i++)
				if(squares[piece.getX() - 1][start + i - 1].isTaken() == 1)
					return false;	
		}
		//if we are moving on the same column
		else{
			distance = piece.absoluteValueDifference(piece.getX(), x);
			start = rookMoveStartLocation(piece.getX(), x);						for(i = 1; i < distance; i++)
				if(squares[start + i - 1][piece.getY() - 1].isTaken() == 1)
					return false;	
		}
		
		return true;
	}
	
	/**
	 * Determines the start location for the loop
	 * @param oldCoordinate
	 * @param newCoordinate
	 * @return the start location for the loop
	 */
	public int rookMoveStartLocation(int oldCoordinate, int newCoordinate){
		int start;		
		if(newCoordinate < oldCoordinate)
			start = newCoordinate;
		else
			start = oldCoordinate;
		//are there pieces in the way?
		return start;
	}
	
	/**
	 * Method which will determine
	 * if a bishop can move on a certain
	 * diagonal
	 * @param piece	bishop that will move
	 * @param x		x coordinate where the bishop will move
	 * @param y		y coordinate where the bishop will move
	 * @return		false if there are pieces in the bishop's way, true otherwise
	 */
	public boolean pathForBishop(Piece piece, int x, int y){
		int distance;
		if(piece.legalMovePiece(x, y) == 0)
			return false;
		distance = piece.absoluteValueDifference(piece.getY(), y);
		//if bishop is moving down
		if(piece.getX() > x)
			//if moving to the left
			if(piece.getY() > y){				
				if(bishopHelperPathFunction(distance, x, 1, 0, y, 1, 0) == false)
					return false;
			}
			//if moving to the right
			else{
				if(bishopHelperPathFunction(distance, piece.getX(), -1, -2, piece.getY(), 1, 0) == false)
					return false;
			}
		//if bishop is moving up
		else
			//and to the left
			if(piece.getY() > y){
				if(bishopHelperPathFunction(distance, piece.getX(), 1, 0, piece.getY(), -1, -2) == false)
					return false;
			}
			//and to the right
			else{
				if(bishopHelperPathFunction(distance, piece.getX(), 1, 0, piece.getY(), 1, 0) == false)
					return false;
			}
		return true;
	}	
	
	private boolean bishopHelperPathFunction(int distance, int x, int iXMultiple, int iXAddition, int y, int iYMultiple, int iYAddition){
		for(int i = 0; i < distance - 1; i++)
			if(squares[x + i*iXMultiple + iXAddition][y + i*iYMultiple + iYAddition].isTaken() == 1)
				return false;
		return true;
	}
}

