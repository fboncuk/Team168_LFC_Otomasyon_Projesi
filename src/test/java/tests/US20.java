package tests;

import com.github.javafaker.Faker;
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
    SoftAssert softAssert ;
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

    @Test (priority = 1, description = "Login ayfası temel öğeler görünürlük ve doğrualama testi")
    public void US20_TC01_LoginPage_Function_Validation_Test () {

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

    @Test (priority = 2, description = "Login sayfasında tümü boş bilgilerle giriş denemesi")
    public void US20_TC02_LoginPage_Negative_Function_Validation_Test_1 () {

        signButonsPage.emailKutusu.clear();
        signButonsPage.passwordKutusu.clear();
        signButonsPage.signInButtonOnay.click();

        //Mesaj uyarısını String olarak tanımladık.
        String validationMessage =
                signButonsPage.resetPasswordPageEmailTextBox.getAttribute("validationMessage");

        //Mesajı yazdıralım
        System.out.println(validationMessage);

        softAssert.assertTrue(!validationMessage.isEmpty(), "Validation Message is not Displayed");

        ReusableMethods.tarihliTumSayfaResimCek(Driver.getDriver(), "US20_TC02_ValidationMessage1");
        softAssert.assertAll();
    }

    @Test (priority = 3, description = "Login sayfasında admin geçerli mail ve pass boş bilgilerle giriş denemesi")
    public void US20_TC02_LoginPage_Negative_Function_Validation_Test_2 () {

        signButonsPage.emailKutusu.clear();
        signButonsPage.emailKutusu.sendKeys(ConfigReader.getProperty("T09AdminMail"));
        signButonsPage.passwordKutusu.clear();
        signButonsPage.signInButtonOnay.click();
        ReusableMethods.bekle(2);

        //Mesaj uyarısını String olarak tanımladık.
        String validationMessage =
                signButonsPage.passwordKutusu.getAttribute("validationMessage");

        //Mesajı yazdıralım
        System.out.println(validationMessage);

        softAssert.assertTrue(!validationMessage.isEmpty(), "Validation Message is not Displayed");

        ReusableMethods.tarihliTumSayfaResimCek(Driver.getDriver(), "US20_TC02_ValidationMessage2");
        softAssert.assertAll();
    }

    @Test (priority = 4, description = "Login sayfasında boş mail ve geçerli admin yetkili maile ait pass bilgilerle giriş denemesi")
    public void US20_TC02_LoginPage_Negative_Function_Validation_Test_3 () {

        signButonsPage.emailKutusu.clear();
        signButonsPage.passwordKutusu.clear();
        signButonsPage.passwordKutusu.sendKeys(ConfigReader.getProperty("T09AdminPassword"));
        signButonsPage.signInButtonOnay.click();

        //Mesaj uyarısını String olarak tanımladık.
        String validationMessage =
                signButonsPage.emailKutusu.getAttribute("validationMessage");

        //Mesajı yazdıralım
        System.out.println(validationMessage);

        softAssert.assertTrue(!validationMessage.isEmpty(), "Validation Message is not Displayed");

        ReusableMethods.tarihliTumSayfaResimCek(Driver.getDriver(), "US20_TC02_ValidationMessage3");
        softAssert.assertAll();
    }

    @Test (priority = 5, description = "Login sayfasında admin geçerli mail ve geçersiz pass ile giriş denemesi")
    public void US20_TC03_04_LoginPage_Negative_Function_Validation_Test_1 () {

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

    @Test (priority = 6, description = "Login sayfasında admin geçerli pass ve geçersiz mail ile giriş denemesi 2")
    public void US20_TC03_04_LoginPage_Negative_Function_Validation_Test_2() {

        signButonsPage.emailKutusu.clear();
        signButonsPage.emailKutusu.sendKeys(faker.internet().emailAddress());
        signButonsPage.passwordKutusu.clear();
        signButonsPage.passwordKutusu.sendKeys(ConfigReader.getProperty("T09AdminPassword"));
        signButonsPage.signInButtonOnay.click();

        String actualInvalidPasswordMessage = signButonsPage.signInPageInvalidPasswordFeedbackMessage.getText();

        //çıkan uarıyı consola yazdır
        System.out.println("Görünen uyarı : " + actualInvalidPasswordMessage);

        softAssert.assertTrue(!actualInvalidPasswordMessage.isEmpty(), "Validation Message is not Displayed");

        ReusableMethods.tarihliTumSayfaResimCek(Driver.getDriver(), "US20_TC03_PozitifPassNegatifMail_Test");
        softAssert.assertAll();
    }

    @Test (priority = 7, description = "Login sayfasında dashboarda erişim öncesi homepage erişim sağlanması")
    public void US20_TC05_Admin_Account_SignIn_to_Admin_Dashboard_Test() {

        //Email box temizle ve Geçerli admin mail adresi gir
        signButonsPage.emailKutusu.clear();
        signButonsPage.emailKutusu.sendKeys(ConfigReader.getProperty("T09AdminMail"));

        //Password box temizle ve admin geçerli şifreyi gir
        signButonsPage.passwordKutusu.clear();
        signButonsPage.passwordKutusu.sendKeys(ConfigReader.getProperty("T09AdminPassword"));

        //Sign in butonuna bas
        wait.until(ExpectedConditions.elementToBeClickable(signButonsPage.signInButtonOnay)).click();

        // Login sonrası URL bekle
        String expectedHomeUrl = ConfigReader.getProperty("LfcUrl");
        wait.until(ExpectedConditions.urlContains(expectedHomeUrl));

        String actualHomeUrl = Driver.getDriver().getCurrentUrl().toLowerCase();
        softAssert.assertTrue(actualHomeUrl.toLowerCase().contains(expectedHomeUrl), "Login To Homepage Failed");

        ReusableMethods.tarihliTumSayfaResimCek(Driver.getDriver(), "US20_TC05_Admin_Account_SignIn_to_Homepage_Test");
//        softAssert.assertAll();
//    }
//
//    @Test (priority = 8, description = "Homepage sayfasından  admin dashboarda erişim sağlanması")
//    public void US20_TC05_Admin_Account_SignIn_to_Homepage_Test() {
//
//
        // Dashboard butonu görünür olana kadar bekle
        //wait.until(ExpectedConditions.visibilityOf(signButonsPage.headerUserName));
        wait.until(ExpectedConditions.elementToBeClickable(signButonsPage.headerUserName)).click();

        // Dashboard URL bekle
        String expectedDashboardUrl = ConfigReader.getProperty("DasUrl");
        wait.until(ExpectedConditions.urlContains(expectedDashboardUrl));

        String actualDashboardUrl = Driver.getDriver().getCurrentUrl();
        softAssert.assertEquals(
                actualDashboardUrl.toLowerCase(),
                expectedDashboardUrl.toLowerCase(),
                "Login To Dashboard Failed"
        );

//        softAssert.assertAll();
//
//    }
//
//    @Test (priority = 9, description = "Dashboard panelinde menü öğelerine erişildiğini doğrulanması (Funcional Test)")
//    public void US20_TC06_Admin_Dashboard_Sol_Menu_Ogeler_Test(){

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
        softAssert.assertTrue(dashboardPage.DashboardPageLeftMenuSubmenuRoles.isDisplayed(),"Görünür değil");
        softAssert.assertTrue(dashboardPage.DashboardPageLeftMenuSubmenuRoles.isEnabled(),"Tıklanır değil");
        dashboardPage.DashboardPageLeftMenuSubmenuRoles.click();
        //Driver.getDriver().navigate().back();

//        Driver.getDriver().get(ConfigReader.getProperty("DasUrl"));
//
//        ReusableMethods.hover(dashboardPage.dashboardPageSideBarMenu);
//        dashboardPage.DashboardPageLeftMenuRolesLink.click();
//        softAssert.assertTrue(dashboardPage.DashboardPageLeftMenuSubmenuCreateRole.isDisplayed());
//        softAssert.assertTrue(dashboardPage.DashboardPageLeftMenuSubmenuCreateRole.isEnabled());
//        dashboardPage.DashboardPageLeftMenuSubmenuCreateRole.click();
//        Driver.getDriver().get(ConfigReader.getProperty("DasUrl"));
//
//        ReusableMethods.hover(dashboardPage.dashboardPageSideBarMenu);
//        dashboardPage.DashboardPageLeftMenuRolesLink.click();
//        softAssert.assertTrue(dashboardPage.DashboardPageLeftMenuSubmenuCreateRole.isDisplayed());
//        softAssert.assertTrue(dashboardPage.DashboardPageLeftMenuSubmenuCreateRole.isEnabled());
//        dashboardPage.DashboardPageLeftMenuSubmenuCreateRole.click();
//        Driver.getDriver().get(ConfigReader.getProperty("DasUrl"));



        ReusableMethods.bekle(5);
        softAssert.assertAll();

    }

}
