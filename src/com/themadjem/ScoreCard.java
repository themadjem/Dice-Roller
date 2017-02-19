package com.themadjem;

import javax.swing.*;
import java.awt.*;

/**
 * @author Jesse Maddox
 */
public class ScoreCard implements Runnable {
    static final int UPPER_SECTION = 0; //constant for the Upper Section
    static final int LOWER_SECTION = 1; //constant for the lower section
    private static final int BONUS = 35;
    private static final Exception INVALID_SECTION = new Exception("Invalid Section");

    private JPanel top = new JPanel(); //panel for the top section
    private JLabel title = new JLabel("Upper Section:            Yahtzee             "); //title label
    JButton instr = new JButton("Instructions");

    /**
     * Panel and components for the upper scoring section
     *
     * @see ScoreCard
     */
    private JPanel upper = new JPanel();
    private JLabel[] tripLabels = {new JLabel(" Upper"), new JLabel("Multiplier:"), new JLabel("   1x"), new JLabel("   2x"), new JLabel("   3x")};
    private JLabel[] topLabels = {new JLabel("Section "), new JLabel("Scoring"), new JLabel("_____"), new JLabel("_____"), new JLabel("_____")};
    private JLabel[] upperLabels = {
            new JLabel("Aces"),
            new JLabel("Twos"),
            new JLabel("Threes"),
            new JLabel("Fours"),
            new JLabel("Fives"),
            new JLabel("Sixes"),
            new JLabel("Total"),
            new JLabel("Bonus"),
            new JLabel("Upper Total        ")
    };
    private JLabel[] upperScoringLabels = {
            new JLabel(" All 1's"),
            new JLabel(" All 2's"),
            new JLabel(" All 3's"),
            new JLabel(" All 4's"),
            new JLabel(" All 5's"),
            new JLabel(" All 6's"),
            new JLabel("~~~~~~~~~>"),
            new JLabel("Score 35"),
            new JLabel("~~~~~~~~~>")
    };
    static JTextField[] upperScores1x = new JTextField[9];
    static JTextField[] upperScores2x = new JTextField[9];
    static JTextField[] upperScores3x = new JTextField[9];

    /**
     * Panel and components for the lower scoring section
     * commented lines are a WIP scoring system
     *
     * @see ScoreCard
     */
    private JPanel lower = new JPanel();
    private JLabel[] lowerLabels = {
            new JLabel("3 of a Kind"),
            new JLabel("4 of a kind"),
            new JLabel("Full House"),
            new JLabel("Sm. Straight"),
            new JLabel("Lrg. Straight"),
            new JLabel("YAHTZEE"),
            new JLabel("Chance"),
            new JLabel("Yahtzee Bonus"),
            new JLabel("Lower Total"),
            new JLabel("Upper Total"),
            new JLabel("GRAND TOTALS")
    };
    private JLabel[] lowerScoringLabels = {
            new JLabel("Add all dice"),
            new JLabel("Add all dice"),
            new JLabel("Score 25"),
            new JLabel("Score 30"),
            new JLabel("Score 40"),
            new JLabel("Score 50"),
            new JLabel("Add all dice"),
            new JLabel("Score 100 per"),
            new JLabel("~~~~~~~~~>"),
            new JLabel("~~~~~~~~~>"),
            new JLabel("~~~~~~~~~>")
    };
    //    private String[] yahtzeeBonuses = {"0", "1", "2", "3"};
//    JComboBox jcb1 = new JComboBox(yahtzeeBonuses);
//    JComboBox jcb2 = new JComboBox(yahtzeeBonuses);
//    JComboBox jcb3 = new JComboBox(yahtzeeBonuses);
//    Component[] lowerScores1x = {new JTextField(), new JTextField(), new JCheckBox("30"), new JCheckBox(), new JCheckBox(), new JCheckBox(), new JTextField(), jcb1};
    static JTextField[] results1x = {new JTextField(), new JTextField(), new JTextField()};
    //    Component[] lowerScores2x = {new JTextField(), new JTextField(), new JCheckBox("30"), new JCheckBox(), new JCheckBox(), new JCheckBox(), new JTextField(), jcb2};
    static JTextField[] results2x = {new JTextField(), new JTextField(), new JTextField()};
    //    Component[] lowerScores3x = {new JTextField(), new JTextField(), new JCheckBox("30"), new JCheckBox(), new JCheckBox(), new JCheckBox(), new JTextField(), jcb3};
    static JTextField[] results3x = {new JTextField(), new JTextField(), new JTextField()};

