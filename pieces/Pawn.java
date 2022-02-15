package pieces;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.ID;
import main.Piece;

public class Pawn extends Piece {
	private BufferedImage img;

	public Pawn(float x, float y, ID id, int xTile, int yTile, int team, int variant) {
		super(x, y, id, xTile, yTile, team, variant);
		if (this.getTeam() == 1)
			img = bigImage.getSubimage(486, 0, 80, 80);
		else
			img = bigImage.getSubimage(486, 100, 80, 80);
	}

	public void render(Graphics g) {
		g.drawImage(img, (int) x - 12, (int) y, null);
		overlay(g);
	}
}
