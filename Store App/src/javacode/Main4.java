package javacode;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import java.util.SortedSet;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import org.jdesktop.swingx.JXMonthView;
import org.jdesktop.swingx.event.DateSelectionEvent;
import org.jdesktop.swingx.event.DateSelectionListener;

import reports.Reports;
import reports.SaleWeek;
import reports.SuppWeek;

public class Main4 extends JPanel
{
	
	private static final long serialVersionUID = 1L;
	final Color COLOR_NORMAL = Color.white;
	final Color COLOR_HOVER     = Color.red;
	static JFrame f;
	JLabel bak;
	//JPanel bak;
	JButton btn[],reportbtn[];
	
	JMenuBar menubar;
	private int year;
	private int month;
	private int day;
	
	String str[]={"Customer","Supplier","Company Details","Stock and Purchase","Sale","Search"};
	String reports[]={"Customer","Purchase","Sale"};
	private Statement st;

	

	private static JXMonthView monthView;
	private JPanel pnlReport;
	private JPanel pnlRecord;
	private JButton btnSuppler;
	private JButton btnSearch;
	private JButton btnCompanyDetails;
	private JButton btnStockAndPurchase;
	private JButton btnCustomer_1;
	private JButton btnSales;
	private JButton btnCust;
	private JButton btnPur;
	private JButton btnSal;
	
