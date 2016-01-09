package javacode;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import de.humatic.dsj.DSCapture;
import de.humatic.dsj.DSFilterInfo;
import de.humatic.dsj.DSFiltergraph;
import de.humatic.dsj.DSJUtils;
import de.humatic.dsj.rc.RendererControls;


public class QRReader extends JPanel implements java.beans.PropertyChangeListener, ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DSCapture graph;
	JLabel msg;
	
	DSFilterInfo[][] dsi;
	public DSCapture getGraph() {
		return graph;
	}
	public void setGraph(DSCapture graph) {
		this.graph = graph;
	}
	Product1 prod;
	Sale1 sale1;
	SaleByCash sale2;
	static javax.swing.JFrame f;
	public QRReader(Product1 p1,Sale1 p2,SaleByCash p3)
	{
		prod=p1;
		sale1=p2;
		sale2=p3;
		createGraph();
	}
	

	public void createGraph() {

		setLayout(new BorderLayout());
		
		setBackground(Color.black);
		msg=new JLabel();
		/*msg=new JLabel("WebCam Device is Busy");
		msg.setForeground(Color.WHITE);
		msg.setFont(new Font("Tahoma",Font.BOLD,16));
		msg.setBounds(getWidth()/2-125,getHeight()/2-20,250,30);
		add(msg);*/
		initDSCapture();
		Timer timer=new Timer(100,this); 
		timer.start();
	}

	public void initDSCapture() {
		
		while(true)
		{
			try
			{
				dsi = DSCapture.queryDevices();
				setLayout(null);
				graph = new DSCapture(DSFiltergraph.DD7, dsi[0][0], false, DSFilterInfo.doNotRender(), this);
				graph.setBounds(0,0,getWidth(),getHeight());
				add(graph.asComponent());
				msg.setVisible(false);
				msg.setBounds(getWidth()/2-125,getHeight()/2-20,250,30);
				break;
			}
			catch(Exception e){
				msg.setVisible(true);
			}
			
		}
	}
	public void propertyChange(java.beans.PropertyChangeEvent pe) {

		switch(DSJUtils.getEventType(pe)) {

		}
		

	}
	
	

	public static void main(String[] args){

		create(null,null,null);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		BufferedImage bi=graph.getImage();
		/*try {
			ImageIO.write(bi, "png", new File("D:\\QR\\dsj1.png"));
		} catch (IOException e1) {
	
			e1.printStackTrace();
		}*/
		List<ZBar.Symbol> ls=ZBar.scan(bi);
		
		
		if(ls==null||ls.size()<=0)
		{
			return;
		}
		
		
		BufferedImage overlay = new BufferedImage(graph.getWidth(), graph.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D g2d = overlay.createGraphics();
		g2d.setBackground(new Color(0, 0, 0, 0));
		g2d.setColor(Color.GREEN);
		
		int y0=0;
		for(ZBar.Symbol s:ls){
			System.out.println(s.data);
			if(prod!=null)
			{
				prod.getTfBarcode().setText(s.data);
				
			}
			if(sale1!=null)
			{
				sale1.getTfBarcode().setText(s.data);
			}
			if(sale2!=null)
			{
				sale2.getTfBarcode().setText(s.data);
			}
			
			g2d.drawPolygon(s.xs, s.ys, s.xs.length);
			g2d.drawString(s.data, 0, y0+=20);
		}
		
		g2d.dispose();
		
		RendererControls rc = graph.getRendererControls();
		rc.setOverlayImage(overlay, null, Color.black, 1);		
	}
	public static void create(Product1 p1,Sale1 p2,SaleByCash p3)
	{
		f=new JFrame();
		//f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		final QRReader r=new QRReader(p1,p2,p3);
		f.add(r);
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int w = gd.getDisplayMode().getWidth();
		int h = gd.getDisplayMode().getHeight();
		int width=400,height=250;
		f.addWindowFocusListener(new WindowAdapter() {
		    public void windowGainedFocus(WindowEvent e) {
		  
		    	r.initDSCapture();
		    	
		    }
		    public void windowLostFocus(WindowEvent e){
		    	
		    	r.graph.dispose();
		    	r.msg.setVisible(true);	
		 
		    }
		    public void windowClosing(WindowEvent e){
		    	r.graph.dispose();
		    	f.setVisible(false);
		    	f.dispose();
		    }
		    
		});
		f.setBounds(w/2-width/2,h/2-height/2,width,height);
		f.setVisible(true);
		

	}
}