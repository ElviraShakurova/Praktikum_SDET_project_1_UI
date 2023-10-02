package tests;

import helpers.EnvHelper;
import org.junit.rules.ExternalResource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import java.io.File;

public class BaseTest extends ExternalResource {
    WebDriver driver;
    @Override
    protected void before() throws Throwable {
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        ChromeDriverService service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File(EnvHelper.getDriverPath()))
                .build();

        ChromeOptions options = new ChromeOptions()
                .setBinary(EnvHelper.getBrowserPath());
        driver = new ChromeDriver(service, options);
        driver.manage().timeouts().implicitlyWait(EnvHelper.getImplicitWaitDuration());
        driver.get(EnvHelper.getBaseUrl());
    }
    @Override
    protected void after() {
        driver.quit();
    }
    public WebDriver getDriver() {
        return driver;
    }
}
