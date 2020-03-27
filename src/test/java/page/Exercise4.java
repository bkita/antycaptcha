package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.LinkedList;
import java.util.List;

public class Exercise4 extends BaseTest {

    private static final String EXERCISE_1 = "exercise4";

    @FindBy(className = "title")
    private WebElement h1Title;

    @FindBy(xpath = "//td[contains(text(), 'In the group')]/code")
    private List<WebElement> actions;

    @FindBy(xpath = "//td[contains(text(), 'Trail set to')]/code")
    private WebElement expectedResult;

    @FindBy(className = "wrap")
    private WebElement result;

    private String radio = "(((//div[./h5[text() = 'Group %s:']]//text()))[.='%s']/preceding-sibling::input)[last()]";

    public Exercise4(WebDriver driver) {
        super(driver);
    }

    public Exercise4 openUrl(final String BASE_URL, String seed) {
        driver.get(BASE_URL + EXERCISES + EXERCISE_1 + "?seed=" + seed);
        driver.manage().window().maximize();

        return this;
    }

    public Exercise4 waitForH1Title() {
        wait.until(ExpectedConditions.visibilityOf(h1Title));
        return this;
    }

    public Exercise4 selectAllRadioButtons() {
        List<String> radioNames = new LinkedList<>();
        actions.stream()
                .forEach(webElement -> radioNames.add(webElement.getText()));

        for (int i = 0; i < radioNames.size(); i++) {
            driver.findElement(By.xpath(String.format(radio, i, radioNames.get(i)))).click();
        }

        return this;
    }

    public String getExpectedResult() {
        return expectedResult.getText();
    }

    public String getResult() {
        return result.getText();
    }
}
