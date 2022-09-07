package lotto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class LottoViewTest {

    LottoView lottoView;

    @BeforeEach
    void setup() {
        lottoView = new LottoView();
    }

    Lotto createLotto(List<Integer> numbers) {
        return new Lotto(numbers, LottoType.MANUAL);
    }

    @Test
    @DisplayName("resultMap이 잘 구해진다.")
    void getResultMap() {
        //given
        List<Lotto> lottoList = new ArrayList<>();
        WinningLotto winningLotto = new WinningLotto(Arrays.asList(1, 2, 3, 4, 5, 6), 7);
        lottoList.add(createLotto(Arrays.asList(1, 2, 3, 4, 5, 6)));
        lottoList.add(createLotto(Arrays.asList(1, 2, 3, 4, 5, 7)));
        lottoList.add(createLotto(Arrays.asList(1, 2, 3, 4, 5, 8)));
        lottoList.add(createLotto(Arrays.asList(1, 2, 3, 4, 8, 9)));
        lottoList.add(createLotto(Arrays.asList(1, 2, 3, 8, 9, 10)));
        for (Lotto lotto : lottoList) {
            lotto.calculateRank(winningLotto);
        }

        //when
        Map<Rank, Integer> resultMap = lottoView.getResultMap(lottoList);

        //then
        Assertions.assertThat(resultMap.get(Rank.FIRST)).isEqualTo(1);
        Assertions.assertThat(resultMap.get(Rank.SECOND)).isEqualTo(1);
        Assertions.assertThat(resultMap.get(Rank.THIRD)).isEqualTo(1);
        Assertions.assertThat(resultMap.get(Rank.FOURTH)).isEqualTo(1);
        Assertions.assertThat(resultMap.get(Rank.FIFTH)).isEqualTo(1);
    }

    @Test
    @DisplayName("earningRatio가 잘 구해진다.")
    void getEarningRatio() {
        //given
        List<Lotto> lottoList = new ArrayList<>();
        WinningLotto winningLotto = new WinningLotto(Arrays.asList(8, 9, 10, 11, 12, 13), 7);
        lottoList.add(createLotto(Arrays.asList(1, 2, 3, 4, 5, 6)));
        lottoList.add(createLotto(Arrays.asList(1, 2, 3, 4, 5, 7)));
        lottoList.add(createLotto(Arrays.asList(1, 2, 3, 4, 5, 8)));
        lottoList.add(createLotto(Arrays.asList(1, 2, 3, 4, 8, 9)));
        lottoList.add(createLotto(Arrays.asList(1, 2, 3, 8, 9, 10)));
        for (Lotto lotto : lottoList) {
            lotto.calculateRank(winningLotto);
        }

        //when
        double earningRatio = lottoView.getEarningRatio(lottoList);

        //then
        Assertions.assertThat(earningRatio).isEqualTo(0);
    }
}
