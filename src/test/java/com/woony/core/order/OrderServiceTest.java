package com.woony.core.order;

import com.woony.core.AppConfig;
import com.woony.core.member.Grade;
import com.woony.core.member.Member;
import com.woony.core.member.MemberService;
import com.woony.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

//    MemberService memberService = new MemberServiceImpl();
//    OrderService orderService = new OrderServiceImpl(memberRepository, discountPolicy);

    MemberService memberService;
    OrderService orderService;

    // @BeforeEach는 각 테스트를 실행하기 전에 호출된다.
    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }
   @Test
    void createOrder() {
       Long memberId = 1L;
       Member member = new Member(memberId, "memberA", Grade.VIP);
       memberService.join(member);

       Order order = orderService.createOrder(memberId, "itemA", 10000);
       Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
   }
}
