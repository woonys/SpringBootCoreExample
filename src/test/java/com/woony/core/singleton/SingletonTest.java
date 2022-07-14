package com.woony.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.woony.core.AppConfig;
import com.woony.core.member.MemberService;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();
        // 1. 조회: 호출할 때마다 객체 생성하니?
        MemberService memberService1 = appConfig.memberService();
        // 2. 조회: 호출할 때마다 객체 생성하니?
        MemberService memberService2 = appConfig.memberService();

        // 참조값이 다른 것을 확인 -> 매 번 객체 생성하면 효율적이지 X!
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        // memberService1 != memberService2 -> 호출할 때마다 객체 계속 새로 생성한다.
        Assertions.assertThat(memberService1).isNotSameAs(memberService2);

    }
}
