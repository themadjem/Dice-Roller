package com.themadjem;

import javax.swing.*;
import java.awt.*;

//TODO add dice total
public class DiceRoller implements Runnable {

    JPanel die = new JPanel();
    JPanel but = new JPanel();
    JPanel nor = new JPanel();
    static DiceButton[] dice = new DiceButton[5];
    JButton roll = new JButton("Roll!");
    JButton reset = new JButton("Reset");
    JLabel rollTxt = new JLabel("Rolls Left: ");
    static JTextField rollsLeft = new JTextField("3", 2);
    JLabel dietot = new JLabel("Total of all Dice: ");
    static JTextField dicetot = new JTextField("0", 2);
    static JLabel[] locks = new JLabel[5];
    static int total = 0;

    /**
     * Constructor for the program
     */
    public DiceRoller() {
        JFrame f = new JFrame("Yahtzee");
        nor.setLayout(new GridLayout());
        for (int i = 0; i < locks.length; i++) {
            locks[i] = new JLabel();
            nor.add(locks[i]);
        }
        System.out.println(f.getTitle());
        f.setSize(500, 190);
        f.setResizable(false);
        f.setLayout(new BorderLayout());
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        die.setLayout(new GridLayout());
        for (int i = 0; i < dice.length; i++) {
            dice[i] = new DiceButton();
            die.add(dice[i]);
        }

        but.add(roll);
        but.add(reset);
        but.add(rollTxt);
        but.add(rollsLeft);
        but.add(dietot);
        but.add(dicetot);
        dicetot.setEditable(false);
        rollsLeft.setEditable(false);
        reset.addActionListener(new ButtonListener());
        roll.addActionListener(new ButtonListener());

        f.add(nor, BorderLayout.NORTH);
        f.add(die, BorderLayout.CENTER);
        f.add(but, BorderLayout.SOUTH);
        f.setVisible(true);
        update();


    }


    /**
     * Sets the labels to be Locked/Unlocked
     */
    public static void update() {
        for (int i = 0; i < DiceRoller.locks.length; i++) {
            if (DiceRoller.dice[i].isLocked()) {
                locks[i].setText("Locked");
            } else {
                locks[i].setText("Unlocked");
            }
            dicetot.setText(String.valueOf(total));
        }
    }


    @Override
    public void run() {
        System.out.println("Starting Dice!");
    }

    public static void main(String[] args) {
        new DiceRoller();
    }
}
