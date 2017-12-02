import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.Serializable;

public class Player extends Entity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	int acc = 1;

	boolean UP = false;
	boolean DOWN = false;
	boolean LEFT = false;
	boolean RIGHT = false;

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
			}
		}
	};

	public void move() {
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
	}

}
