package PageElement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ApplicationTable {

    @FindBy(xpath = "//th[contains(normalize-space(), 'Заявитель')]/ancestor::table[1]")
    private WebElement applicationTable;

    public ApplicationTable(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public String getApplicationStatus(String applicationNumber) {

        final int STATUS_COLUMN_INDEX = 4;
        List<WebElement> rows = applicationTable.findElements(By.cssSelector("tr"));

        for (WebElement r : rows) {
            List<WebElement> rowElements = r.findElements(By.cssSelector("td"));
            // строка заголовка содержит только th — пропускаем её
            if (rowElements.isEmpty()) {
                continue;
            }
            String applicationNumberActual = rowElements.getFirst().getText();

            if (applicationNumberActual.equals(applicationNumber)) {
                return rowElements.get(STATUS_COLUMN_INDEX).getText();
            }
        }
        return "Заявка не найдена";
    }
}