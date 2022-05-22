package cn.rubitcat.dao.database;

import cn.rubitcat.domain.database.Share;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IShareDao {
    @Select("SELECT * FROM E_SHARE")
    @Results(id = "share" , value = {
            @Result(id = true ,property = "shareID", column = "SHARE_ID"),
            @Result(property = "accountID", column = "ACCOUNT_ID"),
            @Result(property = "destAccountID", column = "DEST_ACCOUNT_ID"),
            @Result(property = "shareKey", column = "SHARE_KEY"),
            @Result(property = "fileKeys", column = "FILE_KEYS"),
            @Result(property = "shareDate", column = "SHARE_DATE"),
            @Result(property = "closeDate", column = "CLOSE_DATE")
    })
    List<Share> findAllShare();

    @Select("SELECT * FROM E_SHARE WHERE #{shareID}")
    @ResultMap("share")
    Share findShareByID(Integer shareID);



}
