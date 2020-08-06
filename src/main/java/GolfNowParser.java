import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashMap;
import java.util.Map;

public class GolfNowParser {

    public static Map<String, String> getHotDealsTimePrice(String html) {
        Document doc = Jsoup.parse(html);
        Map<String, String> timePrice = new HashMap<>();
        Elements hotDealContainers = doc.select(".hot-deal-zone-tile");
        for (Element element : hotDealContainers) {
            timePrice.put(
                    element.selectFirst("time").text(),
                    element.selectFirst("p").text()
            );
        }

        return timePrice;
    }


}
