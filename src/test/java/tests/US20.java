package tests;

import com.github.javafaker.Faker;;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.LcfAdminPage.DashboardPage;
import pages.LcfHomePage.SignButonsPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

import java.time.Duration;


public class US20 {

    //Class level'da Obje oluştur
    SignButonsPage signButonsPage;
    SoftAssert softAssert;
    WebDriverWait wait;
    Faker faker;
    DashboardPage dashboardPage;

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

    @Test(priority = 1, description = "Login ayfası temel öğeler görünürlük ve doğrualama testi")
    public void US20_TC01_LoginPage_Function_Validation_Test() {

        softAssert.assertTrue(signButonsPage.signInPageLogo.isDisplayed(), "Sign in page logo is not displayed");
        softAssert.assertTrue(signButonsPage.emailKutusu.isDisplayed(), "Sign in page email box is not displayed");
        softAssert.assertTrue(signButonsPage.emailKutusu.isEnabled(), "Sign in page email box not enabled");
        softAssert.assertTrue(signButonsPage.passwordKutusu.isDisplayed(), "Sign in page password box is not displayed");
        softAssert.assertTrue(signButonsPage.passwordKutusu.isEnabled(), "Sign in page password box not enabled");
        softAssert.assertTrue(signButonsPage.rememberMeKutusu.isEnabled(), "Sign in page rememberMe box not enabled");
        softAssert.assertTrue(signButonsPage.rememberMeKutusu.isDisplayed(), "Sign in page rememberMe box is not displayed");

        String signInPAgeTitleIcerik = Driver.getDriver().getTitle();
        String signInPageLinkIcerik = Driver.getDriver().getCurrentUrl();
        softAssert.assertTrue(signInPageLinkIcerik.toLowerCase().contains(signInPAgeTitleIcerik.toLowerCase()), "Link ve Title eşleşmesi");

        softAssert.assertAll();
    }

    @Test(priority = 2, description = "Login sayfasında tümü boş bilgilerle giriş denemesi")
    public void US20_TC02_LoginPage_Negative_Function_Validation_Test_1() {

        signButonsPage.emailKutusu.clear();
        signButonsPage.passwordKutusu.clear();
        signButonsPage.signInButtonOnay.click();

        //Mesaj uyarısını String olarak tanımladık.
        String validationMessage = signButonsPage.resetPasswordPageEmailTextBox.getAttribute("validationMessage");

        //Mesajı yazdıralım
        System.out.println(validationMessage);

        softAssert.assertTrue(!validationMessage.isEmpty(), "Validation Message is not Displayed");

        ReusableMethods.tarihliTumSayfaResimCek(Driver.getDriver(), "US20_TC02_ValidationMessage1");
        softAssert.assertAll();
    }

    @Test(priority = 3, description = "Login sayfasında admin geçerli mail ve pass boş bilgilerle giriş denemesi")
    public void US20_TC02_LoginPage_Negative_Function_Validation_Test_2() {

        signButonsPage.emailKutusu.clear();
        signButonsPage.emailKutusu.sendKeys(ConfigReader.getProperty("T09AdminMail"));
        signButonsPage.passwordKutusu.clear();
        signButonsPage.signInButtonOnay.click();
        ReusableMethods.bekle(2);

        //Mesaj uyarısını String olarak tanımladık.
        String validationMessage = signButonsPage.passwordKutusu.getAttribute("validationMessage");

        //Mesajı yazdıralım
        System.out.println(validationMessage);

        softAssert.assertTrue(!validationMessage.isEmpty(), "Validation Message is not Displayed");

        ReusableMethods.tarihliTumSayfaResimCek(Driver.getDriver(), "US20_TC02_ValidationMessage2");
        softAssert.assertAll();
    }

