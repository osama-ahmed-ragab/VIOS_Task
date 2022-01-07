package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ResultPage {
    private WebDriver driver;
    private By nextPage = By.xpath("//a[@id='pnnext']");        //Next Page Button
    private By results = By.xpath("//div[@class='g']");         //Search Results id

    //constructor
    public ResultPage(WebDriver driver) {
        this.driver = driver;
    }

    public ResultPage nextPage() {
        driver.findElement(nextPage).click();       // Click on Next Page Button
        return new ResultPage(driver);
    }

    public int calcListSize() {
        List<WebElement> List = driver.findElements(results);       // Create List of Search Results
        return List.size();         // return number of search results
    }

}
