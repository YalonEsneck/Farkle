package output;

import java.util.List;

import engine.player.PlayerInterface;

public interface Output {
  public abstract void err();

  public abstract void err(String msg);

  public abstract void err(String msg, Boolean newLine);

  public abstract void war();

  public abstract void war(String msg);

  public abstract void war(String msg, Boolean newLine);

  public abstract void inf();

  public abstract void inf(String msg);

  public abstract void inf(String msg, Boolean newLine);

  public abstract void updateScoreboard(List<PlayerInterface> players);

  public abstract void announceVictor(PlayerInterface player);

  public abstract void announceTurn(PlayerInterface player);

  public abstract void announceCurrentScore(Integer score);
}
