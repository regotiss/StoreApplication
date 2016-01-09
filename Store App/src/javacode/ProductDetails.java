package javacode;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;

import javax.swing.JTable;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.Vector;

import javax.swing.border.BevelBorder;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ProductDetails extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JFrame f;
	private JPanel bak;
	private JTable table;
	private Vector<Vector<String>> vv;
	private Vector<String> v;
	private Statement s;
	private String billno;
	private JScrollPane sc;
	private String whichId;
	/**
	 * Create the panel.
	 */
	public ProductDetails(String billn,String whichId) {
		
		billno=billn;
		this.whichId=whichId;
		setLayout(new BorderLayout(0, 0));
		
		v=new Vector<String>();
		v.add("ID");
		v.add("Product Name");
		v.add("Rate");
		v.add("Quantity");
		
		s=ConnectToDataBase.getS();
		setSize(450,600);
		bak = new JPanel();
		bak.setBackground(new Color(138, 43, 226));
		add(bak, BorderLayout.CENTER);
		bak.setLayout(null);
		
		table = getDetails();
		table.setFillsViewportHeight(true);
		
		sc=new JScrollPane(table);
		sc.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		sc.setBounds(10, 62, 400, 427);
		bak.add(sc);
		
		JLabel lblProductDetails = new JLabel("Product Details");
		lblProductDetails.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblProductDetails.setForeground(new Color(255, 255, 255));
		lblProductDetails.setBounds(129, 11, 136, 50);
		bak.add(lblProductDetails);
		
		JButton btnPrint = new JButton("Print");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					MessageFormat header=new MessageFormat("Report Print");
					MessageFormat footer=new MessageFormat("Page{0,number,integer}");
					table.print(JTable.PrintMode.NORMAL,header,footer);
				}catch(Exception e1){
					JOptionPane.showMessageDialog(null,
							"error " + e1.getMessage(), "error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnPrint.setIcon(new ImageIcon(ProductDetails.class.getResource("/images/print.PNG")));
		btnPrint.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnPrint.setBounds(223, 500, 89, 35);
		bak.add(btnPrint);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				f.setVisible(false);
				f.dispose();
			}
		});
		btnOk.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnOk.setBounds(322, 500, 89, 35);
		bak.add(btnOk);

	}
	
	public JTable getDetails(){
		
		vv=new Vector<Vector<String>>();
		JTable t=new JTable(vv,v);
		try
		{
			String sql="";
			if(whichId.equals("bill"))
			sql="select pid,pname,prate,quantity from cust_purch natural join product1 where billno="+billno;
			else if(whichId.equals("supplier"))
			{
				sql="select pid,pname,prate,avail from available1 natural join product1 natural join SupplierProducts where sid="+billno;
			}
			
			ResultSet rs=s.executeQuery(sql);
			Vector<String> sv=new Vector<String>();
			while(rs.next()){
				for(int i=1;i<=v.size();i++)
					sv.add(rs.getString(i));
				vv.add(new Vector<String>(sv));
				sv.clear();
			}
			t=new JTable(vv,v);
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return t;
	}
	public static void main(String args[]){
		
		ConnectToDataBase.main(null);
		Main1.setNimbus();
		create("1","bill");
	}
	public static void create(String billno,String whichId){
		
		f=new JFrame();
		f.getContentPane().add(new ProductDetails(billno,whichId));
		f.setVisible(true);
		
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getDefaultScreenDevice();
		int width = gd.getDisplayMode().getWidth();
		int height = gd.getDisplayMode().getHeight();

		int w=435,h=600;
		f.setLocation(width / 2 - w/2, height / 2 - h/2);

		f.setSize(w,h);
	}
}
