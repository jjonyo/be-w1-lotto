package lotto;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class LottoView {
  Scanner scanner = new Scanner(System.in);
  LottoService lottoService = new LottoService();

  public void start() {
    // 구입금액 입력받는 메소드 호출
    System.out.println("구입 금액을 입력해 주세요.");
    int price = scanner.nextInt();
    int size = price / 1000;
    System.out.println(size + "개를 구매했습니다.");

    // 입력받은 금액으로 로또 생성하기 (lottoService 호출)
    lottoService.generateLottoList(size);

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
    for (Lotto lotto : lottoList) {
      System.out.println(lotto.toString());
      System.out.println(lotto.getRank());
    }

  }
}
