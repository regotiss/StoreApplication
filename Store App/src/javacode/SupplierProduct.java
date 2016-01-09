package javacode;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.UIManager;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.Statement;

public class SupplierProduct extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JFrame f;
	private JList<String> listProd;
	private JList<String> list_suppprod;
	private JScrollPane sc_suppprod;
	private JButton btnAddOne;
	private JButton btnAddAll;
	private JButton btnDelOne;
	private JButton btnDelAll;
	private Statement s;
	private JScrollPane sp_prod;
	private DefaultListModel<String> modelPro;
	private DefaultListModel<String> modelSuppPro;
	private String sid;
	private JButton btnAdd;
	/**
	 * Create the panel.
	 * @param sid 
	 */
	
	public SupplierProduct(String sid) {
		setSize(700, 650);
		setLayout(new BorderLayout(0, 0));
		
		this.sid=sid;
		s=ConnectToDataBase.getS();
		//JPanel panel = new JPanel();
		JLabel panel = new JLabel(new ImageIcon(SupplierProduct.class.getResource("/images/suppprod.png")));
		
		add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		sp_prod = new JScrollPane();
		sp_prod.setBorder(UIManager.getBorder("TitledBorder.border"));
		sp_prod.setBounds(26, 102, 260, 403);
		panel.add(sp_prod);
		
		modelPro = new DefaultListModel<String>();
		getProdList();
		listProd = new JList<String>(modelPro);
		sp_prod.setViewportView(listProd);
		
		sc_suppprod = new JScrollPane();
		sc_suppprod.setBounds(389, 102, 258, 401);
		sc_suppprod.setBorder(UIManager.getBorder("TitledBorder.border"));
		panel.add(sc_suppprod);
		
		modelSuppPro = new DefaultListModel<String>();
		list_suppprod = new JList<String>(modelSuppPro);
		sc_suppprod.setViewportView(list_suppprod);
		
		btnAddOne = new JButton("");
		btnAddOne.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 int index = listProd.getSelectedIndex();
				 if(index >= 0){
				        
					 btnAdd.setEnabled(true);
					 String s=listProd.getSelectedValue();
					 modelPro.removeElementAt(index);
				     modelSuppPro.addElement(s);
				 }
				 else{
					 JOptionPane.showMessageDialog(null, "Please Select Item", "Error", JOptionPane.WARNING_MESSAGE);
				 }
					
			}
		});
		btnAddOne.setIcon(new ImageIcon(SupplierProduct.class.getResource("/images/bb2.png")));
		btnAddOne.setBounds(309, 175, 47, 41);
		panel.add(btnAddOne);
		
		btnAddAll = new JButton("");
		btnAddAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i=0;i<modelPro.size();){
					String s=modelPro.getElementAt(i);
					modelPro.removeElementAt(i);
					modelSuppPro.addElement(s);
				}
				btnAdd.setEnabled(true);
			}
		});
		btnAddAll.setIcon(new ImageIcon(SupplierProduct.class.getResource("/images/r1.png")));
		btnAddAll.setBounds(309, 229, 47, 41);
		panel.add(btnAddAll);
		
		btnDelOne = new JButton("");
		btnDelOne.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 int index = list_suppprod.getSelectedIndex();
				 if(index >= 0){
				        
					 String s=list_suppprod.getSelectedValue();
					 modelSuppPro.removeElementAt(index);
					 modelPro.addElement(s);
					 if(modelSuppPro.size()==0)
						 btnAdd.setEnabled(false);
				 }
				 else{
					 JOptionPane.showMessageDialog(null, "Please Select Item", "Error", JOptionPane.WARNING_MESSAGE);
				 }
			}
		});
		btnDelOne.setIcon(new ImageIcon(SupplierProduct.class.getResource("/images/bb1.png")));
		btnDelOne.setBounds(309, 283, 47, 41);
		panel.add(btnDelOne);
		
		btnDelAll = new JButton("");
		btnDelAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i=0;i<modelSuppPro.size();){
					String s=modelSuppPro.getElementAt(i);
					modelSuppPro.removeElementAt(i);
					modelPro.addElement(s);
				}
				btnAdd.setEnabled(false);
			}
		});
		btnDelAll.setIcon(new ImageIcon(SupplierProduct.class.getResource("/images/l1.png")));
		btnDelAll.setBounds(309, 337, 47, 41);
		panel.add(btnDelAll);
		
		btnAdd = new JButton("ADD");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addToSuppTbl();
			}
		});
		btnAdd.setEnabled(false);
		
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAdd.setBounds(565, 515, 82, 27);
		panel.add(btnAdd);
		
		JButton btnAddNewProduct = new JButton("Add New Product");
		btnAddNewProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Company.main(null);
			}
		});
		btnAddNewProduct.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAddNewProduct.setBounds(399, 515, 155, 28);
		panel.add(btnAddNewProduct);
	}
	public void addToSuppTbl() {
		try
		{
			int res=JOptionPane.showConfirmDialog(null, "Are you sure ?");
			if(res!=1&&res!=2)
			{
				for(int i=0;i<modelSuppPro.size();i++){
					String pid=modelSuppPro.getElementAt(i);
					pid=pid.substring(1, pid.indexOf('.'));
					s.executeQuery("insert into SupplierProducts values("+sid+","+pid+")");
					
				}
				JOptionPane.showMessageDialog(f, "Added Successfully");
				f.setVisible(false);
				f.dispose();
			}
			
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	public void getProdList()
	{
		
		try
		{
			ResultSet rs=s.executeQuery("select pid,pname from product1 where pid not in (select pid from SupplierProducts where sid="+sid+")");
			String str="";
			while(rs.next()){
				str+=" "+rs.getString(1)+"."+rs.getString(2);
				modelPro.addElement(str);
				str="";
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	
	}
	public static void main(String args[]){
		
		ConnectToDataBase.main(null);
		Main1.setNimbus();
		create("1");
	}
	public static void create(String sid) {
		f = new JFrame("Add Products Supplied By Supplier "+sid);

		Main1.setNimbus();
		f.getContentPane().add(new SupplierProduct(sid));
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getDefaultScreenDevice();
		int width = gd.getDisplayMode().getWidth();
		int height = gd.getDisplayMode().getHeight();

		int w=700,h=600;
		f.setLocation(width / 2 - w/2, height / 2 - h/2);


		f.setSize(w, h);
		f.setVisible(true);
	}
}
