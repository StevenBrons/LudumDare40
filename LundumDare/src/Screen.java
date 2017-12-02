import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

public class Screen extends Canvas {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Screen() {

	}

	public void drawAll() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(4);
			return;
		}
		Graphics2D g = (Graphics2D) bs.getDrawGraphics();

		g.setColor(Color.RED);
		g.fillRect(0, 0, 100, 100);

		bs.show();
	}

}
