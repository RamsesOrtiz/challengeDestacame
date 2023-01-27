package com.destacame.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerPage {

    public static Logger logger(Class className) {

        return LogManager.getLogger(className);
    }
}
