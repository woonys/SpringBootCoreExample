package com.woony.core.order;

import com.woony.core.discount.DiscountPolicy;
import com.woony.core.discount.FixCountPolicy;
import com.woony.core.member.Member;
import com.woony.core.member.MemberRepository;
import com.woony.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixCountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        // 1. 회원 정보 조회하고
        Member member = memberRepository.findById(memberId);
        // 2. 해당 회원에 대한 할인 정책 어떻게 되는지 확인
        int discountPrice = discountPolicy.discount(member, itemPrice); // 이게 잘 된 설계! -> OrderServiceImpl 입장에서는 discountPolicy가 어떻게 생겼는지 알 필요 없다. 의존하지 않음! 단일 책임 원칙이 잘 설계됐다.
        // 할인한 금액에 대한 주문 반환
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
