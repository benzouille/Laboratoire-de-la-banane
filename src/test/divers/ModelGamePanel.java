package test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.text.ParseException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.MaskFormatter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import main.java.model.Configuration;

public class ModelGamePanel extends JPanel {

	//TODO dans le constructeur ajouter un param mode de jeu

	private static final long serialVersionUID = 1L;

	//-- Les logs
	private static final Logger logger = LogManager.getLogger();

	private Configuration config;	

	private JPanel jpInfo, jpGrid, jpProp, jpSolution;
	private JLabel jlTour, jlJoueur, jlSolution;
	private JButton buttOk;
	private JFormattedTextField jfProp;
	private Dimension size = new Dimension(800,1024);
	private Dimension size_info = new Dimension(800, 200);
	private int nbreChiffre, nbretour;
	private String position, modJeu;
	private boolean devMod;

	public ModelGamePanel(String position, Configuration config, String modJeu) {
		this.position = position;
		this.config = config;
		nbretour = config.getTourPlusMoins();
		nbreChiffre = config.getCombiPlusMoins();
		this.setBackground(Color.white);
		this.setMinimumSize(size);
		this.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
		initPanel();
		this.setVisible(true);
	}

	public void initPanel(){

		//-- Panel info
		jpInfo = new JPanel();
		jpInfo.setPreferredSize(size_info);
		jpInfo.setLayout(new BorderLayout());
		jpInfo.setBackground(Color.white);
		jpSolution = new JPanel();
		jpSolution.setBackground(Color.white);

		jlTour = new JLabel("NBR DE TOUR");
		jlTour.setFont(new Font("Lucida Console", Font.BOLD, 30));
		jlTour.setBorder(BorderFactory.createTitledBorder("Nombre de tour restant"));
		jlTour.setPreferredSize(new Dimension(386, 100));
		jlTour.setHorizontalAlignment(JLabel.CENTER);

		//TODO si panel de gauche avec le devMode desactivé mettre en invisible
		jlSolution = new JLabel("1234567890");
		jlSolution.setFont(new Font("Lucida Console", Font.BOLD, 30));
		jlSolution.setBorder(BorderFactory.createTitledBorder("La solution"));
		jlSolution.setPreferredSize(new Dimension(386, 100));
		jlSolution.setHorizontalAlignment(JLabel.CENTER);
		
		
		if (position == "droite")
			jlSolution.setVisible(true);
		else if (position == "gauche" && config.isDevModEnJeu() == true)
			jlSolution.setVisible(true);
		else
			jlSolution.setVisible(false);
		jpSolution.add(jlTour);
		jpSolution.add(jlSolution);

		if (position == "gauche")
			jlJoueur = new JLabel("Humain");
		else
			jlJoueur = new JLabel("Ordinateur");
		
		jlJoueur.setFont(new Font("Lucida Console", Font.BOLD, 30));
		jlJoueur.setBorder(BorderFactory.createTitledBorder("joueur"));
		jlJoueur.setPreferredSize(new Dimension(350, 80));
		jlJoueur.setHorizontalAlignment(JLabel.CENTER);
		jlJoueur.setBackground(Color.white);

		jpInfo.add(jpSolution, BorderLayout.NORTH);
		jpInfo.add(jlJoueur, BorderLayout.SOUTH);

		//-- Panel grid

		jpGrid = new JPanel();
		jpGrid.setPreferredSize(new Dimension(800, 800));
		jpGrid.setBackground(Color.WHITE);
		jpGrid.setLayout(new GridLayout(nbretour, 2, 3, 3));


		//-- Panel proposition/suggestion
		jpProp = new JPanel();
		jpProp.setSize(new Dimension(800, 150));
		jpProp.setBorder(BorderFactory.createTitledBorder("Propostition"));
		jpProp.setBackground(Color.white);
		//jfProp = new JFormattedTextField(NumberFormat.getNumberInstance());
		try{	
			char [] charac = new char[nbreChiffre];
			for (int i = 0; i<nbreChiffre; i++) {
				charac[i] = '#';
			}
			String str = new String(charac);
			  MaskFormatter mask = new MaskFormatter(str);
			  jfProp = new JFormattedTextField(mask);
			}catch(ParseException e){e.printStackTrace();}
		jfProp.setFont(new Font("Lucida Console", Font.BOLD, 30));
		jfProp.setHorizontalAlignment(JLabel.CENTER);
		jfProp.setPreferredSize(new Dimension(550, 50));

		buttOk = new JButton("ok");
		buttOk.setPreferredSize(new Dimension(150, 50));
		buttOk.addActionListener(new OkListener());
		jpProp.add(jfProp);
		jpProp.add(buttOk);


		this.setLayout(new BorderLayout());
		this.add(jpInfo, BorderLayout.NORTH);
		this.add(jpGrid, BorderLayout.CENTER);
		this.add(jpProp, BorderLayout.SOUTH);
	}


	/**
	 * l'action de OkButton, envoi la proposition au controller
	 * @author Ben
	 *
	 */
	class OkListener implements ActionListener{
		public void actionPerformed(ActionEvent x) {

			//TODO faire un accurate data dans le controller je pense
			//verifier que le nombre de chiffre correspond à la longueur de la combinaison
			//verifier l'absence de virugle ou . dans la proposition

			int prop = Integer.parseInt(jfProp.getText());
			String proposition = jfProp.getText();
			jfProp.setText("");

			//controler.send(proposition);
			logger.debug(proposition);

			JPanel b2 = new JPanel();
			b2.setLayout(new BoxLayout(b2, BoxLayout.LINE_AXIS));
			b2.setBackground(Color.WHITE);
			b2.setMinimumSize(new Dimension(790, 80));


			//proposition
			JLabel t1 = new JLabel();
			t1.setPreferredSize(new Dimension(386, 40));
			t1.setFont(new Font("Lucida Console", Font.BOLD, 30));        
			t1.setBorder(BorderFactory.createBevelBorder(0));
			t1.setSize(new Dimension(386, 100));                 
			t1.setHorizontalAlignment(JLabel.CENTER);    
			t1.setBackground(Color.LIGHT_GRAY);
			t1.setText(proposition);

			//indication
			JLabel t2 = new JLabel();
			t2.setPreferredSize(new Dimension(386, 40));
			t2.setFont(new Font("Lucida Console", Font.BOLD, 30));        
			t2.setBorder(BorderFactory.createBevelBorder(0));
			t2.setSize(new Dimension(386, 100));                 
			t2.setHorizontalAlignment(JLabel.CENTER);    
			t2.setBackground(Color.LIGHT_GRAY);
			t2.setText("+ - - + + = - =");
			b2.add(t1);
			b2.add(t2);
			jpGrid.add(b2);
			jpGrid.revalidate();
			System.out.println("la proposition"+prop);
		}
	}

}
