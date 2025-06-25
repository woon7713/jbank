package org.example.ui;

import com.sun.source.tree.WhileLoopTree;

import java.util.Scanner;

public class ConsoleMenu {
    private final Scanner sc = new Scanner(System.in);

    // 초기화면 메뉴 출력 및 선택
    public int showMainMenu(){
        System.out.println("== J Bank ==");
        System.out.println("1) 회원가입");
        System.out.println("2) 로그인");
        System.out.println("0) 종료");
        System.out.print("선택> ");

        return readInt();
    }

    // 로그인 후 사용자 메뉴
    public int showUserMenu() {
        System.out.println("=== 사용자 메뉴 ===");
        System.out.println("1) 전체 정보 조회");
        System.out.println("2) 소지금 확인");
        System.out.println("3) 입금");
        System.out.println("4) 출금");
        System.out.println("5) 송금");
        System.out.println("6) 채굴");
        System.out.println("7) 로그아웃");
        System.out.print("선택> ");
        return readInt();
    }

    // 로그인 후 관리자 메뉴
    public int showAdminMenu() {
        System.out.println("=== 관리자 메뉴 ===");
        System.out.println("1) 전체 회원 목록 조회");
        System.out.println("2) 소지금 추가");
        System.out.println("3) 소지금 감소");
        System.out.println("4) 등급 수동 변경");
        System.out.println("5) 로그아웃");
        System.out.print("선택> ");
        return readInt();
    }

    public int readInt(){
        while(true){
            String line = sc.nextLine().trim();

            // 잘못된 입력 방지
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.print("숫자로 다시 입력> ");

            }
        }

    }



}
