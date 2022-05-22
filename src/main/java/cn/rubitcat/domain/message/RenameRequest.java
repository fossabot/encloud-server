package cn.rubitcat.domain.message;

import cn.rubitcat.domain.mongodb.ItemType;

public class RenameRequest extends RequestBase{
    ItemType    type;
    String      directoryID;
    String      itemID;
    String      newName;

    @Override
    public String toString() {
        return "RenameRequest{" +
                "type=" + type +
                ", directoryID='" + directoryID + '\'' +
                ", itemID='" + itemID + '\'' +
                ", newName='" + newName + '\'' +
                ", token='" + token + '\'' +
                '}';
    }

    public void setDirectoryID(String directoryID) {
        this.directoryID = directoryID;
    }

    public String getDirectoryID() {
        return directoryID;
    }

    public void setType(ItemType type) {
        this.type = type;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    public ItemType getType() {
        return type;
    }

    public String getItemID() {
        return itemID;
    }

    public String getNewName() {
        return newName;
    }
}
