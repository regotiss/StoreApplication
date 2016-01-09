package reports;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormatSymbols;

import javacode.ConnectToDataBase;
import javacode.Main4;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class SuppWeek extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JFrame f;
	private DefaultComboBoxModel<String> model;
	private DefaultComboBoxModel<String> monthmodel;
	private JComboBox<String> monthsel;
	private JComboBox<String> comboBox;
	private JCheckBox ckprod;
	/**
	 * Create the panel.
	 */
	public SuppWeek() {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(UIManager.getBorder("TitledBorder.border"));
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11, 259, 202);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblSelectMonth = new JLabel("Select Month :");
		lblSelectMonth.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSelectMonth.setBounds(26, 26, 104, 27);
		panel.add(lblSelectMonth);
		
		JLabel lblSelectStartDate = new JLabel("Select Start Date :");
		lblSelectStartDate.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSelectStartDate.setBounds(26, 82, 152, 27);
		panel.add(lblSelectStartDate);
		
		model=new DefaultComboBoxModel<String>(new String[] {"1", "8", "15", "22", "29"});
		comboBox = new JComboBox<String>(model);
		comboBox.setBounds(157, 86, 78, 20);
		panel.add(comboBox);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ckprod.isSelected())
					Reports.getSuppProd(""+(monthsel.getSelectedIndex()+1),(Integer.parseInt(""+comboBox.getSelectedItem())+1));
				else
					Reports.getSupp(""+(monthsel.getSelectedIndex()+1),(Integer.parseInt(""+comboBox.getSelectedItem())+1));
			}
		});
		btnOk.setBounds(157, 156, 78, 23);
		panel.add(btnOk);
		
		
		monthmodel=new DefaultComboBoxModel<String>((new DateFormatSymbols()).getShortMonths());
		monthsel = new JComboBox<String>(monthmodel);
		monthsel.setBounds(157, 30, 78, 20);
		monthsel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(monthsel.getSelectedIndex()==1){
					model.removeElement("29");
				}
				else
					if(!(model.getIndexOf("29")>0))
					model.addElement("29");
				
			}
		});
		panel.add(monthsel);
		
		ckprod = new JCheckBox("With Product Details");
		ckprod.setFont(new Font("Tahoma", Font.BOLD, 13));
		ckprod.setBounds(26, 130, 209, 23);
		panel.add(ckprod);

	}
	public static void main(String[] args) {
		ConnectToDataBase.main(null);
		Main4.setNimbus();
		create();
	}
	public static void create() {
		f = new JFrame("Weekly Report");
		f.getContentPane().add(new SuppWeek());
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getDefaultScreenDevice();
		int width = gd.getDisplayMode().getWidth();
		int height = gd.getDisplayMode().getHeight();

		int w = 310, h = 300;
		f.setLocation(width / 2 - (w / 2), height / 2 - (h / 2));

		f.setSize(w, h);
		f.setVisible(true);
	}
}
