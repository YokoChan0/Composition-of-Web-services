package myInterface;

import javax.imageio.ImageIO;
import javax.swing.*; 
import org.javatuples.Pair;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.Color;

import javax.swing.event.*;
import javax.swing.text.DefaultCaret;

import java.util.*;
import java.util.List;

public class myInterface extends JFrame implements ActionListener {
	
	JList liste;
	
	List<String> list0 = new ArrayList<String>();
	List<String> list1 = new ArrayList<String>();
	List<String> list2 = new ArrayList<String>();
	List<String> list3 = new ArrayList<String>();
	static List<List> myList = new ArrayList<List>();
	List<String> maximaum = new ArrayList<String>();
	static int n = myList.size();
	
	
	
	
	
	static myDatabase data = new myDatabase("root","toor");
	static ResultSet rest = null;
	
	String[] nameService = new String[4],typeService= new String[4],fourni = new String[4]; 
	String[] pri = new String[4],temps = new String[4],dispo = new String[4],fiabi = new String[4];

	JComboBox S0;
	JComboBox F0;
	JComboBox S1;
	JComboBox F1;
	JComboBox S2;
	JComboBox F2;
	JComboBox S3;
	JComboBox F3;
	

	JButton btnAjouter;
	JPanel pnlAjouter;
	
	
	public myInterface(){	
		construireObjetsInterface();
		construireFenetre();	
	}
	
	private void construireObjetsInterface(){
		construirePaneauAjouter();
	}
	
