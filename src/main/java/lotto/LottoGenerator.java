package lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoGenerator {

  private static final List<Integer> numberList = IntStream.range(1, 46)
      .boxed()
      .collect(Collectors.toList());

  public static List<Lotto> generateRandomLottoList(int size) {
    List<Lotto> lottoList = new ArrayList<>();

    for (int i = 0; i < size; i++) {
      lottoList.add(generateRandomLotto());
    }

    return lottoList;
  }

  public static Lotto generateRandomLotto() {
    Collections.shuffle(numberList);
    return new Lotto(numberList.subList(0, 6), LottoType.AUTO);
  }

  public static Lotto generateLotto(List<Integer> numbers) {
    return new Lotto(numbers, LottoType.MANUAL);
  }
}
