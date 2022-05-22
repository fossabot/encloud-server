package cn.rubitcat.domain.mongodb;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ItemType {
    DIR(1),
    FILE(2)
    ;


    private final Integer typeID;
    ItemType(Integer typeID) {
        this.typeID = typeID;
    }

    @JsonValue
    public Integer getTypeID() {
        return typeID;
    }
}
