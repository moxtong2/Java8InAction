package lambdasinaction.chap6;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;
import static lambdasinaction.chap6.Dish.menu;

public class Partitioning {

    public static void main(String ... args) {
       /* System.out.println("Dishes partitioned by vegetarian: " + partitionByVegeterian());
        System.out.println("Vegetarian Dishes by type: " + vegetarianDishesByType());*/
        System.out.println("Most caloric dishes by vegetarian: " + mostCaloricPartitionedByVegetarian());
        System.out.println("You have a partition test 0"+vegetarianDishesByTypeCopy());
        System.out.println("You have a partition test 1 "+vegetarianDishesByTypePartition());


    }

    private static Map<Boolean, List<Dish>> partitionByVegeterian() {
        return menu.stream().collect(partitioningBy(Dish::isVegetarian));
    }

    private static Map<Boolean, Map<Dish.Type, List<Dish>>> vegetarianDishesByType() {
        return menu.stream().collect(partitioningBy(Dish::isVegetarian, groupingBy(Dish::getType)));
    }

    //分区的例子
    private static List<Dish> vegetarianDishesByTypeCopy() {
        return menu.stream().filter(Dish::isVegetarian).collect(Collectors.toList());
    }

    //分区函数
    private static Map<Boolean, Map<Dish.Type, List<Dish>>> vegetarianDishesByTypePartition() {
         return menu.stream().collect(partitioningBy(Dish::isVegetarian, groupingBy(Dish::getType)));
    }

    private static Object mostCaloricPartitionedByVegetarian() {
        return menu.stream().collect(
                partitioningBy(Dish::isVegetarian,
                        collectingAndThen(
                                maxBy(comparingInt(Dish::getCalories)),
                                Optional::get)));
    }
}

