package lotto;

import java.util.Scanner;

public class LottoView {
  Scanner scanner = new Scanner(System.in);
  LottoService lottoService = new LottoService();

  public void start() {
    // 구입금액 입력받는 메소드 호출
    System.out.println("구입 금액을 입력해 주세요.");
    int price = scanner.nextInt();
    int size = price / 10;

    // 입력받은 금액으로 로또 생성하기 (lottoService 호출)
    lottoService.generateLottoList(size);

    // 구매결과 출력 메소드
//    lottoService.getLottoList();

    // 당첨번호 입력받는 메소드 호출
    System.out.println("지난 주 당첨 번호를 입력 해 주세요.");
    String winningNumber = scanner.nextLine();

    // 당첨번호 파싱하여 integer array 반환하는 메소드

    // 당첨 계산 (lottoService 호출)

    // 당첨 통계 출력
  }
}
