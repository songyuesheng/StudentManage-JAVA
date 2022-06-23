package com.StudentManage.enums;

public enum AppHttpCodeEnum {
    // 成功
    SUCCESS(200, "操作成功"),
    // 登录
    NEED_LOGIN(401, "需要登录后操作"),
    DELETE_FAIL(10000,"删除学生失败"),
    ADD_STUDENT_FAIL(1001,"添加学生失败"),
    EDIT_STUDENT_FAIL(1002,"编辑学生失败")
    ;

    int code;
    String msg;

    AppHttpCodeEnum(int code, String errorMessage) {
        this.code = code;
        this.msg = errorMessage;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
