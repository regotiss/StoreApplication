package javacode;

import java.awt.EventQueue;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.KeyStroke;

public class Order extends BaseStruct{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					create("1");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public static void create(String sid){
		frame=new JFrame();
		frame.getContentPane().add(new Order(sid));
		frame.setVisible(true);
		GraphicsDevice gd = GraphicsEnvironment
				.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int width = gd.getDisplayMode().getWidth();
		int height = gd.getDisplayMode().getHeight();

		frame.setLocation(width / 2 - 400, height / 2 - 300);

		frame.setSize(800, 600);
		
	}
	public Order(String sid2) {
	
		
		super(sid2);
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
		/*sid=sid2;
		grnno=0;
		
		sv1=new Vector<Vector<String>>();
		v1=new Vector<String>();
		
		setSize(800, 600);
		bak = new JLabel(new ImageIcon(
				"E:/eclipseprog/StoreApp/src/images/bak2.jpg"));
		bak.setLayout(null);
		bak.setBounds(0, 0, getWidth(), getHeight());
		add(bak);
		
		vv1=new Vector<Vector<String>>();
		scrollPane = new JScrollPane();
		scrollPane.setBorder(new TitledBorder(null, "Items Below Limit", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		scrollPane.setBounds(6, 145, 261, 358);
		bak.add(scrollPane);
		
		shortagetbl = prodList(sid);
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
		tfid.setBounds(85, 66, 120, 28);
		panel.add(tfid);
		tfid.setColumns(10);
		
		JLabel lblName = new JLabel("Name :");
		lblName.setBounds(10, 106, 75, 14);
		panel.add(lblName);
		lblName.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 14));
		
		tfname = new JTextField();
		tfname.setBounds(85, 101, 120, 28);
		panel.add(tfname);
		tfname.setColumns(10);
		
		JLabel lblQuantity = new JLabel("Quantity :");
		lblQuantity.setBounds(10, 137, 75, 22);
		panel.add(lblQuantity);
		lblQuantity.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 14));
		
		tfqu = new JTextField();
		tfqu.setBounds(85, 135, 122, 28);
		panel.add(tfqu);
		tfqu.setColumns(10);
		
		JLabel lblRate = new JLabel("Rate :");
		lblRate.setBounds(10, 175, 75, 22);
		panel.add(lblRate);
		lblRate.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 14));
		
		tfrate = new JTextField();
		tfrate.setBounds(83, 173, 122, 28);
		panel.add(tfrate);
		tfrate.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Vector<String> ve=new Vector<String>();
				ve.add(tfid.getText());
				ve.add(tfname.getText());
				ve.add(tfqu.getText());
				int i;
				for(i=0;i<vv.size();i++){
					if(vv.get(i).contains(ve.get(0)))
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
				
					shortagetbl=new JTable(vect,v);
					shortagetbl.getSelectionModel().addListSelectionListener(new RowListener());
					scrollPane.setViewportView(shortagetbl);
				}
				
				for(i=0;i<vv1.size();i++){
					if(vv1.get(i).contains(ve.get(0)))
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
				scrollPane_1.setViewportView(purchtbl);
				clear();
				
			}
		});
		btnAdd.setBounds(10, 305, 90, 28);
		panel.add(btnAdd);
		
		JButton btnReset = new JButton("Reset");
		btnReset.setBounds(112, 305, 90, 28);
		panel.add(btnReset);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBorder(new TitledBorder(null, "Items To be Purchase ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPane_1.setBounds(517, 145, 255, 358);
		bak.add(scrollPane_1);
		
		purchtbl = new JTable();
		scrollPane_1.setViewportView(purchtbl);
		
		calc();
		JLabel lblGrnNo = new JLabel("GRN NO:"+grnno);
		lblGrnNo.setForeground(Color.YELLOW);
		lblGrnNo.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 14));
		lblGrnNo.setBounds(473, 109, 200, 24);
		bak.add(lblGrnNo);
		
		order = new JButton("Order");
		order.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					s.execute("insert into purchase1 values("
							+ grnno + "," + sid
							+ "," + bill + ",sysdate)");
					for (int i = 0; i < sv1.size(); i++) {

						s.execute("insert into prod_purch values("
								+ sv1.get(i).get(0) + "," + grnno
								+ "," + sv1.get(i).get(1) + ")");

					}
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


		order.setBounds(607, 515, 90, 28);
		bak.add(order);
		
		l1 = new JLabel("Bill=" + bill);
		l1.setForeground(Color.white);
		l1.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		l1.setBounds(160 + 100, 170 + 350, 300, 30);
		bak.add(l1);
		
		
		*/
		
	}
	
	/*public void calc() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:orcl", "system", "orcl");

			s = con.createStatement();

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
			System.out.println(vv.get(i).size());
			tfid.setText("" + vv.get(i).get(0));
			tfname.setText("" + vv.get(i).get(1));
			System.out.println(vv+" "+v);
			tfqu.setText("" + (Integer.parseInt(vv.get(i).get(3))-Integer.parseInt(vv.get(i).get(2))));
			tfrate.setText(""+vv.get(i).get(4));
		}
	}
	public JTable prodList(String sid){
		JTable t=new JTable();
		Vector<Vector<String>> vect=null;
	
		try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con = DriverManager.getConnection(
						"jdbc:oracle:thin:@localhost:1521:orcl", "system", "orcl");

			s = con.createStatement();
			s.execute("select distinct pid,pname,avail,minlimit,prate from product1 natural join available1 natural join prod_purch natural join  purchase1 natural join supplier where sid="+
						sid+" and avail< minlimit");
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
			v.add("quantity");
			System.out.println(vv);
			t = new JTable(vect, v);
			System.out.println(vv);

		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "error " + e1.getMessage(),
					"error", JOptionPane.ERROR_MESSAGE);
		}
		return t;
		
	}
	*/
}
