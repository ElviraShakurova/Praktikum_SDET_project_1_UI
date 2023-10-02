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

public class ListOfCustomersPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    @FindBy(css = "input[placeholder=\'Search Customer\']")
    private WebElement inputSearch;
    @FindBy(xpath = "//table[@class='table table-bordered table-striped']") // //td[@class='ng-binding']
    private WebElement searchResults;
    @FindBy(xpath = "//td[a[contains(text(), 'First Name')]]")
    private WebElement firstNameSort;
    @FindBy(xpath = "//td[contains(text(), 'Albus') or contains(text(), 'Ron') or contains(text(), 'Hermoine') or contains(text(), 'Neville')]")
    private List<WebElement> tableOfCustomers;
    public ListOfCustomersPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, EnvHelper.getExplicitWaitDuration());
    }
    @Step("Нажатие на кнопку сортировки по имени")
    public ListOfCustomersPage clickOnFirstNameSort() {
        wait.until(ExpectedConditions.visibilityOf(firstNameSort));
        firstNameSort.click();
        return this;
    }
    @Step("Сортировка имен в алфавитном порядке")
    public List<String> getSortedTableOfCustomers() {
        List<String> cellTexts = new ArrayList<>();
        for (WebElement cell : tableOfCustomers) {
            cellTexts.add(cell.getText());
        }
        Collections.sort(cellTexts);
        return cellTexts;
    }
    @Step("Сортировка имен в порядке обратном алфавиту")
    public boolean isTableOfCustomersSortedInReverseOrder() {
        List<String> reverseTexts = new ArrayList<>();
        for (WebElement cell : tableOfCustomers) {
            reverseTexts.add(cell.getText());
        }
        List<String> sortedCellTexts = new ArrayList<>(reverseTexts);
        Collections.sort(sortedCellTexts, Collections.reverseOrder());
        return reverseTexts.equals(sortedCellTexts);
    }
    @Step("Нажатие на поле поиска клиента")
    public ListOfCustomersPage clickOnInputSearch() {
        wait.until(ExpectedConditions.visibilityOf(inputSearch));
        inputSearch.click();
        return this;
    }
    @Step("Ввод запроса в поле поиска клиента")
    public ListOfCustomersPage setInputSearch(String searchQuery){
        inputSearch.sendKeys(searchQuery);
        return this;
    }
    @Step("Получение поисковой выдачи после ввода данных в поле поиска")
    public String getSearchResults() {
        return searchResults.getText();
    }
    @Step("Очистка поля поиска клиента")
    public ListOfCustomersPage clearSearch() {
        inputSearch.clear();
        return this;
    }
}
