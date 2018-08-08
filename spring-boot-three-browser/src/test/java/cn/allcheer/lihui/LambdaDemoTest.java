package cn.allcheer.lihui;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Slf4j
public class LambdaDemoTest {
    /**
     * 测试创建函数式接口的实例
     * 1.lambda表达式
     * 2.方法引用
     * 3.构造方法引用
     * 注意：无论是哪种方式，必须要符合抽象方法的方法定义
     */
    @Test
    public void test1(){
        List<Integer> list= Arrays.asList(1,2,45,67,7,97,453,68,34,87,34);
        list.forEach(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                log.info("Consumer.accept---"+integer);
            }
        });
       /*
        * 效果跟上面的一样，上面实现了一个函数式接口：接口中只有一个方法(可以有其他default修饰的默认方法)，接口有@FunctionalInterface注解
        * 使用Lambda表达式创建一个函数式接口的实例，那这个Lambda表达式的入参和返回必须符合这个函数式接口中唯一的抽象方法的定义
        * */
        list.forEach(integer -> log.info("Lambda---"+integer) );
        /*
        *  方法引用 ：对象名::方法名 (其中一种)
        *  使用方法引用创建一个函数式接口，也要符合这个函数式接口中唯一的抽象方法的定义
        * */
        list.forEach(System.out::println);
        /*
        * 构造方法引用：类名::new
        *
        * */
        list.forEach(test1::new);
    }
    class test1{
        test1(Integer integer){
            log.info("test1.Construction---"+integer);
        }
    }

    @Test
    public void functionTest(){
        log.info("5+4="+compute(5,num-> num+4));
        log.info("10-2="+compute(10,num-> num-2));
        log.info("783÷34="+compute(783,num->num/34));
        log.info("745×892="+compute(745,num-> num*892));
    }
    public static Integer compute(Integer item, Function<Integer,Integer> function){
        return function.apply(item);
    }

    @Test
    public void predicateTest(){
        List<Integer> list=Arrays.asList(54,23,6,67,234,2767,346,7,443,78,3,2,112,79);
        List<Integer> list1=conditionFilter(list,num-> num>100);
        list1.forEach(System.out::println);
    }
    public static List<Integer> conditionFilter(List<Integer> list, Predicate<Integer> predicate){
        return list.stream().filter(predicate).collect(Collectors.toList());
    }

    @Test
    public void filterByName(){
        List<Student> studentList=Arrays.asList(new Student("lihui",23,"三"),new Student("test",45,"四"),new Student("lihui",23,"三"));
        studentList.stream().filter(distinctBy(Student::getName)).collect(Collectors.toList()).forEach(student -> log.info(student.toString()));
    }
    public static <T> Predicate<T> distinctBy(Function<? super T,?> function){
        Set<Object> set=ConcurrentHashMap.newKeySet();
        return t->set.add(function.apply(t));
    }
    @Getter
    @Setter
    @ToString
    class Student{
         String name;
         Integer age;
         String className;
         Student(){}
         Student(String name,Integer age,String className){
             this.name=name;
             this.age=age;
             this.className=className;
         }
    }

}
