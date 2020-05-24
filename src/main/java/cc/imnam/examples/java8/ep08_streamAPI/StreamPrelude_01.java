package cc.imnam.examples.java8.ep08_streamAPI;

public class StreamPrelude_01 {
    public static void main(String[] args) {
        /**
         * 다른 입력값, 같은 결과값
         * 입력: -1, 1
         * 결과: 1
         */
        final int abs1 = Math.abs(-1);
        final int abs2 = Math.abs(1);

        System.out.println("abs1:" + abs1);
        System.out.println("ab2:" + abs2);
        System.out.println("abs1 == abs2 is " + (abs1 == abs2) +".");

        /**
         * 입력값: -2147483648
         * 결과값: -2147483648
         * WARNING!: 사용하실때 주의하세요!
         *
         * plus)    java integer : signed integer 이며, 32bit 여서 아래와 같은 결과가 나옴.
         *          Math.abs() 함수도  functional programing 의 조건을 만족시키지 못함.
         */

        System.out.println(Integer.MIN_VALUE);
        System.out.println(Integer.MAX_VALUE);
        final int minInt = Math.abs(Integer.MIN_VALUE);
        System.out.println("abs minInt:" + minInt);

    }
}
