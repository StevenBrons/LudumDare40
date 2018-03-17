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

	public void checkHitbox(Level l) {

		int hb = getHitbox();

		Tile xm = l.getTileAt(x - hb, y);
		Tile xp = l.getTileAt(x + hb, y);
		Tile ym = l.getTileAt(x, y - hb);
		Tile yp = l.getTileAt(x, y + hb);

		if (xm.isSolid()) {
			this.x = (xm.x + 1) * Tile.SIZE + hb;
			this.velx = 0;
			this.vely = 0;
		}
		if (xp.isSolid()) {
			this.x = (xm.x) * Tile.SIZE + hb;
			this.velx = 0;
			this.vely = 0;
		}
		if (ym.isSolid()) {
			this.y = (xm.y) * Tile.SIZE + hb;
			this.vely = 0;
			this.velx = 0;
		}
		if (yp.isSolid()) {
			this.y = (xm.y + 1) * Tile.SIZE - hb;
			this.vely = 0;
			this.velx = 0;
		}
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
