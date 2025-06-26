package org.example.entity;


import org.example.util.PasswordUtil;

import java.util.UUID;

public class Member {
    private String id;
    private String loginId;
    private String passwordHash;
    private String salt;
    private String name;
    private int age;
    private String phoneNumber;
    private double bankBalance;
    private double cashBalance;

    private Role role;
    private MembershipLevel level;

    // Jackjon 역직렬화용 기본생성자
    public Member(){

    }

    // 신규 가입시 생성자
    public Member(String loginId, String rawPw, String name, int age, String phoneNumber) {
        this.id =  UUID.randomUUID().toString();
        this.loginId = loginId;

        this.salt = PasswordUtil.generateSalt();

        this.passwordHash = PasswordUtil.applySha256(rawPw + this.salt);

        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.cashBalance = 3000; //소지금
        this.bankBalance = 0; //은행 잔액
        this.role = Role.USER; // 기본값 USER
        this.level = MembershipLevel.BASIC; // 기본값 BASIC
    }

    public double getCashBalance() {
        return cashBalance;
    }

    public void setCashBalance(double cashBalance) {
        this.cashBalance = cashBalance;
    }

    public void setId(String id){
        this.id = id;}

    public String getId() {
        return id;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public double getBankBalance() {
        return bankBalance;
    }

    public void setBankBalance(double bankBalance) {
        this.bankBalance = bankBalance;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public MembershipLevel getLevel() {
        return level;
    }

    public void setLevel(MembershipLevel level) {
        this.level = level;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("입금액은 0보다 커야합니다.");
        }
        this.cashBalance -= amount;
        this.bankBalance += amount;

    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("출금액은 0원 초과여야 합니다.");
        }
        if (amount > this.bankBalance) {
            throw new IllegalArgumentException("잔액이 부족합니다.");
        }
        this.bankBalance -= amount;
        this.cashBalance += amount;

    }


}
