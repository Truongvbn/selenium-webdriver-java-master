package Assignment;

import driver.driverFactory;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestCase4 {
    @Test
    public void testCompareProducts() {
        // Step 1. Go to http://live.techpanda.org/
        WebDriver driver = driverFactory.getChromeDriver();
        driver.get("http://live.techpanda.org/");

        // Step 2. Click on -> MOBILE -> menu
        WebElement mobileMenu = driver.findElement(By.linkText("MOBILE"));
        mobileMenu.click();

        // Step 3. In mobile products list, click on -> Add To Compare -> for 2 mobiles (Sony Xperia & Iphone)
        WebElement sonyAddToCompare = driver.findElement(By.xpath("//a[@title='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']/ul/li/a[text()='Add to Compare']"));

        sonyAddToCompare.click();
   WebElement iphoneAddToCompare = driver.findElement(By.xpath("//a[@title='IPhone']/parent::h2/following-sibling::div[@class='actions']/ul/li/a[text()='Add to Compare']"));
        iphoneAddToCompare.click();

        // Step 4. Click on -> COMPARE -> button. A popup window opens
        WebElement compareButton = driver.findElement(By.xpath("//button[@title='Compare']"));
        compareButton.click();

        // Step 5. Verify that the pop-up window titled "COMPARE PRODUCTS" is displayed and that the selected products (Sony Xperia and iPhone) are reflected in it.
        Duration timeout = Duration.ofSeconds(10);
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        WebElement popupWindow = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='page-title title-buttons']/h1")));
        String popupText = popupWindow.getText();
        assertEquals("COMPARE PRODUCTS", popupText);

        WebElement sonyProduct = driver.findElement(By.xpath("//a[@title='Sony Xperia']"));
        WebElement iphoneProduct = driver.findElement(By.xpath("//a[@title='IPhone']"));
        assertTrue(sonyProduct.isDisplayed());
        assertTrue(iphoneProduct.isDisplayed());

        // Step 6. Close the Popup Windows
        WebElement closeButton = driver.findElement(By.xpath("//button[@title='Close Window']"));
        closeButton.click();

        driver.quit();
    }
}