package cn.rubitcat.dao.database;

import cn.rubitcat.domain.database.File;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IFileDao {
    @Select("SELECT * FROM E_FILE")
    @Results(id = "file" , value = {
            @Result(id = true, property = "", column = "FILE_ID"),
            @Result(property = "accountID", column = "ACCOUNT_ID"),
            @Result(property = "fileName", column = "FILE_NAME"),
            @Result(property = "hash", column = "HASH"),
            @Result(property = "fileKey", column = "FILE_KEY"),
            @Result(property = "fileSize", column = "FILE_SIZE"),
            @Result(property = "uploadDate", column = "UPLOAD_DATE"),
            @Result(property = "isFromShare", column = "IS_FROM_SHARE")
    })
    public List<File>  findAllFiles();


    @Select("SELECT * FROM E_FILE WHERE FILE_ID = #{fileID}")
    @ResultMap("file")
    public File findFileByID(Integer fileID);


    @Update("INSERT INTO E_FILE " +
            "VALUES(#{fileID},#{accountID},#{fileName}," +
            "#{hash},#{fileKey},#{fileSize},#{uploadDate},#{isFromShare})")
    @SelectKey(statement = "SELECT E_FILE_SEQ.NEXTVAL FROM DUAL",
            keyProperty = "fileID", before = true, resultType = Integer.class)
    public void addFile(File file);

    @Update("UPDATE E_FILE SET FILE_NAME = #{fileName} WHERE FILE_ID = #{fileID}")
    public void setFileNameByID(@Param("fileID") Integer fileID, @Param("fileName") String fileName);

    @Update("DELETE FROM E_FILE WHERE FILE_ID = #{fileID}")
    public void deleteFileByID(@Param("fileID") Integer fileID);


}
