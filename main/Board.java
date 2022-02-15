package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Board {
	private BufferedImage img;
	public static BufferedImage bigImg;

	public Board() {
		try {
			img = ImageIO.read(new File("png/Board.png"));
			bigImg = ImageIO
					.read(new File("png/Pieces.png"));
		} catch (IOException e) {
		}
	}

	public void render(Graphics g) {
		Font font = new Font("serif", Font.PLAIN, 25);
		g.setFont(font);

		g.setColor(Color.gray);
		g.fillRect(0, 0, 2000, 2000);
		g.drawImage(img, 28, 24, null);

		g.setColor(Color.orange);
		g.drawRect(28, 24, 720, 720);

		g.setColor(Color.white);
		g.fillRect(990, 24, 350, 720);
		g.setColor(Color.orange);
		g.drawRect(990, 24, 350, 720);

		g.setColor(Color.lightGray);
		g.fillRect(1170, 695, 160, 40);
		g.fillRect(1000, 695, 160, 40);
		g.setColor(Color.orange);
		g.drawRect(1170, 695, 160, 40);
		g.drawRect(1000, 695, 160, 40);

		g.setColor(Color.white);
		g.drawString("EXIT GAME", 1015, 725);

		g.setColor(Color.white);
		g.drawString("NEW GAME", 1180, 725);

	}
}
