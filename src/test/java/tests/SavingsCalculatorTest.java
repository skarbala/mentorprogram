package tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class SavingsCalculatorTest {
  private WebDriver driver;

  @Before
  public void setUp() {
    System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
    driver = new ChromeDriver();
    driver.get("http://localhost:8888/savingscalculator.php");
  }

  @Test
  public void itShouldDisplayTitle() {
    String expectedTitle = "Savings calculator";
    String actualTitle = driver.findElement(By.cssSelector("h1")).getText();

    Assert.assertEquals(expectedTitle, actualTitle);
  }

  @Test
  public void itShouldCalculateTotalIncome() {
    selectFund("Hoggwart's Fund");
    inputInvestment("52000");
    inputYears("10");
    String actualTotalIncome = driver.findElement(By.cssSelector("div.result p")).getText();
    Assert.assertFalse(actualTotalIncome.equals(""));
    Assert.assertTrue(actualTotalIncome.contains("kr"));
  }

  private void inputYears(String years) {
    driver.findElement(By.id("yearsInput")).sendKeys(years);
    driver.findElement(By.id("yearsInput")).sendKeys(Keys.TAB);
  }
  private void selectFund(String fundName) {
    new Select(driver.findElement(By.id("fundSelect"))).selectByVisibleText(fundName);
  }

  private void inputInvestment(String oneTimeInvestment) {
    driver.findElement(By.id("oneTimeInvestmentInput")).sendKeys(oneTimeInvestment);
    driver.findElement(By.id("oneTimeInvestmentInput")).sendKeys(Keys.TAB);
  }

  @Test
  public void itShouldCalculateNetIncome() {
    //select fund
    selectFund("Batman's Cave Development");
    //input investment
    inputInvestment("25000");
    //input years
    inputYears("25");

    String actualInterestIncome =
        driver.findElement(By.xpath("//div[contains(@class,'result')]/div[2]/p")).getText();
    Assert.assertFalse(actualInterestIncome.equals(""));
    Assert.assertTrue(actualInterestIncome.contains("kr"));
  }

  @After
  public void tearDown() {
    driver.close();
    driver.quit();
  }
}
