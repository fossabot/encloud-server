package cn.rubitcat.dao.database;

import cn.rubitcat.domain.database.Account;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface IAccountDao {
    @Select("SELECT * FROM E_ACCOUNT")
    @Results(id = "account" , value = {
            @Result(id = true ,property = "accountID", column = "ACCOUNT_ID"),
            @Result(property = "password", column = "PASSWORD"),
            @Result(property = "accountName", column = "ACCOUNT_NAME"),
            @Result(property = "imgPath", column = "IMG_PATH"),
            @Result(property = "userID", column = "USER_ID"),
            @Result(property = "createDate", column = "CREATE_DATE"),
            @Result(property = "rootDirID", column = "ROOT_DIR_ID"),
            @Result(property = "mainKey", column = "MAIN_KEY"),
            @Result(property = "publicKey", column = "PUBLIC_KEY"),
            @Result(property = "privateKey", column = "PRIVATE_KEY")
    })
    public List<Account> findAllAccount();


    @Select("SELECT * FROM E_ACCOUNT WHERE ACCOUNT_ID = #{accountID}")
    @ResultMap("account")
    public Account findAccountByID(Integer accountID);


    @Select("SELECT * FROM E_ACCOUNT WHERE USER_ID = #{userID}")
    @ResultMap("account")
    public List<Account> findAccountsByUserID(Integer userID);


    @Select("SELECT * FROM E_ACCOUNT WHERE ACCOUNT_NAME = #{accountName}")
    @ResultMap("account")
    public Account findByAccountName(String accountName);


    @Update("INSERT INTO E_ACCOUNT " +
            "VALUES(#{accountID}, #{password},#{accountName},#{imgPath}，" +
            "#{userID}，SYSDATE,#{rootDirID},#{mainKey},#{publicKey}, #{privateKey})")
    @SelectKey(statement = "SELECT E_ACCOUNT_SEQ.NEXTVAL FROM DUAL",
            keyProperty = "accountID", before = true, resultType = Integer.class)
    public void addAccount(Account account);

    @Update("UPDATE E_ACCOUNT SET  ROOT_DIR_ID = #{rootDirID} WHERE ACCOUNT_ID = #{accountID}")
    public void setRootDirID(@Param("accountID") Integer accountID, @Param("rootDirID") String rootDirID);

}
