package cn.rubitcat.domain.message;

public class DirectoryRequest extends RequestBase{
    String dirID;

    @Override
    public String toString() {
        return "DirectoryRequest{" +
                "dirID='" + dirID + '\'' +
                ", token='" + token + '\'' +
                '}';
    }

    public void setDirID(String dirID) {
        this.dirID = dirID;
    }

    public String getDirID() {
        return dirID;
    }
}
