package exceptions;
// Logging caught exceptions.

import java.util.logging.*;
import java.io.*;

/**
 * @see LoggingException LoggingException将所有记录日志的基础设施都构建在异常
 * 自身中，使得它所使用的方式非常方便，并因此不需要客户端程序员的干预就可以自动运行，
 * 但是更常见的情形是我们需要捕获和记录其他人编写的异常，因此我们必须在异常处理程序中生成日志消息。
 */
public class LoggingExceptions2 {
    private static Logger logger =
            Logger.getLogger("LoggingExceptions2");        //在实践中，异常往往与日志记录框架搭配使用。slf4j接口和log4j、logback、java.util.logging都是其实现。

    static void logException(Exception e) {
        StringWriter trace = new StringWriter();
        e.printStackTrace(new PrintWriter(trace));
        logger.severe(trace.toString());
    }

    public static void main(String[] args) {
        try {
            throw new NullPointerException();
        } catch (NullPointerException e) {
            logException(e);
        }
    }
} /* Output: (90% match)
Aug 30, 2005 4:07:54 PM LoggingExceptions2 logException
SEVERE: java.lang.NullPointerException
        at LoggingExceptions2.main(LoggingExceptions2.java:16)
*///:~
