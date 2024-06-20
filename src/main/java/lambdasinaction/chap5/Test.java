package lambdasinaction.chap5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: zhangTongTong
 * @time: 2024/6/6 10:52
 */
public class Test {
    private String aage;
    public static void main(String[] args) {
        List<StudentDto> studentDtos = Arrays.asList(new StudentDto("张三", "001", 20, "一班"),
                new StudentDto("李四", "002", 21, "一班"),
                new StudentDto("王五", "003", 20, "二班"));
        //自定义函数下的对象转换
      List<Student> map1 = map(studentDtos, (StudentDto studentDto) ->
                new Student(studentDto.getName(),
                        studentDto.getId(),
                        studentDto.getAge(),
                        studentDto.getClassName()));

        System.out.println("map1 : "+map1.size());

       //Java8下的对象转换
        List<Student> stream1 = studentDtos.stream()
                .map(studentDto -> new Student(
                        studentDto.getName(),
                        studentDto.getId(),
                        studentDto.getAge(),
                        studentDto.getClassName()
                )).collect(Collectors.toList());

        System.out.println("stream1 : "+stream1.size());

        //还可以优化2
        List<Student> map2 = map(studentDtos, Student::new);
        System.out.println("map2 : "+map2.size());

        //还可以优化3 (最优)
        List<Student> stream2 = studentDtos.stream().map(Student::new).collect(Collectors.toList());
        System.out.println("stream2 : "+stream2.size());


        //只取部分字段 (其他字段为null，不满足期望)
        //List<Student> stream3 = studentDtos.stream().map(Student::getStudent).collect(Collectors.toList());
        //System.out.println("stream3 : "+stream2.size());

        //静态方法 可以直接调用，
        //非静态方法可以直接调用静态方法
        //静态方法不能直接调用 非静态方法
        //非静态方法属于实例 静态方法不属于实例，存储于Jvm 类加载器中
        //静态方法不能调用非静态常量



    }



    public static  <T, R> List<R> map(List<T> list, Function<T, R> f) {
        List<R> result = new ArrayList<>();
        for(T s: list){
            result.add(f.apply(s));
        }
        return result;
    }

}
