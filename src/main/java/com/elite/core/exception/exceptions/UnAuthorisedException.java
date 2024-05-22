package com.elite.core.exception.exceptions;

import com.elite.core.exception.EFault;
import com.elite.core.factory.MessageResource;

public class UnAuthorisedException extends ExceptionResolver {

    private static final long serialVersionUID = 1L;

    public UnAuthorisedException(String message) {
        super(message);
    }

    public UnAuthorisedException(Exception exception) {
        super(exception);
    }

    public UnAuthorisedException(EFault efault, Object... messageArguments) {
        super(MessageResource.getMessage(efault));
    }
}
