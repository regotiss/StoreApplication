package javacode;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.border.TitledBorder;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.Vector;

import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableCellRenderer;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextArea;
import javax.swing.JComboBox;


public class CustomerPurchase extends JPanel {


	private static final long serialVersionUID = 1L;
	private static JFrame f;
	private JLabel bak;
	//private JPanel bak;
	private JTextField tfDate;
	private JTable tblCredit;
	private JScrollPane sp_credit;
	private JTextField tfCredit;
	private JTextField tfAmount;
	private JScrollPane sp_debit;
	private JCheckBox cbPaid;
	private JCheckBox cbNotPaid;
	private JTable tblDebit;
	private String ID;
	private static Statement s;
	private Vector<Vector<String>> vv,vv1;
	private Vector<String> v,v1;
	private JTextArea tfBillno;
	private JComboBox<String> comboBox;
	public static void main(String args[]){
		
		ConnectToDataBase.main(null);
		Main1.setNimbus();
		create("5");
	}
	public CustomerPurchase(String ID) {
		
		
		setSize(900,600);
		setLayout(new BorderLayout(0, 0));
		
		this.ID=ID;
		v=new Vector<String>();
		v.add("BillNo");
		v.add("Date");
		v.add("Bill");
		v.add("Balance");
		
		v1=new Vector<String>();
		v1.add("Debit No.");
		v1.add("BillNo");
		v1.add("Date");
		v1.add("Amount");
		
		s=ConnectToDataBase.getS();
		bak = new JLabel(new ImageIcon(CustomerPurchase.class.getResource("/images/back3.png")));
		//bak=new JPanel();
		add(bak, BorderLayout.CENTER);
		bak.setLayout(null);
		
		JPanel pnlBill = new JPanel();
		pnlBill.setBorder(new TitledBorder(null, "Credit Details", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlBill.setBackground(Color.WHITE);
		pnlBill.setPreferredSize(new Dimension(441,67));
		pnlBill.setLayout(null);
		
		JLabel lblBillNo = new JLabel("Bill No :");
		lblBillNo.setBounds(17, 23, 69, 21);
		pnlBill.add(lblBillNo);
		lblBillNo.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 18));
		
		JLabel lblDate = new JLabel("Date :");
		lblDate.setBounds(224, 23, 69, 21);
		pnlBill.add(lblDate);
		lblDate.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 18));
		
		tfDate = new JTextField();
		tfDate.setBounds(281, 21, 140, 28);
		pnlBill.add(tfDate);
		tfDate.setColumns(10);
		
		sp_credit = new JScrollPane();
		sp_credit.setBorder(UIManager.getBorder("TitledBorder.border"));
		sp_credit.setBounds(17, 227, 456, 314);
		bak.add(sp_credit);
		
		cbPaid = new JCheckBox("Paid");
		cbPaid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTable();
			}
		});
	
		cbPaid.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 16));
		cbPaid.setBounds(17, 197, 93, 18);
		bak.add(cbPaid);
		cbPaid.setForeground(Color.WHITE);
		
		cbNotPaid = new JCheckBox("Not Paid");
		cbNotPaid.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 16));
		cbNotPaid.setBounds(132, 197, 104, 18);
		cbNotPaid.setSelected(true);
		cbNotPaid.setForeground(Color.WHITE);
		bak.add(cbNotPaid);
		cbNotPaid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTable();
			}
		});
		
		
		
		
		JLabel lblCredit = new JLabel("Credit :");
		lblCredit.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 18));
		lblCredit.setBounds(25, 556, 69, 21);
		lblCredit.setForeground(Color.WHITE);
		bak.add(lblCredit);
		
		tfCredit = new JTextField();
		tfCredit.setColumns(10);
		tfCredit.setBounds(98, 554, 171, 28);
		bak.add(tfCredit);
		
		setValue();
		
		updateTable();
		
		JPanel pnlDebit = new JPanel();
		pnlDebit.setLayout(null);
		pnlDebit.setBorder(new TitledBorder(null, "Debit", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlDebit.setBackground(Color.WHITE);
		pnlDebit.setBounds(479, 16, 355, 67);
		
		
		tfAmount = new JTextField();
		tfAmount.setColumns(10);
		tfAmount.setBounds(108, 18, 140, 28);
		pnlDebit.add(tfAmount);
		
		JLabel lblAmount = new JLabel("Amount :");
		lblAmount.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 18));
		lblAmount.setBounds(19, 20, 105, 21);
		pnlDebit.add(lblAmount);
		
		JButton btnPay = new JButton("Pay");
		btnPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pay();
			}
		});
		btnPay.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		btnPay.setBounds(257, 18, 81, 28);
		pnlDebit.add(btnPay);
		
		JSplitPane sp=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pnlBill, pnlDebit);
		
		sp_debit = new JScrollPane();
		sp_debit.setBorder(UIManager.getBorder("TitledBorder.border"));
		sp_debit.setBounds(485, 227, 338, 314);
		bak.add(sp_debit);
	
	
		tfBillno = new JTextArea();
		updateDebitTbl();
		tfBillno.addCaretListener(new CaretListener() {
			
			@Override
			public void caretUpdate(CaretEvent e) {
	
				updateDebitTbl();
			}
		});
		tfBillno.setBounds(98, 21, 114, 28);
		pnlBill.add(tfBillno);
		
		updateDebitTbl();
		
		sp.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		sp.setBounds(17, 103, 800, 69);
		bak.add(sp);
		
		JButton btnShowDetails = new JButton("Show Details");
		btnShowDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				showDetails();
			}
		});
		btnShowDetails.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
		btnShowDetails.setBounds(261, 187, 126, 28);
		bak.add(btnShowDetails);
		

		comboBox = new JComboBox<String>(new String[] {"Credit Note", "Debit Note"});
		comboBox.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
		comboBox.setBounds(485, 554, 144, 26);
		bak.add(comboBox);
		
		JButton btnPrint = new JButton("Print");
		btnPrint.setIcon(new ImageIcon(CustomerPurchase.class.getResource("/images/print.PNG")));
		btnPrint.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
		btnPrint.setBounds(654, 554, 113, 28);
		btnPrint.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String item=""+comboBox.getSelectedItem();
				JTable j;
				if(item.equals("Credit Note")){
					j=tblCredit;
				}
				else
				{
					j=tblDebit;
				}
				try
				{
					MessageFormat header=new MessageFormat("Report Print");
					MessageFormat footer=new MessageFormat("Page{0,number,integer}");
					j.print(JTable.PrintMode.NORMAL,header,footer);
				}catch(Exception e1){
					JOptionPane.showMessageDialog(null,
							"error " + e1.getMessage(), "error",
							JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		bak.add(btnPrint);
		
		
	}
	public void showDetails() {
		try
		{
			if(tfBillno.getText().length()>0)
			ProductDetails.create(tfBillno.getText(),"bill");
			else
				JOptionPane.showMessageDialog(null, "Choose Bill No", "error", JOptionPane.WARNING_MESSAGE);
		}
		catch(Exception e){}
		
	}
	public void setValue() {
		double amount=0;
		
		try
		{
			s.execute("select balance from sale1 where ID="+ID);
			ResultSet rs=s.getResultSet();
			while(rs.next()){
				amount+=Double.parseDouble(rs.getString(1));
			}
		}
		catch(Exception e){}
		
		tfCredit.setText(""+amount);
		
		
	}
	public void pay() {
		
		try 
		{
			String amo=tfAmount.getText();
			if(amo!=null&&amo.length()>0 && tfBillno.getText().length()>0){
				
				s.execute("insert into CustomerDebit values("+calc()+","+tfBillno.getText()+","+amo+",sysdate)");
				JOptionPane.showMessageDialog(null,"Saved Successfully");
				updateDebitTbl();
				updateTable();
				setValue();
			}
			else
				JOptionPane.showMessageDialog(null,"Enter bill no and Amount both", "error",JOptionPane.ERROR_MESSAGE);
		} catch (Exception e1) 
		{
			JOptionPane.showMessageDialog(null,"error " + e1.getMessage(), "error",JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
		}

	}
	public static String calc() {
		
		String st="0";
		try 
		{
			s=ConnectToDataBase.getS();
			ResultSet rs=s.executeQuery("select max(debitno) from CustomerDebit");
			while(rs.next())
				st=rs.getString(1);
			st=""+(Integer.parseInt(st)+1);
			
		} catch (Exception e1) 
		{
			JOptionPane.showMessageDialog(null,"error " + e1.getMessage(), "error",JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
		}
		return st;
	}
	public void updateDebitTbl() {
		
		tblDebit = getDebitDetails();
		sp_debit.setViewportView(tblDebit);
		
	}
	public void updateTable() {
		getCreditDetails();
		tblCredit=new JTable(vv,v){

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			public Component prepareRenderer(TableCellRenderer renderer,int row,int column)
	        {
	            Component comp=super.prepareRenderer(renderer,row, column);
	           
	           if(Double.parseDouble(""+getValueAt(row,3))>0){
	               comp.setBackground(new Color(200,0,5));
	               comp.setForeground(Color.YELLOW);
	           }
	           else{
	               comp.setBackground(Color.green);
	               comp.setForeground(Color.black);
	           }
	      
	           return comp;
	        }
			
		};
		tblCredit.setGridColor(Color.black);
		sp_credit.setViewportView(tblCredit);
		tblCredit.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int i = tblCredit.getSelectionModel().getLeadSelectionIndex();
				tfBillno.setText(vv.get(i).get(0));
				tfDate.setText(vv.get(i).get(1));
			}
		});
		
	}
	public void getCreditDetails()
	{
		//JTable t=new JTable();
		try 
		{
			vv=new Vector<Vector<String>>();
			
			
			String s ="select billno,sale_date,bill,balance from sale1 where ID="+ID;
			if((!cbPaid.isSelected())&&(!cbNotPaid.isSelected())){
				s+=" and balance <> 0 and balance = 0";
			}
			else if(cbPaid.isSelected()&&(!cbNotPaid.isSelected())){
				s=s+" and balance = 0";
			}
			else if(cbNotPaid.isSelected()&&(!cbPaid.isSelected())){
				s=s+" and balance <> 0";
			}
			
			
			CustomerPurchase.s.execute(s);
			ResultSet rs=CustomerPurchase.s.getResultSet();
			
			Vector<String> sv=new Vector<String>();
			while(rs.next()){
				for(int i=1;i<=v.size();i++){
					sv.add(rs.getString(i));
					
				}
				vv.add(new Vector<String>(sv));
				sv.clear();
			}
			//t=new JTable(vv,v);
		} catch (Exception e1) 
		{
			JOptionPane.showMessageDialog(null,"error " + e1.getMessage(), "error",JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
		}
			
		//return t;
	}
	public JTable getDebitDetails()
	{
		JTable t=new JTable();
		try 
		{
			vv1=new Vector<Vector<String>>();
			
			
			String s ="select debitNo,billno,debitdate,Amount from CustomerDebit natural join sale1 where ID="+ID;
			String billno=tfBillno.getText();
			if(billno!=null&&billno.length()>0){
				s+=" and billno =" +billno;
			}

			CustomerPurchase.s.execute(s);
			ResultSet rs=CustomerPurchase.s.getResultSet();
			
			Vector<String> sv=new Vector<String>();
			while(rs.next()){
				for(int i=1;i<=v1.size();i++){
					sv.add(rs.getString(i));
					
				}
				vv1.add(new Vector<String>(sv));
				sv.clear();
			}
			t=new JTable(vv1,v1);
		} catch (Exception e1) 
		{
			JOptionPane.showMessageDialog(null,"error " + e1.getMessage(), "error",JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
		}
			
		return t;
	}

	public static void create(String ID){
		
		f=new JFrame();
		f.getContentPane().add(new CustomerPurchase(ID));
		f.setVisible(true);
		
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getDefaultScreenDevice();
		int width = gd.getDisplayMode().getWidth();
		int height = gd.getDisplayMode().getHeight();

		int w=900,h=650;
		f.setLocation(width / 2 - w/2, height / 2 - h/2);

		f.setSize(900,650);
	}
}
