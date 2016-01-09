package javacode;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

class Customer extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static JFrame f;

	JLabel bak;
	JButton btnRes, btnSave, btnDel, btnEdit, btnRef;
	JTextField id, name, mob;
	JTextArea addr;
	JLabel l;
	int cid;
	static Statement s;
	JScrollPane list;
	JTable t;
	static Vector<Vector<String>> vv;
	static Vector<String> v, sv;
	JPanel pnlC;

	private JButton btnPurchaseDetails;

	private JPopupMenu popup;

	Customer() {
		super(new BorderLayout());
		popup=new JPopupMenu();
		addMenuItems();
		
		s=ConnectToDataBase.getS();
		setSize(800, 600);
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
		bak = new JLabel(new ImageIcon(
				Customer.class.getResource("/images/bak2.jpg")));
		add(bak);

		bak.setLayout(null);

		l = new JLabel(new ImageIcon(
				Customer.class.getResource("/images/cust.png")));
		l.setForeground(Color.yellow);
		l.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
		l.setBounds(300, 20, 400, 100);
		bak.add(l);

		JPanel pnlW = new JPanel(null);

		int w = 130, h = 60;
		l = new JLabel("Customer ID:");
		// l.setForeground(Color.white);
		l.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		l.setBounds(10, 30, 100, 30);
		pnlW.add(l);

		id = new JTextField();
		id.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		id.setBounds(130, 30, 115, 30);
		pnlW.add(id);
		id.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				name.requestFocus();
			}
		});

		l = new JLabel("Name :");
		// l.setForeground(Color.white);
		l.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		l.setBounds(10, 10 + h, 100, 30);
		pnlW.add(l);

		name = new JTextField();
		name.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		name.setBounds(w, 10 + h, 150, 30);
		pnlW.add(name);
		name.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mob.requestFocus();
			}
		});

		l = new JLabel("Mobile No:");
		l.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		l.setBounds(10, 50 + h, 100, 30);
		pnlW.add(l);

		mob = new JTextField();
		mob.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		mob.setBounds(w, 50 + h, 150, 30);
		pnlW.add(mob);
		mob.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addr.requestFocus();
			}
		});

		l = new JLabel("Address:");
		// l.setForeground(Color.white);
		l.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		l.setBounds(10, 30 + 2 * h, 100, 30);
		pnlW.add(l);

		addr = new JTextArea();
		addr.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		addr.getInputMap().put(KeyStroke.getKeyStroke("TAB"),"action");
		addr.getActionMap().put("action", new AbstractAction() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				btnSave.requestFocus();
				
			}
		});

		JScrollPane sc = new JScrollPane(addr);
		sc.setBounds(w, 30 + 2 * h, 150, 80);
		pnlW.add(sc);

		btnSave = new JButton("Save");
		btnSave.setBounds(w - 100, 240, 100, 30);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				saveAll();
			}

		

	
		});
		btnSave.getInputMap().put(KeyStroke.getKeyStroke("ENTER"),"action");
		btnSave.getActionMap().put("action", new AbstractAction() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				saveAll();
				
			}
		});

		pnlW.add(btnSave);

		btnRes = new JButton("Reset");
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
		btnRes.setBounds(w + 30, 240, 100, 30);
		pnlW.add(btnRes);

		pnlW.setBorder(BorderFactory.createRaisedBevelBorder());
		pnlW.setBounds(10, 150, 300, 300);
		pnlW.setBorder(BorderFactory.createTitledBorder("Customer Details"));
		bak.add(pnlW);
		
		JButton gen = new JButton("");
		gen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calc();
			}
		});
		gen.getInputMap().put(KeyStroke.getKeyStroke("ENTER"),"action");
		gen.getActionMap().put("action", new AbstractAction() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				calc();
				
			}
		});

		gen.setIcon(new ImageIcon(Customer.class.getResource("/images/gen.PNG")));
		gen.setBounds(252, 25, 40, 40);
		pnlW.add(gen);

		t = custList();
		
		addMaptoTable();
		
		list = new JScrollPane(t);

		list.setBounds(330, 150, 450, 300);

		bak.add(list);

		btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				edit();
			}
		});
		btnEdit.getInputMap().put(KeyStroke.getKeyStroke("ENTER"),"action");
		btnEdit.getActionMap().put("action", new AbstractAction() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				edit();
				
			}
		});

		btnEdit.setBounds(w - 100, 500, 100, 30);
		bak.add(btnEdit);

		btnDel = new JButton("Delete");
		btnDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					delet();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null,
							"error " + e1.getMessage(), "error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnDel.getInputMap().put(KeyStroke.getKeyStroke("ENTER"),"action");
		btnDel.getActionMap().put("action", new AbstractAction() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				delet();
				
			}
		});
		btnDel.setBounds(w + 10, 500, 100, 30);
		bak.add(btnDel);
		
		btnPurchaseDetails=new JButton("Credit Debit Note");
		btnPurchaseDetails.setBounds(w + 150, 500, 200, 30);
		btnPurchaseDetails.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(id.getText().length()>0)
				CustomerPurchase.create(id.getText());
				else
				JOptionPane.showMessageDialog(null, "Enter Customer ID","error",JOptionPane.WARNING_MESSAGE);	
				
			}
		});
		bak.add(btnPurchaseDetails);
	}

	
	public void addMenuItems() {
		JMenuItem sale=new JMenuItem("Purchase");
		sale.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(id.getText().length()>0)
				SaleToCust.create(id.getText());
				else
				JOptionPane.showMessageDialog(null,"Please select supplier", "error",JOptionPane.WARNING_MESSAGE);
			}
		});
		popup.add(sale);
	}


	protected void delet() {
		
		int res=JOptionPane.showConfirmDialog(null, "Are you sure to delete record?");
		if(res!=1&&res!=2)
		{
			try {
				
				s.execute("delete from customr where id=" + id.getText());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null,
						"error " + e.getMessage(), "error",
						JOptionPane.ERROR_MESSAGE);
			}
			JOptionPane.showMessageDialog(null, "Deleted Successfully");
			changeTable();
		}
		clear();
		
	}

	protected void edit() {
		try {
			int res=JOptionPane.showConfirmDialog(null, "Do you want to Update ?");
			if(res!=1&&res!=2){
				s.execute("update customr set name='" + name.getText()
						+ "' where id=" + id.getText());
				s.execute("update customr set addr='" + addr.getText()
						+ "' where id=" + id.getText());
				s.execute("update customr set mobno='" + mob.getText()
						+ "' where id=" + id.getText());
				JOptionPane.showMessageDialog(f, "Updated Successfully");
				changeTable();
				
			}
			clear();
			t.requestFocus();
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null,
					"error " + e1.getMessage(), "error",
					JOptionPane.ERROR_MESSAGE);
		}
		
	}

	private void addMaptoTable() {
		
		t.getSelectionModel().addListSelectionListener(new RowListener());
		t.setFillsViewportHeight(true);
		t.setComponentPopupMenu(popup);
		t.setAutoCreateRowSorter(true);
		t.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				
				list.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				
				list.setBorder(UIManager.getBorder("TitledBorder.border"));
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
				btnEdit.requestFocus();
				
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
				name.requestFocus();
			}
		});
	}
	protected void saveAll() {
		
		int res=JOptionPane.showConfirmDialog(null, "Do you want to Save ?");
		if(res!=1&&res!=2){
			try {
				
				s.execute("insert into customr values(" + id.getText()
						+ ",'" + name.getText() + "','" + addr.getText()
						+ "','" + mob.getText() + "')");
				JOptionPane.showMessageDialog(f, "Saved Successfully");
				clear();
				changeTable();
				
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null,
						"error " + e1.getMessage(), "error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		
	}

	private class RowListener implements ListSelectionListener {
		public void valueChanged(ListSelectionEvent event) {
			if (event.getValueIsAdjusting()) {
				return;
			}
			int i = t.getSelectionModel().getLeadSelectionIndex();
			id.setText("" + vv.get(i).get(0));
			name.setText("" + vv.get(i).get(1));
			addr.setText("" + vv.get(i).get(2));
			mob.setText("" + vv.get(i).get(3));
		}
	}

	void changeTable() {
	
		t = custList();
		addMaptoTable();
		list.setViewportView(t);
		clear();
	}

	void clear() {
		id.setText("");
		name.setText("");
		addr.setText("");
		mob.setText("");
	}

	public static JTable custList() {

		JTable sc = null;
		try {

			s.execute("select * from customr where id<>0 order by id desc");
			ResultSet rs = s.getResultSet();

			vv = new Vector<Vector<String>>();
			v = new Vector<String>();
			sv = new Vector<String>();

			while (rs.next()) {
				for (int k = 1; k <= 4; k++)
					sv.add("" + rs.getString(k));

				vv.add(new Vector<String>(sv));
				sv.clear();
			}
			v.add("Customer id");
			v.add("Name");
			v.add("Address");
			v.add("Mobile No");

			sc = new JTable(vv, v);

		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "error " + e1.getMessage(),
					"error", JOptionPane.ERROR_MESSAGE);
		}
		return sc;
	}

	public static void create() {
		f = new JFrame("Customer Details");
		f.setIconImage(new ImageIcon(Customer.class.getResource("/image/Customer.png")).getImage());
		Main1.setNimbus();
		f.getContentPane().add(new Customer());
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getDefaultScreenDevice();
		int width = gd.getDisplayMode().getWidth();
		int height = gd.getDisplayMode().getHeight();

		f.setLocation(width / 2 - 400, height / 2 - 300);

		f.setSize(800, 600);
		f.setVisible(true);
	}

	public static void main(String[] args) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		ConnectToDataBase.main(null);
		create();
	}

	private void calc() {
		
	
		try {
	
			String st = "SELECT id FROM customr";
			s.execute(st);
			ResultSet rs = s.getResultSet();

			int max = 0;
			while (rs.next()) {
				String d = rs.getString(1);
				cid = Integer.parseInt(d);
				if (cid > max) {
					max = cid;
				}
			}
			cid = max + 1;
			id.setText(""+cid);
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, "error " + e.getMessage(),
					"error", JOptionPane.ERROR_MESSAGE);
		}
		
	}
}