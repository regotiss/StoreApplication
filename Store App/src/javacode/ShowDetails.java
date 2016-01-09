package javacode;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.border.TitledBorder;

import java.awt.Color;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.ImageIcon;



public class ShowDetails extends JPanel {

	private static final long serialVersionUID = 1L;
	private static JFrame f;
	private JTextField tfsuppId;
	private JTextField tfbill;
	private JTable table;
	private JList<?> Purch_list;
	private JList<?> Order_list;
	private JLabel lblDate;
	private Statement st;
	private int day;
	private int month;
	private int year;
	private JScrollPane scrollPane;
	private Vector<Vector<String>> vv;
	private Vector<String> v;
	private JLabel lblNewLabel;
	
	public static void main(String args[]){
		
		Main2.setNimbus();
		ConnectToDataBase.main(null);
		create(21,6,2015);
	}
	public ShowDetails(int day, int month, int year) {
		setBorder(UIManager.getBorder("TitledBorder.border"));
		setLayout(null);
		
		vv=new Vector<Vector<String>>();
		v=new Vector<String>();
		v.add("ID");
		v.add("Product Name");
		v.add("Quantity");
		//v.add("MinLimit");
		v.add("Rate");
		v.add("Type");
		v.add("Available");

		
		setBackground(new Color(148,72,197));
		this.day=day;
		this.month=month;
		this.year=year;
		st=ConnectToDataBase.getS();
		lblDate = new JLabel("Date :"+day+"/"+month+"/"+year);
		lblDate.setForeground(new Color(255, 102, 51));
		lblDate.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 16));
		lblDate.setBounds(780, 41, 149, 24);
		add(lblDate);
		
		JPanel panelGrn = new JPanel();
		panelGrn.setBackground(Color.WHITE);
		panelGrn.setBorder(new TitledBorder(null, "GRN no :", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelGrn.setBounds(35, 97, 149, 370);
		add(panelGrn);
		panelGrn.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(19, 24, 113, 329);
		panelGrn.add(scrollPane_1);
		
		Purch_list = new JList<Object>(getPurchDetails());
		Purch_list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				showDetailsOfPurch(Purch_list.getSelectedValue());
			}
		});
		scrollPane_1.setViewportView(Purch_list);
		
		JPanel panelOrder = new JPanel();
		panelOrder.setLayout(null);
		panelOrder.setBorder(new TitledBorder(null, "Order no :", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(59, 59, 59)));
		panelOrder.setBackground(Color.WHITE);
		panelOrder.setBounds(780, 97, 149, 370);
		add(panelOrder);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(19, 24, 113, 328);
		panelOrder.add(scrollPane_2);
		
		Order_list = new JList<Object>(getOrderDetails());
		scrollPane_2.setViewportView(Order_list);
		Order_list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				showDetailsOfOrder(Order_list.getSelectedValue());
			}

		});
		
		JPanel panelDet = new JPanel();
		panelDet.setBorder(UIManager.getBorder("TitledBorder.border"));
		panelDet.setBackground(Color.WHITE);
		panelDet.setBounds(206, 25, 562, 58);
		add(panelDet);
		
		JLabel lblSupplierId = new JLabel("Supplier ID :");
		panelDet.add(lblSupplierId);
		
		tfsuppId = new JTextField();
		panelDet.add(tfsuppId);
		tfsuppId.setColumns(5);
		
		JLabel lblTotalBill = new JLabel("Total Bill :");
		panelDet.add(lblTotalBill);
		
		tfbill = new JTextField();
		panelDet.add(tfbill);
		tfbill.setColumns(10);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(206, 96, 562, 367);
		add(scrollPane);
		
		
		table = new JTable(vv,v);
		table.setFillsViewportHeight(true);
		scrollPane.setBackground(Color.white);
		scrollPane.setViewportView(table);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(ShowDetails.class.getResource("/images/logo3.png")));
		lblNewLabel.setBounds(22, 25, 172, 62);
		add(lblNewLabel);
		
	}
	
	protected void showDetailsOfOrder(Object selectedValue) {
		try
		{
			
			Vector<Vector<String>> vv=new Vector<Vector<String>>();
			Vector<String> v = new Vector<String>();
			
			String s=(String)selectedValue;
			ResultSet rs=st.executeQuery("select sid,bill from purchase1 where grnno="+s);
			while(rs.next()){
				tfsuppId.setText(rs.getString(1));
				tfbill.setText(rs.getString(2));
			}
			rs=st.executeQuery("select pid,pname,quantity,prate,ptype, avail from prod_ordered natural join product1 natural join available1 where orderno="+s);
			
			while(rs.next()){
				for(int i=1;i<=6;i++){
					v.add(rs.getString(i));
					
				}
				vv.add(new Vector<String>(v));
				v.clear();
			}
			table = new JTable(vv,this.v);
			table.setFillsViewportHeight(true);
			scrollPane.setViewportView(table);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	public void showDetailsOfPurch(Object object) {
		try
		{
			Vector<Vector<String>> vv=new Vector<Vector<String>>();
			Vector<String> v = new Vector<String>();
			
			String s=(String)object;
			ResultSet rs=st.executeQuery("select sid,bill from purchase1 where grnno="+s);
			while(rs.next()){
				tfsuppId.setText(rs.getString(1));
				tfbill.setText(rs.getString(2));
			}
			rs=st.executeQuery("select pid,pname,quantity,prate,ptype, avail from prod_purch natural join product1 natural join available1 where grnno="+s);
			
			while(rs.next()){
				for(int i=1;i<=6;i++){
					v.add(rs.getString(i));
					
				}
				vv.add(new Vector<String>(v));
				v.clear();
			}
			
			table = new JTable(vv,this.v);
			table.setFillsViewportHeight(true);
			scrollPane.setViewportView(table);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void create(int day,int month,int year){
		
		f=new JFrame();
		f.getContentPane().add(new ShowDetails(day,month,year));
		f.setVisible(true);
		f.setBounds(100,100,970, 550);
	}
	public Vector<String> getPurchDetails()
	{
		Vector<String> v=new Vector<String>();
		try
		{
			ResultSet rs=st.executeQuery("select grnno from purchase1 where extract(day from purch_date)="+day+" and extract(month from purch_date)="+month+" and extract (year from purch_date)="+year);
			while(rs.next()){
				v.add(rs.getString(1));
			}
		}
		catch(Exception e){}
		return v;
	}
	public Vector<String> getOrderDetails()
	{
		Vector<String> v=new Vector<String>();
		try
		{
			ResultSet rs=st.executeQuery("select orderno from placeorder where extract(day from ord_date)="+day+" and extract(month from ord_date)="+month+" and extract (year from ord_date)="+year);
			while(rs.next()){
				v.add(rs.getString(1));
			}
		}
		catch(Exception e){}
		return v;
	}
}
