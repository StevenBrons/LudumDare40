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

		for (int i = l.entities.size() - 1; i >= 0; i--) {
			Entity e = l.entities.get(i);
			if (e instanceof Enemy) {
				double dist = Math.pow(e.x - this.x, 2) + Math.pow(e.y - this.y, 2);
				if (dist < getHitbox() * Tile.SIZE * 3) {
					e.death(l);
				}
			}
		}

		if (Math.abs(this.velx) + Math.abs(this.vely) < 1) {
			death(l);
		}
	}

	@Override
	public int getHitbox() {
		return 2;
	}

}
