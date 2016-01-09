package javacode;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;


public class PlaceOrder extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JFrame f;
	private JTextField tfId;
	private JTextField tfName;
	private JTextField tfQua;
	private JTable table;
	private JScrollPane sp_ordered;
	private JTextField tfOrderno;
	private JTextField tfBill;
	private JPanel pnlFront;
	private JPanel pnlDet;
	private JPanel bak;
	private String id;
	private double rate;
	private String sid;
	private Statement s;
	private int orderno;
	private int quantity;
	private double bill ;
	private boolean first;
	private Vector<Vector<String>> vv;
	private Vector<String> header;
	private DefaultTableModel model;

	/**
	 * Create the panel.
	 * @param name 
	 * @param id 
	 */
	public PlaceOrder(String id, String name,final String sid) {
		
		setSize(650,300);
		quantity=0;
		setLayout(new BorderLayout(0, 0));
	
		s=ConnectToDataBase.getS();
		
		this.id=id;
		this.sid=sid;
		findRate();
		
		vv=new Vector<Vector<String>>();
		header=new Vector<String>();
		header.add("Id");
		header.add("Name");
		header.add("Quantity");
		
		bak = new JPanel();
		bak.setBackground(new Color(128, 0, 128));
		add(bak, BorderLayout.CENTER);
		bak.setLayout(null);
		
		
		pnlFront = new JPanel();
		pnlFront.setBorder(UIManager.getBorder("TitledBorder.border"));
		pnlFront.setBackground(SystemColor.text);
		pnlFront.setBounds(6, 6, 588, 249);
		bak.add(pnlFront);
		pnlFront.setLayout(null);
		
		pnlDet = new JPanel();
		pnlDet.setBounds(16, 16, 241, 172);
		pnlFront.add(pnlDet);
		pnlDet.setBackground(SystemColor.control);
		pnlDet.setLayout(null);
		
		JLabel lblId = new JLabel("ID :");
		lblId.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblId.setBounds(46, 30, 37, 14);
		pnlDet.add(lblId);
		
		tfId = new JTextField(id);
		tfId.setEditable(false);
		tfId.setBounds(82, 23, 128, 28);
		pnlDet.add(tfId);
		tfId.setColumns(10);
		
		JLabel lblName = new JLabel("Name :");
		lblName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblName.setBounds(31, 62, 46, 14);
		pnlDet.add(lblName);
		
		tfName = new JTextField(name);
		tfName.setEditable(false);
		tfName.setColumns(10);
		tfName.setBounds(82, 55, 128, 28);
		pnlDet.add(tfName);
		
		JLabel lblQuantity = new JLabel("Quantity :");
		lblQuantity.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblQuantity.setBounds(10, 94, 62, 14);
		pnlDet.add(lblQuantity);
		
		tfQua = new JTextField();
		tfQua.setColumns(10);
		tfQua.setBounds(82, 87, 128, 28);
		pnlDet.add(tfQua);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String qua=tfQua.getText();
				@SuppressWarnings("unchecked")
				Vector<Vector<String>> v_chk = model.getDataVector();
			
				for(int i=0;i<v_chk.size();i++){
					
					if((tfId.getText()).equals(v_chk.get(i).get(0))){
						qua=""+(Integer.parseInt(qua)+Integer.parseInt(v_chk.get(i).get(2)));
						model.setValueAt(qua, i, 2);
						quantity=Integer.parseInt(qua);
						Double price=rate*Integer.parseInt(qua);
						bill+=price;
						tfBill.setText(""+bill);
						return;
					}
			
				}
				
				Vector<String> v=new Vector<String>();
				v.addElement(tfId.getText());
				v.addElement(tfName.getText());
				v.addElement(qua);
				quantity=Integer.parseInt(qua);
				Double price=rate*Integer.parseInt(qua);
				bill+=price;
				tfBill.setText(""+bill);
				model.addRow(v);
			}
		});
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAdd.setBounds(135, 126, 75, 28);
		pnlDet.add(btnAdd);
		
		sp_ordered = new JScrollPane();
		sp_ordered.setBackground(new Color(255, 255, 255));
		sp_ordered.setBounds(280, 16, 290, 172);
		pnlFront.add(sp_ordered);
		sp_ordered.setBorder(UIManager.getBorder("TitledBorder.border"));
		
		calc();
		model = getProducts();
		table=new JTable(model);
		sp_ordered.setViewportView(table);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});
		btnSave.setBounds(494, 197, 75, 28);
		pnlFront.add(btnSave);
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel lblOrderNo = new JLabel("Order No :");
		lblOrderNo.setBounds(26, 204, 69, 14);
		pnlFront.add(lblOrderNo);
		lblOrderNo.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		
		tfOrderno = new JTextField(""+orderno);
		tfOrderno.setBounds(93, 199, 95, 28);
		pnlFront.add(tfOrderno);
		tfOrderno.setEditable(false);
		tfOrderno.setColumns(10);
		
		JLabel lblBill = new JLabel("Total Bill :");
		lblBill.setBounds(282, 204, 69, 14);
		pnlFront.add(lblBill);
		lblBill.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		tfBill = new JTextField(""+bill);
		tfBill.setBounds(361, 197, 123, 28);
		pnlFront.add(tfBill);
		tfBill.setEditable(false);
		tfBill.setColumns(10);
		
	}
	public void save() {
		
		int res=JOptionPane.showConfirmDialog(null, "Are you sure ?");
		if(res!=1&&res!=2)
		{
			try {
				if(first){
					s.execute("insert into placeorder values("+orderno+","+sid+","+ bill + ",sysdate,'no')");
					//s.execute("insert into prod_ordered values("+id+","+orderno+","+quantity+")");
				}
				

				if(!isRecordPresent())
				s.execute("insert into prod_ordered values("+id+","+orderno+","+quantity+")");
				else
				s.execute("update prod_ordered set quantity="+quantity+" where orderno="+orderno+" and pid="+id);
				
				if(!first)
				s.execute("update placeorder set bill="+bill+" where orderno="+orderno);
				JOptionPane.showMessageDialog(null, "Saved Successfully");
				f.setVisible(false);
				f.dispose();
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	private boolean isRecordPresent() {
		boolean flg=false;
		try
		{
			ResultSet rs=s.executeQuery("select pid from prod_ordered where orderno="+orderno+" and pid="+id);
			while(rs.next()){
				flg=true;
				break;
			}
		}
		catch(Exception e){}
	
		return flg;
	}
	public double findRate() {
		try
		{
			ResultSet rs=s.executeQuery("select prate from product1 where pid="+id);
			rs.next();
			rate=rs.getDouble(1);
		}
		catch(Exception e){}
		return 0;
	}
	public DefaultTableModel getProducts() {
		DefaultTableModel j=null;
		try
		{
			ResultSet rs=s.executeQuery("select pid,pname,quantity from prod_ordered natural join product1 where orderno="+orderno);
			
			Vector<String> tmp = new Vector<String>();
			while(rs.next())
			{
				for(int i=1;i<=header.size();i++){
					tmp.add(rs.getString(i));
				}
				vv.add(new Vector<String>(tmp));
				tmp.clear();
			}
			//rs=s.executeQuery("select bill");
			j=new DefaultTableModel(vv,header);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return j;
	}
	public static void main(String[] args) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		Main1.setNimbus();
		ConnectToDataBase.main(null);
		create("21","abc","2");
	}
	public static void create(String id,String name,String sid) {
		f = new JFrame("Order to Supplier No."+sid);
		f.getContentPane().add(new PlaceOrder(id,name,sid));

		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getDefaultScreenDevice();
		int width = gd.getDisplayMode().getWidth();
		int height = gd.getDisplayMode().getHeight();

		int w = 620, h = 300;
		f.setLocation(width / 2 - (w / 2), height / 2 - (h / 2));

		f.setSize(w, h);
		f.setVisible(true);
	}
	
	public void calc() {
		try {
		
			
			ResultSet rs=s.executeQuery("select orderno,bill from placeorder where sid="+sid+" and isreceived='no'");
			
			while(rs.next()){
				
				orderno=rs.getInt(1);
				bill=Double.parseDouble(rs.getString(2));
				first=false;
				return;
			}
			bill=0;
			String st = "SELECT max(orderno) FROM placeorder";
			s.execute(st);
			rs = s.getResultSet();
			rs.next();
			orderno=rs.getInt(1)+1;
			first=true;
			
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "error " + e.getMessage(),
					"error", JOptionPane.ERROR_MESSAGE);
		}
	}

}
