package dao_test;

import cn.rubitcat.dao.database.IDaoTest;
import cn.rubitcat.domain.database.Account;
import cn.rubitcat.domain.database.User;
import cn.rubitcat.service.IServiceTest;
import cn.rubitcat.service.IUserServices;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Date;
import java.util.Properties;

/**
 * 本测试类用于测试数据库.
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:springcontext.xml")
public class DataBaseTest {
    private Connection  con = null;
    @Autowired
    IUserServices userServices;

    @Autowired
    IServiceTest serviceTest;


    //获取连接
    @BeforeEach
    void getConnection() throws ClassNotFoundException, SQLException, IOException {
        //初始化信息
        InputStream ris = IDaoTest.class.getClassLoader().getResourceAsStream("database.properties");
        Properties properties = new Properties();
        properties.load(ris);
        String  driver     =  properties.getProperty("jdbc.driver");
        String  dburl      =  properties.getProperty("jdbc.url");
        String  user       =  properties.getProperty("jdbc.username");
        String  password   =  properties.getProperty("jdbc.password");

        //获取数据库连接
        Class.forName(driver);
        con = DriverManager.getConnection(dburl, user, password);
    }

    //释放连接
    @AfterEach
    void freeConnection() throws SQLException {
        if(con != null){
            con.close();
        }

    }

    //测试连接是否打开成功
    @Test
    void connectionTest(){
        if(con != null){
            System.out.println("连接打开成功");
        }
    }

    //JDBC接口测试
    @Test
    void queryTest() throws SQLException {
        PreparedStatement preparedStatement = con.prepareStatement("select * from person");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            System.out.print(resultSet.getString(1));
            System.out.print(" ");
            System.out.println(resultSet.getInt(2));
        }
    }


    //测试spirng集成数据库并提供事务支持
    @Test
    void tsetQuery(){
        serviceTest.testPrintPerson();
    }
    @Test
    void testUpdate(){
        serviceTest.testAddPerson();
    }
    @Test
    void testTransation(){
        serviceTest.testTx();
    }

    @Test
    void testtx2(){
        User user = new User();
        user.setName("reg");
        user.setEmail("regMe");
        user.setPhone("regM");
        user.setRegistrationDate(new java.util.Date());

        Account account = new Account();
        account.setPassword("regMes");
        account.setAccountName("regM");
        account.setImgPath("");
        account.setCreateDate(new Date());
        account.setRootDirID("1");
        account.setMainKey("regMes");
        account.setPublicKey("regMe");
        account.setPrivateKey("regMe");
        userServices.registration(user,account);
    }
}
