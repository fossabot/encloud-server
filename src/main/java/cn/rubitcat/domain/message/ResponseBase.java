package cn.rubitcat.domain.message;

import java.util.Date;

public class ResponseBase {
    StatusCode statusCode;
    Date date;
    String      serverMessage;

    @Override
    public String toString() {
        return "ResponseBase{" +
                "statusCode=" + statusCode +
                ", date=" + date +
                ", serverMessage='" + serverMessage + '\'' +
                '}';
    }

    public void setStatusCode(StatusCode statusCode) {
        this.statusCode = statusCode;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setServerMessage(String serverMessage) {
        this.serverMessage = serverMessage;
    }

    public StatusCode getStatusCode() {
        return statusCode;
    }

    public Date getDate() {
        return date;
    }

    public String getServerMessage() {
        return serverMessage;
    }
}
