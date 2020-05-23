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
        final Product productA = new Product(1L, "A", new BigDecimal("10.00"));
        final Product productB = new Product(2L, "A", new BigDecimal("55.50"));
        final Product productC = new Product(3L, "A", new BigDecimal("17.45"));
        final Product productD = new Product(4L, "A", new BigDecimal("23.00"));
        final Product productE = new Product(5L, "A", new BigDecimal("110.99"));

        final List<Product> products = Arrays.asList(
                productA,
                productB,
                productC,
                productD,
                productE
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
        //final List<Product> discountedProducts = map(expensiveProducts, product -> new DiscountedProduct(product.getId(), product.getName(), product.getPrice().multiply(new BigDecimal("0.5"))));
        final List<DiscountedProduct> discountedProducts = map(expensiveProducts, product -> new DiscountedProduct(product.getId(), product.getName(), product.getPrice().multiply(new BigDecimal("0.5"))));

        System.out.println("expensive   products :" + expensiveProducts);
        System.out.println("discounted  products :" + discountedProducts);

        final Predicate<Product> lessThanOrEqualTo30 = product -> product.getPrice().compareTo(new BigDecimal("30")) <= 0;
        System.out.println("products <= $30:" + filter(discountedProducts, lessThanOrEqualTo30));
        System.out.println("products <= $30:" + filter(products, lessThanOrEqualTo30));//incompatible equality constraint 호환이 되지 않는 평등제약


        // product들의 price만 모아 총합을 구해보자 1 - 구버
        final List<BigDecimal> prices = map(products, product -> product.getPrice());
        BigDecimal total = BigDecimal.ZERO;
        for (final BigDecimal price : prices) {
            total = total.add(price);
        }
        System.out.println("total : " + total);


        // product들의 price만 모아 총합을 구해보자 2 price의 타입에 상관없이 총합을 구해볼 수 있도록??!


        final BigDecimal totalMeResult = total_me(products, product -> product.getPrice());
        System.out.println("totalMeResult :" + totalMeResult);

        final BigDecimal totalKevinResult = total_kevin(products, product -> product.getPrice());
        System.out.println("totalKevinResult :" + totalKevinResult);

        final BigDecimal totalDiscountedProducts = total_me(discountedProducts, product -> product.getPrice());
        System.out.println("totalDiscountedProducts :" + totalDiscountedProducts);



        // order 활용한 예제
        final Order order = new Order(1L, "on-1234", Arrays.asList(
                new OrderedItem(1L, productA, 2),
                new OrderedItem(2L, productC, 1),
                new OrderedItem(3L, productD, 10)
        ));

        System.out.println("order total :" + order.totalPrice());

        // oder 구 버전
        BigDecimal orderTotal = BigDecimal.ZERO;
        for (OrderedItem item : order.getOrderedItems()) {
            orderTotal = orderTotal.add(item.getProduct().getPrice().multiply(new BigDecimal(item.getQuantity())));
        }
        System.out.println("order total previous version :" + orderTotal);
    }

    // generic method로 만들어야 여러 타입에서 사용할 수 있다
    // super class를 이용한 generic method >> 활용도가 커진다
    private static <T> List<T> filter(List<T> list, Predicate<? super T> predicate) {
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

    private  static <T> BigDecimal total_kevin(List<T> list, Function<T, BigDecimal> mapper) {
        BigDecimal total = BigDecimal.ZERO;
        for (T t : list) {
            total = total.add(mapper.apply(t));
        }
        return total; // 1:08:22
    }



    private static <T> BigDecimal total_me(List<T> list, Function<T, BigDecimal> mapper) {
        BigDecimal result = new BigDecimal("0");
        for (T t : list) {
            result = result.add(mapper.apply(t));
        }
        return result;
    }

    @AllArgsConstructor
    @Data
    static class OrderedItem {
        private Long id;
        private  Product product;
        private  int quantity;

        public BigDecimal getItemTotal() {
            return product.getPrice().multiply(new BigDecimal(quantity));
        }
    }

    @AllArgsConstructor
    @Data
    static class Order {
        private long id;
        private String orderNumber;
        private List<OrderedItem> orderedItems;

        // orderTotal() 이 필요한데 옛날 방식이라면 service object에서 이 부분을 구현 할 텐데,
        // orderTotal() 은 order 와 관계된 것이니 order 가 알고 있으면 된다.
        public BigDecimal totalPrice() {
//            // total_me(orderedItems, orderedItem -> orderedItem.getProduct().getPrice()); // >> quantity가 반영이 되지 않았어.
//            for (OrderedItem item : orderedItems) {
//                // do something
//            }
            //return orderedItems.get
            return total_me(orderedItems, orderedItem -> orderedItem.getItemTotal());
        }
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

