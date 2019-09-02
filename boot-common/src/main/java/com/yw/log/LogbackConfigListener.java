package com.yw.log;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.util.StatusPrinter;
import java.io.File;
import java.io.IOException;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import org.slf4j.LoggerFactory;

@WebListener
public class LogbackConfigListener implements ServletRequestListener{
    private static volatile LogbackConfigListener singleton = null;

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {

    }

    @Override
    public void requestInitialized (ServletRequestEvent sre) {
        loadLogXml();
    }

    public static LogbackConfigListener getInstance() {
        if (singleton == null) {
            synchronized (LogbackConfigListener.class) {
                if (singleton == null) {
                    singleton = new LogbackConfigListener();
                }
            }
        }
        return singleton;
    }

    public static void load(String externalConfigFileLocation) throws IOException, JoranException {
        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();

        File externalConfigFile = new File(externalConfigFileLocation);
        if (!externalConfigFile.exists()) {
            throw new IOException("Logback External Config File Parameter does not reference a file that exists");
        }
        if (!externalConfigFile.isFile()) {
            throw new IOException("Logback External Config File Parameter exists, but does not reference a file");
        }
        if (!externalConfigFile.canRead()) {
            throw new IOException("Logback External Config File exists and is a file, but cannot be read.");
        }
        JoranConfigurator configurator = new JoranConfigurator();
        configurator.setContext(lc);
        lc.reset();
        configurator.doConfigure(externalConfigFileLocation);
        StatusPrinter.printInCaseOfErrorsOrWarnings(lc);
    }

    public static void loadLogXml() {
        try {
            StringBuilder sbStr = new StringBuilder();
            sbStr.append(File.separator).append("wls").append(File.separator).append("wls81").append(File.separator)
                    .append("envconfig").append(File.separator).append("hss").append(File.separator)
                    .append("logback.xml");
            load(sbStr.toString());
        } catch (Exception localException) {
        }
    }

    static {

    }
}
