package com.attractpay.admin.utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

public class CommonUtils {
    /**
     * 获取请求IP
     *
     * @author ChenHuiLiang
     * @修改时间 2018年3月20日下午4:00:48
     * @param request
     * @return
     */
    public static String getIP(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = ip.indexOf(",");
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");
        if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            return ip;
        }
        return request.getRemoteAddr();
    }


    /**
     * 判断一个字符是不是纯数字，并根据subJoin判断Parameter是否符合该subJoin
     *
     * @author ChenHuiLiang
     * @修改时间 2018年3月21日下午2:59:16
     * @param Parameter
     *            要判断的数
     * @param subjoin
     *            附加条件，数字计算比较符号(<,>,=,<=,>=)
     * @param basics
     *            比较依据
     * @return
     */
    public static boolean checkIsNumber(String Parameter, String subjoin, Long basics) {
        boolean isNumber = Parameter.matches("-[0-9]+(.[0-9]+)?|[0-9]+(.[0-9]+)?");
        if (isNumber) {
            if (StringUtils.isNotBlank(subjoin) && basics != null) {
                boolean returnResult;
                BigDecimal bd = new BigDecimal(basics);
                switch (subjoin) {
                    case "<":
                        returnResult = new BigDecimal(Parameter).compareTo(bd) < 0;
                        break;

                    case ">":
                        returnResult = new BigDecimal(Parameter).compareTo(bd) > 0;
                        break;

                    case "=":
                        returnResult = new BigDecimal(Parameter).compareTo(bd) == 0;
                        break;

                    case "<=":
                        returnResult = new BigDecimal(Parameter).compareTo(bd) <= 0;
                        break;

                    case ">=":
                        returnResult = new BigDecimal(Parameter).compareTo(bd) >= 0;
                        break;

                    default:
                        returnResult = false;
                        break;
                }
                return returnResult;
            }else{
                return true;
            }
        }
        return false;
    }

    /** @Description create random string with fixed length
    * @author Junji
    * @date 2019/10/31 10:42
    * @param length
    	 * @param numeric_enable
    	 * @param alphabetic_enable
    * @return java.lang.String
    * @exception
    */
    public static String createRandomString(int length,boolean numeric_enable,boolean alphabetic_enable){
        if(numeric_enable && alphabetic_enable){
            return RandomStringUtils.randomAlphanumeric(length);
        }else if(numeric_enable){
            return RandomStringUtils.randomNumeric(length);
        }else{
            return RandomStringUtils.randomAlphabetic(length);
        }
    }

    /** @Description create ATP trans no
    * @author Junji
    * @date 2019/10/31 10:42
    * @param
    * @return java.lang.String
    * @exception
    */
    //ATP trans no = yyyyMMddHHmmss + 10bits random number
    public static String createATPTransNO(){
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault());
        String key = format.format(new Date());
        return key + createRandomString(10,true,false);
    }



}
