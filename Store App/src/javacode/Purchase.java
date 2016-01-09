package javacode;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

class Purchase extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static JFrame f;
	JTextArea ar;
	JButton backbtn;
	JTextField tfid, tfqu, tfsid;
	Statement s;
	JLabel bak;
	JLabel l1;
	int grnno;
	boolean flg = false;
	double bill = 0;
	Connection conn;
	Vector<Vector<String>> vv, vv1;
	Vector<String> v;
	Vector<String> sv, sv1;
	JButton save;
	String name, rate;
	JButton btnSave;
	static JScrollPane sc;

	Purchase() {
		super(new BorderLayout());

		bak = new JLabel(new ImageIcon(
				Purchase.class.getResource("/images/bak5.jpg")));
		add(bak);

		bak.setLayout(null);
		setSize(800, 600);
		int h = 160;
		int w = 170;

		sc = new JScrollPane();
		JLabel l2 = new JLabel(new ImageIcon(
				Purchase.class.getResource("/images/goods.png")));
		l2.setForeground(Color.white);
		l2.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
		l2.setBounds(350, 20, 400, 100);
		bak.add(l2);

		vv = new Vector<Vector<String>>();
		v = new Vector<String>();
		sv = new Vector<String>();

		vv1 = new Vector<Vector<String>>();
		sv1 = new Vector<String>();

		v.add("product_name");
		v.add("rate");
		v.add("quantity");
		v.add("price");

		JPanel p = new JPanel();

		JLabel l = new JLabel("Supplier Id:");
		l.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		p.add(l);
		p.setBounds(w - 120, h + 50, 700, 80);
		p.setBorder(BorderFactory.createTitledBorder("Purchase Details"));
		bak.add(p);

		tfsid = new JTextField(5);
		tfsid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfid.requestFocus();
			}
		});
		tfsid.setFont(new Font("Comic Sans MS", Font.BOLD, 15));

		p.add(tfsid);

		l = new JLabel("Product Id:");
		l.setFont(new Font("Comic Sans MS", Font.BOLD, 18));

		p.add(l);

		tfid = new JTextField(5);
		tfid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfqu.requestFocus();
			}
		});
		tfid.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		tfid.setBounds(w + 200, h, 100, 30);
		p.add(tfid);

		l = new JLabel("Quantity:");
		l.setFont(new Font("Comic Sans MS", Font.BOLD, 18));

		p.add(l);

		calc();

		tfqu = new JTextField(10);
		tfqu.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		tfqu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					s.execute("select pname,prate from product1 where pid="
							+ tfid.getText());
					ResultSet rs = s.getResultSet();

					while (rs.next()) {
						name = rs.getString(1);
						rate = rs.getString(2);
					}
					sv.add(name);
					sv.add(rate);

					int i = 0;
					Vector<Vector<String>> c1 = new Vector<Vector<String>>(vv);
					Vector<Vector<String>> c2 = new Vector<Vector<String>>(vv1);
					for (i = 0; i < c1.size(); i++) {

						Vector<String> test = c2.get(i);
						// System.out.println("i= "+test.get(0)+" "+test.get(1)+" "+test.get(2)+" "+test.get(3));
						String supp = "" + test.get(2);
						String pro = "" + test.get(3);
						// System.out.println(supp+" "+tfsid.getText()+" "+pro+" "+tfid.getText());

						if (supp.equals(tfsid.getText())
								&& pro.equals(tfid.getText())) {
							int qu = Integer.parseInt("" + test.get(0));
							double amount1 = Double.parseDouble(""
									+ c1.get(i).get(3));
							double amount2 = Double.parseDouble(rate)
									* Integer.parseInt(tfqu.getText());
							sv.add(""
									+ (qu + Integer.parseInt(""
											+ tfqu.getText())));
							sv1.add(""
									+ (qu + Integer.parseInt(""
											+ tfqu.getText())));
							sv.add("" + (amount1 + amount2));

							vv1.remove(i);
							vv.remove(i);

							break;
						}

					}
					if (i == c1.size()) {
						sv.add(tfqu.getText());
						sv1.add(tfqu.getText());

						sv.add("" + Double.parseDouble(rate)
								* Integer.parseInt(tfqu.getText()));
					}
					sv1.add("" + grnno);
					sv1.add(tfsid.getText());
					sv1.add(tfid.getText());

					vv.add(new Vector<String>(sv));
					sv.clear();

					vv1.add(new Vector<String>(sv1));
					sv1.clear();

					display();
					tfid.setText("");
					tfqu.setText("");
					tfid.requestFocus();

				} catch (Exception e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null,
							"error " + e1.getMessage(), "error",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});

		p.add(tfqu);

		l1 = new JLabel("Bill=" + bill);
		l1.setForeground(Color.white);
		l1.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		l1.setBounds(w + 100, h + 350, 300, 30);
		bak.add(l1);

		btnSave = new JButton("Save");
		btnSave.setBounds(w + 480, h + 350, 100, 30);
		bak.add(btnSave);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					s.execute("insert into purchase1 values("
							+ (vv1.get(0).get(1)) + "," + vv1.get(0).get(2)
							+ "," + bill + ",sysdate)");
					for (int i = 0; i < vv.size(); i++) {
						System.out.println(vv.get(i).get(0));

						s.execute("insert into prod_purch values("
								+ vv1.get(i).get(3) + "," + vv1.get(i).get(1)
								+ "," + vv1.get(i).get(0) + ")");

					}
					JOptionPane.showMessageDialog(f, "Saved Successfully");
					f.setVisible(false);
					Purchase.main(null);
				} catch (Exception e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(f,
							"error " + e1.getMessage(), "error",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});

		l = new JLabel("GRN No:" + grnno);
		l.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		l.setBounds(w + 300, h - 70, 200, 30);
		bak.add(l);

		backbtn = new JButton("Exit");
		backbtn.setBounds(w - 120, h + 350, 100, 30);
		backbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				f.setVisible(false);
			}
		});
		bak.add(backbtn);

	}

	public static void create() {
		f = new JFrame("Purchase");
		f.getContentPane().add(new Purchase());
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
		JFrame.setDefaultLookAndFeelDecorated(true);
		create();
	}

	public void calc() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:orcl", "system", "orcl");

			s = conn.createStatement();

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

	public void display() {
		try {
			sc.setVisible(false);
			JTable j = new JTable();
			bill += Double.parseDouble(rate) * Integer.parseInt(tfqu.getText());
			l1.setText("Bill=" + bill);
			j = new JTable(vv, v);
			sc = new JScrollPane(j);
			sc.setBounds(50, 300, 700, 200);
			bak.add(sc);

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "error " + e.getMessage(),
					"error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
