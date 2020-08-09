import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GolfNowLoader {

  private static WebDriver driver;
  private static final Map<String, String> courseId;
  private static final String BASE_URL = "https://www.golfnow.co.uk/tee-times/facility/";
  private static final String SEARCH_PARAM = "/search#date=";
  private static final String HOTDEAL_PARAM = "&hotdealsonly=true";

  static {
    courseId = new HashMap<>();
    // System.setProperty("webdriver.gecko.driver", "C:/Users/Pablo/Downloads/geckodriver.exe");
    System.setProperty("webdriver.gecko.driver", "/home/pabrodez/Downloads/geckodriver");
    FirefoxDriver ffdriver = new FirefoxDriver();
    driver = ffdriver;
    courseId.put("chorlton", "12354-chorlton-cum-hardy-golf-club");
    courseId.put("flixton", "15764-flixton-golf-club");
    courseId.put("davyhulme", "13949-davyhulme-park-golf-club");
    courseId.put("ellesmere", "11383-ellesmere-golf-club");
    courseId.put("ashton-on-mersey", "10210-ashton-on-mersey-golf-club");
  }

  public static String getHtmlFromUrl(String url) throws InterruptedException {
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

  public static List<String> getHtml30daysDealsCourse(String course, String fromDate) throws InterruptedException {
    LocalDate startDate = LocalDate.parse(fromDate);
    List<String> dayDealsHtmlList = new ArrayList<>();
    String nextDay = startDate.format(DateTimeFormatter.ofPattern("MMM+d+uuuu"));
    String dayUrl = buildCourseDayUrl(course, nextDay);
    driver.get(dayUrl);
    List<WebElement> buttonToClick = driver.findElements(By.id("onetrust-accept-btn-handler"));
    if (!buttonToClick.isEmpty()) {
      buttonToClick.get(0).click();
    }
    Thread.sleep(7000);
    for (int i = 0; i <= 30; i++) {
      Thread.sleep(2000);
      dayDealsHtmlList.add(driver.getPageSource());
      WebElement nextDayButton = driver.findElement(By.cssSelector("#nextDay div"));
      nextDayButton.click();
    }
    return dayDealsHtmlList;
  }

  public static String buildCourseDayUrl(String course, String date) {
    // assumes date is format Sep+08+2020
    return BASE_URL + courseId.get(course) + SEARCH_PARAM + date + HOTDEAL_PARAM;
  }

  public static Map<String, String> getCourseId() {
    return courseId;
  }

}
