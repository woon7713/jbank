package org.example.controller;

import org.example.entity.Member;
import org.example.service.MemberService;
import org.example.ui.ConsoleMenu;

public class MemberController {
    private final MemberService memberService;
    private final ConsoleMenu consoleMenu;
    private Member currentUser = null;    // 로그인 상태 추적

    public MemberController(MemberService memberService, ConsoleMenu consoleMenu) {
        this.memberService = memberService;
        this.consoleMenu = consoleMenu;
    }

    public void start() {
        boolean running = true;
        while (running) {
            if (currentUser == null) {
                // 비회원 메뉴
                switch (consoleMenu.showMainMenu()) {
                    case 0:
                        consoleMenu.printExit();
                        running = false;
                        break;
                    case 1:
                        handleRegister();
                        break;
                    case 2:
                        handleLogin();
                        break;
                    default:
                        consoleMenu.printInvalidSelection();
                }
            } else {
                // 사용자 메뉴
                switch (consoleMenu.showUserMenu()) {
                    case 0: // 로그아웃
                        consoleMenu.printLogout();
                        currentUser = null;
                        break;

                    case 1: // 로그인 정보 확인
                        consoleMenu.printMemberInfo(currentUser);
                        break;

                    case 2: // 소지금 확인
                        consoleMenu.printBalance(currentUser);
                        break;

                    case 3: // 입금
                        double inAmountt = consoleMenu.promptAmount("입금할 금액: ");
                        try {
                            currentUser.deposit(inAmountt);
                            memberService.update(currentUser);
                            consoleMenu.printBalance(currentUser);
                        } catch (IllegalArgumentException e) {
                            System.out.println("입금 오류: " + e.getMessage());
                        }
                        break;

                    case 4: // 출금
                        double outAmount = consoleMenu.promptAmount("출금할 금액: ");
                        try {
                            currentUser.withdraw(outAmount);
                            memberService.update(currentUser);
                            consoleMenu.printBalance(currentUser);
                        } catch (IllegalArgumentException e) {
                            System.out.println("출금 오류: " + e.getMessage());
                        }
                        break;

                    case 5: // 송금
                        //송금할 대상
                        String transferTarget = consoleMenu.promptTransferTarget("송금할 대상 id: ");

                        double amount = consoleMenu.promptAmount("송금할 금액: ");
                        try{
                            boolean transferOk = memberService.transfer(
                                    currentUser.getLoginId(),
                                    transferTarget,
                                    amount
                            );
                            if (transferOk) {
                                consoleMenu.printBalance(currentUser);
                                System.out.println("송금이 완료되었습니다.");
                            } else {
                                System.out.println("송금 실패: 대상 계정이 없거나 잔액이 부족합니다.");
                            }

                            consoleMenu.printBalance(currentUser);

                        }catch (IllegalArgumentException e) {
                            System.out.println("송금 오류: " + e.getMessage());
                        }

                        break;

                    default:
                        consoleMenu.printInvalidSelection();
                }
            }
        }
    }


    private void handleRegister() {
        String[] reg = consoleMenu.promptRegisterInfo();
        boolean ok = memberService.register(
                reg[0], reg[1], reg[2],
                Integer.parseInt(reg[3]), reg[4]
        );
        consoleMenu.printRegisterResult(ok);
    }

    private void handleLogin() {
        String[] creds = consoleMenu.promptLoginInfo();
        Member member = memberService.login(creds[0], creds[1]);
        consoleMenu.printLoginResult(member);
        if (member != null) {
            currentUser = member;
        }
    }

}
