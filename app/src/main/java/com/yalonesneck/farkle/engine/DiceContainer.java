package com.yalonesneck.farkle.engine;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DiceContainer {
  protected List<Dice> dice = new ArrayList<>();

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

  public void add(DiceContainer dice) {
    this.add(dice.getDice());
  }

  /**
   * Removes a single dice with passed face and returns it.
   * @param face
   * @return Removed dice matching the face passed.
   */
  public Dice pop(Integer face) {
    Optional<Dice> diceToRemove = this.findFirst(face);
    if (!diceToRemove.isPresent()) {
      throw new IllegalArgumentException("Dice with face " + Integer.toString(face) + " not present in container.");
    }
    this.dice.remove(diceToRemove.get());
    return diceToRemove.get();
  }

  public Optional<Dice> findFirst(Integer face) {
    return this.dice.stream().filter(element -> element.getFace().equals(face)).findFirst();
  }

  public void remove(List<Dice> dice) {
    for (Dice pickedDice : dice) {
      this.dice.remove(pickedDice);
    }
  }

  public void remove(DiceContainer dice) {
    this.remove(dice.getDice());
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

  public DiceContainer clone() {
    DiceContainer clone = new DiceContainer();
    List<Dice> clonedDice = new ArrayList<>(this.dice);
    clone.add(clonedDice);
    return clone;
  }
}
