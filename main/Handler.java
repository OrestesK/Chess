package main;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {

	LinkedList<Piece> object = new LinkedList<Piece>();

	public void render(Graphics g) {
		for (int i = 0; i < object.size(); i++) {
			Piece tempObject = object.get(i);
			tempObject.setPosition();
			tempObject.render(g);
		}
	}

	public void clear() {
		for (int i = 0; i < object.size(); i++) {
			Piece tempObject = object.get(i);
				removeObject(tempObject);
				i--;
		}
	}

	public void addObject(Piece object) {
		this.object.add(object);
	}

	public void removeObject(Piece object) {
		this.object.remove(object);
	}
}
