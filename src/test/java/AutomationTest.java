
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.events.EventFiringDecorator;

import static org.hamcrest.MatcherAssert.assertThat;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.hasText;

@Epic("Интернет магазин одежды")
public class AutomationTest {
    WebDriver driver;
    TShirtsPage tShirtsPage;

    @BeforeAll
    static void registerDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void initDriver() {
        driver = new EventFiringDecorator(new CustomLogger()).decorate(new ChromeDriver());
        tShirtsPage = new TShirtsPage(driver);
    }

    @Test
    @Feature("Корзина")
    @Story("Добавление товара в корзину")
    @TmsLink("1234")
    void addToCartTest() throws InterruptedException {
        driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
        new LoginPage(driver)
                .login("spartalex93@test.test", "123456")
                .navigationBlock.clickTShirtsButtonInWomenSuggest()
                .selectSize("S")
                .addTShirtToCartByText("Faded")
                .checkCorrectSummInCart("$18.51");

        Assertions.assertAll(
                () -> Assertions.assertTrue(new SuccessBlock(driver).successHeader.isDisplayed()),
                () -> assertThat(new SuccessBlock(driver).summElement, hasText("$18.51"))
        );
    }

    @AfterEach
    void tearDown() {
        LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
        for (LogEntry logEntry : logEntries){
            Allure.addAttachment("Элемент лога браузера", logEntry.getMessage());
        }
        driver.quit();
    }
}
