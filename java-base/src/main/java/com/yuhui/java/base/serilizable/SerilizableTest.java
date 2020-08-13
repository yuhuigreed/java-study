package com.yuhui.java.base.serilizable;

import org.junit.jupiter.api.Test;

import java.io.*;

/**
 * @author yuhui
 */
public class SerilizableTest {


    @Test
    public void serilizeTest() throws IOException {
        Car car = new Car();
        car.setName("奥迪");
        car.setCar("audi");
        car.setColor("red");
        //序列化
        File file = new File("D:\\WorkSpace\\gitRepository\\gitHub\\java-study\\java-base\\src\\main\\resources\\file.txt");
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
        oos.writeObject(car);
        System.out.println("car对象已被序列化完成");
        oos.close();
    }

    @Test
    public void deSerilizeTest() throws IOException, ClassNotFoundException {
        File file = new File("D:\\WorkSpace\\gitRepository\\gitHub\\java-study\\java-base\\src\\main\\resources\\file.txt");
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        Car car = (Car)ois.readObject();
        System.out.println("反序列化car完成");
        ois.close();
    }

}
