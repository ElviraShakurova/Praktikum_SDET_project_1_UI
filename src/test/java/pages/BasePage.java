package pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
    private final WebDriver driver;

    @FindBy(css = "[ng-class='btnClass1']")
    private WebElement addCustomerButton;

    @FindBy(css = "[ng-class='btnClass3']")
    private WebElement customersButton;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public RegistrationPage clickAddCustomerButton() {
        addCustomerButton.click();
        return new RegistrationPage(driver);
    }

    public CustomersListPage clickCustomersButton() {
        customersButton.click();
        return new CustomersListPage(driver);
    }
}