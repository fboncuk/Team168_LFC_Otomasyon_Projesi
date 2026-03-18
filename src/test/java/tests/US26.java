package tests;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.LcfAdminPage.DashboardPage;
import pages.LcfAdminPage.DashboardUsersPage;
import pages.LcfHomePage.HomeBodyPage;
import pages.LcfHomePage.SignButonsPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;
import java.util.List;

@Listeners(utilities.Listeners.class)

public class US26 {

    DashboardPage dashboardPage;
    DashboardUsersPage usersPage;
    SignButonsPage loginPage;
    HomeBodyPage homePage;
    Actions actions;
    SoftAssert softAssert;
    Faker faker;
    String expectedFullName;

    @BeforeClass
    public void setUp() {
        dashboardPage = new DashboardPage();
        usersPage = new DashboardUsersPage();
        loginPage = new SignButonsPage();
        homePage = new HomeBodyPage();
        actions = new Actions(Driver.getDriver());
        faker = new Faker();


        Driver.getDriver().get(ConfigReader.getProperty("LcfUrl"));
        loginPage.signInLinki
                .click();
        loginPage.emailKutusu
                .sendKeys(ConfigReader.getProperty("T04AdminMail"));
        loginPage.passwordKutusu
                .sendKeys(ConfigReader.getProperty("T04AdminPassword"));
        loginPage.signInButtonOnay
                .click();

        ReusableMethods.bekle(1);
        homePage.dashboardGirisButonu
                .click();

        ReusableMethods.bekle(1);
        actions.moveToElement(dashboardPage.dashboardPageSideBarMenu).perform();

        ReusableMethods.bekle(1);
        usersPage.usersMenuButton
                .click();

        ReusableMethods.bekle(1);
        usersPage.createUsersSubMenu
                .click();
        ReusableMethods.bekle(2);
    }

    @BeforeMethod
    public void beforeMethod() {
        // Her testten önce softAssert sıfırlanmalı ki önceki testin hataları sonrakini etkilemesin
        softAssert = new SoftAssert();
    }

    @Test(priority = 1, description = "TC001: Pozitif Kullanıcı Oluşturma")
    public void test01_TC001_PozitifKullaniciOlusturma() {
        System.out.println("TC001:Yeni Kullanıcı Olusturma");
        try {
            expectedFullName = faker.name().fullName();
            usersPage.nameBox
                    .sendKeys(expectedFullName);
            usersPage.phoneBox
                    .sendKeys("5554443322");
            if (!usersPage.roleDropdownKutusu.isSelected()) { usersPage.roleDropdownKutusu.click(); }
            usersPage.passwordBox
                    .sendKeys("Pass123!");
            usersPage.passwordConfirmationBox
                    .sendKeys("Pass123!");
            usersPage.emailBox
                    .sendKeys(faker.internet().emailAddress());

            String filePath = System.getProperty("user.dir") + "/src/test/resources/avatarrrr.jfif";
            usersPage.avatarUploadInput.sendKeys(filePath);

            usersPage.saveButton.click();
            ReusableMethods.bekle(2);
            // Laravel hata sayfasını yakalamak için
            if (Driver.getDriver().getPageSource()
                    .contains("Exception") || Driver.getDriver().getTitle()
                    .contains("Error")) {
                Assert.fail("[TC001 Hatalı]: Kaydet butonuna basınca sistem hata sayfasına düştü! (UrlGenerationException)");
            }

            boolean successVisible = Driver.getDriver().getPageSource()
                    .contains("successfully");
            Assert.assertTrue(successVisible, "[TC001 Hatalı]: Başarı mesajı görüntülenemedi!");


        } catch (Exception e) {
            Assert.fail("Test beklenmedik bir şekilde kesildi: " + e.getMessage());
        }
    }


    @Test(priority = 2, description = "TC006: Oluşturulan Kullanıcının Tabloda Görüntülenmesi")
    public void test02_TC006_KullaniciTablosuKontrolu() {
        System.out.println("TC006: Kullanıcı Tablosu Kontrolü");

        boolean isUserInTable = Driver.getDriver().getPageSource()
                .contains(expectedFullName);
        Assert.assertTrue(isUserInTable, "BUG_018: Kullanıcı tablosu olusmadıgı icin kullanıcı görünmüyor!");
    }


