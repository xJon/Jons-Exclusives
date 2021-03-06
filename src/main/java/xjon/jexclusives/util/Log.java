package xjon.jexclusives.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Log {

    public static final Logger LOGGER = LogManager.getLogger(Reference.MOD_NAME);

    public static void warn(String msg) {
        LOGGER.warn(msg);
    }

    public static void error(String msg) {
        LOGGER.error(msg);
    }

    public static void info(String msg) {
        LOGGER.info(msg);
    }

    public static void debug(String msg) {
        LOGGER.debug(msg);
    }

}
