package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LcfAdminPage.AdminBodyPage;
import pages.LcfAdminPage.DashboardMedicinesPage;
import pages.LcfHomePage.DoctorsMainPage;
import pages.LcfHomePage.HomeBodyPage;
import pages.LcfHomePage.SignButonsPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;
import utilities.TestBaseRapor;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class US22 extends TestBaseRapor {

    AdminBodyPage adminBodyPage;
    SignButonsPage signButonsPage;

    @BeforeMethod
    public void setup() {
        // Objeleri oluştur
        adminBodyPage = new AdminBodyPage();
        signButonsPage = new SignButonsPage();

        // Admin Dashboard sayfasına git
        Driver.getDriver().get(ConfigReader.getProperty("DasUrl"));
        Driver.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(0));

        try {
            // Eğer email kutusu görünürse (yani dışarıdaysak) giriş yap
            if (signButonsPage.emailKutusu.isDisplayed()) {
                signButonsPage.emailKutusu.sendKeys(ConfigReader.getProperty("T11AdminMail"));
                signButonsPage.passwordKutusu.sendKeys(ConfigReader.getProperty("T11AdminPassword"));
                signButonsPage.signInButtonOnay.click();

            }
        } catch (Exception e) {
            // email kutusu yoksa (yani Dashboard direkt açıldıysa) hata verme, devam et
            System.out.println("Zaten Admin Dashboard sayfasındayız, login adımları atlandı.");
        }
        Driver.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @AfterMethod
    public void tearDown() {
//        adminBodyPage.profileDropdownButton.click();
//        adminBodyPage.profileLogoutOption.click();
//        signButonsPage.homePageSignOut.click();
//       Driver.quitDriver();
    }

    // Devre dışı bırakmak için (enabled = false) kullan
    @Test(priority = -2)
    public void US22_TC01_AdminPaneliGoruntulemeTesti(){
        extentTest = extentReports.createTest("TC01 - Admin Paneli Görüntüleme Testi");
        SoftAssert softAssert = new SoftAssert();

        // Mevcut URL'in admin Dashboard içerdiği doğrulanır
        String expectedUrlContent = "/admin";
        String actualUrl = Driver.getDriver().getCurrentUrl();

        softAssert.assertTrue(actualUrl.contains(expectedUrlContent), "HATA: Admin paneline yönlendirme başarısız!");
        softAssert.assertAll();
    }

    // Devre dışı bırakmak için (enabled = false) kullan
    @Test(priority = -1)
    public void US22_TC02_DashboardOzetKartlariGorunurlukTesti() {

        extentTest = extentReports.createTest("TC02 - Özet Kartları Görünürlük Testi");
        SoftAssert softAssert = new SoftAssert();
        // Dashboard üzerindeki tüm kartlarda linkler de olduğundan,
        // linklerle kartların görünür olduğu doğrulanır.

        // Tüm "Learn More" linklerini bir liste içine toplar
        List<WebElement> dashboardSummaryCardList = Arrays.asList(
                adminBodyPage.learnMoreAtUsersLink,
                adminBodyPage.learnMoreAtMessagesLink,
                adminBodyPage.learnMoreAtRolesLink,
                adminBodyPage.learnMoreAtSettingsLink,
                adminBodyPage.learnMoreAtGoogleAdvertisementLink,
                adminBodyPage.learnMoreAtBedManagersLink,
                adminBodyPage.learnMoreAtMedicinesLink
        );

        // Döngü ile her bir kartın (linkin) görünürlüğünü doğrular
        for (WebElement eachSummaryCard : dashboardSummaryCardList) {

            // Görünürlüğü test eder
            softAssert.assertTrue(eachSummaryCard.isDisplayed(),
                    "HATA: Dashboard özet kartı/linki görünmüyor!");
            // Konsolda hangi kartın başarıyla geçtiğini takip eder
            System.out.println("Görünürlük doğrulandı: " + eachSummaryCard.getText());
        }
        softAssert.assertAll();
    }

    @Test(priority = 1)
    public void US22_TC03_DashboardLearnMoreAtUsersLinkTiklanabilirlikVeYonlendirmeTesti(){

        extentTest = extentReports.createTest("TC03 - Users Link Testi");
        ReusableMethods.adminDashboardLinkKontrol(adminBodyPage.learnMoreAtUsersLink, "/Users", extentTest);
    }

    @Test(priority = 2)
    public void US22_TC03_DashboardlearnMoreAtMessagesLinkVeYonlendirmeTesti(){

        extentTest = extentReports.createTest("TC03 - Messages Link ve Yönlendirme Testi");
        ReusableMethods.adminDashboardLinkKontrol(adminBodyPage.learnMoreAtMessagesLink, "/Messages",extentTest);
    }

    @Test(priority = 3)
    public void US22_TC03_DashboardlearnMoreAtRolesLinkLinkVeYonlendirmeTesti(){

        extentTest = extentReports.createTest("TC03 - Roles Link ve Yönlendirme Testi");
        ReusableMethods.adminDashboardLinkKontrol(adminBodyPage.learnMoreAtRolesLink, "/Roles",extentTest);
    }

    @Test(priority = 4)
    public void US22_TC03_DashboardlearnMoreAtSettingsLinkVeYonlendirmeTesti(){

        extentTest = extentReports.createTest("TC03 - Settings Link ve Yönlendirme Testi");
        ReusableMethods.adminDashboardLinkKontrol(adminBodyPage.learnMoreAtSettingsLink, "/Settings",extentTest);
    }

    @Test(priority = 5)
    public void US22_TC03_DashboardlearnMoreAtGoogleAdvertisementLinkVeYonlendirmeTesti(){

        extentTest = extentReports.createTest("TC03 - Google Ads Link ve Yönlendirme Testi");
        ReusableMethods.adminDashboardLinkKontrol(adminBodyPage.learnMoreAtGoogleAdvertisementLink, "/AdSense",extentTest);
    }

    @Test(priority = 6)
    public void US22_TC03_DashboardlearnMoreAtBedManagersLinkVeYonlendirmeTesti(){

        extentTest = extentReports.createTest("TC03 - Bed Managers Link ve Yönlendirme Testi");
        ReusableMethods.adminDashboardLinkKontrol(adminBodyPage.learnMoreAtBedManagersLink, "/BedManagers",extentTest);
    }

    @Test(priority = 7)
    public void US22_TC03_DashboardlearnMoreAtMedicinesLinkVeYonlendirmeTesti(){

        extentTest = extentReports.createTest("TC03 - Medicines Link ve Yönlendirme Testi");
        ReusableMethods.adminDashboardLinkKontrol(adminBodyPage.learnMoreAtMedicinesLink, "/Medicines",extentTest);
    }

    // TC04'ün görsel testi için;
    // Dashboard üzerindeki özet kartlar, HTML yapısında benzersiz (unique) tanımlayıcılara (ID, Alt Text veya spesifik Icon Class) sahip değildir.
    // Tüm kartlar aynı CSS sınıf yapısını paylaştığı için görsellerin veya ikonların varlığı programatik olarak birbirinden ayırt edilememektedir.
    @Test(priority = 8)
    public void US22_TC04_DashboardMesajTutarlilikTesti() {

        extentTest = extentReports.createTest("TC04 - Dashboard Mesaj Tutarlılık Testi");
        SoftAssert softAssert = new SoftAssert();
        // Elementi dinamik metinle bulur
        WebElement dashboardMessageElement = Driver.getDriver().findElement(
                By.xpath("//*[contains(text(),'Messages in your Database')]")
        );

        // Elementin metnini alır
        String fullText = dashboardMessageElement.getText();

        // Metin içinden sadece rakamı ayıklar
        // replaceAll("[^0-9]", "") ---> Sayı dışındaki her şeyi siler
        int expectedMessageCountFromDashboard = Integer.parseInt(fullText.replaceAll("[^0-9]", ""));
        System.out.println("Dashboard'da beklenen mesaj sayısı: " + expectedMessageCountFromDashboard);

        // Mesajlar sayfasına gider ticketlari sayar
        adminBodyPage.learnMoreAtMessagesLink.click();

        List<WebElement> actualMessageTicketList = Driver.getDriver().findElements(
                By.xpath("//*[@class='card-header clearfix']")
        );

        int actualMessageCountOnMessagesPage = actualMessageTicketList.size();
        System.out.println("Mesajlar sayfasında bulunan gerçek sayı: " + actualMessageCountOnMessagesPage);

        // Dashboard'da gözüken ve aslında olan ticket sayılarını karşılaştırır
        softAssert.assertEquals(actualMessageCountOnMessagesPage, expectedMessageCountFromDashboard,
                "HATA: Dashboard rakamı ile sayfadaki ticket sayısı uyuşmuyor!");
        softAssert.assertAll();
    }

    @Test(priority = 9)
    public void US22_TC05_SosyalMedyaIkonlariGorunurlukVeTiklanabilirlikTesti(){

        extentTest = extentReports.createTest("TC05 - Sosyal Medya İkonları Testi");
        SoftAssert softAssert = new SoftAssert();

        // Tüm ikonları bir listeye toplar
        List<WebElement> dashboardSocialMediaIconList = Arrays.asList(
                adminBodyPage.facebookIconLink,
                adminBodyPage.twitterIconLink,
                adminBodyPage.pinterestIconLink,
                adminBodyPage.instagramIconLink,
                adminBodyPage.linkedinIconLink,
                adminBodyPage.tumblrIconLink,
                adminBodyPage.githubIconLink
        );

        // Döngü ile her bir ikonu kontrol eder
        for (WebElement eachSocialMediaIcon : dashboardSocialMediaIconList) {

            // Görünürlüğünü kontrol eder
            softAssert.assertTrue(eachSocialMediaIcon.isDisplayed(),
                    "HATA: Sosyal medya ikonu görünmüyor!");

            // Tıklanabilir olduğunu kontrol eder
            softAssert.assertTrue(eachSocialMediaIcon.isEnabled(),
                    "HATA: Sosyal medya ikonu tıklanabilir değil!");
        }
        softAssert.assertAll();

    }

}
