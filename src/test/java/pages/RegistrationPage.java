package pages;
import helpers.EnvHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.Assert.assertTrue;

public class RegistrationPage {
    private final WebDriver driver;

    private final WebDriverWait wait;

    @FindBy(css = "input[ng-model='fName'][placeholder='First Name'][required]")
    private WebElement inputFirstName;

    @FindBy(css = "input[ng-model='lName'][placeholder='Last Name'][required]")
    private WebElement inputLastName;

    @FindBy(css = "input[ng-model='postCd'][placeholder='Post Code'][required]")
    private WebElement inputPostCode;

    @FindBy(css = ".btn.btn-default")
    private WebElement addCustomerButton;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, EnvHelper.getExplicitWaitDuration());
    }

    @Step("Ввод имени")
    public RegistrationPage setInputFirstName(String firstName) {
        wait.until(ExpectedConditions.visibilityOf(inputFirstName));
        inputFirstName.sendKeys(firstName);
        return this;
    }

    @Step("Ввод фамилии")
    public RegistrationPage setInputLastName(String lastName) {
        inputLastName.sendKeys(lastName);
        return this;
    }

    @Step("Ввод фамилии")
    public RegistrationPage setInputPostCode(String postCode) {
        inputPostCode.sendKeys(postCode);
        return this;
    }

    @Step("Нажатие на кнопку 'Добавить клиента'")
    public RegistrationPage clickOnAddCustomerButton() {
        addCustomerButton.click();
        return this;
    }

    @Step("Проверка успешного сообщения")
    public RegistrationPage successAlert() {
        WebDriverWait wait = new WebDriverWait(driver, EnvHelper.getExplicitWaitDuration());
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String alertText = alert.getText();
        assertTrue(alertText.contains("Customer added successfully"));
        alert.accept();
        return this;
    }
}