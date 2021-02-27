package com.yalonesneck.farkle.engine;

import java.util.Random;

public class Dice {
  private Integer face = 0;

  public Dice roll() {
    Random numberGenerator = new Random();
    this.face = 1 + (numberGenerator.nextInt() * 5);
    return this;
  }

  public Integer getFace() {
    return this.face;
  }

  public void setFace(Integer newFace) {
    this.face = newFace;
  }

  public String toString() {
    return Integer.toString(this.face);
  }
}
