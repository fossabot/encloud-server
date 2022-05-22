package cn.rubitcat.domain.mongodb;

import org.springframework.data.annotation.Id;

import java.util.Date;

public class DirectoryItem {
    ItemType    itemType;
    String      itemID;
    String      itemName;
    Date        createDate;
    Integer     itemSize;
    String      itemKey;
    String      itemHash;

    @Override
    public String toString() {
        return "DirectoryItem{" +
                "itemID='" + itemID + '\'' +
                ", itemType=" + itemType +
                ", itemName='" + itemName + '\'' +
                ", createDate=" + createDate +
                ", itemSize=" + itemSize +
                ", itemKey='" + itemKey + '\'' +
                ", itemHash='" + itemHash + '\'' +
                '}';
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setItemSize(Integer itemSize) {
        this.itemSize = itemSize;
    }

    public void setItemKey(String itemKey) {
        this.itemKey = itemKey;
    }

    public void setItemHash(String itemHash) {
        this.itemHash = itemHash;
    }

    public String getItemID() {
        return itemID;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public String getItemName() {
        return itemName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Integer getItemSize() {
        return itemSize;
    }

    public String getItemKey() {
        return itemKey;
    }

    public String getItemHash() {
        return itemHash;
    }
}
