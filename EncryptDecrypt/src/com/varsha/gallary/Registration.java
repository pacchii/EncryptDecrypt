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

class MyFrame extends JFrame 
implements ActionListener { 

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Components of the Form 
	private Container c; 
	private JLabel title; 
	
	private JLabel name; 
	private JTextField tname;

	private JLabel folder; 
	private JTextField tfolder;
	
	private JLabel token; 
	private JTextField ttoken;
	private JLabel operation; 
	private JRadioButton encrypt; 
	private JRadioButton decrypt; 
	private ButtonGroup operagp; 
	private JButton sub; 
	private JLabel response; 


	// constructor, to initialize the components 
	// with default values. 
	public MyFrame() 
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

		name = new JLabel("Location"); 
		name.setFont(new Font("Comic Sans MS", Font.PLAIN, 15)); 
		name.setSize(200, 20); 
		name.setLocation(50, 100); 
		c.add(name); 

		tname = new JTextField(); 
		tname.setFont(new Font("Comic Sans MS", Font.PLAIN, 15)); 
		tname.setSize(275, 25); 
		tname.setLocation(125, 100); 
		c.add(tname);
		
		token = new JLabel("Token"); 
		token.setFont(new Font("Comic Sans MS", Font.PLAIN, 15)); 
		token.setSize(200, 20); 
		token.setLocation(50, 150); 
		c.add(token); 

		ttoken = new JTextField(); 
		ttoken.setFont(new Font("Arial", Font.PLAIN, 15)); 
		ttoken.setSize(275, 25); 
		ttoken.setLocation(125, 150); 
		c.add(ttoken);
		
		
		folder = new JLabel("Key Loc"); 
		folder.setFont(new Font("Comic Sans MS", Font.PLAIN, 15)); 
		folder.setSize(200, 20); 
		folder.setLocation(50, 200); 
		c.add(folder); 

		tfolder = new JTextField(); 
		tfolder.setFont(new Font("Arial", Font.PLAIN, 15)); 
		tfolder.setSize(275, 25); 
		tfolder.setLocation(125, 200); 
		c.add(tfolder);

		operation = new JLabel("Operation"); 
		operation.setFont(new Font("Arial", Font.PLAIN, 15)); 
		operation.setSize(100, 20); 
		operation.setLocation(50, 250); 
		c.add(operation); 

		encrypt = new JRadioButton("Encrypt"); 
		encrypt.setFont(new Font("Arial", Font.PLAIN, 15)); 
		encrypt.setSelected(true); 
		encrypt.setSize(75, 25); 
		encrypt.setLocation(150, 250); 
		c.add(encrypt); 

		decrypt = new JRadioButton("Decrypt"); 
		decrypt.setFont(new Font("Arial", Font.PLAIN, 15)); 
		decrypt.setSelected(false); 
		decrypt.setSize(80, 25); 
		decrypt.setLocation(250, 250); 
		c.add(decrypt); 

		operagp = new ButtonGroup();
		operagp.add(encrypt); 
		operagp.add(decrypt); 

		sub = new JButton("Encrypt / Decrypt"); 
		sub.setFont(new Font("Serif", Font.PLAIN, 20)); 
		sub.setSize(200, 30); 
		sub.setLocation(170, 300); 
		sub.addActionListener(this); 
		c.add(sub); 
		
		response = new JLabel();
		response.setFont(new Font("Lucida Sans", Font.PLAIN, 10)); 
		response.setSize(400, 80);
		response.setLocation(50, 350); 
		c.add(response); 

		JLabel copyright = new JLabel("© 2020 varshagroups | Securing world");
		copyright.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12)); 
		copyright.setSize(300, 50); 
		copyright.setLocation(150, 400); 
		c.add(copyright);
		setVisible(true); 
	} 

	// method actionPerformed() 
	// to get the action performed 
	// by the user and act accordingly 
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == sub) {
			response.setText("Operation Started");
			String flag = "";
			String folderName = tfolder.getText();
			String location = tname.getText();
			String token = ttoken.getText();
			if(encrypt.isSelected())
				flag = "encrypt";
			else 
				flag = "decrypt";
			
			List<String> imagesList = new ArrayList<>();
			
			try {
				Files.walk(Paths.get(location))
				.filter(Files::isRegularFile)
				.forEach((fileName->{
					imagesList.add(fileName.toString());
				}));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			
			if("encrypt".equals(flag)) {
				try {
					ApplicationService.encrypt(imagesList,folderName,location);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else {
				try {
					ApplicationService.decrypt(imagesList,folderName,location);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			response.setText(flag+"ed successfully");
		}  
	} 
}