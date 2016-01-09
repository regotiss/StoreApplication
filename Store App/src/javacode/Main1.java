package javacode;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import org.jdesktop.swingx.JXMonthView;
import org.jdesktop.swingx.event.DateSelectionEvent;
import org.jdesktop.swingx.event.DateSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import java.util.SortedSet;

class Main1 extends JPanel implements ActionListener
{
	
	private static final long serialVersionUID = 1L;
	
	static JFrame f;
	JLabel bak;
	JButton btn[];
	JMenuBar menubar;
	private int year;
	private int month;
	private int day;
	
	String str[]={"Customer","Supplier","Company Details","Stock and Purchase","Sale","Search"};

	private Statement st;

	private static JXMonthView monthView;
	public static JXMonthView getMonthView() {
		return monthView;
	}
	public static void setMonthView(JXMonthView monthView) {
		Main1.monthView = monthView;
	}
	Main1()
	{
		setLayout(null);
		menubar=new JMenuBar();
		
		st=ConnectToDataBase.getS();
		bak=new JLabel(new ImageIcon(Main1.class.getResource("/image/wall1.jpg")));
		bak.setBounds(0, 0, 1366, 750);
		add(bak);

		bak.setLayout(null);
		setSize(1366,768);
		
		monthView = new JXMonthView();
		monthView.setDaysOfTheWeekForeground(Color.BLUE);
		monthView.setDayForeground(Calendar.SUNDAY,Color.red);
		monthView.setMonthStringBackground(new Color(148,72,197));
		monthView.setMonthStringForeground(Color.yellow);
		monthView.setSelectionBackground(Color.green);
		monthView.setSelectionDate(Calendar.getInstance().getTime());
		monthView.setTraversable(true);
		monthView.setTodayBackground(Color.orange);
		monthView.setBounds(1100, 20, 224, 176);
		
		
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
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Main1.class.getResource("/image/logo5.png")));
		lblNewLabel.setBounds(10, 0, 400, 150);
		bak.add(lblNewLabel);
		
		int w=150,incr=250,h=200;

		btn=new JButton[6];
		
	
		for(int i=0;i<6;i++)
		{
			try{
			
			ImageIcon icon=new ImageIcon(Main1.class.getResource("/image/"+str[i]+".png"));
			if(icon!=null)
			btn[i]=new JButton(icon);
			btn[i].setRolloverIcon(new ImageIcon(Main1.class.getResource("/image/"+str[i]+"1.png")));
			}catch(Exception e){
				btn[i]=new JButton(str[i]);
				
			}
			if(i<3)
				btn[i].setBounds(w+i*incr,h,200,200);
			else
				btn[i].setBounds(w+(i-3)*incr,h+200,200,200);
			
			btn[i].setVerticalTextPosition(AbstractButton.BOTTOM);
			btn[i].setHorizontalTextPosition(AbstractButton.CENTER);
			btn[i].addActionListener(this);
			btn[i].setOpaque(false);
			btn[i].setContentAreaFilled(false);
		
			
			String s="";
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
			
			btn[i].getInputMap().put(KeyStroke.getKeyStroke("ENTER"),"action");
			btn[i].getActionMap().put("action",new AbstractAction(){

				private static final long serialVersionUID = 1L;

				@Override
				public void actionPerformed(ActionEvent e) {
					
					check(e.getSource());
				}
				
			});
			
			btn[i].addFocusListener(new FocusListener() {
				
				@Override
				public void focusLost(FocusEvent e) {
		
					JButton b=(JButton)e.getSource();
					b.setIcon(new ImageIcon(Main1.class.getResource("/image/" + b.getActionCommand() + ".png")));
				}
				
				@Override
				public void focusGained(FocusEvent e) {
					JButton b=(JButton)e.getSource();
					b.setIcon(new ImageIcon(Main1.class.getResource("/image/" + b.getActionCommand() + "1.png")));
				}
			});		
			bak.add(btn[i]);
		}
		btn[0].requestFocus();
		
		AnalogClock an=new AnalogClock();
		an.setBounds(getWidth()-230,getHeight()-250,200,200);
		bak.add(an);
	//	ClockWindow cw=new ClockWindow();

		
		
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
	public void actionPerformed(ActionEvent e)
	{
		Object o=e.getSource();
		check(o);
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
		f.setIconImage(new ImageIcon(Main1.class.getResource("/image/icon.png")).getImage());
	
		addEscapeListener(f);
		Main1 home=new Main1();
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
