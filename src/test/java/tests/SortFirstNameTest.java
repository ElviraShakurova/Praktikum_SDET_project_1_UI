package tests;
import io.qameta.allure.junit4.DisplayName;
import jdk.jfr.Description;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import pages.BasePage;
import pages.CustomersListPage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortFirstNameTest {
    @Rule
    public BaseTest driverRule = new BaseTest();

    @DisplayName("Тест сортировки клиента по имени в порядке обратном алфавиту")
    @Description("Тест проверяет сортировку клиента по имени в порядке обратном алфавиту")
    @Test
    public void testSortFirstNameInReverseOrder(){
        BasePage basePage = new BasePage(driverRule.getDriver());
        CustomersListPage customersListPage = basePage.clickCustomersButton();
        customersListPage.clickOnFirstNameSort();
        List<String> sortedTableText = customersListPage.getTableText();
        List<String> expectedTableText = new ArrayList<>(sortedTableText);
        Collections.sort(expectedTableText, Collections.reverseOrder());
        Assert.assertEquals(sortedTableText, expectedTableText);
    }

    @DisplayName("Тест сортировки клиента по имени по алфавиту")
    @Description("Тест проверяет сортировку клиента по имени в алфавитном порядке")
    @Test
    public void testSortFirstNameInOrder(){
        BasePage basePage = new BasePage(driverRule.getDriver());
        CustomersListPage customersListPage = basePage.clickCustomersButton();
        customersListPage.clickOnFirstNameSort();
        customersListPage.clickOnFirstNameSort();
        List<String> sortedTableText = customersListPage.getTableText();
        List<String> expectedTableText = new ArrayList<>(sortedTableText);
        Collections.sort(expectedTableText);
        Assert.assertEquals("Проверяем, что отсортированный список совпадает с ожидаемым списком", sortedTableText, expectedTableText);
    }
}
