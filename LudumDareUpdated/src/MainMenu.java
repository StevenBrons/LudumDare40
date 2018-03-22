import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainMenu extends JPanel {

	JLabel title = new JLabel("Ludum Dare #40");
	JLabel subtitle = new JLabel("2D pixel shooter by Steven");
	JButton start = new JButton("Start");
	JButton info = new JButton("Info");
	JButton exit = new JButton("Exit");
	int i = (int) (Math.random() * 10000);

	int mouseX = 0;
	int mouseY = 0;

	public MainMenu() {
		GridBagConstraints gb = new GridBagConstraints();
		setLayout(new GridBagLayout());

		start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.f.change("game");
			}
		});
		info.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.f.change("info");
			}
		});
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(1);
			}
		});

		title.setFont(new Font(null, Font.BOLD, 80));
		subtitle.setFont(new Font(null, Font.BOLD, 30));
		start.setFont(new Font(null, Font.BOLD, 30));
		info.setFont(new Font(null, Font.BOLD, 30));
		exit.setFont(new Font(null, Font.BOLD, 30));
		int p = 300;

		run();

		gb.anchor = GridBagConstraints.CENTER;
		gb.weightx = 1;
		gb.weighty = 1;
		gb.insets = new Insets(p / 2, 5, 0, 5);
		add(title, gb);
		gb.gridx = 0;
		gb.gridy = 1;
		gb.insets = new Insets(0, 5, 0, 5);
		gb.anchor = GridBagConstraints.PAGE_START;
		add(subtitle, gb);
		gb.anchor = GridBagConstraints.CENTER;
		gb.insets = new Insets(p / 2, 5, 5, 5);
		gb.gridx = 0;
		gb.gridy = 2;
		add(start, gb);
		gb.insets = new Insets(5, 5, 5, 5);
		gb.gridx = 0;
		gb.gridy = 3;
		add(info, gb);
		gb.gridx = 0;
		gb.gridy = 4;
		gb.insets = new Insets(5, 5, p, 5);
		add(exit, gb);

	}

	public void run() {
		Color c1 = Color.getHSBColor((float) ((i % 9000) / 9000.0), 0.7f, 0.9f);
		Color c1b = Color.getHSBColor((float) ((i % 9000) / 9000.0), 0.7f, 0.7f);
		Color c2 = Color.getHSBColor((float) ((i % 9000 + 3000) / 9000.0), 0.7f, 0.7f);

		setBackground(c1);

		for (Component comp : getComponents()) {
			JComponent c = (JComponent) comp;
			c.setFocusable(false);
			if (c.getMouseListeners().length <= 1) {
				c.putClientProperty("hover", false);
				c.addMouseListener(new MouseListener() {

					@Override
					public void mouseReleased(MouseEvent e) {
					}

					@Override
					public void mousePressed(MouseEvent e) {
					}

					@Override
					public void mouseExited(MouseEvent e) {
						c.putClientProperty("hover", false);
						run();
					}

					@Override
					public void mouseEntered(MouseEvent e) {
						c.putClientProperty("hover", true);
						run();
					}

					@Override
					public void mouseClicked(MouseEvent e) {
					}
				});
			}

			if (c.getClientProperty("hover").equals(true)) {
				c.setBackground(c1b);
			} else {
				c.setBackground(c1);
			}
			if (c instanceof JLabel) {
				c.setForeground(c2);
			} else {
				c.setPreferredSize(new Dimension(400, 50));
				c.setForeground(c2);
				c.setBorder(BorderFactory.createLineBorder(c2, 4));
			}
		}

		i += 10;
	}

}
