import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GolfNowLoader {

  private WebDriver driver;
  private static final Map<String, String> courseId;
  private static final String BASE_URL = "https://www.golfnow.co.uk/tee-times/facility/";
  private static final String SEARCH_PARAM = "/search#date=";
  private static final String HOTDEAL_PARAM = "&hotdealsonly=true";

  static {
    courseId = new HashMap<>();
    courseId.put("chorlton", "12354-chorlton-cum-hardy-golf-club");
    courseId.put("flixton", "15764-flixton-golf-club");
    courseId.put("davyhulme", "13949-davyhulme-park-golf-club");
    courseId.put("ellesmere", "11383-ellesmere-golf-club");
    courseId.put("ashton-on-mersey", "10210-ashton-on-mersey-golf-club");
  }

  public String getPageFromUrl(String url) throws InterruptedException {
    String htmlStr;
    driver.get(url);
    List<WebElement> buttonToClick = driver.findElements(By.cssSelector("#onetrust-accept-btn-handler"));
    if (!buttonToClick.isEmpty()) {
      buttonToClick.get(0).click();
    }
    Thread.sleep(7000); // sleep while page loads
    htmlStr = driver.getPageSource();

    return htmlStr;
  }

  public static String buildCourseDayUrl(String course, String date) {
    // assumes date is format mm+dd+yyyy
    return BASE_URL + courseId.get(course) + SEARCH_PARAM + date + HOTDEAL_PARAM;
  }

  public void setWebDriver(WebDriver driver) {
    this.driver = driver;
  }

  public static Map<String, String> getCourseId() {
    return courseId;
  }

}
