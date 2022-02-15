package pieces;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.ID;
import main.Piece;

public class Rook extends Piece {
	private BufferedImage img;

	public Rook(float x, float y, ID id, int xTile, int yTile, int team, int variant) {
		super(x, y, id, xTile, yTile, team, variant);
		if (this.getTeam() == 1)
			img = bigImage.getSubimage(400, 0, 80, 80);
		else
			img = bigImage.getSubimage(400, 100, 80, 80);
	}

	public void render(Graphics g) {
		g.drawImage(img, (int) x, (int) y, null);
		overlay(g);
	}
}
