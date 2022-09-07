package lotto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoServiceTest {

  LottoService lottoService;

  @BeforeEach
  void beforeEach() {
    lottoService = new LottoService();
  }

  WinningLotto createWinningLotto() {
    return new WinningLotto(Arrays.asList(1, 2, 3, 4, 5, 6), 10);
  }

  @Test
  @DisplayName("원하는 개수의 로또 생성이 되어야 한다.")
  void createLottoList() {
    //given
    int size = 10;

    //when
    lottoService.generateLottoList(size);
    List<Lotto> lottoList = lottoService.getLottoList();

    //then
    assertThat(lottoList.size()).isEqualTo(size);
  }

  @Test
  @DisplayName("로또의 당첨 결과를 계산할 수 있어야 한다.")
  void calculateLottoRank() {
    //given
    lottoService.generateLottoList(100);
    WinningLotto winningLotto = createWinningLotto();
    lottoService.setWinningLotto(winningLotto);

    //when
    lottoService.calculateLottoRank();
    List<Lotto> lottoList = lottoService.getLottoList();

    //then
    assertThat(lottoList.stream().anyMatch(Objects::isNull)).isFalse();
  }
}
