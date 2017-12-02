import java.awt.image.BufferedImage;

public class Floor extends Tile {

	static BufferedImage texture = Loader.getTexture("floor");

	@Override
	boolean isSolid() {
		return false;
	}

	public BufferedImage getTexture() {
		return texture;
	}

}
