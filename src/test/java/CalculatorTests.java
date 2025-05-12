import java.util.List;

import org.calculator.calc.BcCalculator;
import org.calculator.data.CalcTestCaseRepository;
import org.calculator.model.CalcTestCases;
import org.calculator.model.CalculationResult;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CalculatorTests {

    private Object[][] getDataProvider(List<CalcTestCases> cases) {
        return cases.stream()
                .map(tc -> new Object[]{tc})
                .toArray(Object[][]::new);
    }


    @DataProvider(name = "validCases")
    public Object[][] validCases() {
        return getDataProvider(CalcTestCaseRepository.getValidExpressions());
    }

    @DataProvider(name = "syntaxErrorCases")
    public Object[][] syntaxErrorCases() {
        return getDataProvider(CalcTestCaseRepository.getSyntaxErrorsExpressions());
    }

    @DataProvider(name = "divisionByZeroCases")
    public Object[][] divisionByZeroCases() {
        return getDataProvider(CalcTestCaseRepository.getDivisionByZeroCases());
    }

    @DataProvider(name = "warningCases")
    public Object[][] warningCases() {
        return getDataProvider(CalcTestCaseRepository.getWarningExpressions());
    }

    @DataProvider(name = "whitespaceCases")
    public Object[][] whitespaceCases() {
        return getDataProvider(CalcTestCaseRepository.getWhitespaceExpressions());
    }

    @DataProvider(name = "variableCases")
    public Object[][] variableCases() {
        return getDataProvider(CalcTestCaseRepository.getVariableExpressions());
    }

    @DataProvider(name = "multiExprCases")
    public Object[][] multiExprCases() {
        return getDataProvider(CalcTestCaseRepository.getMultipleExpressions());
    }

    @DataProvider(name = "boundaryCases")
    public Object[][] boundaryCases() {
        return getDataProvider(CalcTestCaseRepository.getLargeNumberExpressions());
    }

    @DataProvider(name = "invalidVariableCases")
    public Object[][] invalidVariableCases() {
        return getDataProvider(CalcTestCaseRepository.getInvalidVariableCases());
    }


    @DataProvider(name = "roundingCases")
    public Object[][] roundingCases() {
        return getDataProvider(CalcTestCaseRepository.getRoundingCases());
    }

    @DataProvider(name = "nestedCases")
    public Object[][] nestedCases() {
        return getDataProvider(CalcTestCaseRepository.getNestedExpressionCases());
    }

    // ======= Test Methods =======

    @Test(dataProvider = "validCases", description = "Test valid expressions")
    public void testValid(CalcTestCases testCase) {
        runTest(testCase);
    }

    @Test(dataProvider = "syntaxErrorCases", description = "Test syntax errors")
    public void testSyntaxErrors(CalcTestCases testCase) {
        runTest(testCase);
    }

    @Test(dataProvider = "divisionByZeroCases", description = "Test division by zero")
    public void testDivideByZero(CalcTestCases testCase) {
        runTest(testCase);
    }

    @Test(dataProvider = "warningCases", description = "Test scale warning cases")
    public void testWarnings(CalcTestCases testCase) {
        runTest(testCase);
    }

    @Test(dataProvider = "whitespaceCases", description = "Test expressions with whitespace")
    public void testWhitespace(CalcTestCases testCase) {
        runTest(testCase);
    }

    @Test(dataProvider = "variableCases", description = "Test expressions using variables")
    public void testVariables(CalcTestCases testCase) {
        runTest(testCase);
    }

    @Test(dataProvider = "multiExprCases", description = "Test multiple expressions per input")
    public void testMultipleExpressions(CalcTestCases testCase) {
        runTest(testCase);
    }

    @Test(dataProvider = "boundaryCases", description = "Test large or extreme values")
    public void testBoundaries(CalcTestCases testCase) {
        runTest(testCase);
    }

    @Test(dataProvider = "invalidVariableCases", description = "Test invalid variable assignments")
    public void testInvalidVariables(CalcTestCases testCase) {
        runTest(testCase);
    }

    @Test(dataProvider = "roundingCases", description = "Test rounding with different scale values")
    public void testRounding(CalcTestCases testCase) {
        runTest(testCase);
    }

    @Test(dataProvider = "nestedCases", description = "Test nested and grouped expressions")
    public void testNested(CalcTestCases testCase) {
        runTest(testCase);
    }

    // ======= Shared Runner =======
    private void runTest(CalcTestCases testCase) {
        BcCalculator calculator = new BcCalculator();
        log.info("Running test: {}", testCase.getExpression());
        CalculationResult result = calculator.calculateWithStreams(testCase.getExpression());
        if(result.stdout() != null && !result.stdout().isEmpty()) {
            Assert.assertEquals(
                    result.stdout(),
                    testCase.getExpectedStdouts(),
                    "STDOUT mismatch for: " + testCase.getExpectedStdouts());
        }
        Assert.assertEquals(result.stderr(), testCase.getExpectedStderr(), "STDERR mismatch for: " + testCase.getExpectedStderr());
    }
}
