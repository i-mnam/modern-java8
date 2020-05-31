package cc.imnam.examples.java8.ep08_streamAPI;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class v05_StreamExamples5Parallel {
    private  static void slowDown() {
        try {
            TimeUnit.MICROSECONDS.sleep(10L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static long interactiveSum(long n) {
        long result = 0;
        for (long i = 0; i <= n; i++) {
            result += i;
            slowDown();
        }
        return result;
    }

    private static long sequentialSum(long n) {
        return Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .peek(i -> slowDown())
                .reduce(Long::sum)
                .get();
    }

    public static long parallelSum(long n) {
        return Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .parallel()
                .peek(i -> slowDown())
                .reduce(Long::sum)
                .get();
    }

    public static long rangedSum(long n) {
        return LongStream.rangeClosed(1, n)
                .peek(i -> slowDown())
                .reduce(Long::sum)
                .getAsLong();
    }

    public static long parallelRangedSum(long n) {
        return LongStream.rangeClosed(1, n)
                .parallel()
                .peek(i -> slowDown())
                .reduce(Long::sum)
                .getAsLong();
    }

    public static void main(String[] args) {
        final long n = 1000;
        final long start = System.currentTimeMillis();

        System.out.println((1 + n) * (n / 2));
        System.out.println("Gauss Way :" + (System.currentTimeMillis() - start));

        final long start1 = System.currentTimeMillis();
        System.out.println("interactiveSum(n):" + interactiveSum(n));
        System.out.println(":" + (System.currentTimeMillis() - start1) + "ms") ;

        final long start2 = System.currentTimeMillis();
        System.out.println("sequentialSum(n):" + sequentialSum(n));
        System.out.println(":" + (System.currentTimeMillis() - start2) + "ms") ;

        final long start3 = System.currentTimeMillis();
        System.out.println("parallelSum(n):" + parallelSum(n));
        System.out.println(":" + (System.currentTimeMillis() - start3) + "ms") ;

        final long start4 = System.currentTimeMillis();
        System.out.println("rangedSum(n):" + rangedSum(n));
        System.out.println(":" + (System.currentTimeMillis() - start4) + "ms") ;

        final long start5 = System.currentTimeMillis();
        System.out.println("parallelRangedSum(n):" + parallelRangedSum(n));
        System.out.println(":" + (System.currentTimeMillis() - start5) + "ms") ;


    }
}
