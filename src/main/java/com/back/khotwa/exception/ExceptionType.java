package com.back.khotwa.exception;

public enum ExceptionType {
    ENTITY_NOT_FOUND("not.found"),
    DUPLICATE_ENTITY("duplicate"),
    ENTITY_EXCEPTION("exception"),
    USER_NOT_ACTIVATED("not.activated"),
    COMPANY_NOT_APPROVED("not.approved"),
    REQUEST_BEING_PROCESSING("in.processing");
    String value;
    ExceptionType(String value){this.value = value;}

    String getValue(){return this.value;}
}
