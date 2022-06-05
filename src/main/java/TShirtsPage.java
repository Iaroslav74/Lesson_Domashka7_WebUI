
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class TShirtsPage extends BaseView {

    public TShirtsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//ul[@id='ul_layered_id_attribute_group_1']//a")
    private List<WebElement> sizesList;

    @Step("Выбор размера")

    public TShirtsPage selectSize(String size) {
        sizesList.stream().filter(s -> s.getText().contains(size)).findFirst().get().click();
        return this;
    }

    @FindBy(xpath = "//ul[@class='product_list grid row']/li")
    private List<WebElement> tShirtsList;

    @FindBy(xpath = "//a[@title='Add to cart']")
    private WebElement addToCartButton;

    @Step("Добавить рубашку в корзину")

    public SuccessBlock addTShirtToCartByText(String productName) {
        actions.moveToElement(tShirtsList.stream().filter(t -> t.getText().contains(productName)).findFirst().get())
                .build().perform();
        addToCartButton.click();
        return new SuccessBlock(driver);
    }
}
