package com.themadjem;

import javax.swing.*;

/**
 * @version 1.1  
 */
public class Yahtzee {
    private static String howToPlay =
            "     The object of the game is to score points by rolling five dice " +
                    "\nto make certain combinations. The dice can be rolled up to " +
                    "\nthree times in a turn to try to make various scoring combinations. " +
                    "\nA game consists of thirteen rounds. After each round the player " +
                    "\nchooses which scoring category is to be used for that round. " +
                    "\nOnce a category has been used in the game, it cannot be used again. " +
                    "\n\nThe scoring categories have varying point values, some of which " +
                    "\nare fixed values and others where the score depends on " +
                    "\nthe value of the dice. A Yahtzee is five-of-a-kind and scores " +
                    "\n50 points; the highest of any category. The winner is the " +
                    "\nplayer who scores the most points.";
    private static String howToTriple =
            "Triple is played following the standard rules with three exceptions\n\n" +
                    "The first exception is that your scorecard has three rounds active at the" +
                    "\nsame time, allowing you to score anywhere across the three rounds at any time." +
                    "\n\nThe second exception is that your scores for the second round will be" +
                    "\nmultiplied by two, and the scores for the third round will be multiplied" +
                    "\nby three. This naturally means that the targets for the bonus will also" +
                    "\nbe higher (63 for round1, 126 for round 2, 189 for round 3)" +
                    "\n\nThe final exception affects Yahtzee Bonus Points: If all three yahtzee" +
                    "\nboxes have a score of 50 or greater, and you roll a subsequent bonus," +
                    "\nyou will score 100 points multipled by the round that you score in.";
    private static String howtoDice =
            "How to use the Dice Roller:" +
                    "\n   -To start the round, press the \"roll\" button" +
                    "\n   -Click on any dice to \"keep\" them" +
                    "\n   -Click \"roll\" to roll any unlocked dice" +
                    "\n   -Click \"reset\" to return the dice to blank" +
                    "\n          and to reset the number of rolls"+
                    "\n\n Added in Version 1.1:"+
                    "\nYou can now double click the roll button to reroll"+
                    "\nafter the number of rolls left reaches zero"
    private static String howToScore =
            "Enter numbers only into the fields"+
            "\nEnter only normal Yahtzee scores (not multiplied)"+
            "\nClick \"Score Total\" to calculate scores of all columns"+
            "\n\nNOTE: The Score Card will multiply the scores for you!";

    public static void main(String[] args) {

        Thread info;
        String name = JOptionPane.showInputDialog(null, "What is your name?", "Name", JOptionPane.QUESTION_MESSAGE);
        if (name == null || name.isEmpty()) {
            name = "Player 1";
        }
        int q = JOptionPane.showConfirmDialog(null, "Would you like to read the instructions?", "Instructions?", JOptionPane.YES_NO_OPTION);
        switch (q) {
            case -1:
                break;
            case 0:
                instructions();
                break;
            case 1:
                break;
        }


        Thread scoreCard = new Thread(new ScoreCard(name));
        Thread score2 = new Thread(new DiceRoller());

        scoreCard.start();
        score2.start();
    }

    public static void instructions() {
        JOptionPane.showMessageDialog(null, howToPlay, "How to play Yahtzee", JOptionPane.INFORMATION_MESSAGE);
        JOptionPane.showMessageDialog(null, howToTriple, "How to play Triple Yahtzee", JOptionPane.INFORMATION_MESSAGE);
        JOptionPane.showMessageDialog(null, howtoDice, "How to use the Dice Roller", JOptionPane.INFORMATION_MESSAGE);
        JOptionPane.showMessageDialog(null, howToScore, "How to use the Score Card", JOptionPane.INFORMATION_MESSAGE);
    }
}
