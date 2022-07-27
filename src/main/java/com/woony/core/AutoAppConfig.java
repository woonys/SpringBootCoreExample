package com.woony.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
// @Component 애노테이션이 붙은 클래스를 찾아 자동으로 일일이 스프링 빈을 등록해준다!
@ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)) // AppConfig를 빼주려고 필터 추가!!
public class AutoAppConfig {
}
