
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class NavigationBlock extends BaseView {
    public NavigationBlock(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[@title='Women']")
    private WebElement womenButton;

    @FindBy(xpath = "//ul[contains(@class,'submenu-container')]//a[@title='T-shirts']")
    private WebElement tshirtsInWomenSuggestButton;

    @Step("Клик на кнопку TShirts в выпадающем меню Женщины")

    public TShirtsPage clickTShirtsButtonInWomenSuggest() {
        actions.moveToElement(womenButton)
                .build().perform();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(tshirtsInWomenSuggestButton));
        tshirtsInWomenSuggestButton.click();
        return new TShirtsPage(driver);
    }
}
