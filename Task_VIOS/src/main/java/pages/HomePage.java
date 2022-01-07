package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private final WebDriver driver;
    private final By inputField = By.xpath("//*[@name = 'q']");               //Google Search input Field
    private final By searchButton = By.xpath("//input[@name = 'btnK']");      //Google Search Button

    //constructor
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public ResultPage setText(String SearchText) {
        driver.findElement(inputField).sendKeys(SearchText + Keys.ENTER);       //Type Vodafone in search input and Press Enter Key to search
        // driver.findElement(searchButton).click();
        return new ResultPage(driver);
    }
}
