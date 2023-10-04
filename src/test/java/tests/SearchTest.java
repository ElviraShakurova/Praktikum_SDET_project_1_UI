package tests;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pages.BasePage;
import pages.CustomersListPage;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class SearchTest {
    @Rule
    public BaseTest driverRule = new BaseTest();

    private final String searchQuery;

    private final String expectedSearchResult;

    public SearchTest(String searchQuery, String expectedSearchResult) {
        this.searchQuery = searchQuery;
        this.expectedSearchResult = expectedSearchResult;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][]{
                {"Harry", "Harry"},
                {"Potter", "Potter"},
                {"E725JB", "E725JB"},
                {"1004", "1004"}
        });
    }

    @DisplayName("Тест поиска клиента")
    @Description("Тест проверяет поиск клиента по имени, фамилии и почтового индекса")
    @Test
    public void testSearchResults(){
        BasePage basePage = new BasePage(driverRule.getDriver());
        CustomersListPage customersListPage = basePage.clickCustomersButton()
                .clickOnInputSearch()
                .clearSearch()
                .setInputSearch(searchQuery);
        List<String> actualSearchResult = customersListPage.getTableText();
        Assert.assertTrue(actualSearchResult.contains(expectedSearchResult)); // Проверяем, что фактический результат поиска содержит ожидаемый результат
    }
}
