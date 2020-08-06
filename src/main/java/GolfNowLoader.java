import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class GolfNowLoader {

  private static final WebDriver driver;
  private static final Map<String, String> courseId;
  private static final String BASE_URL = "https://www.golfnow.co.uk/tee-times/facility/";
  private static final String SEARCH_PARAM = "/search#date=";
  private static final String HOTDEAL_PARAM = "&hotdealsonly=true";

  static {
    driver = new FirefoxDriver();
    courseId = new HashMap<>();
    courseId.put("chorlton", "12354-chorlton-cum-hardy-golf-club");
    courseId.put("flixton", "15764-flixton-golf-club");
    courseId.put("davyhulme", "13949-davyhulme-park-golf-club");
    courseId.put("ellesmere", "11383-ellesmere-golf-club");
    courseId.put("ashton-on-mersey", "10210-ashton-on-mersey-golf-club");
  }

  public static String getPageFromUrl(String url) throws InterruptedException{
    String htmlStr;
    try {
      driver.get(url);
      driver.findElement(By.cssSelector("#onetrust-accept-btn-handler")).click();
      Thread.sleep(5000); // sleep while page loads
      htmlStr = driver.getPageSource();
    } finally {
      driver.quit();
    }
    return htmlStr;
  }

  public static String buildCourseDayUrl(String course, String date) {
    // assumes date is format mm+dd+yyyy
    return BASE_URL + courseId.get(course) + SEARCH_PARAM + date + HOTDEAL_PARAM;
  }

}
