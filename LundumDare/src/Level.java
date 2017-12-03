import java.io.Serializable;

public class Level implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5357890466755108200L;
	static Tile defaultTile = new Wall(-1, -1);

	Tile[][] tiles;

	int width;
	int height;
	Player player = new Player();

	public Level(int width, int height) {
		tiles = new Tile[width][height];
		this.width = width;
		this.height = height;

		player.x = (Tile.SIZE * 4.5);
		player.y = width * Tile.SIZE - (Tile.SIZE * 4.5);

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
