package cn.rubitcat.domain.message;

import cn.rubitcat.domain.mongodb.Directory;

import java.util.List;

public class DirectoryResponse extends ResponseBase {

    Directory  directory;

    @Override
    public String toString() {
        return "DirectoryResponse{" +
                "directory=" + directory +
                ", statusCode=" + statusCode +
                ", date=" + date +
                ", serverMessage='" + serverMessage + '\'' +
                '}';
    }

    public void setDirectory(Directory directory) {
        this.directory = directory;
    }

    public Directory getDirectory() {
        return directory;
    }
}
