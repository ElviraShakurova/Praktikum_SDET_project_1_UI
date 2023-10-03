package tests;
import helpers.EnvHelper;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Rule;
import org.junit.Test;
import pages.BasePage;
import pages.RegistrationPage;

public class RegistrationTest {
    @Rule
    public BaseTest driverRule = new BaseTest();

    @DisplayName("Тест проверки создания клиента")
    @Description("Тест проверяет создание клиента с валидными данными")
    @Test
    public void validRegistrationTest(){
        BasePage basePage = new BasePage(driverRule.getDriver());
        RegistrationPage registrationPage = basePage.clickAddCustomerButton()
                .setInputFirstName(EnvHelper.getFirstName())
                .setInputLastName(EnvHelper.getLastName())
                .setInputPostCode(EnvHelper.getPostCode())
                .clickOnAddCustomerButton()
                .successAlert();
    }
}
