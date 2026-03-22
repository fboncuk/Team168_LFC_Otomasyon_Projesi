package scenarios;

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
import pages.LcfAdminPage.DashboardRolesPage;
import pages.LcfHomePage.SignButonsPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.Listeners;
import utilities.ReusableMethods;

import java.time.Duration;

@org.testng.annotations.Listeners(Listeners.class)
public class SunumSenaryosu_04 {

    DashboardRolesPage dashboardRolesPage;
    DashboardMedicinesPage dashboardMedicinesPage;
    SignButonsPage signButonsPage;
    WebDriverWait wait;
    SoftAssert softAssert;
    JavascriptExecutor jse;

    @BeforeClass
    public void setup() {
        dashboardRolesPage = new DashboardRolesPage();
        dashboardMedicinesPage = new DashboardMedicinesPage();
        signButonsPage = new SignButonsPage();
        softAssert = new SoftAssert();
        wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(15));
        jse = (JavascriptExecutor) Driver.getDriver();

        Driver.getDriver().get(ConfigReader.getProperty("LfcUrl"));
        ReusableMethods.bekle(3);

        signButonsPage.signInLinki.click();
        ReusableMethods.bekle(2);
        signButonsPage.emailKutusu.sendKeys(ConfigReader.getProperty("T01AdminMail"));
        ReusableMethods.bekle(1);
        signButonsPage.passwordKutusu.sendKeys(ConfigReader.getProperty("T01AdminPassword"));
        ReusableMethods.bekle(1);
        signButonsPage.signInButtonOnay.click();

