import java.time.LocalDate;
import java.util.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        TimerTask task = new TimerTask() {
          @Override
          public void run() {
              Map<String, List<Map<String, String>>> collatedDeals = GolfNowParser.parseAllCoursesDaysDeals(LocalDate.now(), 30);
              System.out.println(
                      GolfNowParser.formatDayTimePriceList(collatedDeals)
              );
          }
        };

        Timer timer = new Timer("30DayAlert");
        timer.scheduleAtFixedRate(task, 0, 1000L * 60L * 60L * 24L);
    }
}
