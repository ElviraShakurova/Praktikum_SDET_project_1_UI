package tests;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pages.BasePage;
import pages.ListOfCustomersPage;
import java.util.Arrays;
import java.util.Collection;

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
        ListOfCustomersPage listOfCustomersPage = basePage.clickOnCustomersButton()
                .clickOnInputSearch()
                .clearSearch()
                .setInputSearch(searchQuery);
        String actualSearchResult = listOfCustomersPage.getSearchResults();
        Assert.assertTrue(actualSearchResult.contains(expectedSearchResult));
    }
}
