package javacode;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class AnalogClock extends JPanel  implements Runnable{

	private static final long serialVersionUID = 1L;
	private static JFrame f;
	private int sec;
	private int width;
	private int height;
	private int hr;
	private int min;
	private Thread t;
	private Image bak;
	private String times;

	/**
	 * Create the panel.
	 */
	//187
	public AnalogClock() {
		min=0;
		setBackground(new Color(54,34,96));
		hr=0;
		bak=new ImageIcon(AnalogClock.class.getResource("/image/clockback.jpg")).getImage();
		sec=0;
		width=200;
		height=200;
		t=new Thread(this);
		t.start();
	}
	public void run()
	{
		while(true)
		{
			try
			{
				Calendar cal=Calendar.getInstance();
				sec=cal.get(Calendar.SECOND);
				min=cal.get(Calendar.MINUTE);
				hr=cal.get(Calendar.HOUR_OF_DAY);
				
				if(hr>12)
					hr-=12;
				SimpleDateFormat form=new SimpleDateFormat("hh:mm:ss",Locale.getDefault());
				Date d=cal.getTime();
				times=form.format(d);
				repaint();
				
				Thread.sleep(1000);

			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(bak,0 , 0, width, height, this);
		
		g.fillOval(95, 95, 5, 5);
		
		
		double angle=Math.toRadians(0.5*(60*hr+min));
		drawWedge(angle,width/5,g);
		g.setColor(Color.black);
		
		drawWedge(2*Math.PI*min/60,width/3,g);
		g.setColor(Color.red);
		
		drawWedge(2*Math.PI*sec/60,width/3,g);
		g.drawString(times,250,250);
	
	}
	public void drawWedge(double angle,int r,Graphics g){
		
		angle-=0.5*Math.PI;
		int x=(int)(r*Math.cos(angle));
		int y=(int)(r*Math.sin(angle));
		g.drawLine(width/2,height/2-3,width/2+x,height/2-3+y);
		g.drawLine(width/2-1,height/2-3,width/2-1+x,height/2-3+y);
		g.drawLine(width/2-2,height/2-3,width/2-2+x,height/2-3+y);
	}
	public void drawHand(double angle,int r,Graphics g)
	{
		angle-=0.5*Math.PI;
		int x=(int)(r*Math.cos(angle*0.1047));
		int y=(int)(r*Math.sin(angle*0.1047));
		
		g.drawLine(width/2,height/2-5,width/2+x,height/2-5+y);
		g.drawLine(width/2+2,height/2-5,width/2+x,height/2-5+y);
		g.drawLine(width/2+1,height/2-5,width/2+x,height/2-5+y);
	}
	public static void main(String args[]){
		create();
	}
	public static void create()
	{
		f=new JFrame("Clock");
		f.setBackground(Color.black);
		AnalogClock an=new AnalogClock();
		an.setOpaque(false);
		
		f.getContentPane().add(an);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		f.setSize(300,300);
		f.setVisible(true);
	}
}
