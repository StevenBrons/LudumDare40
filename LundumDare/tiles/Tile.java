import java.awt.image.BufferedImage;

public class Tile {

	public static final int SIZE = 8;

	static BufferedImage texture = Loader.getTexture("floor");
	int x;
	int y;

	public Tile(int x, int y) {
		this.x = x;
		this.y = y;
	}

	boolean isSolid() {
		return true;
	}

	public BufferedImage getTexture() {
		return texture;
	}

}
