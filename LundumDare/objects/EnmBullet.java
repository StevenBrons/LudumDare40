import java.awt.image.BufferedImage;

public class EnmBullet extends Bullet {

	static int speed = 10;
	static BufferedImage texture = Loader.getTexture("enmBullet");

	public EnmBullet(double x, double y, double cos, double sin) {
		super(x, y, cos * speed, sin * speed);
		drag = 0.9;
	}

	@Override
	public void run(Level l) {
		double dist = Math.pow(l.player.x - this.x, 2) + Math.pow(l.player.y - this.y, 2);
		if (dist < getHitbox() * Tile.SIZE * 3) {
			death(l);
			l.player.hit();
		}

		if (Math.abs(this.velx) + Math.abs(this.vely) < 1) {
			death(l);
		}
	}

	public BufferedImage getTexture() {
		return texture;
	}

}
