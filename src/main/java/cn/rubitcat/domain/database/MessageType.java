package cn.rubitcat.domain.database;

import java.util.Date;

public class MessageType {
    //字段
    Integer     megTypeID;
    String      msgTypeName;
    Date        msgTypeCreateDate;

    @Override
    public String toString() {
        return "MessageType{" +
                "megTypeID=" + megTypeID +
                ", msgTypeName='" + msgTypeName + '\'' +
                ", msgTypeCreateDate=" + msgTypeCreateDate +
                '}';
    }

    public void setMegTypeID(Integer megTypeID) {
        this.megTypeID = megTypeID;
    }

    public void setMsgTypeName(String msgTypeName) {
        this.msgTypeName = msgTypeName;
    }

    public void setMsgTypeCreateDate(Date msgTypeCreateDate) {
        this.msgTypeCreateDate = msgTypeCreateDate;
    }

    public Integer getMegTypeID() {
        return megTypeID;
    }

    public String getMsgTypeName() {
        return msgTypeName;
    }

    public Date getMsgTypeCreateDate() {
        return msgTypeCreateDate;
    }
}
