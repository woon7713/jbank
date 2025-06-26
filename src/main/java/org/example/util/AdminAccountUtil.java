package org.example.util;

import org.example.entity.Member;
import org.example.entity.Role;
import org.example.repository.MemberRepository;

public class AdminAccountUtil {
    private static final String ADMIN_LOGIN_ID = "admin";
    private static final String ADMIN_PW = "password";

    public static void generateAdmin(MemberRepository memberRepository) {
        if (memberRepository.findByLoginId(ADMIN_LOGIN_ID) != null) return; // 이미 어드민 계정이 존재하면 실행하지 않음

        String rawPw = ADMIN_PW;
        if (rawPw == null || rawPw.isBlank()) {
            System.out.println("비밀번호 문제");
            return;
        }

        // 기본 정보
        Member admin = new Member(
                ADMIN_LOGIN_ID,
                rawPw,
                "관리자",
                0,
                "000-0000-0000"
        );

        admin.setRole(Role.ADMIN);

        memberRepository.save(admin);
        System.out.println("admin 계정 생성됨 - ID: admin");
    }
}