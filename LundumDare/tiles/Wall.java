import java.awt.image.BufferedImage;

public class Wall extends Tile {

	public Wall(int x, int y) {
		super(x, y);
	}

	static BufferedImage texture = Loader.getTexture("wall");

	public BufferedImage getTexture() {
		return texture;
	}

}
