package test.divers;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.LogManager;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.sun.javafx.logging.Logger;

import classesNeccessaires.Configuration;
import javafx.beans.Observable;
import main.java.observer.Observateur;


public class TestCarac extends JDialog implements Observable {
	private static final long serialVersionUID = -1975827366674767282L;
	private static final Logger logger = LogManager.getLogger();
	protected ArrayList<Observateur> listObservateur = new ArrayList<Observateur>();
	private JPanel content;
	private JLabel jlSigne;
	private Configuration config;
	private JTextField jtSigne;
	private boolean isOkData;
	private int nbreChiffre, combi;

	public TestCarac(JFrame parent, String title, boolean modal, Configuration config, Observateur obs) {
		super(parent, title, modal);
		this.config =config;
		nbreChiffre = config.getCombiPlusMoins();
		this.addObservateur(obs);
		this.setSize(600, 160);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.initComponent();
		this.setVisible(true);
	}

	public void initComponent() {
		jlSigne = new JLabel("test des carateres + - =");
		jtSigne = new JTextField();
		jtSigne.setPreferredSize(new Dimension(210, 40));
		jtSigne.setFont(new Font("Lucida Console", Font.BOLD, 30));
		//Choix de la combinaison
		JPanel panCombi = new JPanel();
		panCombi.setBackground(Color.white);
		panCombi.setPreferredSize(new Dimension(590, 80));
		panCombi.setBorder(BorderFactory.createTitledBorder("Choix de la combinaison"));
		panCombi.add(jlSigne);
		panCombi.add(jtSigne);
		//-- Ajout de tout les composants au content
		content = new JPanel();
		content.setBackground(Color.white);
		content.add(panCombi);
		JPanel control = new JPanel();
		JButton okBouton = new JButton("OK");
		okBouton.addActionListener(new RunListener());
		JButton cancelBouton = new JButton("Annuler");
		cancelBouton.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent arg0) {setVisible(false);}});
		control.setBackground(Color.white);
		control.add(okBouton);
		control.add(cancelBouton);
		this.getContentPane().add(content, BorderLayout.CENTER);
		this.getContentPane().add(control, BorderLayout.SOUTH);
	}

	/**
	 * 
	 */
	private void acurateData() {
		isOkData = true;
		String comb = jtSigne.getText();

		if (comb.length() != nbreChiffre) {
			JOptionPane.showMessageDialog(null, "Erreur ! \n Veuillez entrer une solution à "+ nbreChiffre +".", "ERREUR", JOptionPane.ERROR_MESSAGE);
			isOkData = false;
			jtSigne.setText("");
		}

		else if (!Pattern.matches("^[+-=]{7}$", comb)) {
			JOptionPane.showMessageDialog(null, "Erreur ! \n Veuillez n'entrer que les signes \"+\", \"-\" ou \"=\".", "ERREUR", JOptionPane.ERROR_MESSAGE);
			isOkData = false;
			jtSigne.setText("");
		}
		else {
			System.out.println(comb);
			logger.debug("la combinaison : "+combi);
			isOkData = true;
		}
	}

	class RunListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			acurateData();
			//- ferme la fenetre lorsque les données sont bonnes
			if (isOkData) {
				setVisible(false);
				updateObservateur();
				logger.debug("indice verifié et envoie au controller");
			}	
		}
	}

	public void addObservateur(Observateur obs) {}
	public void updateObservateur() {}
	public void delObservateur() {}
}