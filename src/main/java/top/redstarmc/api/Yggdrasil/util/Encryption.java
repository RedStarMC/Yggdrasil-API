package top.redstarmc.api.Yggdrasil.util;

import top.redstarmc.api.Yggdrasil.Main;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encryption {
    private final static String salt = "3f8y9dak";
    private final Logger logger = Main.getLogger();
    public String passWrodSHA256(String password){
        //输入的字符串转换成字节数组
        String wait_pass_word = password+salt;
        byte[] bytes = wait_pass_word.getBytes(StandardCharsets.UTF_8);
        MessageDigest messageDigest = null;
        try {
            //获得SHA转换器
            messageDigest = MessageDigest.getInstance("SHA-256");
            //bytes是输入字符串转换得到的字节数组
            messageDigest.update(bytes);
        } catch (NoSuchAlgorithmException e) {
           logger.error("SHA签名过程中出现错误,算法异常");
        }
        //转换并返回结果，也是字节数组，包含16个元素
        byte[] digest = new byte[0];
        if (messageDigest != null) {
            digest = messageDigest.digest();
        }
        //字符数组转换成字符串返回
        return byteArrayToHexString(digest);
    }

    private static String byteArrayToHexString(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            //java.lang.Integer.toHexString() 方法的参数是int(32位)类型，
            //如果输入一个byte(8位)类型的数字，这个方法会把这个数字的高24为也看作有效位，就会出现错误
            //如果使用& 0XFF操作，可以把高24 位置0以避免这样错误
            String temp = Integer.toHexString(b & 0xFF);
            if (temp.length() == 1) {
                //1得到一位的进行补0操作
                builder.append("0");
            }
            builder.append(temp);
        }
        return builder.toString();
    }

}
