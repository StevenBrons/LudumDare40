import java.awt.image.BufferedImage;

public class Floor extends Tile {

	static BufferedImage texture = Loader.getTexture("floor");
	
	public Floor(int x, int y) {
		super(x, y);
	}

	@Override
	boolean isSolid() {
		return false;
	}

	public BufferedImage getTexture() {
		return texture;
	}

}
