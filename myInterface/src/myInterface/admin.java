package myInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import javax.swing.event.*;
import java.util.*;

public class admin extends JFrame implements ActionListener {
	
	static myDatabase data = new myDatabase("root","toor");
	static int rest;
	
	JList liste;
	
	JLabel lblNouveauId;
	JLabel lblNouveauLogin;
	JTextField txtNouveauLogin;
	JLabel lblNouveauPassword;
	JPasswordField lbNouveauPassword;
	JButton btnAjouter;
	JPanel pnlAjouter;
	
	JLabel lblNouveauService;
	JLabel lblValeur;
	JTextField txtValeur;
	JButton btnChercher;
	JPanel pnlChercher;
	
	public admin(){
		
		construireObjetsInterface();
		construireFenetre();
		
	}
	
	private void construireObjetsInterface(){
		construirePaneauAjouter();
		construirePaneauChercher();
	}
	
	private void construireFenetre(){
		setTitle("Admin ");
		setSize(1400, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		String imgUrl="v2.png";
	    ImageIcon icone = new ImageIcon(imgUrl);
	  
	    //Création de JLable avec un alignement gauche
	    JLabel jlabel = new JLabel(icone);
		
		getContentPane().add(pnlAjouter, BorderLayout.CENTER);
		jlabel.setPreferredSize(new Dimension(442, 600));
		getContentPane().add(jlabel, BorderLayout.WEST);
		getContentPane().add(pnlChercher, BorderLayout.EAST);
	}
	
	private void construirePaneauAjouter(){
		lblNouveauId = new JLabel("Create user");
		lblNouveauId.setBounds(160, 20, 150, 30);
		lblNouveauLogin = new JLabel("login :");
		lblNouveauLogin.setBounds(20, 70, 150, 30);
		txtNouveauLogin = new JTextField();
		txtNouveauLogin.setBounds(190, 70, 150, 30);
		lblNouveauPassword = new JLabel("password :");
		lblNouveauPassword.setBounds(20, 120, 150, 30);
		lbNouveauPassword= new JPasswordField();
		lbNouveauPassword.setBounds(190, 120, 150, 30);
		btnAjouter = new JButton("Ajouter");
		btnAjouter.setBounds(100, 170, 150, 30);
		btnAjouter.addActionListener(this);
		
		pnlAjouter = new JPanel();
		pnlAjouter.setLayout(null);
		pnlAjouter.add(lblNouveauId);
		pnlAjouter.add(lblNouveauLogin);
		pnlAjouter.add(txtNouveauLogin);
		pnlAjouter.add(lblNouveauPassword);
		pnlAjouter.add(lbNouveauPassword);
		pnlAjouter.add(btnAjouter);
	}
	
	private void construirePaneauChercher(){
		lblNouveauService = new JLabel("Add Service");
		lblNouveauService.setBounds(140, 20, 150, 30);
		lblValeur = new JLabel("Type de service : ");
		lblValeur.setBounds(20, 70, 150, 30);
		txtValeur = new JTextField();
		txtValeur.setBounds(170, 70, 150, 30);
		btnChercher = new JButton("Add");
		btnChercher.setBounds(100, 120, 150, 30);
		btnChercher.addActionListener(this);
		
		pnlChercher = new JPanel();
		pnlChercher.setLayout(null);
		pnlChercher.add(lblNouveauService);
		pnlChercher.add(lblValeur);
		pnlChercher.add(txtValeur);
		pnlChercher.add(btnChercher);
		pnlChercher.setPreferredSize(new Dimension(400, 600));
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == btnAjouter){
			traiterAjouter();
		}else{
			traiterChercher();
		}
	}
	
	private void traiterAjouter(){
		String login = txtNouveauLogin.getText();
		String password = new String(lbNouveauPassword.getPassword());
		String req = "insert into login values('"+login+"','"+password+"')";
		try {
			data.connect();
			rest = data.getStatement().executeUpdate(req);
			data.closeConnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void traiterChercher(){
		String service = txtValeur.getText();
		String req = "insert into typeservice values('"+service+"')";
		try {
			data.connect();
			rest = data.getStatement().executeUpdate(req);
			data.closeConnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}


