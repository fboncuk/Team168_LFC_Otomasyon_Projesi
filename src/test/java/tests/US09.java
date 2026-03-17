package tests;

import com.github.javafaker.Faker;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.LcfHomePage.SignButonsPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

import java.time.Duration;


public class US09 {

    //Class level'da Obje oluştur
    SignButonsPage signButonsPage;
    Faker faker;
    SoftAssert softAssert;
    WebDriverWait wait;

    @BeforeClass
    public void setup() {
        signButonsPage = new SignButonsPage();
        faker = new Faker();
        wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
    }

    @BeforeMethod
    public void goToResetPage() {

        Driver.getDriver().get(ConfigReader.getProperty("ResetUrl"));
        softAssert = new SoftAssert();

    }

    @AfterClass
    public void teardown() {
        Driver.quitDriver();
    }


    @Test(priority = 1, description = "TC_01 : RLogin sayfasinda link gorunurluk ve tıklama testi")
    public void US09_TC01_LoginSayfasindanPassResetSayfasinaErisim() {

        Driver.getDriver().get(ConfigReader.getProperty("LoginUrl"));

        //Login sayfasından Forgot Password Texti Görünür ve tıklanabilir mi?
        softAssert.assertTrue(signButonsPage.forgotPasswordLink.isDisplayed());
        softAssert.assertTrue(signButonsPage.forgotPasswordLink.isEnabled());

        //Login sayfasından Forgot Password Linkine tıkla
        signButonsPage.forgotPasswordLink.click();

        //Password Reset Sayfasında olduğunu doğrula
        softAssert.assertTrue(signButonsPage.resetPasswordPageEmailTextBox.isDisplayed());

        softAssert.assertAll();

    }


    @Test(priority = 2, description = "TC_02 : Kayitli email ile reset maili alınması testi")
    public void US09_TC02_KayitliEmailIlePassResetTalebi() {

        // Email gir
        signButonsPage.resetPasswordPageEmailTextBox.clear();
        signButonsPage.resetPasswordPageEmailTextBox.sendKeys(ConfigReader.getProperty("T09UserMail"));

        // Send Reset Link tıkla
        signButonsPage.resetPasswordPageSendPasswordResetButton.click();

        // Popup için bekle
        wait.until(ExpectedConditions.alertIsPresent());


        // Alert'i yakala
        Alert alert = Driver.getDriver().switchTo().alert();

        String alertText = alert.getText();

        // BUG kontrolü yap
        if (!alertText.contains("Email sent")) {

            System.out.println("BUG Tespit Edildi : " + alertText);

            ReusableMethods.alertVarkenScreenshot("BUG_030_T009");

        }
        softAssert.assertTrue(
                alertText.contains("Email sent"),
                "Email gönderilemedi. Gelen Mesaj : " + alertText
        );

        alert.accept();

        softAssert.assertAll();


    }

    @Test(priority = 3, description = "TC_03 : Email format validasyon testi")
    public void US_09_TC03_EmailFormatValidasyonTEsti() {


        //hatalı formatta bir maili mail boxa gir -->  mailqmail.com -->configreader : LcfGecersizMail =mailqmail.com
        signButonsPage.resetPasswordPageEmailTextBox.sendKeys(ConfigReader.getProperty("LcfGecersizMail"));

        //Send Reset Reset Link butonuna tıkla ve hata mesajını al
        signButonsPage.resetPasswordPageSendPasswordResetButton.click();

        //Mesaj uyarısını String olarak tanımladık.
        String validationMessage =
                signButonsPage.resetPasswordPageEmailTextBox.getAttribute("validationMessage");

        //Mesajı yazdıralım
        System.out.println(validationMessage);

        softAssert.assertTrue(!validationMessage.isEmpty(), "Validation Message is not Displayed");

        ReusableMethods.tarihliTumSayfaResimCek(Driver.getDriver(), "US09_TC03_ValidationMessage");

        softAssert.assertAll();

    }

