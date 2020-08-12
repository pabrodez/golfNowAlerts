import java.util.HashMap;
import java.util.Map;

public class BuildVars {
    public static final Map<String, String> COURSES = new HashMap<>();
    public static final String DRIVER_URL = "C:/Users/Pablo/Downloads/geckodriver.exe"; // /home/pabrodez/Downloads/geckodriver
    public static final String DRIVER_NAME = "webdriver.gecko.driver";

    static {
        COURSES.put("chorlton", "12354-chorlton-cum-ha rdy-golf-club");
        COURSES.put("flixton", "15764-flixton-golf-club");
        COURSES.put("davyhulme", "13949-davyhulme-park-golf-club");
        COURSES.put("ellesmere", "11383-ellesmere-golf-club");
        COURSES.put("ashton-on-mersey", "10210-ashton-on-mersey-golf-club");
    }


}
