package org.calculator.data;

import java.util.List;

import org.calculator.model.CalculatorTestModel;

public class CalcTestCaseRepository {

    public static List<CalculatorTestModel> getBasicArithmeticCases() {
        return List.of(
                CalculatorTestModel.builder().expression("1 + 2").expected("3").build(),
                CalculatorTestModel.builder().expression("10 - 5").expected("5").build(),
                CalculatorTestModel.builder().expression("4 * 3").expected("12").build(),
                CalculatorTestModel.builder().expression("8 / 2").expected("4").build()
        );
    }

    public static List<CalculatorTestModel> getLimitEdgeCases() {
        return List.of(
                // Division by zero
                CalculatorTestModel.builder().expression("1 / 0").expected("Runtime error").build(),

                // Large numbers
                CalculatorTestModel.builder().expression("123456789 * 987654321").expected("121932631112635269").build(),

                // Floating point division (bc returns more digits, awk returns 6-digit precision)
                CalculatorTestModel.builder().expression("5 / 7").expected("0.71428571428571428571").build(),

                // Zero multiplication
                CalculatorTestModel.builder().expression("0 * 9999").expected("0").build(),

                // Negative numbers
                CalculatorTestModel.builder().expression("-5 + -3").expected("-8").build(),

                // Expression with parentheses
                CalculatorTestModel.builder().expression("(2 + 3) * 4").expected("20").build()
        );
    }
}