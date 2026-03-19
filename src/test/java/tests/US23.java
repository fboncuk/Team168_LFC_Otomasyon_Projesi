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
import pages.LcfAdminPage.DashboardRolesPage;
import pages.LcfHomePage.SignButonsPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

import java.time.Duration;

public class US23 {

    DashboardRolesPage dashboardRolesPage;
    SignButonsPage loginPage;
    WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        dashboardRolesPage = new DashboardRolesPage();
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
        jse.executeScript("arguments[0].click();", dashboardRolesPage.sidebarRolesMainMenu);
        ReusableMethods.bekle(2);
    }

    @Test(priority = 1, groups = "smoke", description = "SMOKE: Admin yan menüsünden Roles sayfasına erişim ve liste görünürlüğü")
    public void US23_TC01_RolesMenusuVeListeKontrolu() {
        SoftAssert softAssert = new SoftAssert();
        JavascriptExecutor jse = (JavascriptExecutor) Driver.getDriver();

        jse.executeScript("arguments[0].click();", dashboardRolesPage.sidebarRolesSubMenuLink);
        ReusableMethods.bekle(2);

        softAssert.assertTrue(dashboardRolesPage.roleListAdminText.isDisplayed(), "HATA: admin rolü listede yok!");
        softAssert.assertTrue(dashboardRolesPage.roleListDoctorsText.isDisplayed(), "HATA: doctors rolü listede yok!");
        softAssert.assertTrue(dashboardRolesPage.roleListSubscriberText.isDisplayed(), "HATA: Subscriber rolü listede yok!");
        softAssert.assertTrue(dashboardRolesPage.roleListSuperAdminText.isDisplayed(), "HATA: Super-Admin rolü listede yok!");
        softAssert.assertTrue(dashboardRolesPage.roleListUserText.isDisplayed(), "HATA: User rolü listede yok!");

        try {
            System.out.println("NOT: AC'de istenilen 'View' (Görüntüle) butonu sitede bulunmamaktadır!");
        } catch (Exception e) {}

        ReusableMethods.tumSayfaResimCek(Driver.getDriver(), "US23_TC01_Liste_Gorunumu");
        softAssert.assertAll();
    }

    @Test(priority = 2, groups = "regression", description = "REGRESSION: Roles sayfasında arama (Search) fonksiyonu kontrolü")
    public void US23_TC02_RolesAramaKutusuKontrolu() {
        SoftAssert softAssert = new SoftAssert();
        JavascriptExecutor jse = (JavascriptExecutor) Driver.getDriver();

        jse.executeScript("arguments[0].click();", dashboardRolesPage.sidebarRolesSubMenuLink);
        ReusableMethods.bekle(2);

        softAssert.assertTrue(dashboardRolesPage.rolesPageSearchBox.isDisplayed(), "HATA: Arama kutusu görünmüyor!");

        dashboardRolesPage.rolesPageSearchBox.clear();
        dashboardRolesPage.rolesPageSearchBox.sendKeys("admin");
        ReusableMethods.bekle(2);

        softAssert.assertTrue(dashboardRolesPage.roleListAdminText.isDisplayed(), "HATA: Arama filtrelemesi çalışmadı!");

        ReusableMethods.tarihliTumSayfaResimCek(Driver.getDriver(), "US23_TC02_Arama_Basarili");
        softAssert.assertAll();
    }

    @Test(priority = 3, groups = "regression", description = "REGRESSION: Rol silme (Delete) işlemi ve mesaj kontrolü")
    public void US23_TC03_RolesSilmeIslemiKontrolu() {
        SoftAssert softAssert = new SoftAssert();
        JavascriptExecutor jse = (JavascriptExecutor) Driver.getDriver();

        jse.executeScript("arguments[0].click();", dashboardRolesPage.sidebarRolesSubMenuLink);
        ReusableMethods.bekle(3);

        try {
            WebElement deleteButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("(//*[contains(@class, 'danger') or contains(text(), 'Delete') or contains(@class, 'remove')])[1]")));

            jse.executeScript("arguments[0].click();", deleteButton);
            ReusableMethods.bekle(2);

            System.out.println("BUG: AC'de istenen 'Silme onayı/uyarı penceresi' açılmadan, sistem direkt silmektedir!");

            softAssert.assertTrue(dashboardRolesPage.basariliSilindiMesaji.isDisplayed(), "HATA: Silme mesajı kutusu çıkmadı!");

            String expectedMesaj = "Role deleted successfully";
            String actualMesaj = dashboardRolesPage.basariliSilindiMesaji.getText();
            softAssert.assertEquals(actualMesaj, expectedMesaj, "HATA: Çıkan mesajın metni yanlış!");

            ReusableMethods.tarihliTumSayfaResimCek(Driver.getDriver(), "US23_TC03_Silme_Mesaji_Basarili_Ama_Uyari_Yok");

        } catch (Exception e) {
            softAssert.fail("BUG: Delete butonu bulunamadı veya silme işlemi gerçekleşmedi!");
            System.out.println("Hata detayı: " + e.getMessage());
        }

        softAssert.assertAll();
    }

    @AfterClass
    public void tearDown() {
        Driver.quitDriver();
    }
}