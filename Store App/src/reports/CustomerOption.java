package reports;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javacode.ConnectToDataBase;
import javacode.Main4;

import javax.swing.AbstractAction;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.KeyStroke;
import javax.swing.UIManager;

public class CustomerOption extends JPanel {


	private static final long serialVersionUID = 1L;
	private static JFrame f;
	private JLabel search;
	private JRadioButton rbtnAC;
	private JRadioButton rbtnSC;
	

	public CustomerOption() {
		super(null);
		setBorder(UIManager.getBorder("TitledBorder.border"));
		setBackground(new Color(238, 130, 238));

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
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(21, 18, 207, 192);
		add(panel);
		panel.setLayout(null);
		JLabel l = new JLabel("Report Of");
		l.setBounds(81, 18, 109, 30);
		panel.add(l);
		l.setForeground(Color.blue);
		l.setFont(new Font("Comic Sans MS", Font.BOLD, 18));

		search = new JLabel(new ImageIcon(CustomerOption.class.getResource("/images/Report.png")));
		search.setBounds(21, 6, 50, 50);
		panel.add(search);
		
		ButtonGroup b = new ButtonGroup();
		
		rbtnAC = new JRadioButton("All Customers");
		rbtnAC.setBounds(31, 68, 109, 23);
		panel.add(rbtnAC);
		rbtnAC.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		rbtnSC = new JRadioButton("Specific Customers");
		rbtnSC.setBounds(31, 98, 154, 23);
		panel.add(rbtnSC);
		rbtnSC.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		b.add(rbtnAC);
		b.add(rbtnSC);
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rbtnAC.isSelected()){
					Reports.getAllCust();
				}
			}
		});
		button.setBounds(143, 147, 47, 39);
		panel.add(button);
		button.setIcon(new ImageIcon(CustomerOption.class.getResource("/images/continue.png")));
		
		setSize(250,232);

	}
	public static void main(String[] args) {
		ConnectToDataBase.main(null);
		Main4.setNimbus();
		create();
	}
	public static void create() {
		f = new JFrame("Report of Customer");
		f.getContentPane().add(new CustomerOption());
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getDefaultScreenDevice();
		int width = gd.getDisplayMode().getWidth();
		int height = gd.getDisplayMode().getHeight();

		int w = 270, h = 270;
		f.setLocation(width / 2 - (w / 2), height / 2 - (h / 2));

		f.setSize(w, h);
		f.setVisible(true);
	}
}
