import java.awt.image.BufferedImage;

public class Entity {

	static BufferedImage texture;
	boolean death = false;

	double x;
	double y;

	double velx;
	double vely;

	double drag = 0.4;

	public Entity() {

	}

	public void update(Level l) {
		run(l);

		this.x += this.velx;
		this.y += this.vely;

		checkHitbox(l);

		this.velx *= drag;
		this.vely *= drag;
	}

	public void run(Level l) {
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
		}
		if (xp.isSolid()) {
			this.x = (xm.x) * Tile.SIZE + hb;
			this.velx = 0;
		}
		if (ym.isSolid()) {
			this.y = (xm.y) * Tile.SIZE + hb;
			this.vely = 0;
		}
		if (yp.isSolid()) {
			this.y = (xm.y + 1) * Tile.SIZE - hb;
			this.vely = 0;
		}

	}

	public void death(Level l) {
		this.death = true;
	}

	public BufferedImage getTexture() {
		return texture;
	}

	public int getHitbox() {
		return 4;
	}
}
