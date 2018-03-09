import java.awt.BorderLayout;
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

public class Frame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Screen screen = new Screen();

	public Frame() {
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Ludum Dare #40: The more you have, the worse it is");
		setSize(new Dimension(640, 400));
		setExtendedState(MAXIMIZED_BOTH);
		setIconImage(Player.texture);

		setLayout(new BorderLayout());
		add(screen, BorderLayout.CENTER);

		JLayeredPane panel = getLayeredPane();
		JButton start = new JButton("Start");
		start.setFont(new Font(null, Font.BOLD, 40));
		JLabel controls = new JLabel("Move with W A S D and shoot with I L K J!");
		controls.setFont(new Font(null, Font.BOLD, 40));
		controls.setHorizontalAlignment(JLabel.CENTER);
		JLabel text = new JLabel("Collect energy (right) to shoot, the more you have, the worse it is!");
		text.setFont(new Font(null, Font.BOLD, 40));
		text.setHorizontalAlignment(JLabel.CENTER);

		int w = getWidth() / 8;
		start.setBounds(new Rectangle(getWidth() / 2 - w, getHeight() / 4, w * 2, w / 3));
		controls.setBounds(new Rectangle(0, getHeight() / 2, getWidth(), w / 2));
		text.setBounds(new Rectangle(0, getHeight() / 2, getWidth(), w / 2));
		text.setBounds(new Rectangle(0, getHeight() / 2 + getHeight() / 6, getWidth(), w / 2));

		panel.add(start, JLayeredPane.DRAG_LAYER);
		panel.add(controls, JLayeredPane.DRAG_LAYER);
		panel.add(text, JLayeredPane.DRAG_LAYER);

		start.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				start.setBounds(0, 0, 0, 0);
				controls.setBounds(0, 0, 0, 0);
				text.setBounds(0, 0, 0, 0);
				Main.running = true;
				repaint();
				validate();
			}
		});

		addComponentListener(new ComponentListener() {

			@Override
			public void componentShown(ComponentEvent e) {
			}

			@Override
			public void componentResized(ComponentEvent e) {
				start.setBounds(new Rectangle(getWidth() / 2 - w, getHeight() / 4, w * 2, w / 3));
				controls.setBounds(new Rectangle(0, getHeight() / 2, getWidth(), w / 2));
				validate();
				repaint();
			}

			@Override
			public void componentMoved(ComponentEvent e) {
			}

			@Override
			public void componentHidden(ComponentEvent e) {
			}
		});
	}

}
