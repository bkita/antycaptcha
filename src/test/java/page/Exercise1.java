package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class Exercise1 extends BaseTest {

    private static final String EXERCISE_1 = "exercise1";

    public Exercise1(WebDriver driver) {
        super(driver);
    }

    @FindBy(className = "title")
    private WebElement h1Title;

    @FindBy(xpath = "//td[contains(text(), 'Press button ')]/code")
    private List<WebElement> actions;

    @FindBy(xpath = "//td[contains(text(), 'Trail set to')]/code")
    private WebElement expectedResult;

    @FindBy(className = "wrap")
    private WebElement result;

    private String button = "//button[contains(text(),'%s')]";

    public Exercise1 openUrl(final String BASE_URL, String seed) {
        driver.get(BASE_URL + EXERCISES + EXERCISE_1 + "?seed=" + seed);
        driver.manage().window().maximize();

        return this;
    }

    public Exercise1 clickButton(String buttonName) {
        driver.findElement(By.xpath(String.format(button, buttonName))).click();
        return this;
    }

    public Exercise1 clickAllButtons() {
        actions.stream()
                .forEach(webElement -> {
                    clickButton(webElement.getText());
                    sleep(500);
                });
        return this;
    }

    public Exercise1 waitForH1Title() {
        wait.until(ExpectedConditions.visibilityOf(h1Title));
        return this;
    }

    public String getExpectedResult() {
        return expectedResult.getText();
    }

    public String getResult() {
        return result.getText();
    }
}
