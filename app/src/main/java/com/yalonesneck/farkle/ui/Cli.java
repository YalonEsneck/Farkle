package com.yalonesneck.farkle.ui;

import com.yalonesneck.farkle.engine.Dice;
import com.yalonesneck.farkle.engine.DiceContainer;
import com.yalonesneck.farkle.engine.player.PlayerInterface;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Cli implements UserInterface {

  private void out(String msg, Boolean newLine) {
    if (newLine) {
      System.out.println(msg);
    } else {
      System.out.print(msg);
    }
  }

  private void err(String msg, Boolean newLine) {
    this.out("\033[31m" + msg + "\033[0m", newLine);
  }

  private void war(String msg, Boolean newLine) {
    this.out("\033[33m" + msg + "\033[0m", newLine);
  }

  private void inf(String msg, Boolean newLine) {
    this.out("\033[34m" + msg + "\033[0m", newLine);
  }

  public void announceVictoryConditions(Integer maxScore) {
    this.inf("Playing 'till " + maxScore + ". Let's begin!", true);
    this.inf("", true);
  }

  public void updateScoreboard(List<PlayerInterface> players) {
    for (PlayerInterface player : players) {
      this.inf(player.getName() + ": " + player.getScore(), true);
    }
  }

  public void announceVictor(PlayerInterface player) {
    this.out("\033[32mPlayer " + player.getName() + " wins with a score of " + player.getScore() + "!\033[0m", true);
  }

  public void announceTurn(PlayerInterface player) {
    this.inf("It's player " + player.getName() + "'s turn.", true);
    this.inf("", true);
  }

  public void announceCurrentScore(Integer score) {
    this.inf("Score so far: " + score, true);
  }

  public void castDice(DiceContainer dice) {
    this.inf("Cast dice: " + dice.toString(), true);
  }

  public void selectedDice(DiceContainer dice) {
    this.inf("Selected dice: " + dice.toString(), true);
  }

  public void noDiceSelected() {
    this.err("You must select at least one dice!", true);
  }

  public void unselectNonScoringDice(DiceContainer nonScoringDice) {
    this.err("You must deselect all non-scoring dice: " + nonScoringDice, true);
  }

  public void announcePlayers(List<PlayerInterface> players) {
    String playerList = players.stream().map(PlayerInterface::getName).collect(Collectors.joining(" versus "));
    this.inf("Two combatants: " + playerList, true);
  }

  public void rollsAgain(PlayerInterface player) {
    this.inf(player.getName() + ": `I'm going for another round.`", true);
  }

  public void passes(PlayerInterface player) {
    this.inf(player.getName() + ": `I'll pass.`", true);
  }

  public UserInterface.PlayerAction promptForAction(DiceContainer rolledDice, DiceContainer selectedDice) {
    do {
      String input = "";

      this.inf("Your choice (type `h` for help): ", false);
      input = System.console().readLine();
      this.inf("", true);

      Boolean removal = false;
      if (input.length() > 0 && input.substring(0, 1).equals("!")) {
        removal = true;
        input = input.substring(1, input.length());
      }

      switch (input) {
        case "a":
          return removal ? (new UserInterface.PlayerAction(UserInterface.PlayerAction.Type.UNSELECT, rolledDice))
              : (new UserInterface.PlayerAction(UserInterface.PlayerAction.Type.SELECT, rolledDice));

        case "h":
          this.inf("Type in the face of the dice to select it.", true);
          this.inf("Prepend with an exclamation mark to unselect it.", true);
          this.inf("Special keys:", true);
          this.inf(" a - Select all dice", true);
          this.inf(" r - Roll", true);
          this.inf(" f - Pass", true);
          this.inf(" h - Show this help", true);
          this.inf(" q - Forfeit game", true);
          this.inf("", true);
          break;

        case "r":
          return new UserInterface.PlayerAction(UserInterface.PlayerAction.Type.ROLL);

        case "p":
          return new UserInterface.PlayerAction(UserInterface.PlayerAction.Type.PASS);

        case "q":
          return new UserInterface.PlayerAction(UserInterface.PlayerAction.Type.QUIT);

        case "":
          break;

        default:
          try {
            Integer pickedFace = Integer.parseInt(input);
            DiceContainer container = removal ? selectedDice : rolledDice;
            Optional<Dice> pickedDice = container.findFirst(pickedFace);

            if (pickedDice.isPresent()) {
              DiceContainer pickedDiceContainer = new DiceContainer();
              pickedDiceContainer.add(pickedDice.get());
              return new UserInterface.PlayerAction(UserInterface.PlayerAction.Type.SELECT, pickedDiceContainer);
            } else {
              this.err("Bad choice!", true);
            }
          } catch (NumberFormatException e) {
            this.err("Bad choice!", true);
          }
      }
    } while (true);
  }

  public Boolean confirmYieldingTurn() {
    this.war("You did not select any dice. Are you sure you want to yield your turn? (y/n) ", false);
    String input = "";
    input = System.console().readLine();
    return input.equalsIgnoreCase("y");
  }
}