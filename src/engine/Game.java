package engine;

import java.util.List;

import engine.player.PlayerInterface;
import output.Output;

public class Game {
  private List<PlayerInterface> players;
  private Integer maxScore;
  private Output output;

  /**
   * Choice of a player on how to proceed with a roll.
   */
  public enum PlayerChoice {
    Error, Quit, Pass, Roll
  };

  public Game(List<PlayerInterface> players, Integer maxScore, Output output) {
    this.players = players;
    this.maxScore = maxScore;
    this.output = output;
  }

  public void run() {
    this.output.inf("Playing 'till " + this.maxScore + ". Let's begin!");
    this.output.inf();

    this.prepareNewGame();

    Integer highestScore = 0;
    do {
      for (PlayerInterface player : this.players) {
        if (this.playerTurn(player)) {
          return;
        }

        if (player.getScore() > highestScore) {
          highestScore = player.getScore();
        }

        if (player.getScore() >= this.maxScore) {
          this.output.announceVictor(player);
          break;
        }
      }
    } while (highestScore < this.maxScore);
  }

  private void prepareNewGame() {
    for (PlayerInterface player : this.players) {
      player.setScore(0);
    }
  }

  /**
   * @param player
   * @return If human chose to quit.
   */
  private Boolean playerTurn(PlayerInterface player) {
    this.output.inf("It's player " + player.getName() + "'s turn.");
    this.output.inf();

    Shaker shaker = new Shaker();
    DiceContainer selection = new DiceContainer();

    Integer score = 0;

    PlayerChoice result = PlayerChoice.Roll;

    do {
      if (shaker.getDice().isEmpty()) {
        shaker.generateNewDice();
      }

      selection.clear();
      shaker.roll();

      result = player.handleRoll(shaker, selection);
      if (result == PlayerChoice.Quit) {
        return true;
      }

      if (Score.getNonScoringDice(selection.getDice()).size() > 0) {
        throw new RuntimeException(player.getName() + " caught cheating!");
      }

      if (selection.size() < 1) {
        this.output.war("Your turn is over now.");
        result = PlayerChoice.Pass;
      }

      score += Score.getScore(selection.getDice());

      if (result != PlayerChoice.Pass) {
        this.output.inf("Score so far: " + score);
      }
    } while (result == PlayerChoice.Roll);

    // Don't score fails.
    if (selection.size() > 0) {
      player.addToScore(score);
    }
    this.output.updateScoreboard(this.players);

    return false;
  }
}
