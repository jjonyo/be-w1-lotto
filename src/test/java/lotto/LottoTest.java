package lotto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class LottoTest {

    @Test
    @DisplayName("rank 계산이 잘 됩니다.")
    void calculateRank() {
        //given
        WinningLotto winningLotto = new WinningLotto(Arrays.asList(8, 9, 10, 11, 12, 13), 7);
        Lotto lotto = new Lotto(Arrays.asList(8, 9, 10, 11, 12, 7), LottoType.MANUAL);

        //when
        lotto.calculateRank(winningLotto);

        //then
        Assertions.assertThat(lotto.getRank()).isEqualTo(Rank.SECOND);
    }
}
