import java.io.Serializable;

public class Level implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5357890466755108200L;

	int width = 100;
	int height = 100;

	Tile[][] tiles = new Tile[width][height];
	Tile defaultTile = new Tile(-1, -1);

	Player player = new Player();
	Generator g = new Generator();

	public Level() {

		g.generate();

		player.x = 25 * Tile.SIZE;
		player.y = 25 * Tile.SIZE;

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				tiles[x][y] = new Wall(x, y);
			}
		}

		for (int x = 20; x < 40; x++) {
			for (int y = 20; y < 40; y++) {
				tiles[x][y] = new Floor(x, y);
			}
		}

	}

	Tile getTileAt(double d, double e) {

		double x = d / (double) Tile.SIZE;
		double y = e / (double) Tile.SIZE;

		if (x >= 0 && y >= 0 && x < width && y < height) {
			return tiles[(int) x][(int) y];
		} else {
			return defaultTile;
		}

	}

	public void generate() {

	}

	public void update() {
		player.move();
		player.update(this);
	}

}
