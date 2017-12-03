import java.awt.image.BufferedImage;

public class Bullet extends Entity {

	static BufferedImage texture = Loader.getTexture("bullet");

	public Bullet() {
	}

	public Bullet(double x, double y, double velx, double vely) {
		this.x = x;
		this.y = y;
		this.velx = velx;
		this.vely = vely;
	}

	public BufferedImage getTexture() {
		return texture;
	}

	public void run(Level l) {
		if (Math.abs(this.velx) + Math.abs(this.vely) < 1) {
			death(l);
		}
	}

	@Override
	public int getHitbox() {
		return 2;
	}

}
