package javacode;

import java.awt.EventQueue;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.KeyStroke;
import javax.swing.UIManager;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.Color;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class BaseStruct extends JPanel{

	private static final long serialVersionUID = 1L;
	protected static JFrame frame;
	protected JLabel bak;
	protected JTable shortagetbl;
	protected JTextField tfid;
	protected JTextField tfname;
	protected JTextField tfqu;
	protected JTextField tfrate;
	protected JPanel panel;
	protected JTable purchtbl;
	protected JScrollPane scrollPane;
	protected String sid,no;
	protected JButton order;
	protected Statement s;
	protected JScrollPane scrollPane_1;
	protected JLabel l1;
	protected double bill = 0;
	protected JLabel lblGrnNo;
	protected String tbl1,tbl2;
	protected int grnno = 0;
	private JButton btnAdd;
	protected static Vector<Vector<String>> vv,vv1,sv1;
	protected static Vector<String> v, sv,v1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConnectToDataBase.main(null);
					create("1");
				} catch (Exception e) {
					
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public static void create(String sid){
		frame=new JFrame("Give Order To Supplier No "+sid);
		Main1.setNimbus();
		frame.getContentPane().add(new BaseStruct(sid));
		frame.setVisible(true);
		GraphicsDevice gd = GraphicsEnvironment
				.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int width = gd.getDisplayMode().getWidth();
		int height = gd.getDisplayMode().getHeight();

		frame.setLocation(width / 2 - 400, height / 2 - 300);

		frame.setSize(800, 600);
		
	}
	public BaseStruct(String sid2) {
	
		
		super(null);
		
		s=ConnectToDataBase.getS();
		tbl2="prod_ordered";
		tbl1="placeorder";
		sid=sid2;
		grnno=0;
		no="orderno";
		
		sv1=new Vector<Vector<String>>();
		v1=new Vector<String>();
		vv1=new Vector<Vector<String>>();
		
		setSize(800, 600);
		bak = new JLabel(new ImageIcon(BaseStruct.class.getResource("/images/bak3.jpg")));
		bak.setLayout(null);
		bak.setBounds(0, 0, getWidth(), getHeight());
		add(bak);
		
		scrollPane = new JScrollPane();
		scrollPane.setBorder(new TitledBorder(null, "Items Below Limit", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		scrollPane.setBounds(6, 145, 261, 358);
		bak.add(scrollPane);
		
		shortagetbl = prodList(sid);
		addMaptoTable(shortagetbl);
		shortagetbl.getSelectionModel().addListSelectionListener(new RowListener());
		scrollPane.setViewportView(shortagetbl);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Purchase", TitledBorder.LEADING, TitledBorder.TOP, new Font("Bodoni MT Poster",Font.BOLD,12), Color.BLACK));
		panel.setBounds(277, 145, 225, 358);
		bak.add(panel);
		panel.setLayout(null);
		
		JLabel lblProductId = new JLabel("ID :");
		lblProductId.setBounds(10, 72, 75, 14);
		panel.add(lblProductId);
		lblProductId.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 14));
		
		tfid = new JTextField();
		tfid.setEditable(false);
		tfid.setBounds(85, 66, 120, 28);
		panel.add(tfid);
		tfid.setColumns(10);
		
		JLabel lblName = new JLabel("Name :");
		lblName.setBounds(10, 106, 75, 14);
		panel.add(lblName);
		lblName.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 14));
		
		tfname = new JTextField();
		tfname.setEditable(false);
		tfname.setBounds(85, 101, 120, 28);
		panel.add(tfname);
		tfname.setColumns(10);
		
		JLabel lblQuantity = new JLabel("Quantity :");
		lblQuantity.setBounds(10, 137, 75, 22);
		panel.add(lblQuantity);
		lblQuantity.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 14));
		
		tfqu = new JTextField();
		tfqu.setEditable(true);
		tfqu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				btnAdd.requestFocus();
			}
		});
		tfqu.setBounds(85, 135, 122, 28);
		panel.add(tfqu);
		tfqu.setColumns(10);
		
		JLabel lblRate = new JLabel("Rate :");
		lblRate.setBounds(10, 175, 75, 22);
		panel.add(lblRate);
		lblRate.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 14));
		
		tfrate = new JTextField();
		tfrate.setEditable(false);
		tfrate.setBounds(83, 173, 122, 28);
		panel.add(tfrate);
		tfrate.setColumns(10);
		
		btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addToList();
			}
		});
		btnAdd.setBounds(10, 305, 90, 28);
		panel.add(btnAdd);
		btnAdd.getInputMap().put(KeyStroke.getKeyStroke("ENTER"),"action");
		btnAdd.getActionMap().put("action", new AbstractAction() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				addToList();
				
			}
		});

		
		JButton btnRes = new JButton("Reset");
		btnRes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				clear();
			}
		});
		btnRes.getInputMap().put(KeyStroke.getKeyStroke("ENTER"),"action");
		btnRes.getActionMap().put("action", new AbstractAction() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				clear();
				
			}
		});
		btnRes.setBounds(112, 305, 90, 28);
		panel.add(btnRes);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBorder(new TitledBorder(null, "Items To be Purchase ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPane_1.setBounds(517, 145, 255, 358);
		bak.add(scrollPane_1);
		
		purchtbl = new JTable();
		
		scrollPane_1.setViewportView(purchtbl);
		scrollPane_1.setFocusable(false);
		purchtbl.setFocusable(false);
		calc();
		lblGrnNo = new JLabel(no+" :"+grnno);
		lblGrnNo.setForeground(Color.YELLOW);
		lblGrnNo.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 14));
		lblGrnNo.setBounds(473, 109, 200, 24);
		bak.add(lblGrnNo);
		
		order = new JButton("Order");
		order.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				orderSave();
			}
		});
		addMapOrder();

		order.setBounds(607, 515, 90, 28);
		bak.add(order);
		
		l1 = new JLabel("Bill=" + bill);
		l1.setForeground(Color.white);
		l1.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		l1.setBounds(160 + 100, 170 + 350, 300, 30);
		bak.add(l1);
				
	}
	private void addMapOrder() {
		order.getInputMap().put(KeyStroke.getKeyStroke("ENTER"),"action");
		order.getActionMap().put("action", new AbstractAction() {
		
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				orderSave();
				
			}
		});

	}

	protected void orderSave() {
		int res=JOptionPane.showConfirmDialog(null, "Are you sure ?");
		if(res!=1&&res!=2)
		{
			try {
				s.execute("insert into "+ tbl1+" values("
						+ grnno + "," + sid
						+ "," + bill + ",sysdate,'no')");
				for (int i = 0; i < sv1.size(); i++) {
	
					s.execute("insert into "+tbl2+ " values("
							+ sv1.get(i).get(0) + "," + grnno
							+ "," + sv1.get(i).get(1) + ")");
	
				}
				JOptionPane.showMessageDialog(null, "Saved Successfully");
				Date d=Calendar.getInstance().getTime();
				Main4.getMonthView().addFlaggedDates(d);
				
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null,
						"error " + e1.getMessage(), "error",
						JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}
		}
		frame.setVisible(false);

	}

	protected void addToList() {
		Vector<String> ve=new Vector<String>();
		ve.add(tfid.getText());
		ve.add(tfname.getText());
		ve.add(tfqu.getText());
		int i;
		for(i=0;i<vv.size();i++){
			if(vv.get(i).get(0).equals(ve.get(0)))
			{
				break;
				
			}
		}
		if(i!=vv.size()){
			vv.remove(i);
			
			Vector<Vector<String>> vect=new Vector<Vector<String>>();
			for(i=0;i<vv.size();i++){
			   vect.add(new Vector<String>(vv.get(i)));
			}
		
			changeTable(vect);
		}
		
		for(i=0;i<vv1.size();i++){
			if(vv1.get(i).get(0).equals(ve.get(0)))
			{			
				break;
			}
		}
		if(i==vv1.size()){
			v1.add(tfid.getText());
			v1.add(tfqu.getText());
			sv1.add(new Vector<String>(v1));
			bill += Double.parseDouble(tfrate.getText()) * Integer.parseInt(tfqu.getText());
			l1.setText("Bill=" + bill);
			v1.clear();
			
			vv1.add(new Vector<String>(ve));
	
		}
		ve.clear();
		ve.add("id");
		ve.add("product Name");
		ve.add("quantity");
		purchtbl=new JTable(vv1,ve);
		purchtbl.setAutoCreateRowSorter(true);
		scrollPane_1.setViewportView(purchtbl);
		purchtbl.setFocusable(false);
		clear();
		shortagetbl.requestFocus();
	
			
	}
	
	private void changeTable(Vector<Vector<String>> vect) {
		shortagetbl=new JTable(vect,v);
		shortagetbl.setAutoCreateRowSorter(true);
		addMaptoTable(shortagetbl);
		shortagetbl.getSelectionModel().addListSelectionListener(new RowListener());
		scrollPane.setViewportView(shortagetbl);
		
	}

	private void addMaptoTable(JTable t) {
		
		t.setFillsViewportHeight(true);
		t.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				
				scrollPane.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
			}
			
			@Override
			public void focusGained(FocusEvent e) {
		
				scrollPane.setBorder(UIManager.getBorder("TitledBorder.border"));
			}
		});
		t.getInputMap().put(KeyStroke.getKeyStroke("TAB"),"action");
		t.getActionMap().put("action", new AbstractAction() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				tfqu.requestFocus();
				
			}
		});
		t.getInputMap().put(KeyStroke.getKeyStroke("ENTER"),"action1");
		t.getActionMap().put("action1", new AbstractAction() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				tfqu.requestFocus();
			}
		});
	}
	public void calc() {
		try {

			//System.out.println("SELECT max("+no +") FROM "+tbl1);
			String st = "SELECT max("+no +") FROM "+tbl1;
			s.execute(st);
			ResultSet rs = s.getResultSet();
			rs.next();
			/*int max = 0;
			while (rs.next()) {
				String d = rs.getString(1);
				grnno = Integer.parseInt(d);
				if (grnno > max) {
					max = grnno;
				}
			}
			grnno = max + 1;
			*/
		
			grnno=rs.getInt(1)+1;
			
			
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "error " + e.getMessage(),
					"error", JOptionPane.ERROR_MESSAGE);
		}
	}

	void clear() {
		tfid.setText("");
		tfname.setText("");
		tfqu.setText("");
		tfrate.setText("");
	}
	private class RowListener implements ListSelectionListener {
		public void valueChanged(ListSelectionEvent event) {
			if (event.getValueIsAdjusting()) {
				return;
			}
			
			int i = shortagetbl.getSelectionModel().getLeadSelectionIndex();
		//	System.out.println(vv.get(i).size());
			tfid.setText("" + vv.get(i).get(0));
			tfname.setText("" + vv.get(i).get(1));
		//	System.out.println(vv+" "+v);
			tfqu.setText("" + (Integer.parseInt(vv.get(i).get(3))-Integer.parseInt(vv.get(i).get(2))));
			tfrate.setText(""+vv.get(i).get(4));
		}
	}
	public JTable prodList(String sid){
		JTable t=new JTable();
		Vector<Vector<String>> vect=null;
	
		try {
			/*s.execute("select distinct pid,pname,avail,minlimit,prate from product1 natural join available1 natural join prod_purch natural join purchase1 natural join supplier where pid not in (select pid from SupplierProducts where sid="+
						sid+") and avail< minlimit ) union (select distinct pid,pname,avail,minlimit,prate from product1 natural join available1 where avail=0)");*/
			s.execute("select distinct pid,pname,avail,minlimit,prate from available1 natural join product1  where pid in (select pid from SupplierProducts where sid="+
			sid+") and avail< minlimit");
			ResultSet rs = s.getResultSet();

			vv = new Vector<Vector<String>>();
			v = new Vector<String>();
			sv = new Vector<String>();

			while (rs.next()) {
				for (int k = 1; k <= 5; k++)
					sv.add("" + rs.getString(k));

				vv.add(new Vector<String>(sv));
				
				sv.clear();
			}
			
			vect=new Vector<Vector<String>>();
			for(int i=0;i<vv.size();i++){
			   vect.add(new Vector<String>(vv.get(i)));
			}
			v.add("id");
			v.add("product Name");
			v.add("available");
			v.add("minlimit");
			//System.out.println(vv);
			t = new JTable(vect, v);
			t.setAutoCreateRowSorter(true);
			//System.out.println(vv);

		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "error " + e1.getMessage(),
					"error", JOptionPane.ERROR_MESSAGE);
		}
		return t;
		
	}
	
}