        ReusableMethods.bekle(4);
        Driver.getDriver().get("https://qa.loyalfriendcare.com/en/admin");
        ReusableMethods.bekle(4);
    }

    @Test(priority = 1)
    public void US34_TC01_GorunurlukKontroluVeBosKayitEngeli() {
        jse.executeScript("arguments[0].click();", dashboardMedicinesPage.sidebarMedicinesMainMenu);
        ReusableMethods.bekle(2);
        jse.executeScript("arguments[0].click();", dashboardMedicinesPage.sidebarCreateMedicinesSubMenuLink);
        ReusableMethods.bekle(4);

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

        jse.executeScript("arguments[0].click();", dashboardMedicinesPage.medicinesPageSaveButton);
        ReusableMethods.bekle(5);

        String currentUrl = Driver.getDriver().getCurrentUrl();
        softAssert.assertTrue(currentUrl.contains("create") || currentUrl.contains("add"),
                "HATA: Zorunlu alanlar boş bırakılmasına rağmen kayıt deneme hatası.");

        ReusableMethods.tarihliTumSayfaResimCek(Driver.getDriver(), "Senaryo_US34_Bos_Kayit_Engeli");

        Driver.getDriver().navigate().refresh();
        ReusableMethods.bekle(2);
    }

    @Test(priority = 2)
    public void US34_TC02_GecerliIlacEklemeVeTabloTeyidiPozitifTesti() {
        String testIlacAdi = "Karma Kedi Aşısı";
        String testIcerik = "Yavru ve yetişkin kediler için yeni dönem karma aşı";

        dashboardMedicinesPage.medicinesPageTitleInput.sendKeys(testIlacAdi);
        ReusableMethods.bekle(2);
        dashboardMedicinesPage.medicinesPageContentTextMessage.sendKeys(testIcerik);
        ReusableMethods.bekle(2);

        jse.executeScript("arguments[0].click();", dashboardMedicinesPage.medicinesPageSaveButton);
        ReusableMethods.bekle(4);

        jse.executeScript("arguments[0].click();", dashboardMedicinesPage.sidebarMedicinesSubMenuLink);
        ReusableMethods.bekle(4);

        boolean ilacBulunduMu = false;


        for (int i = 1; i <= 5; i++) {
            try {

                dashboardMedicinesPage.medicineSearchBox.clear();
                ReusableMethods.bekle(1);
                dashboardMedicinesPage.medicineSearchBox.sendKeys(testIlacAdi);


                ReusableMethods.bekle(4);

                WebElement tableBody = Driver.getDriver().findElement(By.xpath("//tbody"));
                if (tableBody.getText().contains(testIlacAdi)) {
                    ilacBulunduMu = true;

                    ReusableMethods.bekle(4);
                    break;
                }

                WebElement nextButton = Driver.getDriver().findElement(By.xpath("//a[contains(text(), 'Next') or contains(text(), 'Sonraki') or contains(@class, 'next')]"));
                jse.executeScript("arguments[0].click();", nextButton);


                ReusableMethods.bekle(3);
            } catch (Exception e) { break; }
        }

        softAssert.assertTrue(ilacBulunduMu, "HATA: Yeni eklenen ilaç listede hiçbir sayfada bulunamadı!");
        ReusableMethods.tarihliTumSayfaResimCek(Driver.getDriver(), "Senaryo_US34_Ilac_Eklendi");
    }

    @Test(priority = 3)
    public void US23_TC03_RolGorunurlukAramaVeSilmeBugRaporu() {
        ReusableMethods.bekle(16);
        jse.executeScript("arguments[0].click();", dashboardRolesPage.sidebarRolesMainMenu);
        ReusableMethods.bekle(2);
        jse.executeScript("arguments[0].click();", dashboardRolesPage.sidebarRolesSubMenuLink);
        ReusableMethods.bekle(5);

        softAssert.assertTrue(dashboardRolesPage.roleListAdminText.isDisplayed(), "HATA: admin rolü listede yok!");
        softAssert.assertTrue(dashboardRolesPage.roleListDoctorsText.isDisplayed(), "HATA: doctors rolü listede yok!");
        softAssert.assertTrue(dashboardRolesPage.roleListSubscriberText.isDisplayed(), "HATA: Subscriber rolü listede yok!");
        softAssert.assertTrue(dashboardRolesPage.roleListSuperAdminText.isDisplayed(), "HATA: Super-Admin rolü listede yok!");
        softAssert.assertTrue(dashboardRolesPage.roleListUserText.isDisplayed(), "HATA: User rolü listede yok!");

        try {
            System.out.println("NOT: AC'de istenilen 'View' (Görüntüle) butonu sitede bulunmamaktadır!");
        } catch (Exception e) {}

        dashboardRolesPage.rolesPageSearchBox.clear();
        dashboardRolesPage.rolesPageSearchBox.sendKeys("Dönemlik Stajyer");
        ReusableMethods.bekle(6);

        softAssert.assertTrue(dashboardRolesPage.rolesPageSearchBox.isDisplayed(), "HATA: Arama kutusu görünmüyor!");

        try {
            WebElement deleteButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("(//*[contains(@class, 'danger') or contains(text(), 'Delete') or contains(@class, 'remove')])[1]")));

            jse.executeScript("arguments[0].click();", deleteButton);
            ReusableMethods.bekle(6);

            boolean alertCiktiMi = false;
            try {
                Driver.getDriver().switchTo().alert();
                alertCiktiMi = true;
            } catch (Exception noAlert) {
                System.out.println("BUG: AC'de istenen 'Silme onayı/uyarı penceresi' açılmadan, sistem direkt silmektedir!");
            }

            softAssert.assertTrue(alertCiktiMi, "BUG: Rol silme işleminde güvenlik (Alert) uyarı penceresinin çıkmaması hatası!");

            try {
                softAssert.assertTrue(dashboardRolesPage.basariliSilindiMesaji.isDisplayed(), "HATA: Silme mesajı kutusu çıkmadı!");
            } catch (Exception e) {}

            ReusableMethods.tarihliTumSayfaResimCek(Driver.getDriver(), "Senaryo_US23_Rol_Silme_Uyari_Eksikligi");

        } catch (Exception e) {
            softAssert.fail("HATA: Sayfada aratılan rol bulunamadı (Pre-condition eksikliği).");
        }

        softAssert.assertAll();
    }

    @AfterClass
    public void tearDown() {
        ReusableMethods.bekle(6);
        Driver.quitDriver();
    }
}