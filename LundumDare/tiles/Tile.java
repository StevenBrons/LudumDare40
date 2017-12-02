import java.awt.image.BufferedImage;

public class Tile {

	public static final int SIZE = 8;

	static BufferedImage texture = Loader.getTexture("floor");

	public Tile() {
	}

	boolean isSolid() {
		return true;
	}

	public BufferedImage getTexture() {
		return texture;
	}

}
