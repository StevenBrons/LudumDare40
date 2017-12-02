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

	public static final int ZOOM = 4;

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

		drawTiles(g, l);
		g.setColor(Color.RED);
		g.fillRect((int) px - l.player.hb, (int) py - l.player.hb, l.player.hb * 2, l.player.hb * 2);

		bs.show();
	}

	public void drawTiles(Graphics2D g, Level l) {
		int w = (getWidth() / Tile.SIZE) + 1;
		int h = (getHeight() / Tile.SIZE) + 1;

		for (int x = 0; x < w; x += 1) {
			for (int y = 0; y < h; y += 1) {
				Tile t = l.getTileAt(x * Tile.SIZE, y * Tile.SIZE);
				g.drawImage(t.getTexture(), x * Tile.SIZE, y * Tile.SIZE, Tile.SIZE, Tile.SIZE, null);

			}
		}

	}

}
