package engine.player;

import java.util.concurrent.TimeUnit;

import engine.DiceContainer;
import engine.Game;
import engine.Score;
import ui.UserInterface;

public class SimpleAiPlayer extends BasePlayer {
  public SimpleAiPlayer(String name, UserInterface ui) {
    super(name, ui);
  }

  public Game.PlayerRollChoice handleRoll(DiceContainer rolledDice) {
    DiceContainer selectedDice = new DiceContainer();

    this.ui.castDice(rolledDice);
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

    this.ui.selectedDice(selectedDice);
    try {
      TimeUnit.MILLISECONDS.sleep(500);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
    if ((rolledDice.size() == 0) || (rolledDice.size() > 3) || (score < 500 && rolledDice.size() >= 3)
        || (score < 300 && rolledDice.size() >= 2)) {
          this.ui.rollsAgain(this);
      try {
        TimeUnit.MILLISECONDS.sleep(500);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
      return new Game.PlayerRollChoice(Game.PlayerRollChoice.Action.ROLL, selectedDice);
    }
    this.ui.passes(this);
    try {
      TimeUnit.MILLISECONDS.sleep(500);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }

    return new Game.PlayerRollChoice(Game.PlayerRollChoice.Action.PASS, selectedDice);
  }
}
