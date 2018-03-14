import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.Serializable;

public class Player extends Entity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static BufferedImage texture = Loader.getTexture("player");

	int acc = 2;
	int hb = 4;
	int health = 3;
	int energy = 20;
	public double hit = 0;

	static boolean UP = false;
	static boolean DOWN = false;
	static boolean LEFT = false;
	static boolean RIGHT = false;

	static boolean NORTH = false;
	static boolean EAST = false;
	static boolean SOUTH = false;
	static boolean WEST = false;

	public static KeyListener input = new KeyListener() {

		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyReleased(KeyEvent e) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_W:
				UP = false;
				break;
			case KeyEvent.VK_S:
				DOWN = false;
				break;
			case KeyEvent.VK_A:
				LEFT = false;
				break;
			case KeyEvent.VK_D:
				RIGHT = false;
				break;
			}
		}

		@Override
		public void keyPressed(KeyEvent e) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_W:
				UP = true;
				break;
			case KeyEvent.VK_S:
				DOWN = true;
				break;
			case KeyEvent.VK_A:
				LEFT = true;
				break;
			case KeyEvent.VK_D:
				RIGHT = true;
				break;
			case KeyEvent.VK_UP:
				NORTH = true;
				break;
			case KeyEvent.VK_RIGHT:
				EAST = true;
				break;
			case KeyEvent.VK_DOWN:
				SOUTH = true;
				break;
			case KeyEvent.VK_LEFT:
				WEST = true;
				break;
			case KeyEvent.VK_ESCAPE:
				Main.f.change("menu");
				break;
			case KeyEvent.VK_F5:
				Main.level.next();
				break;
			}
		}
	};

	public void run(Level l) {
		if (UP) {
			this.vely -= acc;
		}
		if (DOWN) {
			this.vely += acc;
		}
		if (LEFT) {
			this.velx -= acc;
		}
		if (RIGHT) {
			this.velx += acc;
		}
		if ((NORTH || EAST || SOUTH || WEST) && energy > 0) {
			energy--;
			Bullet b = null;
			if (NORTH) {
				b = new PlayerBullet(this.x, this.y, 0);
			}
			if (EAST) {
				b = new PlayerBullet(this.x, this.y, 2);
			}
			if (SOUTH) {
				b = new PlayerBullet(this.x, this.y, 4);
			}
			if (WEST) {
				b = new PlayerBullet(this.x, this.y, 6);
			}

			if (WEST && NORTH) {
				b = new PlayerBullet(this.x, this.y, 7);
			}
			if (SOUTH && WEST) {
				b = new PlayerBullet(this.x, this.y, 5);
			}
			if (EAST && SOUTH) {
				b = new PlayerBullet(this.x, this.y, 3);
			}
			if (NORTH && EAST) {
				b = new PlayerBullet(this.x, this.y, 1);
			}

			l.entities.add(b);

			NORTH = false;
			EAST = false;
			SOUTH = false;
			WEST = false;

		}
		if (hit > 0) {
			hit -= 0.1;
		}
	}

	public BufferedImage getTexture() {
		return texture;
	}

	public void hit() {
		health--;
		hit = 1;
		if (health == 0) {
			health = 3;
			energy = 10;
			Main.level.level = 1;
			Main.level.next();
		}
	}

	public void health() {
		health++;
	}

	public void energy() {
		energy++;
	}

}
