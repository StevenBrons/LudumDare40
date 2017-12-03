import java.awt.image.BufferedImage;

public class PlayerBullet extends Bullet {

	static BufferedImage texture = Loader.getTexture("bullet");

	public PlayerBullet(double x, double y, int i) {
		this.x = x;
		this.y = y;

		int s = 10;
		drag = 0.9;

		switch (i) {
		case 0:
			this.vely = -s;
			break;
		case 1:
			this.vely = -s * Math.sin(Math.PI / 4);
			this.velx = s * Math.cos(Math.PI / 4);
			break;
		case 2:
			this.velx = s;
			break;
		case 3:
			this.vely = s * Math.sin(Math.PI / 4);
			this.velx = s * Math.cos(Math.PI / 4);
			break;
		case 4:
			this.vely = s;
			break;
		case 5:
			this.vely = s * Math.sin(Math.PI / 4);
			this.velx = -s * Math.cos(Math.PI / 4);
			break;
		case 6:
			this.velx = -s;
			break;
		case 7:
			this.vely = -s * Math.sin(Math.PI / 4);
			this.velx = -s * Math.cos(Math.PI / 4);
			break;
		}
	}

	public BufferedImage getTexture() {
		return texture;
	}

	public void run(Level l) {
		if (Math.abs(this.velx) + Math.abs(this.vely) < 1) {
			this.death = true;
		}
	}

	@Override
	public int getHitbox() {
		return 2;
	}

}
