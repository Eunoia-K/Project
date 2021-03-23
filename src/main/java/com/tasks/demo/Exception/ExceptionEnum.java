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
    NOTINTO_01(HttpStatus.BAD_REQUEST,"E0001","해당 관리번호가 존재하지 않습니다."),

    PAYMENT_01(HttpStatus.BAD_REQUEST,"E0001","결제금액이 잘못되었습니다.(100원이상 10억이하)"),
    PAYMENT_02(HttpStatus.BAD_REQUEST,"E0002","부가가치세는 결제금액보다 작아야됩니다."),
    PAYMENT_03(HttpStatus.BAD_REQUEST,"E0003","카드정보가 잘못입력되었습니다."),
    
    CANCEL_01(HttpStatus.BAD_REQUEST,"E0003","취소금액은 결제금액보다 작아야됩니다.");

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
