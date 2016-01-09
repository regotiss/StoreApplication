package javacode;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

class Company extends JPanel {

	private static final long serialVersionUID = 1L;

	static JFrame f;

	JLabel bak;
	JButton btnRes, btnSave, btnDel, btnEdit;
	static JButton btnPro;
	JTextField id, name, type;
	JLabel l;
	JTable j;
	static Statement s;
	JButton add;
	String sid, str;
	JScrollPane list;
	JPanel pnlC;

	private int cid;

	private JButton btngen;
	static Vector<Vector<String>> vv;
	static Vector<String> v, sv;

	Company() {
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
		
		bak = new JLabel(new ImageIcon(
				Company.class.getResource("/images/bak2.jpg")));
		add(bak);
		setSize(800, 600);
		bak.setLayout(null);
		cid=0;
		l = new JLabel(new ImageIcon(
				Company.class.getResource("/images/com.png")));
		l.setForeground(Color.yellow);
		l.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
		l.setBounds(350, 20, 400, 100);
		bak.add(l);

		JPanel pnlW = new JPanel(null);

		int w = 130, h = 60;
		l = new JLabel("Company ID:");
		// l.setForeground(Color.white);
		l.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		l.setBounds(10, 30, 100, 30);
		pnlW.add(l);

		id = new JTextField();
		id.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		id.setBounds(130, 30, 115, 30);
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
		name.setBounds(w, 10 + h, 150, 30);
		pnlW.add(name);

		l = new JLabel("Product Type:");
		// l.setForeground(Color.white);
		l.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		l.setBounds(10, 170, 150, 30);
		pnlW.add(l);

		type = new JTextField();
		type.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		type.setBounds(w, 170, 150, 30);
		pnlW.add(type);

		add = new JButton("Add");
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				store();
				clear();
			}
		});
		
		addMapToAdd();
		add.setMnemonic('a');
		add.setBounds(w - 20, 220, 80, 30);
		pnlW.add(add);

		btnSave = new JButton("Save");
		btnSave.setMnemonic('s');
		btnSave.setBounds(w - 100, 120, 100, 30);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				save();
			}
		});
		addMapToSave();
		pnlW.add(btnSave);

		btnRes = new JButton("Reset");
		btnRes.setMnemonic('r');
		btnRes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				clear();
			}
		});
		addMapToRes();
		
		btnRes.setBounds(w + 30, 120, 100, 30);
		pnlW.add(btnRes);

		btnPro = new JButton("Products Details");
		btnPro.setMnemonic('p');
		btnPro.setBounds(300, 500, 150, 30);
		btnPro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Product1.create(id.getText());
			}
		});
		addMapToProd();
		
		bak.add(btnPro);

		pnlW.setBorder(BorderFactory.createTitledBorder("Company Details"));
		pnlW.setBounds(10, 150, 300, 300);

		bak.add(pnlW);
		
		btngen = new JButton("");
		btngen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calc();
			}
		});
		addMapToGen();
		
		btngen.setIcon(new ImageIcon(Company.class.getResource("/images/gen.PNG")));
		btngen.setBounds(252, 25, 40, 40);
		pnlW.add(btngen);

		j = compList();
		addMaptoTable(j);
		j.getSelectionModel().addListSelectionListener(new RowListener());
		list = new JScrollPane(j);

		list.setBounds(330, 150, 450, 300);

		bak.add(list);

		btnEdit = new JButton("Edit");
		btnEdit.setMnemonic('e');
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				edit();
			}	
		});
		addMapToEdit();
		
		btnEdit.setBounds(w - 100, 500, 100, 30);
		bak.add(btnEdit);

		btnDel = new JButton("Delete");
		btnDel.setMnemonic('d');
		btnDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				delet();
			}
		});
		addMapToDelete();
		btnDel.setBounds(w + 10, 500, 100, 30);
		bak.add(btnDel);

	}
	

	


	private void addMapToProd() {
		
		btnPro.getInputMap().put(KeyStroke.getKeyStroke("ENTER"),"action");
		btnPro.getActionMap().put("action", new AbstractAction() {
		
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				Product1.create(id.getText());
			
			}
		});
	}





	private void addMapToAdd() {
		add.getInputMap().put(KeyStroke.getKeyStroke("ENTER"),"action");
		add.getActionMap().put("action", new AbstractAction() {
		
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				store();
				clear();
				type.requestFocus();
			}
		});
		
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
				s.execute("update company1 set name='" + name.getText()
						+ "' where id=" + id.getText());
	
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
				s.execute("delete from company1 where id=" + id.getText());
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


	protected void save() {
		
		int res=JOptionPane.showConfirmDialog(null, "Are you sure ?");
		if(res!=1&&res!=2)
		{
			try {
				
				sid = id.getText();
				s.execute("insert into company1 values(" + sid + ",'"
						+ name.getText() + "')");
				JOptionPane.showMessageDialog(f, "Saved Successfully");
				changeTable();
	
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null,
						"error " + e1.getMessage(), "error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
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

	private void addMaptoTable(JTable t) {
		
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

	protected void calc() {
		try {
		
			String st = "SELECT max(id) FROM company1";
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
		}
		
	}


	protected void store() {
		try
		{
			s.execute("insert into prodtype values("+id.getText()+",'"+type.getText()+"')");
			JOptionPane.showMessageDialog(null,"Product Type added");
		}
		catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "error " + e.getMessage(),
					"error", JOptionPane.ERROR_MESSAGE);
		}
		
	}

	private class RowListener implements ListSelectionListener {
		public void valueChanged(ListSelectionEvent event) {
			if (event.getValueIsAdjusting()) {
				return;
			}
			int i = j.getSelectionModel().getLeadSelectionIndex();
			id.setText("" + vv.get(i).get(0));
			name.setText("" + vv.get(i).get(1));
		}
	}

	void clear() {
		id.setText("");
		name.setText("");
		type.setText("");
	}

	public void changeTable() {

		j = compList();
		j.getSelectionModel().addListSelectionListener(new RowListener());
		addMaptoTable(j);
		list.setViewportView(j);
		clear();
	}

	public static JTable compList() {

		JTable box = null;
		try {
			
			s.execute("select * from company1");
			ResultSet rs = s.getResultSet();

			vv = new Vector<Vector<String>>();
			v = new Vector<String>();
			sv = new Vector<String>();

			while (rs.next()) {
				sv.add(rs.getString(1));
				sv.add(rs.getString(2));
				vv.add(new Vector<String>(sv));
				sv.clear();
			}
			v.add("Company ID");
			v.add("Company Name");

			box = new JTable(vv, v);

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "error " + e.getMessage(),
					"error", JOptionPane.ERROR_MESSAGE);
		}
		return box;
	}

	public static void create() {
		f = new JFrame("Company Details");
		f.setIconImage(new ImageIcon(Main1.class.getResource("/image/Company Details.png")).getImage());
		f.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0),"action1");
		f.getRootPane().getActionMap().put("action1", new AbstractAction() {
		
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				f.dispose();
			}
		});
		f.getContentPane().add(new Company());
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
}
