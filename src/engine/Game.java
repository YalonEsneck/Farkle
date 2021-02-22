package engine;

import engine.player.PlayerInterface;
import ui.UserInterface;

import java.util.List;

public class Game {
  private List<PlayerInterface> players;
  private Integer maxScore;
  private UserInterface ui;

  /**
   * Choice of a player on how to proceed with a roll. This includes a follow-up
   * action and the selected dice.
   */
  public static class PlayerRollChoice {
    public static enum Action {
      QUIT, PASS, ROLL
    };

    private Action action;
    private DiceContainer selectedDice;

    public PlayerRollChoice(Action followUpAction, DiceContainer selectedDice) {
      this.action = followUpAction;
      this.selectedDice = selectedDice;
    }

    public void setFollowUpAction(Action newAction) {
      this.action = newAction;
    }

    public Action getFollowUpAction() {
      return this.action;
    }

    public DiceContainer getSelectedDice() {
      return this.selectedDice;
    }
  }

  public Game(List<PlayerInterface> players, Integer maxScore, UserInterface ui) {
    this.players = players;
    this.maxScore = maxScore;
    this.ui = ui;
  }

  public void run() {
    this.ui.announceVictoryConditions(this.maxScore);

    this.prepareNewGame();

    Integer highestScore = 0;
    do {
      for (PlayerInterface player : this.players) {
        if (this.playerTurn(player).getFollowUpAction() == PlayerRollChoice.Action.QUIT) {
          return;
        }

        if (player.getScore() > highestScore) {
          highestScore = player.getScore();
        }

        if (player.getScore() >= this.maxScore) {
          this.ui.announceVictor(player);
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
  private PlayerRollChoice playerTurn(PlayerInterface player) {
    this.ui.announceTurn(player);

    Shaker shaker = new Shaker();

    Integer score = 0;
    PlayerRollChoice choice;

    do {
      if (shaker.getDice().isEmpty()) {
        shaker.generateNewDice();
      }

      shaker.roll();

      choice = player.handleRoll(shaker.clone());
      if (choice.getFollowUpAction() == PlayerRollChoice.Action.QUIT) {
        return choice;
      }

      if (!this.checkRoll(choice, shaker)) {
        throw new RuntimeException("Player caught cheating!");
      }

      shaker.remove(choice.getSelectedDice().getDice());
      score += Score.getScore(choice.getSelectedDice().getDice());

      if (choice.getFollowUpAction() != PlayerRollChoice.Action.PASS) {
        this.ui.announceCurrentScore(score);
      }
    } while (choice.getFollowUpAction() == PlayerRollChoice.Action.ROLL);

    // Don't score fails.
    if (choice.getSelectedDice().size() > 0) {
      player.addToScore(score);
    }
    this.ui.updateScoreboard(this.players);

    return choice;
  }

  /**
   * 
   * @param choice
   * @param rolledDice
   * @param selectedDice
   * @return Whether roll is valid.
   */
  private Boolean checkRoll(PlayerRollChoice choice, DiceContainer rolledDice) {

    if (Score.getNonScoringDice(choice.getSelectedDice().getDice()).size() > 0) {
      return false;
    }

    if (choice.getSelectedDice().size() < 1) {
      choice.setFollowUpAction(PlayerRollChoice.Action.PASS);
    }

    // TODO add plausibility check

    return true;
  }
}
