package cc.imnam.examples.java8.ep07_custom_fuctionalInterface;

import java.math.BigDecimal;

public class CustomFunctionalInterfaceExamples {
    public static void main(final String[] args) {

        BigDecimalToCurrency bigDecimalToCurrency = bd -> "$" + bd.toString();
        System.out.println(bigDecimalToCurrency.toCurrency(BigDecimal.valueOf(500)));
        System.out.println(bigDecimalToCurrency.toCurrency(new BigDecimal("120.00")));


        // Invalid #1 type 추론이 가능한 형태
        final InvalidFunctionalInterface anonymousClass = new InvalidFunctionalInterface() {
            @Override
            public <T> String mkString(T value) {
                return value.toString();
            }
        };
        System.out.println("anonymous class: " + anonymousClass.mkString(123));


        // Invalid #2 람다 표현이 불가능한 functional interface 타입.
        // inal InvalidFunctionalInterface invalidFunctionalInterface = value -> value.toString();
        // target method is generic (메소드여서 불가능하다) >> 람다로 표현 불가능.
        // System.out.println("invalid class: " + invalidFunctionalInterface.mkString(123));

    }
}

@FunctionalInterface
interface BigDecimalToCurrency { // << 꼭 generic type이 필요한건 아니다.
    String toCurrency(BigDecimal value);
}

@FunctionalInterface
interface InvalidFunctionalInterface { // lambda expression을 사용한 functional interface가 될 수 없다.
    <T> String mkString(T value);
}