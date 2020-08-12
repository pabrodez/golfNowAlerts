import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.*;

public class GolfNowParser {

    public static Map<String, String> getHotDealsTimePrice(String html, String suffix) {
        Document doc = Jsoup.parse(html);
        Map<String, String> timePrice = new HashMap<>();
        Elements hotDealContainers = doc.select(".hot-deal-zone-tile");
        for (Element element : hotDealContainers) {
            timePrice.put(
                    element.selectFirst("time").text(),
                    element.selectFirst("p").text() + suffix
            );
        }

        return timePrice;
    }

    public static Map<String, List<Map<String, String>>> parseAllCourses30dayDeals(String startDate) {
        Map<String, List<Map<String, String>>> dayTimePrice = new LinkedHashMap<>();
        GolfNowLoader.getCourseId().keySet().forEach(course -> {
            try {
                Map<String, String> dayHtml = GolfNowLoader.getHtml30daysDealsCourse(course, startDate);
                dayHtml.forEach((key, value) -> {
                    Map<String, String> timePrice = getHotDealsTimePrice(value, course);
                    dayTimePrice.computeIfAbsent(key, f -> new ArrayList<>()).add(timePrice);
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        return dayTimePrice;

    }

    public static String formatDayTimePriceList(Map<String, List<Map<String, String>>> parsed30dayDealsCourses) {
        StringBuilder strOut = new StringBuilder();
        parsed30dayDealsCourses.forEach((key, value) -> {
            strOut.append(key);
            strOut.append(value + "\n");
        });

        return strOut.toString();
    }

}
