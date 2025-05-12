package org.calculator.data;

import java.util.List;

import org.calculator.model.CalcTestCases;

public class CalcTestCaseRepository {

    public static List<CalcTestCases> getValidExpressions() {
        return List.of(
                CalcTestCases.builder().expression("1 + 1").expectedStdout("2").expectedStderr("").build(),
                CalcTestCases.builder().expression("4 + 2 * 3").expectedStdout("10").expectedStderr("").build(),
                CalcTestCases.builder().expression("5.6 * 1.20 - 2").expectedStdout("4.72").expectedStderr("").build(),
                CalcTestCases.builder().expression("scale=3; 0.14").expectedStdout(".14").expectedStderr("").build(),
                CalcTestCases.builder().expression("scale=2; 5.0/-3").expectedStdout("-1.66").expectedStderr("").build(),
                CalcTestCases.builder().expression(" - 0.89 ").expectedStdout("-.89").expectedStderr("").build(),
                CalcTestCases.builder().expression("- - 3").expectedStdout("3").expectedStderr("").build(),
                CalcTestCases.builder().expression("999999 * 999999").expectedStdout("999998000001").expectedStderr("").build(),
                CalcTestCases.builder().expression("0011342349878977349729").expectedStdout("11342349878977349729").expectedStderr("").build(),
                CalcTestCases.builder().expression("-234987379834 ").expectedStdout("-234987379834").expectedStderr("").build(),
                CalcTestCases.builder().expression("5.0/-3").expectedStdout("-1").expectedStderr("").build()
        );
    }

    public static List<CalcTestCases> getSyntaxErrorsExpressions() {
        return List.of(
                CalcTestCases.builder().expression("0x13").expectedStdout("").expectedStderr("Parse error: bad expression").build(),
                CalcTestCases.builder().expression(" +17373497854789").expectedStdout("").expectedStderr("Parse error: bad token").build(),
                CalcTestCases.builder().expression(" *924328793497843").expectedStdout("").expectedStderr("Parse error: bad token").build(),
                CalcTestCases.builder().expression("/-8434379374").expectedStdout("").expectedStderr("Parse error: bad token").build(),
                CalcTestCases.builder().expression("--3").expectedStdout("").expectedStderr("Parse error: bad token").build(),
                CalcTestCases.builder().expression("3 +").expectedStdout("").expectedStderr("Parse error: bad expression").build(),
                CalcTestCases.builder().expression("* 5").expectedStdout("").expectedStderr("Parse error: bad token").build(),
                CalcTestCases.builder().expression(")").expectedStdout("").expectedStderr("Parse error: bad token").build(),
                CalcTestCases.builder().expression("(").expectedStdout("").expectedStderr("Parse error: bad expression").build()
        );
    }

    public static List<CalcTestCases> getDivisionByZeroCases() {
        return List.of(
                CalcTestCases.builder().expression("1/0").expectedStdout("").expectedStderr("Math error: divide by 0").build(),
                CalcTestCases.builder().expression("scale=2; 1/0").expectedStdout("").expectedStderr("Math error: divide by 0").build()
        );
    }

    public static List<CalcTestCases> getWarningExpressions() {
        return List.of(
                CalcTestCases.builder().expression("scale=-4; -0.4").expectedStdout("").expectedStderr("Math error: negative number").build()
        );
    }

    public static List<CalcTestCases> getWhitespaceExpressions() {
        return List.of(
                CalcTestCases.builder().expression("  12    +     3  ").expectedStdout("15").expectedStderr("").build(),
                CalcTestCases.builder().expression("scale = 2 ;  7.0000 * 1.0").expectedStdout("7.0000").expectedStderr("").build(),
                CalcTestCases.builder().expression("\n5 + 6\n").expectedStdout("11").expectedStderr("").build(),
                CalcTestCases.builder().expression("    100 - 50").expectedStdout("50").expectedStderr("").build()
        );
    }

    public static List<CalcTestCases> getVariableExpressions() {
        return List.of(
                CalcTestCases.builder().expression("a=5; a+3").expectedStdout("8").expectedStderr("").build(),
                CalcTestCases.builder().expression("x=10;y=3;x*y").expectedStdout("30").expectedStderr("").build(),
                CalcTestCases.builder().expression("n=7;scale=3;n/2").expectedStdout("3.500").expectedStderr("").build()
        );
    }

    public static List<CalcTestCases> getMultipleExpressions() {
        return List.of(
                CalcTestCases.builder().expression("1+2; 3+4").expectedStdouts(List.of("3", "7")).expectedStderr("").build(),
                CalcTestCases.builder().expression("scale=2; 5/2; 6/4").expectedStdouts(List.of("2.50", "1.50")).expectedStderr("").build()
        );
    }

    public static List<CalcTestCases> getLargeNumberExpressions() {
        return List.of(
                CalcTestCases.builder().expression("999999999999999999 + 1").expectedStdout("1000000000000000000").expectedStderr("").build(),
                CalcTestCases.builder().expression("-999999999999999999 * 1").expectedStdout("-999999999999999999").expectedStderr("").build(),
                CalcTestCases.builder().expression("1e3 + 1").expectedStdout("1001").expectedStderr("").build()
        );
    }

    public static List<CalcTestCases> getInvalidVariableCases() {
        return List.of(
                CalcTestCases.builder().expression("foo+").expectedStdout("").expectedStderr("Parse error: bad expression").build()
        );
    }

    public static List<CalcTestCases> getRoundingCases() {
        return List.of(
                CalcTestCases.builder().expression("scale=1; 10/3").expectedStdout("3.3").expectedStderr("").build(),
                CalcTestCases.builder().expression("scale=0; 10/3").expectedStdout("3").expectedStderr("").build()
        );
    }

    public static List<CalcTestCases> getNestedExpressionCases() {
        return List.of(
                CalcTestCases.builder().expression("((3 + 2)").expectedStdout("").expectedStderr("Parse error: bad expression").build(),
                CalcTestCases.builder().expression("3 + (2 * 4)").expectedStdout("11").expectedStderr("").build()
        );
    }
}