package calculator.domain;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public enum Operation {
    PLUS("+", (x, y) -> x + y),
    MINUS("-", (x, y) -> x - y),
    TIMES("*", (x, y) -> x * y),
    DIVIDE("/", (x, y) -> x / y);

    private final String symbol;
    private final IntBinaryOperator operator;

    Operation(String symbol, IntBinaryOperator operator) {
        this.symbol = symbol;
        this.operator = operator;
    }

    public int apply(int x, int y) {
        validateDivideZero(y);
        return operator.applyAsInt(x, y);
    }

    private void validateDivideZero(int y) {
        if(this == DIVIDE && y == 0){
            throw new IllegalArgumentException("나눗셈은 0으로 나눌 수 없습니다.");
        }
    }

    public String getSymbol() {
        return symbol;
    }

    private static Map<String, Operation> stringToEnum = Stream.of(values())
            .collect(toMap(Operation::getSymbol, Function.identity()));

    public static Optional<Operation> fromString(String symbol) {
        return Optional.ofNullable(stringToEnum.get(symbol));
    }

}
