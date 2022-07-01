package com.woony.core.discount;

import com.woony.core.member.Grade;
import com.woony.core.member.Member;

public class FixCountPolicy implements DiscountPolicy {
    private int discountFixAmount = 1000; // 1000원 할인

    @Override
    public int discount(Member member, int price) {
        // 멤버 등급이 VIP인지 확인
        if (member.getGrade() == Grade.VIP) {  // Q) ==으로 비교해도 되나? -> enum 타입은 괜찮다! -> 원래는 ===으로 비교한다.
            return discountFixAmount;
        } else {
            return 0;
        }
    }
}
