package com.destacame.defs;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import static com.destacame.config.PropertiesPage.*;
import static com.destacame.process.AddProductsProcess.*;
import static com.destacame.process.PurchaseProcess.*;

public class TotalPurchaseBelowTheLimitTest {

    @Given("a user visits the pcfactory webpage")
    public void a_user_visits_the_pcfactory_webpage() {
        visitPage();
    }

    @Given("add two products to the cart")
    public void add_two_products_to_the_cart() {
        selectProduct(PRODUCT_1);
        selectProduct(PRODUCT_2);

    }

    @When("goes to the cart")
    public void goes_to_the_cart() {
        visitCart();
    }

    @When("the total of purchase is below the limit")
    public void the_total_of_purchase_is_below_the_limit() {
        isValidAmount();
    }

    @Then("continue with the purchase")
    public void continue_with_the_purchase() {
        Assert.assertTrue(purchase());
    }

    @After
    public void close() {
        quit();
    }
}
