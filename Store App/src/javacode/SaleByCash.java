package javacode;


import java.awt.EventQueue;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import java.awt.SystemColor;
import java.awt.FlowLayout;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import javax.swing.border.BevelBorder;

public class SaleByCash extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JFrame f;
	protected static SaleByCash sale;
	protected JTextArea tfId;
	protected JTextField tfRate;
	protected JTextField tfQua;
	protected JTextField tfPrice;
	protected JTable table;
	protected JTextField tfTot;
	protected JTextField tfDisc;
	protected JTextField tfNetTot;
	protected JTextField tfName;
	protected Statement s;
	protected int billno;
	protected JLabel lblBillNo;
	protected Vector<String> col;
	protected Vector<Vector<String>> data;
	protected double bill;
	protected JScrollPane sp_prod;
	protected Vector<Vector<String>> v_chk;
	protected JTextField tfFindNm1;
	protected JTextField tfBarcode;
	QRReader pnlbarcode;
	protected JPanel pnlBill;
	protected JLabel lblDiscount;
	protected JLabel lblNetTotal;
	protected JButton btnSave;
	protected ActionListener action;

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

	/**
	 * Create the application.
	 */
	public static void create() {
		f = new JFrame("Sales To NonRegistered Customer");

		f.setIconImage(new ImageIcon(Main1.class.getResource("/image/sale.png")).getImage());
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getDefaultScreenDevice();
		int w = gd.getDisplayMode().getWidth();
		int h = gd.getDisplayMode().getHeight();
		
		f.setSize(900,600);
		
		f.setLocation(w/2-400, h/2-300);
		sale=new SaleByCash();
		f.getContentPane().add(sale);
		f.addWindowFocusListener(new WindowAdapter() {
		    public void windowGainedFocus(WindowEvent e) {
		  
		    	sale.pnlbarcode.initDSCapture();
		    	
		    }
		    public void windowLostFocus(WindowEvent e){
		    	sale.pnlbarcode.getGraph().dispose();
		    	sale.pnlbarcode.msg.setVisible(true);
		    }
		    
		});
		/*if(f.isFocused())
			sale.test.setVisible(true);
		else
			sale.test.setVisible(false);
		*/
		f.setVisible(true);
	}
	public SaleByCash() {
		
		setLayout(null);
		setBackground(new Color(166,71,255));
		s=ConnectToDataBase.getS();
		billno=0;
		
		col=new Vector<String>();
		col.add("ID");
		col.add("Product Name");
		col.add("Rate");
		col.add("Quantity");
		col.add("Price");
		data=new Vector<Vector<String>>();
		
		lblBillNo = new JLabel("Bill No :");
		lblBillNo.setFocusable(false);
		lblBillNo.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		lblBillNo.setBounds(16, 185, 173, 16);
		calc();
		JPanel pnlId = new JPanel();
		pnlId.setBackground(Color.WHITE);
		pnlId.setBounds(31, 123, 253, 50);
		add(pnlId);
		
		JLabel lblProductId = new JLabel("Product ID :");
		lblProductId.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
		pnlId.add(lblProductId);
		
		tfId = new JTextArea();
		pnlId.add(tfId);
		tfId.setColumns(10);
		
		addListMapTotfId();
		
		JPanel pnlDet = new JPanel();
		pnlDet.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		pnlDet.setBackground(SystemColor.menu);
		pnlDet.setBounds(31, 197, 800, 50);
		add(pnlDet);
		pnlDet.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblName = new JLabel("Name :");
		lblName.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		pnlDet.add(lblName);
		
		tfName = new JTextField();
		tfName.setFocusable(false);
		tfName.setEditable(false);
		pnlDet.add(tfName);
		tfName.setColumns(10);
		
		JLabel lblQuantity = new JLabel("Rate  :");
		lblQuantity.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		pnlDet.add(lblQuantity);
		
		tfRate = new JTextField();
		tfRate.setEditable(false);
		pnlDet.add(tfRate);
		tfRate.setColumns(10);
		
		JLabel lblQuantity_1 = new JLabel("Quantity :");
		lblQuantity_1.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		pnlDet.add(lblQuantity_1);
		
		tfQua = new JTextField();
		tfQua.setColumns(10);
		addListMapTotfQua();

		pnlDet.add(tfQua);
		
		JLabel lblPrice = new JLabel("Price  :");
		lblPrice.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		pnlDet.add(lblPrice);
		
		tfPrice = new JTextField();
		tfPrice.setEditable(false);
		tfPrice.setFocusable(false);
		tfPrice.setColumns(10);
		pnlDet.add(tfPrice);
		
		sp_prod = new JScrollPane();
		
		sp_prod.setBackground(Color.WHITE);
		sp_prod.setBounds(31, 270, 462, 226);
		add(sp_prod);
		
		table = new JTable(data,col);
		addMapToTable();
		sp_prod.setViewportView(table);
		
		pnlBill = new JPanel();
		pnlBill.setBorder(new TitledBorder(null, "Bill", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlBill.setBackground(SystemColor.text);
		pnlBill.setBounds(515, 270, 322, 225);
		add(pnlBill);
		pnlBill.setLayout(null);
		
		JLabel lblTotal = new JLabel("Total :");
		lblTotal.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		lblTotal.setBounds(45, 27, 55, 16);
		pnlBill.add(lblTotal);
		
		tfTot = new JTextField();
		tfTot.setFocusable(false);
		tfTot.setEditable(false);
		tfTot.setBounds(102, 22, 179, 28);
		pnlBill.add(tfTot);
		tfTot.setColumns(10);
		
		lblDiscount = new JLabel("Discount  :");
		lblDiscount.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		lblDiscount.setBounds(16, 76, 87, 16);
		pnlBill.add(lblDiscount);
		
		tfDisc = new JTextField();
		tfDisc.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Double dis=Double.parseDouble(tfDisc.getText())/100;
				bill=bill-bill*dis;
				tfNetTot.setText(""+bill);
			}
		});
		tfDisc.setBounds(102, 71, 179, 28);
		pnlBill.add(tfDisc);
		tfDisc.setColumns(10);
		
		lblNetTotal = new JLabel("Net Total  :");
		lblNetTotal.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		lblNetTotal.setBounds(16, 133, 87, 16);
		pnlBill.add(lblNetTotal);
		
		tfNetTot = new JTextField();
		tfNetTot.setFocusable(false);
		tfNetTot.setEditable(false);
		tfNetTot.setBounds(102, 128, 179, 28);
		pnlBill.add(tfNetTot);
		tfNetTot.setColumns(10);
		
		btnSave = new JButton("Save");
		btnSave.setMnemonic('s');
		
		action=new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveAll();
			}
		};
		btnSave.addActionListener(action);
		btnSave.setBounds(191, 180, 90, 28);
		pnlBill.add(btnSave);
		
		pnlBill.add(lblBillNo);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(SaleByCash.class.getResource("/images/logo3.png")));
		lblNewLabel.setBounds(40, 30, 187, 70);
		add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Search By Name", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBackground(Color.WHITE);
		panel.setBounds(594, 18, 237, 70);
		add(panel);
		panel.setLayout(null);
		
		List<String> l=getProdNames();
		
		tfFindNm1 = new JTextField();
		tfFindNm1.setBounds(16, 17, 200, 30);
		AutoCompleteDecorator.decorate(tfFindNm1,l,false);
		panel.add(tfFindNm1);
		
		pnlbarcode = new QRReader(null,null,this);
		pnlbarcode.setLayout(null);
		pnlbarcode.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		pnlbarcode.setBounds(296, 16, 295, 158);
		add(pnlbarcode);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(null, "Search By Barcode", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(59, 59, 59)));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(594, 100, 237, 70);
		add(panel_1);
		
		tfBarcode = new JTextField();
		tfBarcode.setBounds(17, 22, 200, 30);
		panel_1.add(tfBarcode);
		tfBarcode.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				tfId.setText(getIDfromBarcode());	
			}
		});
		tfFindNm1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				setID();
			}
		});
		
		
		/*JList list=new JList(getProdNames());
		list.setBounds(595, 110, 207, 30);
		
		tfFindNm1 = new JTextField();
		tfFindNm1.setBounds(595, 80, 207, 30);
		
		AutoCompleteDecorator.decorate(list,tfFindNm1,new ObjectToStringConverter());
		add(tfFindNm1);*/
	}


	public JTextField getTfBarcode() {
		return tfBarcode;
	}

	public void setTfBarcode(JTextField tfBarcode) {
		this.tfBarcode = tfBarcode;
	}

	protected void setID() {
		
		String ID=getID(tfFindNm1.getText());
		tfId.setText(ID);
		tfId.requestFocus();
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

	private Vector<String> getProdNames() {
		
		Vector<String> names=new Vector<String>();
		try{
			ResultSet rs=s.executeQuery("select distinct pname from product1");
			while(rs.next()){
				
				names.add(rs.getString(1));
			}
		
		}catch(Exception e){}
		return names;
	}
	private String getID(String name) {
		
		
		String ID="1";
		try{
			ResultSet rs=s.executeQuery("select pid from product1 where pname='"+name+"'");
			while(rs.next()){
				
				ID=rs.getString(1);
			}
		
		}catch(Exception e){}
		return ID;
	}

	protected void addMapToTable() {
		
		table.setFillsViewportHeight(true);
		table.getInputMap().put(KeyStroke.getKeyStroke("TAB"), "action");
		table.getActionMap().put("action", new AbstractAction() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				tfDisc.requestFocus();
				
			}
		});
	}

	protected void saveAll() {
		try {
			s.execute("insert into sale1 values(" + billno
					+ ",0," + bill+ ",sysdate,"+tfDisc.getText()+",0)");
			for (int i = 0; i < data.size(); i++) {

				s.execute("insert into cust_purch values("
						+ data.get(i).get(0) + "," + billno
						+ "," + data.get(i).get(3) + ")");

			}
			int res=JOptionPane.showConfirmDialog(null, "Are You Sure?");
			if(!(res==1||res==2))
			JOptionPane.showMessageDialog(null, "Saved Successfully");

			f.setVisible(false);
			SaleByCash.main(null);
		} catch (Exception e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null,
					"error " + e1.getMessage(), "error",
					JOptionPane.ERROR_MESSAGE);
		}
		
	}

	private void addListMapTotfId() {
		
		tfId.addCaretListener(new CaretListener() {
					
					@Override
					public void caretUpdate(CaretEvent e) {
						
						String id=tfId.getText();
		
						if(id!=null&&id.length()>0){
							showDetails();
						}
						else {
							clear();
						}
					}
		});
		tfId.getInputMap().put(KeyStroke.getKeyStroke("TAB"), "action");
		tfId.getActionMap().put("action", new AbstractAction() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				tfQua.requestFocus();
				
			}
		});
		tfId.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "action1");
		tfId.getActionMap().put("action1", new AbstractAction() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				tfQua.requestFocus();
				
			}
		});
		
	}

	protected void addListMapTotfQua() {
		tfQua.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				check();
				
			}
		});
		
	}

	protected void check() {
		try
		{
			ResultSet rs=s.executeQuery("select avail from Available1 where pid="+tfId.getText());
			rs.next();
			if(Integer.parseInt(rs.getString(1))<Integer.parseInt(tfQua.getText())){
				JOptionPane.showMessageDialog(null, "Insufficient quantity in Stock:Available "+rs.getString(1),
						"error", JOptionPane.WARNING_MESSAGE);
				
			}
			else{
				
				Double rate=Double.parseDouble(tfRate.getText());
				Double subtot=Integer.parseInt(tfQua.getText())*rate;
				tfPrice.setText(""+subtot);
				bill+=subtot;
				tfTot.setText(""+bill);
				
				Vector<String> v=new Vector<String>();
				v.add(tfId.getText());
				v.add(tfName.getText());
				v.add(tfRate.getText());
				
				
				String qua=tfQua.getText();
				v_chk=new Vector<Vector<String>>(data);
				for(int i=0;i<data.size();i++){
					if(v.get(0).equals(data.get(i).get(0))){
						
						qua=""+(Integer.parseInt(qua)+Integer.parseInt(data.get(i).get(3)));
						v_chk.remove(i);
						break;
					}
			
				}
				data=v_chk;
				
				v.add(qua);
				v.add(tfPrice.getText());
				data.add(v);
				
				table=new JTable(data,col);
				addMapToTable();
				sp_prod.setViewportView(table);
				
			}
			tfQua.setText("0");
			tfId.requestFocus();
			
		}catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, "error " + e.getMessage(),
					"error", JOptionPane.ERROR_MESSAGE);
			clear();
		}
		
	}

	protected void showDetails() {
			
		try {
				
				s.execute("select pname,prate from product1 where pid="+tfId.getText());
				ResultSet rs = s.getResultSet();
				rs.next();
				tfName.setText(rs.getString(1));
				tfRate.setText(rs.getString(2));
			} 
			catch (Exception e) {
		
				JOptionPane.showMessageDialog(null, "error " + e.getMessage(),
						"error", JOptionPane.ERROR_MESSAGE);
				clear();
			}
		
	}
	private void calc() {
		
		
		try {

			String st = "SELECT max(billno) FROM sale1";
			s.execute(st);
			ResultSet rs = s.getResultSet();

			while (rs.next()) {
				String d = rs.getString(1);
				billno = Integer.parseInt(d);

			}
			billno += 1;
			lblBillNo.setText("Bill No : "+billno);
		} catch (Exception e) {
		
			JOptionPane.showMessageDialog(null, "error " + e.getMessage(),
					"error", JOptionPane.ERROR_MESSAGE);
		}
		
	}

	protected void clear() {
		
		try{
			
			tfDisc.setText("");
			tfId.setText("");
			tfName.setText("");
			tfPrice.setText("");
			tfQua.setText("");
			tfRate.setText("");
			tfTot.setText("");
		}catch(Exception e){}
		
	}
}
