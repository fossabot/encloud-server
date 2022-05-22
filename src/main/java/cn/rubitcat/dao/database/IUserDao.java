package cn.rubitcat.dao.database;

import cn.rubitcat.domain.database.User;
import com.sun.java.swing.plaf.windows.WindowsTextAreaUI;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IUserDao {
    @Select("SELECT * FROM E_USER")
    @Results(id = "user", value = {
            @Result(id = true, property = "userID", column = "USER_ID"),
            @Result(property = "name", column = "USER_NAME"),
            @Result(property = "email", column = "EMAIL"),
            @Result(property = "phone", column = "PHONE"),
            @Result(property = "registrationDate", column = "REGISTRATRION_DATE")
    })
    public List<User> findAllUser();


    @Select("SELECT * FROM E_USER WHERE USER_ID = #{userID}")
    @ResultMap("user")
    public User findByID(Integer userID);


    @Update("INSERT INTO E_USER  VALUES(#{userID}, #{name}, #{email}, #{phone} , SYSDATE)")
    @SelectKey(statement = "SELECT E_USER_SEQ.NEXTVAL AS USER_ID FROM DUAL", keyProperty = "userID", before = true, resultType = Integer.class)
    public void addUser(User user);

    @Select("SELECT * FROM E_USER WHERE USER_ID IN " +
            "(SELECT USER_ID FROM E_ACCOUNT WHERE ACCOUNT_ID = #{accountID}) ")
    @ResultMap("user")
    public User findByAccountID(Integer accountID);

}
