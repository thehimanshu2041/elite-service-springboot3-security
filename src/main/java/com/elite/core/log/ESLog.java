package com.elite.core.log;

import org.springframework.context.support.ResourceBundleMessageSource;

public enum ESLog implements ELog {

    ES_001;

    private static final String BUNDLE_NAME = "i18n.message";

    @Override
    public String getKey() {
        return toString();
    }

    @Override
    public ResourceBundleMessageSource getBundle() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename(BUNDLE_NAME);
        return messageSource;
    }
}
