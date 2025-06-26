package org.example.service;


import org.example.entity.Member;
import org.example.repository.MemberRepository;
import org.example.util.PasswordUtil;

public class MemberService {

    private final MemberRepository memberRepository; // 클래스 필드, 객체 담을 그릇

    public MemberService(MemberRepository memberRepository){ // main 메서드에서 생성자를 통해 구현체 주입

        this.memberRepository = memberRepository;
    }

    public boolean register(String loginId, String rawPw, String name, int age, String phoneNumber){
        if(memberRepository.findByLoginId(loginId) != null){
            return false;
        }
        Member member = new Member(loginId, rawPw, name, age, phoneNumber);
        memberRepository.save(member);

        return true;
    }

    public Member login(String loginId, String rawPw){
        Member member = memberRepository.findByLoginId(loginId);
        if (member == null) {
            System.out.println("존재하지 않는 아이디입니다");
            return null;


        }
        String inputPasswordHash = PasswordUtil.applySha256(rawPw + member.getSalt());

        if (!inputPasswordHash.equals(member.getPasswordHash())) {
            return null;
        }


        return member;

    }

    public void update(Member member) {
        memberRepository.update(member);
    }

    public boolean transfer(String fromLoginId, String toLoginId, double amount) {
        Member from = memberRepository.findByLoginId(fromLoginId);
        Member to = memberRepository.findByLoginId(toLoginId);

        if (from == null || to == null) {
            return false;
        }

        if (amount <= 0 || from.getBankBalance() < amount) {
            return false;
        }

        //입출금
        from.withdraw(amount);
        to.deposit(amount);

        //저장소 업데이트
        memberRepository.update(from);
        memberRepository.update(to);

        return true;
    }






}
