package javacode;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

class Search extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static JFrame f;
	JRadioButton rbtn[];
	JButton btn;
	JLabel search;
	String array[] = { "About Purchase", "About Sale", "About Product" };

	Search() {
		super(null);
		setBackground(Color.white);
		getInputMap().put(KeyStroke.getKeyStroke("ESCAPE"),"action");
		getActionMap().put("action", new AbstractAction() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				f.setVisible(false);
				
			}
		});
		
		JLabel l = new JLabel("Choose a option");
		l.setForeground(Color.blue);
		l.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		l.setBounds(20, 20, 200, 30);
		add(l);

		search = new JLabel(new ImageIcon("images/res.gif"));
		search.setBounds(180, 10, 90, 90);
		add(search);

		ButtonGroup b = new ButtonGroup();

		rbtn = new JRadioButton[3];
		for (int i = 0; i < 3; i++) {
			rbtn[i] = new JRadioButton(array[i], true);
			add(rbtn[i]);
			rbtn[i].setFont(new Font("Comic Sans MS", Font.BOLD, 15));
			rbtn[i].setBounds(50, 100 + 50 * i, 150, 20);
			b.add(rbtn[i]);
		}

		btn = new JButton(new ImageIcon(
				Search.class.getResource("/images/continue.jpg")));
		// btn.setBorder(BorderFactory.createEmptyBorder(1,1,1,1));
		btn.setBounds(220, 200, 60, 60);
		add(btn);
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (rbtn[0].isSelected()) {
					SearchPur.main(null);
				} else if (rbtn[1].isSelected()) {
					SearchSale.main(null);
				} else {
					SearchPro.main(null);
				}
			}
		});
	}

	public static void create() {
		f = new JFrame("Search");
		f.getContentPane().add(new Search());
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getDefaultScreenDevice();
		int width = gd.getDisplayMode().getWidth();
		int height = gd.getDisplayMode().getHeight();

		int w = 300, h = 300;
		f.setLocation(width / 2 - (w / 2), height / 2 - (h / 2));

		f.setSize(w, h);
		f.setVisible(true);
	}

	public static void main(String[] args) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		create();
	}

}
