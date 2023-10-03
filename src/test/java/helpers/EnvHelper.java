package helpers;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class EnvHelper {
    private static final String ENV_FILE = ".env_local.properties";
    private static final Properties PROPERTIES = new Properties();

    static {
        try {
            PROPERTIES.load(new FileInputStream(ENV_FILE));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load environment properties file: " + ENV_FILE, e);
        }
    }

    public static String getDriverPath() {
        return PROPERTIES.getProperty("driver.path");
    }

    public static String getBrowserPath() {
        return PROPERTIES.getProperty("browser.path");
    }

    public static String getBaseUrl(){
        return PROPERTIES.getProperty("base.url");
    }

    public static Duration getImplicitWaitDuration() {
        int seconds = Integer.parseInt(PROPERTIES.getProperty("implicitly.wait.seconds", "5"));
        return Duration.ofSeconds(seconds);
    }

    public static Duration getExplicitWaitDuration() {
        int seconds = Integer.parseInt(PROPERTIES.getProperty("explicit.wait.seconds", "10"));
        return Duration.ofSeconds(seconds);
    }

    public static String getFirstName(){
        return PROPERTIES.getProperty("firstname");
    }

    public static String getLastName(){
        return PROPERTIES.getProperty("lastname");
    }

    public static String getPostCode(){
        return PROPERTIES.getProperty("postcode");
    }
}

