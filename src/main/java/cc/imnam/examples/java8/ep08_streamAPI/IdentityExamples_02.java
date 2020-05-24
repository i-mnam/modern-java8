package cc.imnam.examples.java8.ep08_streamAPI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class IdentityExamples_02 {

    public static void main(String[] args) {
        final List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        /**
         *  Function 의 identity() 예제
         * */
        System.out.println("mapOld mapOld(numbers, i -> i * 2):\n" + mapOld(numbers, i -> i * 2));
        System.out.println("mapOld mapOld(numbers, null):\n" + mapOld(numbers, null));

        System.out.println("===================");

        System.out.println("map map(numbers, i -> i * 2)\n" +
                map(numbers, i -> i * 2)
        );
        System.out.println("map map(numbers, i -> i)\n" +
                map(numbers, i -> i)
        );
        System.out.println("map map(numbers, null):\n" +
                map(numbers, null));
        System.out.println("mapNew mapNew(numbers, Function.identity())\n" +
                mapNew(numbers, Function.identity())
        );
    }

    private static <T, R> List<R> mapOld (final List<T> list, final Function<T, R> mapper) {
        final List<R> result;

        if(mapper != null) {
            result = new ArrayList<>();
        } else {
            result = new ArrayList<>((List<R>) list); // casting..
        }

         if (result.isEmpty()) {
             for (final T t : list) {
//                 result.add((R)t);
                 result.add(mapper.apply(t));
             }
         }
         return result;
    }

    private static <T, R> List<R> map (final List<T> list, Function<T, R> mapper) {
        //final List<R> result = new ArrayList<>();
        final Function<T, R> function;

        if (mapper != null) {
            function = mapper;
        } else {
            function = t -> (R) t;
        }

        final List<R> result = new ArrayList<>();
        for (final T t : list) {
            result.add(function.apply(t)); // Function's applu()!!
        }

        return result;
    }

    private static <T, R> List<R> mapNew (final List<T> list, Function<T, R> mapper) {
        final List<R> result = new ArrayList<>();

        for (final T t : list) {
            result.add(mapper.apply(t));
        }

        return result;
    }

}

