package lambdasinaction.chap5;

import java.util.ArrayList;
import java.util.List;
import java.util.function.IntPredicate;

/**
 * @description:
 * @author: zhangTongTong
 * @time: 2024/6/7 10:00
 */
public class Test1 {

    public static void main(String[] args) {
        IntPredicate evenNumbers = (int i) -> i % 2 == 0;
        evenNumbers.test(1000);

    }

    public static void test(String str){
        List<Integer> listInt=new ArrayList<>();
    }
}
