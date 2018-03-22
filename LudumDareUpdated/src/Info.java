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
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class Info extends JPanel {
	int i = (int) (Math.random() * 10000);
	JButton back = new JButton("Back");
	JTextPane ta = new JTextPane();

	public Info() {
		setLayout(new GridBagLayout());

		GridBagConstraints gb = new GridBagConstraints();
		run();
		back.setPreferredSize(new Dimension(400, 50));
		back.setFont(new Font(null, Font.BOLD, 30));

		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Main.f.change("menu");
			}
		});

		ta.setFont(new Font(null, Font.BOLD, 30));

		gb.insets = new Insets(50, 50, 50, 50);
		gb.gridx = 0;
		gb.gridy = 0;
		add(ta, gb);
		gb.gridy = 1;
		add(back, gb);
	}

	public void run() {
		Color c1 = Color.getHSBColor((float) ((i % 9000) / 9000.0), 0.7f, 0.9f);
		Color c2 = Color.getHSBColor((float) ((i % 9000 + 6000) / 9000.0), 0.7f, 0.7f);
		Color c3 = Color.getHSBColor((float) ((i % 9000 + 3000) / 9000.0), 0.7f, 0.7f);		ta.setBackground(c1);
		
		ta.setForeground(c2);

		setBackground(c1);
		back.setBorder(BorderFactory.createLineBorder(c2, 4));
		back.setBackground(c1);
		back.setForeground(c2);

		ta.setText("");

		SimpleAttributeSet sas = new SimpleAttributeSet();
		StyleConstants.setAlignment(sas, StyleConstants.ALIGN_CENTER);
		try {
			StyleConstants.setForeground(sas, c2);
			ta.getDocument().insertString(ta.getDocument().getLength(), "Move using", sas);
			StyleConstants.setForeground(sas, c3);
			ta.getDocument().insertString(ta.getDocument().getLength(), " w a s d\n\n", sas);
			StyleConstants.setForeground(sas, c2);
			ta.getDocument().insertString(ta.getDocument().getLength(), "Shoot using the", sas);
			StyleConstants.setForeground(sas, c3);
			ta.getDocument().insertString(ta.getDocument().getLength(), " arrow keys\n\n", sas);
			StyleConstants.setForeground(sas, c2);
			ta.getDocument().insertString(ta.getDocument().getLength(),
					"Reach the end of each level without runing out of health", sas);
		} catch (BadLocationException e1) {
			e1.printStackTrace();
		}
		ta.getStyledDocument().setParagraphAttributes(0, 104, sas, false);
	}

}
