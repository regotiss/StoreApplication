package javacode;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.TitledBorder;

import org.jdesktop.swingx.JXMonthView;

class Main extends JPanel {
	
	private static final long serialVersionUID = 1L;

	static JFrame f;
	
	JLabel bak;
	int i;
	JButton btn[],b;
	String str[] = { "Customer", "Supplier", "Company Details",
			"Stock and Purchase", "Sale", "Search" };

	private AbstractAction[] action;
	

	Main() {
		super(new BorderLayout());

		bak = new JLabel(new ImageIcon("/images/logo1.png"));
		//add(bak, BorderLayout.WEST);
		setSize(1366,768);
		//setSize();
		bak.setLayout(null);
		btn = new JButton[str.length];

		action=new AbstractAction[str.length];
		
		action[0]=new AbstractAction() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Customer.create();
			}
		};
		action[1]=new AbstractAction() {
					
					/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						Supplier.create();
					}
		};
		action[2]=new AbstractAction() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				Company.main(null);
			}
		};
		action[3]=new AbstractAction() {
		
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {

				StockAndPurchase.main(null);
			}
		};
		action[4]=new AbstractAction() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				SaleOption.main(null);
			}
		};
		action[5]=new AbstractAction() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				Search.main(null);
			}
		};
		FlowLayout f=new FlowLayout(FlowLayout.LEFT,445,0);
		JPanel p1=new JPanel(f);

		p1.add(bak);
		JXMonthView monthView = new JXMonthView();
		p1.add(monthView);
		add(p1,BorderLayout.NORTH);
		p1.setBackground(new Color(148,72,197));
		
		JPanel p=new JPanel(new GridLayout(2,0,100,100));
		p.setBorder(BorderFactory.createEmptyBorder(50,250,50,250));
		for (i = 0; i < str.length; i++) {

			btn[i] = new JButton(new ImageIcon(
					Main1.class.getResource("/images/" + str[i] + ".png")));
			

			btn[i].setRolloverIcon(new ImageIcon(Main1.class.getResource("/images/" + str[i] + "1.png")));
			btn[i].setText("");

			
			btn[i].setVerticalTextPosition(AbstractButton.BOTTOM);
			btn[i].setBackground(Color.red);
			btn[i].setHorizontalTextPosition(AbstractButton.CENTER);
			String s;
			if(i<2){
				s="<html><strong style=\"font-family: Arial;font-size: 18px;\">"+str[i].charAt(0)+"</strong>"+str[i].substring(1)+"</html>";
				btn[i].setMnemonic(str[i].charAt(0));
			}
			else{
				s="<html>"+str[i].charAt(0)+"<strong style=\"font-family: Arial;font-size: 18px;\">"+str[i].charAt(1)+"</strong>"+str[i].substring(2)+"</html>";
				btn[i].setMnemonic(str[i].charAt(1));
			}
			btn[i].setActionCommand(str[i]);
			btn[i].setBorder(new TitledBorder(null,s,TitledBorder.CENTER, TitledBorder.TOP,new Font("Comic Sans MS", Font.BOLD, 15), Color.white));
			
			btn[i].addFocusListener(new FocusListener() {
				
				@Override
				public void focusLost(FocusEvent e) {
					// TODO Auto-generated method stub
					JButton b=(JButton)e.getSource();
					b.setIcon(new ImageIcon(Main1.class.getResource("/images/" + b.getActionCommand() + ".png")));
				}
				
				@Override
				public void focusGained(FocusEvent e) {
					// TODO Auto-generated method stub
					b=(JButton)e.getSource();
					b.setIcon(new ImageIcon(Main1.class.getResource("/images/" + b.getActionCommand() + "1.png")));
				}
			});		
			btn[i].getInputMap().put(KeyStroke.getKeyStroke("ENTER"),"action");
			btn[i].getActionMap().put("action", action[i]);
			btn[i].addActionListener(action[i]);
			p.add(btn[i]);
		}
		
		
		
		
		p.setBackground(new Color(148,72,197));
		add(p);
	}


	public static void create() {
		
		ConnectToDataBase.main(null);
		f = new JFrame("Welcome");
		f.getContentPane().add(new Main());
		addEscapeListener(f);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getDefaultScreenDevice();
		int w = gd.getDisplayMode().getWidth();
		int h = gd.getDisplayMode().getHeight();
		System.out.println(w+" "+h);
		f.setLocation(0,0);

		f.setSize(w, h);
		f.setVisible(true);
	}
	public static void addEscapeListener(final JFrame j) {
	    ActionListener escListener = new ActionListener() {

	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	
	        	int res=JOptionPane.showConfirmDialog(null, "Are you sure to exit ?");
				if(res!=1&&res!=2){
					j.dispose();
				}
				
	        }
	    };
	    ActionListener saleByCash = new ActionListener() {

	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	
	        	SaleByCash.main(null);
				
	        }
	    };
	    ActionListener purchOrder= new ActionListener() {

	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	
	        	StockOpt.main(null);
				
	        }
	    };
	    
	    ActionListener purchOrderNot = new ActionListener() {

	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	
	        	Purchase3.main(null);
				
	        }
	    };

	    j.getRootPane().registerKeyboardAction(escListener,
	            KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
	            JComponent.WHEN_IN_FOCUSED_WINDOW);
	    
	    j.getRootPane().registerKeyboardAction(saleByCash,
	            KeyStroke.getKeyStroke("typed s"),
	            JComponent.WHEN_IN_FOCUSED_WINDOW);
	    
	    j.getRootPane().registerKeyboardAction(purchOrder
	    		,
	            KeyStroke.getKeyStroke("typed o"),
	            JComponent.WHEN_IN_FOCUSED_WINDOW);
	    
	    j.getRootPane().registerKeyboardAction(purchOrderNot,
	            KeyStroke.getKeyStroke("typed p"),
	            JComponent.WHEN_IN_FOCUSED_WINDOW);

	}
	public static void main(String[] args) {

		setNimbus();
		ConnectToDataBase.main(null);
		JFrame.setDefaultLookAndFeelDecorated(true);
		create();
	}

	public static void setNimbus() {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
					.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(Main1.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(Main1.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(Main1.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(Main1.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		}
		
	}

	
}
