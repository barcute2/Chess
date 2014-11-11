package GUI;


/**
 * @author zeno
 *
 */
	public class MovePerformed{
			private int previousX, previousY, currentX, currentY, movedPieceIndex, capturedPieceIndex, player;
			public MovePerformed(int px, int py, int cx, int cy, int mpi, int cpi, int p){
				previousX = px;
				previousY = py;
				currentX = cx;
				currentY = cy;
				movedPieceIndex = mpi;
				capturedPieceIndex = cpi;
				player = p;
			}
			public int getPreviousX(){
				return previousX;
			}
			public int getPreviousY(){
				return previousY;
			}
			public int getCurrentX(){
				return currentX;
			}
			public int getCurrentY(){
				return currentY;
			}
			public int getMovedPieceIndex(){
				return movedPieceIndex;				
			}
			public int getCapturedPieceIndex(){
				return capturedPieceIndex;
			}
			public int getPlayer(){
				return player;
			}
		}