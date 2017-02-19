package com.themadjem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * Created by themadjem on 2/11/2017.
 */
public class DiceButton extends JButton implements ActionListener {
    private boolean isLocked = false;
    ImageIcon I, II, III, IV, V, VI;
    private byte value = 0;
    private Random rand = new Random();

    /**
     * Constructor for a new Dice button
     */
    public DiceButton() {
        I = new ImageIcon(this.getClass().getResource("dice1.png"));
        II = new ImageIcon(this.getClass().getResource("dice2.png"));
        III = new ImageIcon(this.getClass().getResource("dice3.png"));
        IV = new ImageIcon(this.getClass().getResource("dice4.png"));
        V = new ImageIcon(this.getClass().getResource("dice5.png"));
        VI = new ImageIcon(this.getClass().getResource("dice6.png"));
        this.addActionListener(this);

    }

    /**
     * Used by other classes to check if the
     * instance is locked and shouldn't be rolled
     *
     * @return isLocked T/F
     */
    public boolean isLocked() {
        return this.isLocked;
    }

    /**
     * Updates the picture on the die to
     * correspond to its value
     */
    public void update() {
        switch (value) {
            case 0:
                setIcon(null);
                break;
            case 1:
                setIcon(I);
                break;
            case 2:
                setIcon(II);
                break;
            case 3:
                setIcon(III);
                break;
            case 4:
                setIcon(IV);
                break;
            case 5:
                setIcon(V);
                break;
            case 6:
                setIcon(VI);
                break;
            default:
                break;
        }
        DiceRoller.total += value;
    }

    /**
     * "Rolls" the die and updates its picture
     */
    public void rollDie() {
        this.value = (byte) (rand.nextInt(6) + 1);
        this.update();
    }

    /**
     * Listener for the die to be pressed
     * Locks the die
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (value != 0) {
            this.isLocked = !isLocked;
            DiceRoller.update();
        }
    }


    /**
     * Called to reset the die's value and lock status
     */
    public void reset() {
        value = 0;
        isLocked = false;
        update();
    }
}
