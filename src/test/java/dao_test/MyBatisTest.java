package dao_test;

import cn.rubitcat.dao.database.*;
import cn.rubitcat.domain.database.*;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.InputStream;
import java.util.List;


@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:springcontext.xml")
public class MyBatisTest {

    SqlSession  sqlSession  =   null;


    //测试Mybatis单独运行
    @BeforeEach
    void init(){
        InputStream rs = IDaoTest.class.getClassLoader().getResourceAsStream("mybatis.xml");
        SqlSessionFactoryBuilder  sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(rs);
        sqlSession = sqlSessionFactory.openSession(false);
    }

    //测试是否正常映射User实体
    @Test
    void mappingUserTest(){
        IUserDao mapper = sqlSession.getMapper(IUserDao.class);
        List<User> allUser = mapper.findAllUser();
        System.out.println(allUser);
    }

    //测试是否正常映射Account实体
    @Test
    void mappingAccountTest(){
        IAccountDao mapper = sqlSession.getMapper(IAccountDao.class);
        List<Account> allAccount = mapper.findAllAccount();
        System.out.println(allAccount);
    }

    //测试是否正常映射File实体
    @Test
    void mappingFileTest(){
        IFileDao mapper = sqlSession.getMapper(IFileDao.class);
        List<File> allFile = mapper.findAllFiles();
        System.out.println(allFile);
    }

    //测试是否正常映射Share实体
    @Test
    void mappingShareTest(){
        IShareDao mapper = sqlSession.getMapper(IShareDao.class);
        List<Share> allShare = mapper.findAllShare();
        System.out.println(allShare);
    }

    //测试是否正常映射Group体
    @Test
    void mappingGroupTest(){
        IGroupDao mapper = sqlSession.getMapper(IGroupDao.class);
        List<Group> allGroup = mapper.findAllGroup();
        System.out.println(allGroup);
    }

    //测试是否正常映射Message体
    @Test
    void mappingMessageTest(){
        IMessageDao mapper = sqlSession.getMapper(IMessageDao.class);
        List<Message> allMessage = mapper.findAllMessage();
        System.out.println(allMessage);
    }

    //插入测试
    @Test
    void insertUser(){
        IUserDao mapper = sqlSession.getMapper(IUserDao.class);
        User user = new User();
        user.setUserID(2);
        user.setAccounts(null);
        user.setName("陈宏峰2");
        user.setPhone("16677658849");
        user.setEmail("157504572@qq.com");
        user.setRegistrationDate(null);
        mapper.addUser(user);
        sqlSession.commit();
    }

    //Spring集成MyBatis
}
