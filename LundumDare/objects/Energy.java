import java.awt.image.BufferedImage;

public class Energy extends Entity {
	static BufferedImage texture = Loader.getTexture("energy");

	public Energy(double x, double y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public void run(Level l) {
		double dist = Math.pow(Level.player.x - this.x, 2) + Math.pow(Level.player.y - this.y, 2);
		double angle = Math.atan2(Level.player.y - this.y, Level.player.x - this.x);

		if (dist < Tile.SIZE * 30) {
			this.velx = Math.cos(angle) * 1;
			this.vely = Math.sin(angle) * 1;
		}

		if (dist < Tile.SIZE * 2) {
			death(l);
			Level.player.energy();
		}
	}

	public BufferedImage getTexture() {
		return texture;
	}

	@Override
	public int getHitbox() {
		return 2;
	}

}
