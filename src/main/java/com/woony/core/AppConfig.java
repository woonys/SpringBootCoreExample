package com.woony.core;

import com.woony.core.discount.DiscountPolicy;
import com.woony.core.discount.FixDiscountPolicy;
import com.woony.core.discount.RateDiscountPolicy;
import com.woony.core.member.MemberRepository;
import com.woony.core.member.MemberService;
import com.woony.core.member.MemberServiceImpl;
import com.woony.core.member.MemoryMemberRepository;
import com.woony.core.order.OrderService;
import com.woony.core.order.OrderServiceImpl;

// 객체의 생성과 연결: AppConfig가 담당한다!
public class AppConfig {

    // AppConfig 객체는 memoryMemberRepository 객체를 생성하고 memberServiceImpl이 생성될 때 이 참조값을 생성자로 전달한다.
    // 현재 AppConfig 문제점: 현재 AppConfig를 보면 중복이 있고 역할에 따른 구현이 잘 보이지 않는다. 역할을 명확하게 드러낼 필요.
    public MemberService memberService() { // 생성자 주입 방식으로!
        return new MemberServiceImpl(memberRepository());
    } // 현재는 Mem

    // 이렇게 하면 위에 memberService() 전체를 볼 게 아니라 바로 아래 MemberRepository만 보면 된다!
    private MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    public DiscountPolicy discountPolicy() {
        return new FixDiscountPolicy();
    }
}


