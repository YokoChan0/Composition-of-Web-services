package myInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

import javax.swing.event.*;
import java.util.*;


public class login extends JFrame implements ActionListener {
	
	static myDatabase data = new myDatabase("root","toor");
	static ResultSet rest = null;
	
	
	JLabel lblNouveauLogin;
	JTextField txtNouveauLogin;
	JLabel lblNouveauPassword;
	JTextField txtNouveauPassword;
	JPasswordField lbNouveauPassword;
	JButton btnAjouter;
	JPanel pnlAjouter;
	
	
	public login(){
		
		construireObjetsInterface();
		construireFenetre();
		
	}
	
	private void construireObjetsInterface(){
		construirePaneauAjouter();
	}
	
	private void construireFenetre(){
		setTitle("Login");
		setSize(1000, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		getContentPane().add(pnlAjouter, BorderLayout.CENTER);
	}
	
	private void construirePaneauAjouter(){
		lblNouveauLogin = new JLabel("login :");
		lblNouveauLogin.setBounds(400, 100, 150, 30);
		txtNouveauLogin = new JTextField();
		txtNouveauLogin.setBounds(470, 100, 150, 30);
		lblNouveauPassword = new JLabel("password :");
		lblNouveauPassword.setBounds(400, 150, 150, 30);
		lbNouveauPassword= new JPasswordField();
		lbNouveauPassword.setBounds(470, 150, 150, 30);
		btnAjouter = new JButton("Login");
		btnAjouter.setBounds(470, 200, 120, 30);
		btnAjouter.addActionListener(this);
		
		pnlAjouter = new JPanel();
		pnlAjouter.setLayout(null);
		pnlAjouter.add(lblNouveauLogin);
		pnlAjouter.add(txtNouveauLogin);
		pnlAjouter.add(lblNouveauPassword);
		pnlAjouter.add(lbNouveauPassword);
		pnlAjouter.add(btnAjouter);
	}
	
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == btnAjouter){
			traiterAjouter();
		}
	}
	
	//@SuppressWarnings("unlikely-arg-type")
	private void traiterAjouter(){
		String login = txtNouveauLogin.getText();
		String password = new String(lbNouveauPassword.getPassword());
		String req = "select password from login where login = '"+login+"'";
		try {
			data.connect();
			rest = data.getStatement().executeQuery(req);
			while(rest.next()) {
				String num = rest.getString(1);
				if(login.equals("admin") && password.equals("admin") ) {
					System.out.println("Welcom Admin");
					(new admin()).setVisible(true);
				}
				else if (num.equals(password)){
						if(login.contains("user_")) {
							System.out.println("Welcom client");
							(new myInterface()).setVisible(true);
						}
						else if(login.contains("fourni_")) {
							System.out.println("Welcom Fournisseur");
							(new interfaceFournisseur()).setVisible(true);
						}	
					}
				else {
					System.out.println("login or password incorrect ");
					Component controllingFrame = null;
					JOptionPane.showMessageDialog(controllingFrame,
							"login or password incorrect ",
			                "Error Message",
			                JOptionPane.ERROR_MESSAGE);
				}
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public static void main (String args[]) {
		
		(new login()).setVisible(true);
		
	}
}



