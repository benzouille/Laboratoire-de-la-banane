package test.divers;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import main.java.view.game.plusMoins.LigneTableau;

public class FrameWithGridLayout extends JFrame {

    private Container contentPane = this.getContentPane();

    private JPanel jpTop = new JPanel();
    private JPanel jpLeft   = new JPanel();
    private JPanel jpCenter = new JPanel();
    private JPanel jpRight  = new JPanel();
    private JPanel jpBottom = new JPanel();

    private String[] tour = { "#", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };
    private String[] props = { "Proposition", "5555", "2345", "3205", "", "", "", "", "", "", "" };
    private String[] indic = { "Indication", "---=", "+--=", "====", "", "", "", "", "", "", "" };

    private List<JLabel> jlTour = new ArrayList<JLabel>();
    private List<JTextField> jlProps = new ArrayList<JTextField>();
    private List<JLabel> jlIndic = new ArrayList<JLabel>();

    private LigneTableau ligneTableau = null;
    private List<LigneTableau> listLigneTableau = new ArrayList<LigneTableau>();

    /**
     * Constructeur
     */
    public FrameWithGridLayout() {
        this.setTitle("GridLayout Demo");
        this.setSize(1024, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        //this.setResizable(false);

        jpTop.setPreferredSize(new Dimension(800, 200));
        jpLeft.setPreferredSize(new Dimension(300, 200));
        //jpRight.setPreferredSize(new Dimension(400, 200));
        jpRight.setPreferredSize(new Dimension(300, 200));
        jpBottom.setPreferredSize(new Dimension(800, 100));

        jpLeft.setLayout(new GridLayout(11, 1, 10, 5));
        jpCenter.setLayout(new GridLayout(11, 2, 5, 5));

        jpLeft.setBorder(BorderFactory.createEmptyBorder(5,5,5,10)); //- Margin panneau du nombre de tour

        contentPane.add(jpTop, BorderLayout.NORTH);
        contentPane.add(jpLeft, BorderLayout.WEST);
        contentPane.add(jpCenter, BorderLayout.CENTER);
        contentPane.add(jpRight, BorderLayout.EAST);
        contentPane.add(jpBottom, BorderLayout.SOUTH);

        Font font = new Font("Sego UI",Font.PLAIN,24);

        //-- Je rempli le GridLayout
        for (int i=0; i<tour.length; i++) {
            //- La numérotation du nombre de tour
            JLabel jlTour = new JLabel(tour[i]);
            jlTour.setFont(font);
            jlTour.setHorizontalAlignment(JLabel.RIGHT);
            jpLeft.add(jlTour);

            //-- Les champs de saisie
            JTextField jtfProps = new JTextField(props[i]);
            if (i > 0) {
                jtfProps.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
            }
            jtfProps.setFont(font);
            jtfProps.setHorizontalAlignment(JLabel.CENTER);
            jpCenter.add(jtfProps);

            //- Les indications du Maitre du jeux
            JLabel jlIndic= new JLabel(indic[i]);
            jlIndic.setFont(font);
            jlIndic.setHorizontalAlignment(JLabel.CENTER);
            jlIndic.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            jpCenter.add(jlIndic);

            ligneTableau = new LigneTableau(jlTour, jtfProps, jlIndic);
            listLigneTableau.add(ligneTableau);
        }
    }

    
    public static void main(String[] args) {
        FrameWithGridLayout testGridLayout = new FrameWithGridLayout();
        testGridLayout.setVisible(true);
    }
}
