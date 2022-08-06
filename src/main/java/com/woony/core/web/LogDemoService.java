package com.woony.core.web;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;

import com.woony.core.common.MyLogger;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LogDemoService {

    // 의존관계 주입
    private final ObjectProvider<MyLogger> myLoggerObjectProvider;
    public void logic(String id) {
        MyLogger myLogger = myLoggerObjectProvider.getObject();
        myLogger.log("service id= " + id);

    }
}
