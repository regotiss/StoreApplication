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
import javax.swing.AbstractButton;
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

class SearchPro extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static JFrame f;
	JButton search, backbtn;
	JTextField tf;
	private JTable j;
	Statement s;
	JComboBox<String> list;
	JScrollPane pane;
	JLabel bak;
	//JPanel bak;
	Vector<?> att, valu;
	String attr[] = { "pid", "pname" };
	private AbstractButton btnPrint;

	SearchPro() {
		super(new BorderLayout());
		s=ConnectToDataBase.getS();
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
		//bak=new JPanel();
		add(bak);

		bak.setLayout(null);

		setSize(800, 600);
		JLabel l = new JLabel(new ImageIcon(Company.class.getResource("/images/searchpro.png")));
		l.setForeground(Color.yellow);
		l.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
		l.setBounds(300, 20, 400, 100);
		bak.add(l);



		list = new JComboBox<String>(attr);
		list.setBounds(200, 200, 150, 30);
		bak.add(list);
		display();
		tf = new JTextField();
		tf.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		tf.setBounds(370, 200, 200, 30);
		bak.add(tf);

		// search=new JButton("SEARCH");

		// search.setBounds(w+450,h+100,100,30);
		tf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
				
					String st = "SELECT pid,pname,name,avail,prate FROM available1 natural join product1 natural join company1 where "
							+ list.getSelectedItem() + "=" + tf.getText();

					if (list.getSelectedItem().equals("pname")) {
						st = "SELECT pid,pname,name,avail,prate FROM available1 natural join product1 natural join company1 where "
								+ list.getSelectedItem()
								+ " like '"
								+ tf.getText() + "%'";
					}
					if ((tf.getText()).length() <= 0) {
						st = "SELECT pid,pname,name,avail,prate FROM available1 natural join product1 natural join company1";
					}
					s.execute(st);
					ResultSet rs = s.getResultSet();

					j = new JTable();
					Vector<Vector<String>> vv = new Vector<Vector<String>>();
					Vector<String> v = new Vector<String>();
					Vector<String> sv = new Vector<String>();

					while (rs.next()) {
						// ar[i]=new String[2];
						// ar[i][0]=""+rs.getString(1);
						// ar[i][1]=""+rs.getString(2);
						for (int k = 1; k <= 5; k++)
							sv.add("" + rs.getString(k));

						vv.add(new Vector<String>(sv));
						sv.clear();
					}
					v.add("product_id");
					v.add("product_name");
					v.add("company");
					v.add("quantity");
					v.add("price");

					j = new JTable(vv, v);
					pane.setVisible(false);
					pane = new JScrollPane(j);
					pane.setBounds(200, 300, 550, 250);
					bak.add(pane);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		// bak.add(search);

		backbtn = new JButton("Exit");
		backbtn.setBounds(50, 450, 100, 30);
		backbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				f.setVisible(false);
			}
		});
		bak.add(backbtn);
		
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
		btnPrint.setBounds(600,200,100,30);
		bak.add(btnPrint);

	}

	public static void create() {
		f = new JFrame("Delete");
		f.add(new SearchPro());

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
		ConnectToDataBase.main(null);
		JFrame.setDefaultLookAndFeelDecorated(true);
		create();
	}

	public void display() {
		try {

			String st = "SELECT pid,pname,name,avail,prate FROM available1 natural join product1 natural join company1";
			s.execute(st);
			ResultSet rs = s.getResultSet();

			j = new JTable();
	
			Vector<Vector<String>> vv = new Vector<Vector<String>>();
			Vector<String> v = new Vector<String>();
			Vector<String> sv = new Vector<String>();

			while (rs.next()) {

				for (int k = 1; k <= 5; k++)
					sv.add("" + rs.getString(k));

				vv.add(new Vector<String>(sv));
				sv.clear();
			}
			v.add("product_id");
			v.add("product_name");
			v.add("company");
			v.add("quantity");
			v.add("price");

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
