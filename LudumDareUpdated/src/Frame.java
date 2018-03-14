import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class Frame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Screen screen = new Screen();
	CardLayout cards = new CardLayout();
	JPanel main = new JPanel();
	MainMenu menu = new MainMenu();
	String showing;

	public Frame() {
		setUndecorated(true);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Ludum Dare #40: The more you have, the worse it is");
		setSize(new Dimension(640, 400));
		setExtendedState(MAXIMIZED_BOTH);
		setIconImage(Player.texture);

		setLayout(new BorderLayout());
		add(main, BorderLayout.CENTER);

		main.setLayout(cards);
		main.add(screen, "game");
		main.add(menu, "menu");
		change("menu");
	}

	public void change(String str) {
		showing = str;
		cards.show(main, str);
		if (str.equals("game")) {
			screen.requestFocus();
		}
	}

}
