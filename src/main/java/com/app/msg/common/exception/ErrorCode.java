package com.app.msg.common.exception;

import com.app.msg.common.BaseEnum;
import org.apache.commons.lang3.StringUtils;

/**
 * @author chenjianzhong
 *         date 14-12-25
 */
public enum ErrorCode implements BaseEnum {
    SUCCESS("成功", "00"),
    FAIL("失败", "99");

    private String desc;
    private String code;

    ErrorCode(String desc, String code) {
        this.desc = desc;
        this.code = code;
    }

    public static ErrorCode getByCode(String code) {
        for (ErrorCode errorCode : ErrorCode.values()) {
            if (StringUtils.equals(errorCode.getCode(), code)) {
                return errorCode;
            }
        }

        throw new IllegalArgumentException("ErrorCode's code cannot be null.");
    }

    @Override
    public String getName() {
        return name();
    }

    @Override
    public String getDesc() {
        return this.desc;
    }

    public String getCode() {
        return code;
    }
}
