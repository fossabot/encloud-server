package cn.rubitcat.domain.message;

import cn.rubitcat.domain.database.Message;
import cn.rubitcat.domain.database.User;

import java.util.Date;
import java.util.List;

public class MessageSycnResponse extends  ResponseBase{
    List<Message>   messages;

    @Override
    public String toString() {
        return "MessageSycnResponse{" +
                "messages=" + messages +
                ", statusCode=" + statusCode +
                ", date=" + date +
                ", serverMessage='" + serverMessage + '\'' +
                '}';
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public List<Message> getMessages() {
        return messages;
    }
}
