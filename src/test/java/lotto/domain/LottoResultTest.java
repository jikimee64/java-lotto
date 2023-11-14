package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static java.util.Map.entry;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.tuple;

class LottoResultTest {

    @Test
    @DisplayName("로또 결과를 기준으로 당첨 통계를 구한다.")
    void success_generate_lotto_statistics() {
        Map<Long, Long> lottoResults = Map.ofEntries(
                entry(3L, 1L),
                entry(4L, 1L),
                entry(5L, 0L),
                entry(6L, 1L)
        );

        LottoResult lottoResult = new LottoResult(lottoResults);
        List<LottoWinResult> lottoWinResults = lottoResult.lottoStatistics();

        assertThat(lottoWinResults).hasSize(4)
                .extracting("machCount", "prizeAmount", "winCount")
                .containsExactlyInAnyOrder(
                        tuple(3L, 5000L, 1L),
                        tuple(4L, 50000L, 1L),
                        tuple(5L, 1500000L, 0L),
                        tuple(6L, 2000000000L, 1L)
                );
    }

}
