package com.woony.core.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.woony.core.discount.DiscountPolicy;
import com.woony.core.member.Member;
import com.woony.core.member.MemberRepository;

import lombok.RequiredArgsConstructor;

@Component
//@RequiredArgsConstructor // -> final 붙은 필수값 가지고 생성자 뚝딱 만들어준다! 기가 막히게 코드가 줄어든다!
public class OrderServiceImpl implements OrderService{

     /**    아래 둘 다 특정 구현체(MemoryMemeberRepository, FixCountPolicy)에 의존하는 애들.

     private final MemberRepository memberRepository = new MemoryMemberRepository();
     private final DiscountPolicy discountPolicy = new FixCountPolicy();
     */
    // 아래와 같이 해놓으면 더이상 특정 구현체에 의존하지 않는다. 이 경우, 어떤 구현체를 쓸 것인지는 AppConfig와 아래 생성자를 통해 주입한다.
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, @Qualifier("mainDiscountPolicy") DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        // 1. 회원 정보 조회하고
        Member member = memberRepository.findById(memberId); // 구체 클래스가 무엇인지 전혀 고민할 필요 없음.
        // 2. 해당 회원에 대한 할인 정책 어떻게 되는지 확인
        int discountPrice = discountPolicy.discount(member, itemPrice); // 이게 잘 된 설계! -> OrderServiceImpl 입장에서는 discountPolicy가 어떻게 생겼는지 알 필요 없다. 의존하지 않음! 단일 책임 원칙이 잘 설계됐다.
        // 할인한 금액에 대한 주문 반환
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
