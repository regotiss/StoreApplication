package javacode;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

class Stock extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static JFrame f;
	JLabel bak;
	JTextField tfsh;
	JTable listtbl,shtbl;
	static Vector<Vector<String>> vv;
	static Vector<String> v, sv;
	private static Statement s;
	private static Vector<Vector<String>> v_short;
	JScrollPane list, sh;
	//private JButton btnPur;
	JTable shotagetbl;
	private JPopupMenu popup;

	Stock() {
		super(new BorderLayout());
		
		popup=new JPopupMenu();
		
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
		bak = new JLabel(new ImageIcon(Stock.class.getResource("/images/bak2.jpg")));
		add(bak);

		bak.setLayout(null);
		setSize(800, 600);
		JLabel l = new JLabel(new ImageIcon(Stock.class.getResource("/images/stock.png")));
		l.setForeground(Color.yellow);
		l.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
		l.setBounds(350, 20, 400, 100);
		bak.add(l);

		list = new JScrollPane(prodList());
		list.setBackground(Color.blue);
		list.setBounds(400, 150, 400, 330);
		list.setBorder(BorderFactory
				.createTitledBorder("<html><b><font color=yellow family=Arial size=5>Stock Available</font></b></html>"));
		bak.add(list);

		/*JLabel l1 = new JLabel("Stock below :");
		l1.setBounds(10, 500, 150, 30);
		l1.setForeground(Color.yellow);
		l1.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		bak.add(l1);

		/*tfsh = new JTextField("20");
		tfsh.setBounds(150, 500, 150, 30);
		bak.add(tfsh);*/
		
		shotagetbl=shortage();
		addMenuItems();
		sh = new JScrollPane(shotagetbl);
		sh.setBackground(Color.blue);
		sh.setBounds(0, 150, 400, 330);
		sh.setBorder(BorderFactory
				.createTitledBorder("<html><b><font color=yellow family=Arial size=5>Limited Stock</font></b></html>"));
		bak.add(sh);

		/*tfsh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sh.setVisible(false);
				sh = new JScrollPane(shortageList(tfsh.getText()));
				sh.setBounds(0, 150, 400, 300);
				bak.add(sh);
			}
		})*/;
		
		/*btnPur=new JButton("Purchase");
		btnPur.setBounds(800-150,500,100,30);
		btnPur.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){

				PurchaseMenu.main(null);		
				f.setVisible(false);
			}
		});
		bak.add(btnPur);*/

	}


	public void addMenuItems() {
		JMenuItem order=new JMenuItem("Place Order");
		order.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int i = shotagetbl.getSelectionModel().getLeadSelectionIndex();
				if(i>=0)
				{
					String id=v_short.get(i).get(0);
					String name=v_short.get(i).get(1);
	
					ChooseSupplier.create(id,name);
				}
				else
					JOptionPane.showMessageDialog(null,"Please select Item", "error",JOptionPane.WARNING_MESSAGE);


			}
		});
		popup.add(order);
		shotagetbl.setComponentPopupMenu(popup);
	}


	public static JTable prodList() {
	
		JTable sc = null;
		try {
			s=ConnectToDataBase.getS();
			s.execute("select pid,pname,avail,prate,minlimit from product1 natural join available1");
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
			v.add("id");
			v.add("Product Name");
			v.add("Available");
			v.add("Rate");
			v.add("MinLimit");

			sc = new JTable(vv, v);
		

		} catch (Exception e) {
			e.printStackTrace();
		}
		return sc;
	}

	public static JTable shortage() {
		v_short = new Vector<Vector<String>>();
		JTable jt = new JTable();
		try {
			
			for (int i = 0; i < vv.size(); i++) {
				int avail = Integer.parseInt("" + vv.get(i).get(2));
				int min =Integer.parseInt(""+vv.get(i).get(4));
				if (avail < min)
					v_short.add(new Vector<String>(vv.get(i)));
			}
			jt = new JTable(v_short, v);
		
		} catch (Exception e) {
		}
		return jt;
	}
	public void list(String val) {
		Vector<Vector<String>> tmp = new Vector<Vector<String>>();
		
		try {
			int lim = Integer.parseInt(val);
			for (int i = 0; i < vv.size(); i++) {
				int avail = Integer.parseInt("" + vv.get(i).get(2));
				if (avail < lim)
					tmp.add(new Vector<String>(vv.get(i)));
			}
			//jt = new JTable(tmp, v);
		} catch (Exception e) {
		}
	
	}
	

	public static void create() {
		f = new JFrame("Stock Available");
		f.setIconImage(new ImageIcon(Main1.class.getResource("/image/Stock and Purchase.png")).getImage());
		f.add(new Stock());
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getDefaultScreenDevice();
		int width = gd.getDisplayMode().getWidth();
		int height = gd.getDisplayMode().getHeight();

		f.setLocation(width / 2 - 410, height / 2 - 300);

		f.setSize(820, 600);
		f.setVisible(true);
	}

	public static void main(String[] args) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		ConnectToDataBase.main(null);
		create();
	}
	
}