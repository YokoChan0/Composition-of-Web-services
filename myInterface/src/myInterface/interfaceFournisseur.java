package myInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

import javax.swing.event.*;
import java.util.*;

public class interfaceFournisseur extends JFrame implements ActionListener {
	
	static myDatabase data = new myDatabase("root","toor");
	static int rest ;
	static ResultSet resultat ;

	JLabel lblNouveauName;
	JTextField txtNouveauName;
	JLabel lblNouveauType;
	JTextField txtNouveauType;
	JLabel lblNouveauFourni;
	JTextField txtNouveauFourni;
	JLabel lblNouveauPrix;
	JTextField txtNouveauPrix;
	JLabel lblNouveauTemps;
	JTextField txtNouveauTemps;
	JLabel lblNouveauDispo;
	JTextField txtNouveauDispo;
	JLabel lblNouveauFiabi;
	JTextField txtNouveauFiabi;
	JButton btnAjouter;
	JPanel pnlAjouter;
	
	JComboBox typeService;
	
	public interfaceFournisseur(){
		
		construireObjetsInterface();	
		construireFenetre();
		
	}
	
	private void construireObjetsInterface(){
		construirePaneauAjouter();
	}
	
	private void construireFenetre(){
		setTitle("Deployer service");
		setSize(1000, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		String imgUrl="fourni.png";
	    ImageIcon icone = new ImageIcon(imgUrl);
	  
	    //Création de JLable avec un alignement gauche
	    JLabel jlabel = new JLabel(icone);
		
		getContentPane().add(pnlAjouter, BorderLayout.CENTER);
		//JScrollPane pnlListe = new JScrollPane(liste);
		jlabel.setPreferredSize(new Dimension(500, 600));
		getContentPane().add(jlabel, BorderLayout.WEST);
		//getContentPane().add(pnlChercher, BorderLayout.EAST);
	}
	
	private void construirePaneauAjouter(){
		String[] items = new String[10];
		int i = 0;
		String req = "select type from typeservice";
		try {
			data.connect();
			resultat = data.getStatement().executeQuery(req);
			 while(resultat.next()) {
				 String item = resultat.getString(1);
				 items[i] = item;
				 System.out.println("cout est  "+items[i]);
				 i++;	 
			 }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		lblNouveauName = new JLabel("Nom de service :");
		lblNouveauName.setBounds(20, 20, 150, 30);
		txtNouveauName = new JTextField();
		txtNouveauName.setBounds(190, 20, 150, 30);
		lblNouveauType = new JLabel("Type de service :");
		lblNouveauType.setBounds(20, 70, 150, 30);
		typeService = new JComboBox(items);
		typeService.setBounds(190, 70, 150, 30);
		txtNouveauType = new JTextField();
		txtNouveauType.setBounds(190, 70, 150, 30);
		lblNouveauFourni = new JLabel("Fournisseur :");
		lblNouveauFourni.setBounds(20, 120, 150, 30);
		txtNouveauFourni = new JTextField();
		txtNouveauFourni.setBounds(190, 120, 150, 30);
		lblNouveauPrix = new JLabel("Prix :");
		lblNouveauPrix.setBounds(20, 170, 150, 30);
		txtNouveauPrix = new JTextField();
		txtNouveauPrix.setBounds(190, 170, 150, 30);
		lblNouveauTemps = new JLabel("Temps de reponse :");
		lblNouveauTemps.setBounds(20, 220, 150, 30);
		txtNouveauTemps = new JTextField();
		txtNouveauTemps.setBounds(190, 220, 150, 30);
		lblNouveauDispo = new JLabel("Disponibilite :");
		lblNouveauDispo.setBounds(20, 270, 150, 30);
		txtNouveauDispo = new JTextField();
		txtNouveauDispo.setBounds(190, 270, 150, 30);
		lblNouveauFiabi = new JLabel("Fiabilite :");
		lblNouveauFiabi.setBounds(20, 320, 150, 30);
		txtNouveauFiabi = new JTextField();
		txtNouveauFiabi.setBounds(190, 320, 150, 30);
		btnAjouter = new JButton("Ajouter");
		btnAjouter.setBounds(110, 370, 150, 30);
		btnAjouter.addActionListener(this);
		
		pnlAjouter = new JPanel();
		pnlAjouter.setLayout(null);
		pnlAjouter.add(lblNouveauName);
		pnlAjouter.add(txtNouveauName);
		pnlAjouter.add(lblNouveauType);
		pnlAjouter.add(typeService);
		//pnlAjouter.add(txtNouveauType);
		pnlAjouter.add(lblNouveauFourni);
		pnlAjouter.add(txtNouveauFourni);
		pnlAjouter.add(lblNouveauPrix);
		pnlAjouter.add(txtNouveauPrix);
		pnlAjouter.add(lblNouveauTemps);
		pnlAjouter.add(txtNouveauTemps);
		pnlAjouter.add(lblNouveauDispo);
		pnlAjouter.add(txtNouveauDispo);
		pnlAjouter.add(lblNouveauFiabi);
		pnlAjouter.add(txtNouveauFiabi);
		pnlAjouter.add(btnAjouter);
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == btnAjouter){
			traiterAjouter();
		}
	}
	
	private void traiterAjouter() {
		String name = txtNouveauName.getText();
		String type = typeService.getSelectedItem().toString();
		String fourni = txtNouveauFourni.getText();
		String prix = txtNouveauPrix.getText();
		String temps = txtNouveauTemps.getText();
		String dispo = txtNouveauDispo.getText();
		String fiabi = txtNouveauFiabi.getText();
		String req ="Insert into webservice values('"+name+"','"+type+"','"+fourni+"','"+prix+"','"+temps+"','"+dispo+"','"+fiabi+"')"; 
			try {
				data.connect();
				rest = data.getStatement().executeUpdate(req);
				data.closeConnect();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

}
