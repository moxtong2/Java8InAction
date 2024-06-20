package lambdasinaction.chap5;

/**
 * @description:
 * @author: zhangTongTong
 * @time: 2024/6/6 14:15
 */
public class StudentDto{
    private String name;//姓名
    private String id;//学号
    private int age;//年龄
    private String className;//班级
    public  StudentDto(String name,String id,int age,String className){
        this.name = name;
        this.id = id;
        this.age = age;
        this.className = className;
    }
    public StudentDto(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
