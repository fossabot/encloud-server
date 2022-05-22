package cn.rubitcat.dao.redis.impl;

import cn.rubitcat.dao.redis.ISessionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Date;

@Repository
public class SessionDao implements ISessionDao {

    @Autowired
    JedisPool jedisPool;

    @Override
    public Boolean addSession(String sessionId) {
        Jedis jedis = jedisPool.getResource();
        Long hset = jedis.hset(sessionId, "openDate", new Date().toString());
        jedis.close();
        return hset == 1 ? true : false;
    }

    @Override
    public Boolean addSessionAttribute(String sessionID, String name, String value) {
        Jedis jedis = jedisPool.getResource();
        Long hset = jedis.hset(sessionID, name, value);
        jedis.close();
        return hset == 1 ? true : false;
    }

    @Override
    public String getSessionAttribute(String sessionID, String name) {
        Jedis jedis = jedisPool.getResource();
        String result = jedis.hget(sessionID, name);
        jedis.close();
        return result;

    }

    @Override
    public Boolean delSessionAttribute(String sessionID, String name) {
        Jedis jedis = jedisPool.getResource();
        Long hdel = jedis.hdel(sessionID, name);
        jedis.close();
        return hdel == 1 ? true : false;
    }

    @Override
    public Boolean delSession(String sessionID) {
        Jedis jedis = jedisPool.getResource();
        Long del = jedis.del(sessionID);
        jedis.close();
        return del == 1 ? true : false ;
    }

}
