package tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import pages.SavingsRequestPage;

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
    String expectedTitle = "Savings Calculator";
    String actualTitle = driver.findElement(By.cssSelector("h1")).getText();

    Assert.assertEquals(expectedTitle, actualTitle);
  }

  @Test
  public void itShouldCalculateTotalIncome() {
    SavingsRequestPage savingsRequestPage = new SavingsRequestPage(driver);

    savingsRequestPage.selectFund("Hoggwart's Fund");
    savingsRequestPage.inputInvestment("52000");
    savingsRequestPage.inputYears("10");

    String actualTotalIncome = savingsRequestPage.getActualTotalIncome();
    Assert.assertFalse(actualTotalIncome.equals(""));
    Assert.assertTrue(actualTotalIncome.contains("kr"));
  }

  @Test
  public void itShouldCalculateNetIncome() {
    SavingsRequestPage savingsRequestPage = new SavingsRequestPage(driver);
    savingsRequestPage.selectFund("Batman's Cave Development");
    savingsRequestPage.inputInvestment("25000");
    savingsRequestPage.inputYears("25");

    String actualInterestIncome = savingsRequestPage.getInterestIncome();
    Assert.assertFalse(actualInterestIncome.equals(""));
    Assert.assertTrue(actualInterestIncome.contains("kr"));
  }

  @After
  public void tearDown() {
    driver.close();
    driver.quit();
  }
}
