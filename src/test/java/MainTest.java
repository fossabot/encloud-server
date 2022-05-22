import cn.rubitcat.dao.database.IDaoTest;
import cn.rubitcat.dao.database.IAccountDao;
import cn.rubitcat.domain.database.Account;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class MainTest {
    public static void main(String[] args) {
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder   =   null;
        SqlSessionFactory sqlSessionFactory                 =   null;
        SqlSession sqlSession                               =   null;
        InputStream rs = IDaoTest.class.getClassLoader().getResourceAsStream("mybatis.xml");
        sqlSessionFactoryBuilder    =   new SqlSessionFactoryBuilder();
        sqlSessionFactory           =   sqlSessionFactoryBuilder.build(rs);
        sqlSession                  =   sqlSessionFactory.openSession(false);

        IAccountDao mapper = sqlSession.getMapper(IAccountDao.class);
        Account accountByID = mapper.findAccountByID(1);


    }

}
