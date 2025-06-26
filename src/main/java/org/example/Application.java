package org.example;

import org.example.controller.MemberController;
import org.example.repository.JsonMemberRepository;
import org.example.repository.MemberRepository;
import org.example.service.MemberService;
import org.example.ui.ConsoleMenu;

public class Application {
    public static void main(String[] args) {
        MemberRepository memberRepository = new JsonMemberRepository(); // 인터페이스 타입에 구현체 주입
        MemberService memberService = new MemberService(memberRepository);
        ConsoleMenu consoleMenu = new ConsoleMenu();

        MemberController controller = new MemberController(memberService, consoleMenu);

        controller.start();
    }
}
