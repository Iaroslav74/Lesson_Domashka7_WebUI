
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BaseView {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "email")
    public WebElement emailField;

    @FindBy(id = "passwd")
    public WebElement passwordField;

    @FindBy(id = "SubmitLogin")
    public WebElement submitLoginButton;

    @Step("Логинимся на сайте")

    public MyAccountPage login(String email, String password) {
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        submitLoginButton.click();
        return new MyAccountPage(driver);
    }
}
