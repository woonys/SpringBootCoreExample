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
    private final MyLogger myLogger;
    public void logic(String id) {
        myLogger.log("service id= " + id);

    }
}
