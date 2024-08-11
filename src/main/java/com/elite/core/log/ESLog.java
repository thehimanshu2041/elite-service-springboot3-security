package com.elite.core.log;

import org.springframework.context.support.ResourceBundleMessageSource;

public enum ESLog implements ELog {

    ES_001, ES_002, ES_003, ES_004, ES_005, ES_006, ES_007, ES_008, ES_009, ES_010,
    ES_011, ES_012, ES_013, ES_014, ES_015, ES_016, ES_017, ES_018, ES_019, ES_020, ES_021,
    ES_022, ES_023, ES_024, ES_025, ES_026, ES_027, ES_028, ES_029, ES_030, ES_031, ES_032;
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
