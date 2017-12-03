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

	boolean UP = false;
	boolean DOWN = false;
	boolean LEFT = false;
	boolean RIGHT = false;

	boolean NORTH = false;
	boolean EAST = false;
	boolean SOUTH = false;
	boolean WEST = false;

	public KeyListener input = new KeyListener() {

		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyReleased(KeyEvent e) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_W:
			case KeyEvent.VK_UP:
				UP = false;
				break;
			case KeyEvent.VK_S:
			case KeyEvent.VK_DOWN:
				DOWN = false;
				break;
			case KeyEvent.VK_A:
			case KeyEvent.VK_LEFT:
				LEFT = false;
				break;
			case KeyEvent.VK_D:
			case KeyEvent.VK_RIGHT:
				RIGHT = false;
				break;
			case KeyEvent.VK_I:
				NORTH = false;
				break;
			case KeyEvent.VK_L:
				EAST = false;
				break;
			case KeyEvent.VK_K:
				SOUTH = false;
				break;
			case KeyEvent.VK_J:
				WEST = false;
				break;
			}
		}

		@Override
		public void keyPressed(KeyEvent e) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_W:
			case KeyEvent.VK_UP:
				UP = true;
				break;
			case KeyEvent.VK_S:
			case KeyEvent.VK_DOWN:
				DOWN = true;
				break;
			case KeyEvent.VK_A:
			case KeyEvent.VK_LEFT:
				LEFT = true;
				break;
			case KeyEvent.VK_D:
			case KeyEvent.VK_RIGHT:
				RIGHT = true;
				break;
			case KeyEvent.VK_I:
				NORTH = true;
				break;
			case KeyEvent.VK_L:
				EAST = true;
				break;
			case KeyEvent.VK_K:
				SOUTH = true;
				break;
			case KeyEvent.VK_J:
				WEST = true;
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
		if ((NORTH || EAST || SOUTH || WEST) && l.time % 2 == 0) {
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

		}

	}

	public BufferedImage getTexture() {
		return texture;
	}

}
