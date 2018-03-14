import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Generator {

	String roomsString = Loader.getLevelFile();
	int width;
	int height;
	Random rnd = new Random();

	ArrayList<Cell> grid = new ArrayList<>();
	ArrayList<Cell> stack = new ArrayList<Cell>();
	ArrayList<Room> rooms = new ArrayList<>();

	Cell current;

	double difficulty = 1;

	public Generator() {
		width = rnd.nextInt(8) + 2;
		height = rnd.nextInt(8) + 2;
		roomReader();
	}

	public Level generate() {
		Level l = new Level(width * Room.ROOM_SIZE, height * Room.ROOM_SIZE);
		generateMaze();

		Room[][] floorPlan = chooseRooms();

		int rs = Room.ROOM_SIZE;

		for (int x = 0; x < width * rs; x++) {
			for (int y = 0; y < height * rs; y++) {
				l.tiles[x][y] = getTileFromString(floorPlan[x / rs][y / rs].tiles[y % rs][x % rs], x, y);
			}
		}

		int set = 0;
		int enemies = (int) Math.ceil(width * height * difficulty);
		while (set < enemies) {
			int x = rnd.nextInt(width * rs);
			int y = rnd.nextInt(height * rs);

			if (!l.tiles[x][y].isSolid()) {
				l.entities.add(new Enemy(x * Tile.SIZE, y * Tile.SIZE));
				set++;
			}
		}

		int startRoomX = rnd.nextInt(2) == 0 ? 0 : width - 1;
		int startRoomY = rnd.nextInt(2) == 0 ? 0 : height - 1;

		l.player.x = (startRoomX * rs + 4.5) * Tile.SIZE;
		l.player.y = (startRoomY * rs + 4.5) * Tile.SIZE;

		int endRoomX = startRoomX == 0 ? width - 1 : 0;
		int endRoomY = startRoomY == 0 ? height - 1 : 0;

		for (int i = l.entities.size() - 1; i >= 0; i--) {
			if (l.entities.get(i) instanceof Enemy) {
				double dist = Math.pow(l.player.x - l.entities.get(i).x, 2)
						+ Math.pow(l.player.y - l.entities.get(i).y, 2);
				if (dist < Tile.SIZE * 900) {
					l.entities.remove(i);
				}
			}
		}

		Portal portal = new Portal((endRoomX * rs * Tile.SIZE) + Tile.SIZE * 4.5,
				(endRoomY * rs * Tile.SIZE) + Tile.SIZE * 4.5);
		l.entities.add(portal);

		l.setTileAt(l.player.x, l.player.y, new Floor(-1, -1));
		l.setTileAt(l.player.x, l.player.y - Tile.SIZE, new Floor(-1, -1));
		l.setTileAt(l.player.x - Tile.SIZE, l.player.y, new Floor(-1, -1));
		l.setTileAt(l.player.x, l.player.y + Tile.SIZE, new Floor(-1, -1));
		l.setTileAt(l.player.x + Tile.SIZE, l.player.y, new Floor(-1, -1));

		l.setTileAt(portal.x, portal.y, new Floor(-1, -1));
		l.setTileAt(portal.x, portal.y - Tile.SIZE, new Floor(-1, -1));
		l.setTileAt(portal.x - Tile.SIZE, portal.y, new Floor(-1, -1));
		l.setTileAt(portal.x, portal.y + Tile.SIZE, new Floor(-1, -1));
		l.setTileAt(portal.x + Tile.SIZE, portal.y, new Floor(-1, -1));

		return l;

	}

	public void roomToTiles(Level l, Room r, int startx, int starty) {
		for (int x = 0; x < Room.ROOM_SIZE; x++) {
			for (int y = 0; y < Room.ROOM_SIZE; y++) {
				l.tiles[startx + x][starty + y] = getTileFromString(r.tiles[x][y], startx + x, starty + y);

			}
		}
	}

	private Tile getTileFromString(String s, int x, int y) {
		switch (s) {
		case "f":
			return new Floor(x, y);
		case "w":
			return new Wall(x, y);
		default:
			return Level.defaultTile;
		}
	}

	private Room[][] chooseRooms() {
		Room[][] plan = new Room[width][height];

		for (int i = 0; i < grid.size(); i++) {
			plan[grid.get(i).x][grid.get(i).y] = getFitRoom(grid.get(i));
		}

		return plan;
	}

	public Room getFitRoom(Cell c) {
		ArrayList<Room> fitRooms = new ArrayList<>();

		for (Room r : rooms) {
			if (r.north == c.north && r.east == c.east && r.south == c.south && r.west == c.west) {
				fitRooms.add(r);
			}
		}
		int i = rnd.nextInt(fitRooms.size());
		return fitRooms.get(i);

	}

	public void generateMaze() {
		grid.clear();
		stack.clear();

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				Cell cell = new Cell(x, y, width, height);
				grid.add(cell);

			}
		}
		current = grid.get(0);
		while (true) {
			Cell next = current.checkNeighbors(grid);
			if (next != null) {
				next.visited = true;

				stack.add(current);

				removeWalls(current, next);

				current = next;

			} else if (stack.size() > 0) {
				current = stack.get(stack.size() - 1);
				stack.remove(stack.size() - 1);
			} else if (stack.size() == 0) {
				break;
			}
		}
	}

	public void removeWalls(Cell a, Cell b) {
		int x = a.x - b.x;
		int y = a.y - b.y;

		if (x == 1) {
			a.west = false;
			b.east = false;
		} else if (x == -1) {
			a.east = false;
			b.west = false;
		}

		if (y == 1) {
			a.north = false;
			b.south = false;
		} else if (y == -1) {
			a.south = false;
			b.north = false;
		}
	}

	public void roomReader() {
		Scanner s = new Scanner(roomsString);

		Room room = new Room();

		while (s.hasNext()) {
			switch (s.next()) {
			case "#":
				s.nextLine();
				break;
			case "type":
				room.type = s.nextInt();
				switch (room.type) {
				case 0:
					room.north = false;
					break;
				case 1:
					room.north = false;
					room.east = false;
					break;
				case 2:
					room.north = false;
					room.south = false;
					break;
				case 3:
					room.north = false;
					room.east = false;
					room.south = false;
					break;
				case 4:
					room.north = false;
					room.east = false;
					room.south = false;
					room.west = false;
					break;
				}
				break;
			case "tiles":
				for (int i = 0; i < Room.ROOM_SIZE * Room.ROOM_SIZE; i++) {
					room.tiles[i / Room.ROOM_SIZE][i % Room.ROOM_SIZE] = s.next();
				}
				addRoom(room);
				room = new Room();
				break;
			default:
				break;
			}
		}

		s.close();
	}

	public void addRoom(Room r) {
		switch (r.type) {
		case 0:
		case 1:
		case 3:
			rooms.add(r);
			rooms.add(Room.rotateRight(r, 1));
			rooms.add(Room.rotateRight(r, 2));
			rooms.add(Room.rotateRight(r, 3));
			break;
		case 2:
			r.weight *= 2;
			rooms.add(r);
			rooms.add(Room.rotateRight(r, 1));
			break;
		case 4:
			r.weight *= 4;
			rooms.add(r);
			break;
		default:
			break;
		}

	}

}
