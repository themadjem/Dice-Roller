package com.themadjem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by themadjem on 2/13/2017.
 */
public class ButtonListener implements ActionListener {


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Instructions")){
            Yahtzee.instructions();
        }

        if (e.getActionCommand().equals("Score Total")) {
            ScoreCard.scoreSection(ScoreCard.upperScores1x, 1, ScoreCard.UPPER_SECTION);
            ScoreCard.scoreSection(ScoreCard.upperScores2x, 2, ScoreCard.UPPER_SECTION);
            ScoreCard.scoreSection(ScoreCard.upperScores3x, 3, ScoreCard.UPPER_SECTION);
            ScoreCard.scoreSection(ScoreCard.lowerScores1x, 1, ScoreCard.LOWER_SECTION);
            ScoreCard.scoreSection(ScoreCard.lowerScores2x, 2, ScoreCard.LOWER_SECTION);
            ScoreCard.scoreSection(ScoreCard.lowerScores3x, 3, ScoreCard.LOWER_SECTION);
            ScoreCard.comboScore();

        }
        if (e.getActionCommand().equals("Roll!")) {
            DiceRoller.total = 0;
            int rolls = Integer.parseInt(DiceRoller.rollsLeft.getText());
            if (rolls > 0) {
                for (int i = 0; i < DiceRoller.dice.length; i++) {
                    if (!DiceRoller.dice[i].isLocked()) {
                        DiceRoller.dice[i].rollDie();
                    }
                }
                DiceRoller.rollsLeft.setText(String.valueOf(--rolls));
            }
            DiceRoller.update();

        }
        if (e.getActionCommand().equals("Reset")) {
            DiceRoller.total = 0;
            for (int i = 0; i < DiceRoller.dice.length; i++) {
                DiceRoller.dice[i].reset();
                DiceRoller.update();
                DiceRoller.rollsLeft.setText("3");
            }

        }
    }
}