    @Test(priority = 4, description = "Login sayfasında boş mail ve geçerli admin yetkili maile ait pass bilgilerle giriş denemesi")
    public void US20_TC02_LoginPage_Negative_Function_Validation_Test_3() {

        signButonsPage.emailKutusu.clear();
        signButonsPage.passwordKutusu.clear();
        signButonsPage.passwordKutusu.sendKeys(ConfigReader.getProperty("T09AdminPassword"));
        signButonsPage.signInButtonOnay.click();

        //Mesaj uyarısını String olarak tanımladık.
        String validationMessage = signButonsPage.emailKutusu.getAttribute("validationMessage");

        //Mesajı yazdıralım
        System.out.println(validationMessage);

        softAssert.assertTrue(!validationMessage.isEmpty(), "Validation Message is not Displayed");

        ReusableMethods.tarihliTumSayfaResimCek(Driver.getDriver(), "US20_TC02_ValidationMessage3");
        softAssert.assertAll();
    }

    @Test(priority = 5, description = "Login sayfasında admin geçerli mail ve geçersiz pass ile giriş denemesi")
    public void US20_TC03_04_LoginPage_Negative_Function_Validation_Test_1() {

        signButonsPage.emailKutusu.clear();
        signButonsPage.emailKutusu.sendKeys(ConfigReader.getProperty("T09AdminMail"));
        signButonsPage.passwordKutusu.clear();
        signButonsPage.passwordKutusu.sendKeys(faker.internet().password());
        signButonsPage.signInButtonOnay.click();

        //Beklenen ve gerçekleşen sonuçları belirtelim ve Assert ile denetleyelim.


        String actualInvalidPasswordMessage = signButonsPage.signInPageInvalidPasswordFeedbackMessage.getText();

        //çıkan alerti consola yazdır
        System.out.println("Görünen uyarı : " + actualInvalidPasswordMessage);

        softAssert.assertTrue(!actualInvalidPasswordMessage.isEmpty(), "Validation Message is not Displayed");

        ReusableMethods.tarihliTumSayfaResimCek(Driver.getDriver(), "US20_TC03_PozitifMailNegatifPass_Test");
        softAssert.assertAll();
    }

