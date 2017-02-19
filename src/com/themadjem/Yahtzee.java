package com.themadjem;

import javax.swing.*;

public class Yahtzee {

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
                JOptionPane.showMessageDialog(null, "Insert useful info here \n- Not available", "Info", JOptionPane.ERROR_MESSAGE);
                break;
            case 1:
                break;
        }

        Thread scoreCard = new Thread(new ScoreCard(name));
        Thread score2 = new Thread(new DiceRoller());

        scoreCard.start();
        score2.start();
    }
}
