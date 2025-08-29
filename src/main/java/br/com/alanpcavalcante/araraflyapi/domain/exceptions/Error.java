package br.com.alanpcavalcante.araraflyapi.domain.exceptions;


import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;

/*
error: {
    status: 403,
    message: Invalid CPF,
    time: 28-08-2025-10:16
}
 */

public class Error {

    private final int status;
    private final String message;
    private final String time;

    public Error(int status, String message) {
        this.status = status;
        this.message = message;
        this.time = ZonedDateTime.now().toString();
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getTime() {
        return time;
    }
}


