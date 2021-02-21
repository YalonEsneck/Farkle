package engine;

import java.util.stream.Collectors;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

public class DiceContainer {
  protected List<Dice> dice = new ArrayList<Dice>();

  public void generateNewDice() {
    for (int i = 0; i < 6; i++) {
      this.dice.add(new Dice());
    }
  }

  public void add(Dice dice) {
    this.dice.add(dice);
  }

  public void add(List<Dice> dice) {
    this.dice.addAll(dice);
  }

  public Dice pop(Integer face) {
    Optional<Dice> diceToRemove = this.dice.stream().filter(element -> element.getFace() == face).findFirst();
    if (diceToRemove.isEmpty()) {
      throw new IllegalArgumentException("Dice with face " + Integer.toString(face) + " not present in container.");
    }
    this.dice.remove(diceToRemove.get());
    return diceToRemove.get();
  }

  public void remove(List<Dice> dice) {
    for (Dice pickedDice : dice) {
      this.dice.remove(pickedDice);
    }
  }

  public List<Dice> getDice() {
    return this.dice;
  }

  public String toString() {
    return this.dice.stream().map(Dice::toString).collect(Collectors.joining(", "));
  }

  public void clear() {
    this.dice.clear();
  }

  public Integer size() {
    return this.dice.size();
  }
}
