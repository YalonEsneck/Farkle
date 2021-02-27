package com.yalonesneck.farkle.engine;

public class Shaker extends DiceContainer {
  public Shaker roll() {
    for (Dice pickedDice : this.dice) {
      pickedDice.roll();
    }
    /*
     * // FIXME DEBUG int i = 0; for (Dice pickedDice : this.dice) { if(i > 4) i =
     * 1; pickedDice.setFace(++i); }
     */
    return this;
  }

  public Shaker clone() {
    Shaker clone = new Shaker();
    clone.add(super.clone().getDice());
    return clone;
  }
}
