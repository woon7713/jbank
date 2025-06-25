package org.example.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class PasswordUtil {

    public static String generateSalt() {
        try {
            SecureRandom random = SecureRandom.getInstanceStrong();
            byte[] salt = new byte[16]; // 16바이트 길이의 salt 생성
            random.nextBytes(salt);
            return Base64.getEncoder().encodeToString(salt); // 바이트 배열을 문자열로 인코딩하여 반환
        } catch (Exception e) {
            throw new RuntimeException("Salt 생성에 실패했습니다.");
        }
    }

    public static String applySha256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(input.getBytes("UTF-8"));

            StringBuilder hex = new StringBuilder();

            for (byte b : hashBytes) {
                String hexChar = Integer.toHexString(0xff & b);
                if (hexChar.length() == 1) hex.append('0');
                hex.append(hexChar);
            }

            return hex.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

}