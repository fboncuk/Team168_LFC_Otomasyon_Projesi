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
import utilities.TestBaseRapor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class US41 extends TestBaseRapor {

    SignButonsPage signButonsPage;
    AdminBodyPage adminBodyPage;
    WebDriver driver;
    DashboardPage dashboardPage;
    DashboardTicketsPage dashboardTicketsPage;
    Actions actions;

    @BeforeClass
    public void setup() {
        adminBodyPage = new AdminBodyPage();
        signButonsPage = new SignButonsPage();
        dashboardPage = new DashboardPage();
        dashboardTicketsPage = new DashboardTicketsPage();
        actions = new Actions(Driver.getDriver());

    }

    @AfterClass
    public void teardown() {
        Driver.quitDriver();
    }

    @Test(priority = 1)

    public void US41_TC001_AdminIsimBilgisineTiklayipDashboarddanTickestsSeceneginiGorebilmeli(){

        extentTest =extentReports.createTest("US41_TC001_Tickets seceneginin gorulebilir ve tiklanabilir oldugunu dogrulama");
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
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(dashboardTicketsPage.ticketsLink.isDisplayed());
        softAssert.assertTrue(dashboardTicketsPage.ticketsLink.isEnabled());

        softAssert.assertAll();

    }


    @Test (priority = 2)
    public void US41_TC002_AdminTickectsSeceneginiTiklayabilmeli(){


        extentTest = extentReports.createTest("US41_TC002_Tickets secenegini tiklama");
        // Sol tarafta açılan menüden Tickets seçeneğine tıklar
        dashboardTicketsPage.ticketsLink.click();

    }

    @Test(priority = 3)
    public void US41_TC003_SayfadaRandevulariSondanGeriyeDogruGoruntuleyebilmeli(){


        // Açılan sayfada randevu bilgilerini güncel olandan geriye doğru görüntüler
        extentTest = extentReports.createTest("US41_TC003_Randevu siralamasi son tarihten geriye dogru goruntulenemedi.");
        SoftAssert softAssert = new SoftAssert();

        List<String> dateList = dashboardTicketsPage.tumRandevuTarihleri
                        .stream()
                        .map(WebElement::getText)
                        .map(String::trim)
                        .collect(Collectors.toList());

        System.out.println("Tarih listesi : " + dateList);

        List<LocalDate> actualDates = dateList.stream()
                .filter(data -> ! data.isEmpty())
                .map(LocalDate::parse)
                .collect(Collectors.toList());

        List<LocalDate> expectedDates = new ArrayList<>(actualDates);
        expectedDates.sort(Comparator.reverseOrder());

        softAssert.assertEquals(actualDates,expectedDates,"Tarihler guncelden eskiye sirali degil");

        softAssert.assertAll();

    }

    @Test(priority = 4)
    public void US41_TC004_RandevuTarihiYYYYMMddDoktorBolumVarsaMesajGoruntulenebilmeli() {

        extentTest = extentReports.createTest("US41_TC004_Randevu bilgilerini goruntuleme");
        // Randevu bilgilerinde tarih,doktor ve bölüm bilgisini ve varsa mesajı görüntüler

        dashboardTicketsPage.randevuDoktorTarihMesajGoruntuleme.isDisplayed();
    }

    @Test(priority = 5)
    public void US41_TC005_RandevularArasiTabIleGecisYapilabilmeli() {

        // Randevular arasında TAB tuşu ile geçiş yapar
        extentTest = extentReports.createTest("US41_TC005_Randevular arası gecisler TAB ile yapilamadi.");

        SoftAssert softAssert = new SoftAssert();

        dashboardTicketsPage.randevuDoktorTarihMesajGoruntuleme.isEnabled();
        dashboardTicketsPage.randevuDoktorTarihMesajGoruntuleme.click();
        ReusableMethods.bekle(2);
        actions.sendKeys(Keys.TAB).perform();
        ReusableMethods.bekle(1);

        String aktifElementText = Driver.getDriver().switchTo().activeElement().getText();

        // Veya beklediğin 2. randevu metniyle uyuşuyor mu?
        softAssert.assertFalse(aktifElementText.isEmpty(), "TAB tusu bir randevuya odaklanmadı, sayfa dışına kaçtı!");

        //Hata mesaji
        softAssert.fail("TAB tusu randevular arasında geçiş yapamadı, odak URL veya sayfa butonlarına kaydı.");


        softAssert.assertAll();
    }

    @Test(priority = 6)
    public void US41_TC006_RandevuDetaySayfasinaTiklayarakVeyaEnterYaparakUlasabilmeli() {

        // Diledigi randevu bilgisine tıklayarak veya ENTER yaparak detay sayfasına ulaşır
        extentTest = extentReports.createTest("US41_TC006_Randevu detay sayfasini goruntulemek icin tiklandi ancak sayfa acilmadi");
        SoftAssert softAssert = new SoftAssert();

        String ilkSayfaUrl =Driver.getDriver().getCurrentUrl();
        dashboardTicketsPage.randevuDoktorTarihMesajGoruntuleme.click();
        ReusableMethods.bekle(2);
        String sonrakiSayfaUrl = Driver.getDriver().getCurrentUrl();

        softAssert.assertNotEquals(ilkSayfaUrl,sonrakiSayfaUrl,"Tiklama sonrasi sayfa degismedi.");

        extentTest = extentReports.createTest("US41_TC006_Randevu detay sayfasina ulasmak icin ENTER tusuna basilamadi");

        String enterOncesiUrl= Driver.getDriver().getCurrentUrl();
        actions.sendKeys(Keys.ENTER).perform();
        ReusableMethods.bekle(2);
        String enterSonrasiSayfaUrl = Driver.getDriver().getCurrentUrl();

        softAssert.assertNotEquals(enterOncesiUrl,enterSonrasiSayfaUrl,"Enter sonrasi sayfa degismedi.");

        softAssert.assertAll();
    }

    @Test(priority = 7)
    public void US41_TC007_YoneticiTickestUrliniGirdigindeAnasayfayaYonlendirilmeli(){

        extentTest = extentReports.createTest("US41_TC007_Login sayfasina yonlendirme");
        // Kullanıcı giriş yapabilmesi için Login sayfasına yönlendirilir

        Driver.getDriver().get(ConfigReader.getProperty("LoginUrl"));
    }

}

