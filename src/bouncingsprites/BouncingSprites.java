
/* File Name:
 * Author Name: Algonquin College
 * Modified By:  Tihomir Penev
 * Date: 03.02.2017
 * Description: This is the starting point of the program, the size of the window is defined here
 * and the basic user interface
 */
package bouncingsprites;

import javax.swing.JFrame;


public class BouncingSprites {

	private JFrame frame;
	private SpritePanel panel = new SpritePanel();

	public BouncingSprites() {
		frame = new JFrame("Bouncing Sprites 2017W");
		frame.setSize(500, 500); //defining the size of the window
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		frame.setVisible(true);
	}


	public static void main(String[] args) {
		new BouncingSprites().panel.animate();
	}//end main
}//end BouncingSprites
