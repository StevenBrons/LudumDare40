import java.awt.image.BufferedImage;

public class Wall extends Tile {

	static BufferedImage texture = Loader.getTexture("wall");

	public BufferedImage getTexture() {
		return texture;
	}

}
