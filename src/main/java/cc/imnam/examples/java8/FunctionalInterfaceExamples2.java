package cc.imnam.examples.java8;

public class FunctionalInterfaceExamples2 {
    public static void main(String[] args) {
        //println(1, 2, 3, (i1, i2, i3) -> Integer.valueOf(i1 + i2 + i3).toString());
        println(1, 2, 3, (integer, integer2, integer3) -> String.valueOf(integer + integer2 + integer3));
        println("Area is ", 12, 20, (message, length, width) -> (message + (length * width)));

        println(1L, "Nam", "test@mail.com", (id, name, email) -> ("User Info: ID=" + id
                + ", name=" + name + ", email" + email));
    }

    //private void println >> to generic method
    private static <T1, T2, T3> void println(T1 t1, T2 t2, T3 t3,Function3<T1, T2, T3, String> function3) {
        System.out.println(function3.apply(t1, t2, t3));
    }

}

//e.g. BiFunction
@FunctionalInterface    // << Functional Interface를 만들 때는 @FunctionalInterface annotation을 꼭 사용하는걸 추천!
interface Function3<T1, T2, T3, R> {
    R apply(T1 t1, T2 t2, T3 t3);

    //void print(int i);
}