package com.yalonesneck.farkle.ui;

import com.yalonesneck.farkle.engine.player.PlayerInterface;

import java.util.List;

public abstract class Gui implements UserInterface {

  public void announceVictoryConditions(Integer maxScore) {
  }

  public void updateScoreboard(List<PlayerInterface> players) {
  }

  public void announceVictor(PlayerInterface player) {
  }

  public void announceTurn(PlayerInterface player) {
  }

  public void announceCurrentScore(Integer score) {
  }
}