	public static JXMonthView getMonthView() {
		return monthView;
	}
	public static void setMonthView(JXMonthView monthView) {
		Main4.monthView = monthView;
	}
	Main4()
	{
		setLayout(null);
		menubar=new JMenuBar();
		
		st=ConnectToDataBase.getS();
		bak=new JLabel(new ImageIcon(Main1.class.getResource("/image/wall1.jpg")));
		
		//bak=new JPanel();
		bak.setBounds(-6, 6, 1366, 750);
		add(bak);

		bak.setLayout(null);
		setSize(1366,768);
		
		final JLabel myRef = new JLabel("By Sujata Regoti");
		myRef.setForeground(COLOR_NORMAL);
		myRef.addMouseListener(new MouseAdapter(){
			 
			 public void mouseEntered(MouseEvent me) 
			 {
				 myRef.setForeground(COLOR_HOVER);
			     setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
	 
			public void mouseClicked(java.awt.event.MouseEvent me)
			{

				if(me.getSource() instanceof JLabel)
				{
					myRef.setForeground(COLOR_NORMAL);
					new About();
				}
			}
			public void mouseExited(MouseEvent me)
		    {
				myRef.setForeground(COLOR_NORMAL);  
		        setCursor(Cursor.getDefaultCursor());
		        //System.out.println("Mouse Exited");
		    }
		   
		});
		myRef.setBounds(10,getHeight()-100,300,40);
		myRef.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
		bak.add(myRef);
		
		monthView = new JXMonthView();
		monthView.setDaysOfTheWeekForeground(Color.BLUE);
		monthView.setDayForeground(Calendar.SUNDAY,Color.red);
		monthView.setMonthStringBackground(new Color(148,72,197));
		monthView.setMonthStringForeground(Color.yellow);
		monthView.setSelectionBackground(Color.green);
		monthView.setSelectionDate(Calendar.getInstance().getTime());
		monthView.setTraversable(true);
		monthView.setTodayBackground(Color.orange);
		monthView.setBounds(1121, 17, 224, 182);
		
		
		monthView.getSelectionModel().addDateSelectionListener(new DateSelectionListener() {

				public void valueChanged(DateSelectionEvent e) {
		           SortedSet<Date> s=e.getSelection();
		          Date d=s.first();
		          
		          Calendar cal=Calendar.getInstance();
	        	  cal.setTime(d);
	        	  
		          int year1 = cal.get(Calendar.YEAR);
	        	  int month1 = cal.get(Calendar.MONTH);
	        	  int day1 = cal.get(Calendar.DAY_OF_MONTH);
		          if(monthView.isFlaggedDate(d)&& (year1!=year||month1!=month||day1!=day)){
		        	 
		        	  year=year1;
		        	  month=month1;
		        	  day=day1;
		        	  
		        	  ShowDetails.create(day,(month+1),year);
		          }
		        }
		    });
		monthView.setFlaggedDayForeground(Color.BLUE);
		
		setDates();
		bak.add(monthView);
		
		//int w=0,incr=230,h=250;

				
		AnalogClock an=new AnalogClock();
		an.setBounds(1138,522,200,200);
		bak.add(an);
		
		pnlReport = new JPanel();
		pnlReport.setOpaque(false);
		pnlReport.setBorder(new TitledBorder(null, "Get Report", TitledBorder.LEADING, TitledBorder.TOP,new Font("Comic Sans MS", Font.BOLD, 15), Color.white));
		pnlReport.setBackground(new Color(255, 255, 255));
		pnlReport.setBounds(800, 203, 185, 455);
		bak.add(pnlReport);
		
		btnCust = new JButton("");
		btnCust.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Reports.getAllCust();
			}
		});
		pnlReport.setLayout(new GridLayout(0, 1, 0, 0));
		btnCust.setIcon(new ImageIcon(Main4.class.getResource("/image/cust.png")));
		btnCust.setRolloverIcon(new ImageIcon(Main4.class.getResource("/image/cust1.png")));
		btnCust.setMnemonic('s');
		btnCust.setContentAreaFilled(false);
		btnCust.setBorder(new TitledBorder(null, "Customer Report", TitledBorder.LEADING, TitledBorder.TOP, new Font("Comic Sans MS", Font.BOLD, 15), Color.white));
		pnlReport.add(btnCust);
		
		btnPur = new JButton("");
		btnPur.setRolloverIcon(new ImageIcon(Main4.class.getResource("/image/supp1.png")));
		btnPur.setIcon(new ImageIcon(Main4.class.getResource("/image/supp.png")));
		btnPur.setMnemonic('s');
		btnPur.setContentAreaFilled(false);
		btnPur.setBorder(new TitledBorder(null, "Purchase Report", TitledBorder.LEADING, TitledBorder.TOP, new Font("Comic Sans MS", Font.BOLD, 15), Color.white));
		pnlReport.add(btnPur);
		btnPur.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SuppWeek.create();
				
			}
		});
		
		btnSal = new JButton("");
		btnSal.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				SaleWeek.create();
				
			}
		});
		btnSal.setRolloverIcon(new ImageIcon(Main4.class.getResource("/image/sales1.png")));
		btnSal.setIcon(new ImageIcon(Main4.class.getResource("/image/salesRep.png")));
		btnSal.setMnemonic('s');
		btnSal.setContentAreaFilled(false);
		btnSal.setBorder(new TitledBorder(null, "Sales Report", TitledBorder.LEADING, TitledBorder.TOP, new Font("Comic Sans MS", Font.BOLD, 15), Color.white));
		
		
		pnlReport.add(btnSal);
		
		pnlRecord = new JPanel();
		pnlRecord.setBorder(new TitledBorder(null, "Record", TitledBorder.LEADING, TitledBorder.TOP,new Font("Comic Sans MS", Font.BOLD, 15), Color.white));
		pnlRecord.setBounds(43, 203, 745, 455);
		bak.add(pnlRecord);
		pnlRecord.setLayout(new GridLayout(2, 0, 10, 10));
		pnlRecord.setOpaque(false);
		
		btnCustomer_1 = new JButton("");
		btnCustomer_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Customer.create();
			}
		});
		btnCustomer_1.setRolloverIcon(new ImageIcon(Main4.class.getResource("/image/"+str[0]+"1.png")));
		btnCustomer_1.setContentAreaFilled(false);
		String s="<html><strong style=\"font-family: Arial;font-size: 18px;\">"+"C"+"</strong>"+"ustomer"+"</html>";
		btnCustomer_1.setIcon(new ImageIcon(Main3.class.getResource("/image/Customer.png")));
		btnCustomer_1.setMnemonic('c');
		btnCustomer_1.setBorder(new TitledBorder(null, s, TitledBorder.CENTER, TitledBorder.TOP, new Font("Comic Sans MS", Font.BOLD, 15), Color.white));
		pnlRecord.add(btnCustomer_1);
		
		btnSuppler = new JButton("");
		btnSuppler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Supplier.create();
			}
		});
		btnSuppler.setRolloverIcon(new ImageIcon(Main4.class.getResource("/image/"+str[1]+"1.png")));
		s="<html><strong style=\"font-family: Arial;font-size: 18px;\">"+"S"+"</strong>"+"upplier"+"</html>";
		btnSuppler.setContentAreaFilled(false);
		btnSuppler.setIcon(new ImageIcon(Main3.class.getResource("/image/Supplier.png")));
		btnSuppler.setMnemonic('s');
		btnSuppler.setBorder(new TitledBorder(null, s, TitledBorder.CENTER, TitledBorder.TOP, new Font("Comic Sans MS", Font.BOLD, 15), Color.WHITE));
		pnlRecord.add(btnSuppler);
		
		btnCompanyDetails = new JButton("");
		btnCompanyDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Company.create();
			}
		});
		btnCompanyDetails.setRolloverIcon(new ImageIcon(Main4.class.getResource("/image/"+str[2]+"1.png")));
		btnCompanyDetails.setMnemonic('o');
		s="<html>C<strong style=\"font-family: Arial;font-size: 18px;\">"+"O"+"</strong>"+"mpany Details"+"</html>";
		btnCompanyDetails.setContentAreaFilled(false);
		btnCompanyDetails.setIcon(new ImageIcon(Main3.class.getResource("/image/Company Details.png")));
		btnCompanyDetails.setBorder(new TitledBorder(null, s, TitledBorder.CENTER, TitledBorder.TOP, new Font("Comic Sans MS", Font.BOLD, 15), Color.WHITE));
		pnlRecord.add(btnCompanyDetails);
		
		btnStockAndPurchase = new JButton("");
		btnStockAndPurchase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StockAndPurchase.create();
			}
		});
		btnStockAndPurchase.setMnemonic('t');
		btnStockAndPurchase.setRolloverIcon(new ImageIcon(Main4.class.getResource("/image/"+str[3]+"1.png")));
		s="<html>S<strong style=\"font-family: Arial;font-size: 18px;\">"+"T"+"</strong>"+"ock And Purchases"+"</html>";
		btnStockAndPurchase.setContentAreaFilled(false);
		btnStockAndPurchase.setIcon(new ImageIcon(Main3.class.getResource("/image/Stock and Purchase.png")));
		btnStockAndPurchase.setBorder(new TitledBorder(null,s, TitledBorder.CENTER, TitledBorder.TOP, new Font("Comic Sans MS", Font.BOLD, 15), Color.WHITE));
		pnlRecord.add(btnStockAndPurchase);
		
		btnSales = new JButton("");
		btnSales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SaleOption.create();
			}
		});
		btnSales.setMnemonic('a');
		btnSales.setRolloverIcon(new ImageIcon(Main4.class.getResource("/image/"+str[4]+"1.png")));
		s="<html>S<strong style=\"font-family: Arial;font-size: 18px;\">"+"A"+"</strong>"+"les"+"</html>";
		btnSales.setContentAreaFilled(false);
		btnSales.setIcon(new ImageIcon(Main3.class.getResource("/image/Sale.png")));
		btnSales.setBorder(new TitledBorder(null, s, TitledBorder.CENTER, TitledBorder.TOP, new Font("Comic Sans MS", Font.BOLD, 15), Color.WHITE));
		pnlRecord.add(btnSales);
		
		btnSearch = new JButton("");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Search.create();
			}
		});
		btnSearch.setMnemonic('e');
		btnSearch.setRolloverIcon(new ImageIcon(Main4.class.getResource("/image/"+str[5]+"1.png")));
		s="<html>S<strong style=\"font-family: Arial;font-size: 18px;\">"+"E"+"</strong>"+"earch"+"</html>";
		btnSearch.setContentAreaFilled(false);
		btnSearch.setIcon(new ImageIcon(Main3.class.getResource("/image/Search.png")));
		btnSearch.setBorder(new TitledBorder(null, s, TitledBorder.CENTER, TitledBorder.TOP, new Font("Comic Sans MS", Font.BOLD, 15), Color.WHITE));
		pnlRecord.add(btnSearch);
		
		JLabel btnLogo = new JLabel("");
		btnLogo.setIcon(new ImageIcon(Main4.class.getResource("/image/logo5.png")));
		btnLogo.setBounds(29, 17, 406, 123);
		bak.add(btnLogo);
	//	ClockWindow cw=new ClockWindow();

		
	}
	public void getCustomerReport() {
		Vector<String> v=new Vector<String>();
		v.add("ID");
		v.add("NAME");
		v.add("MOBNO");
		
		//String sql="select id,name,mobno,sum(balance) as Credit, sum(amount) as Debit "+
		//			"from customr natural join sale1 natural join customerdebit where id <> 0"+
		//			" group by (id,name,mobno)";
		
		
	}
	private void setDates() {
		try {
			ResultSet rs=st.executeQuery("select distinct purch_date from purchase1");
			
			while(rs.next()){
				Date d=rs.getDate(1);
				monthView.addFlaggedDates(d);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public void check(Object o) {
		if(o==btn[0]){
			Customer.create();
		}
		else if(o==btn[1]){
			Supplier.create();
		}
		else if(o==btn[2]){
			Company.create();
		}
		else if(o==btn[3]){
			StockAndPurchase.create();
		}
		else if(o==btn[4]){
			SaleOption.create();
		}
		else{
			Search.create();
		}
	}
	public static void create()
	{
		f=new JFrame("Welcome");
		f.setIconImage(new ImageIcon(Main3.class.getResource("/image/icon.png")).getImage());
	
		addEscapeListener(f);
		Main4 home=new Main4();
		f.getContentPane().add(home);
		f.setJMenuBar(home.menubar);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int w = gd.getDisplayMode().getWidth();
		int h = gd.getDisplayMode().getHeight();

		f.setLocation(0,0);
		
		f.setSize(w,h);
		
	
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

	public static void main(String[] args) 
	{
		setNimbus();
		JFrame.setDefaultLookAndFeelDecorated(true);
		ConnectToDataBase.main(null);
		create();
	}
	public static void setNimbus() {

		try 
		{
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) 
			{
                if ("Nimbus".equals(info.getName())) 
				{
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } 
		catch (ClassNotFoundException ex) 
		{
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
		
	}
}
