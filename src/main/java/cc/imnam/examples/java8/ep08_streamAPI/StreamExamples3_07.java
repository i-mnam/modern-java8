package cc.imnam.examples.java8.ep08_streamAPI;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;


public class StreamExamples3_07 {
    public static void main(String[] args) {
        // Anonymous Class 의 경우 class 외부의 variable 에 접근하기 위해서는 그  variable 이  final variable 이어야만
        // 한다. array 의 경우 array 자체가 object 이고 그 안의 값 변경은 array object 의 상태변경이라고 생각하자.

        /*
        System.out.println("======================================");
        final int[] sum1 = { 0 };
            IntStream.range(0, 100)
                .forEach(i -> sum1[0] += i);

        System.out.println("Stream sum: " + sum1[0]);

        final int[] sum2 = { 0 };
            IntStream.range(0, 100)
                .parallel()
                .forEach(i -> sum2[0] += i);
        System.out.println("parallel Stream sum (with side-effect):" + sum2[0]);

        //final int[] sum3 = { 0 };
        System.out.println("Stream sum (no side-effect):" +
            IntStream.range(0, 100)
                .sum()
        );

        System.out.println("parallel Stream sum (no side-effect):" +
                IntStream.range(0, 100)
                        .parallel()
                        .sum()
        );

        System.out.println("Stream :");
        final long start = System.currentTimeMillis();
        Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8)
                .stream()
                .map(i -> { // for test
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return i;
                })
                .forEach(i -> System.out.println(i));
        System.out.println("result :" + (System.currentTimeMillis() - start));

        System.out.println("======================================");
        System.out.println("Parallel Stream (8elements):");
        final long start2 = System.currentTimeMillis();
        Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8)
                .parallelStream()
                .map(i -> { // for test
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return i;
                })
                .forEach(i -> System.out.println(i));
        System.out.println("result :" + (System.currentTimeMillis() - start2));

        System.out.println("Parallel Stream (9elements) :");
        final long start3 = System.currentTimeMillis();
        Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9)
                .parallelStream()
                .map(i -> { // for test
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return i;
                })
                .forEach(i -> System.out.println(i));
        System.out.println("result :" + (System.currentTimeMillis() - start3));
*/
        System.out.println("======================================");
        System.out.println("Parallel Stream (8elements) with parallelism: 7 (core 8)");
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "3");
        final long start4 = System.currentTimeMillis();
        Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8)
                .parallelStream()
                .map(i -> { // for test
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return i;
                })
                .forEach(i -> System.out.println(i));
        System.out.println("result :" + (System.currentTimeMillis() - start4));

        System.out.println("Parallel Stream (8elements) with parallelism: 3 (core 4)");
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "7");
        final long start5 = System.currentTimeMillis();
        Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8)
                .parallelStream()
                .map(i -> { // for test
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return i;
                })
                .forEach(i -> System.out.println(i));
        System.out.println("result :" + (System.currentTimeMillis() - start5));

        System.out.println("Parallel Stream (8elements) with parallelism: 1 (core 2)");
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "1");
        final long start6 = System.currentTimeMillis();
        Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8)
                .parallelStream()
                .map(i -> { // for test
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return i;
                })
                .forEach(i -> System.out.println(i));
        System.out.println("result :" + (System.currentTimeMillis() - start6));
        // setProperty() 가 적용되지 않아 다른 class 에서 예제 테스트 하였음


    }
}
