package pieces;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.ID;
import main.Piece;

public class Pawn extends Piece {
private BufferedImage img;
	public Pawn(float x, float y, ID id, int xTile, int yTile, int team, int variant) {
		super(x, y, id, xTile, yTile, team, variant);
		try {
			BufferedImage Bigimage = ImageIO
					.read(new File(System.getProperty("user.dir") + "\\png\\Pieces.png"));
			if(this.getTeam() == 1) img = Bigimage.getSubimage(486, 0, 80, 80);
			else img = Bigimage.getSubimage(486, 100, 80, 80);
		} catch (IOException e) {
			System.out.println("IOException");
		}
	}

	public void render(Graphics g) {
		g.drawImage(img, (int) x - 12, (int) y, null);
		overlay(g);
	}
}
