package javacode;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.Color;

import javax.swing.border.TitledBorder;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class ChooseSupplier extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JDialog dialog;
	private final JPanel contentPanel = new JPanel();
	private Statement s;
	private String id;
	private String name;
	private JComboBox<String> comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ConnectToDataBase.main(null);
			create("21","abc");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void create(String id,String name)
	{
		dialog = new ChooseSupplier(id,name);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);
	}
	/**
	 * Create the dialog.
	 */
	public ChooseSupplier(String pid,String pname) {
		
		
		setBounds(100, 100, 396, 194);
		
		this.id=pid;
		name=pname;
		s=ConnectToDataBase.getS();
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBorder(new TitledBorder(null, "Choose Supplier", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		
		comboBox = new JComboBox<String>(new DefaultComboBoxModel<>(getSuppliers()));
		comboBox.setBounds(67, 24, 198, 26);
		contentPanel.add(comboBox);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String supp=""+comboBox.getSelectedItem();
						supp=supp.substring(0, supp.indexOf('.'));
						PlaceOrder.create(id, name,supp);
						dialog.setVisible(false);
						dialog.dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dialog.setVisible(false);
						dialog.dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	private Vector<String> getSuppliers() {
		
		Vector<String> v=new Vector<String>();
		try
		{
			ResultSet rs=s.executeQuery("select distinct sid,sname from SupplierProducts natural join supplier where pid="+id);
			while(rs.next()){
				String s=rs.getString(1)+"."+rs.getString(2);
				v.add(s);
			}
			
		}
		catch(Exception e){}
		return v;
	}
}
