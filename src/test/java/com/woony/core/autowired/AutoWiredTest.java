package com.woony.core.autowired;

import java.util.Optional;

import javax.swing.text.html.Option;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import com.woony.core.member.Member;

public class AutoWiredTest {

    @Test
    void AutowiredOption() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);// 이러면 TestBean이 스프링 빈으로 등록

    }

    static class TestBean {
        // 자동 주입 대상을 옵션으로 처리하는 방법
        // 1. @Autowired(required=false): 자동 주입할 대상이 없으면 수정자 메소드 자체가 호출 안된다.

        @Autowired(required = false) // false -> 의존관계 없을 경우 메소드 자체가 아예 호출 안 된다!
        public void setNoBean1(Member noBean1) { // 여기서 Member는 스프링 관련 빈이 아님! 컨테이너에 등록 안된다.
            System.out.println("noBean1 = " + noBean1); // 얘 자체가 호출되지 않는다
        }

        // 2. @Nullable: 자동 주입할 대상이 없으면 null 입력된다.
        @Autowired
        public void setNoBean2(@Nullable Member noBean2) {
            System.out.println("noBean2 = " + noBean2);
        }

        // 3. Optional: 자동 주입할 대상이 없으면 Optional.empty가 입력된다
        @Autowired
        public void setNoBean3(Optional<Member> noBean3) {
            System.out.println("noBean3 = " + noBean3);
        }
    }
}
