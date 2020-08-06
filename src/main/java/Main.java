public class Main {
  public static void main(String[] args) {
    System.setProperty("webdriver.gecko.driver", "/home/pabrodez/Downloads/geckodriver");
    String url = GolfNowLoader.buildCourseDayUrl("flixton", "Sep+01+2020");
    String html = "";
    try {
      html = GolfNowLoader.getPageFromUrl(url);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.println(GolfNowParser.getHotDealsDateTime(html));
  }
}
