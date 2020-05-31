package cc.imnam.examples.java8.ep08_streamAPI;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class parallelStreamTest {
    public static void main(String[] args) {

        final List dealMaxList = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
//        Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14)
//            .parallelStream()
//            .forEach(i -> {
//                System.out.println("idx:" + i
//                + " - " + Thread.currentThread().getName()
//                + " @ "+ new Date());
//                try {
//                    TimeUnit.SECONDS.sleep(1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            });
//          my macbook pro has 8  core.
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism" , "1");
        final long start = System.currentTimeMillis();

        dealMaxList.parallelStream()
                .forEach(i -> {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(
                        Thread.currentThread().getName()
                        + " - idx: " + i
                        + " @ " + new Date()
                    );
                });
        System.out.println("result: " +
                (System.currentTimeMillis() - start));
    }
}
