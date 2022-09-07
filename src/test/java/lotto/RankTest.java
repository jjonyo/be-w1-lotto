package lotto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RankTest {

  @Test
  @DisplayName("랭크 생성이 제대로 되어야 한다.")
  void createRank() {
    //given
    int countOfMatch = 6;
    boolean matchBonus = false;

    //when
    Rank rank = Rank.valueOf(countOfMatch, matchBonus);

    //then
    assertThat(rank).isEqualTo(Rank.FIRST);
  }

  @Test
  @DisplayName("5개 + 보너스를 맞췄을 때 2등이 되어야 한다.")
  void createSecondRank() {
    //given
    int countOfMatch = 5;

    //when
    Rank secondRank = Rank.valueOf(countOfMatch, true);
    Rank thirdRank = Rank.valueOf(countOfMatch, false);

    //then
    assertThat(secondRank).isEqualTo(Rank.SECOND);
    assertThat(thirdRank).isEqualTo(Rank.THIRD);
  }

  @Test
  @DisplayName("맞춘 개수가 3개 미만일 때는 null이 되야 한다.")
  void rankIsNull() {
    //given

    //when
    Rank rank1 = Rank.valueOf(2, true);
    Rank rank2 = Rank.valueOf(1, false);

    //then
    assertThat(rank1).isEqualTo(Rank.NONE);
    assertThat(rank2).isEqualTo(Rank.NONE);
  }

}
