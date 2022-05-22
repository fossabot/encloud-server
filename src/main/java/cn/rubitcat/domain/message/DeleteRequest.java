package cn.rubitcat.domain.message;

import cn.rubitcat.domain.mongodb.ItemType;

public class DeleteRequest extends RequestBase{
    ItemType    type;
    String      directoryID;
    String      itemID;

    @Override
    public String toString() {
        return "DeleteRequest{" +
                "type=" + type +
                ", directoryID='" + directoryID + '\'' +
                ", itemID='" + itemID + '\'' +
                ", token='" + token + '\'' +
                '}';
    }

    public void setType(ItemType type) {
        this.type = type;
    }

    public void setDirectoryID(String directoryID) {
        this.directoryID = directoryID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public ItemType getType() {
        return type;
    }

    public String getDirectoryID() {
        return directoryID;
    }

    public String getItemID() {
        return itemID;
    }
}
