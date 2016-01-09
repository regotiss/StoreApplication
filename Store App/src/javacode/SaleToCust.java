package javacode;

import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class SaleToCust extends SaleByCash {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JFrame f;
	private static SaleToCust sale1;
	private JTextField tfCash;
	private JTextField tfBalance;
	String id;

	public SaleToCust(String id) {
		super();
		
		this.id=id;
		lblDiscount.setBounds(16, 55, 87, 16);
		tfDisc.setBounds(102, 55, 179, 28);
		lblNetTotal.setBounds(16, 93, 87, 16);
		tfNetTot.setBounds(102, 88, 179, 28);
		
		tfCash = new JTextField();
		tfCash.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				double balance=bill-Double.parseDouble(tfCash.getText());
				tfBalance.setText(""+balance);
			}
		});
		tfCash.setColumns(10);
		tfCash.setBounds(102, 121, 179, 28);
		pnlBill.add(tfCash);
		
		JLabel lblCash = new JLabel("Cash :");
		lblCash.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		lblCash.setBounds(16, 126, 87, 16);
		pnlBill.add(lblCash);
		
		tfBalance = new JTextField();
		tfBalance.setFocusable(false);
		tfBalance.setEditable(false);
		tfBalance.setColumns(10);
		tfBalance.setBounds(102, 154, 179, 28);
		pnlBill.add(tfBalance);
		
		JLabel lblBalance = new JLabel("Balance  :");
		lblBalance.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		lblBalance.setBounds(16, 159, 87, 16);
		pnlBill.add(lblBalance);
		
		btnSave.removeActionListener(action);
		btnSave.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				saveRecord();
			}
			
		});
	}
	protected void saveRecord() {
		int res=JOptionPane.showConfirmDialog(null, "Are You Sure?");
		if(!(res==1||res==2))
		{
			try
			{
				s.execute("insert into sale1 values(" + billno
						+ ",0," + bill+ ",sysdate,"+tfDisc.getText()+","+id+")");
				s.execute("insert into CustomerDebit values("+CustomerPurchase.calc()+","+billno+","+tfCash.getText()+",sysdate)");
				for (int i = 0; i < data.size(); i++) {

					s.execute("insert into cust_purch values("
							+ data.get(i).get(0) + "," + billno
							+ "," + data.get(i).get(3) + ")");

				}
				
				JOptionPane.showMessageDialog(null,"Saved Successfully");
			}
			catch (Exception e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null,
						"error " + e1.getMessage(), "error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		
	}
	public static void main(String[] args) {
		Main1.setNimbus();
		ConnectToDataBase.main(null);
		create("1");
	}
	public static void create(String id) {
		f = new JFrame("Sales To NonRegistered Customer");

		f.setIconImage(new ImageIcon(Main1.class.getResource("/image/sale.png")).getImage());
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getDefaultScreenDevice();
		int w = gd.getDisplayMode().getWidth();
		int h = gd.getDisplayMode().getHeight();
		
		f.setSize(900,600);
		
		f.setLocation(w/2-400, h/2-300);
		sale1=new SaleToCust(id);
		f.getContentPane().add(sale1);
		f.addWindowFocusListener(new WindowAdapter() {
		    public void windowGainedFocus(WindowEvent e) {
		  
		    	if(sale1.pnlbarcode!=null)
		    	sale1.pnlbarcode.initDSCapture();
		    	
		    }
		    public void windowLostFocus(WindowEvent e){
		    	sale1.pnlbarcode.getGraph().dispose();
		    	sale1.pnlbarcode.msg.setVisible(true);
		    }
		    
		});
		
		f.setVisible(true);
	}

}
