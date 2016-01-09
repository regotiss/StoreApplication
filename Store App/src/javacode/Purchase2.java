package javacode;

import java.awt.EventQueue;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class Purchase2 extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JFrame f;
	JTabbedPane tb;
	JTextField tf;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					create();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public static void create() {
		f = new JFrame("Welcome");
		f.add(new Purchase2());
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getDefaultScreenDevice();
		int width = gd.getDisplayMode().getWidth();
		int height = gd.getDisplayMode().getHeight();

		f.setLocation(width / 2 - 410, height / 2 - 300);

		f.setSize(820, 600);
		f.setVisible(true);
	}
	private JLabel bak;
	/**
	 * Create the application.
	 */
	public Purchase2() {
		bak = new JLabel(new ImageIcon(
				"/images/bak5.jpg"));
		bak.setLayout(null);
		bak.setBounds(0, 0, getWidth(), getHeight());
		add(bak);

		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
}