    @Test(priority = 6, description = "Login sayfasında admin geçerli pass ve geçersiz mail ile giriş denemesi 2")
    public void US20_TC03_04_LoginPage_Negative_Function_Validation_Test_2() {

        signButonsPage.emailKutusu.clear();
        signButonsPage.emailKutusu.sendKeys(faker.internet().emailAddress());
        signButonsPage.passwordKutusu.clear();
        signButonsPage.passwordKutusu.sendKeys(ConfigReader.getProperty("T09AdminPassword"));
        signButonsPage.signInButtonOnay.click();
        wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));

        String actualInvalidPasswordMessage = signButonsPage.signInPageInvalidPasswordFeedbackMessage.getText();

        //çıkan uarıyı consola yazdır
        System.out.println("Görünen uyarı : " + actualInvalidPasswordMessage);

        softAssert.assertTrue(!actualInvalidPasswordMessage.isEmpty(), "Validation Message is not Displayed");

        ReusableMethods.tarihliTumSayfaResimCek(Driver.getDriver(), "US20_TC03_PozitifPassNegatifMail_Test");
        softAssert.assertAll();
    }

    @Test(priority = 7, description = "Login sayfasında dashboarda erişim öncesi homepage erişim sağlanması")
    public void US20_TC05_Admin_Account_SignIn_to_Admin_Dashboard_Test() {

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

    @Test(priority = 8, description = "Homepage sayfasından  admin dashboarda erişim sağlanması")
    public void US20_TC05_Admin_Account_SignIn_to_Homepage_Test() {


        // Dashboard butonu görünür olana kadar bekle
        //wait.until(ExpectedConditions.visibilityOf(signButonsPage.headerUserName));
        //wait.until(ExpectedConditions.elementToBeClickable(signButonsPage.headerUserName)).click();
        signButonsPage.headerUserName.click();

        // Dashboard URL bekle
        String expectedDashboardUrl = ConfigReader.getProperty("DasUrl");
        wait.until(ExpectedConditions.urlContains(expectedDashboardUrl));

        String actualDashboardUrl = Driver.getDriver().getCurrentUrl().toLowerCase();
        softAssert.assertEquals(actualDashboardUrl, expectedDashboardUrl.toLowerCase(), "Login To Dashboard Failed");

        softAssert.assertAll();

    }

    @Test(priority = 9, description = "Dashboard panelinde menü öğelerine erişildiğini doğrulanması (Funcional Test)")
    public void US20_TC06_Admin_Dashboard_Sol_Menu_Ogeler_Test() {

        //Sol Menü açılmasını sağla
        ReusableMethods.hover(dashboardPage.dashboardPageSideBarMenu);

        //sol menüdeki dashboard menü görünürlüğü ve tıklanabilirliği
        softAssert.assertTrue(dashboardPage.DashboardPageLeftMenuDashboardLink.isDisplayed());
        softAssert.assertTrue(dashboardPage.DashboardPageLeftMenuDashboardLink.isEnabled());

        //sol menüdeki Roles kısmı görünür, tıklanır mı?
        softAssert.assertTrue(dashboardPage.DashboardPageLeftMenuRolesLink.isDisplayed());
        softAssert.assertTrue(dashboardPage.DashboardPageLeftMenuRolesLink.isEnabled());
        dashboardPage.DashboardPageLeftMenuRolesLink.click();

        //sol menüdeki Roles altındaki Roles kısmı gürünür ve tıklanabilir mi?
        softAssert.assertTrue(dashboardPage.DashboardPageLeftMenuSubmenuRoles.isDisplayed(), "Görünür değil");
        softAssert.assertTrue(dashboardPage.DashboardPageLeftMenuSubmenuRoles.isEnabled(), "Tıklanır değil");
        wait.until(ExpectedConditions.elementToBeClickable(dashboardPage.DashboardPageLeftMenuSubmenuRoles)).click();
        Driver.getDriver().navigate().back();


        //Sol menü Roles Create kısmı görünür ve tıklanabilir mi?
        ReusableMethods.hover(dashboardPage.dashboardPageSideBarMenu);
        softAssert.assertTrue(dashboardPage.DashboardPageLeftMenuSubmenuCreateRole.isDisplayed(), "Görünür değil");
        softAssert.assertTrue(dashboardPage.DashboardPageLeftMenuSubmenuCreateRole.isEnabled(), "Tıklanır Değil");
        wait.until(ExpectedConditions.elementToBeClickable(dashboardPage.DashboardPageLeftMenuSubmenuCreateRole)).click();
        Driver.getDriver().navigate().back();

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
        Driver.getDriver().navigate().back();

        //3- sol menüdeki Users altındaki Users Create kısmı gürünür ve tıklanabilir mi
        softAssert.assertTrue(dashboardPage.DashboardPageLeftMenuSubmenuCreateUser.isDisplayed(), "Görünür değil");
        softAssert.assertTrue(dashboardPage.DashboardPageLeftMenuSubmenuCreateUser.isEnabled(), "Tıklanır değil");
        wait.until(ExpectedConditions.elementToBeClickable(dashboardPage.DashboardPageLeftMenuSubmenuCreateUser)).click();
        Driver.getDriver().navigate().back();


        //## Bed Managers BOLUMU ICIN
        //1- Sol menü Bed Managers kısmı görünür ve tıklanabilir mi?
        ReusableMethods.hover(dashboardPage.dashboardPageSideBarMenu);
        softAssert.assertTrue(dashboardPage.DashboardPageLeftMenuBedManagersLink.isDisplayed(), "Görünür değil");
        softAssert.assertTrue(dashboardPage.DashboardPageLeftMenuBedManagersLink.isEnabled(), "Tıklanır Değil");
        wait.until(ExpectedConditions.elementToBeClickable(dashboardPage.DashboardPageLeftMenuBedManagersLink)).click();

        //2- sol menüdeki Bed Managers altındaki Bed Managers kısmı gürünür ve tıklanabilir mi?
        softAssert.assertTrue(dashboardPage.DashboardPageLeftMenuSubmenuBedManagers.isDisplayed(), "Görünür değil");
        softAssert.assertTrue(dashboardPage.DashboardPageLeftMenuSubmenuBedManagers.isEnabled(), "Tıklanır değil");

        wait.until(ExpectedConditions.elementToBeClickable(dashboardPage.DashboardPageLeftMenuSubmenuBedManagers)).click();
        Driver.getDriver().navigate().back();

        //3- sol menüdeki Bed Managers altındaki Create Bed Managers kısmı gürünür ve tıklanabilir mi
        softAssert.assertTrue(dashboardPage.DashboardPageLeftMenuSubmenuCreateBedManagers.isDisplayed(), "Görünür değil");
        softAssert.assertTrue(dashboardPage.DashboardPageLeftMenuSubmenuCreateBedManagers.isEnabled(), "Tıklanır değil");
        wait.until(ExpectedConditions.elementToBeClickable(dashboardPage.DashboardPageLeftMenuSubmenuCreateBedManagers)).click();
        Driver.getDriver().navigate().back();


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
        Driver.getDriver().navigate().back();

        //3- sol menüdeki Departments altındaki Create Departments kısmı gürünür ve tıklanabilir mi
        softAssert.assertTrue(dashboardPage.DashboardPageLeftMenuSubmenuCreateDepartments.isDisplayed(), "Görünür değil");
        softAssert.assertTrue(dashboardPage.DashboardPageLeftMenuSubmenuCreateDepartments.isEnabled(), "Tıklanır değil");
        wait.until(ExpectedConditions.elementToBeClickable(dashboardPage.DashboardPageLeftMenuSubmenuCreateDepartments)).click();
        Driver.getDriver().navigate().back();


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
        Driver.getDriver().navigate().back();

        //3- sol menüdeki Doctors altındaki Create Doctors kısmı gürünür ve tıklanabilir mi
        softAssert.assertTrue(dashboardPage.DashboardPageLeftMenuSubmenuCreateDoctors.isDisplayed(), "Görünür değil");
        softAssert.assertTrue(dashboardPage.DashboardPageLeftMenuSubmenuCreateDoctors.isEnabled(), "Tıklanır değil");
        wait.until(ExpectedConditions.elementToBeClickable(dashboardPage.DashboardPageLeftMenuSubmenuCreateDoctors)).click();
        Driver.getDriver().navigate().back();

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
        Driver.getDriver().navigate().back();

        //3- sol menüdeki Medicines altındaki Create Medicines kısmı gürünür ve tıklanabilir mi
        softAssert.assertTrue(dashboardPage.DashboardPageLeftMenuSubmenuCreateMedicines.isDisplayed(), "Görünür değil");
        softAssert.assertTrue(dashboardPage.DashboardPageLeftMenuSubmenuCreateMedicines.isEnabled(), "Tıklanır değil");
        wait.until(ExpectedConditions.elementToBeClickable(dashboardPage.DashboardPageLeftMenuSubmenuCreateMedicines)).click();
        Driver.getDriver().navigate().back();

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
        Driver.getDriver().navigate().back();

        //3- sol menüdeki Pet Adsense altındaki Create Pet Adsense kısmı gürünür ve tıklanabilir mi
        softAssert.assertTrue(dashboardPage.DashboardPageLeftMenuSubmenuCreatePetsAdsense.isDisplayed(), "Görünür değil");
        softAssert.assertTrue(dashboardPage.DashboardPageLeftMenuSubmenuCreatePetsAdsense.isEnabled(), "Tıklanır değil");
        wait.until(ExpectedConditions.elementToBeClickable(dashboardPage.DashboardPageLeftMenuSubmenuCreatePetsAdsense)).click();
        Driver.getDriver().navigate().back();

        //## Tickets BOLUMU ICIN
        //1- Sol menü Tickets kısmı görünür ve tıklanabilir mi?
        ReusableMethods.hover(dashboardPage.dashboardPageSideBarMenu);
        softAssert.assertTrue(dashboardPage.DashboardPageLeftMenuTicketsLink.isDisplayed(), "Görünür değil");
        softAssert.assertTrue(dashboardPage.DashboardPageLeftMenuTicketsLink.isEnabled(), "Tıklanır Değil");
        wait.until(ExpectedConditions.elementToBeClickable(dashboardPage.DashboardPageLeftMenuTicketsLink)).click();
        Driver.getDriver().navigate().back();

        //## Vaccinations BOLUMU ICIN
        //1- Sol menü Vaccinations kısmı görünür ve tıklanabilir mi?
        ReusableMethods.hover(dashboardPage.dashboardPageSideBarMenu);
        softAssert.assertTrue(dashboardPage.DashboardPageLeftMenuVaccinationsLink.isDisplayed(), "Görünür değil");
        softAssert.assertTrue(dashboardPage.DashboardPageLeftMenuVaccinationsLink.isEnabled(), "Tıklanır Değil");
        wait.until(ExpectedConditions.elementToBeClickable(dashboardPage.DashboardPageLeftMenuVaccinationsLink)).click();
        Driver.getDriver().navigate().back();

        //ReusableMethods.bekle(5);
        softAssert.assertAll();
    }

    @Test (priority = 10, description = "Dashboard panelinde Logoya ve anasayfaya erişildiğini doğrulanması (Funcional Test)")
    public void US20_TC07_Admin_Dashboard_Logo_Homepage_navigation(){

//        ReusableMethods.hover(dashboardPage.dashboardPageSideBarMenu);
//        softAssert.assertTrue(dashboardPage.dashboardLeftMenuLogo.isDisplayed());
//        softAssert.assertTrue(dashboardPage.dashboardLeftMenuLogo.isEnabled());
//
//
//        softAssert.assertTrue(
//                ReusableMethods.isReallyClickable(Driver.getDriver(), dashboardPage.dashboardLeftMenuLogo, 5),
//                "Logo tıklanabilir olmalı!"
//        );

        ReusableMethods.hover(dashboardPage.dashboardPageSideBarMenu);

        // 1. Aşama: Görünürlük kontrolü
        softAssert.assertTrue(dashboardPage.dashboardLeftMenuLogo.isDisplayed(), "Logo görünür değil!");

        // 2. Aşama: Gerçek tıklama ve URL kontrolü
        String ilkUrl = Driver.getDriver().getCurrentUrl();

        try {
            dashboardPage.dashboardLeftMenuLogo.click(); // JS kullanmadan normal click
            ReusableMethods.bekle(1); // Sayfanın yüklenmesi için kısa bir bekleme
            String sonrakiUrl = Driver.getDriver().getCurrentUrl();

            // Eğer logo tıklandığında anasayfaya gitmiyorsa (URL değişmiyorsa) fail ver
            softAssert.assertNotEquals(ilkUrl, sonrakiUrl, "Logo tıklandı ama URL değişmedi, yönlendirme başarısız!");

        } catch (Exception e) {
            softAssert.fail("Logo fiziksel olarak tıklanabilir değil! Engel var: " + e.getMessage());
        }

        softAssert.assertAll();


//@Test(priority = 9, description = "Dashboard panelinde menü öğelerine erişildiğini doğrulanması (Funcional Test)")
//public void US20_TC06_Admin_Dashboard_Sol_Menu_Ogeler_Test() {


        softAssert.assertAll();
        //Bu test Sinem Hanım tarafından US23 içinde işlendi
        //@Test(priority = 10, description = "Dashboard panelinde SummaryCards menü öğelerine erişildiğini doğrulanması (Funcional Test)")
        //public void US20_TC08_Admin_Dashboard_SummaryCards_Menu_Ogeler_Test() {}

        //Bu test Sinem Hanım tarafından US23 içinde işlendi
        //@Test(priority = 10, description = "Dashboard panelinde SummaryCards menü Activity Liste erişildiğini doğrulanması (Funcional Test)")
        //public void US20_TC09_Admin_Dashboard_SummaryCards_Menu_Activity_List_Ogeler_Testi() {}

        //Bu test Sinem Hanım tarafından US23 içinde işlendi
        //@Test(priority = 10, description = "Dashboard panelinde SummaryCards menü öğelerinden sosyal medya ikonlarının erişildiğini doğrulanması (Funcional Test)")
        //public void US20_TC14_Admin_Dashboard_SummaryCards_Menu_Sosyal_Medya_Ikonlari_Ogeler_Testi() {}

    }
}
