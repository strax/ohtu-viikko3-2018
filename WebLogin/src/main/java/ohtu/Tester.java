package ohtu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Tester {

    public static void main(String[] args) {
        WebDriver driver = new HtmlUnitDriver();

        driver.get("http://localhost:4567");
        
        sleep(2);

        // Go to login page
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();

        sleep(2);

        // Perform logging in with correct username and password
        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkep");
        element = driver.findElement(By.name("login"));
        
        sleep(2);
        element.submit();

        sleep(3);

        // Go to login page
        driver.get("http://localhost:4567");
        sleep(2);
        driver.findElement(By.linkText("login")).click();
        sleep(2);

        // Perform logging in with invalid username
        driver.findElement(By.name("username")).sendKeys("sami");
        driver.findElement(By.name("password")).sendKeys("isam");
        sleep(2);
        driver.findElement(By.name("login")).submit();
        sleep(3);

        // Perform creating a new user
        driver.get("http://localhost:4567");
        sleep(2);
        driver.findElement(By.linkText("register new user")).click();
        sleep(2);
        driver.findElement(By.name("username")).sendKeys("sami");
        driver.findElement(By.name("password")).sendKeys("isam");
        driver.findElement(By.name("passwordConfirmation")).sendKeys("isam");
        sleep(2);
        driver.findElement(By.name("signup")).submit();
        sleep(3);

        // Log out
        driver.findElement(By.linkText("continue to application mainpage")).click();
        sleep(2);
        driver.findElement(By.linkText("logout")).click();
        sleep(3);

        driver.quit();
    }
    
    private static void sleep(int n){
        try{
            Thread.sleep(n*1000);
        } catch(Exception e){}
    }

}
