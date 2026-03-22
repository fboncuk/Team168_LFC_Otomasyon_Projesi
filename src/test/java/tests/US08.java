package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LcfHomePage.SignButonsPage;
import utilities.ConfigReader;
import utilities.Driver;

import java.time.Duration;

public class US08 {

    SignButonsPage signButonsPage;
    WebDriver driver;
    SoftAssert softAssert;

    @BeforeClass
    public void setup(){

       signButonsPage= new SignButonsPage();
       softAssert = new SoftAssert();
    }
    @AfterClass
    public void teardown(){
        Driver.quitDriver();
    }

    @Test (priority = 1)
    public void US08_TC_001_GirisYapabilmekIcinSignInButtonuAktifOlmali(){

        //Kullanici Url'e gider
        Driver.getDriver().get(ConfigReader.getProperty("LfcUrl"));
        //Sign in butonunu görüntüler
        softAssert.assertTrue(signButonsPage.signInLinki.isDisplayed());
        //Sign in butonunu tıklar
        signButonsPage.signInLinki.isEnabled();
        signButonsPage.signInLinki.click();
        //softAssert.assertAll();
    }

    @Test (priority = 2)
    public void US08_TC_002_GecerliEmailAdresiniGirebilmeli(){
        // Kullanici geçerli e-mail adresini "e-mail" kutucuğuna girer
        signButonsPage.emailKutusu.sendKeys(ConfigReader.getProperty("T08UserMail"));

    }
    @Test (priority = 3)
    public void US08_TC_003_GecerliPasswordBilgisiniPasswordKutusunaGirebilmeli(){
        // Geçerli password bilgilerini "password" kutucuğuna girer
        signButonsPage.passwordKutusu.sendKeys(ConfigReader.getProperty("T08UserPassword"));

    }

    @Test (priority = 4)
    public void US08_TC_004_SifreUnutulursaDiyeForgotPasswordSecenegiOlmali(){
        // Kullanici "Forgot Password" seçeneğini görüntüler
        softAssert.assertTrue(signButonsPage.forgotPasswordLink.isDisplayed());

    }

    @Test (priority = 5)
    public void US08_TC_005_SiteyeOtomatikGirisYapmakIcinRememberMeKutusuOlmali(){
        //  "Remember Me" yazısını ve işaretlenebilir kutucuğu görüntüler
        softAssert.assertTrue(signButonsPage.rememberMeKutusu.isDisplayed());

    }

    @Test(priority = 6)
    public void US08_TC_006_GirilenBilgileriOnaylamakIcinSignInButonuTiklanabilmeli(){
        // Kullanici 2.Sign in butonuna tıklar
        signButonsPage.signInButtonOnay.click();
    }





}
