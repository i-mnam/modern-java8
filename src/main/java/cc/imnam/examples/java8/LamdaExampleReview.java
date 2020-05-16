package cc.imnam.examples.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class LamdaExampleReview {
    public static void main(String[] args) {
        filteringTest();
    }

    private static void filteringTest() {
        System.out.println("\nLambdaExampleReview.filteringTest");
        final List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6,
                7, 8, 9, 10);
        /* 예전 방법으로 2보다 큰 정수 찾기 */
        final List<Integer> result = new ArrayList<>();
        for (final Integer number : list) {
            if (number > 2) {
                result.add(number);
            }
        }
        System.out.println("n > 2 :" + result);

        /* 예전 방법으로 7보다 작은 정수 찾기 */
        final List<Integer> result2 = new ArrayList<>();
        for (final Integer number : list) {
            if (number < 7) {
                result2.add(number);
            }
        }
        System.out.println("n < 7 :" + result2);

        /* 람다를 이용해서 2보다 큰 정수 찾기 */
        final Predicate<Integer> greaterThan2 = n -> n > 2;
        final  List<Integer> result3 = filter(list, greaterThan2);
        System.out.println("n > 2 : " + result3);

        /* 람다를 이용해서 7보다 작은 정수 찾기 (기존 filter 메소드 재사용) */
        final Predicate<Integer> lessThan7 = n -> n < 7;
        final List<Integer> result4 = new ArrayList<>();
        for (Integer number : list) {
            if (lessThan7.test(number)) {
                result4.add(number);
            }
        }
        System.out.println("n < 7 : " + result4);

        /* Function composition:
         * 2개의 함수를 합쳐서 쉽게
         * 2보다 크고 7보다 작은 정수 찾기
         */
        final List<Integer> result5 = filter(list, greaterThan2.and(lessThan7));
        System.out.println("2 < n < 7 : " + result5);

        /* Closure: 람다 바디에서 람다 바깥에 있는 factor (free variable) 접근
         * Note: 엄밀히 따지면 자바의 Closure는 variable이 아니라 거기 들은 값(value)에
         * 접근 하는겁니다 (capturing value).
         */
        int factor = 10; // effectively final
        final Comparator<Integer> comparator = (o1, o2) -> o1 > factor ? o1 : o1.compareTo(o2);
        // 이게 왜 클로져인지 모르겠다..
    }

    private static <T> List<T> filter(final List<T> list, final Predicate<T> predicate) {
    //private static List<T> filter(final List<T> list, ...
        final List<T> result = new ArrayList<>();
        for (final T value : list) {
            // predicate의 test를 통과한 경우만 저장
            if (predicate.test(value)) {
                result.add(value);
            }
        }
        return result;
    }
}
