package com.eam.utils;
/*
 * @author  Jason.Zhao
 * @E-mail: jason.zhao@assetech.cn
 * @date 创建时间：2017年12月25日 下午3:14:47
 * @version 1.0
 */

import org.apache.commons.codec.binary.Base64;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PwHasher {

    private static final String ALGORITHM="SHA-256";
    private static final String PREFIX="{S2X}";
    private static final char[] PADDING="@#$ABCDEFGHIJKLMNOPQRSTUVWXYZ/(-".toCharArray();
    private static PwHasher instance=new PwHasher();

    public static void main(String[] args) throws NoSuchAlgorithmException{
        String pwdHash="{S2X}tFYDl8Sznt1gkiU/2vxLt1N1MDYShkV1C9rDDGKx3GY=";
        System.out.println(isEqual(pwdHash,"Infor,2018","R5"));
        System.out.println(isEqual(pwdHash,"Infor,208","R"));
        System.out.println(PwHasher.getHashedPassword("Infor,201", "R"));
        System.out.println(PwHasher.getHashedPassword("Infor,2018", "R5"));
    }
    private static String getHashedPassword(String password,String userId)
            throws NoSuchAlgorithmException{
        byte[] salt = getSalt(userId);
        return instance.hash(password, salt);
    }
 
    private static boolean isEqual(String hashedPassword,String password2,String userId2)
    {
        boolean areEqual = false;
        if ((hashedPassword != null) && (password2 != null)) {
            try
            {
                String hashed2 = getHashedPassword(password2, userId2);
                areEqual = hashedPassword.equals(hashed2);
            }
            catch (Exception ignored) {}
        }
        return areEqual;
    }

    private static byte[] getSalt(String userId){
        StringBuilder sb = new StringBuilder(userId);
        int count = sb.length();int i = 0;
        while (count < 32)
        {
            sb.append(PADDING[(i++)]);
            count++;
        }
        return sb.toString().getBytes(StandardCharsets.UTF_8);
    }

    private String hash(String password, byte[] salt)
            throws NoSuchAlgorithmException{
        MessageDigest digest = MessageDigest.getInstance(ALGORITHM);
        digest.reset();
        digest.update(salt);
        byte[] hashValue = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        return PREFIX + Base64.encodeBase64String(hashValue);
    }
}
