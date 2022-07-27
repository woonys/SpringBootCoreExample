package com.woony.core;

import com.woony.core.member.Grade;
import com.woony.core.member.Member;
import com.woony.core.member.MemberService;
import com.woony.core.member.MemberServiceImpl;

public class MemberApp {

    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        /**
         * 아래에서 appConfig.memberService()를 호출하면 memberServiceImpl 객체를 생성하면서 그 안에 MemoryMemberRepository 객체가 생성된다.
         * 그러면
         * */
        MemberService memberService = appConfig.memberService();
//        MemberService memberService = new MemberServiceImpl();
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("newMember = " + member.getName());
        System.out.println("findMember = " + findMember.getName());
    }
}
