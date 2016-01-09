package javacode;


import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormatSymbols;
import java.util.Calendar;
import com.toedter.calendar.JCalendar;

class Main2 extends JPanel implements ActionListener
{
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
	static JFrame f;
	JLabel bak;
	JButton btn[];
	JMenuBar menubar;
	Statement st;
	String str[]={"Customer","Supplier","Company Details","Stock and Purchase","Sale","Search"};
	private JCalendar calendar;

	private Component[] component;

	private int offset;

	private JComboBox<?> b;

	private String monthNames[];

	private int pos;
	Main2()
	{
		setLayout(null);
		menubar=new JMenuBar();
		st=ConnectToDataBase.getS();
		bak=new JLabel(new ImageIcon(Main1.class.getResource("/image/wall1.jpg")));
		bak.setBounds(0, 0, 1366, 750);
		add(bak);

		bak.setLayout(null);
		setSize(1366,768);
		
	
		
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
			//btn[i].setBorderPainted(false);
			
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
		
		calendar = new JCalendar();
		calendar.setBorder(UIManager.getBorder("TitledBorder.border"));
		calendar.getDayChooser().setBackground(new Color(204, 153, 255));
		calendar.getYearChooser().getSpinner().setBackground(new Color(204, 153, 255));
		calendar.getDayChooser().getDayPanel().setBackground(new Color(204, 153, 255));
		calendar.getMonthChooser().getComboBox().setBackground(new Color(204, 153, 255));
		calendar.setBounds(950, 17, 390, 217);
		/*calendar.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				

				setAllDates();
			}
		});*/
		b=(JComboBox<?>) calendar.getMonthChooser().getComboBox();

		b.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				setAllDates();
			}
		});
		monthNames=new DateFormatSymbols().getMonths();
		
		//calendar.getMonthChooser().remove(calendar.getMonthChooser().getSpinner());
		
		JSpinner js=(JSpinner)calendar.getMonthChooser().getSpinner();
		js.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				
			
				//setAllDates();
			}
		});
		bak.add(calendar);

		
		
	}
	protected void setAllDates() {
		try
		{

			Calendar cal = Calendar.getInstance();
			cal.setTime(calendar.getDate());
	
			int month = cal.get(Calendar.MONTH)+1;
			final int year = cal.get(Calendar.YEAR);
			
			System.out.println("Date : "+calendar.getDate());
			System.out.println(calendar.getDate());
			System.out.println("month "+month);
			
			int i=0;
			pos=0;
			while(i<monthNames.length){
				String s=monthNames[i];
	        	if(s.equalsIgnoreCase((String) b.getSelectedItem()))
	        	{
	        		pos=i;
	        		break;
	        	}
	        	i++;
	        }
			/*DateFormat d=new SimpleDateFormat("dd/MM/YYYY");
			try {
				Date date=d.parse("01/"+(pos+1)+"/"+year);
				cal.setTime(date);
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			
			pos++;
			
			JPanel jpanel = calendar.getDayChooser().getDayPanel();
			component = jpanel.getComponents();
			cal.set(Calendar.DAY_OF_MONTH,1);
	        offset = cal.get(Calendar.DAY_OF_WEEK) - 1;
	        
	        System.out.println("offset "+offset);
	        
			ResultSet rs=st.executeQuery("select distinct extract(day from purch_date) from purchase1 where extract (month from purch_date)="
										+pos+" and extract (year from purch_date)="+year);
			

			
	        
	        //System.out.println(Arrays.binarySearch(monthNames, ""+b.getSelectedItem()));
	        //System.out.println("offset "+offset);
	        
		//	System.out.println("Pos="+pos);
	      //  System.out.println("month "+calendar.getCalendar().get(Calendar.MONTH));
	        while(rs.next()){
	        	System.out.println(rs.getString(1)+" "+(offset+Integer.parseInt(rs.getString(1))+6));
	    
	        	((JButton)component[offset+Integer.parseInt(rs.getString(1))+6]).setBackground(Color.CYAN);
	        	((JButton)component[offset+Integer.parseInt(rs.getString(1))+6]).addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						ShowDetails.create(Integer.parseInt(((JButton)e.getSource()).getText()),pos,year);
					}
				});
			
	        }
		}
		catch(Exception e)
		{
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
		f.setIconImage(new ImageIcon(Main2.class.getResource("/image/icon.png")).getImage());
	
		addEscapeListener(f);
		Main2 home=new Main2();
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
            java.util.logging.Logger.getLogger(Main2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
		
	}
}
