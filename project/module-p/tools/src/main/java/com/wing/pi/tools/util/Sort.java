package com.wing.pi.tools.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Sort {
    public static void main(String[] args) {

        List<Student> list = new ArrayList<Student>();

        //创建3个学生对象，年龄分别是20、19、21，并将他们依次放入List中
        Student s1 = new Student();
        s1.setAge(20l);
        Student s2 = new Student();
        s2.setAge(19l);
        Student s3 = new Student();
        s3.setAge(21l);
        list.add(s1);
        list.add(s2);
        list.add(s3);

        System.out.println("排序前：");
        for (Student s : list){
            System.out.println(s.getAge());
        }

        Collections.sort(list, new Comparator<Student>(){

            /*
             * int compare(Student o1, Student o2) 返回一个基本类型的整型，
             * 返回负数表示：o1 小于o2，
             * 返回0 表示：o1和o2相等，
             * 返回正数表示：o1大于o2。
             */
            public int compare(Student o1, Student o2) {

                //按照学生的年龄进行升序排列
                if(o1.getAge() > o2.getAge()){
                    return -1;
                }
                if(o1.getAge() == o2.getAge()){
                    return 0;
                }
                return 1;
            }
        });
        System.out.println("排序后：");
        for (Student s : list){
            System.out.println(s.getAge());
        }
    }

}
