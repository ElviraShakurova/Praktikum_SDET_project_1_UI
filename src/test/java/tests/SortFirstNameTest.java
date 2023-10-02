package tests;
import io.qameta.allure.junit4.DisplayName;
import jdk.jfr.Description;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import pages.BasePage;
import pages.ListOfCustomersPage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortFirstNameTest {
    @Rule
    public BaseTest driverRule = new BaseTest();
    @DisplayName("Тест сортировки клиента по имени")
    @Description("Тест проверяет сортировку клиента по имени в алфавитном порядке и порядке обратном алфавиту")
    @Test
    public void testSortFirstName(){
        BasePage basePage = new BasePage(driverRule.getDriver());
        ListOfCustomersPage listOfCustomersPage = basePage.clickOnCustomersButton();
        listOfCustomersPage.clickOnFirstNameSort();
        Assert.assertTrue(listOfCustomersPage.isTableOfCustomersSortedInReverseOrder());
        listOfCustomersPage.clickOnFirstNameSort();
        List<String> sortedList = listOfCustomersPage.getSortedTableOfCustomers();
        List<String> originalList = new ArrayList<>(sortedList);
        Collections.sort(originalList);
        Assert.assertEquals(originalList, sortedList);
    }
}
