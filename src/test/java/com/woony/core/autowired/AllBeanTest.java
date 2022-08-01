package com.woony.core.autowired;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.woony.core.AutoAppConfig;
import com.woony.core.discount.DiscountPolicy;
import com.woony.core.member.Grade;
import com.woony.core.member.Member;

public class AllBeanTest {

    @Test
    void findAllBean() {
        // AutoAppConfig를 넣으면 DiscountPolicy에 해당하는 Fix, Rate 둘다 스프링 빈에 등록된다!
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);

        // 여기서부터 TDD! 테스트부터 먼저 짜본다! -> 할인 서비스 로직을 짜보자.
        DiscountService discountService = ac.getBean(DiscountService.class);
        Member member = new Member(1L, "userA", Grade.VIP);
        int discountPrice = discountService.discount(member, 10000, "fixDiscountPolicy"); // 내 멤버 등급과 금액을 넣었을 때 얼마나 할인되는지를 보는 메소드 -> 테스트부터 짠다!

        assertThat(discountService).isInstanceOf(DiscountService.class);
        assertThat(discountPrice).isEqualTo(1000); // 고정 discount 정책이면 1000원이 할인될 것!

        int rateDiscountPrice = discountService.discount(member, 20000, "rateDiscountPolicy");
        assertThat(rateDiscountPrice).isEqualTo(2000);
    }

    static class DiscountService {
        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policies;

        @Autowired
        DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policies) { // Map, List로 받을 경우 -> 다양한 빈에 대해서 가능!
            this.policyMap = policyMap;
            this.policies = policies;
            System.out.println("policyMap = " + policyMap);
            System.out.println("policies = " + policies);
        }

        public int discount(Member member, int price, String discountCode) {
            DiscountPolicy discountPolicy = policyMap.get(discountCode);
            return discountPolicy.discount(member, price);
        }
    }

}
