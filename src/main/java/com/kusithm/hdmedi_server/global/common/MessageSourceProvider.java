package com.kusithm.hdmedi_server.global.common;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Slf4j
@RequiredArgsConstructor
@Component
public class MessageSourceProvider {
    private final MessageSource messageSource;

    public String getEmergencyContent() {
        return messageSource.getMessage("emergency.content", null, Locale.KOREA);
    }

    public String getNonEmergencyContent() {
        return messageSource.getMessage("non.emergency.content", null, Locale.KOREA);
    }

    public String getNonSurveyContent() {
        return messageSource.getMessage("non.Survey.content", null, Locale.KOREA);
    }
}
