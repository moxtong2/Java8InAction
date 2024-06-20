package lambdasinaction.chap5;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: zhangTongTong
 * @time: 2024/6/6 14:16
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student{
    private String name;//姓名
    private String id;//学号
    private int age;//年龄
    private String className;//班级



    public static Student getStudent(StudentDto studentDto){
        return  Student.builder()
                .name(studentDto.getName())
                .className(studentDto.getClassName())
                .build();
    }

    public Student(StudentDto studentDto) {
        this.name = studentDto.getName();
        this.id = studentDto.getId();
        this.age = studentDto.getAge();
        this.className = studentDto.getClassName();
    }


}