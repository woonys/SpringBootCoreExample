package com.woony.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.woony.core.member.Grade;
import com.woony.core.member.Member;
import com.woony.core.member.MemberService;
import com.woony.core.member.MemberServiceImpl;
import com.woony.core.order.Order;
import com.woony.core.order.OrderService;
import com.woony.core.order.OrderServiceImpl;

public class OrderApp {
    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();
//        OrderService orderService = appConfig.orderService();

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);

        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 20000);
        System.out.println("Order = " + order);
        System.out.println("Order.calculatePrice = " + order.calculatorPrice());
    }
}
