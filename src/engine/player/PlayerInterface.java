package engine.player;

import engine.Game;
import engine.DiceContainer;

/**
 * Only classes implementing this interface will be accepted by Game as valid
 * players.
 */
public interface PlayerInterface {

  public abstract void addToScore(Integer increment);
  public abstract void setScore(Integer newScore);
  public abstract String getName();
  public abstract Integer getScore();

  // TODO remove. this ain't needed anymore
  public abstract Boolean isHuman();

  /**
   * Handle a roll. The player is supposed to select dice from the roll.
   * @param rolledDice The dice to select from.
   * @param selectedDice The selected dice.
   * @return The choice of the player on how to proceed.
   */
  public abstract Game.PlayerChoice handleRoll (DiceContainer rolledDice, DiceContainer selectedDice);
}
