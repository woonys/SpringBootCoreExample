package com.woony.core.singleton;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.woony.core.AppConfig;
import com.woony.core.member.Member;
import com.woony.core.member.MemberService;

public class SingletonService {

    //JVM이 실행될 때 SingletonService의 static 영역에 new SingletonService()를 내부적으로 실행해서 자기 자신을 객체로 생성한 다음 instance에 참조로 넣는다!
    private static final SingletonService instance = new SingletonService(); // 자기 자신을 static으로 하나만 갖는다! -> static 영역에 하나만 만들어져서 올라감!

    public static SingletonService getInstance() { // 조회할 때는 얘로 쓴다.
        return instance; // 항상 같은 인스턴스(요놈)만 반환한다!
    }

    private SingletonService() { // 이렇게 생성자를 private으로 막아두면 다른 곳에서 절대 호출 못함!
    }

    public void  logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    public void singletonServiceTest() {
        //private으로 생성자 막기 -> 컴파일 오류 발생
        //new SingletonService();

        //1. 조회: 호출할 때마다 같은 객체 반환
        SingletonService singletonService1 = SingletonService.getInstance();
        //2. 조회: 호출할 때마다 같은 객체 반환
        SingletonService singletonService2 = SingletonService.getInstance();

        // 참조값 같은지 확인
        System.out.println("singletonService1 = " + singletonService1);
        System.out.println("singletonService2 = " + singletonService2);

        assertThat(singletonService1).isSameAs(singletonService2);

        singletonService1.logic();
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    public void springContainer() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class); // 스프링 적용!

        // 1. 조회: 호출할 때마다 같은 객체 반환
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        // 2. 조회: 호출할 때마다 같은 객체 반환
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        //memberService1 == memberService2
        assertThat(memberService1).isSameAs(memberService2);

    }

}
