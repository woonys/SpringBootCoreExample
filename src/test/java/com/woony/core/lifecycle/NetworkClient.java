package com.woony.core.lifecycle;


public class NetworkClient {

    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url); // 처음에는 url 없음
    }

    public void setUrl(String url) {
        this.url = url;
    }

    // 서비스 시작시 호출
    public void connect() {
        System.out.println("connect: " + url); // 여전히 여기 값이 없다.
    }

    public void call(String message) {
        System.out.println("call: " + url + "message = " + message);
    }

    // 서비스 종료시 호출
    public void disconnect() {
        System.out.println("Close: " + url);
    }


    public void init() { // 이번에는 빈에 초기화, 소멸 지정하기
        System.out.println("init");
        connect();
        call("초기화 연결 메시지");
    }

    public void close() {
        System.out.println("NetworkClient.close");
        disconnect();
    }
}
