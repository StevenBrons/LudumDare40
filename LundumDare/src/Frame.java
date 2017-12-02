import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Frame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Screen screen = new Screen();

	public Frame() {
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setExtendedState(MAXIMIZED_BOTH);
		setTitle("Lundum Dare #40");
		setSize(new Dimension(640, 400));

		setLayout(new BorderLayout());
		add(screen, BorderLayout.CENTER);
	}

}
