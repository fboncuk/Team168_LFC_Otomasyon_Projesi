package scenarios;

import com.github.javafaker.Faker;;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.LcfAdminPage.AdminBodyPage;
import pages.LcfAdminPage.DashboardPage;
import pages.LcfHomePage.SignButonsPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;
import utilities.TestBaseRapor;

import java.time.Duration;

public class SunumSenaryosu_Admin_Panel extends TestBaseRapor {

    //Class level'da Obje oluştur
    SignButonsPage signButonsPage;
    SoftAssert softAssert;
    WebDriverWait wait;
    Faker faker;
    DashboardPage dashboardPage =new DashboardPage();
    AdminBodyPage adminBodyPage =new AdminBodyPage();

    @BeforeClass
    public void setup() {
        signButonsPage = new SignButonsPage();
        dashboardPage = new DashboardPage();
        Driver.getDriver().get(ConfigReader.getProperty("LoginUrl"));
        wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));

    }

    @BeforeMethod
    public void beforeMethod() {
        softAssert = new SoftAssert();
        faker = new Faker();
        wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
    }

    @AfterClass
    public void teardown() {

        Driver.quitDriver();

    }

    @Test(priority = 19, description = "Login sayfasında dashboarda erişim öncesi homepage erişim sağlanması")
    public void US20_TC05_Admin_Account_SignIn_to_Admin_Dashboard_Test() {
        extentTest = extentReports.createTest("US20_TC05_Admin_Account_SignIn_to_Admin_Dashboard_Test");
        Driver.getDriver().get(ConfigReader.getProperty("LoginUrl"));

        //Email box temizle ve Geçerli admin mail adresi gir
        signButonsPage.emailKutusu.clear();
        signButonsPage.emailKutusu.sendKeys(ConfigReader.getProperty("T09AdminMail"));

        //Password box temizle ve admin geçerli şifreyi gir
        signButonsPage.passwordKutusu.clear();
        signButonsPage.passwordKutusu.sendKeys(ConfigReader.getProperty("T09AdminPassword"));

        //Sign in butonuna bas
        //wait.until(ExpectedConditions.elementToBeClickable(signButonsPage.signInButtonOnay)).click();
        signButonsPage.signInButtonOnay.click();

        // Login sonrası URL bekle
        String expectedHomeUrl = ConfigReader.getProperty("LfcUrl");
//        wait.until(ExpectedConditions.urlContains(expectedHomeUrl));

        String actualHomeUrl = Driver.getDriver().getCurrentUrl().toLowerCase();
        softAssert.assertTrue(actualHomeUrl.toLowerCase().contains(expectedHomeUrl), "Login To Homepage Failed");

        ReusableMethods.tarihliTumSayfaResimCek(Driver.getDriver(), "US20_TC05_Admin_Account_SignIn_to_Homepage_Test");

        softAssert.assertAll();
    }

    @Test(priority = 20, description = "Homepage sayfasından  admin dashboarda erişim sağlanması")
    public void US20_TC05_Admin_Account_SignIn_to_Homepage_Test() {
        extentTest = extentReports.createTest("S20_TC05_Admin_Account_SignIn_to_Homepage_Test");

        // Dashboard butonu görünür olana kadar bekle
        //wait.until(ExpectedConditions.visibilityOf(signButonsPage.headerUserName));
        //wait.until(ExpectedConditions.elementToBeClickable(signButonsPage.headerUserName)).click();
        signButonsPage.headerUserName.click();
        ReusableMethods.bekle(5);

        // Dashboard URL bekle
        String expectedDashboardUrl = ConfigReader.getProperty("DasUrl").toLowerCase();
        //wait.until(ExpectedConditions.urlContains(expectedDashboardUrl));

        String actualDashboardUrl = Driver.getDriver().getCurrentUrl().toLowerCase();
        softAssert.assertEquals(actualDashboardUrl, expectedDashboardUrl.toLowerCase(), "Login To Dashboard Failed");

        softAssert.assertAll();

    }

    @Test(priority = 21, description = "Dashboard panelinde menü öğelerine erişildiğini doğrulanması (Funcional Test)")
    public void US20_TC06_Admin_Dashboard_Sol_Menu_Ogeler_Test() {
        extentTest = extentReports.createTest("US20_TC06_Admin_Dashboard_Sol_Menu_Ogeler_Test");

        //Sol Menü açılmasını sağla
        ReusableMethods.hover(dashboardPage.dashboardPageSideBarMenu);

        //sol menüdeki dashboard menü görünürlüğü ve tıklanabilirliği
        softAssert.assertTrue(dashboardPage.DashboardPageLeftMenuDashboardLink.isDisplayed());
        softAssert.assertTrue(dashboardPage.DashboardPageLeftMenuDashboardLink.isEnabled());

        extentTest = extentReports.createTest("sol menüdeki Roles kısmı görünür testi");

        //sol menüdeki Roles kısmı görünür, tıklanır mı?
        softAssert.assertTrue(dashboardPage.DashboardPageLeftMenuRolesLink.isDisplayed());
        softAssert.assertTrue(dashboardPage.DashboardPageLeftMenuRolesLink.isEnabled());
        dashboardPage.DashboardPageLeftMenuRolesLink.click();
        ReusableMethods.bekle(2);

        //sol menüdeki Roles altındaki Roles kısmı gürünür ve tıklanabilir mi?
        softAssert.assertTrue(dashboardPage.DashboardPageLeftMenuSubmenuRoles.isDisplayed(), "Görünür değil");
        softAssert.assertTrue(dashboardPage.DashboardPageLeftMenuSubmenuRoles.isEnabled(), "Tıklanır değil");
        wait.until(ExpectedConditions.elementToBeClickable(dashboardPage.DashboardPageLeftMenuSubmenuRoles)).click();
        ReusableMethods.bekle(5);
        Driver.getDriver().navigate().back();


        //Sol menü Roles Create kısmı görünür ve tıklanabilir mi?
        ReusableMethods.hover(dashboardPage.dashboardPageSideBarMenu);
        softAssert.assertTrue(dashboardPage.DashboardPageLeftMenuSubmenuCreateRole.isDisplayed(), "Görünür değil");
        softAssert.assertTrue(dashboardPage.DashboardPageLeftMenuSubmenuCreateRole.isEnabled(), "Tıklanır Değil");
        wait.until(ExpectedConditions.elementToBeClickable(dashboardPage.DashboardPageLeftMenuSubmenuCreateRole)).click();
        ReusableMethods.bekle(5);
        Driver.getDriver().navigate().back();

        extentTest = extentReports.createTest("sol menüdeki User kısmı testi");
        //## USER BOLUMU ICIN
        //1- Sol menü Users kısmı görünür ve tıklanabilir mi?
        ReusableMethods.hover(dashboardPage.dashboardPageSideBarMenu);
        softAssert.assertTrue(dashboardPage.DashboardPageLeftMenuUsersLink.isDisplayed(), "Görünür değil");
        softAssert.assertTrue(dashboardPage.DashboardPageLeftMenuUsersLink.isEnabled(), "Tıklanır Değil");
        wait.until(ExpectedConditions.elementToBeClickable(dashboardPage.DashboardPageLeftMenuUsersLink)).click();


        //2- sol menüdeki Users altındaki User kısmı gürünür ve tıklanabilir mi?
        softAssert.assertTrue(dashboardPage.DashboardPageLeftMenuSubmenuUsers.isDisplayed(), "Görünür değil");
        softAssert.assertTrue(dashboardPage.DashboardPageLeftMenuSubmenuUsers.isEnabled(), "Tıklanır değil");

        wait.until(ExpectedConditions.elementToBeClickable(dashboardPage.DashboardPageLeftMenuSubmenuUsers)).click();
        ReusableMethods.bekle(5);
        Driver.getDriver().navigate().back();

        //3- sol menüdeki Users altındaki Users Create kısmı gürünür ve tıklanabilir mi
        softAssert.assertTrue(dashboardPage.DashboardPageLeftMenuSubmenuCreateUser.isDisplayed(), "Görünür değil");
        softAssert.assertTrue(dashboardPage.DashboardPageLeftMenuSubmenuCreateUser.isEnabled(), "Tıklanır değil");
//        wait.until(ExpectedConditions.elementToBeClickable(
                dashboardPage.DashboardPageLeftMenuSubmenuCreateUser
//        ))
                .click();
        ReusableMethods.bekle(5);
        Driver.getDriver().navigate().back();


        extentTest = extentReports.createTest("sol menüdeki Bed Manager kısmı testi");
        //## Bed Managers BOLUMU ICIN
        //1- Sol menü Bed Managers kısmı görünür ve tıklanabilir mi?
        ReusableMethods.hover(dashboardPage.dashboardPageSideBarMenu);
        softAssert.assertTrue(dashboardPage.DashboardPageLeftMenuBedManagersLink.isDisplayed(), "Görünür değil");
        softAssert.assertTrue(dashboardPage.DashboardPageLeftMenuBedManagersLink.isEnabled(), "Tıklanır Değil");
//        wait.until(
//                ExpectedConditions.elementToBeClickable(
        dashboardPage.DashboardPageLeftMenuBedManagersLink
//                        ))
                .click();

        //2- sol menüdeki Bed Managers altındaki Bed Managers kısmı gürünür ve tıklanabilir mi?
        softAssert.assertTrue(dashboardPage.DashboardPageLeftMenuSubmenuBedManagers.isDisplayed(), "Görünür değil");
        softAssert.assertTrue(dashboardPage.DashboardPageLeftMenuSubmenuBedManagers.isEnabled(), "Tıklanır değil");

        wait.until(ExpectedConditions.elementToBeClickable(dashboardPage.DashboardPageLeftMenuSubmenuBedManagers)).click();
        ReusableMethods.bekle(7);
        Driver.getDriver().navigate().back();

        //3- sol menüdeki Bed Managers altındaki Create Bed Managers kısmı gürünür ve tıklanabilir mi
        softAssert.assertTrue(dashboardPage.DashboardPageLeftMenuSubmenuCreateBedManagers.isDisplayed(), "Görünür değil");
        softAssert.assertTrue(dashboardPage.DashboardPageLeftMenuSubmenuCreateBedManagers.isEnabled(), "Tıklanır değil");
        wait.until(ExpectedConditions.elementToBeClickable(dashboardPage.DashboardPageLeftMenuSubmenuCreateBedManagers)).click();
        ReusableMethods.bekle(7);
        Driver.getDriver().navigate().back();

        extentTest = extentReports.createTest("sol menüdeki Departmente kısmı testi");
        //## Departments BOLUMU ICIN
        //1- Sol menü Departments kısmı görünür ve tıklanabilir mi?
        ReusableMethods.hover(dashboardPage.dashboardPageSideBarMenu);
        softAssert.assertTrue(dashboardPage.DashboardPageLeftMenuDepartmentsLink.isDisplayed(), "Görünür değil");
        softAssert.assertTrue(dashboardPage.DashboardPageLeftMenuDepartmentsLink.isEnabled(), "Tıklanır Değil");
        wait.until(ExpectedConditions.elementToBeClickable(dashboardPage.DashboardPageLeftMenuDepartmentsLink)).click();

        //2- sol menüdeki Departments altındaki Departments kısmı gürünür ve tıklanabilir mi?
        softAssert.assertTrue(dashboardPage.DashboardPageLeftMenuSubmenuDepartments.isDisplayed(), "Görünür değil");
        softAssert.assertTrue(dashboardPage.DashboardPageLeftMenuSubmenuDepartments.isEnabled(), "Tıklanır değil");

        wait.until(ExpectedConditions.elementToBeClickable(dashboardPage.DashboardPageLeftMenuSubmenuDepartments)).click();
        ReusableMethods.bekle(7);
        Driver.getDriver().navigate().back();

        //3- sol menüdeki Departments altındaki Create Departments kısmı gürünür ve tıklanabilir mi
        softAssert.assertTrue(dashboardPage.DashboardPageLeftMenuSubmenuCreateDepartments.isDisplayed(), "Görünür değil");
        softAssert.assertTrue(dashboardPage.DashboardPageLeftMenuSubmenuCreateDepartments.isEnabled(), "Tıklanır değil");
        wait.until(ExpectedConditions.elementToBeClickable(dashboardPage.DashboardPageLeftMenuSubmenuCreateDepartments)).click();
        ReusableMethods.bekle(7);
        Driver.getDriver().navigate().back();

        extentTest = extentReports.createTest("sol menüdeki Doctors kısmı testi");
        //## Doctors BOLUMU ICIN
        //1- Sol menü Doctors kısmı görünür ve tıklanabilir mi?
        ReusableMethods.hover(dashboardPage.dashboardPageSideBarMenu);
        softAssert.assertTrue(dashboardPage.DashboardPageLeftMenuDoctorsLink.isDisplayed(), "Görünür değil");
        softAssert.assertTrue(dashboardPage.DashboardPageLeftMenuDoctorsLink.isEnabled(), "Tıklanır Değil");
        wait.until(ExpectedConditions.elementToBeClickable(dashboardPage.DashboardPageLeftMenuDoctorsLink)).click();

        //2- sol menüdeki Doctors altındaki Doctors kısmı gürünür ve tıklanabilir mi?
        softAssert.assertTrue(dashboardPage.DashboardPageLeftMenuSubmenuDoctors.isDisplayed(), "Görünür değil");
        softAssert.assertTrue(dashboardPage.DashboardPageLeftMenuSubmenuDoctors.isEnabled(), "Tıklanır değil");

        wait.until(ExpectedConditions.elementToBeClickable(dashboardPage.DashboardPageLeftMenuSubmenuDoctors)).click();
        ReusableMethods.bekle(7);
        Driver.getDriver().navigate().back();

        //3- sol menüdeki Doctors altındaki Create Doctors kısmı gürünür ve tıklanabilir mi
        softAssert.assertTrue(dashboardPage.DashboardPageLeftMenuSubmenuCreateDoctors.isDisplayed(), "Görünür değil");
        softAssert.assertTrue(dashboardPage.DashboardPageLeftMenuSubmenuCreateDoctors.isEnabled(), "Tıklanır değil");
        wait.until(ExpectedConditions.elementToBeClickable(dashboardPage.DashboardPageLeftMenuSubmenuCreateDoctors)).click();
        ReusableMethods.bekle(7);
        Driver.getDriver().navigate().back();

        extentTest = extentReports.createTest("sol menüdeki Medicine kısmı testi");
        //## Medicines BOLUMU ICIN
        //1- Sol menü Medicines kısmı görünür ve tıklanabilir mi?
        ReusableMethods.hover(dashboardPage.dashboardPageSideBarMenu);
        softAssert.assertTrue(dashboardPage.DashboardPageLeftMenuMedicinesLink.isDisplayed(), "Görünür değil");
        softAssert.assertTrue(dashboardPage.DashboardPageLeftMenuMedicinesLink.isEnabled(), "Tıklanır Değil");
        wait.until(ExpectedConditions.elementToBeClickable(dashboardPage.DashboardPageLeftMenuMedicinesLink)).click();

        //2- sol menüdeki Medicines altındaki Mediciens kısmı gürünür ve tıklanabilir mi?
        softAssert.assertTrue(dashboardPage.DashboardPageLeftMenuSubmenuMedicines.isDisplayed(), "Görünür değil");
        softAssert.assertTrue(dashboardPage.DashboardPageLeftMenuSubmenuMedicines.isEnabled(), "Tıklanır değil");

        wait.until(ExpectedConditions.elementToBeClickable(dashboardPage.DashboardPageLeftMenuSubmenuMedicines)).click();
        ReusableMethods.bekle(7);
        Driver.getDriver().navigate().back();

        //3- sol menüdeki Medicines altındaki Create Medicines kısmı gürünür ve tıklanabilir mi
        softAssert.assertTrue(dashboardPage.DashboardPageLeftMenuSubmenuCreateMedicines.isDisplayed(), "Görünür değil");
        softAssert.assertTrue(dashboardPage.DashboardPageLeftMenuSubmenuCreateMedicines.isEnabled(), "Tıklanır değil");
        wait.until(ExpectedConditions.elementToBeClickable(dashboardPage.DashboardPageLeftMenuSubmenuCreateMedicines)).click();
        ReusableMethods.bekle(7);
        Driver.getDriver().navigate().back();

        extentTest = extentReports.createTest("sol menüdeki Pet Adsense kısmı testi");
        //## Pet Adsense BOLUMU ICIN
        //1- Sol menü Pet Adsense kısmı görünür ve tıklanabilir mi?
        ReusableMethods.hover(dashboardPage.dashboardPageSideBarMenu);
        softAssert.assertTrue(dashboardPage.DashboardPageLeftMenuPetAdsenseLink.isDisplayed(), "Görünür değil");
        softAssert.assertTrue(dashboardPage.DashboardPageLeftMenuPetAdsenseLink.isEnabled(), "Tıklanır Değil");
        wait.until(ExpectedConditions.elementToBeClickable(dashboardPage.DashboardPageLeftMenuPetAdsenseLink)).click();

        //2- sol menüdeki Pet Adsense altındaki Mediciens kısmı gürünür ve tıklanabilir mi?
        softAssert.assertTrue(dashboardPage.DashboardPageLeftMenuSubmenuPetsAdsense.isDisplayed(), "Görünür değil");
        softAssert.assertTrue(dashboardPage.DashboardPageLeftMenuSubmenuPetsAdsense.isEnabled(), "Tıklanır değil");

        wait.until(ExpectedConditions.elementToBeClickable(dashboardPage.DashboardPageLeftMenuSubmenuPetsAdsense)).click();
        ReusableMethods.bekle(7);
        Driver.getDriver().navigate().back();

        //3- sol menüdeki Pet Adsense altındaki Create Pet Adsense kısmı gürünür ve tıklanabilir mi
        softAssert.assertTrue(dashboardPage.DashboardPageLeftMenuSubmenuCreatePetsAdsense.isDisplayed(), "Görünür değil");
        softAssert.assertTrue(dashboardPage.DashboardPageLeftMenuSubmenuCreatePetsAdsense.isEnabled(), "Tıklanır değil");
        wait.until(ExpectedConditions.elementToBeClickable(dashboardPage.DashboardPageLeftMenuSubmenuCreatePetsAdsense)).click();
        ReusableMethods.bekle(5);
        Driver.getDriver().navigate().back();

        extentTest = extentReports.createTest("sol menüdeki Ticket kısmı testi");
        //## Tickets BOLUMU ICIN
        //1- Sol menü Tickets kısmı görünür ve tıklanabilir mi?
        ReusableMethods.hover(dashboardPage.dashboardPageSideBarMenu);
        softAssert.assertTrue(dashboardPage.DashboardPageLeftMenuTicketsLink.isDisplayed(), "Görünür değil");
        softAssert.assertTrue(dashboardPage.DashboardPageLeftMenuTicketsLink.isEnabled(), "Tıklanır Değil");
        wait.until(ExpectedConditions.elementToBeClickable(dashboardPage.DashboardPageLeftMenuTicketsLink)).click();
        ReusableMethods.bekle(7);
        Driver.getDriver().navigate().back();

        extentTest = extentReports.createTest("sol menüdeki Vaccitaions kısmı testi");
        //## Vaccinations BOLUMU ICIN
        //1- Sol menü Vaccinations kısmı görünür ve tıklanabilir mi?
        ReusableMethods.hover(dashboardPage.dashboardPageSideBarMenu);
        softAssert.assertTrue(dashboardPage.DashboardPageLeftMenuVaccinationsLink.isDisplayed(), "Görünür değil");
        softAssert.assertTrue(dashboardPage.DashboardPageLeftMenuVaccinationsLink.isEnabled(), "Tıklanır Değil");
        wait.until(ExpectedConditions.elementToBeClickable(dashboardPage.DashboardPageLeftMenuVaccinationsLink)).click();
        ReusableMethods.bekle(7);
        Driver.getDriver().navigate().back();

        //ReusableMethods.bekle(5);
        softAssert.assertAll();
    }


