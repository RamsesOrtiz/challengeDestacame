package com.destacame.process;

import com.destacame.config.ConfigPage;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.destacame.config.LoggerPage.*;
import static com.destacame.config.PropertiesPage.*;

public class PurchaseProcess extends ConfigPage {

    private static final Logger logger = logger(PurchaseProcess.class);

    private static final By myCartLocator = By.cssSelector("button[class*='cart-modal__button-open']");
    private static final By deleteBaseProductLocator = By.id("btndeletetocart_47689_1");
    private static final By productDeletedLocator = By.cssSelector("div[class*='paragraph paragraph--sm']");
    private static final By totalPurchaseLocator = By.id("total_precio_normal");
    private static final By purchaseBtnLocator = By.id("ir_a_comprar");
    private static final By continueInvitedLocator = By.id("btn_invitado");
    private static final By takeAwayOptionLocator = By.cssSelector("input[name='rb_tipo_despacho'][id='accordion1']");
    private static final By dropdownListLocator = By.cssSelector("div[role='listbox'][data-type='select-one']");
    private static final By selectShopLocator = By.cssSelector("div[role='option'][id='choices--sel_tienda_carro-item-choice-2']");
    private static final By addressLocator = By.id("direccion_entrega");
    private static final By confirmPaymentLocator = By.id("btn_continuar_pago");

    public PurchaseProcess(WebDriver driver) {
        super(driver);
    }

    public static void visitCart() {
        waitAndClick(myCartLocator);
    }

    public static void isValidAmount() {
        waitElement(totalPurchaseLocator, 5);
        String totalAmount = getText(totalPurchaseLocator);
        totalAmount = totalAmount.substring(2).replace(".", "");
        int amount = Integer.parseInt(totalAmount);
        logger.info("Total Amount: $" + amount);

        if (amount < LIMIT_AMOUNT) {
            screenshot("Amount OK");
            logger.info("Amount below the limit");
        } else {
            screenshot("Amount Over the Limit");
            logger.info("Amount over the limit");
        }
    }

    public static void deleteProduct() {
        logger.info("Product deleted: " + getText(productDeletedLocator));
        waitAndClick(deleteBaseProductLocator);
        waitElement(deleteBaseProductLocator, 5);
        screenshot("Product deleted");
    }

    public static boolean purchase() {
        waitAndClick(purchaseBtnLocator);
        if(isDisplayed(continueInvitedLocator)){
            waitAndClick(continueInvitedLocator);
        }
        waitAndClick(takeAwayOptionLocator);
        waitAndClick(dropdownListLocator);
        moveToElement(selectShopLocator);
        waitElement(addressLocator, 5);

        if (isDisplayed(addressLocator) && getText(addressLocator).contentEquals(ADDRESS)) {
            waitElement(confirmPaymentLocator, 5);
            click(confirmPaymentLocator);
            screenshot("Successful Purchase");
            logger.info("Successful Purchase");
            return true;
        } else {
            screenshot("Purchase Error");
            logger.error("Purchase Error");
            return false;
        }
    }
}
