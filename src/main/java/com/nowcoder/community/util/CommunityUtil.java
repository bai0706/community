package com.nowcoder.community.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;

import java.util.UUID;

public class CommunityUtil {

    //功能：生成随机字符串
    public static String generateUUID(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    //MD5加密
    //只能加密，不能解密，且对某值的加密是永远固定的值  abc-> abc123def
    //+salt(随机字符串)  abc + 28dfv74  -> jce45p56468FV
    public static String md5(String key){
        //使用apach的工具去判断：只要是个空的值就不去处理
        if(StringUtils.isBlank(key)){
            return null;
        }
        return DigestUtils.md5DigestAsHex(key.getBytes());
    }
}
