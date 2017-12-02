import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

public class Screen extends Canvas {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final int ZOOM = 3;
	int tileWidth = 8 * ZOOM;

	public Screen() {

	}

	public void drawAll(Level l) {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(4);
			return;
		}
		Graphics2D g = (Graphics2D) bs.getDrawGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		// g.translate(-getWidth() / 2, -getHeight() / 2);

		drawTiles(g, l);

		bs.show();
	}

	public void drawTiles(Graphics2D g, Level l) {
		int w = (getWidth() / tileWidth) + 1;
		int h = (getHeight() / tileWidth) + 1;

		for (int x = 0; x < w; x += 1) {
			for (int y = 0; y < h; y += 1) {
				Tile t = l.getTileAt(x, y);
				g.drawImage(t.getTexture(), x * tileWidth, y * tileWidth, tileWidth, tileWidth, null);

			}
		}

	}

}
