package PageElement;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import WebDriver.WaitHelper;

import java.util.List;

public class ApplicationTable {

    private static final Logger log = LogManager.getLogger(ApplicationTable.class);

    @FindBy(xpath = "//th[contains(normalize-space(), 'Заявитель')]/ancestor::table[1]")
    private WebElement applicationTable;

    private final WebDriver driver;

    public ApplicationTable(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Поиск заявки в таблице и чтение её статуса")
    public String getApplicationStatus(String applicationNumber) {

        final int STATUS_COLUMN_INDEX = 4;
        WaitHelper.waitForVisible(driver, applicationTable);
        List<WebElement> rows = applicationTable.findElements(By.cssSelector("tr"));

        for (WebElement r : rows) {
            List<WebElement> rowElements = r.findElements(By.cssSelector("td"));
            if (rowElements.isEmpty()) {
                continue;
            }
            String applicationNumberActual = rowElements.getFirst().getText();

            if (applicationNumberActual.equals(applicationNumber)) {
                String status = rowElements.get(STATUS_COLUMN_INDEX).getText();
                log.info("Статус заявки № {} найден: {}", applicationNumber, status);
                return status;
            }
        }
        log.warn("Заявка № {} не найдена в таблице администрирования", applicationNumber);
        return "Заявка не найдена";
    }
}