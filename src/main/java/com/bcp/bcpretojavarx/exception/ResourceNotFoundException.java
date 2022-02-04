package com.bcp.bcpretojavarx.exception;

public class ResourceNotFoundException extends Exception
{
    public ResourceNotFoundException(final String message) {
        super(message);
    }

    public ResourceNotFoundException(final String message,final Throwable cause) {
        super(message, cause);
    }
}
