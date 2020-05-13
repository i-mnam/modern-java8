package cc.imnam.examples.java8;

import org.junit.Test;

//import static org.junit.Assert.*; //더 편하다고 한다.
import static org.assertj.core.api.Assertions.*;

public class CalculatorServiceTest  {
    @Test
    public void testCalculateAddition() throws Exception {
        //CalculatorService calculatorService = new CalculatorService();
        //final int actual = calculatorService.calculate('+', 1, 2);

        // 이것 또한 잘못된 테스트 // 앞으론 실제 행동을 가지고 있는 Calculation에 대해서 테스트를 해야한다.
        //CalculatorService calculatorServic e = new CalculatorService(new Addition());
        //final int actual = calculatorService.calculate(1, 2);

        // Calculation calculation = new Addition();
        Calculation calculation = (i1, i2) -> i1 + i2;
        final int actual = calculation.calculate(1, 2);
        assertThat(actual).isEqualTo(3);
    }

    @Test
    public void testCalculateSubtraction() throws Exception {
        Calculation calculation = new Subtraction();
        final int actual = calculation.calculate(1, 2);
        assertThat(actual).isEqualTo(-1);
    }

    @Test
    public void testCalculateMultiplication() throws Exception {
        Calculation calculation = new Multiplication();
        final int actual = calculation.calculate(1, 2);
        assertThat(actual).isEqualTo(2);
    }

    @Test
    public void testCalculateDivision() throws Exception {
        Calculation calculation = new Division();
        final int actual = calculation.calculate(4, 2);
        assertThat(actual).isEqualTo(2);
    }
}