package com.woony.core.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.woony.core.common.MyLogger;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

        private final LogDemoService logDemoService;
        private final MyLogger myLogger; // -> 이러면 MyLogger를 주입받는 게 아니라 MyLogger를 찾을 수 있는 Dependency Lookup할 수 있는 애를 주입받음!

        @RequestMapping("log-demo")
        @ResponseBody
        public String logDemo(HttpServletRequest request) { // 여기서 고객 요청 정보를 받을 수 있다!
                String requestURL = request.getRequestURL().toString();

                System.out.println("myLogger = " + myLogger.getClass());
                myLogger.setRequestURL(requestURL);

                myLogger.log("controller test");
                logDemoService.logic("testId");
                return "OK";
        }
}
