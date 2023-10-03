package tests;

import helpers.EnvHelper;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import java.io.File;

public class BaseTest implements TestRule {
    WebDriver driver;

    @Override
    public Statement apply(Statement statement, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                before();
                try {
                    statement.evaluate();
                } finally {
                    after();
                }
            }
        };
    }

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

    protected void after() {
        driver.quit();
    }

    public WebDriver getDriver() {
        return driver;
    }
}