package javacode;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JTextArea;
//Created using Window Builder tool in eclipse
public class About extends JFrame{
	public About() {
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(About.class.getResource("/images/suju.jpg")));
		label.setBounds(-68, 0, 500, 476);
		panel.add(label);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(431, 0, 411, 476);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblAbout = new JLabel("About :");
		lblAbout.setForeground(Color.RED);
		lblAbout.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblAbout.setBounds(10, 31, 77, 32);
		panel_1.add(lblAbout);
		
		JTextArea txtrHelloMyName = new JTextArea();
		txtrHelloMyName.setEditable(false);
		txtrHelloMyName.setFont(new Font("Monospaced", Font.BOLD, 16));
		txtrHelloMyName.setText("Hello, My name is Sujata Regoti.\nI am pursuing B.Tech. degreen in CSE\nbranch at WCE,Sangli(India).Thanks to\nYogesh Patil Sir for helping me to do\nthis project.");
		txtrHelloMyName.setBounds(10, 64, 391, 155);
		panel_1.add(txtrHelloMyName);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setText("Mob. No.:+91 7767887872\nEmail:sujata.regoti@gmail.com");
		textArea.setFont(new Font("Monospaced", Font.BOLD, 16));
		textArea.setBounds(10, 279, 391, 155);
		panel_1.add(textArea);
		
		JLabel lblContact = new JLabel("Contact :");
		lblContact.setForeground(Color.RED);
		lblContact.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblContact.setBounds(10, 246, 77, 32);
		panel_1.add(lblContact);
		
		JLabel lblDateJune = new JLabel("Date: 3 June 2015");
		lblDateJune.setForeground(Color.BLUE);
		lblDateJune.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDateJune.setBounds(236, 11, 149, 32);
		panel_1.add(lblDateJune);
		setResizable(false);
		setSize(855,506);
		setVisible(true);
		
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int w = gd.getDisplayMode().getWidth();
		int h = gd.getDisplayMode().getHeight();

		setLocation(w/2-getWidth()/2,h/2-getHeight()/2);
	}

	
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		new About();

	}
}
