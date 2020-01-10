package com.attractpay.admin.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DataTransformationUtils {

    public static final char UNDERLINE = '_';



    /**
     * 实体对象转成Map
     *
     * @param obj 实体对象
     * @return
     */
    public static Map<String, Object> object2Map(Object obj) {
        Map<String, Object> map = new HashMap<>();
        if (obj == null) {
            return map;
        }
        Class<? extends Object> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                map.put(field.getName(), field.get(obj));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }




    //TODO whether this method is needed or not
    /**
     * 把map的key转换成驼峰命名
     * @param map
     * @return
     */
    /*public static Map<String, Object> mapKeyToCamel(Map<String, Object> map) {
        Map resultMap = new HashMap();
        if (map != null) {
            Iterator var2 = map.entrySet().iterator();

            while (var2.hasNext()) {
                Map.Entry<String, Object> entry = (Map.Entry) var2.next();
                resultMap.put(underlineToCamel((String) entry.getKey()), map.get(entry.getKey()));
            }
            map.clear();
        }
        return resultMap;
    }*/

    /** @Description 参数名从下划线连接转换驼峰
     * @author Junji
     * @date 2019/11/4 17:56
     * @param param
     * @return java.lang.String
     * @exception
     */
   /* public static String underlineToCamel(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (c == UNDERLINE) {
                if (++i < len) {
                    sb.append(Character.toUpperCase(param.charAt(i)));
                }
            } else {
                sb.append(Character.toLowerCase(param.charAt(i)));
            }
        }
        return sb.toString();
    }*/


}
