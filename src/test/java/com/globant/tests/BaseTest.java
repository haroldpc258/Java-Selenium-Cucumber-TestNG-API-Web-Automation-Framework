package com.globant.tests;

import com.globant.pages.ArticlePage;
import com.globant.pages.EditPage;
import com.globant.pages.HistoryPage;
import com.globant.pages.HomePage;
import io.restassured.response.Response;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class BaseTest {

    protected WebDriver driver;

    protected HomePage homePage;
    protected ArticlePage articlePage;
    protected EditPage editPage;
    protected HistoryPage historyPage;
    protected String characterName;
    protected String filmName;
    protected String articleTitle;

    protected void navigateTo(String url) {
        driver.get(url);
    }

    protected void maximizeWindow() {
        driver.manage().window().maximize();
    }

    protected void setUpDriver(String browser) {
        switch (browser) {
            case "chrome" -> {
                driver = new ChromeDriver();
            }
            case "firefox" -> {
                driver = new FirefoxDriver();
            }
            case "edge" -> {
                driver = new EdgeDriver();
            }
        }
    }

    public HomePage loadFirstPage() {
        return new HomePage(driver);
    }

    protected String getARandomFilm() {
        Response response = given()
                .when()
                .get("https://swapi.dev/api/films/")
                .then()
                .statusCode(200)
                .extract().response();

        List<Map<String, String>> films =  response.jsonPath().getList("results");
        return films.get((int) (Math.random() * films.size())).get("title");
    }

    protected String getCharacterName(int characterPath) {

        Response response = given()
                .when()
                .get("https://swapi.dev/api/people/" + characterPath)
                .then()
                .statusCode(200)
                .extract().response();

        return response.jsonPath().getString("name");
    }
}
