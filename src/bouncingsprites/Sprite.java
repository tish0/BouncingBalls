/* File Name:Sprite
 * Author Name: Algonquin College
 * Modified By:  Tihomir Penev
 * Date: 03.02.2017
 * Description: This class makes balls that move into the window and bounce on the 4 sides of it.
 * also there is as statement that decides what is the position of the ball to the circle
 */

package bouncingsprites;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Sprite implements Runnable{     // changed to implements runnable

	public final static Random random = new Random();

	final static int SIZE = 20;// size of the diameter of the sprite
	final static int MAX_SPEED = 5;// speed of the sprite
	private boolean inCircle; // declare boolean that we gonna use to decide whether the ball is in or out
	SpritePanel panel;
	private int x;
	private int y;
	private int dx;
	private int dy;
	private Color color = Color.BLUE;


	/**
	 * @param panel
	 */
	public Sprite (SpritePanel panel)
	{
		this.panel = panel;
		boolean inCircle = false; //Create all sprites with status outside the circle
		x = random.nextInt(panel.getWidth());
		y = random.nextInt(panel.getHeight());
		dx = random.nextInt(2*MAX_SPEED) - MAX_SPEED;
		dy = random.nextInt(2*MAX_SPEED) - MAX_SPEED;
	}

	public void draw(Graphics g){
		g.setColor(color);
		g.fillOval(x, y, SIZE, SIZE);
	}
	// using run instead of move method because of implementing runnable interface 
	@Override
	public void run() {

		int ballx, bally;// balls x and y coordinates

		while(true){
			//each call increases the position of (x,y) of the ball and the call to repaint()
			ballx = x;
			bally = y;
			move();// calling move method to start

			//Calculating the distance between Circle and the Sprite
			double	distance = Math.sqrt(((((SpritePanel.Oval_X - ballx)  * (SpritePanel.Oval_X - ballx))) + 
					(SpritePanel.Oval_Y - bally) * (SpritePanel.Oval_Y - bally)));

			//Checking if the ball is entering the circle

			if(distance < Math.abs(SpritePanel.Oval_R - SIZE/2)){

				inCircle = true;

				try {
					panel.consume();
					System.out.println("true");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			//Checking if the ball is outside the circle
			else if (distance > Math.abs(SpritePanel.Oval_R - SIZE/2))
			{
				inCircle = false;

				try {
					panel.produce();
					System.out.println("false");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}

			try {
				Thread.sleep(40); // wake up roughly 25 frames per second
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}

	}// end run method

	public void move(){


		// check for bounce and make the ball bounce if necessary
		//
		if (x < 0 && dx < 0){
			//bounce off the left wall 
			x = 0;
			dx = -dx;
		}
		if (y < 0 && dy < 0){
			//bounce off the top wall
			y = 0;
			dy = -dy;
		}
		if (x > panel.getWidth() - SIZE && dx > 0){
			//bounce off the right wall
			x = panel.getWidth() - SIZE;
			dx = - dx;
		}       
		if (y > panel.getHeight() - SIZE && dy > 0){
			//bounce off the bottom wall
			y = panel.getHeight() - SIZE;
			dy = -dy;
		}

		//make the ball move
		x += dx;
		y += dy;
	}// end move

}// end class Sprite
