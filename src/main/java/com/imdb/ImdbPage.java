package com.imdb;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

public class ImdbPage {

    public void openGoogle() {
        Selenide.open("https://www.google.com");
    }

    public void clickAcceptAllButton() {
        $(byId("L2AGLb")).click(); // Assuming you have imported static method byId from Selenide
    }
    public void searchFor(String query) {
        $(byName("q")).setValue(query).pressEnter();
    }

    public void clickOnIMDbLink() {
        // Click link with exact text "IMDb"
        $("h3").shouldHave(text("IMDb")).click();
    }
    public void clickAcceptButton() {
        $("button[data-testid='accept-button']").shouldBe(visible).click();
    }
    public void clickMenuButton() {
        $(".ipc-icon--menu").click(); // Assuming the SVG icon has an id of "home_img"
    }

    public void clickBornToday(WebDriver driver) {
        // Explicitly wait for the element to be clickable
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Wait up to 10 seconds

        WebElement bornTodayElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='ipc-list-item__text' and text()='Born Today']")));

        // Click on the element
        bornTodayElement.click();
    }

    public void printActorsBornToday() {
        // Adjust the XPath to target the <li> elements within the specified <ul>
        String actorsListXPath = "//*[@id='__next']/main/div[2]/div[3]/section/section/div/section/section/div[2]/div/section/div[2]/div[2]/ul/li";
        ElementsCollection actorNames = $$x(actorsListXPath);

        System.out.println("First 5 Actors Born Today:");

        // Ensure that we do not exceed the size of the list
        int count = Math.min(actorNames.size(), 5);

        for (int i = 0; i < count; i++) {
            System.out.println(actorNames.get(i).text()); // Print each actor's name
        }
    }
}