    @Test(priority = 4, description = "TC_04 Sistem email var/yok bilgisini ifşa etmemeli (Kayıtlı olmayan email - Privacy Test)")
    public void us09_TC04_unRegisteredEmailprivacy_Test() {


        //Faker kütüphane ile kayıtsız email oluşturup kullanalım.
        signButonsPage.resetPasswordPageEmailTextBox.clear();
        signButonsPage.resetPasswordPageEmailTextBox.sendKeys(faker.internet().emailAddress());

        //Send Reset Reset Link butonuna tıkla, bekle ve hata mesajını al
        signButonsPage.resetPasswordPageSendPasswordResetButton.click();

        wait.until(ExpectedConditions.visibilityOf(
                signButonsPage.feedbackMessageInvalidEmailInResetPageTextBox
        ));

        //Beklenen ve gerçekleşen sonuçları belirtelim ve Assert ile denetleyelim.
        // OWASP Top10’a uygun mesaj kontrolü
        String expectedAlertMessage = "If your account exists, a password reset link has been sent to the provided e-mail address.";
        String actualAlertMessage = signButonsPage.feedbackMessageInvalidEmailInResetPageTextBox.getText();

        //çıkan alerti consola yazdır
        System.out.println("Görünen uyarı : " + actualAlertMessage);

        if(!actualAlertMessage.equals(expectedAlertMessage)){

            System.out.println("BUG Tespit edildi → Privacy Leak");

            ReusableMethods.tarihliTumSayfaResimCek(
                    Driver.getDriver(),
                    "BUG_031_T009"
            );
        }

        softAssert.assertEquals(actualAlertMessage, expectedAlertMessage,
                "Privacy Bug → Sistem email var/yok bilgisini ifşa ediyor");

        softAssert.assertAll();


    }


     //------------------- TC05 – TC11 Placeholder Tests -------------------

    @Test(priority = 5, description = "TC_05 : Reset link yalnızca bir kez kullanılabilmeli testi TC02 sonrası BLOCKED")
    public void US09_TC05_ResetLinkOnceOnly() {
        //Blocked Testlerde skip kullanılır
        throw new SkipException("Test blocked: TC02 sonrası çalıştırılacak");
    }

    @Test(priority = 6, description = "TC_06 : Şifre gereksinimlerine uygun yeni şifre oluşturma testi TC02 sonrası BLOCKED")
    public void US09_TC06_NewPasswordRequirements() {
        //Blocked Testlerde skip kullanılır
        throw new SkipException("Test blocked: TC02 sonrası çalıştırılacak");
    }

    @Test(priority = 7, description = "TC_07 : Şifre gereksinimlerini karşılamayan giriş testi TC02 sonrası BLOCKED")
    public void US09_TC07_InvalidPasswordRequirements() {
        //Blocked Testlerde skip kullanılır
        throw new SkipException("Test blocked: TC02 sonrası çalıştırılacak");
    }

    @Test(priority = 8, description = "TC_08 : Şifre ve confirm eşleşme kontrolü testi (Password & Confirm mismatch) TC02 sonrası BLOCKED")
    public void US09_TC08_PasswordConfirmMismatch() {
        //Blocked Testlerde skip kullanılır
        throw new SkipException("Test blocked: TC02 sonrası çalıştırılacak");
    }

    @Test(priority = 9, description = "TC_09 : Eski Şifre ile kullanıcı girişi yapılamaması testi (Authentication Flow Test) TC02 sonrası BLOCKED")
    public void US09_TC09_OldPasswordLoginNotAllowed() {
        //Blocked Testlerde skip kullanılır
        throw new SkipException("Test blocked: TC02 sonrası çalıştırılacak");
    }

    @Test(priority = 10, description = "TC_10 : Eski Şifre ile kullanıcı girilmesi durumunda Privacy kontrolü (Privacy Test) TC02 sonrası BLOCKED")
    public void US09_TC10_OldPasswordPrivacyCheck() {
        //Blocked Testlerde skip kullanılır
        throw new SkipException("Test blocked: TC02 sonrası çalıştırılacak");
    }

    @Test(priority = 11, description = "TC_11 : Yeni oluşturulan Şifre ile kullanıcı girişi yapılmalı (Authentication Flow Test) TC02 sonrası BLOCKED")
    public void US09_TC11_NewPasswordLogin() {
        //Blocked Testlerde skip kullanılır
        throw new SkipException("Test blocked: TC02 sonrası çalıştırılacak");
    }


