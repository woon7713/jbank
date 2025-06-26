package org.example.repository;



import org.example.entity.Member;

import java.util.List;

public interface MemberRepository {
    void save(Member member);
    void update(Member member);

    Member findByLoginId(String loginId);

    List<Member> findAll();
}
