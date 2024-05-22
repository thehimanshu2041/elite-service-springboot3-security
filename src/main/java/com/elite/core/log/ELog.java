package com.elite.core.log;

import org.springframework.context.MessageSource;

import java.io.Serializable;

public interface ELog extends Serializable {

    String getKey();

    MessageSource getBundle();
}
