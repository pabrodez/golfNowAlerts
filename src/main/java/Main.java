public class Main {
  public static void main(String[] args) {
    System.setProperty("webdriver.gecko.driver", "/home/pabrodez/Downloads/geckodriver");
//    String url = GolfNowLoader.buildCourseDayUrl("chorlton", "Sep+05+2020");
    String html = GolfNowLoader.getPageFromUrl("https://www.golfnow.co.uk/tee-times/facility/12354-chorlton-cum-hardy-golf-club/search#hotdealsonly=true&date=Sep+05+2020&sortby=Date&view=Grouping&holes=3&timemax=42&timemin=10&players=0&pricemax=10000&pricemin=0");
    System.out.println(GolfNowParser.getHotDealsDateTime(html));
  }
}
