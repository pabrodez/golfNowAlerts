import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {
  public static void main(String[] args) throws InterruptedException {
    // golfNow seems to make available Hotdeals of a given course
    // no earlier than 30 days in advance, so the Task is run daily
    // to search reduced green fees in 30 days from date of execution
    // Issue is that new tee times are added to days before then
      List<String> dealsList =  GolfNowLoader.getHtml30daysDealsCourse("chorlton", LocalDate.now().toString());
      for (String html : dealsList) {
          System.out.println(GolfNowParser.getHotDealsTimePrice(html).toString());
      }

//    LocalDate currentDatePlus30 = LocalDate.now().plusDays(30);
//    String formatDate = currentDatePlus30.format(DateTimeFormatter.ofPattern("MMM+d+uuuu"));
//    Map<String, List<String>> collatedTimePriceCourse = new HashMap<>();
//    GolfNowLoader.getCourseId().keySet().forEach(course -> {
//              String url = GolfNowLoader.buildCourseDayUrl(course, "Sep+05+2020");
//              String html = "";
//              try {
//                html = GolfNowLoader.getHtmlFromUrl(url);
//                Map<String, String> timePrice = GolfNowParser.getHotDealsTimePrice(html);
//                timePrice.forEach((time, price) -> {
//                  collatedTimePriceCourse.computeIfAbsent(time, k -> new ArrayList<>()).add(course + price);
//                });
//
//              } catch (InterruptedException e) {
//                e.printStackTrace();
//              } finally {
//
//              }
//            });
//    System.out.println(collatedTimePriceCourse.toString());
  }
}

    // TODO create separate alertClass extends TimerTask @overrides run
//    TimerTask task = new TimerTask() {
//      @Override
//      public void run() {
//        LocalDate currentDatePlus30 = LocalDate.now().plusDays(30);
//        String formatDate = currentDatePlus30.format(DateTimeFormatter.ofPattern("MMM+d+uuuu"));
//        Map<String, List<String>> collatedTimePriceCourse = new HashMap<>();
//        GolfNowLoader.getCourseId().keySet().forEach(course -> {
//          String url = GolfNowLoader.buildCourseDayUrl(course, formatDate);
//          String html = "";
//          try {
//            html = loader.getHtmlFromUrl(url);
//            Map<String, String> timePrice = GolfNowParser.getHotDealsTimePrice(html);
//            timePrice.forEach((time, price) -> {
//              collatedTimePriceCourse.computeIfAbsent(time, k -> new ArrayList<>()).add(course + price);
//            });
//
//          } catch (InterruptedException e) {
//            e.printStackTrace();
//          } finally {
//
//          }
//        });
//        // TODO use collatedTimePriceCourse
//      }
//    };
//
//    Timer timer = new Timer("30DayAlert");
//    timer.scheduleAtFixedRate(task, 0, 1000L * 60L * 60L * 24L);

//  driver.quit();

