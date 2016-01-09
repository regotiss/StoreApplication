package javacode;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;

public class StockOpt extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JFrame frame;
	private JTextField tfOrder;
	private JTextField tfSupp;
	private JTable tableOrd;
	private JTable tblReceiv;
	private JTextField tfid;
	private JTextField tfnm;
	private JTextField tfrate;
	private JTextField tfcomp;
	private JTextField tftype;
	private JTextField tfQua;
	private Statement s;
	private JTextField tfdate;

	Vector<Vector<String>> vv,vv1;
	Vector<String> sv,sv1;
	private JScrollPane sp_prod;
	private JScrollPane scrollPane;
	private int grn;
	private double bill;
	private JLabel lblBill;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					Main1.setNimbus();
					ConnectToDataBase.main(null);
					create();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public static void create(){
		
		frame = new JFrame("Add Purchase Details After Order is Received");
		frame.setBounds(100, 100, 1250, 582);
		JPanel p=new StockOpt();
		frame.getContentPane().add(p, BorderLayout.CENTER);
		frame.setVisible(true);
	}
	/**
	 * Create the application.
	 */
	public StockOpt() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		
		setLayout(new BorderLayout());
		s=ConnectToDataBase.getS();
		JPanel panel = new JPanel();
		//panel.setBackground(new Color(148,72,197));
		panel.setBackground(new Color(216,133,255));
		add(panel);
		panel.setLayout(null);
		
		panel.getInputMap().put(KeyStroke.getKeyStroke("ESCAPE"),"action");
		panel.getActionMap().put("action", new AbstractAction() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				
			}
		});
		vv1=new Vector<Vector<String>>();
		sv1=new Vector<String>();
		sv1.add("ID");
		sv1.add("Name");
		sv1.add("Rate");
		sv1.add("Company");
		sv1.add("Type");
		sv1.add("Quantity");
		grn=0;
		bill=0;
		
		JScrollPane sp_order = new JScrollPane();
		sp_order.setBackground(new Color(204, 0, 0));
		sp_order.setBorder(new TitledBorder(null, "Orders Remaining", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 0)));
		sp_order.setBounds(6, 91, 137, 435);
		panel.add(sp_order);
		
		final JList<String> list_order = new JList<String>(getListOfOrders());
		sp_order.setViewportView(list_order);
		list_order.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				getDetails(list_order.getSelectedValue());
				
			}
		});
		JPanel pnlOrdDet = new JPanel();
		pnlOrdDet.setForeground(new Color(128, 0, 0));
		pnlOrdDet.setBorder(new TitledBorder(null, "Order Details", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(59, 59, 59)));
		pnlOrdDet.setBackground(Color.WHITE);
		pnlOrdDet.setBounds(144, 91, 721, 78);
		panel.add(pnlOrdDet);
		pnlOrdDet.setLayout(null);
		
		JLabel lblOrderNo = new JLabel("Order No :");
		lblOrderNo.setBounds(16, 39, 62, 16);
		pnlOrdDet.add(lblOrderNo);
		
		tfOrder = new JTextField();
		tfOrder.setEditable(false);
		tfOrder.setBounds(90, 33, 122, 28);
		pnlOrdDet.add(tfOrder);
		tfOrder.setColumns(10);
		
		JLabel lblSupplierId = new JLabel("Supplier ID");
		lblSupplierId.setBounds(224, 39, 71, 16);
		pnlOrdDet.add(lblSupplierId);
		
		tfSupp = new JTextField();
		tfSupp.setEditable(false);
		tfSupp.setBounds(293, 33, 122, 28);
		pnlOrdDet.add(tfSupp);
		tfSupp.setColumns(10);
		
		JLabel lblOrderedDate = new JLabel("Ordered Date :");
		lblOrderedDate.setBounds(434, 39, 85, 16);
		pnlOrdDet.add(lblOrderedDate);
		
		tfdate = new JTextField();
		tfdate.setEditable(false);
		tfdate.setBounds(531, 33, 122, 28);
		pnlOrdDet.add(tfdate);
		tfdate.setColumns(10);
		
		sp_prod = new JScrollPane();
		sp_prod.setBackground(new Color(255, 0, 0));
		sp_prod.setBorder(new TitledBorder(null, "Products Ordered", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 0)));
		sp_prod.setBounds(144, 183, 466, 331);
		panel.add(sp_prod);
		
		tableOrd = new JTable();
		sp_prod.setViewportView(tableOrd);
		
		JPanel pnlProd = new JPanel();
		pnlProd.setBorder(new TitledBorder(null, "Products to add in received list", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(59, 59, 59)));
		pnlProd.setBackground(Color.WHITE);
		pnlProd.setBounds(622, 187, 243, 315);
		panel.add(pnlProd);
		pnlProd.setLayout(null);
		
		JLabel lblID = new JLabel("ID :");
		lblID.setBounds(27, 32, 55, 16);
		pnlProd.add(lblID);
		
		JLabel lblName = new JLabel("Name :");
		lblName.setBounds(27, 74, 55, 16);
		pnlProd.add(lblName);
		
		JLabel lblRate = new JLabel("Rate :");
		lblRate.setBounds(27, 121, 55, 16);
		pnlProd.add(lblRate);
		
		JLabel lblCompany = new JLabel("Company :");
		lblCompany.setBounds(27, 162, 65, 16);
		pnlProd.add(lblCompany);
		
		JLabel lblType = new JLabel("Type :");
		lblType.setBounds(27, 196, 55, 16);
		pnlProd.add(lblType);
		
		JLabel lblQuantity = new JLabel("Quantity :");
		lblQuantity.setBounds(27, 236, 55, 16);
		pnlProd.add(lblQuantity);
		
		tfid = new JTextField();
		tfid.setEditable(false);
		tfid.setBounds(104, 26, 122, 28);
		pnlProd.add(tfid);
		tfid.setColumns(10);
		
		tfnm = new JTextField();
		tfnm.setEditable(false);
		tfnm.setBounds(104, 68, 122, 28);
		pnlProd.add(tfnm);
		tfnm.setColumns(10);
		
		tfrate = new JTextField();
		tfrate.setEditable(false);
		tfrate.setBounds(104, 115, 122, 28);
		pnlProd.add(tfrate);
		tfrate.setColumns(10);
		
		tfcomp = new JTextField();
		tfcomp.setEditable(false);
		tfcomp.setBounds(104, 156, 122, 28);
		pnlProd.add(tfcomp);
		tfcomp.setColumns(10);
		
		tftype = new JTextField();
		tftype.setEditable(false);
		tftype.setBounds(104, 190, 122, 28);
		pnlProd.add(tftype);
		tftype.setColumns(10);
		
		tfQua = new JTextField();
		tfQua.setBounds(104, 230, 122, 28);
		pnlProd.add(tfQua);
		tfQua.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i;
				for(i=0;i<vv.size();i++){
					if(vv.get(i).get(0).equals(tfid.getText()))
					{
						break;
						
					}
				}
				if(i!=vv.size()){
					vv.remove(i);
					tableOrd=new JTable(vv,sv);
					tableOrd.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
						
						@Override
						public void valueChanged(ListSelectionEvent e) {
							
							updateTbl();
						}
					});
					sp_prod.setViewportView(tableOrd);
					update();
					tblReceiv=new JTable(vv1,sv1);
					scrollPane.setViewportView(tblReceiv);
				}
			}
		});
		btnAdd.setBounds(19, 270, 90, 28);
		pnlProd.add(btnAdd);
		
		JButton btnReset = new JButton("Reset");
		btnReset.setBounds(121, 270, 90, 28);
		pnlProd.add(btnReset);
		
		scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(255, 0, 0));
		scrollPane.setBorder(new TitledBorder(null, "Order Received List", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 0)));
		scrollPane.setBounds(883, 91, 331, 416);
		panel.add(scrollPane);
		
		tblReceiv = new JTable();
		scrollPane.setViewportView(tblReceiv);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					try {
					
						s.execute("insert into purchase1 values("
								+ grn + "," + tfSupp.getText()
								+ "," + bill + ",sysdate,"+tfOrder.getText()+")");
						for (int i = 0; i < vv1.size(); i++) {
	
							s.execute("insert into prod_purch values("
									+ vv1.get(i).get(0) + "," + grn
									+ "," + vv1.get(i).get(5) + ")");
	
						}
						s.execute("update placeorder set isreceived='yes' where orderno="+tfOrder.getText());
						JOptionPane.showMessageDialog(null, "Saved Successfully");
						frame.setVisible(false);
						
					} catch (Exception e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null,
								"error " + e1.getMessage(), "error",
								JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnSave.setBounds(1009, 510, 103, 28);
		panel.add(btnSave);
		
		JPanel billpnl = new JPanel();
		billpnl.setBackground(Color.WHITE);
		billpnl.setBorder(UIManager.getBorder("TitledBorder.border"));
		billpnl.setBounds(883, 23, 331, 51);
		panel.add(billpnl);
		billpnl.setLayout(null);
		
		calcGrn();
		
		JLabel lblBillNo = new JLabel("GRN No."+grn);
		lblBillNo.setBounds(19, 17, 142, 16);
		billpnl.add(lblBillNo);
		
		lblBill = new JLabel("Bill :"+bill);
		lblBill.setBounds(165, 17, 149, 16);
		billpnl.add(lblBill);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(StockOpt.class.getResource("/images/logo3.png")));
		label.setBounds(46, 6, 198, 73);
		panel.add(label);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(StockOpt.class.getResource("/images/goods.png")));
		label_1.setBounds(341, 23, 409, 51);
		panel.add(label_1);
	}

	private void calcGrn() {
		// TODO Auto-generated method stub
		try {
		
			ResultSet rs = s.executeQuery("SELECT max(grnno) FROM purchase1");
			rs.next();
			String str=rs.getString(1);
			if(str.length()>0&&str!=null)
			grn=rs.getInt(1)+1;
			else
			grn=1;
			
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "error " + e.getMessage(),
					"error", JOptionPane.ERROR_MESSAGE);
		}
	}

	protected void update() {
		
		Vector<String> sv1=new Vector<String>();
		sv1.add(tfid.getText());
		sv1.add(tfnm.getText());
		sv1.add(tfrate.getText());
		sv1.add(tfcomp.getText());
		sv1.add(tftype.getText());
		sv1.add(tfQua.getText());
		
		bill+= Double.parseDouble(tfrate.getText()) * Integer.parseInt(tfQua.getText());
		lblBill.setText("Bill :"+bill);
		vv1.add(new Vector<String>(sv1));
		
	}

	protected void getDetails(String selectedValue) {
		try
		{
		
			ResultSet rs=s.executeQuery("select sid,ord_date from placeorder where orderno="+selectedValue);
			rs.next();
			tfOrder.setText(selectedValue);
			tfSupp.setText(rs.getString(1));
			tfdate.setText(rs.getString(2));
			
			rs=s.executeQuery("select pid,pname,prate,name,ptype,minlimit,quantity from placeorder natural join prod_ordered natural join product1 natural join company1 where orderno="+selectedValue);
			vv=new Vector<Vector<String>>();
			sv=new Vector<String>();
			while(rs.next()){
				for(int i=1;i<=7;i++){
					sv.add(rs.getString(i));
				}
				vv.add(new Vector<String>(sv));
				sv.clear();
			}
			sv.add("ID");
			sv.add("Name");
			sv.add("Rate");
			sv.add("Company");
			sv.add("Type");
			sv.add("MinLimit");
			sv.add("Ordered");
			
			tableOrd = new JTable(vv,sv);
			tableOrd.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
				
				@Override
				public void valueChanged(ListSelectionEvent e) {
					
					updateTbl();
				}
			});
			sp_prod.setViewportView(tableOrd);
		}
		catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "error " + e1.getMessage(),
					"error", JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
		}
		
	}

	protected void updateTbl() {
		int i = tableOrd.getSelectionModel().getLeadSelectionIndex();
		tfid.setText("" + vv.get(i).get(0));
		tfnm.setText("" + vv.get(i).get(1));
		tfrate.setText("" + vv.get(i).get(2));
		tfcomp.setText("" + vv.get(i).get(3));
		tftype.setText("" + vv.get(i).get(4));
		int u=Integer.parseInt(""+vv.get(i).get(6));
		tfQua.setText(""+u);
	
	}

	private Vector<String> getListOfOrders() {
		
		Vector<String> v=new Vector<String>();
		try
		{
			ResultSet rs=s.executeQuery("select orderno from placeorder where isReceived='no'");
			
			while(rs.next()){
				v.add(rs.getString(1));
			}
		
			
		}
		catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "error " + e1.getMessage(),
					"error", JOptionPane.ERROR_MESSAGE);
		}
		return v;
		
	}
}
