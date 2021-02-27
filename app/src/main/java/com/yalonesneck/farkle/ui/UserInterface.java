package com.yalonesneck.farkle.ui;

import com.yalonesneck.farkle.engine.DiceContainer;
import com.yalonesneck.farkle.engine.player.PlayerInterface;

import java.util.List;

public interface UserInterface {

  class PlayerAction {
    public enum Type {
      SELECT, UNSELECT, QUIT, PASS, ROLL
    }

    private final Type type;
    private final DiceContainer selectedDice;

    public PlayerAction(Type type) {
      this.type = type;
      this.selectedDice = null;
    }

    public PlayerAction(Type type, DiceContainer selectedDice) {
      this.type = type;
      this.selectedDice = selectedDice;
    }

    public Type getType() {
      return this.type;
    }

    public DiceContainer getSelectedDice() {
      if (this.type != Type.SELECT && this.type != Type.UNSELECT) {
        throw new RuntimeException("Cannot return selected dice for non-selection.");
      }
      return this.selectedDice;
    }
  }

  void announceCurrentScore(Integer score);

  void announcePlayers(List<PlayerInterface> players);

  void announceTurn(PlayerInterface player);

  void announceVictoryConditions(Integer maxScore);

  void announceVictor(PlayerInterface player);

  void castDice(DiceContainer dice);

  Boolean confirmYieldingTurn();

  void noDiceSelected();

  void passes(PlayerInterface player);

  PlayerAction promptForAction(DiceContainer rolledDice, DiceContainer selectedDice);

  void rollsAgain(PlayerInterface player);

  void selectedDice(DiceContainer dice);

  void updateScoreboard(List<PlayerInterface> players);

  void unselectNonScoringDice(DiceContainer nonScoringDice);
}
