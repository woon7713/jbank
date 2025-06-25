package org.example.entity;


import org.example.util.PasswordUtil;

import java.util.UUID;

public class Member {
    private final String id = UUID.randomUUID().toString();
    private String loginId;
    private String passwordHash;
    private String salt;
    private String name;
    private int age;
    private String phoneNumber;
    private double balance;
    private Role role;
    private MembershipLevel level;

    public Member(String loginId, String rawPw, String name, int age, String phoneNumber) {
        this.loginId = loginId;

        this.salt = PasswordUtil.generateSalt();

        this.passwordHash = PasswordUtil.applySha256(rawPw + this.salt);

        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.balance = 3000;
        this.role = Role.USER; // 기본값 USER
        this.level = MembershipLevel.BASIC; // 기본값 BASIC
    }

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

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
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
}
