
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.hamcrest.MatcherAssert.assertThat;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.hasText;

public class SuccessBlock extends BaseView {
    public SuccessBlock(WebDriver driver) {
        super(driver);
    }

    private final static String SUCCESS_XPATH_LOCATOR = "//div[contains(@class,'layer_cart_product')]//h2";

    @FindBy(xpath = SUCCESS_XPATH_LOCATOR)
    public WebElement successHeader;

    @FindBy(xpath = "//span[@class='ajax_block_cart_total']")
    public WebElement summElement;

    @Step("Проверка корректности суммы товаров в корзине")

    public void checkCorrectSummInCart(String expectedSum) {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SUCCESS_XPATH_LOCATOR)));
        Assertions.assertAll(
                () -> Assertions.assertTrue(new SuccessBlock(driver).successHeader.isDisplayed()),
                () -> assertThat(new SuccessBlock(driver).summElement, hasText(expectedSum))
        );
    }
}
