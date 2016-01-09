package javacode;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.Color;

import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.ImageIcon;

public class Sale1 extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int prod,cust;
	private double bill;
	private static JFrame frame;
	private static Sale1 sale;
	private JTable custdettbl;
	private JTable proddettbl;
	private JScrollPane sp_cust;
	private JTextField tfcustid;
	private JTextField tfprodid;
	private JTextField tfqua;
	private JTable saleprodtbl;
	private JScrollPane sp_prod_sale;
	private JTextArea tfsearchc;
	private JTextArea tfsearchp;
	private JTextField tfbill;
	private Statement s;
	private int grnno;
	private Vector<Vector<String>> v_cust;
	private Vector<String> sv;
	private Vector<Vector<String>> v_prodd,v_prods;
	private Vector<String> sv1,sv2;
	private Vector<Vector<String>> vsearch;
	private Vector<Vector<String>> vsearchp;
	private Vector<Vector<String>> v_chk;
	private JLabel lblDiscount;
	private JTextField tfDisc;
	private JLabel lblCashPaid;
	private JTextField tfNetBal;
	private JLabel lblBalance;
	private JTextField tfCash;
	private JLabel lblBalance_1;
	private JTextField tfBal;
	private JLabel label_2;
	private JTextField tfbillno;
	private JPanel pnlBill;
	private JTextField tfBarcode;
	private QRReader barcodepnl;


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

	protected static void create() {
		frame=new JFrame("Sale To Registered Customers");
		frame.setIconImage(new ImageIcon(Main1.class.getResource("/image/sale.png")).getImage());
		sale=new Sale1();
		frame.getContentPane().add(sale);
		frame.setBounds(100, 10, 1200, 700);
		frame.addWindowFocusListener(new WindowAdapter() {
		    public void windowGainedFocus(WindowEvent e) {
		  
		    	sale.barcodepnl.initDSCapture();
		    	
		    }
		    public void windowLostFocus(WindowEvent e){
		    	sale.barcodepnl.getGraph().dispose();
		    	sale.barcodepnl.msg.setVisible(true);
		    }
		    
		});
		frame.setVisible(true);
	}

	/**
	 * Create the application.
	 */
	public Sale1() {
		setLayout(null);
		
		s=ConnectToDataBase.getS();
		
		bill=0;
		v_prods=new Vector<Vector<String>>();
		setBackground(new Color(216,133,255));
		setBounds(0, 0, 1200, 650);
		sp_cust = new JScrollPane();
		sp_cust.setBorder(new TitledBorder(null, "Customer Details", TitledBorder.LEADING, TitledBorder.TOP, null, Color.white));
		sp_cust.setBounds(35, 119, 292, 432);
		add(sp_cust);
		
		pnlBill = new JPanel();
		pnlBill.setBorder(new TitledBorder(null, "Bill", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlBill.setBackground(Color.WHITE);
		pnlBill.setBounds(366, 454, 428, 176);
		add(pnlBill);
		pnlBill.setLayout(null);
		
		
		JLabel lblTotalBill = new JLabel("Total Bill :");
		lblTotalBill.setBounds(22, 28, 53, 16);
		pnlBill.add(lblTotalBill);
		
		tfbill = new JTextField();
		tfbill.setBounds(88, 22, 177, 28);
		pnlBill.add(tfbill);
		tfbill.setColumns(15);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(315, 122, 92, 35);
		btnSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int res=JOptionPane.showConfirmDialog(null, "Are You Sure?");
				if(!(res==1||res==2))
				{
					try {
						s.execute("insert into sale1 values(" + tfbillno.getText()
								+ "," + tfcustid.getText() + "," + bill
								+ ",sysdate,"+tfDisc.getText()+","+bill+")");
						s.execute("insert into CustomerDebit values("+CustomerPurchase.calc()+","+tfbillno.getText()+","+tfCash.getText()+",sysdate)");
						for (int i = 0; i < v_prods.size(); i++) {
	
							s.execute("insert into cust_purch values("
									+ v_prods.get(i).get(0) + "," +  tfbillno.getText()
									+ "," + v_prods.get(i).get(2) + ")");
	
						}
						
						JOptionPane.showMessageDialog(null, "Saved Successfully");
						frame.setVisible(false);
						Sale1.main(null);
					} catch (Exception e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null,
								"error " + e1.getMessage(), "error",
								JOptionPane.ERROR_MESSAGE);
					}
				}

			}
		});
		
		lblDiscount = new JLabel("Discount  :");
		lblDiscount.setBounds(277, 28, 58, 16);
		pnlBill.add(lblDiscount);
		
		tfDisc = new JTextField();
		tfDisc.setBounds(340, 22, 67, 28);
		tfDisc.setColumns(5);
		addMapAndListener();
	
		pnlBill.add(tfDisc);
		
		lblCashPaid = new JLabel("Net Total  :");
		lblCashPaid.setBounds(16, 62, 67, 16);
		pnlBill.add(lblCashPaid);
		
		tfNetBal = new JTextField();
		tfNetBal.setBounds(88, 56, 177, 28);
		tfNetBal.setColumns(15);
		pnlBill.add(tfNetBal);
		
		lblBalance = new JLabel("Cash :");
		lblBalance.setBounds(43, 97, 43, 16);
		pnlBill.add(lblBalance);
		
		tfCash = new JTextField();
		tfCash.setBounds(88, 91, 177, 28);
		tfCash.setColumns(15);
		addMapAndListenerTotfCash();
		
		pnlBill.add(tfCash);
		pnlBill.add(btnSave);
		
		lblBalance_1 = new JLabel("Balance  :");
		lblBalance_1.setBounds(28, 131, 58, 16);
		pnlBill.add(lblBalance_1);
		
		tfBal = new JTextField();
		tfBal.setEditable(false);
		tfBal.setColumns(5);
		tfBal.setBounds(88, 125, 174, 28);
		pnlBill.add(tfBal);
		
		label_2 = new JLabel("Bill No :");
		label_2.setBounds(293, 62, 42, 16);
		pnlBill.add(label_2);
		
		tfbillno = new JTextField();
		tfbillno.setFocusable(false);
		tfbillno.setEditable(false);
		tfbillno.setColumns(5);
		tfbillno.setBounds(340, 56, 67, 28);
		pnlBill.add(tfbillno);
		calc();
		
		custdettbl = custDetails();
		custdettbl.setAutoCreateRowSorter(true);
		custdettbl.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				
				cust = custdettbl.getSelectionModel().getLeadSelectionIndex();
				tfcustid.setText(v_cust.get(cust).get(0));
			
				if(tfprodid.getText().length()>0){
					tfqua.setEnabled(true);
				}
			
			}
		});
		sp_cust.setViewportView(custdettbl);
		
		final JScrollPane sp_prod_det = new JScrollPane();
		sp_prod_det.setBorder(new TitledBorder(null, "Product Details", TitledBorder.LEADING, TitledBorder.TOP, null, Color.white));
		sp_prod_det.setBounds(817, 228, 327, 323);
		add(sp_prod_det);
		
		proddettbl = prodDetails();
		proddettbl.setAutoCreateRowSorter(true);
		sp_prod_det.setViewportView(proddettbl);
		proddettbl.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
					
					

					@Override
					public void valueChanged(ListSelectionEvent e) {
						
						prod = proddettbl.getSelectionModel().getLeadSelectionIndex();
						tfprodid.setText(v_prodd.get(prod).get(0));
						tfBarcode.setText("");
						//System.out.println(""+v_prodd.get(prod).get(0));
						if(tfcustid.getText().length()>0){
							tfqua.setEditable(true);
						}
					}
				});
		
		JPanel panel = new JPanel();
		panel.setBorder(UIManager.getBorder("TitledBorder.border"));
		panel.setBackground(Color.WHITE);
		panel.setBounds(35, 44, 565, 63);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblCustomerId = new JLabel("Customer ID :");
		lblCustomerId.setBounds(23, 21, 76, 16);
		panel.add(lblCustomerId);
		
		tfcustid = new JTextField();
		tfcustid.setBounds(111, 15, 76, 28);
		tfcustid.setEditable(false);
		panel.add(tfcustid);
		tfcustid.setColumns(10);
		
		JLabel lblProductId = new JLabel("Product ID :");
		lblProductId.setBounds(199, 21, 63, 16);
		panel.add(lblProductId);
		
		tfprodid = new JTextField();
		tfprodid.setBounds(262, 15, 105, 28);
		tfprodid.setEditable(false);
		panel.add(tfprodid);
		tfprodid.setColumns(10);
		
		JLabel lblQuantity = new JLabel("Quantity :");
		lblQuantity.setBounds(370, 21, 50, 16);
		panel.add(lblQuantity);
		
		tfqua = new JTextField();
		tfqua.setBounds(432, 15, 122, 28);
		tfqua.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
					if(Integer.parseInt(tfqua.getText())<=Integer.parseInt(v_prodd.get(prod).get(3))){
					addInList();
					}
					else{
						JOptionPane.showMessageDialog(null, "Insufficient quantity in Stock", "error",JOptionPane.WARNING_MESSAGE);
					}
					tfqua.setText("");
			}
		});
		panel.add(tfqua);
		tfqua.setColumns(10);
		
		sp_prod_sale = new JScrollPane();
		sp_prod_sale.setBorder(new TitledBorder(null, "Products To Sale", TitledBorder.LEADING, TitledBorder.TOP, null, Color.white));
		sp_prod_sale.setBounds(356, 119, 449, 323);
		add(sp_prod_sale);
		
		saleprodtbl = new JTable();

		sp_prod_sale.setViewportView(saleprodtbl);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Search By Customer Name", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(59, 59, 59)));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(45, 567, 282, 63);
		add(panel_1);
		
		tfsearchc = new JTextArea();
		tfsearchc.addCaretListener(new CaretListener() {
			
			@Override
			public void caretUpdate(CaretEvent e) {
				
				
				custdettbl=custFind();
				custdettbl.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
					
					@Override
					public void valueChanged(ListSelectionEvent e) {
						
						cust = custdettbl.getSelectionModel().getLeadSelectionIndex();
						tfcustid.setText(v_cust.get(cust).get(0));
					
						if(tfprodid.getText().length()>0){
							tfqua.setEnabled(true);
						}
					
					}
				});
		
				sp_cust.setViewportView(custdettbl);
				
			}
		});
		
		
		panel_1.add(tfsearchc);
		tfsearchc.setColumns(18);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Search By Product Name", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(827, 564, 317, 63);
		add(panel_2);
		
		tfsearchp = new JTextArea();
		panel_2.add(tfsearchp);
		tfsearchp.setColumns(18);
		tfsearchp.addCaretListener(new CaretListener() {
			
			@Override
			public void caretUpdate(CaretEvent e) {
				
				//System.out.println(tfsearchc.getText());
				proddettbl=prodFind();
				proddettbl.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
					
					@Override
						public void valueChanged(ListSelectionEvent e) {
						
						prod = proddettbl.getSelectionModel().getLeadSelectionIndex();
						tfprodid.setText(v_prodd.get(prod).get(0));
						//System.out.println(""+v_prodd.get(prod).get(0));
						if(tfcustid.getText().length()>0){
							tfqua.setEditable(true);
						}
					}
				});
				sp_prod_det.setViewportView(proddettbl);
				
			}
		});
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel_3.setBorder(new TitledBorder(null, "Search By Barcode", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(612, 44, 181, 63);
		add(panel_3);
		panel_3.setLayout(null);
		
		tfBarcode = new JTextField();
		tfBarcode.setBounds(18, 18, 145, 28);
		panel_3.add(tfBarcode);
		tfBarcode.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				tfprodid.setText(getIDfromBarcode());	
			}
		});
		tfBarcode.setColumns(10);
		
		barcodepnl = new QRReader(null,this,null);
		barcodepnl.setBounds(817, 21, 327, 195);
		add(barcodepnl);
		
	}

	public String getIDfromBarcode() {
		
		String st=tfBarcode.getText();
		String res="";
		try
		{
			
			if(st.length()>0){
				ResultSet rs=s.executeQuery("select pid from product1 where barcode='"+st+"'");
				while(rs.next()){
					
					res=rs.getString(1);
					
				}
				
			}
			
		}
		
		catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}

	public JTextField getTfBarcode() {
		return tfBarcode;
	}

	public void setTfBarcode(JTextField tfBarcode) {
		this.tfBarcode = tfBarcode;
	}

	protected void addMapAndListenerTotfCash() {
		tfCash.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
		
				Double cash=Double.parseDouble(tfCash.getText());
				
				Double netbal=Double.parseDouble(tfNetBal.getText());
				if((netbal-cash)>=0)
				tfBal.setText(""+(netbal-cash));
				else
				JOptionPane.showMessageDialog(null, "Balance cant be negative", "error",JOptionPane.WARNING_MESSAGE);
			}
		});
		
	}

	private void addMapAndListener() {
			tfDisc.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Double dis=Double.parseDouble(tfDisc.getText())/100;
				bill=bill-bill*dis;
				tfNetBal.setText(""+bill);
			}
		});
	}

	protected void addInList() {
		
		sv2=new Vector<String>();
		sv2.add(tfprodid.getText());
		sv2.add(v_prodd.get(prod).get(1));
		String qua=tfqua.getText();
		v_chk=new Vector<Vector<String>>(v_prods);
		for(int i=0;i<v_prods.size();i++){
			if(sv2.get(0).equals(v_prods.get(i).get(0))){
				qua=""+(Integer.parseInt(qua)+Integer.parseInt(v_prods.get(i).get(2)));
				v_chk.remove(i);
				break;
			}
	
		}
		v_prods=v_chk;
		
		sv2.add(qua);
		sv2.add(v_prodd.get(prod).get(2));
		double subbill=Double.parseDouble(v_prodd.get(prod).get(2))*Integer.parseInt(tfqua.getText());
		bill+=subbill;
		sv2.add(""+subbill);
		v_prods.add(new Vector<String>(sv2));
		tfbill.setText(""+bill);
		sv2.clear();
		sv2.add("product id");
		sv2.add("product Name");
		sv2.add("quantity");
		sv2.add("Rate");
		sv2.add("Price");
		
		saleprodtbl=new JTable(v_prods,sv2);
		sp_prod_sale.setViewportView(saleprodtbl);
	}

	private JTable custDetails() {
		JTable j=new JTable();
		try
		{
			v_cust=new Vector<Vector<String>>();
			sv=new Vector<String>();
			
			ResultSet rs=s.executeQuery("select id,name,addr,mobno from customr where id <> 0");
			while(rs.next()){
				for(int i=1;i<=4;i++){
					sv.add(rs.getString(i));
				}
				v_cust.add(new Vector<String>(sv));
				sv.clear();
			}
			sv.add("ID");
			sv.add("Name");
			sv.add("Address");
			sv.add("Mobile No");
			j=new JTable(v_cust,sv);
			j.setAutoCreateRowSorter(true);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "error " + e.getMessage(),
					"error", JOptionPane.ERROR_MESSAGE);
		}
		return j;
	}
	private JTable custFind() {
		JTable j=new JTable();
		try
		{
			
			sv=new Vector<String>();
			vsearch = new Vector<Vector<String>>();
			for(int i=0;i<v_cust.size();i++){
				String nm=v_cust.get(i).get(1);
				if(nm.contains(tfsearchc.getText())){
					vsearch.add(v_cust.get(i));
				}
			}

			sv.add("ID");
			sv.add("Name");
			sv.add("Address");
			sv.add("Mobile No");
			j=new JTable(vsearch,sv);
			j.setAutoCreateRowSorter(true);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "error " + e.getMessage(),
					"error", JOptionPane.ERROR_MESSAGE);
		}
		return j;
	}
	private JTable prodFind() {
		JTable j=new JTable();
		try
		{
			
		
			vsearchp = new Vector<Vector<String>>();
			for(int i=0;i<v_prodd.size();i++){
				String nm=v_prodd.get(i).get(1);
				if(nm.contains(tfsearchp.getText())){
					vsearchp.add(v_prodd.get(i));
				}
			}
		
			j=new JTable(vsearchp,sv1);
			j.setAutoCreateRowSorter(true);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "error " + e.getMessage(),
					"error", JOptionPane.ERROR_MESSAGE);
		}
		return j;
	}
	private JTable prodDetails() {
		JTable j=new JTable();
		try
		{
			v_prodd=new Vector<Vector<String>>();
			sv1=new Vector<String>();
			
			ResultSet rs=s.executeQuery("select pid,pname,prate,avail from product1 natural join available1");
			while(rs.next()){
				for(int i=1;i<=4;i++){
					sv1.add(rs.getString(i));
				}
				v_prodd.add(new Vector<String>(sv1));
				sv1.clear();
			}
			sv1.add("ID");
			sv1.add("Name");
			sv1.add("Rate");
			sv1.add("Available");
			//System.out.println(v_prodd);
			j=new JTable(v_prodd,sv1);
			j.setAutoCreateRowSorter(true);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "error " + e.getMessage(),
					"error", JOptionPane.ERROR_MESSAGE);
		}
		return j;
	}

	private void calc() {
		
	
		try {
		

			String st = "SELECT billno FROM sale1";
			s.execute(st);
			ResultSet rs = s.getResultSet();

			int max = 0;
			while (rs.next()) {
				String d = rs.getString(1);
				grnno = Integer.parseInt(d);
				if (grnno > max) {
					max = grnno;
				}
			}
			grnno = max + 1;
			tfbillno.setText(""+grnno);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "error " + e.getMessage(),
					"error", JOptionPane.ERROR_MESSAGE);
		}
		
	}
}
