package base;

import com.google.common.io.Files;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.*;
import pages.HomePage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

public class BaseTests {
    private WebDriver driver;
    protected static int steps = 1;
    protected WebDriverWait wait;
    protected HomePage homePage;
    protected JavascriptExecutor js;
    protected XSSFSheet mySheet;


    @BeforeClass
    /// browser values from TestNG.xml
    @Parameters("browser")
    public void setUp(@Optional("chrome") String browser) throws Exception {
        if (browser.equalsIgnoreCase("firefox")) {
            // Path of Firefox Driver
            System.setProperty("webdriver.gecko.driver", "reso/geckodriver.exe");
            //// Create FireFox Driver
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("chrome")) {
            //// Create Chrome Driver
            System.setProperty("webdriver.chrome.driver", "reso/chromedriver.exe");
            // Path of Chrome Driver
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            // Path of Edge Driver
            System.setProperty("webdriver.edge.driver", "reso/msedgedriver.exe");
            //// Create Edge Driver
            driver = new EdgeDriver();
        }

        // Path of Test Data File
        File myFile = new File(System.getProperty("user.dir") + "\\Test.xlsx");
        FileInputStream fis = new FileInputStream(myFile);
        // Finds the workbook instance for XLSX file
        XSSFWorkbook myWorkBook = new XSSFWorkbook(fis);
        // Return first sheet from the XLSX workbook
        mySheet = myWorkBook.getSheetAt(0);

        // get Main Url Test from Test.xlsx
        String url = mySheet.getRow(0).getCell(1).getStringCellValue();
        // open url in driver (browser)
        driver.get(url);
        homePage = new HomePage(driver);
        js = (JavascriptExecutor) driver;
        // maximize the browser window
        driver.manage().window().maximize();
    }

    /// Wait Function
    @BeforeMethod
    public void wait5() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        //driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);          Deprecated in Selenium 4
    }

    // ScreenShot Function
    @AfterMethod
    public void screenshot() {
        var camera = (TakesScreenshot) driver;
        // Take screenShot
        File screenshot = camera.getScreenshotAs(OutputType.FILE);
        // Create A File
        File ScreenshotName = new File("reports/screenshots/" + steps + ".png");
        try {
            //move the ScreenShot to the target file
            Files.move(screenshot, ScreenshotName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Relative Path for Image Tag
        String filePath = "screenshots/" + steps + ".png";
        //img Tag in HTML
        String path = "<div><img src=\"" + filePath + "\" alt=\"Faild\" width=\"400px\" height=\"200px\" ></div> ";
        // Adding Statement Before ScreenShot
        Reporter.log("step: " + steps);
        //Adding ScreenShot To HTML default report at Reporter output
        Reporter.log(path);

    }

    @AfterClass
    public void tearDown() {
        //close The Driver
        driver.quit();
    }
}
