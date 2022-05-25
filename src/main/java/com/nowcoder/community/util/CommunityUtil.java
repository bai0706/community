package com.nowcoder.community.util;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.util.DigestUtils;


import java.util.HashMap;
import java.util.Map;
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

    // 获取json字符
    public static String getJSONString(int code, String msg, Map<String, Object> map){
        JSONObject json = new JSONObject();
        json.put("code", code);
        json.put("msg", msg);
        if(map != null){
            for(String key : map.keySet()){
                json.put(key, map.get(key));
            }
        }
        return json.toString();
    }

    public static String getJSONString(int code, String msg){
        return getJSONString(code, msg, null);
    }

    public static String getJSONString(int code){
        return getJSONString(code, null, null);
    }

    public static void main(String[] args) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "zhangsan");
        map.put("age", 25);
        System.out.println(getJSONString(0, "ok", map));
    }
}
