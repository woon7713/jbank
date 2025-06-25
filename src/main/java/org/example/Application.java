package org.example;


import org.example.repository.JsonMemberRepository;
import org.example.repository.MemberRepository;
import org.example.service.MemberService;
import org.example.ui.ConsoleMenu;

public class Application {
    public static void main(String[] args) {
        MemberRepository repository = new JsonMemberRepository(); // 인터페이스 타입에 구현체 주입
        MemberService service = new MemberService();

        ConsoleMenu consoleMenu = new ConsoleMenu();
        boolean running = true;

        final int MENU_EXIT = 0;
        final int MENU_REGISTER = 1;
        final int MENU_LOGIN = 2;

        while(running){
            int menuSelect = consoleMenu.showMainMenu();

            switch (menuSelect) {
                case MENU_EXIT:
                    running = false;
                    System.out.println("프로그램 종료..");
                    break;
                case MENU_REGISTER:
                    //회원가입 로직 호출

                    System.out.println("==회원가입==");
                    break;
                case MENU_LOGIN:
                    // 로그인 로직 호출
                    System.out.println("로그인");
                    break;

                default:
                    System.out.println("잘못된 입력.. 다시 입력바랍니다..");

            }

        }



    }
}