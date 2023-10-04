package pages;
import helpers.EnvHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CustomersListPage {
    private final WebDriver driver;

    private final WebDriverWait wait;

    @FindBy(css = "input[placeholder=\'Search Customer\']")
    private WebElement inputSearch;

    @FindBy(css = "a[ng-click*='fName']")
    private WebElement firstNameSort;

    @FindBy(css = "tbody tr")
    private List<WebElement> tableOfCustomers;

    public CustomersListPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, EnvHelper.getExplicitWaitDuration());
    }

    @Step("Нажатие на кнопку сортировки по имени")
    public CustomersListPage clickOnFirstNameSort() {
        wait.until(ExpectedConditions.visibilityOf(firstNameSort));
        firstNameSort.click();
        return this;
    }

    @Step("Получение списка текстовых значений элементов таблицы")
    public List<String> getTableText(){
        List<String> rowTexts = new ArrayList<>();
        for (WebElement row : tableOfCustomers) {
            rowTexts.add(row.getText());
        }
        return rowTexts;
    }

    @Step("Нажатие на поле поиска клиента")
    public CustomersListPage clickOnInputSearch() {
        wait.until(ExpectedConditions.visibilityOf(inputSearch));
        inputSearch.click();
        return this;
    }

    @Step("Ввод запроса в поле поиска клиента")
    public CustomersListPage setInputSearch(String searchQuery){
        inputSearch.sendKeys(searchQuery);
        return this;
    }

    @Step("Очистка поля поиска клиента")
    public CustomersListPage clearSearch() {
        inputSearch.clear();
        return this;
    }
}