    @Test(priority = 3, description = "TC002: Boş Alan Kontrolü")
    public void test03_TC002_BosAlanKontrolu() {

        Driver.getDriver().navigate().back();
        ReusableMethods.bekle(1);

        Driver.getDriver().navigate().refresh();
        ReusableMethods.bekle(1);
        System.out.println("TC002: Boş Alan Kontrolü");
        Driver.getDriver().navigate().refresh();
        ReusableMethods.bekle(1);

        usersPage.saveButton
                .click();
        ReusableMethods.bekle(1);

        boolean isErrorVisible = Driver.getDriver().getPageSource()
                .contains("Please fill out this field");
        softAssert.assertTrue(isErrorVisible, "BUG_014: Boş alanlar için 'Please fill out this field' uyarı mesajı görüntülenemedi!");
        softAssert.assertAll();
    }

    @Test(priority = 4, description = "TC003: Geçersiz Email Kontrolü")
    public void test04_TC003_GecersizEmailKontrolu() {
        System.out.println("TC003: Geçersiz Email Kontrolü");
        Driver.getDriver().navigate().refresh();
        ReusableMethods.bekle(1);

        usersPage.nameBox
                .sendKeys(faker.name().fullName());
        usersPage.emailBox
                .sendKeys("invalid-email.@co");
        usersPage.saveButton
                .click();
        ReusableMethods.bekle(1);

        boolean emailError = Driver.getDriver().getPageSource().contains("invalid email");
        softAssert.assertTrue(emailError, "BUG_015: Sistem geçersiz e-posta formatını kabul etti!");
        softAssert.assertAll();
    }

    @Test(priority = 5, description = "TC004: Şifre Uyuşmazlığı Kontrolü")
    public void test05_TC004_SifreUyusmazligiKontrolu() {
        System.out.println("TC004: Şifre Uyuşmazlığı Kontrolü");
        Driver.getDriver().navigate().refresh();
        ReusableMethods.bekle(1);

        usersPage.passwordBox
                .sendKeys("Pass123!");
        usersPage.passwordConfirmationBox
                .sendKeys("FarkliSifre456!");
        usersPage.saveButton
                .click();
        ReusableMethods.bekle(1);

        boolean mismatchError = Driver.getDriver().getPageSource()
                .contains("does not match");
        softAssert.assertTrue(mismatchError, "BUG_016: Şifreler farklı olmasına rağmen hata mesajı verilmedi!");
        softAssert.assertAll();
    }

    @Test(priority = 6, description = "TC005: Geçersiz Telefon Kontrolü")
    public void test06_TC005_GecersizTelefonKontrolu() {
        System.out.println("TC005: Geçersiz Telefon Kontrolü");
        Driver.getDriver().navigate().refresh();
        ReusableMethods.bekle(1);

        usersPage.nameBox
                .sendKeys(faker.name().fullName());
        usersPage.phoneBox
                .sendKeys("abc123");
        usersPage.saveButton
                .click();
        ReusableMethods.bekle(1);

        boolean phoneError = Driver.getDriver().getPageSource()
                .contains("valid phone");
        softAssert.assertTrue(phoneError, "BUG_017: Geçersiz telefon numarası formatı(abc123) için hata mesajı görüntülenemedi!");
        softAssert.assertAll();
    }

    @Test(priority = 7, description = "TC007: Avatar Delete Butonu Kontrolü")
    public void test07_TC007_AvatarDeleteButonuKontrolu() {
        System.out.println("TC007: Avatar Delete Butonu Kontrolü");
        Driver.getDriver().navigate().refresh();
        ReusableMethods.bekle(1);

        String filePath = System.getProperty("user.dir") + "/src/test/resources/avatarrrr.jfif";
        usersPage.avatarUploadInput
                .sendKeys(filePath);
        ReusableMethods.bekle(2);

        List<WebElement> deleteButtons = Driver.getDriver().findElements(By.className("delete-btn-class"));
        softAssert.assertFalse(deleteButtons.isEmpty(), "BUG_019: Dosya yüklendikten sonra 'Sil' (Delete) butonu DOM'da bulunamadı!");
        softAssert.assertAll();
    }

    @AfterClass
    public void tearDown() {
        if (Driver.getDriver() != null) {
            Driver.quitDriver();
        }
    }
}