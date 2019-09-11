package utils;

import org.apache.log4j.Logger;

public class LogUtil {

    private static Logger logger = Logger.getLogger(LogUtil.class.getName());//

    public static void startTestCase(String sTestCaseName){

        logger.info("****************************************************************************************");

        logger.info("****************************************************************************************");

        logger.info("$$$$$$$$$$$$$$$$$$$$$                 "+sTestCaseName+ "       $$$$$$$$$$$$$$$$$$$$$$$$$");

        logger.info("****************************************************************************************");

        logger.info("****************************************************************************************");

    }


    public static void endTestCase(String sTestCaseName){

        logger.info("XXXXXXXXXXXXXXXXXXXXXXX             "+"-E---N---D-"+"             XXXXXXXXXXXXXXXXXXXXXX");

        logger.info("X");

        logger.info("X");

        logger.info("X");

        logger.info("X");

    }


    public static void info(String message) {

        logger.info(message);

    }

    public static void warn(String message) {

        logger.warn(message);

    }

    public static void error(String message) {

        logger.error(message);

    }

    public static void fatal(String message) {

        logger.fatal(message);

    }

    public static void debug(String message) {

        logger.debug(message);

    }

}
