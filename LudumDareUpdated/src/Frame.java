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
	Info info = new Info();
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
		main.add(info, "info");
		change("menu");
	}

	public void change(String str) {
		showing = str;
		if (str.equals("game")) {
		}
		switch (str) {
		case "game":
			cards.show(main, str);
			screen.requestFocus();
			break;
		case "info":
			cards.show(main, "info");
			info.requestFocus();
			break;
		case "menu":
			cards.show(main, "menu");
			menu.requestFocus();
			menu.start.setText("Play");
			break;
		case "pause":
			menu.title.setText("Ludum Dare #40");
			menu.subtitle.setText("2D pixel shooter by Steven");
			cards.show(main, "menu");
			menu.start.setText("Resume");
			menu.requestFocus();
			break;
		case "died":
			menu.start.setText("Play Again");
			cards.show(main, "menu");
			menu.title.setText("You DIED");
			menu.subtitle.setText("You reached level " + Main.level.level);
			menu.requestFocus();
			break;
		}

	}

}
