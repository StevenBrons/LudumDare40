import java.awt.image.BufferedImage;

public class Portal extends Entity {

	static BufferedImage texture = Loader.getTexture("portal");

	public Portal() {
	}

	public Portal(double d, double e) {
		this.x = d;
		this.y = e;
	}

	@Override
	public void run(Level l) {
		double dist = Math.pow(Level.player.x - this.x, 2) + Math.pow(Level.player.y - this.y, 2);
		if (dist < Tile.SIZE * 10) {
			l.next();
		}

	}

	public BufferedImage getTexture() {
		return texture;
	}

	@Override
	public int getHitbox() {
		return 6;
	}

}
