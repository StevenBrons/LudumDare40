import java.awt.image.BufferedImage;

public class Floor extends Tile {

	public Floor(int x, int y) {
		super(x, y);
	}

	static BufferedImage texture = Loader.getTexture("floor");

	@Override
	boolean isSolid() {
		return false;
	}

	public BufferedImage getTexture() {
		return texture;
	}

}
