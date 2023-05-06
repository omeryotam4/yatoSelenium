package com.example.demo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class YatoSeleniumScript {
    // These fields are defined for the entire class, since all methods need a driver and a driver wait.
    private WebDriver driver;
    private WebDriverWait wait;

    /**
     * The setup method, that runs before each of the methods are executed.
     * Opens a chromedriver for the driver field, sets a few properties for said driver,
     * and defines a wait for the wait field.
     */
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

    /**
     * A method that uses selenium to enter the poll voting page, catch the relevant elements,
     * send the name 'yato' to all input areas and press the submit button.
     * @param driver is the reference to the running driver
     * @param wait is the reference to the defined wait function
     */
    public void add3VotesForYato(WebDriver driver, WebDriverWait wait)
    {
        // Enter the voting page
        driver.get("https://www.menti.com/aletmybmwa6w");

        // Catch the relevant elements in the page
        WebElement option1Txt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(
                "input[data-testid='vote-input-0']")));
        WebElement option2Txt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(
                "body > div > div > div:nth-of-type(2) > div > form > div:nth-of-type(3) > div > input")));
        WebElement option3Txt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(
                "form[class] > div:nth-of-type(4) > div >input")));

        WebElement submitBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(
                "button[type='submit']")));

        // perform the desired actions on the caught elements
        option1Txt.sendKeys("yato");
        option2Txt.sendKeys("yato");
        option3Txt.sendKeys("yato");

        submitBtn.click();
    }

    /**
     * This method performs the previous method in a loop, making it possible to vote as many times as desired.
     */
    @Test
    public void add30VotesForYato()
    {
        // The loop performs the method 10 times, securing 30 votes for Yato (3 votes per call)
        for (int i=0; i<10; i++)
        {
            add3VotesForYato(driver, wait);
        }
    }

    /**
     * !This method was not shown in the presentation!
     * An extra method voting on a different voting form, for a possible 'finals' scene.
     * It's catching a select box, in which the name 'yato' is chosen and submitted.
     * @param driver is the reference to the running driver
     * @param wait is the reference to the defined wait function
     */
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

    /**
     * !This method was not shown in the presentation!
     * This method performs the previous method in a loop, making it possible to vote as many times as desired.
     * To enable multiple votes, the cookies for the driver are deleted.
     */
    @Test
    public void add10VotesForYatoInFinals()
    {
        for (int i=0; i<10; i++)
        {
            addVoteForYatoInFinals(driver, wait);
            driver.manage().deleteAllCookies();
        }
    }

    /**
     * The tear down method, that runs after every method finishes its execution.
     * Removes the driver after its job is done.
     */
    @AfterEach
    public void tearDown()
    {
        driver.close();
        driver.quit();
    }

}