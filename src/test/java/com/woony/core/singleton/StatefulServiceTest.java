package com.woony.core.singleton;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean("statefulService", StatefulService.class);
        StatefulService statefulService2 = ac.getBean("statefulService", StatefulService.class);

        // ThreadA: A 사용자 10000원 주문
        statefulService1.order("userA", 10000);
        // ThreadB: B 사용자 20000원 주문
        statefulService2.order("userB", 20000);

        // ThreadA: 사용자 A 주문 금액 조회
        int price = statefulService1.getPrice();
        // ThreadA: 사용자는 10000원이 조회될 것을 기대했으나 기대와 달리 20000원이 출력?!
        System.out.println("price = " + price);

        assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }

    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}
