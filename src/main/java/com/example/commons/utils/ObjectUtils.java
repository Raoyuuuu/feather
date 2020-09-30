package com.example.commons.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @Title:
 * @auther: raohr
 * @Date: 2020/9/30 17:22
 * @param:
 * @Description:
 * @return:
 * @throws:
 */
public class ObjectUtils {

    public ObjectUtils() {
    }

    public static Map<String, Object> objectToMap(Object obj) {
        JSONObject jsonObject = (JSONObject) JSON.toJSON(obj);
        Set<Map.Entry<String, Object>> entrySet = jsonObject.entrySet();
        Map<String, Object> map = new HashMap();
        Iterator var4 = entrySet.iterator();

        while(var4.hasNext()) {
            Map.Entry<String, Object> entry = (Map.Entry)var4.next();
            map.put(entry.getKey(), entry.getValue());
        }

        return map;
    }

}
