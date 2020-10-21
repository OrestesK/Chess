package main;

import java.awt.Canvas;

import javax.swing.JFrame;

public class Window extends Canvas {

	private static final long serialVersionUID = 7046162652717156913L;
	private JFrame frame;
	
	public Window(String title, Game game) {
		frame = new JFrame(title);
		//frame.setPreferredSize(new Dimension(screenSize.width, screenSize.height));
		//frame.setMaximumSize(new Dimension(screenSize.width, screenSize.height));
		//frame.setMinimumSize(new Dimension(screenSize.width, screenSize.height));
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setUndecorated(true);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(game);
		frame.setVisible(true);
		game.start();
		//frame.dispose();
	}
	
	public void reset() {
		frame.dispose();
	}
}
