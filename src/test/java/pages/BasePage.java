package pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
    private final WebDriver driver;
    @FindBy(xpath = "//button[@ng-click='addCust()']")
    private WebElement addCustomerButton;
    @FindBy(xpath = "//button[contains(text(),'Customers')]")
    private WebElement customersButton;
    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public RegistrationPage clickAddCustomerButton() {
        addCustomerButton.click();
        return new RegistrationPage(driver);
    }
    public ListOfCustomersPage clickOnCustomersButton() {
        customersButton.click();
        return new ListOfCustomersPage(driver);
    }
}