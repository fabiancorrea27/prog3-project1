package co.edu.uptc.project1.exeptions;

import org.springframework.http.HttpStatus;

public enum TypeMessage {
    NOT_FOUND(HttpStatus.BAD_REQUEST.value(), "Not Found", 404),
    NOT_ADDED(HttpStatus.BAD_REQUEST.value(), "Not added", 410),
    MAX_SCHEDULES(HttpStatus.BAD_REQUEST.value(), "Schedules Number Exceeded", 414),
    SCHEDULES_CONFLICT(HttpStatus.BAD_REQUEST.value(), "Schedules Conflict", 415),
    INFORMATION_INCOMPLETE(HttpStatus.BAD_REQUEST.value(), "Information Incomplete", 430),
    CANT_DELETE(HttpStatus.BAD_REQUEST.value(), "Can't Delete", 411),
    NOT_UPDATED(HttpStatus.BAD_REQUEST.value(), "Not Update", 412),
    DELETED(HttpStatus.OK.value(), "Deleted", 211),
    UPDATED(HttpStatus.OK.value(), "Updated", 212);

    public final String message;
    public final int code;
    public final int codeHttp;

    private TypeMessage(int codeHttp, String message, int code) {
        {
            this.message = message;
            this.code = code;
            this.codeHttp = codeHttp;
        }

    }

}
