package ResultsPage;

import base.BaseTests;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import pages.ResultPage;

import static org.testng.Assert.assertEquals;

public class ResultsPageTest extends BaseTests {

    @Test
    public void searchTest() {
        // get SearchText  from Test.xlsx
        String SearchText = mySheet.getRow(1).getCell(1).getStringCellValue();
        //Call Search function
        ResultPage result = homePage.setText(SearchText);
        // take screenShot for first step
        screenshot();
        //increment counter
        steps++;
        // wait until next button appear
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='pnnext']")));
        // Go to next result page function (second page of results)
        ResultPage secondPage = result.nextPage();
        screenshot();
        steps++;
        // wait until next button appear
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='pnnext']")));
        //scroll down for end of seconde page
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        //calculate results in second page
        int secondPageResults = secondPage.calcListSize();
        screenshot();
        steps++;
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='pnnext']")));
        // Go to next result page function (third page of results)
        ResultPage thirdPage = secondPage.nextPage();
        screenshot();
        steps++;
        //scroll down for end of third page
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        screenshot();
        steps++;
        //calculate results in third page
        int thirdPageResults = thirdPage.calcListSize();
        assertEquals(secondPageResults, thirdPageResults, "its not Equals");
    }
}
