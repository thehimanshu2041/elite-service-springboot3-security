package com.elite.core.exception.exceptions;

import com.elite.core.exception.EFault;

public class ExceptionResolver extends RuntimeException {

    protected EFault efault;
    protected Object[] messageArguments;

    public ExceptionResolver(String message) {
        super(message);
    }

    public ExceptionResolver(String message, Throwable cause) {
        super(message, cause);
    }

    public ExceptionResolver(Throwable cause) {
        super(cause);
    }

    public ExceptionResolver(ExceptionResolver cause) {
        this(cause.getCause(), cause.getFaultCode(), cause.getMessageArguments());
    }

    public ExceptionResolver(Throwable cause, EFault efault, Object... messageArguments) {
        super(cause);
        this.efault = efault;
        this.messageArguments = messageArguments;
    }

    public ExceptionResolver(EFault efault, Object... messageArguments) {
        this((Throwable) null, efault, messageArguments);
    }

    public EFault getFaultCode() {
        return this.efault;
    }

    public Object[] getMessageArguments() {
        return this.messageArguments;
    }
}
