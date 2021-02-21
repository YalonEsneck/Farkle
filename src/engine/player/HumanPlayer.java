package engine.player;

import engine.DiceContainer;
import engine.Game;
import engine.Score;
import output.Output;

public class HumanPlayer extends BasePlayer {
  public HumanPlayer(String name, Output output) {
    super(name, output);
  }

  public Boolean isHuman() {
    return true;
  }

  public Game.PlayerChoice handleRoll(DiceContainer rolledDice, DiceContainer selectedDice) {
    if (Score.getScore(rolledDice.getDice()) > 0) {

      String input = "";
      do {
        this.output.inf("Cast dice: " + rolledDice.toString());
        this.output.inf("Selected dice: " + selectedDice.toString() + " (DEBUG: "
            + Integer.toString(Score.getScore(selectedDice.getDice())) + ")");

        this.output.inf("Your choice (type `h` for help): ", false);
        input = System.console().readLine();
        this.output.inf();

        Boolean removal = false;
        if (input.length() > 0 && input.substring(0, 1).equals("!")) {
          removal = true;
          input = input.substring(1, input.length());
        }

        DiceContainer nonScoringDice = new DiceContainer();
        nonScoringDice.add(Score.getNonScoringDice(selectedDice.getDice()));

        switch (input) {
          case "a":
            selectedDice.add(rolledDice.getDice());
            rolledDice.clear();
            break;

          case "h":
            this.output.inf("Type in the face of the dice to select it.");
            this.output.inf("Prepend with an exclamation mark to unselect it.");
            this.output.inf("Special keys:");
            this.output.inf(" a - Select all dice");
            this.output.inf(" r - Roll");
            this.output.inf(" f - Pass");
            this.output.inf(" h - Show this help");
            this.output.inf(" q - Forfeit game");
            this.output.inf();
            break;

          case "r":
            if (selectedDice.size() < 1) {
              this.output.err("You must select at least one dice!");
            } else if (nonScoringDice.size() > 0) {
              this.output.err("You must deselect all non-scoring dice: " + nonScoringDice);
            } else {
              return Game.PlayerChoice.Roll;
            }
            break;

          case "p":
            if (selectedDice.size() > 0) {
              if (nonScoringDice.size() > 0) {
                this.output.err("You must deselect all non-scoring dice: " + nonScoringDice);
                break;
              } else {
                return Game.PlayerChoice.Pass;
              }
            } else {
              this.output.war("You did not select any dice. Are you sure you want to yield your turn? (y/n) ", false);
              input = System.console().readLine();
              if (input.equalsIgnoreCase("y")) {
                return Game.PlayerChoice.Pass;
              } else {
                break;
              }
            }

          case "q":
            return Game.PlayerChoice.Quit;

          case "":
            break;

          default:
            try {
              Integer pickedFace = Integer.parseInt(input);
              DiceContainer activeContainer = removal ? selectedDice : rolledDice;
              DiceContainer passiveContainer = removal ? rolledDice : selectedDice;

              if (0 < activeContainer.getDice().stream().filter(element -> element.getFace() == pickedFace).count()) {
                passiveContainer.add(activeContainer.pop(pickedFace));
              } else {
                this.output.err("Bad choice!");
              }
            } catch (NumberFormatException e) {
              this.output.err("Bad choice!");
            }
        }
      } while (true);
    } else {
      this.output.inf("Cast dice: " + rolledDice.toString());
      this.output.err("Tough luck!");
    }

    return Game.PlayerChoice.Error;
  }
}
