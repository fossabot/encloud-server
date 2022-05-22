package cn.rubitcat.dao.redis;

public interface ISessionDao {

    //设置session
    public Boolean addSession(String sessionID);

    public Boolean addSessionAttribute(String sessionID, String name, String value);

    public String  getSessionAttribute(String sessionID, String name);

    public Boolean delSessionAttribute(String sessionID, String name);

    public Boolean delSession(String sessionID);

}