    static JTextField[] lowerScores1x = {new JTextField(), new JTextField(), new JTextField(), new JTextField(), new JTextField(), new JTextField(), new JTextField(), new JTextField()};
    static JTextField[] lowerScores2x = {new JTextField(), new JTextField(), new JTextField(), new JTextField(), new JTextField(), new JTextField(), new JTextField(), new JTextField()};
    static JTextField[] lowerScores3x = {new JTextField(), new JTextField(), new JTextField(), new JTextField(), new JTextField(), new JTextField(), new JTextField(), new JTextField()};

    /**
     * Panel and component for the lower section
     * contains the scoring button
     * and a combined total
     */
    JPanel bottom = new JPanel();
    JButton calc = new JButton("Score Total");
    JLabel  totlab = new JLabel("Combined Total: ");
    static JLabel ct = new JLabel("0");

    Container c = new Container(); //Container used for formatting the scoring sections into the center of the frame

    /**
     * Executed when a thread of this class is started
     */
    public void run() {
        System.out.println("Constructing Score Card!");
    }

    /**
     * Constructor of the Score Card
     *
     * @param playerName name of player
     */
    public ScoreCard(String playerName) {
        JFrame f = new JFrame("Name: " + playerName); //creates the frame (window container)
        f.setLayout(new BorderLayout()); //sets the layout of the frame
        f.setSize(350, 655); //sets the dimensions of the frame
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //makes the [x] button close the application
        f.setResizable(false); //makes window not resizable

        top.add(title); //adding the title to the top pane
        top.add(instr);
        instr.addActionListener(new ButtonListener());
        f.add(top, BorderLayout.NORTH); //adds the top pane to the frame in the NORTH section

        c.setLayout(new BorderLayout());
        upper.setLayout(new SpringLayout()); //sets the layout for the upper section pane
        setupUpper(upper); //calls setupUpper to populate the upper section pane
        upper.setBorder(BorderFactory.createBevelBorder(1)); //gives the upper pane a border style
        c.add(upper, BorderLayout.NORTH); //adds the upper pane to the NORTH section of the Container

        lower.setLayout(new SpringLayout()); //sets the layout for the lower section pane
        setupLower(lower); //calls setupLower to populate the lower section pane
        lower.setBorder(BorderFactory.createBevelBorder(1)); //gives the lower pane a border style
        c.add(lower, BorderLayout.SOUTH); //adds the lower pane to the SOUTH section of the Container

        c.add(new JPanel().add(new JLabel("       Lower Section:")), BorderLayout.CENTER);//adds title bar for the lower section
        f.add(c);//adds the Container to the frame in the CENTER

        bottom.setLayout(new BorderLayout());
        bottom.add(calc, BorderLayout.SOUTH); //adds scoring button to the bottom pane
        Container c2 = new Container();
        c2.setLayout(new FlowLayout());
        c2.add(totlab, BorderLayout.SOUTH);
        c2.add(ct, BorderLayout.SOUTH);
        //ct.setEditable(false);
        bottom.add(c2, BorderLayout.NORTH);
        calc.addActionListener(new ButtonListener()); //adds the Action Listener for the button
        f.add(bottom, BorderLayout.SOUTH); // adds the bottom pane to the SOUTH section of the frame
        f.setLocation(0, 200); //sets Score Card below the Dice Roller
        f.setVisible(true); //sets the frame to be visible

    }

    /**
     * Sets up the upper scoring section
     * adds all necessary components and formats using Spring Utilities
     *
     * @param p panel used to contain components
     */
    private void setupUpper(JPanel p) {
        for (JLabel tripLabel : tripLabels) {
            p.add(tripLabel);
        }
        for (JLabel topLabel : topLabels) {
            p.add(topLabel);
        }
        for (int i = 0; i < upperScores1x.length; i++) {
            upperScores1x[i] = new JTextField();
            upperScores2x[i] = new JTextField();
            upperScores3x[i] = new JTextField();

            p.add(upperLabels[i]);
            p.add(upperScoringLabels[i]);
            p.add(upperScores1x[i]);
            p.add(upperScores2x[i]);
            p.add(upperScores3x[i]);
            if (i > 5) {
                upperScores1x[i].setEditable(false);
                upperScores2x[i].setEditable(false);
                upperScores3x[i].setEditable(false);
            }
        }

        SpringUtilities.makeCompactGrid(p, 11, 5,
                3, 3,  //initX, initY
                3, 3); //xPad, yPad
    }

