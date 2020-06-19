package practice.automationtesting.in.utils;

import io.qameta.allure.Step;

public class Logger {
    private static org.apache.log4j.Logger consoleLogger = org.apache.log4j.Logger.getLogger(Logger.class);

    @Step("[INFO]: {s}")
    public static void info(String s) {
        consoleLogger.info(s);
    }

    @Step("[CASE]: {s}")
    public static void testLog(String s) {
        consoleLogger.info("\t\t\t" + s);
    }

    @Step("[ERROR]: {s}")
    public static void error(String s) {
        consoleLogger.error("\t\t\t" + s);
    }
}
