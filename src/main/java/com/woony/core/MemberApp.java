package com.woony.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.woony.core.member.Grade;
import com.woony.core.member.Member;
import com.woony.core.member.MemberService;
import com.woony.core.member.MemberServiceImpl;

public class MemberApp {

    public static void main(String[] args) {
//         스프링으로 전환
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class); // AppConfig에 있는 메소드 이름을 가져와야 된다.
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("newMember = " + member.getName());
        System.out.println("findMember = " + findMember.getName());
    }
}
