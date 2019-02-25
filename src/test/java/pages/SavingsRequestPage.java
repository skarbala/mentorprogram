package pages;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import enumerators.RiskLevel;
import models.SavingRequest;

public class SavingsRequestPage {

  @FindBy(id = "oneTimeInvestmentInput")
  private WebElement oneTimeInvestmentInput;

  @FindBy(id = "yearsInput")
  private WebElement yearsInput;

  @FindBy(id = "fundSelect")
  private WebElement fundSelect;

  @FindBy(css = "div.result")
  private WebElement resultWrapper;

  @FindBy(id = "emailInput")
  private WebElement emailInput;

  @FindBy(css = "button.btn")
  private WebElement applyForSavingButton;

  private WebDriver driver;

  public SavingsRequestPage(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
  }

  public void inputInvestment(String oneTimeInvestment) {
    oneTimeInvestmentInput.sendKeys(oneTimeInvestment);
    oneTimeInvestmentInput.sendKeys(Keys.TAB);
  }

  public void inputYears(String years) {
    yearsInput.sendKeys(years);
    yearsInput.sendKeys(Keys.TAB);
  }

  public void selectFund(String fundName) {
    new Select(fundSelect).selectByVisibleText(fundName);
  }

  public String getInterestIncome() {
    return resultWrapper.findElement(By.xpath("./div[2]/p")).getText();
  }

  public RiskLevel getRiskLevel() {
    String displayedRisk = resultWrapper.findElement(By.xpath("./div[3]/p")).getText();
    RiskLevel riskLevel = RiskLevel.valueOf(displayedRisk.toUpperCase());
    return riskLevel;
  }

  public String getActualTotalIncome() {
    return resultWrapper.findElement(By.cssSelector("p")).getText();
  }

  public void inputEmail(String email) {
    emailInput.sendKeys(email);
    emailInput.sendKeys(Keys.TAB);
  }

  public WebElement getApplyForSavingButton() {
    return applyForSavingButton;
  }

  public List<WebElement> getRecentRequestsList() {
    return driver.findElements(By.cssSelector("ul.saving-list li"));
  }

  public void enterNewSavingRequestData(SavingRequest request) {
    selectFund(request.getFund());
    inputInvestment(request.getOneTimeInvestment());
    inputYears(String.valueOf(request.getYears()));
    inputEmail(request.getEmail());
  }

  public void checkMostRecentSavingRequest(SavingRequest request) {
    //getting the recent saving request element
    WebElement mostRecentSavingRequest = getRecentRequestsList().get(0);

    //getting the actual text from the element
    String actualRequestAmountText = mostRecentSavingRequest
        .findElement(By.cssSelector("div.amounts"))
        .getText();
    String actualRisk = mostRecentSavingRequest.findElement(By.cssSelector("p.risk")).getText();
    String actualFundDescription = mostRecentSavingRequest
        .findElement(By.cssSelector("p.fund-description"))
        .getText();

    //comparing actual text with SavingRequest data
    Assert.assertEquals(request.getSavingResult().getRisk().getUiValue(), actualRisk);
    Assert.assertEquals(request.getFund(), actualFundDescription);
    Assert.assertTrue(actualRequestAmountText.contains(request.getSavingResult().getTotalIncome()));

  }
}
