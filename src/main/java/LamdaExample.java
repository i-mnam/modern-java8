import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.joining;

public class LamdaExample {
    public static void main(String[] args) {
        final List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12,
                13, 14, 15, 16, 17, 18, 19, 20);
        final int repeat = 5;
        System.out.println("\nLambdaExample.raceCondition");

        final StringBuilder stringBuilder = new StringBuilder();
//        final int size = numbers.size();
//        for (int i = 0; i < size; i++) {
//            stringBuilder.append(numbers.get(i));
//            if(i != size - 1) {
//                stringBuilder.append(" : ");
//            }
//        }
        final String separator = " : ";
        for (Integer number : numbers) {
            stringBuilder.append(number).append(separator);
        }

        final int stringLength = stringBuilder.length();
        if (stringLength > 0) {
            stringBuilder.delete(stringLength - separator.length(),
                    stringLength);
        }

        System.out.println(stringBuilder.toString());
        System.out.println("============================");

        final String result = numbers.stream()
                .map(String::valueOf)
                .collect(joining(" : "));
        System.out.println("result:\n" + result);
    }
}