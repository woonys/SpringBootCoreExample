package com.woony.core.order;

public interface OrderService {
    Order createOrder(Long memberId, String itemName, int itemPrice); // 주문 생성 -> 아이템 이름, 아이템 가격
}
