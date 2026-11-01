package POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ApplicationStatusPage {

    @FindBy(xpath = "//span[text() = 'Спасибо за обращение!']")
    private WebElement textThanks;

    @FindBy(xpath = "//span[contains(text(), 'Ваша заявка №')]")
    private WebElement textApplicationNumber;

    @FindBy(xpath = "//span[contains(text(), 'Дата регистрации заявки')]")
    private WebElement textRegistrationDate;

    @FindBy(xpath = "//span[contains(text(), 'Статус заявки:')]")
    private WebElement textApplicationStatus;

    @FindBy(xpath = "//button[text() = 'Обновить']")
    private WebElement buttonRefresh;

    @FindBy(xpath = "//button[text() = 'Создать новую заявку']")
    private WebElement buttonCreateNewApplication;

    public ApplicationStatusPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void clickButtonRefresh() {
        buttonRefresh.click();
    }

    public void clickButtonCreateNewApplication() {
        buttonCreateNewApplication.click();
    }

    public String getThanksText() {
        return textThanks.getText();
    }

    public String getApplicationNumberText() {
        return textApplicationNumber.getText();
    }

    public String getApplicationNumber() {
        String fullText = textApplicationNumber.getText();
        return fullText.substring(fullText.indexOf("№") + 1, fullText.indexOf("отправлена")).trim();
    }

    public String getRegistrationDateText() {
        return textRegistrationDate.getText();
    }

    public String getRegistrationDate() {
        String fullText = textRegistrationDate.getText();
        return fullText.substring(fullText.indexOf("заявки") + "заявки".length()).trim();
    }

    public String getApplicationStatusText() {
        return textApplicationStatus.getText();
    }

    public String getStatus() {
        String fullText = textApplicationStatus.getText();
        return fullText.substring(fullText.indexOf(":") + 1).trim();
    }

    public boolean isThanksTextDisplayed() {
        return textThanks.isDisplayed();
    }

    public boolean isApplicationNumberTextDisplayed() {
        return textApplicationNumber.isDisplayed();
    }

    public boolean isRegistrationDateTextDisplayed() {
        return textRegistrationDate.isDisplayed();
    }

    public boolean isStatusTextDisplayed() {
        return textApplicationStatus.isDisplayed();
    }
}
