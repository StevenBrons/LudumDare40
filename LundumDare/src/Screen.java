import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferStrategy;

public class Screen extends Canvas {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final int ZOOM = 6;

	public Screen() {
		addKeyListener(Main.level.player.input);
	}

	public void drawAll(Level l) {
		double px = l.player.x;
		double py = l.player.y;

		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		Graphics2D g = (Graphics2D) bs.getDrawGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.translate(getWidth() / 2, getHeight() / 2);
		g.scale(ZOOM, ZOOM);

		g.translate(-px, -py);

		drawEntities(g, l);
		drawTiles(g, l);

		// drawMaze(g, l);

		bs.show();
	}

	public void drawEntities(Graphics2D g, Level l) {
		for (Entity ent : l.entities) {
			int hb = ent.getHitbox();
			g.drawImage(ent.getTexture(), (int) ent.x - hb, (int) ent.y - hb, hb * 2, hb * 2, null);
		}
	}

	// private void drawMaze(Graphics2D g, Level l) {
	//
	// int w = 30;
	//
	// for (Cell c : l.g.grid) {
	// int x = w * 3 * c.x;
	// int y = w * 3 * c.y;
	//
	// if (c.north) {
	// g.fillRect(x + w, y, 30, 30);
	// }
	// if (c.east) {
	// g.fillRect(x + 2 * w, y + w, 30, 30);
	// }
	// if (c.south) {
	// g.fillRect(x + w, y + 2 * w, 30, 30);
	// }
	// if (c.west) {
	// g.fillRect(x, y + w, 30, 30);
	// }
	//
	// g.fillRect(x, y, 30, 30);
	// g.fillRect(x + 2 * w, y, 30, 30);
	// g.fillRect(x, y + 2 * w, 30, 30);
	// g.fillRect(x + 2 * w, y + 2 * w, 30, 30);
	//
	// }
	//
	// }

	public void drawTiles(Graphics2D g, Level l) {
		int w = (getWidth() / Tile.SIZE) + 1;
		int h = (getHeight() / Tile.SIZE) + 1;

		for (int x = -20; x < w; x += 1) {
			for (int y = -20; y < h; y += 1) {
				Tile t = l.getTileAt(x * Tile.SIZE, y * Tile.SIZE);
				g.drawImage(t.getTexture(), x * Tile.SIZE, y * Tile.SIZE, Tile.SIZE, Tile.SIZE, null);
			}
		}

	}

}
