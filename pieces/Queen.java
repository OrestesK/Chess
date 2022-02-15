package pieces;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.ID;
import main.Piece;

public class Queen extends Piece {
	private BufferedImage img;

	public Queen(float x, float y, ID id, int xTile, int yTile, int team, int variant) {
		super(x, y, id, xTile, yTile, team, variant);
		if (this.getTeam() == 1)
			img = bigImage.getSubimage(96, 0, 88, 82);
		else
			img = bigImage.getSubimage(96, 100, 88, 82);
	}

	public void render(Graphics g) {
		g.drawImage(img, (int) x - 4, (int) y, null);
		overlay(g);
	}
}
