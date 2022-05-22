package cn.rubitcat.domain.database;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties({"handler"})
public class Message implements Serializable {
    //字段
    Integer     msgID;
    Integer     msgSenderID;
    Integer     msgReceiverID;
    Integer     msgTypeID;
    String      msgContent;

    //关系
    Account     account;


    @Override
    public String toString() {
        return "Message{" +
                "msgID=" + msgID +
                ", msgSenderID=" + msgSenderID +
                ", msgReceiverID=" + msgReceiverID +
                ", msgTypeID=" + msgTypeID +
                ", msgContent='" + msgContent + '\'' +
                ", account=" + account +
                '}';
    }

    public void setMsgID(Integer msgID) {
        this.msgID = msgID;
    }

    public void setMsgSenderID(Integer msgSenderID) {
        this.msgSenderID = msgSenderID;
    }

    public void setMsgReceiverID(Integer msgReceiverID) {
        this.msgReceiverID = msgReceiverID;
    }

    public void setMsgTypeID(Integer msgTypeID) {
        this.msgTypeID = msgTypeID;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Integer getMsgID() {
        return msgID;
    }

    public Integer getMsgSenderID() {
        return msgSenderID;
    }

    public Integer getMsgReceiverID() {
        return msgReceiverID;
    }

    public Integer getMsgTypeID() {
        return msgTypeID;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public Account getAccount() {
        return account;
    }
}
