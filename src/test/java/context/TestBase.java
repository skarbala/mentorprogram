package context;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TestBase {
  protected WebDriver driver;

  @Before
  public void setUp() throws MalformedURLException {
    ChromeOptions chromeOptions = new ChromeOptions();
    driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub/"), chromeOptions);

    //    System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
    //    driver = new ChromeDriver();
  }

  @After
  public void tearDown() {
    driver.close();
    driver.quit();
  }


}
