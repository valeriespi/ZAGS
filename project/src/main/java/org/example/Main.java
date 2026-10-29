package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Main {
    public static void main(String[] args) {

        final String BASE_URL = "https://user:senlatest@regoffice.senla.eu";
        WebDriver driver = null;
        WebDriverWait wait = null;

        try {
            driver = new ChromeDriver();
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            driver.manage().window().maximize();
            driver.get(BASE_URL);

            // Вход как пользователь
            WebElement buttonLoginAsUser = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text() = 'Войти как пользователь']")));
            buttonLoginAsUser.click();

            // Форма "Данные заявителя"
            WebElement fieldLastName = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[placeholder = 'Введите фамилию (минимум 2 символа)']")));
            fieldLastName.sendKeys("Иванов");

            WebElement fieldFirstName = driver.findElement(By.cssSelector("input[placeholder = 'Введите имя (минимум 2 символа)']"));
            fieldFirstName.sendKeys("Иван");

            WebElement fieldPatronymic = driver.findElement(By.cssSelector("input[placeholder = 'Введите отчество (минимум 5 символов)']"));
            fieldPatronymic.sendKeys("Иванович");

            WebElement fieldNumber = driver.findElement(By.cssSelector("input[placeholder = 'Введите номер телефона (не более 11 символов)']"));
            fieldNumber.sendKeys("12345678910");

            WebElement fieldPassport = driver.findElement(By.cssSelector("input[placeholder = 'Введите номер паспорта (не более 8 символов)']"));
            fieldPassport.sendKeys("GD345678");

            WebElement fieldAddress = driver.findElement(By.cssSelector("input[placeholder = 'Введите адрес прописки']"));
            fieldAddress.sendKeys("Гродно");

            WebElement buttonNext = driver.findElement(By.xpath("//button[contains(text(), 'Далее')]"));
            buttonNext.click();

            // Выбор услуги
            WebElement buttonChoiceOfService = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(., 'Регистрация брака')]")));
            buttonChoiceOfService.click();

            // Форма "Данные гражданина"
            WebElement fieldLastName2 = wait.until(ExpectedConditions.elementToBeClickable(By.id("TextInputField-7")));
            fieldLastName2.sendKeys("Иванов");

            WebElement fieldFirstName2 = driver.findElement(By.id("TextInputField-8"));
            fieldFirstName2.sendKeys("Иван");

            WebElement fieldPatronymic2 = driver.findElement(By.id("TextInputField-9"));
            fieldPatronymic2.sendKeys("Иванович");

            WebElement fieldDateOfBirth = driver.findElement(By.id("TextInputField-10"));
            fieldDateOfBirth.sendKeys("01012000");

            WebElement fieldPassport2 = driver.findElement(By.id("TextInputField-11"));
            fieldPassport2.sendKeys("GD345678");

            WebElement fieldGender = driver.findElement(By.id("TextInputField-12"));
            fieldGender.sendKeys("Мужс");

            WebElement fieldAddress2 = driver.findElement(By.id("TextInputField-13"));
            fieldAddress2.sendKeys("Гродно");

            WebElement buttonNext2 = driver.findElement(By.xpath("//button[text() = 'Далее']"));
            buttonNext2.click();

            // Форма "Данные услуги"
            WebElement fieldDateOfRegistration = wait.until(ExpectedConditions.elementToBeClickable(By.id("TextInputField-14")));
            fieldDateOfRegistration.sendKeys("01012000");

            WebElement fieldNewLastName = driver.findElement(By.id("TextInputField-15"));
            fieldNewLastName.sendKeys("Иванов");

            WebElement fieldSpousesLastName = driver.findElement(By.id("TextInputField-16"));
            fieldSpousesLastName.sendKeys("Ивановa");

            WebElement fieldSpousesFirstName = driver.findElement(By.id("TextInputField-17"));
            fieldSpousesFirstName.sendKeys("Катя");

            WebElement fieldSpousesPatronymic = driver.findElement(By.id("TextInputField-18"));
            fieldSpousesPatronymic.sendKeys("Ивановна");

            WebElement fieldSpousesDateOfBirth = driver.findElement(By.id("TextInputField-19"));
            fieldSpousesDateOfBirth.sendKeys("01011950");

            WebElement fieldSpousesPassport = driver.findElement(By.id("TextInputField-20"));
            fieldSpousesPassport.sendKeys("GD345678");

            WebElement buttonFinish = driver.findElement(By.xpath("//button[text() = 'Завершить']"));
            buttonFinish.click();

            // Проверка результата
            WebElement message1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text() = 'Спасибо за обращение!']")));
            WebElement message2 = driver.findElement(By.xpath("//span[contains(text(), 'Ваша заявка №') and contains(text(), 'отправлена на рассмотрение')]"));
            WebElement message3 = driver.findElement(By.xpath("//span[contains(text(), 'Дата регистрации заявки')]"));
            WebElement message4 = driver.findElement(By.xpath("//span[contains(text(), 'Статус заявки:')]"));

            if (!message1.getText().isEmpty() && !message2.getText().isEmpty() && !message3.getText().isEmpty() && !message4.getText().isEmpty()) {
                System.out.println("Тест прошел успешно!");
            } else System.out.println("Тест не прошел");

        } catch (Exception e) {
            System.out.println("Произошла ошибка во время выполнения теста");
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if (driver != null) driver.quit();
        }
    }
}