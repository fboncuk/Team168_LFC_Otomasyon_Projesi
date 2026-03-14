package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LcfAdminPage.AdminBodyPage;
import pages.LcfHomePage.SignButonsPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

import java.util.Arrays;
import java.util.List;

public class US22 {

    AdminBodyPage adminBodyPage;
    SoftAssert softAssert;

    @BeforeMethod
    public void setup(){

        // Admin Login sayfasına gidilir
        Driver.getDriver().get(ConfigReader.getProperty("DasUrl"));

        // Geçerli admin bilgileriyle giriş yapılır
        SignButonsPage signButonsPage = new SignButonsPage();
        signButonsPage.emailKutusu.sendKeys(ConfigReader.getProperty("T11AdminMail"));
        signButonsPage.passwordKutusu.sendKeys(ConfigReader.getProperty("T11AdminPassword"));
        signButonsPage.signInButtonOnay.click();

        adminBodyPage = new AdminBodyPage();
        softAssert = new SoftAssert();
    }

    @AfterMethod
    public void tearDown() {
       Driver.quitDriver();
    }

    // Devre dışı bırakmak için (enabled = false) kullan
    @Test(priority = -2)
    public void US22_TC01_AdminPaneliGoruntulemeTesti(){

        // Mevcut URL'in admin Dashboard içerdiği doğrulanır
        String expectedUrlContent = "/admin";
        String actualUrl = Driver.getDriver().getCurrentUrl();

        Assert.assertTrue(actualUrl.contains(expectedUrlContent), "HATA: Admin paneline yönlendirme başarısız!");
    }

    // Devre dışı bırakmak için (enabled = false) kullan
    @Test(priority = -1)
    public void US_22_TC02_DashboardOzetKartlariGorunurlukTesti() {
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
    public void US_22_TC03_DashboardLearnMoreAtUsersLinkTiklanabilirlikVeYonlendirmeTesti(){

        ReusableMethods.adminDashboardLinkKontrol(adminBodyPage.learnMoreAtUsersLink, "/Users");
    }

    @Test(priority = 2)
    public void US_22_TC03_DashboardlearnMoreAtMessagesLinkVeYonlendirmeTesti(){

        ReusableMethods.adminDashboardLinkKontrol(adminBodyPage.learnMoreAtMessagesLink, "/Messages");
    }

    @Test(priority = 3)
    public void US_22_TC03_DashboardlearnMoreAtRolesLinkLinkVeYonlendirmeTesti(){

        ReusableMethods.adminDashboardLinkKontrol(adminBodyPage.learnMoreAtRolesLink, "/Roles");
    }

    @Test(priority = 4)
    public void US_22_TC03_DashboardlearnMoreAtSettingsLinkVeYonlendirmeTesti(){

        ReusableMethods.adminDashboardLinkKontrol(adminBodyPage.learnMoreAtSettingsLink, "/Settings");
    }

    @Test(priority = 5)
    public void US_22_TC03_DashboardlearnMoreAtGoogleAdvertisementLinkVeYonlendirmeTesti(){

        ReusableMethods.adminDashboardLinkKontrol(adminBodyPage.learnMoreAtGoogleAdvertisementLink, "/AdSense");
    }

    @Test(priority = 6)
    public void US_22_TC03_DashboardlearnMoreAtBedManagersLinkVeYonlendirmeTesti(){

        ReusableMethods.adminDashboardLinkKontrol(adminBodyPage.learnMoreAtBedManagersLink, "/BedManagers");
    }

    @Test(priority = 7)
    public void US_22_TC03_DashboardlearnMoreAtMedicinesLinkVeYonlendirmeTesti(){

        ReusableMethods.adminDashboardLinkKontrol(adminBodyPage.learnMoreAtMedicinesLink, "/Medicines");
    }

    // TC04'ün görsel testi için;
    // Dashboard üzerindeki özet kartlar, HTML yapısında benzersiz (unique) tanımlayıcılara (ID, Alt Text veya spesifik Icon Class) sahip değildir.
    // Tüm kartlar aynı CSS sınıf yapısını paylaştığı için görsellerin veya ikonların varlığı programatik olarak birbirinden ayırt edilememektedir.
    @Test(priority = 8)
    public void US22_TC04_DashboardMesajTutarlilikTesti() {
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
        Assert.assertEquals(actualMessageCountOnMessagesPage, expectedMessageCountFromDashboard,
                "HATA: Dashboard rakamı ile sayfadaki ticket sayısı uyuşmuyor!");
    }

    @Test(priority = 9)
    public void US_22_TC_05_SosyalMedyaIkonlariGorunurlukVeTiklanabilirlikTesti(){

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
