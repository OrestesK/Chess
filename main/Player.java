package main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.TimeUnit;

import main.Game.STATE;

public class Player extends MouseAdapter {
	private Handler handler;
	private Game game;
	private WinMenu winMenu;
	private Piece selected;
	private int XTilePressed = 0;
	private int YTilePressed = 0;
	private int turnWho = 1;
	private int whiteWins = 0;
	private int blackWins = 0;

	public Player(Handler handler, Game game, WinMenu winmenu) {
		this.handler = handler;
		this.game = game;
		this.winMenu = winmenu;
	}

	public void mousePressed(MouseEvent e) {
		if (whiteWins == 0 && blackWins == 0) {
			int mx = e.getX();
			int my = e.getY();

			checkMove(mx, my);
			select(mx, my);
		}
		if(winMenu.gameReset == true) {
			whiteWins = 0;
			blackWins = 0;
			XTilePressed = 0;
			YTilePressed = 0;
		}
	}

	public void mouseReleased(MouseEvent e) {
	}

	private void capture() {
		for (int i = 0; i < handler.object.size(); i++) {
			Piece tempObject = handler.object.get(i);
			if (tempObject.getXTile() == XTilePressed && tempObject.getYTile() == YTilePressed
					&& tempObject.getTeam() != selected.getTeam()) {
				handler.removeObject(tempObject);
			}
		}
	}

	private boolean freeSpace() {
		for (int i = 0; i < handler.object.size(); i++) {
			Piece tempObject = handler.object.get(i);
			if (tempObject.getXTile() == XTilePressed && tempObject.getYTile() == YTilePressed) {
				return false;
			}
		}
		return true;
	}

	private boolean allySpace() {
		for (int i = 0; i < handler.object.size(); i++) {
			Piece tempObject = handler.object.get(i);
			if (tempObject.getXTile() == XTilePressed && tempObject.getYTile() == YTilePressed
					&& tempObject.getTeam() == selected.getTeam()) {
				return true;
			}
		}
		return false;
	}

	private boolean enemySpace() {
		for (int i = 0; i < handler.object.size(); i++) {
			Piece tempObject = handler.object.get(i);
			if (tempObject.getXTile() == XTilePressed && tempObject.getYTile() == YTilePressed
					&& tempObject.getTeam() != selected.getTeam()) {
				return true;
			}
		}
		return false;
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

	private void tilePressed(int mx, int my) {
		for (int x = 1; x < 9; x++) {
			for (int y = 1; y < 9; y++) {
				if (mouseOver(mx, my, 28 + (90 * x) - 90, (int) Game.screenSize.getHeight() - 114 - (90 * y) + 90, 90,
						90)) {
					XTilePressed = x;
					YTilePressed = y;
					break;
				}
			}
		}
	}

	private void select(int mx, int my) {
		if (turnWho == 1) {
			if (selected == null) {
				for (int i = 0; i < handler.object.size(); i++) {
					Piece tempObject = handler.object.get(i);
					if (mouseOver(mx, my, tempObject.getX(), tempObject.getY(), 80, 80) && tempObject.getTeam() == 1) {
						selected = tempObject;
						selected.setSelected(true);
					}
				}
			}
		}
		if (turnWho == 2) {
			if (selected == null) {
				for (int i = 0; i < handler.object.size(); i++) {
					Piece tempObject = handler.object.get(i);
					if (mouseOver(mx, my, tempObject.getX(), tempObject.getY(), 80, 80) && tempObject.getTeam() == 2) {
						selected = tempObject;
						selected.setSelected(true);
					}
				}
			}
		}
	}

	private void checkMove(int mx, int my) {
		if (selected != null) {
			tilePressed(mx, my);
			if (selected.getId() == ID.Pawn && Rules.pawn(selected, XTilePressed, YTilePressed, handler)) {
				basicCheck(mx, my);
			} else if (selected.getId() == ID.King && Rules.king(selected, XTilePressed, YTilePressed, handler)) {
				basicCheck(mx, my);
			} else if (selected.getId() == ID.Rook && Rules.rook(selected, XTilePressed, YTilePressed, handler)) {
				basicCheck(mx, my);
			} else if (selected.getId() == ID.Knight && Rules.knight(selected, XTilePressed, YTilePressed, handler)) {
				basicCheck(mx, my);
			} else if (selected.getId() == ID.Queen && Rules.queen(selected, XTilePressed, YTilePressed, handler)) {
				basicCheck(mx, my);
			} else if (selected.getId() == ID.Bishop && Rules.bishop(selected, XTilePressed, YTilePressed, handler)) {
				basicCheck(mx, my);
			} else {
				XTilePressed = 0;
				YTilePressed = 0;
				selected.setSelected(false);
				selected = null;
			}
		}
	}

	private void basicCheck(int mx, int my) {
		if (Rules.castle(selected, XTilePressed, YTilePressed, handler)) {
			XTilePressed = 0;
			YTilePressed = 0;
			selected.setMoves(selected.getMoves() + 1);
			selected.setSelected(false);
			selected = null;
			callSwitchSides();
		} else {
			if (freeSpace()) {
				selected.setXTile(XTilePressed);
				selected.setYTile(YTilePressed);
				XTilePressed = 0;
				YTilePressed = 0;
				selected.setMoves(selected.getMoves() + 1);
				selected.setSelected(false);
				selected = null;
				callSwitchSides();
			} else {
				if (enemySpace()) {
					capture();
					selected.setXTile(XTilePressed);
					selected.setYTile(YTilePressed);
					XTilePressed = 0;
					YTilePressed = 0;
					selected.setMoves(selected.getMoves() + 1);
					selected.setSelected(false);
					selected = null;
					callSwitchSides();
				}
				if (allySpace()) {
					XTilePressed = 0;
					YTilePressed = 0;
					selected.setSelected(false);
					selected = null;
				}
			}
		}
	}

	private void switchTurns() {
		if (turnWho == 1) {
			turnWho = 2;
		} else {
			turnWho = 1;
		}
	}

	private void switchSides() {
		checkGameOver();
		if (whiteWins == 0 && blackWins == 0) {
			for (int i = 0; i < handler.object.size(); i++) {
				Piece tempObject = handler.object.get(i);
				tempObject.setXTile(9 - tempObject.getXTile());
				tempObject.setYTile(9 - tempObject.getYTile());
			}
		}
	}

	private void callSwitchSides() {
		try {
			TimeUnit.MILLISECONDS.sleep(200);
		} catch (InterruptedException ex) {
		}
		switchSides();
		switchTurns();
	}

	private void checkGameOver() {
		int tempWhite = 0;
		int tempBlack = 0;
		for (int i = 0; i < handler.object.size(); i++) {
			Piece tempObject = handler.object.get(i);
			if (tempObject.getId() == ID.King && tempObject.getTeam() == 1) {
				tempWhite = 1;
			}
			if (tempObject.getId() == ID.King && tempObject.getTeam() == 2) {
				tempBlack = 1;
			}
		}
		if (tempWhite == 0) {
			blackWins = 1;
		}
		if (tempBlack == 0) {
			whiteWins = 1;
		}
		if (whiteWins == 1) {
			whiteWins = 2;
			winMenu.win = "WHITE ";
			game.gameState = STATE.Menu;
		}
		if (blackWins == 1) {
			blackWins = 2;
			winMenu.win = "BLACK ";
			game.gameState = STATE.Menu;
		}
	}

}
