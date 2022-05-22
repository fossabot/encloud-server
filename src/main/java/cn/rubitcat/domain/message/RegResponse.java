package cn.rubitcat.domain.message;

import java.util.Date;

public class RegResponse extends ResponseBase{

    @Override
    public String toString() {
        return "RegResponse{" +
                "statusCode=" + statusCode +
                ", date=" + date +
                ", serverMessage='" + serverMessage + '\'' +
                '}';
    }
}
