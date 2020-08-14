package com.varsha.gallary;

import javax.swing.*;

import com.varsha.gallary.helper.StaticContents;

import java.awt.*; 
import java.awt.event.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List; 

class LoginFrame extends JFrame 
implements ActionListener { 

	// Components of the Form 
	private Container c; 
	private JLabel title; 
	
	private JLabel name; 
	private JTextField tname;

	private JLabel folder; 
	private JTextField tfolder;
	
	private JLabel token; 
	private JTextField ttoken;
	
	private JButton sub; 
	private JLabel response; 


	// constructor, to initialize the components 
	// with default values. 
	public LoginFrame() 
	{ 
		setTitle("Encrypt Decrypt Utility"); 
		setBounds(300, 90, 500, 500);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE); 
		setResizable(false);

		ImageIcon background = new ImageIcon(StaticContents.getValue(StaticContents.IMG_LOCATION));
		Image image = background.getImage();
		Image tmp = image.getScaledInstance(550, 550, Image.SCALE_SMOOTH);
		background = new ImageIcon(tmp);

		JLabel jLabel = new JLabel(background);
		jLabel.setSize(550, 550);
		jLabel.setLocation(0, 30);
		setContentPane(jLabel);

		c = getContentPane(); 
		c.setLayout(null);
		
		title = new JLabel("Encrypt Decrypt"); 
		title.setFont(new Font("Comic Sans MS", Font.PLAIN, 30)); 
		title.setSize(300, 50); 
		title.setLocation(160, 30); 
		c.add(title); 

		name = new JLabel("Username"); 
		name.setFont(new Font("Comic Sans MS", Font.PLAIN, 15)); 
		name.setSize(200, 20); 
		name.setLocation(50, 150); 
		c.add(name); 

		tname = new JTextField(); 
		tname.setFont(new Font("Comic Sans MS", Font.PLAIN, 15)); 
		tname.setSize(300, 25); 
		tname.setLocation(125, 150); 
		c.add(tname);
		
		token = new JLabel("Token"); 
		token.setFont(new Font("Comic Sans MS", Font.PLAIN, 15)); 
		token.setSize(200, 20); 
		token.setLocation(50, 200); 
		c.add(token); 

		ttoken = new JTextField(); 
		ttoken.setFont(new Font("Arial", Font.PLAIN, 15)); 
		ttoken.setSize(300, 25); 
		ttoken.setLocation(125, 200); 
		c.add(ttoken);
		
		
		
		sub = new JButton("Login"); 
		sub.setFont(new Font("Serif", Font.PLAIN, 20)); 
		sub.setSize(150, 30); 
		sub.setLocation(200, 250); 
		sub.addActionListener(this); 
		c.add(sub); 
		
		response = new JLabel();
		response.setFont(new Font("Lucida Sans", Font.PLAIN, 10)); 
		response.setSize(200, 25);
		response.setLocation(50, 300); 
		c.add(response); 
		
		JLabel copyright = new JLabel("© 2020 varshagroups | Securing world");
		copyright.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12)); 
		copyright.setSize(300, 50); 
		copyright.setLocation(150, 350); 
		c.add(copyright);
		
		setVisible(true); 
	} 

	// method actionPerformed() 
	// to get the action performed 
	// by the user and act accordingly 
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == sub) {
			String username = tname.getText();
			String token = ttoken.getText();
			if("superadmin".equals(username) && "Wsxcde@123".equals(token)) {
				MyFrame f = new MyFrame();
				setVisible(false);
			} else if("admin".equals(username) && "Admin@123".equals(token)) {
				MyFrame f = new MyFrame();
				setVisible(false);
			} else {
				response.setText("Invalid credentials");
			}
		}
	} 
} 

public class Login { 

	public static void main(String[] args) throws Exception 
	{ 
		LoginFrame f = new LoginFrame(); 
	} 
}