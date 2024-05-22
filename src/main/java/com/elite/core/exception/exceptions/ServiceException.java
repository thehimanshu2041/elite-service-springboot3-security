package com.elite.core.exception.exceptions;

import com.elite.core.exception.EFault;
import com.elite.core.factory.MessageResource;

public class ServiceException extends ExceptionResolver {

    private static final long serialVersionUID = 1L;

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(Exception exception) {
        super(exception);
    }

    public ServiceException(EFault efault, Object... messageArguments) {
        super(MessageResource.getMessage(efault));
    }
}
