package cc.imnam.examples.java8.ep08_streamAPI;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
//import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class StreamExamples2_06 {
    public static void main(String[] args) {
        final List<Product> products = Arrays.asList(
            new Product(1L, "A", new BigDecimal("100.50")),
            new Product(2L, "B", new BigDecimal("23.00")),
            new Product(3L, "C", new BigDecimal("31.45")),
            new Product(4L, "D", new BigDecimal("80.20")),
            new Product(5L, "E", new BigDecimal("7.50"))
        );

        System.out.println("Products.price >= 30:" +
            products.stream()
                    .filter(product -> product.getPrice().compareTo(new BigDecimal("30")) >= 0)
                    .collect(toList())
        );

        System.out.println("=============================");
        System.out.println("Products.price >= 30: with joining() \n" +
            products.stream()
                    .filter(product -> product.getPrice().compareTo(new BigDecimal("30")) >= 0)
                    .map(product -> product.toString())// type 변환 or 값 등 변환이 일어날 때 : map() !! // 유지 일때는 identity()
                    .collect(joining("\n"))
        );

        // 합계구하기
        System.out.println("IntStream.sum():" +
        IntStream.of(1, 2, 3, 4, 5)
                .sum()
        );

        System.out.println("=============================");
        System.out.println("Total Price :"  +
            products.stream()
                // .reduce(BigDecimal.ZERO, (product1, product2) -> product1.getPrice().add(product2.getPrice()));
                // 오류 발생하는 이유 : Product 에 BigDecimal 를 인자로 받으려 하니
                .map(product -> product.getPrice())
                .reduce(BigDecimal.ZERO, (price1, price2) -> price1.add(price2))
        );
        // Element 를 하나하나 줄여나가서 하나만 남기기 떄문에 reduce 이다.
        //reduc(p1, p2) p1 : initial value, p2: arity가 2인 function으로 구성된다. function의 parameter는
        //전자가 이전결과값(or initial value) 후자가 이후 element를 뜻한다.

        System.out.println("=============================");
        System.out.println("Total Price of price >= 30 :"  +
        products.stream()
                .filter(product -> product.getPrice().compareTo(new BigDecimal("30")) >= 0)
                .map(product -> product.getPrice())
                .reduce(BigDecimal.ZERO, (price1, price2) -> price1.add(price2))
                // .reduce(BigDecimal.ZERO, BigDecimal::add)
        );

        // 매번 new BigDecimal("30")이 생성되기 때문에 variable 에 넣어 재사용하는게 좋음. 특히 BigDecimal 은
        // immutable object 이기 때문에 안전하게 재사용 할 수 있다.

        System.out.println("=============================");
        System.out.println("Total count Price of price >= 30 :"  +
                products.stream()
                        .filter(product -> product.getPrice().compareTo(new BigDecimal("30")) >= 0)
                        .count()
        );

        final OrderedItem item1 = new OrderedItem(1L, products.get(0), 1);
        final OrderedItem item2 = new OrderedItem(2L, products.get(2), 3);
        final OrderedItem item3 = new OrderedItem(3L, products.get(4), 10);

        final  Order order = new Order(1L, Arrays.asList(item1, item2, item3));

        System.out.println("=============================");
        System.out.println("order.totalPrice():" + order.totalPrice());

    }

}

@AllArgsConstructor
@Data
class Product {
    private Long id;
    private String name;
    private BigDecimal price;
}

@AllArgsConstructor
@Data
class OrderedItem {
    private Long id;
    private Product product;
    private int quantity;

    public BigDecimal getTotalPrice() {
        return product.getPrice().multiply(new BigDecimal(quantity));
    }
}

@AllArgsConstructor
@Data
class Order {
    private Long id;
    private List<OrderedItem> items;

    public BigDecimal totalPrice() {
        return items.stream()
                    .map(orderedItem -> orderedItem.getTotalPrice())
                    .reduce(BigDecimal.ZERO, (price1, price2) -> price1.add(price2));
    }
}

