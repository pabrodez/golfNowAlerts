import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class GolfNowLoader {

  private static WebDriver driver;
  private static final String BASE_URL = "https://www.golfnow.co.uk/tee-times/facility/";
  private static final String SEARCH_PARAM = "/search#date=";
  private static final String HOTDEAL_PARAM = "&hotdealsonly=true";

  static {
    driver = new FirefoxDriver();

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

  public static Map<String, String> getHtmlDaysDealsCourse(String course, LocalDate fromDate, int daysOut) throws InterruptedException {
    Map<String, String> dayHtml = new LinkedHashMap<>();
    String dayUrl = buildCourseDayUrl(course, fromDate.format(DateTimeFormatter.ofPattern("MMM+d+uuuu")));
    driver.get(dayUrl);
    List<WebElement> buttonToClick = driver.findElements(By.id("onetrust-accept-btn-handler"));
    if (!buttonToClick.isEmpty()) {
      buttonToClick.get(0).click();
    }
    Thread.sleep(7000);
    for (int i = 0; i <= daysOut; i++) {
      Thread.sleep(2000);
      dayHtml.put(fromDate.plusDays(i).toString(), driver.getPageSource());
      WebElement nextDayButton = driver.findElement(By.cssSelector("#nextDay div"));
      nextDayButton.click();
    }
    return dayHtml;
  }

  public static String buildCourseDayUrl(String course, String date) {
    // assumes date is format Sep+08+2020
    return BASE_URL + BuildVars.COURSES.get(course) + SEARCH_PARAM + date + HOTDEAL_PARAM;
  }

  public static Map<String, String> getCourseId() {
    return BuildVars.COURSES;
  }

}
