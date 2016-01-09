package javacode;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

class SearchPur extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static JFrame f;
	JButton search, backbtn;
	JTextField tf, tfBill;
	Statement s;
	JComboBox<?> list;
	JTable j;
	JScrollPane pane;
	JLabel bak, l1;
	Vector<?> att, valu;
	String attr[] = { "grnno", "sid" ,"sname"};
	String attr1[] = { "GRN no", "Supplier ID","Supplier Name" };
	private JButton btnPrint;
	SearchPur() {
		super(new BorderLayout());
		getInputMap().put(KeyStroke.getKeyStroke("ESCAPE"),"action");
		getActionMap().put("action", new AbstractAction() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				f.setVisible(false);
				
			}
		});
		
		bak = new JLabel(new ImageIcon(Company.class.getResource("/images/bak5.jpg")));
		add(bak);
		setSize(800, 600);
		JLabel l = new JLabel(new ImageIcon(Company.class.getResource("/images/spur.png")));
		l.setForeground(Color.yellow);
		l.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
		l.setBounds(300, 20, 400, 100);
		bak.add(l);
		s=ConnectToDataBase.getS();
		bak.setLayout(null);

		int h = 100;
		int w = 200;


		JPanel pnl = new JPanel();

		list = new JComboBox<Object>(attr1);
		pnl.add(list);

		tf = new JTextField(10);
		tf.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		// tf.setBounds(w+200,h+100,200,30);
		pnl.add(tf);

		pnl.setBounds(w, h + 80, 400, 80);
		bak.add(pnl);
		pnl.setBorder(BorderFactory.createTitledBorder("Choose"));

		JPanel p = new JPanel(null);

		l1 = new JLabel("Bill", SwingConstants.CENTER);
		// l1.setForeground(Color.white);
		l1.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		l1.setBounds(0, 0, 100, 30);
		p.setBounds(w - 180, h + 350, 150, 70);
		p.add(l1);

		tfBill = new JTextField();
		// tfBill.setHorizontalAlignment(SwingConstants.CENTER);
		tfBill.setFont(new Font("Comic Sans MS", Font.BOLD, 10));
		tfBill.setBounds(20, 30, 100, 30);
		p.add(tfBill);
		p.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		bak.add(p);

		display();
		// search=new JButton("SEARCH");

		// search.setBounds(w+450,h+100,100,30);
		tf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
			

					String st = "";
					if (list.getSelectedItem().equals("Supplier Name")) {
						st = "SELECT grnno,pid,pname,prate,quantity,sname FROM product1 natural join prod_purch natural join purchase1 natural join supplier where "
								+ attr[list.getSelectedIndex()]
								+ " like '"
								+ tf.getText() + "%'";
					} else {
						st = "SELECT  grnno,pid,pname,prate,quantity,sname FROM product1 natural join prod_purch natural join purchase1 natural join supplier where "
								+ attr[list.getSelectedIndex()]
								+ "="
								+ tf.getText();
					}
					if ((tf.getText()).length() <= 0) {
						st = "SELECT  grnno,pid,pname,prate,quantity,sname FROM product1 natural join prod_purch natural join purchase1 natural join supplier ";
					}
					s.execute(st);
					ResultSet rs = s.getResultSet();

					j = new JTable();
					
					Vector<Vector<String>> vv = new Vector<Vector<String>>();
					Vector<String> v = new Vector<String>();
					Vector<String> sv = new Vector<String>();

					while (rs.next()) {
						for (int k = 1; k <= 6; k++)
							sv.add("" + rs.getString(k));

						vv.add(new Vector<String>(sv));
						sv.clear();
					}
					v.add("GRN No");
					v.add("product_id");
					v.add("product_name");
					v.add("rate");
					v.add("quantity");
					v.add("Supplier name");

					j = new JTable(vv, v);
					j.setAutoCreateRowSorter(true);
					pane.setVisible(false);
					pane = new JScrollPane(j);
					pane.setBounds(200, 300, 550, 250);
					bak.add(pane);

					if ((tf.getText()).length() <= 0) {
						s.execute("select bill from purchase1 natural join supplier");
					} else {
						s.execute("select bill from purchase1 natural join supplier where "
								+ attr[list.getSelectedIndex()]
								+ "="
								+ tf.getText());
					}

					rs = s.getResultSet();

					double b = 0;
					while (rs.next()) {
						b += Double.parseDouble(rs.getString(1));
					}
					tfBill.setText("" + b);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnPrint=new JButton("Print");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		btnPrint.setHorizontalAlignment(SwingConstants.CENTER);
		btnPrint.setIcon(new ImageIcon(SearchPur.class.getResource("/images/print.PNG")));
		btnPrint.setBounds(20,400,150,30);
		pnl.add(btnPrint);

	}

	public static void create() {
		f = new JFrame("Search");
		f.getContentPane().add(new SearchPur());

		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getDefaultScreenDevice();
		int width = gd.getDisplayMode().getWidth();
		int height = gd.getDisplayMode().getHeight();

		int w = 800, h = 600;
		f.setLocation(width / 2 - (w / 2), height / 2 - (h / 2));

		f.setSize(w, h);
		f.setVisible(true);
	}

	public static void main(String[] args) {
		//JFrame.setDefaultLookAndFeelDecorated(true);
		ConnectToDataBase.main(null);
		create();
	}

	public void display() {
		try {

	
			String st = "SELECT  grnno,pid,pname,prate,quantity,sname FROM product1 natural join prod_purch natural join purchase1 natural join supplier ";
			s.execute(st);
			ResultSet rs = s.getResultSet();

			j = new JTable();
			Vector<Vector<String>> vv = new Vector<Vector<String>>();
			Vector<String> v = new Vector<String>();
			Vector<String> sv = new Vector<String>();

			while (rs.next()) {
				for (int k = 1; k <= 6; k++)
					sv.add("" + rs.getString(k));

				vv.add(new Vector<String>(sv));
				sv.clear();
			}
			v.add("GRN no");
			v.add("product_id");
			v.add("product_name");
			v.add("rate");
			v.add("quantity");
			v.add("Supplier name");

			s.execute("select bill from purchase1 natural join supplier");

			rs = s.getResultSet();

			double b = 0;
			while (rs.next()) {
				b += Double.parseDouble(rs.getString(1));
			}
			tfBill.setText("" + b);
			j = new JTable(vv, v);
			j.setAutoCreateRowSorter(true);
			pane = new JScrollPane(j);
			pane.setBounds(200, 300, 550, 250);
			bak.add(pane);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
