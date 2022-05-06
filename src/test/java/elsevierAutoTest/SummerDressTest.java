package elsevierAutoTest;

/**
 * TASK 4 Test Automation for Elsevier QA Skills Exercises
 * Go to http://automationpractice.com/index.php and using C# or Java,
 * write an automated test to verify that summer dresses can be added
 * to the cart, and it’s possible to proceed to the Sign in section
 */


import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import static org.junit.jupiter.api.Assertions.*;


public class SummerDressTest {

    @Test
    public void dressTest() throws InterruptedException {

        // set up driver, webDriver
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("http://automationpractice.com/index.php");
        driver.manage().window().maximize();
        Thread.sleep(3000);

        //hoover over dresses module
        WebElement dresses = driver.findElement(By.xpath("//*[@id=\"block_top_menu\"]/ul/li[2]/a"));
        Actions action = new Actions(driver);
        action.moveToElement(dresses).perform();
        Thread.sleep(3000);

        // click on summer dresses
        WebElement summerDresses = driver.findElement(By.xpath("//*[@id=\"block_top_menu\"]/ul/li[2]/ul/li[3]/a"));
        summerDresses.click();
        Thread.sleep(3000);

        // hoover over dress image and click add to cart
        WebElement dressImage = driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[1]/div/div[1]/div/a[1]/img"));
        action.moveToElement(dressImage).perform();
        Thread.sleep(3000);
        WebElement addToCart = driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[1]/div/div[2]/div[2]/a[1]"));
        addToCart.click();
        Thread.sleep(3000);

        // add to cart success message verification
        WebElement addToCartMessage = driver.findElement(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[1]/h2"));
        String actualMessage = addToCartMessage.getText();
        System.out.println("Add to cart Message = " + addToCartMessage.getText() );
        String expectedSuccessMessage = "Product successfully added to your shopping cart";
        System.out.println("Expected Success Text Message = " + expectedSuccessMessage);
        // verification part
        assertTrue(addToCartMessage.isDisplayed());
        assertEquals(expectedSuccessMessage,actualMessage);
        Thread.sleep(2000);

        //proceed to checkout and verify you are at the Sign_in page
        WebElement proceedToCheckoutButton = driver.findElement(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/a"));
        proceedToCheckoutButton.click();
        Thread.sleep(2000);
        WebElement checkout = driver.findElement(By.xpath("//*[@id=\"center_column\"]/p[2]/a[1]"));
        checkout.click();
        Thread.sleep(2000);
        WebElement signInButton = driver.findElement(By.id("SubmitLogin"));
        // Sign_in button is enabled and displayed
        assertTrue(signInButton.isDisplayed());
        assertTrue(signInButton.isEnabled());


        //close driver
        driver.quit();
    }
}


