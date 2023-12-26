package com.wh.demo;

import lombok.Data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Author: wanghao
 * Date: 2023/9/15 13:36
 * Description:
 */
public class Demo002 {

    public static void main(String[] args) {

        List<Person> personList = new ArrayList<>();

        List<Long> ids = new ArrayList<>();
        ids.add(111L);
        ids.add(222L);
        System.out.println(ids);

        personList.add(new Person("wh1", 21));
        personList.add(new Person("wh2", 22));
        personList.add(new Person("wh3", 23));
        personList.add(new Person("wh4", 24));
        personList.add(new Person("wh5", 25));

        Iterator<Person> iterator = personList.iterator();
        while (iterator.hasNext()) {
            Person next = iterator.next();
            next.setName(next.getName() + "_" + next.getAge());
        }

        for (Person person : personList) {
            System.out.println(person.toString());
        }

    }
}

@Data
class Person {
    private String name;

    private Integer age;

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String toString() {
        return this.name + "," + this.age;
    }

}
