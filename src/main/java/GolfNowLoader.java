import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.edge.EdgeDriver;

import java.util.HashMap;
import java.util.Map;

public class GolfNowLoader {

  private static WebDriver driver;
  private static Map<String, String> courseId;
  private static final String BASE_URL = "https://www.golfnow.co.uk/tee-times/facility/";
  private static final String SEARCH_PARAM = "/search#date=";
  private static final String HOTDEAL_PARAM = "&hotdealsonly=true";

  static {
    driver = new EdgeDriver();
    courseId = new HashMap<>();
    courseId.put("chorlton", "12354-chorlton-cum-hardy-golf-club");
    courseId.put("flixton", "15764-flixton-golf-club");
    courseId.put("davyhulme", "13949-davyhulme-park-golf-club");
    courseId.put("ellesmere", "11383-ellesmere-golf-club");
    courseId.put("ashton-on-mersey", "10210-ashton-on-mersey-golf-club");
  }

  public static String getPageFromUrl(String url) {
    String htmlStr = "not parsed";
    try {
      driver.get("https://www.golfnow.co.uk/tee-times/facility/12354-chorlton-cum-hardy-golf-club/search#date=Sept+05+2020&hotdealsonly=true");
      WebElement acceptButton = driver.findElement(By.cssSelector("#onetrust-accept-btn-handler"));
      acceptButton.click();
      htmlStr = driver.getPageSource();
    } finally {
      driver.quit();
    }
    System.out.println(htmlStr);
    return htmlStr;
  }
}
