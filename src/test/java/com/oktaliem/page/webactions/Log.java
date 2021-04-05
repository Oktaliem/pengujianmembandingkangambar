package com.oktaliem.page.webactions;

import org.apache.log4j.Logger;

public class Log {
    private Log() {throw new IllegalStateException("logging class"); }
    private static Logger logging = Logger.getLogger("com.oktaliem.automation");
    public static void info (String message) {logging.info(message); }
    public static void warn (String message) { logging.warn(message); }
    public static void error (String message) {logging.error(message); }
}
