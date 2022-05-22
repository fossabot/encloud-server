package cn.rubitcat.utils;

import javax.servlet.http.Cookie;

public class CommonUtils {
    public static String getSessionID(Cookie[] cookies){
        if(cookies != null){
            for (Cookie cookie : cookies) {
                if("SESSIONID".equals(cookie.getName())){
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
