package com.strongliu.blog.utility;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * Created by liuyuzhe on 2017/5/1.
 */
public class SecurityUtil {
    public static String encryptAES(String data, String keyData) throws Exception {
        SecretKey key = new SecretKeySpec(keyData.getBytes("UTF-8"), "AES");

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedBytes = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decryptAES(String data, String keyData) throws Exception {
        SecretKey key = new SecretKeySpec(keyData.getBytes("UTF-8"), "AES");

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);

        byte[] textBytes = Base64.getDecoder().decode(data);
        byte[] decValue = cipher.doFinal(textBytes);
        return new String(decValue);
    }
}
