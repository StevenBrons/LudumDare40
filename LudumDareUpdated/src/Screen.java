import java.awt.Canvas;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RadialGradientPaint;
import java.awt.Toolkit;
import java.awt.MultipleGradientPaint.CycleMethod;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Screen extends Canvas {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final int ZOOM = 6;

	public Screen() {
		addKeyListener(Player.input);
	}

	public void drawAll(Level l) {
		double px = Math.floor(l.player.x);
		double py = Math.floor(l.player.y);

		BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
		Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "blank cursor");
		setCursor(blankCursor);

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

		g.setTransform(new AffineTransform());

		drawUI(g, l);

		bs.show();
	}

	private void drawUI(Graphics2D g, Level l) {
		double i = getWidth() / 80.0;
		g.drawImage(Health.texture, (int) i, (int) i, (int) (i * 3), (int) (i * 3), null);
		g.setFont(new Font(null, Font.BOLD, (int) (i * 3)));
		g.setColor(Color.WHITE);
		g.drawString("x" + l.player.health, (int) i * 6, (int) (i * 3.5));
		g.setColor(Color.WHITE);
		g.drawString("Level " + l.level, (int) (getWidth() / 2 - 5 * i), (int) (i * 3.5));
		g.drawImage(Energy.texture, (int) (getWidth() - i - (12 * i)), (int) (i * 0.8), (int) (4 * i), (int) (4 * i),
				null);
		g.drawString("x" + l.player.energy, (int) (getWidth() - i - (6 * i)), (int) (i * 3.5));

		if (l.player.hit > 0) {
			float[] fractions = { 0.2f, 0.8f };
			Color[] c = { new Color(0, 0, 0, 0), new Color(255, 0, 0, (int) (255 * l.player.hit)) };
			RadialGradientPaint gp = new RadialGradientPaint(new Point(getWidth() / 2, getHeight() / 2),
					(float) (getWidth() * 0.6), new Point(getWidth() / 2, getHeight() / 2), fractions, c, CycleMethod.NO_CYCLE);

			g.setPaint(gp);
			g.fillRect(0, 0, getWidth(), getHeight());
		}

	}

	public void drawEntities(Graphics2D g, Level l) {
		for (Entity ent : l.entities) {
			int hb = ent.getHitbox();
			g.drawImage(ent.getTexture(), (int) ent.x - hb, (int) ent.y - hb, hb * 2, hb * 2, null);
		}
	}

	public void drawTiles(Graphics2D g, Level l) {
		int w = (getWidth() / Tile.SIZE) + 1;
		int h = (getHeight() / Tile.SIZE) + 1;

		for (int x = -20; x < w; x += 1) {
			for (int y = -20; y < h; y += 1) {
				Tile t = l.getTileAt(x * Tile.SIZE, y * Tile.SIZE);
				if (t.isSolid()) {
					Color c1 = Color.getHSBColor((float) ((l.color % 10000) / 10000.0),0.7f,0.7f);
					g.setColor(c1);
					g.fillRect(x * Tile.SIZE, y * Tile.SIZE, Tile.SIZE, Tile.SIZE);
				}
			}
		}

	}

}
