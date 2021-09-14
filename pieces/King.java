package pieces;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.ID;
import main.KeyInput;
import main.Piece;

public class King extends Piece {
	private BufferedImage img;
	public King(float x, float y, ID id, int xTile, int yTile, int team, int variant) {
		super(x, y, id, xTile, yTile, team, variant);
		try {
			BufferedImage Bigimage = ImageIO
					.read(new File(System.getProperty("user.dir") + "\\png\\Pieces.png"));
			if(this.getTeam() == 1) img = Bigimage.getSubimage(0, 0, 80, 80);
			else img = Bigimage.getSubimage(0, 100, 80, 80);
		} catch (IOException e) {
			System.out.println("IOException");
		}
	}

	public void render(Graphics g) {
		testingFun();
		g.drawImage(img, (int) x, (int) y, null);
		overlay(g);
	}

	public void testingFun() {
		if (KeyInput.direction == 1) {
			setXTile(this.getXTile() + 1);
			KeyInput.direction = 0;
		}
		if (KeyInput.direction == -1) {
			setXTile(this.getXTile() - 1);
			KeyInput.direction = 0;
		}
		if (KeyInput.direction == 2) {
			setYTile(this.getYTile() + 1);
			KeyInput.direction = 0;
		}
		if (KeyInput.direction == -2) {
			setYTile(this.getYTile() - 1);
			KeyInput.direction = 0;
		}
	}
}
