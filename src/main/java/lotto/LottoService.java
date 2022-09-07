package lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoService {
  private final List<Lotto> lottoList = new ArrayList<>();
  private static final List<Integer> numberList = IntStream.range(1, 46)
          .boxed()
          .collect(Collectors.toList());
  private WinningLotto winningLotto;

  public void generateLottoList(int size) {
    lottoList.clear();

    for (int i = 0; i < size; i++) {
      lottoList.add(generateRandomLotto());
    }
  }

  public void setWinningLotto(WinningLotto winningLotto) {
    this.winningLotto = winningLotto;
  }

  public List<Lotto> getLottoList() {
    return lottoList;
  }

  private Lotto generateRandomLotto() {
    Collections.shuffle(numberList);
    return new Lotto(numberList.subList(0, 6));
  }

  public void calculateLottoRank() {
    //lottoList를 돌면서 각각의 로또에 대해 Rank를 계산하여 Lotto에 저장.
    for (Lotto lotto : lottoList) {
      lotto.calculateRank(winningLotto);
    }
  }
}
