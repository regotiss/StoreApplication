package javacode;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

class Product1 extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static JFrame f;

	JLabel bak;
	JButton btnRes, btnSave, btnDel, btnEdit, btnRef;
	JTextField id, name, rate, minLimit;

	static JComboBox<String> type;
	JTextArea info;
	JLabel l;
	String sid;
	JTable t;
	static Statement s;
	static Connection con;
	JScrollPane list;
	JPanel pnlC;

	private int cid;

	private JButton btngen;
	static Vector<Vector<String>> vv;
	static Vector<String> v, sv;

	private static Product1 prod;
	private JTextField tfBarcode;

	Product1(final String sid) {
		super(null);
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
		bak = new JLabel(new ImageIcon(Purchase1.class.getResource("/images/bak2.jpg")));
		bak.setLayout(null);
		bak.setBounds(0, 0, getWidth(), getHeight());
		add(bak);

		this.sid = sid;
		l = new JLabel(new ImageIcon(Purchase1.class.getResource("/images/prod.png")));
		l.setForeground(Color.yellow);
		l.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
		l.setBounds(350, 30, 300, 40);
		bak.add(l);

		int w = 130, h = 60;

		t = custList(sid);
		addMapToTable();
		list = new JScrollPane(t);

		list.setBounds(330, 150, 450, 300);

		bak.add(list);

		btnEdit = new JButton("Update");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				edit();
			}
		});
		btnEdit.setBounds(367, 499, 100, 30);
		bak.add(btnEdit);
		addMapToEdit();
		btnDel = new JButton("Delete");

		btnDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delet();
			}
		});
		addMapToDelete();
		btnDel.setBounds(494, 499, 100, 30);
		bak.add(btnDel);

		JPanel pnlW = new JPanel(null);

		l = new JLabel("Product ID:");
		// l.setForeground(Color.white);
		l.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		l.setBounds(10, 30, 100, 30);
		pnlW.add(l);

		int size=27;
		id = new JTextField();
		id.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		id.setBounds(130, 30, 115, 27);
		pnlW.add(id);
		id.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		name.setBounds(130, 70, 150, size);
		pnlW.add(name);
		name.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rate.requestFocus();
			}
		});

		l = new JLabel("Rate:");
		// l.setForeground(Color.white);
		l.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		l.setBounds(10, 45 + h, 100, 30);
		pnlW.add(l);

		rate = new JTextField();
		rate.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		rate.setBounds(130, 105, 150, size);
		pnlW.add(rate);
		rate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				type.requestFocus();
			}
		});

		l = new JLabel("Type:");
		// l.setForeground(Color.white);
		l.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		l.setBounds(10, 20 + 2 * h, 100, 30);
		pnlW.add(l);

		type = new JComboBox<String>(listOfTypes());
		type.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		type.setBounds(130, 140, 150, 22);
		pnlW.add(type);
		type.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				info.requestFocus();
			}
		});
		
		l = new JLabel("Min Limit:");
		// l.setForeground(Color.white);
		l.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		l.setBounds(10, 150 + 2 * h, 100, 30);
		pnlW.add(l);

		minLimit = new JTextField();
		minLimit.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		minLimit.setBounds(130, 150 + 2 * h, 150, size);
		pnlW.add(minLimit);


		l = new JLabel("Info:");
		// l.setForeground(Color.white);
		l.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		l.setBounds(10, 50 + 2 * h, 100, 30);
		pnlW.add(l);

		info = new JTextArea("");
		addMapToInfo();
		
		info.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));

		JScrollPane sc = new JScrollPane(info);
		sc.setBounds(w, 50 + 2 * h, 150, 80);
		pnlW.add(sc);

		btnSave = new JButton("Save");
		btnSave.setBounds(w - 100, 360, 100, 30);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				save();
			}
		});
		addMapToSave();

		pnlW.add(btnSave);

		btnRes = new JButton("Reset");
		addMapToRes();
		
		btnRes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				clear();
			}
		});
		btnRes.setBounds(w + 30, 360, 100, 30);
		pnlW.add(btnRes);

		pnlW.setBorder(BorderFactory.createTitledBorder("Product Details"));
		pnlW.setBounds(10, 150, 300, 410);
		bak.add(pnlW);
		
		btngen = new JButton("");
		btngen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calc();
			}
		});
		
		addMapToGen();
		
		btngen.setIcon(new ImageIcon(Purchase1.class.getResource("/images/gen.PNG")));
		btngen.setBounds(252, 25, 40, 40);
		pnlW.add(btngen);
		
		JLabel lblBarcode = new JLabel("Barcode:");
		lblBarcode.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		lblBarcode.setBounds(10, 308, 72, 30);
		pnlW.add(lblBarcode);
		
		tfBarcode = new JTextField();
		tfBarcode.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		tfBarcode.setBounds(130, 311, 150, 27);
		pnlW.add(tfBarcode);
		
		JButton brcodeScan = new JButton("");
		brcodeScan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				QRReader.create(Product1.this,null,null);
			}
		});
		brcodeScan.setIcon(new ImageIcon(Product1.class.getResource("/images/mzi.cdepzxus1.png")));
		brcodeScan.setBounds(80, 299, 50, 50);
		pnlW.add(brcodeScan);

	}
	
	public JTextField getTfBarcode() {
		return tfBarcode;
	}

	public void setTfBarcode(JTextField tfBarcode) {
		this.tfBarcode = tfBarcode;
	}

	private void addMapToSave() {
		btnSave.getInputMap().put(KeyStroke.getKeyStroke("ENTER"),"action");
		btnSave.getActionMap().put("action", new AbstractAction() {
		
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				save();
				list.requestFocus();
			}
		});
	}
	private void addMapToDelete() {
		btnDel.getInputMap().put(KeyStroke.getKeyStroke("ENTER"),"action");
		btnDel.getActionMap().put("action", new AbstractAction() {
		
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				delet();
				list.requestFocus();
			}
		});
		
	}


	protected void delet() {
		
		int res=JOptionPane.showConfirmDialog(null, "Are you sure ?");
		if(res!=1&&res!=2)
		{
			try {
				s.execute("delete from product1 where pid=" + id.getText());
				JOptionPane.showMessageDialog(f, "Deleted Successfully");
				changeTable();
				
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null,
						"error " + e1.getMessage(), "error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		clear();
	}


	private void addMapToEdit() {
		btnEdit.getInputMap().put(KeyStroke.getKeyStroke("ENTER"),"action");
		btnEdit.getActionMap().put("action", new AbstractAction() {
		
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				edit();
				list.requestFocus();
			}
		});
	}
	protected void edit() {
		
		int res=JOptionPane.showConfirmDialog(null, "Are you sure ?");
		if(res!=1&&res!=2)
		{
			try {
				s.execute("update product1 set pname='" + name.getText()
						+ "' where pid=" + id.getText());
				s.execute("update product1 set ptype='" + type.getSelectedItem()
						+ "' where pid=" + id.getText());
				s.execute("update product1 set prate='" + rate.getText()
						+ "' where pid=" + id.getText());
				s.execute("update product1 set info='" + info.getText()
						+ "' where pid=" + id.getText());
				s.execute("update product1 set minlimit='" + minLimit.getText()
						+ "' where pid=" + id.getText());
				s.execute("update product1 set barcode='" + tfBarcode.getText()
						+ "' where pid=" + id.getText());
				JOptionPane.showMessageDialog(f, "Updated Successfully");
				changeTable();

			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null,
						"error " + e1.getMessage(), "error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		clear();
		
	}

	protected void save() {
		
		int res=JOptionPane.showConfirmDialog(null, "Are you sure ?");
		if(res!=1&&res!=2)
		{
			try {
				
				//System.out.println(id.getText()+" "+name.getText()+" "+sid+" "+rate.getText()+" "
				//+type.getSelectedItem()+" "+info.getText()+" "+minLimit.getText());
				s.execute("insert into product1 values(" + id.getText()
						+ ",'" + name.getText() + "'," + sid + ","
						+ rate.getText() + ",'" + type.getSelectedItem() + "','"
						+ info.getText() +"',"+minLimit.getText()+ ","+tfBarcode.getText()+")");
				s.execute("insert into available1 values(" + id.getText()
						+ ",0)");
				JOptionPane.showMessageDialog(f, "Saved Successfully");
				changeTable();
	
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null,
						"error " + e1.getMessage(), "error",
						JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
	
			}
		}
			
	}

	private void addMapToRes() {
		btnRes.getInputMap().put(KeyStroke.getKeyStroke("ENTER"),"action");
		btnRes.getActionMap().put("action", new AbstractAction() {
		
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				clear();
				id.requestFocus();
			}
		});
		
	}

	private void addMapToInfo() {
		info.getInputMap().put(KeyStroke.getKeyStroke("TAB"),"action");
		info.getActionMap().put("action", new AbstractAction() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
			
				minLimit.requestFocus();
			}
		});
	}

	private void addMapToTable() {
		t.getSelectionModel().addListSelectionListener(new RowListener());
		t.setFillsViewportHeight(true);
		t.setAutoCreateRowSorter(true);
		t.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				
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

	private void addMapToGen() {
		btngen.getInputMap().put(KeyStroke.getKeyStroke("ENTER"),"action");
		btngen.getActionMap().put("action", new AbstractAction() {
		
			private static final long serialVersionUID = 1L;
			
			@Override
			public void actionPerformed(ActionEvent e) {
				calc();
				name.requestFocus();
			}
		});
		
	}

	protected void calc() {
		try {
		
			String st = "SELECT max(pid) FROM product1";
			s.execute(st);
			ResultSet rs = s.getResultSet();


			while (rs.next()) {
				cid = Integer.parseInt( rs.getString(1));
			}
			cid+=1;
			id.setText(""+cid);
		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, "error " + e.getMessage(),
					"error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		
	}

	private Vector<String> listOfTypes() {
		
		Vector<String> v=new Vector<String>();
		try {
			 
			
			s.execute("select ptype from prodtype where id="
					+ sid);
			ResultSet rs = s.getResultSet();

			while (rs.next()) {
					v.add(rs.getString(1));
			}

		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "error " + e1.getMessage(),
					"error", JOptionPane.ERROR_MESSAGE);
			
		}
		return v;
		
	}

	private class RowListener implements ListSelectionListener {
		public void valueChanged(ListSelectionEvent event) {
			if (event.getValueIsAdjusting()) {
				return;
			}
			int i = t.getSelectionModel().getLeadSelectionIndex();
			id.setText("" + vv.get(i).get(0));
			name.setText("" + vv.get(i).get(1));
			rate.setText("" + vv.get(i).get(2));
			info.setText("" + vv.get(i).get(4));
			minLimit.setText(""+vv.get(i).get(5));
			tfBarcode.setText(""+vv.get(i).get(6));

		}
	}

	void clear() {
		id.setText("");
		name.setText("");
		info.setText("");
		rate.setText("");
		minLimit.setText("");
		tfBarcode.setText("");
	}

	public static JTable custList(String sid) {

		JTable sc = null;
		try {
			
			s.execute("select pid,pname,prate,ptype,info,minlimit,barcode from product1 where id="
					+ sid);
			ResultSet rs = s.getResultSet();

			vv = new Vector<Vector<String>>();
			v = new Vector<String>();
			sv = new Vector<String>();
			v.add("id");
			v.add("product Name");
			v.add("rate");
			v.add("type");
			v.add("info");
			v.add("minlimit");
			v.add("barcode");

			while (rs.next()) {
				for (int k = 1; k <= v.size(); k++)
					sv.add("" + rs.getString(k));

				vv.add(new Vector<String>(sv));
				sv.clear();
			}
			

			sc = new JTable(vv, v);

		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "error " + e1.getMessage(),
					"error", JOptionPane.ERROR_MESSAGE);
		}
		return sc;
	}

	void changeTable() {
		list.setVisible(false);
		t = custList(sid);
		t.getSelectionModel().addListSelectionListener(new RowListener());
		list = new JScrollPane(t);
		list.setBounds(330, 150, 450, 300);
		bak.add(list);
		clear();

	}

	public static void create(String id) {
		if (!(id == null || id.equals(""))) {
			f = new JFrame("Product Details Of Company No "+id);
			f.setIconImage(new ImageIcon(Main1.class.getResource("/image/icon.png")).getImage());
			prod=new Product1(id);
			f.getContentPane().add(prod);
			GraphicsDevice gd = GraphicsEnvironment
					.getLocalGraphicsEnvironment().getDefaultScreenDevice();
			int width = gd.getDisplayMode().getWidth();
			int height = gd.getDisplayMode().getHeight();

			f.setLocation(width / 2 - 400, height / 2 - 300);
			f.addWindowListener(new WindowAdapter() {
			    public void windowClosing(WindowEvent e)
			    {
			    	QRReader.f.setVisible(false);
			    	QRReader.f.dispose();
			    	f.setVisible(false);
			    	f.dispose();
			    }
			    
			});
			f.setSize(800, 600);
			f.setVisible(true);
		} else
			JOptionPane.showMessageDialog(null, "Enter Company ID");
	}

	public static void main(String[] args) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		ConnectToDataBase.main(null);
		
		Main1.setNimbus();
		create("1");
	}
}
