package cc.imnam.examples.java8.ep07_custom_fuctionalInterface;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class FunctionalInterfaceExamples3 {
    public static void main(String[] args) {
        final List<Product> products = Arrays.asList(
          new Product(1L, "A", new BigDecimal("10.00")),
                new Product(2L, "A", new BigDecimal("55.50")),
                new Product(3L, "A", new BigDecimal("17.45")),
                new Product(4L, "A", new BigDecimal("23.00")),
                new Product(5L, "A", new BigDecimal("110.99"))
        );

        final BigDecimal twenty = new BigDecimal("20"); // 비효율적인 방
//        final List<Product> result = new ArrayList<>();
//        for (final Product product : products) {
//            if (product.getPrice().compareTo(twenty) >= 0) {
//                // compareTo() == 0   : 비교 대상과 같다
//                // compareTo() == 1   : 비교 대상보다 크다
//                // compareTo() == -1  : 비교 대상보다 작다
//
//                result.add(product);
//            }
//        }
//        List<Product> result = filter(products, product -> product.getPrice().compareTo(twenty) >= 0);
//        System.out.println(result);
        System.out.println("products >= $20:" + filter(products, product -> product.getPrice().compareTo(twenty) >= 0));
        System.out.println("products <= $10:" + filter(products, product -> product.getPrice().compareTo(new BigDecimal("10")) <= 0));

        final List<Product> expensiveProducts = filter(products, product -> product.getPrice().compareTo(new BigDecimal("50")) >= 0);
//        final List<DiscountedProduct> discountedProducts = new ArrayList<>();
//        // 구 버전
//        for (final Product product : expensiveProducts) {
//            discountedProducts.add(new DiscountedProduct(product.getId(), product.getName(), product.getPrice()));
//        }

        // 신 버전
        final List<Product> discountedProducts = map(expensiveProducts, product -> new DiscountedProduct(product.getId(), product.getName(), product.getPrice().multiply(new BigDecimal("0.5"))));

        System.out.println("expensive   products :" + expensiveProducts);
        System.out.println("discounted  products :" + discountedProducts);

    }
    // generic method로 만들어야 여러 타입에서 사용할 수 있다
    private static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        final List<T> result = new ArrayList<>();
        for (final T t : list) {
            if (predicate.test(t)) {
                result.add(t);
            }
        }
        return result;
    }

    private static <T, R> List<R> map(List<T> list, Function<T, R> function) {
        final List<R> result = new ArrayList<>();
        // 구 버전
        for (final T t : list) {
            result.add(function.apply(t));
        }
        return result;
    }
}

@AllArgsConstructor
@Data // constuctor 가 기본형(parameter없는)밖에 안됨..
class Product {
    private Long id;
    private String name;
    private BigDecimal price;
}

@ToString(callSuper = true) // super 가 Product라는 이야기임.
class DiscountedProduct extends Product {

    public DiscountedProduct(Long id, String name, BigDecimal price) {
        super(id, name, price);
    }
}