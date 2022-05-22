package cn.rubitcat.domain.message;

public class DownloadRequest extends RequestBase {
    String  itemID;

    @Override
    public String toString() {
        return "DownloadRequest{" +
                "itemID='" + itemID + '\'' +
                ", token='" + token + '\'' +
                '}';
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getItemID() {
        return itemID;
    }
}
