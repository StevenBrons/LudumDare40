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

	boolean UP = false;
	boolean DOWN = false;
	boolean LEFT = false;
	boolean RIGHT = false;

	boolean NORTH = false;
	boolean EAST = false;
	boolean SOUTH = false;
	boolean WEST = false;

	public static KeyListener input = new KeyListener() {

		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyReleased(KeyEvent e) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_W:
			case KeyEvent.VK_UP:
				Level.player.UP = false;
				break;
			case KeyEvent.VK_S:
			case KeyEvent.VK_DOWN:
				Level.player.DOWN = false;
				break;
			case KeyEvent.VK_A:
			case KeyEvent.VK_LEFT:
				Level.player.LEFT = false;
				break;
			case KeyEvent.VK_D:
			case KeyEvent.VK_RIGHT:
				Level.player.RIGHT = false;
				break;
			// case KeyEvent.VK_I:
			// Level.player.NORTH = false;
			// break;
			// case KeyEvent.VK_L:
			// Level.player.EAST = false;
			// break;
			// case KeyEvent.VK_K:
			// Level.player.SOUTH = false;
			// break;
			// case KeyEvent.VK_J:
			// Level.player.WEST = false;
			// break;
			}
		}

		@Override
		public void keyPressed(KeyEvent e) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_W:
			case KeyEvent.VK_UP:
				Level.player.UP = true;
				break;
			case KeyEvent.VK_S:
			case KeyEvent.VK_DOWN:
				Level.player.DOWN = true;
				break;
			case KeyEvent.VK_A:
			case KeyEvent.VK_LEFT:
				Level.player.LEFT = true;
				break;
			case KeyEvent.VK_D:
			case KeyEvent.VK_RIGHT:
				Level.player.RIGHT = true;
				break;
			case KeyEvent.VK_I:
				Level.player.NORTH = true;
				break;
			case KeyEvent.VK_L:
				Level.player.EAST = true;
				break;
			case KeyEvent.VK_K:
				Level.player.SOUTH = true;
				break;
			case KeyEvent.VK_J:
				Level.player.WEST = true;
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

	}

	public BufferedImage getTexture() {
		return texture;
	}

	public void hit() {
		health--;
		if (health == 0) {
			health = 3;
			energy = 10;
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
