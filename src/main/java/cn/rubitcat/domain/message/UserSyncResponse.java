package cn.rubitcat.domain.message;

import cn.rubitcat.domain.database.User;

import java.util.Date;

public class UserSyncResponse extends ResponseBase{
    User user;

    @Override
    public String toString() {
        return "UserSyncResponse{" +
                "statusCode=" + statusCode +
                ", date=" + date +
                ", serverMessage='" + serverMessage + '\'' +
                ", user=" + user +
                '}';
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
