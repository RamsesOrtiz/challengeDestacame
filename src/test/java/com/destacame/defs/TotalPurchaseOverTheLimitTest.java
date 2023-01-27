package com.destacame.defs;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import static com.destacame.config.PropertiesPage.*;
import static com.destacame.process.AddProductsProcess.*;
import static com.destacame.process.PurchaseProcess.*;

public class TotalPurchaseOverTheLimitTest {

    @Given("a user visits the webpage")
    public void a_user_visits_the_webpage() {
        visitPage();
    }

    @Given("add some products to the cart")
    public void add_some_products_to_the_cart() {
        selectProduct(PRODUCT);
        selectProduct(PRODUCT_1);
    }

    @When("visit the cart")
    public void visit_the_cart() {
        visitCart();
    }

    @When("the total of purchase is over the limit")
    public void the_total_of_purchase_is_over_the_limit() {
        isValidAmount();

    }

    @Then("drop a product and continue with the purchase")
    public void drop_a_product_and_continue_with_the_purchase() {
        deleteProduct();
        Assert.assertTrue(purchase());
    }

    @After
    public void close() {
        quit();
    }
}
