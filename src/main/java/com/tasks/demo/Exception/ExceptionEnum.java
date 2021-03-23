package com.tasks.demo.Exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.ToString;


@Getter
@ToString
public enum ExceptionEnum {

	RUNTIME_EXCEPTION(HttpStatus.BAD_REQUEST, "E0001"),
    ACCESS_DENIED_EXCEPTION(HttpStatus.UNAUTHORIZED, "E0002"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "E0003"),

    SECURITY_01(HttpStatus.INTERNAL_SERVER_ERROR, "S0001", "해당 관리번호가 존재하지 않습니다."),
    NOTINTO_01(HttpStatus.BAD_REQUEST,"E0001","해당 관리번호가 존재하지 않습니다.");

    private final HttpStatus status;
    private final String code;
    private String message;

    ExceptionEnum(HttpStatus status, String code) {
        this.status = status;
        this.code = code;
    }

    ExceptionEnum(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

}
