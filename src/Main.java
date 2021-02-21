import java.util.List;

import engine.player.HumanPlayer;
import engine.player.SimpleAiPlayer;
import output.Cli;
import engine.Game;

public class Main {
  public static void main(String[] args) {
    Main.runAsCli();
  }

  private static void runAsCli() {
    Cli console = new Cli();

    HumanPlayer player1 = new HumanPlayer("Human", console);
    SimpleAiPlayer player2 = new SimpleAiPlayer("Computer", console);

    console.inf("Two combatants: " + player1.getName() + " versus " + player2.getName());

    Game game = new Game(List.of(player1, player2), 2000, console);
    game.run();
  }
}