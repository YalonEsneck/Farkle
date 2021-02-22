package engine.player;

import engine.DiceContainer;
import engine.Game;

/**
 * Only classes implementing this interface will be accepted by Game as valid
 * players.
 */
public interface PlayerInterface {

  public abstract void addToScore(Integer increment);

  public abstract void setScore(Integer newScore);

  public abstract String getName();

  public abstract Integer getScore();

  /**
   * Handle a roll. The player is supposed to select dice from the roll.
   * 
   * You should definitely make sure that the selected dice are plausible.
   * 
   * @param rolledDice   The dice to select from.
   * @param selectedDice The selected dice.
   * @return The choice of the player on how to proceed.
   */
  public abstract Game.PlayerRollChoice handleRoll(DiceContainer rolledDice);
}
