package tests;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pages.BasePage;
import pages.RegistrationPage;
import java.util.Arrays;
import java.util.Collection;


@RunWith(Parameterized.class)
public class RegistrationTest {
    @Rule
    public BaseTest driverRule = new BaseTest();
    private final String firstName;
    private final String lastName;
    private final String postCode;
    public RegistrationTest(String firstName, String lastName,String postCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.postCode = postCode;
    }
    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][]{
                {"Draco", "Malfoy", "E10020"},
                {"Ginny", "Weisley", "E12345"},
                {"Polumna", "Lovgud", "E455667"}
        });
    }
    @DisplayName("Тест проверки создания клиента")
    @Description("Тест проверяет создание клиента с валидными данными")
    @Test
    public void validRegistrationTest(){
        BasePage basePage = new BasePage(driverRule.getDriver());
        RegistrationPage registrationPage = basePage.clickAddCustomerButton()
                .setInputFirstName(firstName)
                .setInputLastName(lastName)
                .setInputPostCode(postCode)
                .clickOnAddCustomerButton()
                .successAlert();
    }
}