    /**
     * Sets up the lower scoring section
     * adds all necessary components and formats using Spring Utilities
     *
     * @param p panel used to contain components
     */
    private void setupLower(JPanel p) {

        for (int i = 0; i < lowerScores1x.length; i++) {
            p.add(lowerLabels[i]);
            p.add(lowerScoringLabels[i]);
            p.add(lowerScores1x[i]);
            p.add(lowerScores2x[i]);
            p.add(lowerScores3x[i]);
        }

        for (int i = 0; i < results1x.length; i++) {
            p.add(lowerLabels[i + lowerScores1x.length]);
            p.add(lowerScoringLabels[i + lowerScores1x.length]);
            p.add(results1x[i]);
            p.add(results2x[i]);
            p.add(results3x[i]);
            results1x[i].setEditable(false);
            results2x[i].setEditable(false);
            results3x[i].setEditable(false);
        }

        SpringUtilities.makeCompactGrid(p, 11, 5,
                3, 3,  //initX, initY
                3, 3); //xPad, yPad
    }

    /**
     * Used for testing
     *
     * @param args
     */
    @Deprecated
    public static void main(String[] args) {
        new ScoreCard("TEST");
    }

    /**
     * Generic scorer to calculate the score of a given array of JTextFields and a section
     *
     * @param scores     array to be scored
     * @param multiplier multiplier to be applied
     * @param section    section of the scoring (Valid: UPPER_SECTION, LOWER_SECTION)
     */
    static void scoreSection(JTextField[] scores, int multiplier, int section) {
        int tempScore = 0;
        int total = 0;
        String tempString = null;
        String gt = null;
        int looper;

        try {
            if (section == UPPER_SECTION) {
                looper = 6;
            } else if (section == LOWER_SECTION) {
                looper = 8;
            } else {
                throw INVALID_SECTION;
            }

            for (int i = 0; i < looper; i++) {
                if (!getScoresText(scores, i).isEmpty()) {
                    tempScore = getInt(getScoresText(scores, i));
                } else {
                    tempScore = 0;
                }
                total += tempScore;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            if (section == UPPER_SECTION) {
                setScoresText(scores, getString(total * multiplier), 6);
                if (total >= 63) { //checks if eligible for the bonus
                    setScoresText(scores, getString(BONUS * multiplier), 7);
                } else {
                    setScoresText(scores, "0", 7);
                }
                setScoresText(scores, getString((total + getInt(getScoresText(scores, 7))) * multiplier), 8);
            } else if (section == LOWER_SECTION) {
                switch (multiplier) {
                    case 1:
                        tempString = getString((total * multiplier));
                        setScoresText(results1x, tempString, 0);

                        tempString = getScoresText(upperScores1x, 8);
                        setScoresText(results1x, tempString, 1);

                        gt = String.valueOf(getInt(getScoresText(results1x, 0)) + getInt(getScoresText(results1x, 1)));
                        setScoresText(results1x, gt, 2);
                        break;
                    case 2:
                        tempString = getString((total * multiplier));
                        setScoresText(results2x, tempString, 0);

                        tempString = getScoresText(upperScores2x, 8);
                        setScoresText(results2x, tempString, 1);

                        gt = String.valueOf(getInt(getScoresText(results2x, 0)) + getInt(getScoresText(results2x, 1)));
                        setScoresText(results2x, gt, 2);
                        break;
                    case 3:
                        tempString = getString((total * multiplier));
                        setScoresText(results3x, tempString, 0);

                        tempString = getScoresText(upperScores3x, 8);
                        setScoresText(results3x, tempString, 1);

                        gt = String.valueOf(getInt(getScoresText(results3x, 0)) + getInt(getScoresText(results3x, 1)));
                        setScoresText(results3x, gt, 2);
                        break;
                }

            } else {
                throw INVALID_SECTION;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getString(Object o) {
        return String.valueOf(o);
    }

    private static int getInt(String o) {
        int temp;
        try {
            temp = Integer.parseInt(o);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid characters in text field \nPlease only use numbers \n" + e.getLocalizedMessage(), "Error, ", JOptionPane.ERROR_MESSAGE);
            return 0;
        }
        return temp;
    }

    private static void setScoresText(JTextField[] jtf, String text, int index) {
        jtf[index].setText(text);
    }

    private static String getScoresText(JTextField[] jtf, int index) {
        return jtf[index].getText();
    }

    public static void comboScore() {
        int total = 0;
        total += getInt(getScoresText(results1x,  2));
        total += getInt(getScoresText(results2x,  2));
        total += getInt(getScoresText(results3x,  2));
        ct.setText(getString(total));
    }
}
