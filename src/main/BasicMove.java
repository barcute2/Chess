package main;

/**
 * This class will just keep the basic information about a certain move
 * @author zeno
 *
 */
public class BasicMove{
	private int currentX, currentY, previousX, previousY;	
	public BasicMove(int cX, int cY, int pX, int pY){
		currentX = cX;
		currentY = cY;
		previousX = pX;
		previousY = pY;
	}
	
	public int getCurrentX(){
		return currentX;
	}
	public int getCurrentY(){
		return currentY;
	}
	public int getPreviousX(){
		return previousX;
	}
	public int getPreviousY(){
		return previousY;
	}
}