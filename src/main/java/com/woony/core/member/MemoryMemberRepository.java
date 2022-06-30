package com.woony.core.member;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository {
    private static Map<Long, Member> store = new HashMap<>(); // 실무에서는 ConcurrentHashMap을 쓴다 -> 동시성 문제로!
    @Override
    public void save(Member member) {

    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
