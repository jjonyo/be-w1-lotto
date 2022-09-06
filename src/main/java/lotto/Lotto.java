package lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lotto {
  private final List<Integer> numbers;
  private Rank rank;
ㅇ  private final static int LOTTO_NUMBER_SIZE = 6;

  public Lotto(List<Integer> numbers) {
    long size = numbers.stream()
            .distinct()
            .count();

    if (size != LOTTO_NUMBER_SIZE) {
      throw new RuntimeException("로또의 숫자 개수가 잘못되었습니다. 현재 개수 : " + numbers.size());
    }

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
