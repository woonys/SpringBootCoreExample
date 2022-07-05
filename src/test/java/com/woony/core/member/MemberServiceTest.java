package com.woony.core.member;

import static org.assertj.core.api.Assertions.assertThat;

import com.woony.core.AppConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {
    // MemberService memberService = new MemberServiceImpl();  -> 생성자 넣어줘야지!
    /* MemberApp에서처럼
    AppConfig appConfig = new AppConfig();
    MemberService memberService = appConfig.memberService();
    이렇게 하지 않는다 -> AppConfig에서 바로 꺼내기 애매함.
    아래와 같이 @BeforeEach 어노테이션 사용 -> @Test 실행하기 전에 여기서부터 실행된다!
    */
    MemberService memberService;
    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    @Test
    void join() {
        // given
        Member member = new Member(1L, "memberA", Grade.VIP);

        // when
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        // then
        assertThat(member).isEqualTo(findMember);
    }
}
