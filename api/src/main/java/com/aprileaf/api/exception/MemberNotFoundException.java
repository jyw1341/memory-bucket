package com.aprileaf.api.exception;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;

import java.util.Locale;

public class MemberNotFoundException extends BaseException {

    public MemberNotFoundException(MessageSource messageSource) {
        super(messageSource.getMessage("notFound", null, Locale.KOREA));
    }

    @Override
    public int getStatusCode() {
        return HttpStatus.NOT_FOUND.value();
    }
}
