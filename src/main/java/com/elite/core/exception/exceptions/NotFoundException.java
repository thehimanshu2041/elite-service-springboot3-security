package com.elite.core.exception.exceptions;

import com.elite.core.exception.EFault;
import com.elite.core.factory.MessageResource;

public class NotFoundException extends ExceptionResolver {

    private static final long serialVersionUID = 1L;

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(Exception exception) {
        super(exception);
    }

    public NotFoundException(EFault efault, Object... messageArguments) {
        super(MessageResource.getMessage(efault));
    }
}
