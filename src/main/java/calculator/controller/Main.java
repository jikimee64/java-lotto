package calculator.controller;

import calculator.domain.Calculator;
import calculator.domain.CalculatorInputProcessor;
import calculator.ui.InputView;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        InputView inputView = new InputView(new Scanner(System.in));
        String inputText = inputView.text();

        CalculatorInputProcessor calculatorInputProcessor = new CalculatorInputProcessor(inputText);
        List<String> operators = calculatorInputProcessor.extractOperator();
        List<Integer> numbers = calculatorInputProcessor.extractNumber();

        Calculator calculator = new Calculator();
        System.out.println("계산 결과 " + calculator.calculate(operators, numbers));
    }

}
