package demo;

public class ClassTest {

    public static ClassTest test = new ClassTest();
    public static String name = "zhangsan";


    public ClassTest(){
        System.out.println(name);
    }

    public static void main(String[] args) {
        System.out.println(name);
    }


    // null zhangsan
}
