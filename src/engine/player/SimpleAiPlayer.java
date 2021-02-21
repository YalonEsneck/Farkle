package engine.player;

import java.util.concurrent.TimeUnit;

import engine.DiceContainer;
import engine.Game;
import engine.Score;
import output.Output;

public class SimpleAiPlayer extends BasePlayer {
  public SimpleAiPlayer(String name, Output output) {
    super(name, output);
  }

  public Boolean isHuman() {
    return false;
  }

  public Game.PlayerChoice handleRoll(DiceContainer rolledDice, DiceContainer selectedDice) {
    this.output.inf("Player " + this.getName() + " cast: " + rolledDice.toString());
    try {
      TimeUnit.MILLISECONDS.sleep(500);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }

    Integer score = Score.getScore(rolledDice.getDice());
    selectedDice.add(rolledDice.getDice());
    rolledDice.clear();
    rolledDice.add(Score.getNonScoringDice(selectedDice.getDice()));
    selectedDice.remove(rolledDice.getDice());

    this.output.inf("Selected dice: " + selectedDice.toString());
    try {
      TimeUnit.MILLISECONDS.sleep(500);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
    if ((rolledDice.size() == 0) || (rolledDice.size() > 3) || (score < 500 && rolledDice.size() >= 3)
        || (score < 300 && rolledDice.size() >= 2)) {
      this.output.inf(this.getName() + ": `I'm going for another round.`");
      try {
        TimeUnit.MILLISECONDS.sleep(500);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
      return Game.PlayerChoice.Roll;
    }
    this.output.inf(this.getName() + ": `I'll pass.`");
    try {
      TimeUnit.MILLISECONDS.sleep(500);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }

    return Game.PlayerChoice.Pass;
  }
}
