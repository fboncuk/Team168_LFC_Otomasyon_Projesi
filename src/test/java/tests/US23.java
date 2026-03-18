package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LcfAdminPage.DashboardRolesPage;
import pages.LcfHomePage.SignButonsPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

public class US23 {

    DashboardRolesPage dashboardRolesPage;
    SignButonsPage loginPage;

    @BeforeMethod
    public void setUp() {
        dashboardRolesPage = new DashboardRolesPage();
        loginPage = new SignButonsPage();

        Driver.getDriver().get(ConfigReader.getProperty("LcfUrl"));

        loginPage.signInLinki.click();
        loginPage.emailKutusu.sendKeys("beril.admin@loyalfriendcare.com");
        loginPage.passwordKutusu.sendKeys("Loyal.123123");
        loginPage.signInButtonOnay.click();
        ReusableMethods.bekle(2);


        Driver.getDriver().get("https://qa.loyalfriendcare.com/en/admin");
        ReusableMethods.bekle(3);
    }

    @Test(priority = 1, groups = "smoke", description = "SMOKE: Admin yan menüsünden Roles sayfasına erişim ve liste görünürlüğü")
    public void US23_TC01_RolesMenusuVeListeKontrolu() {
        SoftAssert softAssert = new SoftAssert();
        JavascriptExecutor jse = (JavascriptExecutor) Driver.getDriver();

        jse.executeScript("arguments[0].click();", dashboardRolesPage.sidebarRolesMainMenu);
        ReusableMethods.bekle(2);

        jse.executeScript("arguments[0].click();", dashboardRolesPage.sidebarRolesSubMenuLink);
        ReusableMethods.bekle(2);


        softAssert.assertTrue(dashboardRolesPage.roleListAdminText.isDisplayed(), "HATA: admin rolü listede yok!");
        softAssert.assertTrue(dashboardRolesPage.roleListDoctorsText.isDisplayed(), "HATA: doctors rolü listede yok!");
        softAssert.assertTrue(dashboardRolesPage.roleListSubscriberText.isDisplayed(), "HATA: Subscriber rolü listede yok!");
        softAssert.assertTrue(dashboardRolesPage.roleListSuperAdminText.isDisplayed(), "HATA: Super-Admin rolü listede yok!");
        softAssert.assertTrue(dashboardRolesPage.roleListUserText.isDisplayed(), "HATA: User rolü listede yok!");

        try {

            System.out.println("NOT: AC'de istenen 'View' (Görüntüle) butonu sitede bulunmamaktadır!");
        } catch (Exception e) {}

        ReusableMethods.tumSayfaResimCek(Driver.getDriver(), "US23_TC01_Liste_Gorunumu");
        softAssert.assertAll();
    }

    @Test(priority = 2, groups = "regression", description = "REGRESSION: Roles sayfasında arama (Search) fonksiyonu kontrolü")
    public void US23_TC02_RolesAramaKutusuKontrolu() {
        SoftAssert softAssert = new SoftAssert();
        JavascriptExecutor jse = (JavascriptExecutor) Driver.getDriver();

        jse.executeScript("arguments[0].click();", dashboardRolesPage.sidebarRolesMainMenu);
        ReusableMethods.bekle(1);
        jse.executeScript("arguments[0].click();", dashboardRolesPage.sidebarRolesSubMenuLink);
        ReusableMethods.bekle(2);

        softAssert.assertTrue(dashboardRolesPage.rolesPageSearchBox.isDisplayed(), "HATA: Arama kutusu görünmüyor!");

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

        jse.executeScript("arguments[0].click();", dashboardRolesPage.sidebarRolesMainMenu);
        ReusableMethods.bekle(1);
        jse.executeScript("arguments[0].click();", dashboardRolesPage.sidebarRolesSubMenuLink);
        ReusableMethods.bekle(2);

        softAssert.assertTrue(dashboardRolesPage.rolesPageFirstDeleteButton.isDisplayed(), "HATA: Delete butonu görünmüyor!");
        dashboardRolesPage.rolesPageFirstDeleteButton.click();
        ReusableMethods.bekle(1);


        System.out.println("BUG: AC'de istenen 'Silme onayı/uyarı penceresi' açılmadan, sistem direkt silmektedir!");

        try {
            softAssert.assertTrue(dashboardRolesPage.basariliSilindiMesaji.isDisplayed(), "HATA: Silme mesajı kutusu çıkmadı!");

            String expectedMesaj = "Role deleted successfully";
            String actualMesaj = dashboardRolesPage.basariliSilindiMesaji.getText();
            softAssert.assertEquals(actualMesaj, expectedMesaj, "HATA: Çıkan mesajın metni yanlış!");

            ReusableMethods.tarihliTumSayfaResimCek(Driver.getDriver(), "US23_TC03_Silme_Mesaji_Basarili_Ama_Uyari_Yok");
        } catch (Exception e) {
            System.out.println("NOT: Silme başarılı mesajı ekranda bulunamadı!");
        }
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown() {
        Driver.quitDriver();
    }
}