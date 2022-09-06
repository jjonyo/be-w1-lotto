package lotto;

import java.util.ArrayList;
import java.util.List;

public class Lotto {
  private final List<Integer> numbers;

  public Lotto(List<Integer> numbers) {
    this.numbers = new ArrayList<>(numbers);
  }

  public List<Integer> getNumbers() {
    return numbers;
  }
}
