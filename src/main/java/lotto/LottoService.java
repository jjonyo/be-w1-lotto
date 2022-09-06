package lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoService {
  private final List<Lotto> lottoList = new ArrayList<>();
  private static List<Integer> numberList = IntStream.range(1, 46)
          .boxed()
          .collect(Collectors.toList());


  public void generateLottoList(int size) {
    lottoList.clear();

    for (int i=0; i<size; i++) {
      lottoList.add(generateRandomLotto());
    }
  }

  public List<Lotto> getLottoList() {
    return lottoList;
  }
  private Lotto generateRandomLotto() {
    Collections.shuffle(numberList);
    return new Lotto(numberList.subList(0, 6));
  }

}
