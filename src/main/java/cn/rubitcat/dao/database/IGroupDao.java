package cn.rubitcat.dao.database;

import cn.rubitcat.domain.database.Group;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IGroupDao {
    @Select("SELECT * FROM E_GROUP")
    @Results(id = "group" , value = {
            @Result(id = true ,property = "groupID", column = "GROUP_ID"),
            @Result(property = "groupName", column = "GROUP_NAME"),
            @Result(property = "createDate", column = "CREATE_DATE")
    })
    List<Group> findAllGroup();

    @Select("SELECT * FROM E_GROUP WHERE GROUP_ID = #{groupID}")
    @ResultMap("group")
    Group findGroupByID(Integer groupID);

}
