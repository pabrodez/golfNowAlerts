import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.edge.EdgeDriver;


public class Main {
  public static void main(String[] args) {
    System.setProperty("webdriver.edge.driver", "C:/Users/Pablo/Downloads/msedgedriver.exe");
    GolfNowLoader.getPageFromUrl("");
  }
}
