package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;


		/**
		 * Class that implements ActionListener,
		 * will be used for determining when to move 
		 * a piece on the board
		 * @author zeno
		 * @version 1.0
		 */
		class MoveListener implements ActionListener{
			JButton button;
			int x, y;
			boolean firstClick = true, ready = false;
			int currentX, currentY, nextX, nextY;
			JButton squares[][];
			public void setUp(JButton s[][]){
				squares = s;
			}
			public void actionPerformed(ActionEvent e){
				button = (JButton)e.getSource();
				for(int i = 0; i < 8; i++)
					for(int j = 0; j < 8; j++)
						if(button == squares[i][j]){
							x = 8 - i;
							y = j + 1;
						}
				if(firstClick == true){
					firstClick = false;
					ready = false;
					currentX = x;
					currentY = y;
				}
				else{
					firstClick = true;
					ready = true;
					nextX = x;
					nextY = y;
					//performTheMove(currentX, currentY, nextX, nextY);					
				}
			}
			public boolean isItReady(){
				return ready;
			}
			public int getCurrentX(){
				return currentX;
			}
			public int getCurrentY(){
				return currentY;				
			}
			public int getNextX(){
				return nextX;
			}
			public int getNextY(){
				return nextY;
			}
			
		}