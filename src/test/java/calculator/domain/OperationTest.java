package calculator.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class OperationTest {

    @ParameterizedTest(name = "연산자 : {0}, 피연산자1 : {1}, 피연산자2 : {2}, 연산결과 : {3}")
    @MethodSource("provideStringAndIndexAndChar")
    @DisplayName("성공 - 단일 사칙연산으로 원하는 결과값이 나온다.")
    void success_plus(String operator, int operand, int operand2, int result) {
        // when
        Operation operation = Operation.fromString(operator)
                .orElseThrow(() -> new IllegalArgumentException("사칙연산 기호만 사용 가능합니다."));

        // then
        assertThat(operation.apply(operand, operand2)).isEqualTo(result);
    }

    private static Stream<Arguments> provideStringAndIndexAndChar() {
        return Stream.of(
                Arguments.of("+", 1, 2, 3),
                Arguments.of("-", 2, 1, 1),
                Arguments.of("*", 2, 3, 6),
                Arguments.of("/", 4, 2, 2)
        );
    }

    @Test
    @DisplayName("실패 - 사칙연산 기호가 나눗셈 인 경우 0으로 나눌시 예외가 발생한다.")
    void fail_divide_zero() {
        // given
        Operation operation = Operation.fromString("/")
                .orElseThrow(() -> new IllegalArgumentException("사칙연산 기호만 사용 가능합니다."));

        // when & then
        Assertions.assertThatThrownBy(() -> operation.apply(2, 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("나눗셈은 0으로 나눌 수 없습니다.");
    }

}
