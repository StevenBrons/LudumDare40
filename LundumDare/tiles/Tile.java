import java.awt.image.BufferedImage;

public class Tile {

	static BufferedImage texture = Loader.getTexture("floor");

	public Tile() {
	}

	boolean isSolid() {
		return false;
	}

	public BufferedImage getTexture() {
		return texture;
	}

}
