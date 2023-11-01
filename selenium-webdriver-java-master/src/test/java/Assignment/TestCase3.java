package Assignment;

import driver.driverFactory;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import static org.junit.Assert.assertEquals;

public class TestCase3 {
    @Test
    public void TestCase3() {
        // Step 1. Go to http://live.techpanda.org/
        WebDriver driver = driverFactory.getChromeDriver();
        driver.get("http://live.techpanda.org/");

        // Step 2. Click on -> MOBILE -> menu
        WebElement MOBILElink = driver.findElement(By.className("first"));
        MOBILElink.click();

        // Step 3. In the list of all mobile , click on -> ADD TO CART -> for Sony Xperia mobile
        WebElement sony = driver.findElement(By.xpath("//div[h2/a/@title='Sony Xperia']"));
        WebElement addToCart = sony.findElement(By.xpath(".//following-sibling::div/button[@title='Add to Cart']"));
        addToCart.click();

        // Step 4. Change -> QTY -> value to 1000 and click -> UPDATE -> button. Expected that an error is displayed
        WebElement qty = driver.findElement(By.xpath("//input[@title='Qty']"));
        qty.clear();
        qty.sendKeys("1000");
        WebElement updateButton = driver.findElement(By.xpath("//button[@title='Update']"));
        updateButton.click();

        WebElement errorMessage = driver.findElement(By.cssSelector("p.item-msg.error"));
        assertEquals("The requested quantity for \"Sony Xperia\" is not available.", errorMessage.getText());
        // Step 5. Verify the error message

        // Step 6. Then click on -> EMPTY CART -> link in the footer of list of all mobiles. A message "SHOPPING CART IS EMPTY" is shown.
        WebElement emptyCartLink = driver.findElement(By.xpath("//a[contains(text(),'Empty Cart')]"));
        emptyCartLink.click();

        // Step 7. Verify cart is empty
        WebElement emptyCartMessage = driver.findElement(By.xpath("//div[@class='page-title']/h1"));
        assertEquals("SHOPPING CART IS EMPTY", emptyCartMessage.getText());
    }
}