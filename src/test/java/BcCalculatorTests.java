import java.util.List;

import org.calculator.calc.AwkCalculator;
import org.calculator.calc.BcCalculator;
import org.calculator.calc.Calculator;
import org.calculator.data.CalcTestCaseRepository;
import org.calculator.model.CalculatorTestModel;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BcCalculatorTests {

    @DataProvider(name = "testCases")
    public Object[][] getBcCases() {
        return getDataProvider(CalcTestCaseRepository.getBasicArithmeticCases());
    }

    @DataProvider(name = "limitCases")
    public Object[][] getBcLimitCases() {
        return getDataProvider(CalcTestCaseRepository.getLimitEdgeCases());
    }

    @Test(dataProvider = "testCases")
    public void testBcCalculator(CalculatorTestModel testCase)  {
        Calculator calculator = new BcCalculator();
        log.info("Running test: {}", testCase.getExpression());
        String result = calculator.calculate(testCase.getExpression());
        Assert.assertEquals(result, testCase.getExpected(), "Failed for: " + testCase.getExpression());
    }

    @Test(dataProvider = "limitCases")
    public void testBcLimits(CalculatorTestModel testCase) {
        try {
            Calculator calculator = new BcCalculator();
            log.info("Running test: {}", testCase.getExpression());
            String result = calculator.calculate(testCase.getExpression());
            Assert.assertEquals(result, testCase.getExpected(), "Failed for: " + testCase.getExpression());
        } catch (Exception e) {
            Assert.assertTrue(e.getMessage().contains(testCase.getExpected()), "Expected error: " + testCase.getExpected());
        }
    }

    @Test(dataProvider = "testCases")
    public void testAwkCalculator(CalculatorTestModel testCase) {
        Calculator calculator = new AwkCalculator();
        log.info("Running test: {}", testCase.getExpression());
        String result = calculator.calculate(testCase.getExpression());
        Assert.assertEquals(result, testCase.getExpected(), "Failed for: " + testCase.getExpression());
    }

    @Test(dataProvider = "limitCases")
    public void testAwkLimits(CalculatorTestModel testCase) {
        try {
            Calculator calculator = new AwkCalculator();
            log.info("Running test: {}", testCase.getExpression());
            String result = calculator.calculate(testCase.getExpression());
            Assert.assertEquals(result, testCase.getExpected(), "Failed for: " + testCase.getExpression());
        } catch (Exception e) {
            Assert.assertTrue(e.getMessage().contains(testCase.getExpected()), "Expected error: " + testCase.getExpected());
        }
    }

    private Object[][] getDataProvider(List<CalculatorTestModel> cases) {
        return cases.stream().map(tc -> new Object[]{tc}).toArray(Object[][]::new);
    }
}