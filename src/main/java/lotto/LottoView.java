package lotto;

import java.util.*;
import java.util.stream.Collectors;

public class LottoView {
  private final Scanner scanner = new Scanner(System.in);
  private final LottoService lottoService = new LottoService();
  private int price = 0;
  private final static int LOTTO_PRICE = 1000;

  public void start() {
    price = inputLottoPrice();
    buyManualLotto();
    buyAutoLotto();
    printLottoList(lottoService.getLottoList());

    WinningLotto winningLotto = createWinningLotto();
    lottoService.setWinningLotto(winningLotto);
    lottoService.calculateLottoRank();

    calculateWinningResult(lottoService.getLottoList());
  }

  private void buyManualLotto() {
    System.out.println("수동으로 구매 할 로또 수를 입력해주세요.");
    int size = scanner.nextInt();

    if (size * LOTTO_PRICE > price) {
      throw new RuntimeException("금액이 부족합니다.");
    }

    price -= size * LOTTO_PRICE;

    scanner.nextLine();
    System.out.println("수동으로 구매할 번호를 입력해주세요.");
    for (int i=0; i<size; i++) {
      List<Integer> numbers = parseLottoNumber(scanner.nextLine());
      lottoService.generateLotto(numbers);
    }
  }

  private WinningLotto createWinningLotto() {
    String winningNumber = inputWinningNumber();
    int bonusNumber = inputBonusNumber();

    List<Integer> splitNumbers = parseLottoNumber(winningNumber);

    return new WinningLotto(splitNumbers, bonusNumber);
  }

  private List<Integer> parseLottoNumber(String lottoNumber) {
    try {
      List<Integer> splitNumbers = Arrays.stream(lottoNumber.split(", "))
          .mapToInt(Integer::parseInt)
          .boxed()
          .collect(Collectors.toList());

      return splitNumbers;
    } catch(Exception e) {
      throw new RuntimeException("입력받은 로또 번호 형식이 잘못되었습니다.");
    }
  }

  private String inputWinningNumber() {
    System.out.println("지난 주 당첨 번호를 입력 해 주세요.");
    return scanner.nextLine();
  }

  private int inputBonusNumber() {
    System.out.println("보너스 볼을 입력해 주세요.");
    return scanner.nextInt();
  }

  private void buyAutoLotto() {
    int size = (int) price / LOTTO_PRICE;

    lottoService.generateLottoList(size);
    System.out.println(size + "개를 구매했습니다.");
    price -= size * LOTTO_PRICE;
  }

  private void printLottoList(List<Lotto> lottoList) {
    int autoCount = (int) lottoList.stream().filter(l -> l.getType() == LottoType.AUTO).count();
    int manualCount = (int) lottoList.stream().filter(l -> l.getType() == LottoType.MANUAL).count();

    System.out.printf("수동으로 %d개, 자동으로 %d개를 구매했습니다.%n", manualCount, autoCount);

    for (Lotto lotto : lottoList) {
      System.out.println(lotto.toString());
    }
  }

  private int inputLottoPrice() {
    System.out.println("구입 금액을 입력해 주세요.");
    int price = scanner.nextInt();

    if (price < LOTTO_PRICE) {
      throw new RuntimeException("금액은 " + LOTTO_PRICE + "원 이상이여야 합니다.");
    }

    scanner.nextLine();
    return price;
  }

  public void calculateWinningResult(List<Lotto> lottoList) {
    Map<Rank, Integer> resultMap = getResultMap(lottoList);
    double earningRatio = getEarningRatio(lottoList);

    printWinningResult(resultMap, earningRatio);
  }

  public Map<Rank, Integer> getResultMap(List<Lotto> lottoList) {
    Map<Rank, Integer> map = new EnumMap<>(Rank.class);

    for (Rank rank : Rank.values()) {
      map.put(rank, 0);
    }

    for (Lotto lotto : lottoList) {
      if (lotto.getRank() == null) continue;
      map.put(lotto.getRank(), map.get(lotto.getRank()) + 1);
    }

    return map;
  }

  public double getEarningRatio(List<Lotto> lottoList) {
    long earningPrice = 0;

    for (Lotto lotto : lottoList) {
      if (lotto.getRank() == null) continue;
      earningPrice += lotto.getRank().getWinningMoney();
    }

    long principal = (long) LOTTO_PRICE * lottoList.size();

    return ((double) (earningPrice - principal) / principal) * 100;
  }

  public void printWinningResult(Map<Rank, Integer> map, double earningRatio) {
    System.out.println("당첨 통계\n---------");
    for (Rank rank : Rank.values()) {
      if (rank == Rank.NONE) continue;
      int count = map.get(rank);
      String bonusString = rank == Rank.SECOND ? "보너스볼 일치" : "";
      System.out.printf("%d개 일치 %s (%d원)- %d개%n", rank.getCountOfMatch(), bonusString, rank.getWinningMoney(), count);
    }
    System.out.printf("총 수익률은 %.2f%%입니다.%n", earningRatio);
  }
}
