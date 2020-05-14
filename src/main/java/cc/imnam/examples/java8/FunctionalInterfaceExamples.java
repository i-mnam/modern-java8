package cc.imnam.examples.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class FunctionalInterfaceExamples {
    public static void main(String[] args) {
// functional interface example
// This is a functional interface and can therefore be used as the assignment target
// for a lambda expression or method reference.
// 1)
//        Function<String, Integer> toInt = new Function<String, Integer>() {
//            @Override
//            public Integer apply(final String value) {
//                return Integer.parseInt(value);
//            }
//        };
// 2)
//        Function<String, Integer> toInt = (final String value) -> {
//            return Integer.parseInt(value);
//        };
// 3) lambda expression version
        final Function<String, Integer> toInt = value -> Integer.parseInt(value);
        final Integer number = toInt.apply("100");
        System.out.println("number:" + number);

        //final Function<Integer, Integer> identity = Function.identity();
        final Function<Integer, Integer> identity = t -> t;
        System.out.println("Identity:" + identity.apply(999) + "// type:" + identity.apply(83).getClass());
// another example : Consumer
//        final Consumer<String> print = new Consumer<String>() {
//            @Override
//            public void accept(String value) {
//                System.out.println("Consumer:" + value);
//            }
//        };
        final Consumer<String> print = value -> System.out.println("lambda expression test:" + value);
        final Consumer<String> greetings = value -> System.out.println("Hello " + value);

        print.accept("Consumer test222!");
        greetings.accept("World");
        greetings.accept("Nam!");
// void cannot be converted to Void
//        final Function<String, Void> functionPrintTest = value -> System.out.println("Function Void Test:" + value);

        Predicate<Integer> isPositive = i -> i > 0;
        System.out.println("t1: " + isPositive.test(1));
        System.out.println("t2: " + isPositive.test(0));
        System.out.println("t3: " + isPositive.test(-1));

// Function으로 대체 가능함에도 불구하고 왜 Predicate로 사용하는지에 대해

        List<Integer> numbers = Arrays.asList(-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5);

        List<Integer> positiveNumbers = new ArrayList<>();
        for(Integer num: numbers) {
            if(isPositive.test(num)) {
                positiveNumbers.add(num);
            }
        }
        System.out.println("positive numbers:" + positiveNumbers);

        Predicate<Integer> lessThan3 = i -> i < 3;
        List<Integer> numbersLessThan3 = new ArrayList<>();
        for(Integer num: numbers) {
            if(lessThan3.test(num)) {
                numbersLessThan3.add(num);
            }
        }
        System.out.println("lessThan3 numbers:" + numbersLessThan3);

        ///////////////////////////// boilerplate 해결한 방
        System.out.println("is positive : " + filter(numbers, isPositive));
        System.out.println("less than 3 : " + filter(numbers, lessThan3));

// lazy evaluation이 가능하다 : Supplier
        final Supplier<String> helloSupplier = () -> "Hello ";
        System.out.println(helloSupplier.get() + "World!");


//        printValidIndex(0, "Nam");
//        printValidIndex(1, "Nam");
//        printValidIndex(-1, "Nam");

        long start = System.currentTimeMillis();
        printValidIndex(0, getVeryExpensiveValue());
        printValidIndex(-1, getVeryExpensiveValue());
        printValidIndex(-2, getVeryExpensiveValue());
        System.out.println("It took " + (System.currentTimeMillis() - start) / 1000 +" seconds.");
// 둘의 차이 : lazy evalueation을 통해서 불필요한 계산을 줄일 수 있다. 불필요한 자원 낭비를 줄일 수 있다.
        start = System.currentTimeMillis();
        printValidIndexUpgraded(0, new Supplier<String>() {
            @Override
            public String get() {
                return getVeryExpensiveValue();
            }
        });
        printValidIndexUpgraded(-1,() ->  getVeryExpensiveValue());
        printValidIndexUpgraded(-2, () -> getVeryExpensiveValue());
        System.out.println("It took " + (System.currentTimeMillis() - start) / 1000 +" seconds.");
    }

    // boilerplate code가 많음으로 이를 수정해본다
    private static <T> List<T> filter(List<T> list, Predicate<T> filterIns) {
        List<T> result = new ArrayList<>();
        for (T input : list) {
            if(filterIns.test(input)) {
                result.add(input);
            }
        }
        return result;
    }

    private static void printValidIndex(int number, String value) {
        if(number >= 0) {
            System.out.println("The value is " + value + ".");
        } else {
            System.out.println("Invalid");
        }
    }

    private static void printValidIndexUpgraded(int number, Supplier<String> valueSupplier) {
        if(number >= 0) {
            System.out.println("The value is " + valueSupplier.get() + ".");
        } else {
            System.out.println("Invalid");
        }
    }

    private static String getVeryExpensiveValue() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Let's just say it has very expensive calculation here!
        return "EXPENSIVE!!!";
    }


}
