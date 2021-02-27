package com.yalonesneck.farkle.engine;

import java.util.ArrayList;
import java.util.List;

public class Score {

  private static Integer calculateScore(Integer face, Integer count) {
    Integer score = 0;

    if (count >= 3) {
      score = face * (face == 1 ? 1000 : 100) * (int) Math.pow(2, count - 3);
    }

    if (score == 0) {
      if (face == 1) {
        score = 100 * count;
      }
      if (face == 5) {
        score = 50 * count;
      }
    }

    return score;
  }

  private static Integer calculateScore(List<Dice> dice) {
    Integer[] matches = { 0, 0, 0, 0, 0, 0 };
    for (Dice pickedDice : dice) {
      matches[pickedDice.getFace() - 1]++;
    }

    Integer score = 0;

    // at least one of each 2, 3, 4, 5
    if (matches[1] > 0 && matches[2] > 0 && matches[3] > 0 && matches[4] > 0) {

      // long street
      if (matches[0] > 0 && matches[5] > 0) {
        dice.clear();
        return 1500;
      }

      // high short street
      else if (matches[5] > 0) {
        for (int i = 2; i < 7; i++) {
          Integer face = i;
          dice.remove(dice.stream().filter(element -> element.getFace() == face).findFirst().get());
        }
        return 750 + Score.calculateScore(dice);
      }

      // low short street
      else if (matches[0] > 0) {
        for (int i = 1; i < 6; i++) {
          Integer face = i;
          dice.remove(dice.stream().filter(element -> element.getFace() == face).findFirst().get());
        }
        return 500 + Score.calculateScore(dice);
      }
    }

    for (int face = 1; face < 7; face++) {
      Integer faceScore = Score.calculateScore(face, matches[face - 1]);
      score += faceScore;
      if (faceScore > 0) {
        Integer finalFace = face;
        dice.removeIf(element -> element.getFace() == finalFace);
      }
    }

    return score;
  }

  /**
   * Returns the dice which don't add to the score.
   * 
   * @param dice
   * @return
   */
  public static List<Dice> getNonScoringDice(List<Dice> dice) {
    List<Dice> nonScoringDice = new ArrayList<>(dice);
    Score.calculateScore(nonScoringDice);
    return nonScoringDice;
  }

  /**
   * Get total score of dice passed.
   * 
   * @param dice
   * @return
   */
  public static Integer getScore(List<Dice> dice) {
    return Score.calculateScore(new ArrayList<>(dice));
  }
}
