package cc.imnam.examples.java8.ep08_streamAPI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class StreamExample_04 {
    public static void main(String[] args) {
        //Stream.of(1, 2, 3, 4, 5).forEach(i -> System.out.print(i + " "));

        final List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5
        , 6, 7, 8, 9, 10);
        Integer result = null;
        for (final Integer number : numbers) {
            if (number > 3 && number < 9) {
                final Integer newNumber = number * 2;
                if (newNumber > 20) {
                    result = newNumber;
                    break;
                }
            }
        }
        System.out.println("Imperative Result:" + result);

        System.out.println("============================");

        System.out.println("Functional Result (with logging)" +
                numbers.stream()
                        .filter(number -> {
                            System.out.println("number > 3");
                            return number > 3;
                        })
                        .filter(number -> {
                            System.out.println("number < 9");
                            return number < 9;
                        })
                        .map(number -> {
                            System.out.println("number * 2");
                            return number * 2;
                        })
                        .filter(number -> {
                            System.out.println("number > 10");
                            return number > 10;
                        })
                        .findFirst()
        );

        // java8 부터는 Collection들에게 기본적으로 stream() method 가 추가되었다.
        System.out.println("FUnctional Result: " + numbers.stream() //return stream object
                .filter(num -> num > 3)
                .filter(num -> num < 9)
                .map(num -> num * 2)
                .filter(num -> num > 20)
                .findFirst() // return type java.util.Optional !Integer
        );
        // findRirst()에서 타겟이 없을 수 도 있어서 Optional
        // target이 null 일때 Null point exception 문제가 나올 수 있음
        // Functional programming 에서는 null 이 존재하지 않음
        // 결과적으로 null 을 직접적으로 다루는 것을 권장하지 않음

        // 로직을 보기엔 편해졌지만 처리하는데 퍼포먼스 속도 문제나 효율적으로 보았을 때 확실히 나은 것인지 살펴봄

        // 단순 연산 예상 횟수 비교 I:15 VS F:27
        // 알고보니 실제 연산 횟수 F:15
        // Stream 은 Collection Builder + lazy Collection 임.
        // 실제로 호출할 떄 (findFirst() 호출시) 연산 시작함..ㅋㅋㅋ 방학숙제


        System.out.println("============================");

        final List<Integer> greaterThan3 = filter(numbers, i -> i > 3);
        final List<Integer> lessThan9 = filter(greaterThan3, i -> i < 9);
        final List<Integer> doubled = map(lessThan9, i -> i * 2);
        final List<Integer> greaterThan10 = filter(doubled, i -> i > 10);
        System.out.println("greaterThan10 :" + greaterThan10.size() + "//" + greaterThan10.get(0));
        for (Integer i : greaterThan10) {
            System.out.println(".." + i);
        }

        System.out.println("============================");

        final AtomicInteger count = new AtomicInteger(1);

        final List<Integer> greaterThan3Log = filter(numbers, i -> {
            System.out.println(count.getAndAdd(1) + ": i > 3");
            return i > 3;
        });

        final List<Integer> lessThan9Log = filter(greaterThan3Log, i -> {
           System.out.println(count.getAndAdd(1) + ": i < 9");
           return i < 9;
        });

        final List<Integer> doubledLog = map(lessThan9Log, i -> {
            System.out.println(count.getAndAdd(1) + ": i * 2");
            return i * 2;
        });

        final List<Integer> greaterThan10Log = filter(doubledLog, i -> {
            System.out.println(count.getAndAdd(1) + ": i > 10");
            return i > 10;
        });

        System.out.println("============================");

        final List<Integer> oneMthod = filter(map(filter(filter(numbers, i -> i > 3), i -> i < 9), i -> i * 2), i -> i > 10);
        System.out.println("one method: " + oneMthod);
    }

    private static <T> List<T> filter(List<T> list, Predicate<? super T> predicate) {
        final List<T> result = new ArrayList<>();
        for (final T t : list) {
            if (predicate.test(t)) {
                result.add(t);
            }
        }
        return result;
    }

    private static <T, R> List<R> map(List<T> list, Function<T, R> mapper) {
        final List<R> result = new ArrayList<>();
        for (final T t : list) {
            result.add(mapper.apply(t));
        }
        return result;
    }
}
