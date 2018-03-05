package com.jianyu.serialize;

import com.alibaba.fastjson.JSON;

public class JsonDemo {
    public static void main(String[] args) {
        executeWithFastJson();
    }

    private static void executeWithFastJson(){
        Person person = new Person();
        person.setName("xiaoFang");
        person.setAge(20);
        String text = JSON.toJSONString(person);

        Person p = (Person) JSON.parseObject(text,Person.class);
        System.out.println(p);

    }
}
