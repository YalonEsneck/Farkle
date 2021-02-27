package com.yalonesneck.farkle.engine.player;

import com.yalonesneck.farkle.engine.DiceContainer;
import com.yalonesneck.farkle.engine.Game;
import com.yalonesneck.farkle.engine.Score;
import com.yalonesneck.farkle.ui.UserInterface;

public class HumanPlayer extends BasePlayer {
  public HumanPlayer(String name, UserInterface ui) {
    super(name, ui);
  }

  public Game.PlayerRollChoice handleRoll(DiceContainer rolledDice) {
    DiceContainer selectedDice = new DiceContainer();

    if (Score.getScore(rolledDice.getDice()) > 0) {
      do {
        this.ui.castDice(rolledDice);
        this.ui.selectedDice(selectedDice);

        UserInterface.PlayerAction action = this.ui.promptForAction(rolledDice, selectedDice);

        DiceContainer nonScoringDice = new DiceContainer();
        nonScoringDice.add(Score.getNonScoringDice(selectedDice.getDice()));

        switch (action.getType()) {
          case SELECT:
            selectedDice.add(action.getSelectedDice());
            rolledDice.remove(action.getSelectedDice());
            break;

          case ROLL:
            if (selectedDice.size() < 1) {
              this.ui.noDiceSelected();
            } else if (nonScoringDice.size() > 0) {
              this.ui.unselectNonScoringDice(nonScoringDice);
            } else {
              return new Game.PlayerRollChoice(Game.PlayerRollChoice.Action.ROLL, selectedDice);
            }
            break;

          case PASS:
            if (selectedDice.size() > 0) {
              if (nonScoringDice.size() > 0) {
                this.ui.unselectNonScoringDice(nonScoringDice);
                break;
              } else {
                return new Game.PlayerRollChoice(Game.PlayerRollChoice.Action.PASS, selectedDice);
              }
            } else {
              if (this.ui.confirmYieldingTurn()) {
                return new Game.PlayerRollChoice(Game.PlayerRollChoice.Action.PASS, selectedDice);
              } else {
                break;
              }
            }

          case QUIT:
            return new Game.PlayerRollChoice(Game.PlayerRollChoice.Action.QUIT, selectedDice);

          default:
            throw new RuntimeException("Unexpected player action type: " + action.getType());
        }
      } while (true);
    } else {
      this.ui.castDice(rolledDice);
      return new Game.PlayerRollChoice(Game.PlayerRollChoice.Action.PASS, selectedDice);
    }
  }
}
