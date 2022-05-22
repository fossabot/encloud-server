package cn.rubitcat.service;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.omg.CORBA.BooleanHolder;

public interface ISessionServices {
    String  initSession();
    Boolean setSessionAttribute(String sessionID,String name, String value);
    String  getSessionAttribute(String sessionID, String name);
    Boolean delSessionAttribute(String sessionID,String name);
    Boolean closeSession(String sessionID);

    String  initToken(String sessionID, Integer accountID);
    Boolean verifiyToken(String sessionID,  String token);


}
