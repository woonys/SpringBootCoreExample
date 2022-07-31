package com.woony.core.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.woony.core.discount.DiscountPolicy;
import com.woony.core.member.Member;
import com.woony.core.member.MemberRepository;
@Component
public class OrderServiceImpl implements OrderService{

     /**    아래 둘 다 특정 구현체(MemoryMemeberRepository, FixCountPolicy)에 의존하는 애들.

     private final MemberRepository memberRepository = new MemoryMemberRepository();
     private final DiscountPolicy discountPolicy = new FixCountPolicy();
     */
    // 아래와 같이 해놓으면 더이상 특정 구현체에 의존하지 않는다. 이 경우, 어떤 구현체를 쓸 것인지는 AppConfig와 아래 생성자를 통해 주입한다.
    private MemberRepository memberRepository;
    private DiscountPolicy discountPolicy;

    /** AppConfig로 설계를 변경했기 때문에 'OrderServiceImpl'은 'FixDiscountPolicy'에 의존하지 않는다.
     * 단지 'DiscountPolicy' 인터페이스에만 의존함.
     * 'OrderServiceImpl' 입장에서 생성자를 통해 어떤 구현 객체가 들어올지(주입될지)는 알 수 없다.
     * 'OrderServiceImpl'의 생성자를 통해서 어떤 구현 객체를 주입할지는 오직 외부('AppConfig')에서 결정한다(의존관계 주입!).
     * 따라서 'OrderServiceImpl'은 이제부터 실행에만 집중하면 된다.
     *
     * 결과적으로 'OrderServiceImpl'에는 'MemoryMemberRepository', 'FixDiscountPolicy' 객체 의존관계가 AppConfig를 통해 외부로부터 주입된다.
     * */

    /**
     * 생성자를 이용한 의존성 주입 과정
     *
     * - 스프링에서 컴포넌트 스캔해서 OrderServiceImpl을 빈에 등록할 때
     * - 등록하려면 생성자를 호출해야지 → 생성자 호출할 때 @Autowired 있는 거 보고
     * - 스프링 컨테이너에서 MemberRepository, DiscountPolicy 스프링 빈을 꺼낸 다음 여기에 주입해준다!
     *
     * - 이 방식은 불변, 필수 의존관계에 사용한다.
     * - 위의 OrderServiceImpl에서 생성자만 memberRepository, discountPolicy를 건들 수 있지 다른 애들은 못 건드린다! → 의도: 중간에 memberRepo, discountPolicy를 바꾸고 싶지 않기 때문!
     * - 불변 객체에 열어두는 코드가 생기면 → 어떻게든 누군가가 수정할 수도 있다!
     *
     */
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        System.out.println("1. OrderServiceImpl.OrderServiceImpl");
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    /* 아래와 같이 변경에 열려있으면 안됨.
    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
		this.discountPolicy = discountPolicy;
    }

    *
    * */

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        // 1. 회원 정보 조회하고
        Member member = memberRepository.findById(memberId); // 구체 클래스가 무엇인지 전혀 고민할 필요 없음.
        // 2. 해당 회원에 대한 할인 정책 어떻게 되는지 확인
        int discountPrice = discountPolicy.discount(member, itemPrice); // 이게 잘 된 설계! -> OrderServiceImpl 입장에서는 discountPolicy가 어떻게 생겼는지 알 필요 없다. 의존하지 않음! 단일 책임 원칙이 잘 설계됐다.
        // 할인한 금액에 대한 주문 반환
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
