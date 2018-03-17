import java.io.Serializable;
import java.util.ArrayList;

public class Level implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5357890466755108200L;
	static Tile defaultTile = new Wall(-1, -1);

	Tile[][] tiles;

	long time = 0;
	long color = (long) Math.floor(Math.random() * 10000);
	int width;
	int height;
	Player player = new Player();
	public ArrayList<Entity> entities = new ArrayList<Entity>();
	int level = 1;

	public Level(int width, int height) {
		tiles = new Tile[width][height];
		this.width = width;
		this.height = height;

		entities.add(player);

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

	public void setTileAt(double d, double e, Tile t) {
		double x = d / (double) Tile.SIZE;
		double y = e / (double) Tile.SIZE;

		if (x >= 0 && y >= 0 && x < width && y < height) {
			tiles[(int) x][(int) y] = t;
			t.x = (int) x;
			t.y = (int) y;
		}
	}

	public void generate() {

	}

	public void update() {
		time++;
		color++;
		for (int i = entities.size() - 1; i >= 0; i--) {
			entities.get(i).update(this);
			if (entities.get(i).death) {
				entities.remove(i);
			}
		}

	}

	public void next() {
		Generator g = new Generator();
		g.difficulty += Main.level.level * 0.2;
		int l = Main.level.level;
		Main.level = g.generate();
		Main.level.level = l + 1;
		player.velx = 0;
		player.vely = 0;
	}

}
