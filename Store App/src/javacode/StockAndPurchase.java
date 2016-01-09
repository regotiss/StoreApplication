package javacode;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.KeyStroke;
import javax.swing.border.TitledBorder;

public class StockAndPurchase extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	protected JRadioButton rdbtnCheckStockAvailable;
	protected JRadioButton rdbtnPurchaseDetails;
	protected JButton cancelButton;
	protected JButton okButton;
	protected ActionListener action;
	static StockAndPurchase dialog;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Main1.setNimbus();
			ConnectToDataBase.main(null);
			create();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void create() {
		dialog= new StockAndPurchase();
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
	}

	/**
	 * Create the dialog.
	 */
	
	public StockAndPurchase() {
		
		addEscapeListener(this);
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getDefaultScreenDevice();
		int w = gd.getDisplayMode().getWidth();
		int h = gd.getDisplayMode().getHeight();
		setBounds(w/2-588/2, h/2-237/2, 493, 237);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new TitledBorder(null, "Choose Option", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		rdbtnCheckStockAvailable = new JRadioButton("Check Stock Available");
		rdbtnCheckStockAvailable.setMnemonic('c');
		addMapToStock();
		
		
		rdbtnCheckStockAvailable.setBounds(141, 63, 178, 18);
		contentPanel.add(rdbtnCheckStockAvailable);
		
		rdbtnPurchaseDetails = new JRadioButton("Enter Purchase Details");
		rdbtnPurchaseDetails.setBounds(300, 63, 157, 18);
		contentPanel.add(rdbtnPurchaseDetails);
		rdbtnPurchaseDetails.setMnemonic('e');
		addMapToPurchase();
		
		ButtonGroup b=new ButtonGroup();
		b.add(rdbtnCheckStockAvailable);
		b.add(rdbtnPurchaseDetails);
		rdbtnCheckStockAvailable.setSelected(true);
		//rdbtnCheckStockAvailable.setSelected(true);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(StockAndPurchase.class.getResource("/images/guest1.gif")));
		label.setBounds(17, 28, 112, 105);
		contentPanel.add(label);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("");
				
				action=new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(rdbtnCheckStockAvailable.isSelected()){
							Stock.main(null);
						}
						else{
							PurchaseMenu.create();
							
						}
						dialog.setVisible(false);
					}
				};
				okButton.addActionListener(action);
				okButton.setIcon(new ImageIcon(StockAndPurchase.class.getResource("/images/continue.png")));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("");
				addMapToCancel();
				cancelButton.setBackground(Color.white);
				cancelButton.setIcon(new ImageIcon(StockAndPurchase.class.getResource("/images/delete-icon1.png")));
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dialog.setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	private void addMapToCancel() {
		cancelButton.getInputMap().put(KeyStroke.getKeyStroke("TAB"), "action");
		cancelButton.getActionMap().put("action", new AbstractAction() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
			
				rdbtnCheckStockAvailable.setSelected(true);
				
			}
		});
	}

	private void addMapToStock() {
		rdbtnCheckStockAvailable.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT,0), "action");
		rdbtnCheckStockAvailable.getActionMap().put("action", new AbstractAction() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
		
			//	rdbtnCheckStockAvailable.setSelected(false);
				rdbtnPurchaseDetails.requestFocus();
				rdbtnPurchaseDetails.setSelected(true);
				
			}
		});
		rdbtnCheckStockAvailable.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB,0), "action1");
		rdbtnCheckStockAvailable.getActionMap().put("action1", new AbstractAction() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
		
			//	rdbtnCheckStockAvailable.setSelected(false);
				rdbtnPurchaseDetails.requestFocus();
				rdbtnPurchaseDetails.setSelected(true);
				
			}
		});
	}
	private void addMapToPurchase() {
		rdbtnPurchaseDetails.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT,0), "action");
		rdbtnPurchaseDetails.getActionMap().put("action", new AbstractAction() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
		
				
				//rdbtnPurchaseDetails.setSelected(false);
				rdbtnCheckStockAvailable.requestFocus();
				rdbtnCheckStockAvailable.setSelected(true);
				
			}
		});
	}
	public static void addEscapeListener(final JDialog j) {
	    ActionListener escListener = new ActionListener() {

	        @Override
	        public void actionPerformed(ActionEvent e) {
	            j.setVisible(false);
	        }
	    };

	    j.getRootPane().registerKeyboardAction(escListener,
	            KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
	            JComponent.WHEN_IN_FOCUSED_WINDOW);

	}

	
}
