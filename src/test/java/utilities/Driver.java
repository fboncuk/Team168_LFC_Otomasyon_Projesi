package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
//import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class Driver {

    private static WebDriver driver;

    public static WebDriver getDriver(){

        String kullanilacakBrowser = ConfigReader.getProperty("browser");

        if (driver == null){

            switch (kullanilacakBrowser){

                case "firefox":
                    driver = new FirefoxDriver();
                    break;

                case "edge":
                    driver = new EdgeDriver();
                    break;

                case "safari":
                    driver = new SafariDriver();
                    break;

                default:
                    driver = new ChromeDriver();
            }
//            ChromeOptions options = new ChromeOptions();
//
//            // Temiz profil oluştur
//            options.addArguments("--user-data-dir=/tmp/test-profile");
//            // Google servislerini kapat
//            options.addArguments("--guest");
//
//            driver = new ChromeDriver(options);

            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        }

        return driver;
    }

    public static void quitDriver(){

        if (driver != null){
            driver.quit();
            driver = null;
        }
    }
}