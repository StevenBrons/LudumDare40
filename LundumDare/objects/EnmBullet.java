import java.awt.image.BufferedImage;

public class EnmBullet extends Bullet {

	static int speed = 10;
	static BufferedImage texture = Loader.getTexture("enmBullet");

	public EnmBullet(double x, double y, double cos, double sin) {
		super(x, y, cos * speed, sin * speed);
		drag = 0.9;
	}

	public BufferedImage getTexture() {
		return texture;
	}

}
