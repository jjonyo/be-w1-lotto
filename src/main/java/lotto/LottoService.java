package lotto;

import java.util.ArrayList;
import java.util.List;

public class LottoService {
  private final List<Lotto> lottoList = new ArrayList<>();
  private WinningLotto winningLotto;

  public void generateLottoList(int size) {
    lottoList.addAll(LottoGenerator.generateRandomLottoList(size));
  }

  public void generateLotto(List<Integer> numbers) {
    lottoList.add(LottoGenerator.generateLotto(numbers));
  }

  public void setWinningLotto(WinningLotto winningLotto) {
    this.winningLotto = winningLotto;
  }

  public List<Lotto> getLottoList() {
    return lottoList;
  }

  public void calculateLottoRank() {
    for (Lotto lotto : lottoList) {
      lotto.calculateRank(winningLotto);
    }
  }
}
