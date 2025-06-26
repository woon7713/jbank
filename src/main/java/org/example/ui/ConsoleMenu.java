package org.example.ui;

import org.example.entity.Member;

import java.util.Scanner;

public class ConsoleMenu {
    // ─── 메인 메뉴 상수 ───
    public static final int MAIN_EXIT    = 0;
    public static final int MAIN_JOIN    = 1;
    public static final int MAIN_LOGIN   = 2;

    // ─── 사용자 메뉴 상수 ───
    public static final int USER_LOGOUT          = 0;
    public static final int USER_VIEW_INFO       = 1;
    public static final int USER_VIEW_CASH_BAL   = 2;

    public static final int USER_VIEW_BANK_BAL   = 3;

    public static final int USER_DEPOSIT_BANK    = 4;
    public static final int USER_WITHDRAW_BANK   = 5;
    public static final int USER_TRANSFER        = 8;
    public static final int USER_MINING          = 9;

    // ─── 관리자 메뉴 상수 ───
    public static final int ADMIN_LIST_MEMBERS   = 1;
    public static final int ADMIN_ADD_BALANCE     = 2;
    public static final int ADMIN_DEDUCT_BALANCE  = 3;
    public static final int ADMIN_CHANGE_LEVEL    = 4;
    public static final int ADMIN_LOGOUT         = 5;

    private final Scanner sc = new Scanner(System.in);

    // 메인 메뉴
    public int showMainMenu() {
        System.out.println("\n\n== J Bank ==");
        System.out.printf("%d) 종료%n", MAIN_EXIT);
        System.out.printf("%d) 회원가입%n", MAIN_JOIN);
        System.out.printf("%d) 로그인%n", MAIN_LOGIN);
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
        System.out.printf("%d) 로그아웃%n",           USER_LOGOUT);
        System.out.printf("%d) 로그인 정보 조회%n",    USER_VIEW_INFO);
        System.out.printf("%d) 소지금 확인%n",        USER_VIEW_CASH_BAL);
        System.out.printf("%d) 은행 잔액 확인%n",      USER_VIEW_BANK_BAL);
        System.out.printf("%d) 은행 입금%n",          USER_DEPOSIT_BANK);
        System.out.printf("%d) 은행 출금%n",          USER_WITHDRAW_BANK);
        System.out.printf("%d) 송금%n",               USER_TRANSFER);

        System.out.print("선택> ");
        return readInt();
    }

    // 관리자 메뉴 출력
    public int showAdminMenu() {
        System.out.println("\n\n=== 관리자 메뉴 ===");
        System.out.printf("%d) 전체 회원 목록 조회%n",  ADMIN_LIST_MEMBERS);
        System.out.printf("%d) 소지금 추가%n",        ADMIN_ADD_BALANCE);
        System.out.printf("%d) 소지금 감소%n",        ADMIN_DEDUCT_BALANCE);
        System.out.printf("%d) 등급 수동 변경%n",      ADMIN_CHANGE_LEVEL);
        System.out.printf("%d) 로그아웃%n",           ADMIN_LOGOUT);
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
        System.out.println("은행 잔액: " + member.getBankBalance());
        System.out.println("소지금: " + member.getCashBalance());
        System.out.println("역할: " + member.getRole());
        System.out.println("회원 등급: " + member.getLevel());

    }

    public void printCashBalance(Member member) {
        System.out.println("소지금 : " + member.getCashBalance());
    }
    public void printBankBalance(Member member) {
        System.out.println("은행 잔액: " + member.getBankBalance());
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
