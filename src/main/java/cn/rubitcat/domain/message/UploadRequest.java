package cn.rubitcat.domain.message;

public class UploadRequest extends RequestBase {
    String  directoryID;
    String  fileName;
    String  fileKey;
    String  hash;
    Integer size;

    @Override
    public String toString() {
        return "UploadRequest{" +
                "directoryID='" + directoryID + '\'' +
                ", fileName='" + fileName + '\'' +
                ", fileKey='" + fileKey + '\'' +
                ", hash='" + hash + '\'' +
                ", size=" + size +
                '}';
    }

    public void setDirectoryID(String directoryID) {
        this.directoryID = directoryID;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setFileKey(String fileKey) {
        this.fileKey = fileKey;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getDirectoryID() {
        return directoryID;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileKey() {
        return fileKey;
    }

    public String getHash() {
        return hash;
    }

    public Integer getSize() {
        return size;
    }
}
