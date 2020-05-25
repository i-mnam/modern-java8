package cc.imnam.examples.java8.ep08_streamAPI;

import java.math.BigDecimal;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamPrelude2_03 {

    public static void main(String[] args) {
        IntStream.rangeClosed(1, 10).forEach(i -> System.out.print(i + " "));
        //IntStream.iterate(1, i -> i + 1).forEach(i -> System.out.print(i + " "));
        //무한대 collection //int 자체의 리미트는 있지.

        //Stream.iterate(BigDecimal.ONE, i -> i.add(BigDecimal.ONE)).forEach(i -> System.out.print(i + " "));
        // 무한대 collection이 가능해짐

        //java repl(read evaluation print loop)

        System.out.println(Stream.iterate(1, i -> i + 2).getClass());
        // class java.util.stream.ReferencePipeline$Head
    }
}
