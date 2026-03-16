package tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LcfAdminPage.AdminBodyPage;
import pages.LcfAdminPage.DashboardMedicinesPage;
import pages.LcfAdminPage.DashboardPage;
import pages.LcfHomePage.SignButonsPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class US33 {
    AdminBodyPage adminBodyPage;
    DashboardPage dashboardPage;
    DashboardMedicinesPage dashboardMedicinesPage;

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
        dashboardPage = new DashboardPage();
        dashboardMedicinesPage = new DashboardMedicinesPage();
    }

    @AfterMethod
    public void tearDown() {
        Driver.quitDriver();
    }

    @Test
    public void US33_TC01_SidebarMedicinesModuluGorunurlukVeTiklanabilirlikTesti(){

        SoftAssert softAssert = new SoftAssert();

        ReusableMethods.hover(dashboardPage.dashboardPageSideBarMenu);

        dashboardMedicinesPage.sidebarMedicinesMainMenu.click();
        // Medicines kontrolü
        softAssert.assertTrue(dashboardMedicinesPage.sidebarMedicinesSubMenuLink.isDisplayed(),
                "HATA: Medicines alt menüsü görünmüyor!");
        softAssert.assertTrue(dashboardMedicinesPage.sidebarMedicinesSubMenuLink.isEnabled(),
                "HATA: Medicines alt menüsü tıklanabilir değil!");

        // Create medicines kontrolü
        softAssert.assertTrue(dashboardMedicinesPage.sidebarCreateMedicinesSubMenuLink.isDisplayed(),
                "HATA: Create Medicines alt menüsü görünmüyor!");
        softAssert.assertTrue(dashboardMedicinesPage.sidebarCreateMedicinesSubMenuLink.isEnabled(),
                "HATA: Create Medicines alt menüsü tıklanabilir değil!");

        // Tüm bulguları raporla
        softAssert.assertAll();
    }

    @Test
    public void US33_TC02_MedicinesSayfasiYonlendirmeVeURLDogrulamaTesti() {

        SoftAssert softAssert = new SoftAssert();

        // Sidebar'ı aç ve Medicines alt menüsüne tıkla
        ReusableMethods.hover(dashboardPage.dashboardPageSideBarMenu);
        dashboardMedicinesPage.sidebarMedicinesMainMenu.click();
        dashboardMedicinesPage.sidebarMedicinesSubMenuLink.click();

        // Beklenen ve gerçekleşen URL'i karşılaştırır
        String expectedUrlIcerek = "medicines";
        String actualUrl = Driver.getDriver().getCurrentUrl().toLowerCase();

        softAssert.assertTrue(actualUrl.contains(expectedUrlIcerek),
                "HATA/BUG: Medicines sayfasına tıklandığında URL '/Medicines' içermiyor! Mevcut URL: " + actualUrl);

        softAssert.assertAll();
    }

    @Test
    public void US33_TC03_IlacListesiAlfabetikSiralamaliMiTesti() {

        SoftAssert softAssert = new SoftAssert();

        ReusableMethods.hover(dashboardPage.dashboardPageSideBarMenu);
        dashboardMedicinesPage.sidebarMedicinesMainMenu.click();
        dashboardMedicinesPage.sidebarMedicinesSubMenuLink.click();

        // Tablodaki mevcut ilaç isimlerini bir String listesine dönüştürerek saklar
        List<String> actualMedicineNames = ReusableMethods.stringListeDondur(dashboardMedicinesPage.allMedicineTitles);
        // Karşılaştırma yapabilmek için mevcut listenin kopyasını oluşturur ve alfabetik (A-Z) sıralar
        List<String> expectedSortedMedicineNames = new ArrayList<>(actualMedicineNames);
        Collections.sort(expectedSortedMedicineNames);

        // Tablodaki orijinal sıralama ile olması gereken alfabetik sıralamayı karşılaştırır
        // Eğer sıralama yanlışsa test fail verir ve hata mesajını raporlar
        softAssert.assertEquals(actualMedicineNames, expectedSortedMedicineNames,
                "HATA/BUG: İlaç listesi tabloda alfabetik sırada değil!");

        softAssert.assertAll();
    }

    @Test
    public void US33_TC04_MedicinesAramaFonksiyonuDogrulamaTesti() {

        SoftAssert softAssert = new SoftAssert();

        // Medicines sayfasına gider
        Driver.getDriver().get(ConfigReader.getProperty("DasmedUrl"));

        // Arama kutusuna anahtar kelimeyi gönderir
        String searchKeyword = "dog v".toLowerCase();
        dashboardMedicinesPage.medicineSearchBox.sendKeys(searchKeyword);

        // Mevcut tüm başlıkları ve içerikleri listelere alır
        List<String> actualTitles = ReusableMethods.stringListeDondur(dashboardMedicinesPage.allMedicineTitles);
        List<String> actualContents = ReusableMethods.stringListeDondur(dashboardMedicinesPage.allMedicineContents);

        // Her bir satırı kontrol eder
        for (int i = 0; i < actualTitles.size(); i++) {
            String currentTitle = actualTitles.get(i).toLowerCase();
            String currentContent = actualContents.get(i).toLowerCase();

            // Aranan kelime ya başlıkta YA DA içerikte geçmelidir
            boolean isKeywordFound = currentTitle.contains(searchKeyword) || currentContent.contains(searchKeyword);

            softAssert.assertTrue(isKeywordFound,
                    "HATA/BUG: Arama sonucuyla eşleşmeyen bir kayıt bulundu! \n" +
                            "Aranan Kelime: " + searchKeyword + "\n" +
                            "Satır No: " + (i + 1) + "\n" +
                            "Başlık: " + currentTitle + "\n" +
                            "İçerik: " + currentContent);
        }

        softAssert.assertAll();
    }

    @Test
    public void US33_TC05_IlacDuzenlemeSayfasiErisimTesti() {

        SoftAssert softAssert = new SoftAssert();

        Driver.getDriver().get(ConfigReader.getProperty("DasmedUrl"));

        // Listelenen ilaçlardan ilkinin 'Edit' butonuna tıklar
        if (!dashboardMedicinesPage.allEditButtons.isEmpty()) {
            dashboardMedicinesPage.allEditButtons.get(0).click();
        } else {
            softAssert.fail("HATA: Düzenlenecek hiç ilaç bulunamadı, liste boş!");
        }

        // Edit sayfasının açıldığını URL kontrolü ile doğrular
        String expectedUrlIcerik = "edit";
        String actualUrl = Driver.getDriver().getCurrentUrl();

        softAssert.assertTrue(actualUrl.contains(expectedUrlIcerik),
                "HATA: Edit butonuna tıklandığında düzenleme sayfası açılmadı! \n" +
                        "Mevcut URL: " + actualUrl);

        softAssert.assertAll();

    }


}
