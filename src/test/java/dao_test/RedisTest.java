package dao_test;
import cn.rubitcat.dao.database.IDaoTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:springcontext.xml")
public class RedisTest {

    @Resource(name = "redisTemplate")
    ListOperations<String ,String> listOps;

    @Resource(name = "redisTemplate")
    RedisTemplate<String ,String> template;

    //测试Redis单独运行
    @Test
    void test1() throws IOException {
        InputStream ris = IDaoTest.class.getClassLoader().getResourceAsStream("database.properties");
        Properties properties = new Properties();
        properties.load(ris);
        Jedis jedis = new Jedis("192.168.3.240", 6379);
        jedis.set("redis_test", "1");
        String redis_test = jedis.get("redis_test");
        System.out.println(redis_test);
    }

    @Test
    void test2(){
//        template.setKeySerializer(new StringRedisSerializer());
//        template.boundListOps("template").leftPush("hello world");

        listOps.leftPush("template","hello world");
    }

}
