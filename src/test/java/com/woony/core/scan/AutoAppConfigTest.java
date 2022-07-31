package com.woony.core.scan;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.woony.core.AutoAppConfig;
import com.woony.core.member.MemberRepository;
import com.woony.core.member.MemberService;
import com.woony.core.member.MemberServiceImpl;
import com.woony.core.order.OrderServiceImpl;

public class AutoAppConfigTest {

    @Test
    void basicScan() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);

        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberService.class);

//        OrderServiceImpl bean = ac.getBean(OrderServiceImpl.class);
//        MemberRepository memberRepository = bean.getMemberRepository();
//        System.out.println("memberRepository = " + memberRepository);
    }
}