	private void construireFenetre(){
		setTitle("Mon Interface");
		setSize(1400,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		String imgUrl="user.png";
	    ImageIcon icone = new ImageIcon(imgUrl);
	  
	    //Création de JLable avec un alignement gauche
	    JLabel jlabel = new JLabel(icone);
		
		getContentPane().add(pnlAjouter, BorderLayout.CENTER);
		jlabel.setPreferredSize(new Dimension(463, 600));
		getContentPane().add(jlabel, BorderLayout.WEST);
		
	     validate();
	}
	
	private void construirePaneauAjouter(){
		//String[] items = {"Meteo", "Maps", "Taxi", "Hotel"};
		String[] items = new String[10];
		String[] items1 = {"Temps", "prix", "Disponibilite", "Fiabilite"};
		int i = 0;
		String req = "select type from typeservice";
		try {
			data.connect();
			rest = data.getStatement().executeQuery(req);
			 while(rest.next()) {
				 String item = rest.getString(1);
				 items[i] = item;
				 System.out.println("cout est  "+items[i]);
				 i++;	 
			 }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		S0 = new JComboBox(items);
		F0 = new JComboBox(items1);
		S0.setBounds(180, 20, 190, 30);
		F0.setBounds(400, 20, 190, 30);
		S1 = new JComboBox(items);
		F1 = new JComboBox(items1);
		S1.setBounds(180, 70, 190, 30);
		F1.setBounds(400, 70, 190, 30);
		S2 = new JComboBox(items);
		F2 = new JComboBox(items1);
		S2.setBounds(180, 120, 190, 30);
		F2.setBounds(400, 120, 190, 30);
		S3 = new JComboBox(items);
		F3 = new JComboBox(items1);
		S3.setBounds(180, 170, 190, 30);
		F3.setBounds(400, 170, 190, 30);
		
		btnAjouter = new JButton("Chercher");
		btnAjouter.setBounds(300, 220, 180, 30);
		btnAjouter.addActionListener(this);
		
		pnlAjouter = new JPanel();
		pnlAjouter.setLayout(null);
		pnlAjouter.add(btnAjouter);
		pnlAjouter.add(S0);
		pnlAjouter.add(F0);
		pnlAjouter.add(S1);
		pnlAjouter.add(F1);
		pnlAjouter.add(S2);
		pnlAjouter.add(F2);
		pnlAjouter.add(S3);
		pnlAjouter.add(F3);
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == btnAjouter){
			traiterAjouter();
		}
	}
	private void traiterAjouter(){
		//G E T  D A T A  F R O M  U S E R
		list0.add(S0.getSelectedItem().toString());
		list0.add(F0.getSelectedItem().toString());
		myList.add(list0);
		list1.add(S1.getSelectedItem().toString());
		list1.add(F1.getSelectedItem().toString());
		myList.add(list1);
		list2.add(S2.getSelectedItem().toString());
		list2.add(F2.getSelectedItem().toString());
		myList.add(list2);
		list3.add(S3.getSelectedItem().toString());
		list3.add(F3.getSelectedItem().toString());
		myList.add(list3);
		System.out.println(myList);
		maximaum.add("Disponibilite");
		maximaum.add("Fiabilite");

		for(int i=0;i<=myList.size()-1;i++) {
			String service =  (myList.get(i).get(0)).toString();
			String fact =  (myList.get(i).get(1)).toString();
			if(maximaum.contains(fact)){ 
				String req0 = "select max("+fact+") from webservice where typeService = '"+service+"'";
				   try {
					  data.connect();
					  rest = data.getStatement().executeQuery(req0);
					  while(rest.next()) {
						  String num = rest.getString(1);
						  String req = "select nomService, fourni, prix,Temps, Disponibilite, Fiabilite from webservice where typeService = '"+service+"' and "+fact+"="+num+"";
						  rest = data.getStatement().executeQuery(req);
						  while(rest.next()) {
							  String item1 = rest.getString(1);
							  String item2 = rest.getString(2);
							  String item3 = rest.getString(3);
							  String item4 = rest.getString(4);
							  String item5 = rest.getString(5);
							  String item6 = rest.getString(6);
							  nameService[i] = item1;
							  String hi = item1;
							  fourni[i] = item2;
							  pri[i] = item3;
							  temps[i] = item4;
							  dispo[i] = item5;
							  fiabi[i] = item6;
							  typeService[i] = service;
							  System.out.println("name of service is: "+item1);
						  }
						  
					   }
					     
					  rest.close();
					  data.closeConnect();
				   }catch(Exception e){
					  System.out.println(e);
				  }	
			  }	
			 
			else {
				String req0 = "select min("+fact+") as hi from webservice where typeService = '"+service+"'";
				   try {
					  data.connect();
					  rest = data.getStatement().executeQuery(req0);
					  while(rest.next()) {
						  String num = rest.getString(1);
						  String req = "select nomService, fourni, prix,Temps, Disponibilite, Fiabilite from webservice where typeService = '"+service+"' and "+fact+"="+num+"";
						  rest = data.getStatement().executeQuery(req);
						  while(rest.next()) {
							  String item1 = rest.getString(1);
							  String item2 = rest.getString(2);
							  String item3 = rest.getString(3);
							  String item4 = rest.getString(4);
							  String item5 = rest.getString(5);
							  String item6 = rest.getString(6);
							  nameService[i] = item1;
							  fourni[i] = item2;
							  pri[i] = item3;
							  temps[i] = item4;
							  dispo[i] = item5;
							  fiabi[i] = item6;
							  typeService[i] = service;
							  System.out.println("name of service is: "+item1);
						  }		  
					   }
					  rest.close();
					  data.closeConnect();
				   }catch(Exception e){
					  System.out.println(e);
				  }	}		
		 }// } for function  'f o r'
		
		traiterServices(nameService,typeService,fourni,pri,temps,dispo,fiabi);
	}// } for function  a j o u t e r
	
	public static void traiterServices(String[] nameService,String[] typeService,String[] fourni,String[] pri,String[] temps,String[] dispo,String[] fiabi)
    {
		JFrame f = new JFrame("Mes services");
	    f.setSize(1400,1400);
	    f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	    System.out.println(myList.size()-1);
	    ImageIcon image = new ImageIcon("hi.jpg");
	    f.setIconImage(image.getImage());
	    	
	    JPanel pannel = new JPanel();


	    
	    
	    for(int i=0;i<=myList.size()-1;i++) { 	
	        if(typeService[i].equals("Taxi")){
	        	JLabel label = new JLabel(); //JLabel Creation
	            label.setIcon(new ImageIcon("Taxi1.png")); //Sets the image to be displayed as an icon
	            label.setBounds(20, 10, 300, 500);
	            
	        	JLabel lblName = new JLabel("Nom: ");
		    	lblName.setBounds(20, 520, 150, 30);
		    	JLabel lblName1 = new JLabel(nameService[i]);
			    lblName1.setBounds(120, 520, 150, 30);
			    
			    JLabel lblType = new JLabel("Type: ");
			    lblType.setBounds(20, 540, 150, 30);
		    	JLabel lblType1 = new JLabel(typeService[i]);
		    	lblType1.setBounds(120, 540, 150, 30);
		    	
		    	JLabel lblFourni = new JLabel("Fournisseur: ");
		    	lblFourni.setBounds(20, 560, 150, 30);
		    	JLabel lblFourni1 = new JLabel(fourni[i]);
		    	lblFourni1.setBounds(120, 560, 150, 30);
		    	
		    	JLabel lblPrix = new JLabel("Prix: ");
		    	lblPrix.setBounds(20, 580, 150, 30);
		    	JLabel lblPrix1 = new JLabel(pri[i]+" DH");
		    	lblPrix1.setBounds(120, 580, 150, 30);
		    	
		    	JLabel lblTemps = new JLabel("Temps: ");
		    	lblTemps.setBounds(20, 600, 150, 30);
		    	JLabel lblTemps1 = new JLabel(temps[i]+" s");
		    	lblTemps1.setBounds(120, 600, 150, 30);
		    	
		    	JLabel lblDispo = new JLabel("Disponibilite: ");
		    	lblDispo.setBounds(20, 620, 150, 30);
		    	JLabel lblDispo1 = new JLabel(dispo[i]+" %");
		    	lblDispo1.setBounds(120, 620, 150, 30);
		    	
		    	JLabel lblFiab = new JLabel("Fiabilite: ");
		    	lblFiab.setBounds(20, 640, 150, 30);
		    	JLabel lblFiab1 = new JLabel(fiabi[i]+" %");
		    	lblFiab1.setBounds(120, 640, 150, 30);
		    	
		    	pannel.add(label);
		    	pannel.add(lblName);
			    pannel.add(lblName1);
			    pannel.add(lblType);
			    pannel.add(lblType1);
			    pannel.add(lblFourni);
			    pannel.add(lblFourni1);
			    pannel.add(lblPrix);
			    pannel.add(lblPrix1);
			    pannel.add(lblTemps);
			    pannel.add(lblTemps1);
			    pannel.add(lblDispo);
			    pannel.add(lblDispo1);
			    pannel.add(lblFiab);
			    pannel.add(lblFiab1);
		        }// this } is end of  I F 
	        else if(typeService[i].equals("Meteo")){
	        	JLabel label = new JLabel(); //JLabel Creation
	            label.setIcon(new ImageIcon("meteo.png")); //Sets the image to be displayed as an icon
	            //Dimension size = label.getPreferredSize(); //Gets the size of the image
	            label.setBounds(350, 10, 300, 500);
	            
	        	JLabel lblName = new JLabel("Nom: ");
		    	lblName.setBounds(350, 520, 150, 30);
		    	JLabel lblName1 = new JLabel(nameService[i]);
			    lblName1.setBounds(450, 520, 150, 30);
			    
			    JLabel lblType = new JLabel("Type: ");
			    lblType.setBounds(350, 540, 150, 30);
		    	JLabel lblType1 = new JLabel(typeService[i]);
		    	lblType1.setBounds(450, 540, 150, 30);
		    	
		    	JLabel lblFourni = new JLabel("Fournisseur: ");
		    	lblFourni.setBounds(350, 560, 150, 30);
		    	JLabel lblFourni1 = new JLabel(fourni[i]);
		    	lblFourni1.setBounds(450, 560, 150, 30);
		    	
		    	JLabel lblPrix = new JLabel("Prix: ");
		    	lblPrix.setBounds(350, 580, 150, 30);
		    	JLabel lblPrix1 = new JLabel(pri[i]+" DH");
		    	lblPrix1.setBounds(450, 580, 150, 30);
		    	
		    	JLabel lblTemps = new JLabel("Temps: ");
		    	lblTemps.setBounds(350, 600, 150, 30);
		    	JLabel lblTemps1 = new JLabel(temps[i]+" s");
		    	lblTemps1.setBounds(450, 600, 150, 30);
		    	
		    	JLabel lblDispo = new JLabel("Disponibilite: ");
		    	lblDispo.setBounds(350, 620, 150, 30);
		    	JLabel lblDispo1 = new JLabel(dispo[i]+" %");
		    	lblDispo1.setBounds(450, 620, 150, 30);
		    	
		    	JLabel lblFiab = new JLabel("Fiabilite: ");
		    	lblFiab.setBounds(350, 640, 150, 30);
		    	JLabel lblFiab1 = new JLabel(fiabi[i]+" %");
		    	lblFiab1.setBounds(450, 640, 150, 30);
		    	
		    	pannel.add(label);
		    	pannel.add(lblName);
			    pannel.add(lblName1);
			    pannel.add(lblType);
			    pannel.add(lblType1);
			    pannel.add(lblFourni);
			    pannel.add(lblFourni1);
			    pannel.add(lblPrix);
			    pannel.add(lblPrix1);
			    pannel.add(lblTemps);
			    pannel.add(lblTemps1);
			    pannel.add(lblDispo);
			    pannel.add(lblDispo1);
			    pannel.add(lblFiab);
			    pannel.add(lblFiab1);
		        }// this } is end of  I F 
	        
	        else if(typeService[i].equals("Maps")){
	        	JLabel label = new JLabel(); //JLabel Creation
	            label.setIcon(new ImageIcon("maps.png")); //Sets the image to be displayed as an icon
	            //Dimension size = label.getPreferredSize(); //Gets the size of the image
	            label.setBounds(680, 10, 300, 500);
	            
	        	JLabel lblName = new JLabel("Nom: ");
		    	lblName.setBounds(680, 520, 150, 30);
		    	JLabel lblName1 = new JLabel(nameService[i]);
			    lblName1.setBounds(780, 520, 150, 30);
			    
			    JLabel lblType = new JLabel("Type: ");
			    lblType.setBounds(680, 540, 150, 30);
		    	JLabel lblType1 = new JLabel(typeService[i]);
		    	lblType1.setBounds(780, 540, 150, 30);
		    	
		    	JLabel lblFourni = new JLabel("Fournisseur: ");
		    	lblFourni.setBounds(680, 560, 150, 30);
		    	JLabel lblFourni1 = new JLabel(fourni[i]);
		    	lblFourni1.setBounds(780, 560, 150, 30);
		    	
		    	JLabel lblPrix = new JLabel("Prix: ");
		    	lblPrix.setBounds(680, 580, 150, 30);
		    	JLabel lblPrix1 = new JLabel(pri[i]+" DH");
		    	lblPrix1.setBounds(780, 580, 150, 30);
		    	
		    	JLabel lblTemps = new JLabel("Temps: ");
		    	lblTemps.setBounds(680, 600, 150, 30);
		    	JLabel lblTemps1 = new JLabel(temps[i]+" s");
		    	lblTemps1.setBounds(780, 600, 150, 30);
		    	
		    	JLabel lblDispo = new JLabel("Disponibilite: ");
		    	lblDispo.setBounds(680, 620, 150, 30);
		    	JLabel lblDispo1 = new JLabel(dispo[i]+" %");
		    	lblDispo1.setBounds(780, 620, 150, 30);
		    	
		    	JLabel lblFiab = new JLabel("Fiabilite: ");
		    	lblFiab.setBounds(680, 640, 150, 30);
		    	JLabel lblFiab1 = new JLabel(fiabi[i]+" %");
		    	lblFiab1.setBounds(780, 640, 150, 30);
		    	
		    	pannel.add(label);
		    	pannel.add(lblName);
			    pannel.add(lblName1);
			    pannel.add(lblType);
			    pannel.add(lblType1);
			    pannel.add(lblFourni);
			    pannel.add(lblFourni1);
			    pannel.add(lblPrix);
			    pannel.add(lblPrix1);
			    pannel.add(lblTemps);
			    pannel.add(lblTemps1);
			    pannel.add(lblDispo);
			    pannel.add(lblDispo1);
			    pannel.add(lblFiab);
			    pannel.add(lblFiab1);
		        }// this } is end of  I F 
	        else {
	        	JLabel label = new JLabel(); //JLabel Creation
	            label.setIcon(new ImageIcon("Hotel.png")); //Sets the image to be displayed as an icon
	            //Dimension size = label.getPreferredSize(); //Gets the size of the image
	            label.setBounds(1010, 10, 300, 500);
	            
	            //label.getSubimage(50, 50, 500, 500);
	            
	        	JLabel lblName = new JLabel("Nom: ");
		    	lblName.setBounds(1010, 520, 150, 30);
		    	JLabel lblName1 = new JLabel(nameService[i]);
			    lblName1.setBounds(1110, 520, 150, 30);
			    
			    JLabel lblType = new JLabel("Type: ");
			    lblType.setBounds(1010, 540, 150, 30);
		    	JLabel lblType1 = new JLabel(typeService[i]);
		    	lblType1.setBounds(1110, 540, 150, 30);
		    	
		    	JLabel lblFourni = new JLabel("Fournisseur: ");
		    	lblFourni.setBounds(1010, 560, 150, 30);
		    	JLabel lblFourni1 = new JLabel(fourni[i]);
		    	lblFourni1.setBounds(1110, 560, 150, 30);
		    	
		    	JLabel lblPrix = new JLabel("Prix: ");
		    	lblPrix.setBounds(1010, 580, 150, 30);
		    	JLabel lblPrix1 = new JLabel(pri[i]+" DH");
		    	lblPrix1.setBounds(1110, 580, 150, 30);
		    	
		    	JLabel lblTemps = new JLabel("Temps: ");
		    	lblTemps.setBounds(1010, 600, 150, 30);
		    	JLabel lblTemps1 = new JLabel(temps[i]+" s");
		    	lblTemps1.setBounds(1110, 600, 150, 30);
		    	
		    	JLabel lblDispo = new JLabel("Disponibilite: ");
		    	lblDispo.setBounds(1010, 620, 150, 30);
		    	JLabel lblDispo1 = new JLabel(dispo[i]+" %");
		    	lblDispo1.setBounds(1110, 620, 150, 30);
		    	
		    	JLabel lblFiab = new JLabel("Fiabilite: ");
		    	lblFiab.setBounds(1010, 640, 150, 30);
		    	JLabel lblFiab1 = new JLabel(fiabi[i]+" %");
		    	lblFiab1.setBounds(1110, 640, 150, 30);
		    	
		    	pannel.add(label);
		    	pannel.add(lblName);
			    pannel.add(lblName1);
			    pannel.add(lblType);
			    pannel.add(lblType1);
			    pannel.add(lblFourni);
			    pannel.add(lblFourni1);
			    pannel.add(lblPrix);
			    pannel.add(lblPrix1);
			    pannel.add(lblTemps);
			    pannel.add(lblTemps1);
			    pannel.add(lblDispo);
			    pannel.add(lblDispo1);
			    pannel.add(lblFiab);
			    pannel.add(lblFiab1);
		        }// this } is end of  I F 
	        	    
		    
	    }
	    
	    
	    pannel.setLayout(null);
	    f.getContentPane().add(pannel);
	    f.getContentPane().setBackground(Color.WHITE);
	    f.setVisible(true);
	    f.validate();

    }
	
}


