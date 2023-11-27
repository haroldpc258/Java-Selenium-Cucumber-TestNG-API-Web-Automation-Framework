package com.globant.tests;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class StepDefinitions extends BaseTest {


    @Given("I am an user at the Wikipedia WebPage")
    public void i_am_at_the_Wikipedia_WebPage() {
        navigateTo("https://es.wikipedia.org/wiki/Wikipedia:Portada");
        homePage = loadFirstPage();
    }

    @When("I search for the name of the character with path {int}")
    public void search_for_the_name_of_the_character_with_path(int characterPath) {
        characterName = getCharacterName(characterPath);
        articlePage = homePage.search(characterName);
    }

    @Then("I should be able to see the article page correctly displayed for the requested character")
    public void see_the_article_page_correctly_displayed_for_the_requested_character() {
        Assert.assertEquals(articlePage.getTitle(), characterName);
    }

    @When("I search for a random SW film")
    public void search_for_a_random_SW_film() {
        filmName = getARandomFilm();
        articlePage = homePage.search(filmName);
    }

    @Then("I will be able to see the article correctly displayed for the requested film")
    public void see_the_article_correctly_correctly_displayed_for_the_requested_film() {
        Assert.assertEquals(articlePage.getRedirectLabel(), filmName);
    }

    @When("I try to edit the article")
    public void try_to_edit_the_article() {
        articleTitle = articlePage.getTitle();
        editPage = articlePage.goToEditPage();
    }

    @Then("I will be redirected to the editing page")
    public void be_redirected_to_the_edit_page() {
        Assert.assertTrue(editPage.isTitleCorrect(articleTitle));
    }

    @When("I try to see the history of the article")
    public void try_to_see_the_history_of_the_article() {
        articleTitle = articlePage.getTitle();
        historyPage = articlePage.goToHistoryPage();
    }

    @Then("I will be redirected to the history page")
    public void be_redirected_to_the_history_page() {
        Assert.assertTrue(historyPage.isTitleCorrect(articleTitle));
    }

    @Before
    public void setUp() {
        setUpDriver("chrome");
        maximizeWindow();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
