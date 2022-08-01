package com.woony.core;

import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//Lombok 기능! Getter / Setter 자동으로 만들어준다 by annotation
@Getter
@Setter
@ToString
public class HelloLombok {
    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setName("asdf");

        String name = helloLombok.getName();
        System.out.println("name = " + name);
    }
}
