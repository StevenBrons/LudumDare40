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
	int width;
	int height;
	Player player;
	public ArrayList<Entity> entities = new ArrayList<Entity>();

	public Level(int width, int height) {
		tiles = new Tile[width][height];
		this.width = width;
		this.height = height;
		player = new Player();

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

	public void generate() {

	}

	public void update() {
		time++;
		for (int i = entities.size() - 1; i >= 0; i--) {
			entities.get(i).update(this);
			if (entities.get(i).death) {
				entities.remove(i);
			}
		}

	}

}
