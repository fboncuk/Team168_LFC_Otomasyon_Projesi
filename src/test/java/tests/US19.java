package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LcfHomePage.SignButonsPage;
import utilities.ConfigReader;
import utilities.Driver;

import java.time.Duration;

public class US19 {

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
    public void US19_TC_001_SistemeKullaniciOlarakGirisYapilabilmeli(){
        //Kullanici Url'e gider
        Driver.getDriver().get(ConfigReader.getProperty("LfcUrl"));
        //Sign in butonuna tıklar
        signButonsPage.signInLinki.click();
        //Geçerli mail adresini girer
        signButonsPage.emailKutusu.sendKeys(ConfigReader.getProperty("T08UserMail"));
        //Geçerli password bilgilerini girer
        signButonsPage.passwordKutusu.sendKeys(ConfigReader.getProperty("T08UserPassword"));
        //Kullanici 2. sign in butonuna tıklar ve siteye giriş yapar
        signButonsPage.signInButtonOnay.click();
    }

    @Test (priority = 2)
    public void US19_TC_002_KullaniciGirisYaptiginiDogrulayanIsimBilgisiniGoruntuleyebilmeli(){
       // Kullanici siteye giriş yaptığına dair isim bilgisini görüntüler
        softAssert.assertTrue(signButonsPage.headerUserName.isDisplayed());
        //softAssert.assertAll();
    }

    @Test (priority = 3)
    public void US19_TC_003_KullaniciCikisYapabilmekIcinSignOutButonunuGoruntleyebilmeli(){
        // Kullanıcı sayfasından çıkış yapabilmek için Sign Out butonunu görüntüler
        softAssert.assertTrue(signButonsPage.signOutlinki.isDisplayed());
    }

    @Test (priority = 4)
    public void US19_TC_004_KullaniciSignOutButonunaTiklayabilmeli(){
        // Kullanıcı sayfasından çıkış yapabilmek için Sign Out butonunu tıklar
        signButonsPage.homePageSignOut.click();
    }

    @Test (priority = 5)
    public void US19_TC_05_KullaniciKendiSayfasindanCikisYapabilmeli(){
        // Kullanıcı sitenin anasayfasını görüntüler

        String expectedUrlIcerik = "https://qa.loyalfriendcare.com/en";
        String actualUrlIcerik= Driver.getDriver().getCurrentUrl();

        softAssert.assertEquals(actualUrlIcerik,expectedUrlIcerik);

    }
}
