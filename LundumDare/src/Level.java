
public class Level {

	int width = 100;
	int height = 100;

	Tile[][] tiles = new Tile[width][height];
	Tile defaultTile = new Tile();

	Player player;

	public Level() {
		player = new Player();

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (Math.random() > 0.5) {
					tiles[x][y] = new Wall();
				} else {
					tiles[x][y] = new Floor();
				}
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

}
