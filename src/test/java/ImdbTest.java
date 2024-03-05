import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.*;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.imdb.ImdbPage;
import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

@Epic("IMDb Testing")
@Feature("Browsing Functionality")
public class ImdbTest {

    @BeforeEach
    @Step("Open Google Search Page")
    public void setUp() {
        Configuration.browser = "chrome";
        openGoogleSearchPage();
    }

    @Step("Navigate to Google Search Page")
    public void openGoogleSearchPage() {
        open("https://www.google.com/");
        clickAcceptAllCookiesButton();
    }

    @Step("Click the 'Nõustu kõigiga' button")
    public void clickAcceptAllCookiesButton() {
        Selenide.element("#L2AGLb").click();
    }

    @Test
    @Step("Verify Celebrities Born Today on IMDb")
    public void verifyCelebritiesBornToday() {
        searchForIMDb();
        navigateToIMDb();
        acceptCookiesOnIMDb();
        openIMDbMenu();
        selectBornToday();
        printActorsBornToday();
    }

    @Step("Search for IMDb")
    public void searchForIMDb() {
        $(byName("q")).setValue("IMDb").pressEnter();
    }

    @Step("Navigate to IMDb from search results")
    public void navigateToIMDb() {
        $("h3").shouldHave(text("IMDb")).click();
    }

    @Step("Accept cookies on IMDb")
    public void acceptCookiesOnIMDb() {
        $("button[data-testid='accept-button']").shouldBe(visible).click();
    }

    @Step("Open IMDb Menu")
    public void openIMDbMenu() {
        $(".ipc-icon--menu").click();
    }

    @Step("Select 'Born Today' from the menu")
    public void selectBornToday() {
        $(By.xpath("//span[@class='ipc-list-item__text' and text()='Born Today']")).click();
    }

    @Step("Print Actors Born Today")
    public void printActorsBornToday() {
        ImdbPage imdbPage = new ImdbPage();
        imdbPage.printActorsBornToday();
    }
}
