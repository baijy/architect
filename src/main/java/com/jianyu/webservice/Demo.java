package com.jianyu.webservice;

public class Demo {
    public static void main(String[] args) {
        SayHelloImplService service = new SayHelloImplService();
        SayHelloImpl sayHello = service.getSayHelloImplPort();
        System.out.println(sayHello.sayHello("大王"));
    }
}
