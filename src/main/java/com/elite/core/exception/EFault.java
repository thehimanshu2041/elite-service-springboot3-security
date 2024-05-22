package com.elite.core.exception;

import java.io.Serializable;
import org.springframework.context.MessageSource;

public interface EFault extends Serializable {

    String getKey();

    MessageSource getBundle();
}
