package lotto;

import java.util.List;

public class Lotto extends BaseLotto {
  private final LottoType type;
  private Rank rank;

  public Lotto(List<Integer> numbers, LottoType type) {
    super(numbers);
    this.type = type;
  }

  public void calculateRank(WinningLotto winningLotto) {
    int countOfMatch = countMatchNumber(winningLotto);

    boolean isBonusMatch = getBonusMatch(winningLotto);

    this.rank = Rank.valueOf(countOfMatch, isBonusMatch);
  }

  private boolean getBonusMatch(WinningLotto winningLotto) {
    return this.getNumbers().contains(winningLotto.getBonusNumber());
  }

  private int countMatchNumber(WinningLotto winningLotto) {
    int countOfMatch = 0;

    for (Integer number : this.getNumbers()) {
      if (winningLotto.getNumbers().contains(number)) {
        countOfMatch += 1;
      }
    }

    return countOfMatch;
  }

  public Rank getRank() {
    return rank;
  }

  public LottoType getType() {
    return type;
  }
}
