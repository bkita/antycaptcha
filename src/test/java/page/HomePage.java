package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BaseTest {

    @FindBy(xpath = "//code/em")
    private WebElement seed;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage openUrl(final String BASE_URL) {
        driver.get(BASE_URL);
        driver.manage().window().maximize();

        return this;
    }

    public String getSeed() {
        return seed.getText();
    }
}
