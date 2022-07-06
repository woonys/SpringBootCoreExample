package com.woony.core;

import com.woony.core.discount.FixCountPolicy;
import com.woony.core.member.MemberService;
import com.woony.core.member.MemberServiceImpl;
import com.woony.core.member.MemoryMemberRepository;
import com.woony.core.order.OrderService;
import com.woony.core.order.OrderServiceImpl;

// 객체의 생성과 연결: AppConfig가 담당한다!
public class AppConfig {

    // AppConfig 객체는 memoryMemberRepository 객체를 생성하고 memberServiceImpl이 생성될 때 이 참조값을 생성자로 전달한다.
    public MemberService memberService() { // 생성자 주입 방식으로!
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    public OrderService orderService() { // 마찬가지로 Appconfig에서 의존성 주입
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixCountPolicy());
    }
}
