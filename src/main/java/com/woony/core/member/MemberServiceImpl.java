package com.woony.core.member;

public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository = new MemoryMemberRepository(); // 구현체(MemoryMemberRepository)에 의존하고 있음! 추상화에도, 구체화에도 의존 -> DIP 위반!
    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
