package engine.player;

import output.Output;

public abstract class BasePlayer implements PlayerInterface {
  protected String name;
  protected Integer score;
  protected Output output;

  public BasePlayer(String name, Output output) {
    this.name = name;
    this.score = 0;
    this.output = output;
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
