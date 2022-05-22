package cn.rubitcat.domain.message;

public class MkdirRequest extends RequestBase{

    String directoryID;
    String directoryName;

    @Override
    public String toString() {
        return "MkdirRequest{" +
                "directoryID='" + directoryID + '\'' +
                ", directoryName='" + directoryName + '\'' +
                ", token='" + token + '\'' +
                '}';
    }

    public void setDirectoryID(String directoryID) {
        this.directoryID = directoryID;
    }

    public void setDirectoryName(String directoryName) {
        this.directoryName = directoryName;
    }

    public String getDirectoryID() {
        return directoryID;
    }

    public String getDirectoryName() {
        return directoryName;
    }
}
