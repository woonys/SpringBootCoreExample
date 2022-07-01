package com.woony.core.order;

public class Order {
    private Long memberId;
    private String itemName;
    private  int itemPrice;
    private int discountPrice;

    public Order(Long memberId, String itemName, int itemPrice, int discountPrice) {
        this.memberId = memberId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.discountPrice = discountPrice;
    }

    public int calculatorPrice() {
        return itemPrice - discountPrice;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }

    public void setDiscountPrice(int discountPrice) {
        this.discountPrice = discountPrice;
    }

    public Long getMemberId() {
        return memberId;
    }

    public String getItemName() {
        return itemName;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public int getDiscountPrice() {
        return discountPrice;
    }

    @Override
    public String toString() { // 객체 출력하면 toString()을 자동 호출해서 string으로 보낸다!
        return "Order{" +
               "memberId=" + memberId +
               ", itemName='" + itemName + '\'' +
               ", itemPrice=" + itemPrice +
               ", discountPrice=" + discountPrice +
               '}';
    }
}
