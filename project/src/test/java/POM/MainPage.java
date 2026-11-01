package POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {

    @FindBy(xpath = "//button[text() = 'Войти как пользователь']")
    private WebElement buttonLoginAsUser;

    @FindBy(xpath = "//button[text() = 'Войти как администратор']")
    private WebElement buttonLoginAsAdmin;

    @FindBy(xpath = "//button[text() = 'Заказать справку']")
    private WebElement buttonOrderCertificate;

    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void clickButtonLoginAsUser() {
        buttonLoginAsUser.click();
    }

    public void clickButtonLoginAsAdmin() {
        buttonLoginAsAdmin.click();
    }

    public void clickButtonOrderCertificate() {
        buttonOrderCertificate.click();
    }
}
