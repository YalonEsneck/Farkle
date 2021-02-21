package engine;

public class Shaker extends DiceContainer {
  public Shaker roll() {
    for (Dice pickedDice : this.dice) {
      pickedDice.roll();
    }
/*
    // FIXME DEBUG
    int i = 0;
    for (Dice pickedDice : this.dice) {
      if(i > 4) i = 1;
      pickedDice.setFace(++i);
    }
*/
    return this;
  }
}
