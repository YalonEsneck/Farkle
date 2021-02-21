import java.util.Arrays;
import java.util.List;

import engine.player.HumanPlayer;
import engine.player.PlayerInterface;
import engine.player.SimpleAiPlayer;
import ui.Cli;
import engine.Game;

public class Main {
  public static void main(String[] args) {
    Main.runAsCli();
  }

  private static void runAsCli() {
    Cli console = new Cli();

    HumanPlayer player1 = new HumanPlayer("Human", console);
    SimpleAiPlayer player2 = new SimpleAiPlayer("Computer", console);

    console.announcePlayers(Arrays.asList(player1, player2));

    Game game = new Game(List.of(player1, player2), 2000, console);
    game.run();
  }
}