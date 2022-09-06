package lotto;

import java.util.*;
import java.util.stream.Collectors;

public class LottoView {
  Scanner scanner = new Scanner(System.in);
  LottoService lottoService = new LottoService();

  public void start() {
    // 구입금액 입력받는 메소드 호출
    System.out.println("구입 금액을 입력해 주세요.");
    int price = scanner.nextInt();
    lottoService.setPrice(price);
    System.out.println(lottoService.size + "개를 구매했습니다.");

    // 입력받은 금액으로 로또 생성하기 (lottoService 호출)
    lottoService.generateLottoList(lottoService.size);

    // 구매결과 출력 메소드
    for (Lotto lotto : lottoService.getLottoList()) {
      System.out.println(lotto.toString());
    };

    scanner.nextLine();

    // 당첨번호 입력받는 메소드 호출
    System.out.println("지난 주 당첨 번호를 입력 해 주세요.");
    String winningNumber = scanner.nextLine();

    // 당첨번호 파싱하여 integer array 반환하는 메소드
    List<Integer> splitNumbers = Arrays.stream(winningNumber.split(", "))
            .mapToInt(Integer::parseInt)
            .boxed()
            .collect(Collectors.toList());

    lottoService.setWinningLotto(splitNumbers);

    // 당첨 계산 (lottoService 호출)
    lottoService.calculateRank();

    // 당첨 통계 출력
    List<Lotto> lottoList = lottoService.getLottoList();
    printWinningResult(lottoList);
  }

  public void printWinningResult(List<Lotto> lottoList) {
    Map<Rank, Integer> map = new EnumMap<>(Rank.class);
    for (Rank rank : Rank.values()) {
      map.put(rank, 0);
    }
    for (Lotto lotto : lottoList) {
      if (lotto.getRank() == null) continue;
      map.put(lotto.getRank(), map.get(lotto.getRank()) + 1);
    }

    System.out.println("당첨 통계\n---------");
    for (Rank rank : Rank.values()) {
      System.out.println(String.format("%d개 일치 (%d원)- %d개", rank.getCountOfMatch(), rank.getWinningMoney(), map.get(rank)));
    }
  }
}
