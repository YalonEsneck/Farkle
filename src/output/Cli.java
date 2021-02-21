package output;

import java.util.List;

import engine.player.PlayerInterface;

public class Cli implements Output {
  public void err() {
    this.err("");
  }

  public void err(String msg) {
    this.err(msg, true);
  }

  public void err(String msg, Boolean newLine) {
    this.out("\033[31m" + msg + "\033[0m", newLine);
  }

  public void war() {
    this.war("");
  }

  public void war(String msg) {
    this.war(msg, true);
  }

  public void war(String msg, Boolean newLine) {
    this.out("\033[33m" + msg + "\033[0m", newLine);
  }

  public void inf() {
    this.inf("");
  }

  public void inf(String msg) {
    this.inf(msg, true);
  }

  public void inf(String msg, Boolean newLine) {
    this.out("\033[34m" + msg + "\033[0m", newLine);
  }

  private void out(String msg, Boolean newLine) {
    if (newLine) {
      System.out.println(msg);
    } else {
      System.out.print(msg);
    }
  }

  public void updateScoreboard(List<PlayerInterface> players) {
    for (PlayerInterface player : players) {
      this.inf(player.getName() + ": " + player.getScore());
    }
  }

  public void announceVictor(PlayerInterface player) {
    this.out("\033[32mPlayer " + player.getName() + " wins with a score of " + player.getScore() + "!\033[0m", true);
  }
}