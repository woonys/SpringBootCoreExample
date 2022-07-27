package com.woony.core.singleton;

public class StatefulService {
    private int price; // 상태 유지하는 필드

    public void order(String name, int price) {
        System.out.println("name = " + name + "price = " + price);
        this.price = price; // 여기가 문제! -> order 메소드는 public이니까 모두가 접근 가능 -> private 값을 바꿀 수 있다!
    }

    public int getPrice() {
        return price;
    }

}
