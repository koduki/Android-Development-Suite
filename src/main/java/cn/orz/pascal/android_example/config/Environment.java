package cn.orz.pascal.android_example.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * environment.xml loader.
 */
public class Environment {
    public enum Profile {DEV, PROD}

    private static Environment self = null;
    private Properties properties = new Properties();

    private Environment() {
        if (self == null) {
            self = this;
            try {
                this.properties.loadFromXML(Environment.class.getClassLoader().getResourceAsStream("environment.xml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static Environment getInstance() {
        if (self == null) {
            self = new Environment();
        }
        return self;
    }

    public Profile getProfile() {
        String profile = this.properties.getProperty("profile");
        return (profile.equals("production")) ? Profile.PROD : Profile.DEV;
    }
}
