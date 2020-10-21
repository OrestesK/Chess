package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import main.Game.STATE;

public class WinMenu extends MouseAdapter {
	private Game game;
	public boolean gameReset = false;
	public String win = "";

	public WinMenu(Game game, Handler handler) {
		this.game = game;
	}

	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();

		if (game.gameState == STATE.Menu) {
			if (mouseOver(mx, my, (int) Game.screenSize.getWidth() / 6 + (int) Game.screenSize.getWidth() / 22,
					(int) Game.screenSize.getWidth() / 5 + (int) Game.screenSize.getWidth() / 38,
					(int) Game.screenSize.getWidth() / 7, (int) Game.screenSize.getHeight() / 16)) {
				game.gameState = STATE.Game;
				gameReset = true;
			}
			if (mouseOver(mx, my, (int) Game.screenSize.getWidth() / 6 + (int) Game.screenSize.getWidth() / 22,
					(int) Game.screenSize.getWidth() / 4 + (int) Game.screenSize.getWidth() / 15,
					(int) Game.screenSize.getWidth() / 7, (int) Game.screenSize.getHeight() / 16)) {
				System.exit(0);
			}
		}
		if (game.gameState == STATE.Game) {
			if (mouseOver(mx, my, 1170, 695, 160, 40)) {
				game.gameState = STATE.Game;
				gameReset = true;
			}
			if (mouseOver(mx, my, 1000, 695, 160, 40)) {
				System.exit(0);

			}
		}

	}

	public void mouseReleased(MouseEvent e) {

	}

	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if (mx > x && mx < x + width) {
			if (my > y && my < y + height) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public void render(Graphics g) {
		if (game.gameState == STATE.Menu) {

			g.setColor(Color.lightGray);
			g.fillRect((int) Game.screenSize.getWidth() / 17 * 2, (int) Game.screenSize.getWidth() / 11,
					(int) Game.screenSize.getWidth() / 3, (int) Game.screenSize.getHeight() / 3 * 2);
			g.setColor(Color.orange);
			g.drawRect((int) Game.screenSize.getWidth() / 17 * 2, (int) Game.screenSize.getWidth() / 11,
					(int) Game.screenSize.getWidth() / 3, (int) Game.screenSize.getHeight() / 3 * 2);

			g.setColor(Color.white);
			g.fillRect((int) Game.screenSize.getWidth() / 7 + (int) Game.screenSize.getWidth() / 23,
					(int) Game.screenSize.getWidth() / 9, (int) Game.screenSize.getWidth() / 5,
					(int) Game.screenSize.getHeight() / 12);
			g.setColor(Color.orange);
			g.drawRect((int) Game.screenSize.getWidth() / 7 + (int) Game.screenSize.getWidth() / 23,
					(int) Game.screenSize.getWidth() / 9, (int) Game.screenSize.getWidth() / 5,
					(int) Game.screenSize.getHeight() / 12);

			Font font = new Font("serif", Font.PLAIN, 40);
			g.setFont(font);
			g.setColor(Color.gray);
			g.drawString(win + "WINS",
					(int) Game.screenSize.getWidth() / 10 * 2 - (int) Game.screenSize.getWidth() / 300,
					(int) Game.screenSize.getWidth() / 7);

			g.setColor(Color.white);
			g.fillRect((int) Game.screenSize.getWidth() / 6 + (int) Game.screenSize.getWidth() / 22,
					(int) Game.screenSize.getWidth() / 5 + (int) Game.screenSize.getWidth() / 38,
					(int) Game.screenSize.getWidth() / 7, (int) Game.screenSize.getHeight() / 16);
			g.setColor(Color.orange);
			g.drawRect((int) Game.screenSize.getWidth() / 6 + (int) Game.screenSize.getWidth() / 22,
					(int) Game.screenSize.getWidth() / 5 + (int) Game.screenSize.getWidth() / 38,
					(int) Game.screenSize.getWidth() / 7, (int) Game.screenSize.getHeight() / 16);

			font = new Font("serif", Font.PLAIN, 30);
			g.setFont(font);
			g.setColor(Color.gray);
			g.drawString("NEW GAME", (int) Game.screenSize.getWidth() / 6 + (int) Game.screenSize.getWidth() / 18,
					(int) Game.screenSize.getWidth() / 4);

			g.setColor(Color.white);
			g.fillRect((int) Game.screenSize.getWidth() / 6 + (int) Game.screenSize.getWidth() / 22,
					(int) Game.screenSize.getWidth() / 4 + (int) Game.screenSize.getWidth() / 15,
					(int) Game.screenSize.getWidth() / 7, (int) Game.screenSize.getHeight() / 16);
			g.setColor(Color.orange);
			g.drawRect((int) Game.screenSize.getWidth() / 6 + (int) Game.screenSize.getWidth() / 22,
					(int) Game.screenSize.getWidth() / 4 + (int) Game.screenSize.getWidth() / 15,
					(int) Game.screenSize.getWidth() / 7, (int) Game.screenSize.getHeight() / 16);

			font = new Font("serif", Font.PLAIN, 30);
			g.setFont(font);
			g.setColor(Color.gray);
			g.drawString("EXIT GAME", (int) Game.screenSize.getWidth() / 6 + (int) Game.screenSize.getWidth() / 18,
					(int) Game.screenSize.getWidth() / 4 + (int) Game.screenSize.getWidth() / 11);

		}
	}

}
