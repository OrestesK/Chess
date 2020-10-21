package main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
	public static int direction;

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_ESCAPE) {
			System.exit(0);
		}
		if (key == KeyEvent.VK_D) {
			direction = 1;
		}
		if (key == KeyEvent.VK_A) {
			direction = -1;
		}
		if (key == KeyEvent.VK_W) {
			direction = 2;
		}
		if (key == KeyEvent.VK_S) {
			direction = -2;
		}
	}

	public void keyReleased(KeyEvent e) {
	}

}
