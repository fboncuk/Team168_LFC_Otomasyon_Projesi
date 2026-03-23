package tests;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LcfAdminPage.*;
import pages.LcfHomePage.SignButonsPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;


public class US41 {

    SignButonsPage signButonsPage;
    AdminBodyPage adminBodyPage;
    WebDriver driver;
    SoftAssert softAssert;
    DashboardPage dashboardPage;
    DashboardTicketsPage dashboardTicketsPage;

    //Actions actions;

    @BeforeClass
    public void setup() {
        adminBodyPage = new AdminBodyPage();
        signButonsPage = new SignButonsPage();
        softAssert = new SoftAssert();
        dashboardPage = new DashboardPage();
        dashboardTicketsPage = new DashboardTicketsPage();
        //actions = new Actions(driver);
    }

    @AfterClass
    public void teardown() {


    }

    @Test(priority = 1)

    public void US41_TC001_AdminIsimBilgisineTiklayipDashboarddanTickestsSeceneginiGorebilmeli(){

        //Kullanici Url' e gider
        Driver.getDriver().get(ConfigReader.getProperty("LfcUrl"));

        //Sign in butonuna tıklar
        signButonsPage.signInLinki.click();
        //Geçerli mail adresini girer
        signButonsPage.emailKutusu.sendKeys(ConfigReader.getProperty("T08AdminMail"));
        //Geçerli password bilgilerini girer
        signButonsPage.passwordKutusu.sendKeys(ConfigReader.getProperty("T08AdminPassword"));
        //Kullanici 2. sign in butonuna tıklar ve siteye giriş yapar
        signButonsPage.signInButtonOnay.click();
        //Isim bilgisine tıklar
        signButonsPage.headerUserName.click();
        ReusableMethods.bekle(2);

        //Sol tarafta açılan menüden Tickets seçeneğini görüntüler
        ReusableMethods.hover(dashboardPage.dashboardPageSideBarMenu);
        softAssert.assertTrue(dashboardTicketsPage.ticketsLink.isDisplayed());
        softAssert.assertTrue(dashboardTicketsPage.ticketsLink.isEnabled());

        softAssert.assertAll();

    }


    @Test (priority = 2)
    public void US41_TC002_AdminTickectsSeceneginiTiklayabilmeli(){


        // Sol tarafta açılan menüden Tickets seçeneğine tıklar
        dashboardTicketsPage.ticketsLink.click();

    }

    @Test(priority = 3)
    public void US41_TC003_SayfadaRandevulariSondanGeriyeDogruGoruntuleyebilmeli(){


        // Açılan sayfada randevu bilgilerini güncel olandan geriye doğru görüntüler
        LocalDate ldt = LocalDate.now();

        String expectedDate = ldt.toString();
        String actualDate = dashboardTicketsPage.ilkRandevuTarihBilgisi.getText().trim();

        //softAssert.assertEquals(actualDate,expectedDate,"Tarih bilgisi guncel tarih ile uyusmuyor");

        System.out.println("Beklenen Son Kayıt Tarihi: " + expectedDate);
        System.out.println("İlk Satır Tarih : " + actualDate);

        // aynı olmalı diyorsan:
//        softAssert.assertEquals(actualDate, expectedDate,
//                "Son randevu bugünün tarihi olmalı!");


        softAssert.assertEquals(actualDate, expectedDate,
                "En güncel kayıt en üstte değil!");

        softAssert.assertAll();

    }

    @Test(priority = 4)
    public void US41_TC004_RandevuTarihiYYYYMMddDoktorBolumVarsaMesajGoruntulenebilmeli() {

        // Randevu bilgilerinde tarih,doktor ve bölüm bilgisini ve varsa mesajı görüntüler

        dashboardTicketsPage.randevuDoktorTarihMesajGoruntuleme.isDisplayed();
    }



}

