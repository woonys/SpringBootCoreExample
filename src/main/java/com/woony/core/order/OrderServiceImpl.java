package com.woony.core.order;

import com.woony.core.discount.DiscountPolicy;
import com.woony.core.member.Member;
import com.woony.core.member.MemberRepository;

public class OrderServiceImpl implements OrderService{

/**    아래 둘 다 특정 구현체(MemoryMemeberRepository, FixCountPolicy)에 의존하는 애들.

        private final MemberRepository memberRepository = new MemoryMemberRepository();
        private final DiscountPolicy discountPolicy = new FixCountPolicy();
 */
    // 아래와 같이 해놓으면 더이상 특정 구현체에 의존하지 않는다. 이 경우, 어떤 구현체를 쓸 것인지는 AppConfig와 아래 생성자를 통해 주입한다.
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    /** AppConfig로 설계를 변경했기 때문에 'OrderServiceImpl'은 'FixDiscountPolicy'에 의존하지 않는다.
     * 단지 'DiscountPolicy' 인터페이스에만 의존함.
     * 'OrderServiceImpl' 입장에서 생성자를 통해 어떤 구현 객체가 들어올지(주입될지)는 알 수 없다.
     * 'OrderServiceImpl'의 생성자를 통해서 어떤 구현 객체를 주입할지는 오직 외부('AppConfig')에서 결정한다(의존관계 주입!).
     * 따라서 'OrderServiceImpl'은 이제부터 실행에만 집중하면 된다.
     *
     * 결과적으로 'OrderServiceImpl'에는 'MemoryMemberRepository', 'FixDiscountPolicy' 객체 의존관계가 AppConfig를 통해 외부로부터 주입된다.
     * */
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        // 1. 회원 정보 조회하고
        Member member = memberRepository.findById(memberId); // 구체 클래스가 무엇인지 전혀 고민할 필요 없음.
        // 2. 해당 회원에 대한 할인 정책 어떻게 되는지 확인
        int discountPrice = discountPolicy.discount(member, itemPrice); // 이게 잘 된 설계! -> OrderServiceImpl 입장에서는 discountPolicy가 어떻게 생겼는지 알 필요 없다. 의존하지 않음! 단일 책임 원칙이 잘 설계됐다.
        // 할인한 금액에 대한 주문 반환
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
