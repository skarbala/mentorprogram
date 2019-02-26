package tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import context.TestBase;
import models.SavingRequest;
import pages.SavingsRequestPage;

public class SavingsCalculatorTest extends TestBase {
  private SavingsRequestPage savingsRequestPage;

  @Before
  public void openPage() {
    savingsRequestPage = new SavingsRequestPage(driver);
    savingsRequestPage.openPage();
  }

  @Test
  public void itShouldDisplayTitle() {
    String expectedTitle = "Savings Calculator";
    String actualTitle = driver.findElement(By.cssSelector("h1")).getText();

    Assert.assertEquals("wefkwefoiwefhwef", expectedTitle, actualTitle);
  }

  @Test
  public void itShouldCalculateTotalIncome() {
    savingsRequestPage.selectFund("Hoggwart's Fund");
    savingsRequestPage.inputInvestment("52000");
    savingsRequestPage.inputYears("10");

    String actualTotalIncome = savingsRequestPage.getActualTotalIncome();
    Assert.assertFalse(actualTotalIncome.equals(""));
    Assert.assertTrue(
        "Total income should contain currency",
        actualTotalIncome.contains("kr")
    );
  }

  @Test
  public void itShouldCalculateNetIncome() {
    savingsRequestPage.selectFund("Batman's Cave Development");
    savingsRequestPage.inputInvestment("25000");
    savingsRequestPage.inputYears("25");

    String actualInterestIncome = savingsRequestPage.getInterestIncome();
    Assert.assertFalse(actualInterestIncome.equals(""));
    Assert.assertTrue(actualInterestIncome.contains("kr"));
  }

  @Test
  public void itShouldEnableAddSavingButton() {
    savingsRequestPage.selectFund("Batman's Cave Development");
    savingsRequestPage.inputInvestment("25000");
    savingsRequestPage.inputYears("25");
    savingsRequestPage.inputEmail("martin.skarbala@itera.no");

    Assert.assertTrue(savingsRequestPage.getApplyForSavingButton().isEnabled());
  }

  @Test
  public void itShouldAddNewSavingRequestToTheRecentRequestList() {
    SavingRequest request = new SavingRequest(
        "Batman's Cave Development",
        "25000",
        25,
        "martin.skarbala@itera.no"
    );
    //arrange
    int initialNumberOfRequests = savingsRequestPage.getRecentRequestsList().size();
    savingsRequestPage.enterNewSavingRequestData(request);
    //act
    savingsRequestPage.getApplyForSavingButton().click();
    //assert
    int currentNumberOfRequests = savingsRequestPage.getRecentRequestsList().size();
    Assert.assertEquals(initialNumberOfRequests + 1, currentNumberOfRequests);
  }

  @Test
  public void itShouldStoreCorrectResultDataInNewSavingRequest() {
    SavingRequest request = new SavingRequest(
        "Batman's Cave Development",
        "25000",
        25,
        "martin.skarbala@itera.no"
    );
    savingsRequestPage.enterNewSavingRequestData(request);
    request.getSavingResult().setTotalIncome(savingsRequestPage.getActualTotalIncome());
    request.getSavingResult().setRisk(savingsRequestPage.getRiskLevel());

    savingsRequestPage.getApplyForSavingButton().click();

    savingsRequestPage.checkMostRecentSavingRequest(request);
  }
}
