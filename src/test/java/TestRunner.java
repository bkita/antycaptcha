import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import page.Exercise1;
import page.Exercise4;
import page.HomePage;

import static org.assertj.core.api.Assertions.*;

public class TestRunner {

    private static final String BASE_URL = "https://antycaptcha.amberteam.pl/";
    private static WebDriver driver;
    private static String seed;

    private static HomePage homePage;
    private static Exercise1 exercise1;
    private static Exercise4 exercise4;

    @BeforeAll
    public static void beforeAll() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        homePage = new HomePage(driver);
        seed = homePage.openUrl(BASE_URL)
                .getSeed();

        exercise1 = new Exercise1(driver);
        exercise4 = new Exercise4(driver);
    }

    @AfterAll
    public static void afterAll() {
        driver.quit();
    }

    @Test
    public void exerciseOne() {
        exercise1.openUrl(BASE_URL, seed)
                .waitForH1Title()
                .clickAllButtons();

        assertThat(exercise1.getResult()).isEqualTo(exercise1.getExpectedResult());
    }

    @Test
    public void exerciseFour() {
        exercise4.openUrl(BASE_URL, seed)
                .waitForH1Title()
                .selectAllRadioButtons();

        assertThat(exercise4.getResult()).isEqualTo(exercise4.getExpectedResult());
    }
}
