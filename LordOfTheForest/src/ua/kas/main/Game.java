package ua.kas.main;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	public static final int WIDTH = 360;
	public static final int HEIGHT = WIDTH / 12 * 9;
	public static final int SCALE = 2;

	public final static String TITLE = "Lord of the Forest";

	private BufferedImage backgroundIMG = null;

	private Thread thread;

	private boolean running = false;

	private BufferedImageLoader loader;

	public synchronized void start() {
		if (running) {
			return;
		}
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	public void init() {
		loader = new BufferedImageLoader();
		backgroundIMG = loader.loadImage("/background.png");
	}

	@Override
	public void run() {
		requestFocus();
		init();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;

		long timer = System.currentTimeMillis();
		int update = 0;
		int frames = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1) {
				tick();
				update++;
				delta--;
			}
			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				System.out.println("Ticks: " + update + " FPS: " + frames);
				timer += 1000;
				update = 0;
				frames = 0;
			}
		}
	}

	public void tick() {

	}

	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(2);
			return;
		}
		Graphics g = bs.getDrawGraphics();

		g.drawImage(backgroundIMG, 0, 0, null);

		g.dispose();
		bs.show();
	}

	public static void main(String[] args) {
		Game game = new Game();
		game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

		JFrame frame = new JFrame(TITLE);
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		game.start();
	}
}
