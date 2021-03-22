package com.jleo.jcontrol.session.security;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author jleo
 * @date 2021/1/20
 */
@Component
public class AESUtil {
    public static final String defaultCharset = "UTF-8";
    public static final String KEY_AES = "AES";
    public static final String KEY_MD5 = "MD5";
    public static MessageDigest md5Digest;
    public static SecretKeySpec keySpec;
    static {
        try {
            md5Digest = MessageDigest.getInstance(KEY_MD5);
        } catch (NoSuchAlgorithmException e) {

        }
    }

    /**
     * 加密
     *
     * @param data 需要加密的内容
     * @param key 加密密码
     * @return
     */
    public static String encrypt(String data, String key) {
        return doAES(data, key, Cipher.ENCRYPT_MODE);
    }
    /**
     * 解密
     *
     * @param data 待解密内容
     * @param key 解密密钥
     * @return
     */
    public static String decrypt(String data, String key) {
        return doAES(data, key, Cipher.DECRYPT_MODE);
    }


    /**
     * 加解密
     *
     * @param data
     * @param key
     * @param mode
     * @return
     */
    private static String doAES(String data, String key, int mode) {
        try {
            boolean encrypt = mode == Cipher.ENCRYPT_MODE;
            byte[] content;
            if (encrypt) {
                content = data.getBytes(defaultCharset);
            } else {
                content = Base64.decodeBase64(data.getBytes());
            }
            if (keySpec == null) {
                keySpec = new SecretKeySpec(md5Digest.digest(key.getBytes(defaultCharset))
                        , KEY_AES);
            }
            Cipher cipher = Cipher.getInstance(KEY_AES);// 创建密码器
            cipher.init(mode, keySpec);// 初始化
            byte[] result = cipher.doFinal(content);
            if (encrypt) {
                return new String(Base64.encodeBase64(result));
            } else {
                return new String(result, defaultCharset);
            }
        } catch (Exception e) {
            //e.printStackTrace();
            keySpec = null;
            return null;
        }
    }

}
