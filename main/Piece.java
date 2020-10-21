package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Piece {
	protected float x, y;
	protected ID id;
	protected int xTile;
	protected int yTile;
	protected int team;
	protected int moves = 0;
	protected int variant;
	protected boolean selected = false;

	public Piece(float x, float y, ID id, int xTile, int yTile, int team, int variant) {
		this.x = x;
		this.y = y;
		this.id = id;
		this.xTile = xTile;
		this.yTile = yTile;
		this.team = team;
		this.variant = variant;
	}

	public void setPosition() {
		this.x = 34 + (90 * xTile) - 90;
		// there is 90 x-space between the squares, and the leftmost piece starts at 33
		this.y = (float) Game.screenSize.getHeight() - (109 + (90 * yTile) - 90);
		// there is 90 y-space between the squares, and the bottommost piece starts at
		// the (screenHeight - 109)
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 80, 80);
	}

	public abstract void render(Graphics g);

	public void setXTile(int xTile) {
		this.xTile = xTile;
	}

	public void setYTile(int yTile) {
		this.yTile = yTile;
	}

	public int getXTile() {
		return xTile;
	}

	public int getYTile() {
		return yTile;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return (int) x;
	}

	public int getY() {
		return (int) y;
	}

	public void setId(ID id) {
		this.id = id;
	}

	public ID getId() {
		return id;
	}

	public int getTeam() {
		return team;
	}

	public void setTeam(int value) {
		team = value;
	}

	public void setMoves(int value) {
		moves = value;
	}

	public int getMoves() {
		return moves;
	}
	
	public void setVariant(int value) {
		variant = value;
	}

	public int getVariant() {
		return variant;
	}

	public void setSelected(boolean value) {
		selected = value;
	}

	public void overlay(Graphics g) {
		if (selected == true) {
			g.setColor(new Color(0, 0, 0, 120));
			g.fillRect((int) x, (int) y, 80, 80);
		}
	}
}
