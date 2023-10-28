package com.example.secondSeminar.common.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access =  AccessLevel.PRIVATE)
public enum SuccessType {

    /**
     * 200 OK
     */
    PROCESS_SUCCESS(HttpStatus.OK, "OK"),
    GET_MEMBER_SUCCESS(HttpStatus.OK, "특정 사용자 정보 단건 조회 성공"),
    GET_MEMBER_LIST_SUCCESS(HttpStatus.OK, "전체 사용자 정보 조회 성공"),
    UPDATE_MEMBER_SUCCESS(HttpStatus.OK, "사용자 정보 수정 성공"),
    GET_POST_SUCCESS(HttpStatus.OK, "특정 게시물 단건 조회 성공"),
    GET_POST_LIST_SUCCESS(HttpStatus.OK, "특정 사용자의 게시물 전체 조회 성공"),
    UPDATE_POST_SUCCESS(HttpStatus.OK, "게시물 내용 수정 성공"),

    /**
     * 201 CREATED
     */
    CREATE_MEMBER_SUCCESS(HttpStatus.CREATED, "신규 사용자 생성 성공"),
    CREATE_POST_SUCCESS(HttpStatus.CREATED, "신규 게시물 생성 성공"),

    /**
     * 204 NO CONTENT
     */
    DELETE_MEMBER_SUCCESS(HttpStatus.NO_CONTENT, "사용자 삭제 성공"),
    DELETE_POST_SUCCESS(HttpStatus.NO_CONTENT, "게시물 삭제 성공")
    ;

    private final HttpStatus httpStatus;
    private final String message;

    public int getHttpStatusCode() {
        return httpStatus.value();
    }
}
