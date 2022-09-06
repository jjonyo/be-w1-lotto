package lotto;

import java.util.*;
import java.util.stream.Collectors;

public class LottoView {
  private final Scanner scanner = new Scanner(System.in);
  private final LottoService lottoService = new LottoService();
  private final static int LOTTO_PRICE = 1000;

  public void start() {
    int price = inputLottoPrice();

    buyLotto(price);

    Lotto winningLotto = createWinningLotto();
    lottoService.setWinningLotto(winningLotto);
    lottoService.calculateLottoRank();

    printWinningResult(lottoService.getLottoList());
  }

  private Lotto createWinningLotto() {
    String winningNumber = inputWinningNumber();

    List<Integer> splitNumbers = Arrays.stream(winningNumber.split(", "))
            .mapToInt(Integer::parseInt)
            .boxed()
            .collect(Collectors.toList());

    return new Lotto(splitNumbers);
  }

  private String inputWinningNumber() {
    System.out.println("지난 주 당첨 번호를 입력 해 주세요.");
    return scanner.nextLine();
  }

  private void buyLotto(int price) {
    int size = (int) price / LOTTO_PRICE;

    lottoService.generateLottoList(size);
    System.out.println(size + "개를 구매했습니다.");

    printLottoList(lottoService.getLottoList());
  }

  private void printLottoList(List<Lotto> lottoList) {
    for (Lotto lotto : lottoList) {
      System.out.println(lotto.toString());
    }
  }

  private int inputLottoPrice() {
    System.out.println("구입 금액을 입력해 주세요.");
    int price = scanner.nextInt();
    scanner.nextLine();

    return price;
  }

  public void printWinningResult(List<Lotto> lottoList) {
    Map<Rank, Integer> map = new EnumMap<>(Rank.class);

    long earningPrice = 0;

    for (Rank rank : Rank.values()) {
      map.put(rank, 0);
    }

    for (Lotto lotto : lottoList) {
      if (lotto.getRank() == null) continue;
      map.put(lotto.getRank(), map.get(lotto.getRank()) + 1);
    }

    System.out.println("당첨 통계\n---------");
    for (Rank rank : Rank.values()) {
      int count = map.get(rank);
      System.out.printf("%d개 일치 (%d원)- %d개%n", rank.getCountOfMatch(), rank.getWinningMoney(), count);
      earningPrice += ((long) rank.getWinningMoney() * count);
    }

    long principal = (long) LOTTO_PRICE * lottoList.size();
    // 원래 금액, 획득한 금액
    double earningRatio = ((double) (earningPrice - principal) / principal) * 100;

    System.out.printf("총 수익률은 %.2f%%입니다.%n", earningRatio);
  }
}
