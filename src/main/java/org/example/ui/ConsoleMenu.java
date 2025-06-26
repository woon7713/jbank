package org.example.ui;

import org.example.entity.Member;

import java.util.Scanner;

public class ConsoleMenu {
    private final Scanner sc = new Scanner(System.in);

    // 메인 메뉴
    public int showMainMenu() {
        System.out.println("\n\n== J Bank ==");
        System.out.println("0) 종료");
        System.out.println("1) 회원가입");
        System.out.println("2) 로그인");
        System.out.print("선택> ");
        return readInt();
    }

    // 회원가입 정보 입력
    public String[] promptRegisterInfo() {
        System.out.println("\n\n== 회원가입 ==");
        System.out.print("아이디: ");
        String loginId = sc.nextLine().trim();
        System.out.print("비밀번호: ");
        String rawPw = sc.nextLine().trim();
        System.out.print("이름: ");
        String name = sc.nextLine().trim();
        System.out.print("나이: ");
        String age = sc.nextLine().trim();
        System.out.print("전화번호: ");
        String phone = sc.nextLine().trim();
        return new String[]{loginId, rawPw, name, age, phone};
    }

    // 로그인 정보 입력
    public String[] promptLoginInfo() {
        System.out.println("== 로그인 ==");
        System.out.print("아이디: ");
        String loginId = sc.nextLine().trim();
        System.out.print("비밀번호: ");
        String rawPw = sc.nextLine().trim();
        return new String[]{loginId, rawPw};
    }

    // 사용자 메뉴 출력
    public int showUserMenu() {
        System.out.println("\n\n=== 사용자 메뉴 ===");
        System.out.println("0) 로그아웃");
        System.out.println("1) 로그인 정보 조회");
        System.out.println("2) 소지금 확인");
        System.out.println("3) 입금");
        System.out.println("4) 출금");
        System.out.println("5) 송금");
        System.out.println("6) 채굴");

        System.out.print("선택> ");
        return readInt();
    }

    // 관리자 메뉴 출력
    public int showAdminMenu() {
        System.out.println("\n\n=== 관리자 메뉴 ===");
        System.out.println("1) 전체 회원 목록 조회");
        System.out.println("2) 소지금 추가");
        System.out.println("3) 소지금 감소");
        System.out.println("4) 등급 수동 변경");
        System.out.println("5) 로그아웃");
        System.out.print("선택> ");
        return readInt();
    }

    // 가입 결과 출력
    public void printRegisterResult(boolean success) {
        System.out.println(success ? "가입 성공!" : "이미 존재하는 아이디입니다.");
    }

    // 로그인 결과 출력
    public void printLoginResult(Member user) {
        if (user == null) {
            System.out.println("로그인 실패");
        } else {
            System.out.println("환영합니다, " + user.getName() + "님");
        }
    }

    public void printLogout() {


    }

    public void printMemberInfo(Member member) {
        System.out.println("ID: " + member.getLoginId());
        System.out.println("이름: " + member.getName());
        System.out.println("나이: " + member.getAge());
        System.out.println("전화번호: " + member.getPhoneNumber());
        System.out.println("잔액: " + member.getBalance());
        System.out.println("역할: " + member.getRole());
        System.out.println("회원 등급: " + member.getLevel());

    }

    public void printBalance(Member member) {
        System.out.println("잔액: " + member.getBalance());
    }

    public double promptAmount(String message) {
        while (true) {
            System.out.print(message);
            String line = sc.nextLine().trim(); // 개행 제거 입력
            try {
                double amount = Double.parseDouble(line);
                if (amount <= 0) {
                    System.out.println("금액은 0보다 커야 합니다. 다시 입력해주세요.");
                } else {
                    return amount;
                }
            } catch (NumberFormatException e) {
                System.out.println("올바른 숫자를 입력해주세요.");
            }

        }

    }

    public String promptTransferTarget(String message){
        while (true) {
            System.out.print(message);
            String line = sc.nextLine().trim();
            if (!line.isEmpty()) {
                return line;
            }
            System.out.println("아이디를 입력 해주세요.");

        }

    }


    // 잘못된 선택 메시지
    public void printInvalidSelection() {
        System.out.println("잘못된 입력.. 다시 선택해주세요.");
    }

    // 종료 메시지
    public void printExit() {
        System.out.println("프로그램 종료..");
    }

    // 정수 입력 처리
    private int readInt() {
        while (true) {
            String line = sc.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.print("숫자로 다시 입력> ");
            }
        }
    }
}
