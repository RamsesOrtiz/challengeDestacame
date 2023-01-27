package com.destacame.process;

import com.destacame.config.ConfigPage;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.destacame.config.PropertiesPage.*;
import static com.destacame.config.LoggerPage.*;

public class AddProductsProcess extends ConfigPage {

    private static final Logger logger = logger(AddProductsProcess.class);

    private static final By searchBoxLocator = By.cssSelector("input[class='ais-SearchBox-input']");
    private static final By findBtnLocator = By.cssSelector("button[class='ais-input-search-button']");
    private static final By addToCartBtnLocator = By.cssSelector("button[id*='addtocart']");
    private static final By confirmPurchaseLocator = By.cssSelector("div[class*='product-single-cart-modal__heading']");
    private static final By closeModalLocator = By.cssSelector("button[class*='product-single-cart-modal__close']");

    public AddProductsProcess(WebDriver driver) {
        super(driver);
    }

    public static void visitPage() {
        setup(BROWSER);
        visit(URL);
        logger.info("Web successfully visited: " + URL);
    }

    public static void findProduct(String product) {
        waitAndClick(searchBoxLocator);
        type(product, searchBoxLocator);
        click(findBtnLocator);
        waitElement(addToCartBtnLocator, 5);
        screenshot("Find Product");
        logger.info("Product found: " + product);
    }

    public static void addToCartProduct() {
        waitAndClick(addToCartBtnLocator);
        logger.info("Product added to cart");
    }

    public static void getConfirmation() {
        waitElement(confirmPurchaseLocator, 5);
        String confirmation = getText(confirmPurchaseLocator);
        screenshot("Product Added to Cart");
        if(confirmation.contains(CONFIRMATION_MESSAGE)){
            logger.info("Confirmation Message OK: " + confirmation);
        } else {
            logger.error("Confirmation Message Error: " + confirmation);
        }

        click(closeModalLocator);
    }

    public static void selectProduct(String product) {
        findProduct(product);
        addToCartProduct();
        getConfirmation();
    }


}
