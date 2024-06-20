package lambdasinaction.chap5;

import lambdasinaction.chap4.*;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static lambdasinaction.chap4.Dish.menu;

public class Mapping{

    public static void main(String...args){

        // map
        List<String> dishNames = menu.stream()
                                     .map(Dish::getName)
                                     .collect(toList());
        System.out.println("====>"+dishNames);

        // map
        List<String> words = Arrays.asList("Hello", "World");
        List<Integer> wordLengths = words.stream()
                                         .map(String::length)
                                         .collect(toList());
        System.out.println("====>"+wordLengths);

        //使用map的过程中，map是将一个元素映射成一个新的元素，所以要去重这里不行
        List<String[]> collect = words.stream()
                .map(word -> word.split(""))
                .distinct()
                .collect(toList());

        System.out.println("分割线=====>");
        // flatMap，方法用于将一个流中的每个元素映射到另一个流，并将所有流合并成一个流
        String collect1 = words.stream()
                .flatMap((String line) -> Arrays.stream(line.split("")))
                .distinct()
                .collect(Collectors.joining());
        System.out.println("分割线=====>"+ collect1);
       /* // flatMap
        List<Integer> numbers1 = Arrays.asList(1,2,3,4,5);
        List<Integer> numbers2 = Arrays.asList(6,7,8);
        List<int[]> pairs =
                        numbers1.stream()
                                .flatMap((Integer i) -> numbers2.stream()
                                                       .map((Integer j) -> new int[]{i, j})
                                 )
                                .filter(pair -> (pair[0] + pair[1]) % 3 == 0)
                                .collect(toList());
        pairs.forEach(pair -> System.out.println("(" + pair[0] + ", " + pair[1] + ")"));*/
    }
}
