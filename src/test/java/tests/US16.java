package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LcfHomePage.SignButonsPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;



public class US16 {

    // System.out.printf("Hadi BISMILLAH baslasinn >>>>>>>>>");


    //Bir kayıtlı kullanıcı olarak,
    // Home Page sayfasından Doctors sayfasına erişebilmeli,
    // Doctors sayfasındaki doktor bilgilerini inceleyebilmeli ve
    // seçtiğim doktorun
    // sayfasına erişerek o doktordan randevu talebi oluşturabilmeliyim

    SignButonsPage signButonsPage;


    @BeforeClass
    public void setup() {
        signButonsPage = new SignButonsPage();
        // Homepage açılır
        Driver.getDriver().get(ConfigReader.getProperty("LfcUrl"));

        signButonsPage = new SignButonsPage();
        signButonsPage.signInLinki.click();
        signButonsPage.emailKutusu.sendKeys(ConfigReader.getProperty("T05UserMail"));
        signButonsPage.passwordKutusu.sendKeys(ConfigReader.getProperty("T05UserPassword"));
        signButonsPage.signInButtonOnay.click();

    }

    @AfterClass
    public void tearDown() {
        Driver.quitDriver();
    }


    @Test
    public void US16_TC01_DoktorlarErisimTest() {
        //Kayıtlı kullanıcının
        //Home Page’den Doctors sayfasına erişimini doğrulama

        Driver.getDriver().get(ConfigReader.getProperty("DocUrl"));



          WebDriver driver = new ChromeDriver();


        // 2) Doctors sayfasına git
        driver.findElement(By.xpath("//a[contains(text(),'Doctors')]")).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("/doctors"),
                "Doctors sayfası açılmadı!");

        // 3) Doctors sayfasındaki doktor kartlarını incele
        List<WebElement> doctorCards = driver.findElements(By.cssSelector(".doctor-card"));
        Assert.assertTrue(doctorCards.size() > 0, "Doktor kartı bulunamadı!");

        for (WebElement card : doctorCards) {

            // Doktor adı
            WebElement name = card.findElement(By.tagName("h3"));
            Assert.assertTrue(name.isDisplayed(), "Doktor adı görünmüyor.");
            Assert.assertFalse(name.getText().trim().isEmpty(), "Doktor adı boş.");

            // Profession
            WebElement profession = card.findElement(By.xpath(".//p[contains(text(),'Profession')]"));
            Assert.assertTrue(profession.isDisplayed(), "Profession görünmüyor.");

            // Years of Experience
            WebElement years = card.findElement(By.xpath(".//p[contains(text(),'Years of Experience')]"));
            Assert.assertTrue(years.isDisplayed(), "Years of Experience görünmüyor.");

            // Specialization
            WebElement specialization = card.findElement(By.xpath(".//p[contains(text(),'Specialization')]"));
            Assert.assertTrue(specialization.isDisplayed(), "Specialization görünmüyor.");

            // Message
            WebElement message = card.findElement(By.xpath(".//p[contains(text(),'Message')]"));
            Assert.assertTrue(message.isDisplayed(), "Message görünmüyor.");
        }

        // 4) Seçilecek doktor
        String doctorName = "Alejandro Martinez";

        // 5) Doktor kartına tıkla → detay sayfasına git
        WebElement selectedDoctor = driver.findElement(
                By.xpath("//h3[contains(text(),'" + doctorName + "')]")
        );
        selectedDoctor.click();

        // 6) Doktor detay sayfasının açıldığını doğrula
        WebElement doctorHeader = driver.findElement(By.tagName("h2"));
        Assert.assertTrue(doctorHeader.getText().contains(doctorName),
                "Doktor detay sayfası doğru yüklenmedi!");

        // 7) Appointment formunu doldur
        driver.findElement(By.id("name")).sendKeys("Test User");
        driver.findElement(By.id("email")).sendKeys("testuser@example.com");
        driver.findElement(By.id("phone")).sendKeys("1234567890");
        driver.findElement(By.id("message")).sendKeys("I would like to request an appointment.");

        // 8) Submit butonuna tıkla
        driver.findElement(By.xpath("//button[contains(text(),'Submit')]")).click();

        // 9) Başarı mesajını doğrula
        WebElement successMessage = driver.findElement(
                By.xpath("//*[contains(text(),'Your appointment request has been sent')]")
        );

        Assert.assertTrue(successMessage.isDisplayed(),
                "Randevu talebi başarıyla gönderilmedi!");







    }

}