package edu.wzm.jdk.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

public class ReflectTest {

    public Class<Student> generateClass()throws ClassNotFoundException{
        Class stuClazz = Student.class;
        System.out.println(stuClazz.getName());

        Student student = new Student();
        Class stuGetClazz = student.getClass();
        System.out.println(stuGetClazz == stuClazz);

        Class stuForName = Class.forName("edu.wzm.jdk.reflect.Student");
        System.out.println(stuForName == stuClazz);
        System.out.println(stuForName == stuGetClazz);
        return stuForName;
    }

    public void constructor(Class clazz)throws Exception{
        System.out.println("---------- Constructor ---------");
        /** 获取所有构造方法 */
        Constructor[] declaredConstructors = clazz.getDeclaredConstructors();
        Arrays.stream(declaredConstructors)
                .forEach(System.out::println);

        /** 获取所有public构造方法 */
        Constructor[] constructors = clazz.getConstructors();
        Arrays.stream(constructors)
                .forEach(System.out::println);

        /** 用无参public构造函数实例对象 */
        Student studentNoneArg = (Student)clazz.newInstance();
        System.out.println(studentNoneArg);

        /** 用有参private构造函数实例对象 */
        Constructor studentPrivateArg = clazz.getDeclaredConstructor(String.class, int.class);
        studentPrivateArg.setAccessible(true);
        studentNoneArg = (Student)studentPrivateArg.newInstance("Jimmy", 27);
        System.out.println(studentNoneArg);
    }

    public void field(Class clazz)throws Exception{
        System.out.println("---------- Filed ---------");
        /** 获取类中所有属性 */
        Field[] declaredFields = clazz.getDeclaredFields();
        Arrays.stream(declaredFields)
                .forEach(System.out::println);

        /** 获取类中所有public属性 */
        Field[] fields = clazz.getFields();
        Arrays.stream(fields)
                .forEach(System.out::println);

        Student student = (Student)clazz.newInstance();

        /** 修改public属性的值 */
        Field name = clazz.getField("name");
        name.set(student, "Jimmy");

        /** 修改private属性的值 */
        Field age = clazz.getDeclaredField("age");
        age.setAccessible(true);
        age.setInt(student, 24);
        System.out.println(student);
    }

    public void method(Class clazz)throws Exception{
        System.out.println("---------- Method ---------");
        /** 获取类中所有方法 */
        Method[] declaredMethods = clazz.getDeclaredMethods();
        Arrays.stream(declaredMethods)
                .forEach(System.out::println);

        /** 获取类中所有public属性 */
        Method[] methods = clazz.getMethods();
        Arrays.stream(methods)
                .forEach(System.out::println);

        Student student = (Student)clazz.newInstance();
        /** 调用private有参方法 */
        Method privateMethod = clazz.getDeclaredMethod("setName", String.class);
        privateMethod.setAccessible(true);
        privateMethod.invoke(student, "Jimmy");

        /** 调用public无参返回方法 */
        Method publicNoneReturnMethod = clazz.getMethod("getScore");
        Object res1 = publicNoneReturnMethod.invoke(student);
        System.out.println(Objects.toString(res1));

        /** 调用public有参返回方法 */
        Method publicReturnMethod = clazz.getMethod("getMsg", String.class);
        Object res2 = publicReturnMethod.invoke(student,"Grade");
        System.out.println(Objects.toString(res2));
    }

    public static void main(String[] args){
        try {
            ReflectTest reflect = new ReflectTest();
            Class clazz = reflect.generateClass();

            reflect.constructor(clazz);

            reflect.field(clazz);

            reflect.method(clazz);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
