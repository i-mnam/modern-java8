package cc.imnam.examples.java8.ep08_streamAPI;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors; // 유용한 static method 들이 많음.
import java.util.stream.Stream;

// import static java.util.stream.Collectors.*;

public class StreamExample1_05 {
    public static void main(String[] args) {
        // e.g. 1
        //final List<Integer> numbers = Arrays.asList(1, 2, 3,);
        //numbers.stream().filter();

        // e.g. 2
        //final Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5);

        System.out.println("collect(Collectors.toList())" +
            Stream.of(1, 2, 3, 4, 5)
                    .filter(i -> i > 2)     // Int
                    .map(i -> i * 2)        // Int
                    .map(i -> "#" + i)      // Str
                    .collect(Collectors.toList())
        );

        System.out.println("collect(Collectors.toSet())" +
            Stream.of(1, 3, 3, 5, 5)
                    .filter(i -> i > 2)
                    .map(i -> i * 2)
                    .map(i -> "#" + i)
                    .collect(Collectors.toSet())
            +"//" + Stream.of(1, 3, 3, 5, 5)
            .filter(i -> i > 2)
            .map(i -> i * 2)
            .map(i -> "#" + i)
            .collect(Collectors.toSet()).getClass()
        );

        System.out.println("collect(Collectors.joining())" +
            Stream.of(1, 3, 3, 5, 5)
                    .filter(i -> i > 2)
                    .map(i -> i * 2)
                    .map(i -> "#" + i)
                    .collect(Collectors.joining(", "))      // cmd p
        );

        System.out.println("collect(Collectors.joining(\", \", \"[\", \"]\"))" +
            Stream.of(1, 3, 3, 5, 5)
                    .filter(i -> i > 2)
                    .map(i -> i * 2)
                    .map(i -> "#" + i)
                    .collect(Collectors.joining(", ", "[", "]"))
        );

        System.out.println("collect(Collectors.joining()) with distinct()" +
            Stream.of(1, 3, 3, 5, 5)
                    .filter(i -> i > 2)
                    .map(i -> i * 2)
                    .map(i -> "#" + i)
                    .distinct()
                    .collect(Collectors.joining(", ", "[", "]"))
        );

        System.out.println("collect(Collectors.toList()) with distinct()" +
            Stream.of(1, 3, 3, 5, 5)
                    .filter(i -> i > 2)
                    .map(i -> i * 2)
                    .map(i -> "#" + i)
                    .distinct()
                    .collect(Collectors.toList())
         );

        System.out.println(
             Stream.of(1, 2, 3, 4, 5)// primitive >>compile 할  auto boxing >> Integer Object :: generic method
                .filter(i -> i == 3) //3은 primitive 이고 i는  Integer.valueOf() 로 primitive 로 비교 함
                .findFirst()
        );

        final Integer integer3 = 3;
        System.out.println(
            Stream.of(1, 2, 3, 4, 5) // new Int(1) - X // Int.valueOf(1
                    .filter(i -> {
                        return i == integer3;
                    }) // obj == obj : obj을가지고 equality연산을 하면 Identity 비교를 함 (메모리 레퍼런스)
                    .findFirst()
        );
        // java 의 auto boxing VS unBoxing 원리
        //33:29
        // valueOf()

        final Integer integer127 = 127;
        System.out.println(
            Stream.of(1, 2, 3, 4, 5, 127)
                .filter(i -> i == integer127)
                .findFirst()
        );

        final Integer integer128 = 128;
        System.out.println(
            Stream.of(1, 2, 3, 4, 5, 128)
                .filter(i -> i == integer128)
                .findFirst()
        );

        System.out.println(
                Stream.of(1, 2, 3, 4, 5, 128)
                        .filter(i -> i.equals(integer128))
                        .findFirst()
        );

        System.out.println("count()" +
                Stream.of(1, 2, 3, 4, 5)
                        .filter(i -> i > integer3)
                        .count()
        );

        final List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        for (Integer i : numbers) {
            System.out.println("i = " + i);
        } // external iterator for each

        System.out.println("forEach(i -> ");
        Stream.of(1, 2, 3, 4, 5).forEach(i -> System.out.println(i));
        // stream 자체가 internal iterator 라서 다본인 자체를 (내부적으로) 반복하며 element 를 가지고 무언가를 할 수 있
    }
}
