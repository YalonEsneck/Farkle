package com.yalonesneck.farkle.engine.player;

import com.yalonesneck.farkle.ui.UserInterface;

public abstract class BasePlayer implements PlayerInterface {
  protected String name;
  protected Integer score;
  protected UserInterface ui;

  public BasePlayer(String name, UserInterface ui) {
    this.name = name;
    this.score = 0;
    this.ui = ui;
  }

  public void addToScore(Integer increment) {
    this.score += increment;
  }

  public void setScore(Integer newScore) {
    this.score = newScore;
  }

  public String getName() {
    return this.name;
  }

  public Integer getScore() {
    return this.score;
  }
}
