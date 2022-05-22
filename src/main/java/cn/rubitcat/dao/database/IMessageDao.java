package cn.rubitcat.dao.database;

import cn.rubitcat.domain.database.Message;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IMessageDao {
    @Select("SELECT * FROM E_MESSAGE")
    @Results(id = "message" , value = {
            @Result(id = true ,property = "msgID", column = "MSG_ID"),
            @Result(property = "msgSenderID", column = "MSG_SENDER_ID"),
            @Result(property = "msgReceiverID", column = "MSG_RECEIVER_ID"),
            @Result(property = "msgTypeID", column = "MSG_TYPE_ID"),
            @Result(property = "msgContent", column = "MSG_CONTENT")
    })
    List<Message> findAllMessage();


    @Select("SELECT * FROM E_MESSAGE WHERE MSG_ID = #{messageID}")
    @ResultMap("message")
    Message findMessageByID(Integer messageID);

    @Select("SELECT * FROM E_MESSAGE WHERE  MSG_RECEIVER_ID = #{receiverID}")
    @ResultMap("message")
    List<Message> findMessageByReceiverID(Integer receiverID);

}
