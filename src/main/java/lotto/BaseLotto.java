package lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BaseLotto {
    private final List<Integer> numbers;
    private final static int LOTTO_NUMBER_SIZE = 6;

    public BaseLotto(List<Integer> numbers) {
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

    @Override
    public String toString() {
        return numbers.toString();
    }
}
