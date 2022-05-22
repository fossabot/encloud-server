package cn.rubitcat.service.impl;

import cn.rubitcat.dao.redis.ISessionDao;
import cn.rubitcat.dao.redis.impl.SessionDao;
import cn.rubitcat.service.ISessionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SessionServices implements ISessionServices {

    @Autowired
    ISessionDao sessionDao;

    @Override
    public String initSession() {
        String uuid = UUID.randomUUID().toString().replace("-","");
        sessionDao.addSession(uuid);
        return uuid;
    }

    @Override
    public Boolean setSessionAttribute(String sessionID, String name, String value) {
        Boolean result = sessionDao.addSessionAttribute(sessionID, name, value);
        return  result;
    }

    @Override
    public String getSessionAttribute(String sessionID, String name) {
        String sessionAttribute = sessionDao.getSessionAttribute(sessionID, name);
        return sessionAttribute;
    }

    @Override
    public Boolean delSessionAttribute(String sessionID, String name) {
        Boolean result = sessionDao.delSessionAttribute(sessionID, name);
        return result;
    }

    @Override
    public Boolean closeSession(String sessionID) {
        Boolean result = sessionDao.delSession(sessionID);
        return result;
    }


    @Override
    public String initToken(String sessionID, Integer accountID) {
        String token = UUID.randomUUID().toString().replace("-","");
        sessionDao.addSessionAttribute(sessionID,"token", token);
        sessionDao.addSessionAttribute(sessionID,"accountID", accountID.toString());
        return token;
    }

    @Override
    public Boolean verifiyToken(String sessionID, String token) {
        String sessionToken = sessionDao.getSessionAttribute(sessionID, "token");
        System.out.println("从redis获取的sessiontoken"+sessionToken);
        if(sessionToken.equals(token)){
            return true;
        }
        return false;
    }
}
