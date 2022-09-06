package lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lotto {
  private final List<Integer> numbers;
  private Rank rank;

  public Lotto(List<Integer> numbers) {
    Collections.sort(numbers);

    this.numbers = new ArrayList<>(numbers);
  }

  public List<Integer> getNumbers() {
    return numbers;
  }

  public void calculateRank(Lotto winningLotto) {
    int countOfMatch = countMatchNumber(winningLotto);

    this.rank = Rank.valueOf(countOfMatch);
  }

  private int countMatchNumber(Lotto winningLotto) {
    int countOfMatch = 0;

    for (Integer number : numbers) {
      if (winningLotto.getNumbers().contains(number)) {
        countOfMatch += 1;
      }
    }

    return countOfMatch;
  }


  public Rank getRank() {
    return rank;
  }

  @Override
  public String toString() {
    return numbers.toString();
  }
}
