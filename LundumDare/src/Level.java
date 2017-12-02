import java.io.Serializable;

public class Level implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5357890466755108200L;

	int width = 100;
	int height = 100;

	Tile[][] tiles = new Tile[width][height];
	Tile defaultTile = new Tile();

	Player player = new Player();

	public Level() {
		player.x = 25 * Tile.SIZE;
		player.y = 25 * Tile.SIZE;

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				tiles[x][y] = new Wall();
			}
		}

		for (int x = 20; x < 40; x++) {
			for (int y = 20; y < 40; y++) {
				tiles[x][y] = new Floor();
			}
		}

	}

	Tile getTileAt(double d, double e) {

		if (d >= 0 && e >= 0 && d < width && e < height) {
			return tiles[(int) d][(int) e];
		} else {
			return defaultTile;
		}

	}

	public void generate() {

	}

	public void update() {
		player.move();
		player.update();
	}

}
