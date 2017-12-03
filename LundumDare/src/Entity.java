
public class Entity {

	double x;
	double y;

	double velx;
	double vely;
	int hb = Tile.SIZE / 2;

	public Entity() {

	}

	public void update(Level l) {

		this.x += this.velx;
		this.y += this.vely;

		checkHitbox(l);

		this.velx *= 0.7;
		this.vely *= 0.7;
	}

	public void checkHitbox(Level l) {

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
}