//    @Test (priority = 22)
//    public void US44_TC01_ProfilMenusuTetiklemeTutarlilikTesti() {
//
//        extentTest = extentReports.createTest("TC01 - Profil Dropdown Menü Tetikleme ve UI Tutarlılık Testi");
//        //SoftAssert softAssert = new SoftAssert();
//
//        // Admin kullanıcısı isim alanına tıklanarak dropdown menünün açıldığı doğrulanır (Beklenen davranış)
//        adminBodyPage.profileNameText.click();
//
//        // İsim alanı tıklandığında menü açılmazsa bu durum bir UI tutarsızlığı (BUG) olarak kaydedilir
//        softAssert.assertTrue(adminBodyPage.profileLogoutOption.isDisplayed(),
//                "HATA/BUG: Admin kullanıcısı isim alanına tıklandığında menü açılmıyor!");
//
//        // Menünün isim alanının yanındaki boşlukta tetiklenip tetiklenmediği kontrol edilir
//        if (!adminBodyPage.profileLogoutOption.isDisplayed()) {
//
//            System.out.println("BİLGİ: İsim tıklandığında menü açılmadı şimdi boşluk alanı deneniyor");
//
//            // Boşluk alanına tıklanır
//            adminBodyPage.profileDropdownButton.click();
//
//            // Menünün sadece bu alanda çalışıp çalışmadığı teyit edilir
//            boolean isMenuOpenedByClickingSpace = adminBodyPage.profileLogoutOption.isDisplayed();
//
//            if (isMenuOpenedByClickingSpace) {
//                System.out.println("TEYİT: Menü sadece boşluk alanında tetikleniyor.");
//            }
//
//            softAssert.assertTrue(isMenuOpenedByClickingSpace, "HATA: Boşluk alanı da menüyü tetiklemiyor!");
//        }
//
//        softAssert.assertAll();
//    }
//
//    @Test (priority = 23)
//    public void US44_TC02_AdminLogoutVeAnaSayfaYonlendirmeTesti() {
//
//        extentTest = extentReports.createTest("TC02 - Güvenli Çıkış (Logout) ve Ana Sayfa Yönlendirme Testi");
//        //SoftAssert softAssert = new SoftAssert();
//
//        // Profil menüsünü tetiklemek için dropdown alanına boşluk alanına tıklanır
//        adminBodyPage.profileDropdownButton.click();
//        // Açılan menüden 'Logout' seçeneği seçilerek oturum sonlandırılır
//        adminBodyPage.logoutLeft.click();
//
//        // Kullanıcının HomePage'e yönlendirilip yönlendirilmediği doğrulanır
//        String expectedUrl = ConfigReader.getProperty("LfcUrl");
//        String actualUrl = Driver.getDriver().getCurrentUrl();
//
//        // URL doğrulaması yapılır eşleşmeme durumunda hata mesajı raporlanır
//        softAssert.assertEquals(actualUrl,expectedUrl, "HATA: Logout işlemi sonrası ana sayfaya yönlendirme yapılamadı!");
//
//        softAssert.assertAll();
//
//    }
//
//    @Test (priority = 24)
//    public void US44_TC03_LogoutSonrasiGeriButonuIleYetkisizErisimTesti() {
//
//        extentTest = extentReports.createTest("TC03 - Logout Sonrası Geri Butonu ile Yetkisiz Erişim Güvenlik Testi");
//        //SoftAssert softAssert = new SoftAssert();
//
//        // Profil menüsünü tetiklemek için dropdown alanına boşluk alanına tıklanır
//        adminBodyPage.profileDropdownButton.click();
//        // Açılan menüden 'Logout' seçeneği seçilerek oturum sonlandırılır
//        adminBodyPage.profileLogoutOption.click();
//        // Tarayıcıda 'Geri' butonuna basılarak önceki sayfaya dönülmeye çalışılır
//        Driver.getDriver().navigate().back();
////        ReusableMethods.bekle(2);
//
//
//        String actualUrl = Driver.getDriver().getCurrentUrl();
//
//        // Eğer sistem güvenliyse, geri basıldığında URL "/admin" İÇERMEMELİDİR.
//        // Eğer actualUrl hala "/admin" içeriyorsa bu test FAIL verecek ve mesajını yazacaktır.
//        softAssert.assertFalse(actualUrl.toLowerCase().contains("/admin"),
//                "BUG: Kullanıcı çıkış yaptıktan sonra geri butonu ile admin sayfasına tekrar erişebiliyor!");
//        softAssert.assertAll();
//
//    }


}
