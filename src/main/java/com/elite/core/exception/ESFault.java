package com.elite.core.exception;

import org.springframework.context.support.ResourceBundleMessageSource;

public enum ESFault implements EFault {
    ES_001,ES_002,ES_003,ES_004,ES_005;

    private static final String BUNDLE_NAME = "i18n.exception";

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
