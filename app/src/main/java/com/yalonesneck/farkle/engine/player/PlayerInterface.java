package com.yalonesneck.farkle.engine.player;

import com.yalonesneck.farkle.engine.DiceContainer;
import com.yalonesneck.farkle.engine.Game;

/**
 * Only classes implementing this interface will be accepted by Game as valid
 * players.
 */
public interface PlayerInterface {

  void addToScore(Integer increment);

  void setScore(Integer newScore);

  String getName();

  Integer getScore();

  /**
   * Handle a roll. The player is supposed to select dice from the roll.
   * 
   * You should definitely make sure that the selected dice are plausible.
   * 
   * @param rolledDice   The dice to select from.
   * @return The choice of the player on how to proceed.
   */
  Game.PlayerRollChoice handleRoll(DiceContainer rolledDice);
}
