package javacode;

import java.awt.EventQueue;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.border.TitledBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.Color;

import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

public class Purchase3 extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int prod,cust;
	private double bill;
	private static JFrame frame;
	private JTable supdettbl;
	private JTable proddettbl;
	private JScrollPane sp_cust;
	private JTextField tfsid;
	private JTextField tfprodid;
	private JTextField tfqua;
	private JTable saleprodtbl;
	private JScrollPane sp_prod_sale;
	private JTextArea tfsearchc;
	private JTextArea tfsearchp;
	private JTextField tfbill;
	private JTextField tfbillno;
	private JLabel label;

	private Statement s;
	private int grnno;
	private Vector<Vector<String>> v_supp;
	private Vector<String> sv;
	private Vector<Vector<String>> v_prodd,v_prods;
	private Vector<String> sv1,sv2;
	private Vector<Vector<String>> vsearch;
	private Vector<Vector<String>> vsearchp;
	private Vector<Vector<String>> v_chk;
	private JScrollPane sp_prod_det;

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
	
		frame=new JFrame("Add Purchase Details if Order is not given");
		frame.setIconImage(new ImageIcon(Main1.class.getResource("/image/Stock And Purchase.png")).getImage());
		frame.getContentPane().add(new Purchase3());
		frame.setBounds(100, 100, 1200, 582);
		frame.setVisible(true);
	}

	/**
	 * Create the application.
	 */
	public Purchase3() {
		
		s=ConnectToDataBase.getS();
		getInputMap().put(KeyStroke.getKeyStroke("ESCAPE"),"action");
		getActionMap().put("action", new AbstractAction() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				
			}
		});
		
		setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(153, 102, 255), new Color(153, 0, 153), new Color(153, 0, 153), new Color(153, 0, 51)));
		setLayout(null);
		bill=0;
		v_prods=new Vector<Vector<String>>();
		setBackground(new Color(216,133,255));
		setBounds(0, 0, 1200, 582);
		sp_cust = new JScrollPane();
		sp_cust.setBorder(new TitledBorder(null, "Supplier Details", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
		sp_cust.setBounds(35, 119, 292, 323);
		add(sp_cust);
		
		
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Bill", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(366, 454, 428, 68);
		add(panel_3);
		
		JLabel lblBillNo = new JLabel("GRN No :");
		panel_3.add(lblBillNo);
		
		tfbillno = new JTextField();
		calc();
		panel_3.add(tfbillno);
		tfbillno.setColumns(5);
		
		JLabel lblTotalBill = new JLabel("Total Bill :");
		panel_3.add(lblTotalBill);
		
		tfbill = new JTextField();
		panel_3.add(tfbill);
		tfbill.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					s.execute("insert into purchase1 values(" + tfbillno.getText()
							+ "," + tfsid.getText() + "," + bill
							+ ",sysdate,0)");
					for (int i = 0; i < v_prods.size(); i++) {

						s.execute("insert into prod_purch values("
								+ v_prods.get(i).get(0) + "," +  tfbillno.getText()
								+ "," + v_prods.get(i).get(2) + ")");

					}
					int res=JOptionPane.showConfirmDialog(null, "Are You Sure?");
					if(!(res==1||res==2))
					JOptionPane.showMessageDialog(null, "Saved Successfully");
					
					Date d=Calendar.getInstance().getTime();
					Main4.getMonthView().addFlaggedDates(d);
					frame.setVisible(false);
					Purchase3.main(null);
				} catch (Exception e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null,
							"error " + e1.getMessage(), "error",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		panel_3.add(btnSave);
		
		supdettbl = suppDetails();
		supdettbl.setAutoCreateRowSorter(true);
		supdettbl.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				
				cust = supdettbl.getSelectionModel().getLeadSelectionIndex();
				tfsid.setText(v_supp.get(cust).get(0));
			
				if(tfprodid.getText().length()>0){
					tfqua.setEnabled(true);
				}
			
			}
		});
		sp_cust.setViewportView(supdettbl);
		
		sp_prod_det = new JScrollPane();
		sp_prod_det.setBorder(new TitledBorder(null, "Product Details", TitledBorder.LEADING, TitledBorder.TOP, null, Color.white));
		sp_prod_det.setBounds(840, 119, 304, 337);
		add(sp_prod_det);
		
		JPanel panel = new JPanel();
		panel.setBorder(UIManager.getBorder("TitledBorder.border"));
		panel.setBackground(Color.WHITE);
		panel.setBounds(507, 44, 637, 63);
		add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblCustomerId = new JLabel("Supplier ID :");
		panel.add(lblCustomerId);
		
		tfsid = new JTextField();
		tfsid.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				UdateProddetTbl();
			}
		});
		panel.add(tfsid);
		tfsid.setColumns(10);
		
		UdateProddetTbl();
		
		
		JLabel lblProductId = new JLabel("Product ID :");
		panel.add(lblProductId);
		
		tfprodid = new JTextField();
		tfprodid.setEditable(false);
		panel.add(tfprodid);
		tfprodid.setColumns(10);
		
		JLabel lblQuantity = new JLabel("Quantity :");
		panel.add(lblQuantity);
		
		tfqua = new JTextField();
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
		tfqua.setEditable(false);
		panel.add(tfqua);
		tfqua.setColumns(10);
		
		sp_prod_sale = new JScrollPane();
		sp_prod_sale.setBorder(new TitledBorder(null, "Products To Purchase", TitledBorder.LEADING, TitledBorder.TOP, null, Color.white));
		sp_prod_sale.setBounds(356, 119, 449, 323);
		add(sp_prod_sale);
		
		saleprodtbl = new JTable();

		sp_prod_sale.setViewportView(saleprodtbl);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Search By Supplier Name", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(59, 59, 59)));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(45, 454, 282, 63);
		add(panel_1);
		
		tfsearchc = new JTextArea();
		tfsearchc.addCaretListener(new CaretListener() {
			
			@Override
			public void caretUpdate(CaretEvent e) {
				
				
				supdettbl=custFind();
				supdettbl.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
					
					@Override
					public void valueChanged(ListSelectionEvent e) {
						
						cust = supdettbl.getSelectionModel().getLeadSelectionIndex();
						tfsid.setText(v_supp.get(cust).get(0));
					
						if(tfprodid.getText().length()>0){
							tfqua.setEnabled(true);
						}
					
					}
				});
		
				sp_cust.setViewportView(supdettbl);
				
			}
		});
		
		
		panel_1.add(tfsearchc);
		tfsearchc.setColumns(18);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Search By Product Name", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(850, 465, 292, 63);
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
						if(tfsid.getText().length()>0){
							tfqua.setEditable(true);
						}
					}
				});
				sp_prod_det.setViewportView(proddettbl);
				
			}
		});
		
		
		label = new JLabel("");
		label.setIcon(new ImageIcon(Purchase3.class.getResource("/images/logo3.png")));
		label.setBounds(35, 21, 170, 70);
		add(label);
		
		JButton btnAddProducts = new JButton("Add Products");
		btnAddProducts.setMnemonic('a');
		btnAddProducts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String sid=tfsid.getText();
				if(sid!=null&&sid.length()>0){
					SupplierProduct.create(sid);
				}
				else
					JOptionPane.showMessageDialog(null, "Please Enter Supplier ID", "Error", JOptionPane.WARNING_MESSAGE);;
			}
		});
		btnAddProducts.setBounds(312, 56, 131, 35);
	
		
		add(btnAddProducts);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Purchase3.main(null);
			}
		});
		btnNewButton.setBackground(new Color(154, 205, 50));
		btnNewButton.setIcon(new ImageIcon(Purchase3.class.getResource("/images/refresh.png")));
		btnNewButton.setBounds(450, 50, 45, 45);
		add(btnNewButton);
		
		/*label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(Purchase3.class.getResource("/images/s.png")));
		label_1.setBounds(295, 44, 183, 93);
		add(label_1);*/
		
	}

	public void UdateProddetTbl() {
		proddettbl = prodDetails();
		proddettbl.setAutoCreateRowSorter(true);
		sp_prod_det.setViewportView(proddettbl);
		proddettbl.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
					
					

					@Override
					public void valueChanged(ListSelectionEvent e) {
						
						prod = proddettbl.getSelectionModel().getLeadSelectionIndex();
						tfprodid.setText(v_prodd.get(prod).get(0));
						//System.out.println(""+v_prodd.get(prod).get(0));
						if(tfsid.getText().length()>0){
							tfqua.setEditable(true);
						}
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
		saleprodtbl.setAutoCreateRowSorter(true);
		sp_prod_sale.setViewportView(saleprodtbl);
	}

	private JTable suppDetails() {
		JTable j=new JTable();
		try
		{
			v_supp=new Vector<Vector<String>>();
			sv=new Vector<String>();
			
			ResultSet rs=s.executeQuery("select sid,sname,saddr,smobno from supplier");
			while(rs.next()){
				for(int i=1;i<=4;i++){
					sv.add(rs.getString(i));
				}
				v_supp.add(new Vector<String>(sv));
				sv.clear();
			}
			sv.add("ID");
			sv.add("Name");
			sv.add("Address");
			sv.add("Mobile No");
			j=new JTable(v_supp,sv);
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
			for(int i=0;i<v_supp.size();i++){
				String nm=v_supp.get(i).get(1);
				if(nm.contains(tfsearchc.getText())){
					vsearch.add(v_supp.get(i));
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
			
			String sql="select pid,pname,prate,avail from product1 natural join available1";
			String sid=tfsid.getText();
			if(sid!=null&&sid.length()>0){
				sql+=" where pid in (select pid from SupplierProducts where sid="+sid+")";
			}
			ResultSet rs=s.executeQuery(sql);
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
		
			String st = "SELECT grnno FROM purchase1";
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
