package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TestTemporisation extends JFrame{

    //-- Variables d'instance pour l'UI
    private Container contentPane;
    private JPanel jpButton;
    private JButton jbGauche = new JButton("Obtenir le focus ici !"), jbDroite = new JButton("Obtenir le focus ici !");
    private Dimension dimButton = new Dimension(200, 30);


    /**
     * Une classe de test pour attendre x secondes avant de changer les propriétés graphique d'un composant.
     */
    public TestTemporisation () {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Test refresh UI via Thread dans l'EDT");
        this.setSize(800, 600);

        //-- Les boutons et leurs proriétés
        jbGauche.setBorder(BorderFactory.createLineBorder(Color.GREEN, 5, true));
        jbDroite.setBorder(BorderFactory.createLineBorder(Color.RED, 5, true));
        jbGauche.setPreferredSize(dimButton);
        jbDroite.setPreferredSize(dimButton);

        //-- On dispose le tout sur le contentPane : Les boutons dans un panneau au nord du contentPane
        contentPane = this.getContentPane();
        jpButton = new JPanel();
        contentPane.add(jpButton, BorderLayout.NORTH);
        jpButton.add(jbGauche);
        jpButton.add(jbDroite);

        //-- Action derrière le bouton ON
        jbGauche.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jbDroite.setBorder(BorderFactory.createLineBorder(Color.RED, 5, true));
                updateFocus(jbGauche, Color.GREEN);
            }
        });

        //-- Action derrière le bouton OFF
        jbDroite.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jbGauche.setBorder(BorderFactory.createLineBorder(Color.RED, 5, true));
                updateFocus(jbDroite, Color.GREEN);
            }
        });
    }

    /**
     * Mettre à jour le focus du second bouton
     * @param jbTarget
     */
    public static void updateFocus(JButton jbTarget, Color color) {
        new Thread(new Runnable() {
            public void run() {
                sleep(1);
                //-- Modification de notre composant dans l'EDT
                Thread t = new Thread(new Runnable() {
                    public void run() {
                        jbTarget.setBorder(BorderFactory.createLineBorder(color, 5, true));
                    }
                });

                //-- Si on est dans l'EDT, on lance le Thread sinon il est lancé dans l'EDT qui s'en chargera.
                //-- Une fois la main donée à l'EDT, on rend la main au Thread principal et chacun gère ses missions en parallèle
                if (SwingUtilities.isEventDispatchThread())
                    t.start();
                else {
                    SwingUtilities.invokeLater(t);
                }
            }
        }).start();
    }

    /**
     * Lance une temporisation de x secondes.
     */
    private static void sleep(Integer secondes) {
        try {
            Thread.sleep(secondes * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * La méthode de démarrage pour tester cette classe
     * @param args
     */
    public static void main(String[] args) {
        TestTemporisation testTempo = new TestTemporisation();
        testTempo.setVisible(true);
    }

}
