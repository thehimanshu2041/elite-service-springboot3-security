package com.elite.core.factory;

import com.elite.core.exception.EFault;
import com.elite.core.log.ELog;

import java.util.Locale;
import java.util.MissingResourceException;

public class MessageResource {

    private MessageResource() {
    }

    public static String getMessage(final EFault efault) {
        if (efault == null) {
            return null;
        }
        String key = efault.getKey();
        try {
            return efault.getBundle().getMessage(key, null, null, Locale.ENGLISH);
        } catch (MissingResourceException missingResourceException) {
            String name = efault.getClass().getSimpleName();
            return "! Pair (code/message) missing in related bundle. Please provide a suitable message for the code : "
                    + name
                    + "."
                    + key
                    + " !";
        }
    }

    public static String getMessage(final ELog ELog) {
        if (ELog == null) {
            return null;
        }
        String key = ELog.getKey();
        try {
            return ELog.getBundle().getMessage(key, null, null, Locale.ENGLISH);
        } catch (MissingResourceException missingResourceException) {
            String name = ELog.getClass().getSimpleName();
            return "! Pair (code/message) missing in related bundle. Please provide a suitable message for the code : "
                    + name
                    + "."
                    + key
                    + " !";
        }
    }
}
