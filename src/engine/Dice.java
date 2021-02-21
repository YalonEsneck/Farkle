package engine;

public class Dice {
  private Integer face = 0;

  public Dice roll() {
    this.face = 1 + (int)(Math.random() * 5);
    return this;
  }

  public Integer getFace() {
    return this.face;
  }

  public void setFace(Integer newFace) {
    this.face = newFace;
  }

  public String toString() {
    return Integer.toString(this.face);
  }
}
