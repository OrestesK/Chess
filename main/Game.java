package main;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;

import pieces.Rook;
import pieces.Bishop;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Queen;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = -473349850293143017L;
	static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private Thread thread;
	public boolean running = false;
	private Handler handler;
	private WinMenu winMenu;
	private Board board;
	private Player player;
	private Window window;

	public Game() {
		board = new Board();
		handler = new Handler();
		winMenu = new WinMenu(this, handler);
		player = new Player(handler, this, winMenu);
		window = new Window("Chess", this);
		this.addKeyListener(new KeyInput());
		this.addMouseListener(player);
		this.addMouseListener(winMenu);
		startGame();
	}

	public enum STATE {
		Menu, Game, Help
	};

	public STATE gameState = STATE.Game;

	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}

	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		@SuppressWarnings("unused")
		int frames = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				resetGame();
				delta--;
			}
			if (running) {
				render();
			}
			frames++;
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				frames = 0;
			}
		}
		stop();
	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();
		if (gameState == STATE.Game) {
			board.render(g);
			handler.render(g);
			winMenu.render(g);
		} else if (gameState == STATE.Menu || gameState == STATE.Help) {
			winMenu.render(g);
		}
		g.dispose();
		bs.show();

	}

	public void startGame() {
		// white side 1
		handler.addObject(new King(0, 0, ID.King, 5, 1, 1, 1));
		handler.addObject(new Queen(0, 0, ID.Queen, 4, 1, 1, 1));
		handler.addObject(new Rook(0, 0, ID.Rook, 1, 1, 1, 1));
		handler.addObject(new Rook(0, 0, ID.Rook, 8, 1, 1, 2));
		handler.addObject(new Bishop(0, 0, ID.Bishop, 3, 1, 1, 1));
		handler.addObject(new Bishop(0, 0, ID.Bishop, 6, 1, 1, 2));
		handler.addObject(new Knight(0, 0, ID.Knight, 2, 1, 1, 1));
		handler.addObject(new Knight(0, 0, ID.Knight, 7, 1, 1, 2));
		handler.addObject(new Pawn(0, 0, ID.Pawn, 1, 2, 1, 1));
		handler.addObject(new Pawn(0, 0, ID.Pawn, 2, 2, 1, 2));
		handler.addObject(new Pawn(0, 0, ID.Pawn, 3, 2, 1, 3));
		handler.addObject(new Pawn(0, 0, ID.Pawn, 4, 2, 1, 4));
		handler.addObject(new Pawn(0, 0, ID.Pawn, 5, 2, 1, 5));
		handler.addObject(new Pawn(0, 0, ID.Pawn, 6, 2, 1, 6));
		handler.addObject(new Pawn(0, 0, ID.Pawn, 7, 2, 1, 7));
		handler.addObject(new Pawn(0, 0, ID.Pawn, 8, 2, 1, 8));

		// black side 2
		handler.addObject(new King(0, 0, ID.King, 5, 8, 2, 1));
		handler.addObject(new Queen(0, 0, ID.Queen, 4, 8, 2, 1));
		handler.addObject(new Rook(0, 0, ID.Rook, 1, 8, 2, 1));
		handler.addObject(new Rook(0, 0, ID.Rook, 8, 8, 2, 2));
		handler.addObject(new Bishop(0, 0, ID.Bishop, 3, 8, 2, 1));
		handler.addObject(new Bishop(0, 0, ID.Bishop, 6, 8, 2, 2));
		handler.addObject(new Knight(0, 0, ID.Knight, 2, 8, 2, 1));
		handler.addObject(new Knight(0, 0, ID.Knight, 7, 8, 2, 2));
		handler.addObject(new Pawn(0, 0, ID.Pawn, 1, 7, 2, 1));
		handler.addObject(new Pawn(0, 0, ID.Pawn, 2, 7, 2, 2));
		handler.addObject(new Pawn(0, 0, ID.Pawn, 3, 7, 2, 3));
		handler.addObject(new Pawn(0, 0, ID.Pawn, 4, 7, 2, 4));
		handler.addObject(new Pawn(0, 0, ID.Pawn, 5, 7, 2, 5));
		handler.addObject(new Pawn(0, 0, ID.Pawn, 6, 7, 2, 6));
		handler.addObject(new Pawn(0, 0, ID.Pawn, 7, 7, 2, 7));
		handler.addObject(new Pawn(0, 0, ID.Pawn, 8, 7, 2, 8));
	}

	public void resetGame() {
		if (winMenu.gameReset == true) {
			winMenu.gameReset = false;
			// gameState = STATE.Game;
			// handler.clear();
			// startGame();
			window.reset();
			new Game();
		}
	}

	public static void main(String[] args) {
		new Game();
	}

}
