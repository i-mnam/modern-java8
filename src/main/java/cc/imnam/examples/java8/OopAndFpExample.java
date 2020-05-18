package cc.imnam.examples.java8;

public class OopAndFpExample {

    public static void main(String[] args) {
        //final CalculatorService calculatorService = new CalculatorService();
        //final int additionResult = calculatorService.calculate('+',1, 2);
        //final CalculatorService calculatorService = new CalculatorService(new Addition());
        final CalculatorService calculatorService = new CalculatorService(new Addition(), new Subtraction());
        final int additionResult = calculatorService.calculate(11, 2);
        System.out.println("additionResult :" + additionResult);
        final int subtractionResult = calculatorService.calculate(11, 2);
        System.out.println("subtractionResult :" + subtractionResult);
        final int multiplicationResult = calculatorService.calculate(11, 2);
        System.out.println("multiplicationResult :" + multiplicationResult);
        final int divisionResult = calculatorService.calculate(40, 2);
        System.out.println("divisionResult :" + divisionResult);

        System.out.println("========================");

        final FpCalculatorService fpCalculatorService = new FpCalculatorService();
//        System.out.println("     addition :" + fpCalculatorService.calculate(new Addition(), 11, 2));
//        .....

        final Calculation addition = (i1, i2) -> i1 + i2;
        //!!!! function이 variable에 할당이 된다!! >> 더욱 다양한 기능을 구현 할 수 있다.
        System.out.println("     addition :" + fpCalculatorService.calculate2(addition, 11, 2));
        System.out.println("   subtraction:" + fpCalculatorService.calculate2((i1, i2) -> i1 - i2, 11,2));
        System.out.println("Multiplication:" + fpCalculatorService.calculate2((i1, i2) -> i1 * i2, 11, 2));
        System.out.println("      Division:" + fpCalculatorService.calculate2((i1, i2) -> i1 / i2, 40, 2));
        // 추가 : 좀 더 복잡하게 활용
        System.out.println("   custom calc:" + fpCalculatorService.calculate2((i1, i2) -> ((i1 + i2) * 2) / i2, 40, 2));
    }

}
// strategy pattern
interface Calculation {
    int calculate(int num1, int num2);

    default void nothingFunc() {
        System.out.println("nonthing!!");
    }
}

class Addition implements Calculation { // dependency
    @Override
    public int calculate(int num1, int num2) {
        return num1 + num2;
    }
}

class Subtraction implements Calculation {
    @Override
    public int calculate(int num1, int num2) {
        return num1 - num2;
    }
}

class Multiplication implements Calculation {
    @Override
    public int calculate(int num1, int num2) {
        return num1 * num2;
    }
}
class Division implements Calculation {
    @Override
    public int calculate(int num1, int num2) {
        return num1 / num2;
    }
}

class CalculatorService {
//    public int calculate(char operator, int num1, int num2) {
//        int result;
//        if (operator == '+') {
//            result = num1 + num2;
// .....
//        } else {
//            throw new IllegalArgumentException("Unknown calculation : " + operator);
//        }
//        return result;
//    }

    private final Calculation calculation;
    private final Calculation calculation2;// 추가

//    public CalculatorService(final Calculation calculation) {
//        this.calculation = calculation;
//    }

    public CalculatorService(final Calculation calculation, final Calculation calculation2) {
        this.calculation = calculation;
        this.calculation2 = calculation2;
    }

//    public int calculate(int num1, int num2) { // dependency를 이용할거임.
//        return calculation.calculate(num1, num2);
//    }

    public int calculate(int num1, int num2) { // service에서 비지니스 로직이 예를들어 옆처럼 생겼을 때,
        if (num1 > 10 && num2 < num1) {
            return calculation.calculate(num1, num2);
        } else {
            throw new IllegalArgumentException("Invalid input num1:" + num1 + "num2:" + num2);
        }
    }

    public int compute(int num1, int num2) { // 구조가 흡사한 예시 //여기에서는 다른 계산을 하고 싶다면 calculation이 달라야된다는 문제가 발생.
       if (num1 > 10 && num2 < num1) {
            return calculation2.calculate(num1, num2);
        } else {
            throw new IllegalArgumentException("Invalid input num1:" + num1 + "num2:" + num2);
        }
    }
}

class FpCalculatorService {
    public int calculate2(Calculation calculation, int num1, int num2) {
        if (num1 > 10 && num2 < num1) { //보일러플레이트 부분을 그대로 가져
            calculation.nothingFunc();
            return calculation.calculate(num1, num2);
        } else {
            throw new IllegalArgumentException("Invalid input num1:" + num1 + "num2:" + num2);
        }
    }
}
