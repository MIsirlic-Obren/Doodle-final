package doodle.com;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.Assert;
import model.CreatePollPage;
import model.Dashboard;
import model.LandingPage;
import model.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DoodleCom {

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        
        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        
        driver.get("https://doodle.com/en/");
        LandingPage landingPage = new LandingPage();

        WebElement loginField = driver.findElement(By.xpath(landingPage.loginBtn));
        loginField.click();

        LoginPage loginPage = new LoginPage();
        WebElement emailField = driver.findElement(By.name(loginPage.email));
       



        emailField.click();
        emailField.clear();
        emailField.sendKeys("misirlicobren@gmail.com");

        WebElement passwordField = driver.findElement(By.name(loginPage.password));
        passwordField.click();
        passwordField.sendKeys("123456789obren");

       
        WebElement loginButton = driver.findElement(By.cssSelector(loginPage.loginBtn));
        loginButton.click();

        Dashboard dashboard = new Dashboard();

       
        WebElement createPoll= driver.findElement(By.cssSelector(dashboard.createPoll));
        createPoll.click();

       
        WebElement meetingType= driver.findElement(By.cssSelector(dashboard.oneOnOne));
        meetingType.click();

        CreatePollPage createPollPage = new CreatePollPage();
        
        
        String meetingName = "MyMeeting";

        
        WebElement inputTitle= driver.findElement(By.id(createPollPage.title));
        inputTitle.sendKeys(meetingName);
        

        
        WebElement continueButton = driver.findElement(By.cssSelector(createPollPage.continueBtn));
        continueButton.click();


        
        WebElement month = driver.findElement(By.cssSelector("a[href='#month']"));
        month.click();

        WebElement btnDate = driver.findElement(By.cssSelector("td[data-day='29']"));
        btnDate.click();
        
       
        WebElement btnContinue = driver.findElement(By.cssSelector("#d-wizardOptionsNavigationView .d-nextButton"));
        btnContinue.click();
        
        

        WebElement btnFinish = driver.findElement(By.cssSelector("#d-wizardSettingsNavigationView .d-nextButton"));        
        btnFinish.click();

        waitForLoad(driver); 
       
      
        WebElement title = driver.findElement(By.cssSelector("#d-pollHeaderView .d-pollTitle"));
        Assert.assertTrue(title.getText().equalsIgnoreCase(meetingName));
        
        String actualURL = driver.getCurrentUrl();        
        System.out.println(actualURL);

        Assert.assertTrue(actualURL.contains("https://doodle.com/poll"));
        System.out.println("Test Passed");

    }
    
    static void waitForLoad(WebDriver driver) {
        new WebDriverWait(driver, 30).until((ExpectedCondition<Boolean>) wd ->
        ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }
}
