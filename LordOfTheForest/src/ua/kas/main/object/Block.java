package ua.kas.main.object;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import ua.kas.main.Game;
import ua.kas.main.Texture;
import ua.kas.main.framework.GameObject;
import ua.kas.main.framework.ObjectId;

public class Block extends GameObject {

	private int type;

	public Game game;

	private Texture texture;

	public Block(float x, float y, ObjectId id, int type, Game game) {
		super(x, y, id);
		this.type = type;
		this.game = game;
		texture = game.getTexture();
	}

	@Override
	public void tick(LinkedList<GameObject> object) {

	}

	@Override
	public void render(Graphics g) {
		if (type == 0) {
			g.drawImage(texture.block[0], (int) x, (int) y, null);
		}
		if (type == 1) {
			g.drawImage(texture.block[1], (int) x, (int) y, null);
		}
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 32, 32);
	}
}
