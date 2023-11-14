package lotto.controller;

import lotto.domain.*;
import lotto.ui.InputView;
import lotto.ui.OutputView;
import lotto.util.StringParser;

import java.util.List;

import static lotto.constant.Constant.PRICE_UNIT;

public class LottoMain {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        int price = validatePrice(inputView);

        OutputView outputView = new OutputView();
        Lottos lottos = generateLottosAndPrint(outputView, price);

        LottoResult lottoResult = calculateLottoResult(inputView, lottos);

        List<LottoWinResult> lottoWinResults = calculateLottoStatisticsAndPrint(outputView, lottoResult);

        printLottoRate(outputView, price, lottoWinResults);
    }

    private static int validatePrice(InputView inputView) {
        int price = inputView.inputPrice();
        InputValidator.validatePurchasePrice(price);
        return price;
    }

    private static Lottos generateLottosAndPrint(OutputView outputView, int price) {
        int lottoCount = price / PRICE_UNIT;
        Lottos lottos = LottoFactory.generateLottos(lottoCount);
        outputView.printLottos(lottoCount, lottos);
        return lottos;
    }

    private static LottoResult calculateLottoResult(InputView inputView, Lottos lottos) {
        List<Integer> winningLottoNumbers = StringParser.parseToInts(inputView.inputWinningNumber());
        return lottos.winCounts(new LottoWinNumbers(winningLottoNumbers));
    }

    private static List<LottoWinResult> calculateLottoStatisticsAndPrint(OutputView outputView, LottoResult lottoResult) {
        List<LottoWinResult> lottoWinResults = lottoResult.lottoStatistics();
        outputView.printLottoResultInfo();
        outputView.printLottoResult(lottoWinResults);
        return lottoWinResults;
    }

    private static void printLottoRate(OutputView outputView, int print, List<LottoWinResult> lottoWinResults) {
        LottoWinPercentage lottoWinPercentage = new LottoWinPercentage(print, lottoWinResults);
        outputView.printLottoRate(lottoWinPercentage);
    }

}
