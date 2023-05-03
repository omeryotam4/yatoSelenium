package com.example.demo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class YatoSeleniumScript {
    private WebDriver driver;
    private WebDriverWait wait;


    @BeforeEach
    public void setUp()
    {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        wait = new WebDriverWait(driver, 90);
    }

    public void add3VotesForYato(WebDriver driver, WebDriverWait wait)
    {
        driver.get("https://www.menti.com/aletmybmwa6w");

        WebElement option1Txt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(
                "input[data-testid='vote-input-0']")));
        WebElement option2Txt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(
                "body > div > div > div:nth-of-type(2) > div > form > div:nth-of-type(3) > div > input")));
        WebElement option3Txt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(
                "form[class] > div:nth-of-type(4) > div >input")));

        WebElement submitBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(
                "button[type='submit']")));

        option1Txt.sendKeys("yato");
        option2Txt.sendKeys("yato");
        option3Txt.sendKeys("yato");

        submitBtn.click();
    }

    @Test
    public void add30VotesForYato()
    {
        for (int i=0; i<10; i++)
        {
            add3VotesForYato(driver, wait);
        }
    }

    public void addVoteForYatoInFinals(WebDriver driver, WebDriverWait wait)
    {
        driver.get("https://www.menti.com/aletmybmwa6w");

        Select selectBox = new Select(
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu-0"))));

        WebElement submitBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(
                "button[type='submit']")));

        selectBox.selectByValue("312844912");
        submitBtn.click();
    }

    @Test
    public void add10VotesForYatoInFinals()
    {
        for (int i=0; i<10; i++)
        {
            addVoteForYatoInFinals(driver, wait);
            driver.manage().deleteAllCookies();
        }
    }

    @Test
    public void basicTestExample()
    {

        driver.get("https://www.selenium.dev/selenium/web/web-form.html");

        String title = driver.getTitle();
        assertEquals("Web form", title);

        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);

        WebElement textBox = driver.findElement(By.name("my-text"));
        WebElement submitButton = driver.findElement(By.cssSelector("button"));

        textBox.sendKeys("Selenium");
        submitButton.click();

        WebElement message = driver.findElement(By.id("message"));
        String value = message.getText();
        assertEquals("Received!", value);
    }

    @AfterEach
    public void tearDown()
    {
        driver.close();
        driver.quit();
    }

}

// extra code for reference
/*
System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver");
ChromeDriver chromeDriver = new ChromeDriver(createChromeOptions());

chromeDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
chromeDriver.manage().timeouts().scriptTimeout(Duration.ofSeconds(60));
chromeDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));

chromeDriver.manage().window().maximize();


@Test
WebDriver driver = this.basePage.driver;
WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(90));




driver.close();
driver.quit();
 */