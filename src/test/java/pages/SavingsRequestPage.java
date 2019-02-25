package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class SavingsRequestPage {

  @FindBy(id = "oneTimeInvestmentInput")
  private WebElement oneTimeInvestmentInput;

  @FindBy(id = "yearsInput")
  private WebElement yearsInput;

  @FindBy(id = "fundSelect")
  private WebElement fundSelect;

  @FindBy(css = "div.result")
  private WebElement resultWrapper;

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

  public String getActualTotalIncome() {
    return resultWrapper.findElement(By.cssSelector("p")).getText();
  }
}
