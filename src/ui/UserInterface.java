package ui;

import engine.DiceContainer;
import engine.player.PlayerInterface;

import java.util.List;

public interface UserInterface {

  public static class PlayerAction {
    public static enum Type {
      SELECT, UNSELECT, QUIT, PASS, ROLL
    };

    private Type type;
    private DiceContainer selectedDice;

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
  };

  public abstract void announceCurrentScore(Integer score);

  public abstract void announcePlayers(List<PlayerInterface> players);

  public abstract void announceTurn(PlayerInterface player);

  public abstract void announceVictoryConditions(Integer maxScore);

  public abstract void announceVictor(PlayerInterface player);

  public abstract void castDice(DiceContainer dice);

  public abstract Boolean confirmYieldingTurn();

  public abstract void noDiceSelected();

  public abstract void passes(PlayerInterface player);

  public abstract PlayerAction promptForAction(DiceContainer rolledDice, DiceContainer selectedDice);

  public abstract void rollsAgain(PlayerInterface player);

  public abstract void selectedDice(DiceContainer dice);

  public abstract void updateScoreboard(List<PlayerInterface> players);

  public abstract void unselectNonScoringDice(DiceContainer nonScoringDice);
}
