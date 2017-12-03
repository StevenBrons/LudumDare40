import java.awt.image.BufferedImage;

public class Enemy extends Entity {

	static BufferedImage texture = Loader.getTexture("enemy");
	int range = Tile.SIZE * 1000;
	int shootrange = Tile.SIZE * 600;
	int a = (int) Math.floor(Math.random() * 10);

	public Enemy(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public BufferedImage getTexture() {
		return texture;
	}

	public void run(Level l) {

		double dist = Math.pow(l.player.x - this.x, 2) + Math.pow(l.player.y - this.y, 2);
		double angle = Math.atan2(l.player.y - this.y, l.player.x - this.x);

		if (dist < shootrange) {
			if ((l.time + a) % 10 == 0) {
				EnmBullet enmBullet = new EnmBullet(this.x, this.y, Math.cos(angle), Math.sin(angle));
				l.entities.add(enmBullet);
			}
			return;
		}

		if (dist < range) {
			this.velx = Math.cos(angle) * 3;
			this.vely = Math.sin(angle) * 3;
		}

	}

}
