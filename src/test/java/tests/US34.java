package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LcfAdminPage.DashboardMedicinesPage;
import pages.LcfHomePage.SignButonsPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

import java.time.Duration;

public class US34 {

    DashboardMedicinesPage dashboardMedicinesPage;
    SignButonsPage loginPage;
    WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        dashboardMedicinesPage = new DashboardMedicinesPage();
        loginPage = new SignButonsPage();
        wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(15));

        Driver.getDriver().get(ConfigReader.getProperty("LfcUrl"));

        loginPage.signInLinki.click();
        loginPage.emailKutusu.sendKeys(ConfigReader.getProperty("T01AdminMail"));
        loginPage.passwordKutusu.sendKeys(ConfigReader.getProperty("T01AdminPassword"));
        loginPage.signInButtonOnay.click();
        ReusableMethods.bekle(2);

        Driver.getDriver().get("https://qa.loyalfriendcare.com/en/admin");
        ReusableMethods.bekle(3);

        JavascriptExecutor jse = (JavascriptExecutor) Driver.getDriver();
        jse.executeScript("arguments[0].click();", dashboardMedicinesPage.sidebarMedicinesMainMenu);
        ReusableMethods.bekle(2);
    }

    @Test(priority = 1, groups = "smoke", description = "Menü, Buton ve Form Görünürlüğü")
    public void US34_TC01_MedicinesMenuVeFormKontrolu() {
        SoftAssert softAssert = new SoftAssert();
        JavascriptExecutor jse = (JavascriptExecutor) Driver.getDriver();

        jse.executeScript("arguments[0].click();", dashboardMedicinesPage.sidebarMedicinesSubMenuLink);
        ReusableMethods.bekle(3);

        try {
            WebElement addBtn = wait.until(ExpectedConditions.visibilityOf(dashboardMedicinesPage.addMedicinesButtonOnPage));
            softAssert.assertTrue(addBtn.isDisplayed(), "HATA: Add Medicines butonu görünmüyor!");
            softAssert.assertTrue(addBtn.isEnabled(), "HATA: Add Medicines butonu aktif değil!");

            jse.executeScript("arguments[0].click();", addBtn);
            ReusableMethods.bekle(3);
        } catch (Exception e) {
            softAssert.fail("BUG: Add Medicines butonu ekranda bulunamadı veya tıklanamadı!");
        }

        try {
            WebElement titleInput = wait.until(ExpectedConditions.visibilityOf(dashboardMedicinesPage.medicinesPageTitleInput));
            softAssert.assertTrue(titleInput.isDisplayed(), "HATA: İlaç adı kutusu görünmüyor!");

            WebElement contentInput = wait.until(ExpectedConditions.visibilityOf(dashboardMedicinesPage.medicinesPageContentTextMessage));
            softAssert.assertTrue(contentInput.isDisplayed(), "HATA: İlaç içeriği kutusu görünmüyor!");

            WebElement dropBox = wait.until(ExpectedConditions.visibilityOf(dashboardMedicinesPage.medicinesPageFileDropBox));
            softAssert.assertTrue(dropBox.isDisplayed(), "HATA: Resim yükleme alanı (DropBox) görünmüyor!");

            WebElement saveBtn = wait.until(ExpectedConditions.visibilityOf(dashboardMedicinesPage.medicinesPageSaveButton));
            softAssert.assertTrue(saveBtn.isDisplayed(), "HATA: Save butonu görünmüyor!");
        } catch (Exception e) {
            softAssert.fail("BUG: Form elementleri yüklenemedi veya bulunamadı!");
        }

        ReusableMethods.tumSayfaResimCek(Driver.getDriver(), "US34_TC01_Form_Ekrani");
        softAssert.assertAll();
    }

    @Test(priority = 2, groups = "regression", description = "Zorunlu alanlar boş bırakılarak kayıt yapılamaması")
    public void US34_TC02_ZorunluAlanlarBosKayitKontrolu() {
        SoftAssert softAssert = new SoftAssert();
        JavascriptExecutor jse = (JavascriptExecutor) Driver.getDriver();

        jse.executeScript("arguments[0].click();", dashboardMedicinesPage.sidebarCreateMedicinesSubMenuLink);
        ReusableMethods.bekle(3);

        jse.executeScript("arguments[0].click();", dashboardMedicinesPage.medicinesPageSaveButton);
        ReusableMethods.bekle(3);

        String currentUrl = Driver.getDriver().getCurrentUrl();
        softAssert.assertTrue(currentUrl.contains("create") || currentUrl.contains("add"), "BUG: Zorunlu alanlar boşken sistem kaydetmeye çalıştı!");

        System.out.println("NOT: AC-3 uyarı mesajı (Lütfen bu alanı doldurun vs.) manuel olarak ekrandan da teyit edilmelidir.");

        ReusableMethods.tarihliTumSayfaResimCek(Driver.getDriver(), "US34_TC02_Bos_Kayit_Kontrol");
        softAssert.assertAll();
    }

    @Test(priority = 3, groups = "regression", description = "Geçerli veri ile kayıt ve listede arama")
    public void US34_TC03_GecerliVerilerleIlacEkleme() {
        SoftAssert softAssert = new SoftAssert();
        JavascriptExecutor jse = (JavascriptExecutor) Driver.getDriver();

        jse.executeScript("arguments[0].click();", dashboardMedicinesPage.sidebarCreateMedicinesSubMenuLink);
        ReusableMethods.bekle(3);

        String testIlacAdi = "Test QA Ilaci " + System.currentTimeMillis();
        String testIcerik = "Bu bir test icerigidir.";

        dashboardMedicinesPage.medicinesPageTitleInput.sendKeys(testIlacAdi);
        dashboardMedicinesPage.medicinesPageContentTextMessage.sendKeys(testIcerik);
        jse.executeScript("arguments[0].click();", dashboardMedicinesPage.medicinesPageSaveButton);

        try {
            WebElement successMessage = wait.until(ExpectedConditions.visibilityOf(dashboardMedicinesPage.basariliEklendiMesaji));
            softAssert.assertTrue(successMessage.isDisplayed(), "HATA: Başarılı eklendi mesajı çıkmadı!");
        } catch (Exception e) {
            System.out.println("NOT: Başarılı mesajı ekranda yakalanamadı.");
        }

        ReusableMethods.bekle(3);

        jse.executeScript("arguments[0].click();", dashboardMedicinesPage.sidebarMedicinesSubMenuLink);
        ReusableMethods.bekle(3);


        dashboardMedicinesPage.medicineSearchBox.clear();
        dashboardMedicinesPage.medicineSearchBox.sendKeys(testIlacAdi);
        ReusableMethods.bekle(3);

        boolean ilacBulunduMu = false;


        for (int i = 1; i <= 5; i++) {
            try {
                WebElement tabloGovdesi = Driver.getDriver().findElement(By.xpath("//tbody"));
                if (tabloGovdesi.getText().contains(testIlacAdi)) {
                    ilacBulunduMu = true;
                    System.out.println("İlaç " + i + ". sayfada bulundu!");
                    break;
                }

                System.out.println("İlaç " + i + ". sayfada yok, diğer sayfaya geçiliyor...");
                WebElement nextButton = Driver.getDriver().findElement(By.xpath("//a[contains(text(), 'Next') or contains(text(), 'Sonraki') or contains(@class, 'next')]"));
                jse.executeScript("arguments[0].click();", nextButton);
                ReusableMethods.bekle(2);
            } catch (Exception e) {
                System.out.println("Tablonun sonuna ulaşıldı.");
                break;
            }
        }

        softAssert.assertTrue(ilacBulunduMu, "HATA: Yeni eklenen ilaç listede hiçbir sayfada bulunamadı!");

        ReusableMethods.tarihliTumSayfaResimCek(Driver.getDriver(), "US34_TC03_Basarili_Kayit_Listesi");
        softAssert.assertAll();
    }


    @AfterClass
    public void tearDown() {
        Driver.quitDriver();
    }
}