    @Test(priority = 12, description = "Password Reset Sayfasının içerik kontolü (UI / Branding inconsistency)")
    public void US09_TC12_PasswordResetPageIcerikKontrolTesti() {

        //Header'daki site adını kontrol et (logo yok, text vethouse)
        String actualTitle = Driver.getDriver().getTitle().trim();
        String expectedTitleKeyword = "loyalfriendcare"; // QA staging hedef

        if (!actualTitle.toLowerCase().contains(expectedTitleKeyword)) {
            System.out.println("Title uyumsuz: QA sayfa title '" + actualTitle + "' mevcut");
            ReusableMethods.tarihliTumSayfaResimCek(Driver.getDriver(), "BUG_032_T009_TitleMismatch");
        }
        softAssert.assertTrue(actualTitle.toLowerCase().contains(expectedTitleKeyword), "Page title branding hatalı");

        //Reset Password Sayfasinin Title Textini al ve Reset Password var mı test et
        String actualResetPageTitle = signButonsPage.passwordResetPageTitle.getText().trim();
        String expectedResetPageTitle = "Reset Password";

        if (!actualResetPageTitle.equals(expectedResetPageTitle)) {
            ReusableMethods.tarihliTumSayfaResimCek(Driver.getDriver(), "BUG_032_T009_ResetPageTitle");
        }
        softAssert.assertEquals(actualResetPageTitle, expectedResetPageTitle, "Reset Password kart header hatalı");

        // Header Branding uyum kontrolü
        String actualHeaderText = signButonsPage.passwordResetPageTextInsteadOfLogo.getText().trim();
        String expectedHeaderText = "loyalfriendcare.com";  // QA staging brand name

        if (!actualHeaderText.equals(expectedHeaderText)) {
            System.out.println("Header uyumsuz: QA sayfada '" + actualHeaderText + "' görünüyor");
            ReusableMethods.tarihliTumSayfaResimCek(Driver.getDriver(), "BUG_032_T009_TitleMismatch");
        }
        softAssert.assertEquals(actualHeaderText, expectedHeaderText, "Header branding hatalı");


        // Email box label kontrolü
        String actualEmailLabel = signButonsPage.resetPasswordPageEmailTextBoxLabel.getText().trim();
        String expectedEmailLabel = "E-Mail Address";

        if (!actualEmailLabel.equals(expectedEmailLabel)) {
            ReusableMethods.tarihliTumSayfaResimCek(Driver.getDriver(), "BUG_032_T009_EmailLabel");
        }
        softAssert.assertEquals(actualEmailLabel, expectedEmailLabel, "Email label hatalı");

        // Send Password Reset Button Kontrolü
        if (!signButonsPage.resetPasswordPageSendPasswordResetButton.isDisplayed() ||
                !signButonsPage.resetPasswordPageSendPasswordResetButton.isEnabled()) {
            ReusableMethods.tarihliTumSayfaResimCek(Driver.getDriver(), "BUG_032_T009_SendPasswordResetButton");
        }
        softAssert.assertTrue(signButonsPage.resetPasswordPageSendPasswordResetButton.isDisplayed(),
                "Send Password Reset Link butonu görünür değil");
        softAssert.assertTrue(signButonsPage.resetPasswordPageSendPasswordResetButton.isEnabled(),
                "Send Password Reset Link butonu aktif değil");

        // Login link kontrolü
        if (!signButonsPage.passwordResetPageLoginLink.isDisplayed()) {
            ReusableMethods.tarihliTumSayfaResimCek(Driver.getDriver(), "BUG_032_T009_LoginLink");
        }
        softAssert.assertTrue(signButonsPage.passwordResetPageLoginLink.isDisplayed(), "Login link görünür değil");

        // Register link kontrolü
        if (!signButonsPage.passwordResetPageRegisterLink.isDisplayed()) {
            ReusableMethods.tarihliTumSayfaResimCek(Driver.getDriver(), "BUG_032_T009_RegisterLink");
        }
        softAssert.assertTrue(signButonsPage.passwordResetPageRegisterLink.isDisplayed(), "Register link görünür değil");

        softAssert.assertAll();

    }
}