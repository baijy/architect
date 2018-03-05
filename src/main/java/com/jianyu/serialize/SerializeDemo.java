package com.jianyu.serialize;

import java.io.*;

public class SerializeDemo {
    public static void main(String[] args) {
        //SerializePerson();
        deSerialLizePerson();
    }

    private static void SerializePerson() {
        try {
            // 生成的文件在工程根目录下
            ObjectOutputStream oo = new ObjectOutputStream(new FileOutputStream(new File("person")));
            Person person = new Person();
            person.setAge(18);
            person.setName("Baozi");
            oo.writeObject(person);
            oo.flush();

            oo.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void deSerialLizePerson(){
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("person")));
            Person person = (Person) ois.readObject();
            System.out.println(person);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
