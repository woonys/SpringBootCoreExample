package com.woony.core.member;

public class MemberServiceImpl implements MemberService {

    //private final MemberRepository memberRepository = new MemoryMemberRepository(); // 구현체(MemoryMemberRepository)에 의존하고 있음! 추상화에도, 구체화에도 의존 -> DIP 위반!

    // 위와 달리 더 이상 특정 구현체에 의존하지 않음. 이거 구현은 어디서? AppConfig에서!
    private MemberRepository memberRepository;


    /** 클라이언트인 memberServiceImpl 입장에서 보면 의존관계를 마치 외부에서 주입해주는 것 같다고 해서
     * DI(Dependency Injection, 의존관계 주입 또는 의존성 주입)라고 한다.
     * */
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository; // => 이젠 어떤 구현체를 쓰는지 알 길이 없다. AppConfig에서 생성자 주입 방식으로 넣어준다.
    }
    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
