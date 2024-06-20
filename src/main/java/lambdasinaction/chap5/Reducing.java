package lambdasinaction.chap5;

import lambdasinaction.chap4.*;

import java.io.Serializable;
import java.util.stream.*;
import java.util.*;

import static lambdasinaction.chap4.Dish.menu;

public class Reducing {

    public static void main(String... args) {

        List<Integer> numbers = Arrays.asList(3, 4, 5, 1, 2);
        int sum = numbers.stream().reduce(0, (a, b) -> a + b);
        System.out.println(sum);

        int sum2 = numbers.stream().reduce(0, Integer::sum);
        System.out.println(sum2);

        int max = numbers.stream().reduce(0, (a, b) -> Integer.max(a, b));
        System.out.println(max);

        Optional<Integer> min = numbers.stream().reduce(Integer::min);
        min.ifPresent(System.out::println);

        //有装箱的成本
        int calories = menu.stream()
                .map(Dish::getCalories)
                .reduce(0, Integer::sum);
        System.out.println("Number of calories:" + calories);

        //排除装箱的成本 ，如果流是空的那么sum默认返回0
        //数值流 IntStream/ doubleStream/ LongStream
        int sum1 = menu.stream()
                .mapToInt(Dish::getCalories)
                .sum();
        System.out.println("没有装箱成本的操作 ：" + sum1);

        //使用特化流 IntStream
        IntStream intStream = menu.stream().mapToInt(Dish::getCalories);
        //将特化流转变成非特化流 ，boxed
        Stream<Integer> boxed = intStream.boxed();

        //当求一组最大值时没有,(如何区分没有元素的流和最大值真的是0的流呢) 这时候就是Optional类，它也有特殊化类
        int i = menu.stream()
                .mapToInt(Dish::getCalories)
                .max().orElse(12);


        //特殊化流，生成数据过程中的差异  (包含两端)
        LongStream rangeClosed = LongStream.rangeClosed(1, 100).filter(n -> n % 2 == 0);
        System.out.println("rangeClosed : " + rangeClosed.count());

        //特殊化流，生成数据过程中的差异 (只包含一端)
        LongStream range = LongStream.range(1, 100).filter(n -> n % 2 == 0);
        System.out.println("range : " + range.count());


        //构建一个勾股定理 (t) 先进行了过滤 ，两次平方根
        Stream<int[]> pythagoreanTriples =
                IntStream.rangeClosed(1, 100).boxed()
                        .flatMap(a ->
                                IntStream.rangeClosed(a, 100)
                                        .filter(b -> Math.sqrt(a*a + b*b) % 1 == 0)
                                        .mapToObj(b ->
                                                new int[]{a, b, (int)Math.sqrt(a * a + b * b)})
                        );

        //如上勾股定理不是最优，去掉多余平方梗，放在最后再过滤
        Stream<double[]> integerStream = IntStream.rangeClosed(1, 100).boxed()
                .flatMap(a ->
                        IntStream.rangeClosed(a, 100)
                                .mapToObj(b -> new double[]{a, b,  Math.sqrt(a * a + b * b)})
                                .filter(t -> t[2] % 1 == 0));
        integerStream.limit(5).forEach(t -> System.out.println("[" + t[0] + " ," + t[1] + " ," + t[2] + "]"));

        //如何得到一个流 ,任意类型！
        Stream<? extends Serializable> ddd = Stream.of("DDD", "d", 2, 333, "6666", false);
        Stream<String> stringStream = ddd.map(s -> s.toString());
        stringStream.forEach(s1-> System.out.println("===> : "+s1));

        //空流
        Stream<Object> empty = Stream.empty();
        int[] numbers1 = {2, 3, 5, 7, 11, 13};
        int ss = Arrays.stream(numbers1).sum();



        List<Dish.Type> collect = menu.stream().map(Dish::getType).collect(Collectors.toList());
        System.out.println(collect.get(0));



    }
}
