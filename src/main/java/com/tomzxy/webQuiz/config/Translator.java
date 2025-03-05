package com.tomzxy.webQuiz.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class Translator {
    private static ResourceBundleMessageSource resource;


    @Autowired
    public Translator(ResourceBundleMessageSource resource) {
        Translator.resource = resource;
    }

    public static String toLocale(String msgCode){
        Locale locale = LocaleContextHolder.getLocale();
        return resource.getMessage(msgCode, null, locale);
    }
}
