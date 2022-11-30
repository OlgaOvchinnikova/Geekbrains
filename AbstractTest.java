package RestApiGB;

import org.junit.jupiter.api.BeforeAll;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class AbstractTest {
    static Properties prop = new Properties();
    private static InputStream configFile;
    private static String apiKey;
    private static String apiKeyError;
    private static String baseUrl;
    private static String hashSpoon;
    private static String spoonName;



    @BeforeAll
     static void initTest() throws IOException {
        configFile = new FileInputStream("src/main/Resourses/my.properties");
        prop.load(configFile);

        apiKey = prop.getProperty("apiKey");
        baseUrl = prop.getProperty("base_url");
        apiKeyError = prop.getProperty("apiKeyError");
        hashSpoon = prop.getProperty("hashSpoon");
        spoonName = prop.getProperty("spoonName");
    }

    public static String getApiKey() {
        return apiKey;
    }

    public static String getBaseUrl() {
        return baseUrl;
    }

    public static String getApiKeyError() {
        return apiKeyError;
    }

    public static String getHashSpoon() {
        return hashSpoon;
    }

    public static String getSpoonName() {
        return spoonName;
    }
}
