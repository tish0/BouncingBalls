/* File Name:SpritePanel
 * Author Name: Algonquin College
 * Modified By:  Tihomir Penev
 * Date: 03.02.2017
 * Description: This class assigns each balls its own thread and 
 * decides how many balls are in the circle, the circle is created in this as well
 */

package bouncingsprites;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;



public class SpritePanel extends JPanel{

	private static final long serialVersionUID = 1L;

	private List <Sprite> SpriteArray = new ArrayList<Sprite>();
	private int ballsIn;// number of balls in the circle

	public static final int Oval_R = 150;//defining static radius for the Circle
	public static final int CENTER = 237; //defining static Center variable for the Circle
	public static final int Oval_X = 242; //defining static x axis for the Circle
	public static final int Oval_Y = 232; //defining static y axis for the Circle
	/*
	 * Default constructor.
	 */
	public SpritePanel(){
		addMouseListener(new Mouse());
	}

	public synchronized void consume() throws InterruptedException {
		//a sprite cannot leave the circle with less than or equal to 2 sprites inside
		while (ballsIn <= 2){
			wait();
		}

		//	ballsIn--;
		notifyAll();
	}
	public synchronized void produce() throws InterruptedException {
		// check if there are 4 balls in the circle,if yes wait
		while (ballsIn >= 4) {
			wait();
		}

		//  ballsIn++;
		notifyAll();
	}	

	private void newSprite (MouseEvent event){

		Sprite sprite = new Sprite(this);
		new Thread(sprite).start(); //	
		SpriteArray.add(sprite); // adding the the ball to the arraylist
		System.out.println("New ball added");

	}
	public void animate(){
		while (true){
			repaint();    //when it is called it paints new ball
		}
	}

	private class Mouse extends MouseAdapter {
		@Override
		public void mousePressed( final MouseEvent event ){
			newSprite(event);
		}
	}


	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g); //when call it, cleans the screen and if we comment this line we can see the path of the the balls

		drawCircle(g, Oval_X, Oval_Y, Oval_R ); // drawing circle with center x = 242,y=232 and radius 150
		// inside of the window is 484x464 pixels
		for (Sprite sprite : SpriteArray){
			sprite.draw(g);

		}
	}
	protected void drawCircle(Graphics g1, int x, int y, int r) {

		g1.setColor(Color.green);
		g1.fillOval(x-r, y-r, 2*r, 2*r); // using Oval to create our circle and fill colors it

	}//end drawCircle

}//end class SpritePanel

