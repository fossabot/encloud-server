package cn.rubitcat.domain.message;

import com.fasterxml.jackson.annotation.JsonValue;

import javax.print.attribute.IntegerSyntax;

public enum StatusCode {
    MISSING_SESSION(1),
    CHECK_CODE_ERROR(2),
    REGISTRATION_ERROR(3),
    REGISTRATION_SUCCESS(4),
    LOGIN_SUCCESS(5),
    LOGIN_FAILED(6),
    TOKEN_VERIFY_ERROR(7),
    MESSAGE_SYNC_SUCCESS(8),
    USER_SYNC_SUCCESS(9),
    ACCOUNT_SYNC_SUCCESS(10),
    DIRECTORY_SYNC_SUCCESS(11),
    RENAME_SUCCESS(12),
    UPLOAD_FILE_SUCCESS(13),
    RENAME_FALSE(14),
    MKDIR_SUCCESS(15),
    MKDIR_FALSE(16),
    DELETE_SUCCESS(17),
    DELETE_FALSE(18)
    ;
    private final Integer  codeID;

    private StatusCode(Integer codeID){
        this.codeID = codeID;
    }

    @JsonValue
    public Integer getCodeID() {
        return codeID;
    }
}
