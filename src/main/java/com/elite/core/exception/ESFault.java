package com.elite.core.exception;

import org.springframework.context.support.ResourceBundleMessageSource;

public enum ESFault implements EFault {
    ES_000, ES_001, ES_002, ES_003, ES_004, ES_005, ES_006, ES_007, ES_008, ES_009, ES_010,
    ES_011, ES_012, ES_013, ES_014, ES_015, ES_016, ES_017, ES_018, ES_019;

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
