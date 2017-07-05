package com.strongliu.blog.utility;

import com.strongliu.blog.constant.Constant;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * Created by liuyuzhe on 2017/5/1.
 */
public class SecurityUtil {

    public static SecretKey deriveKey() throws Exception {
        /* 耗时过长
        SecretKeyFactory factory = SecretKeyFactory.getInstance("AES");
        KeySpec keySpec = new PBEKeySpec(Constant.AES_PASSWORD.toCharArray(), Constant.AES_SALT.getBytes(), 65536, 256);
        SecretKey keyData = factory.generateSecret(keySpec);
        return new SecretKeySpec(keyData.getEncoded(), "AES");
        */

        // AES 算法位数: 128、192、256
        return new SecretKeySpec(Constant.AES_SALT.getBytes("UTF-8"), "AES");
    }

    public static String encryptAES(String data) throws Exception {
        SecretKey key = deriveKey();

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedBytes = cipher.doFinal(data.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decryptAES(String data) throws Exception {
        SecretKey key = deriveKey();

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);

        byte[] textBytes = Base64.getDecoder().decode(data);
        byte[] decValue = cipher.doFinal(textBytes);
        return new String(decValue, "UTF-8");
    }
}
