package lotto;

import java.util.ArrayList;
import java.util.List;

public class LottoService {
  private final List<Lotto> lottoList = new ArrayList<>();

  public void generateLottoList(int size) {
    lottoList.clear();

    for (int i=0; i<size; i++) {
      lottoList.add(generateLotto());
    }
  }

  public List<Lotto> getLottoList() {
    return lottoList;
  }
  private Lotto generateLotto() {

    return null;
  }

}
