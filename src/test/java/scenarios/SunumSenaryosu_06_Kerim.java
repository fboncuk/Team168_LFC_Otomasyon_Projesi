package scenarios;

import com.github.javafaker.Faker;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LcfAdminPage.DashboardPage;
import pages.LcfHomePage.SignButonsPage;
import utilities.*;

import java.time.Duration;

public class SunumSenaryosu_06_Kerim extends TestBaseRapor {

    SoftAssert softAssert;
    SignButonsPage signButonsPage;
    Faker faker;
    WebDriverWait wait;
    DashboardPage dashboardPage;

    @BeforeMethod
    public void setUp() {

        Driver.getDriver().manage().deleteAllCookies();
        softAssert = new SoftAssert();
        signButonsPage = new SignButonsPage();
        faker = new Faker();
        wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
    }

    @AfterClass
    public void tearDown() {
        Driver.quitDriver();
    }



    @Test (priority = 1)
    public void US07_TC01_RegisterFormuPozitifTest() {
        Driver.getDriver().get(ConfigReader.getProperty("RegUrl"));

        extentTest = extentReports.createTest("US07_TC01_RegisterFormuPozitifTest");

        //Açılan sayfadaki Register formundaki User Name, E-Mail Address,Password,Confirm //Password alanlarına geçerli bilgileri girin.

        String validUserName = faker.name().lastName();

        signButonsPage.registerPageUserNameBox.
                sendKeys(validUserName);
        signButonsPage.registerPageEmailBox.
                sendKeys(faker.internet().emailAddress());
        signButonsPage.registerPagePasswordBox.
                sendKeys(ConfigReader.getProperty("T07AdminPassword"));
        signButonsPage.registerPagePasswordConfirmBox.
                sendKeys(ConfigReader.getProperty("T07AdminPassword"));

        ReusableMethods.bekle(7);

        //Sayfadaki Sign Up butonuna tıklayın

        signButonsPage.registerPageSignUpBox.click();

        //Header bölümünde username’in göründüğünü doğrulayın

        softAssert.assertTrue(signButonsPage.headerUserName.isDisplayed(),
                "Geçerli bilgilerle kayıt yapılmasına rağmen kullanıcı ismi header bölümünde görüntülenemedi.");

        softAssert.assertAll();

    }

    @Test (priority = 2)
    public void US07_TC02_UserNameBenzersizlikKontrolu() {
        Driver.getDriver().get(ConfigReader.getProperty("RegUrl"));
        extentTest = extentReports.createTest("US07_TC02_UserNameBenzersizlikKontrolu");

        //Register formunun username alanına,sistemde kayıtlı bir User Name, diğer alanlara
        // geçerli veriler girin.

        String registeredUserName = "gokcen";
        signButonsPage.registerPageUserNameBox.
                sendKeys(registeredUserName);
        signButonsPage.registerPageEmailBox.
                sendKeys(faker.internet().emailAddress());
        signButonsPage.registerPagePasswordBox.
                sendKeys(ConfigReader.getProperty("T07AdminPassword"));
        signButonsPage.registerPagePasswordConfirmBox.
                sendKeys(ConfigReader.getProperty("T07AdminPassword"));

        ReusableMethods.bekle(7);

        //Sayfadaki Sign Up butonuna tıklayın

        signButonsPage.registerPageSignUpBox.click();

        //Sign Up Butonunun görünür olmadığını doğrulayın.

        softAssert.assertFalse(signButonsPage.homePageSignUpButton.isDisplayed(),
                "Sistemde zaten kayıtlı olan kullanıcı için tekrar kayıt yapılmasına izin verildi," +
                        "kayıt sayfasından çıkış yapılmamalıydı");

        ReusableMethods.bekle(2);
        softAssert.assertAll();


    }

    @Test (priority = 3)
    public void US07_TC03_EmailBenzersizlikKontrolu() {
        Driver.getDriver().get(ConfigReader.getProperty("RegUrl"));
        extentTest = extentReports.createTest("US07_TC03_EmailBenzersizlikKontrolu");

        //Register formunun email alanına,sistemde kayıtlı olan bir email adresini,
        // diğer alanlara geçerli bilgiler girin.


        signButonsPage.registerPageUserNameBox.
                sendKeys(faker.name().lastName());
        signButonsPage.registerPageEmailBox.
                sendKeys(ConfigReader.getProperty("T07UserMail"));
        signButonsPage.registerPagePasswordBox.
                sendKeys(ConfigReader.getProperty("T07AdminPassword"));
        signButonsPage.registerPagePasswordConfirmBox.
                sendKeys(ConfigReader.getProperty("T07AdminPassword"));

        //Sayfadaki Sign Up butonuna tıklayın


        signButonsPage.registerPageSignUpBox.click();
        ReusableMethods.bekle(7);
        //Email adres kutusunda hata mesajı olduğunu doğrulayın.

        String expectedContent = "taken";
        String actualText = signButonsPage.emailTakenErrorMessage.getText();

        softAssert.assertTrue(actualText.contains(expectedContent),
                "Kayıtlı bir email adresi girilmesine rağmen sistem hata mesajı vermedi.");

        //Kayıt işleminin gerçekleşmediğini doğrulayın

        softAssert.assertTrue(signButonsPage.registerPageSignUpBox.isDisplayed(), "Kayıt işlemi gerçekleşti");


        softAssert.assertAll();

    }

    @Test (priority = 4)
    public void US07_TC04_YetersizKarakterSayisiIlePasswordKontrolu() {
        Driver.getDriver().get(ConfigReader.getProperty("RegUrl"));
        extentTest = extentReports.createTest("US07_TC04_YetersizKarakterSayisiIlePasswordKontrolu");

        //Register formunun password alanına 8 karakterden az olacak şekilde en az 1 harf,1 rakam ve 1 özel karakter
        // içeren şifre girin,diğer alanlara geçerli bilgiler girin.

        String shortPassword = faker.lorem().characters(4, true, true)
                + faker.number().digit() + "!";

        signButonsPage.registerPageUserNameBox.
                sendKeys(faker.name().lastName());
        signButonsPage.registerPageEmailBox.
                sendKeys(faker.internet().emailAddress());
        signButonsPage.registerPagePasswordBox.
                sendKeys(shortPassword);
        signButonsPage.registerPagePasswordConfirmBox.
                sendKeys(shortPassword);

        //Sayfadaki Sign Up butonuna tıklayın


        signButonsPage.registerPageSignUpBox.click();
        ReusableMethods.bekle(7);
        //Kayıt işleminin gerçekleşmediğini doğrulayın

        softAssert.assertTrue(signButonsPage.registerPageSignUpBox.isDisplayed(),
                "Sistem 8 karakterden az olan şifreyi kabul ederek kaydı tamamladı," +
                        "kayıt butonunun aktif kalması gerekiyordu.");

        softAssert.assertAll();


    }

    @Test (priority = 5)
    public void US07_TC05_LimitUstuPasswordKarakterKontrolu() {
        Driver.getDriver().get(ConfigReader.getProperty("RegUrl"));
        extentTest = extentReports.createTest("US07_TC05_LimitUstuPasswordKarakterKontrolu");

        //Register formunun password alanına maximum karakter limiti olan 24'ten fazla olacak şekilde
        // en az 1 harf,1 rakam ve 1 özel karakter içeren bir şifre girin,diğer alanlara geçerli bilgiler girin.


        String longPassword = faker.lorem().characters(70, true, true)
                + faker.number().digit() + "!";

        signButonsPage.registerPageUserNameBox.
                sendKeys(faker.name().lastName());
        signButonsPage.registerPageEmailBox.
                sendKeys(faker.internet().emailAddress());
        signButonsPage.registerPagePasswordBox.
                sendKeys(longPassword);
        signButonsPage.registerPagePasswordConfirmBox.
                sendKeys(longPassword);

        //Sayfadaki Sign Up butonuna tıklayın

        ReusableMethods.bekle(7);

        signButonsPage.registerPageSignUpBox.click();

        //Kayıt işleminin gerçekleşmediğini doğrulayın
        String actualUrl = Driver.getDriver().getCurrentUrl();
        String expectedUrlContent = "register";

        softAssert.assertTrue(actualUrl.contains(expectedUrlContent),
                "Şifre alanında belirlenen maksimum karakter limiti aşıldığı halde sistem kaydı onayladı.");


        softAssert.assertAll();


    }

    @Test (priority = 6)
    public void US07_TC06_RakamIcermeyenPasswordKontrolu() {
        Driver.getDriver().get(ConfigReader.getProperty("RegUrl"));
        extentTest = extentReports.createTest("US07_TC06_RakamIcermeyenPasswordKontrolu");

        //Register formunda password alanına en az 1 harf ve 1 özel karakter içeren
        // 8 karakterli bir şifre ,diğer alanlara geçerli bilgiler girin.

        String noDigitPassword = faker.lorem()
                .characters(7, true, false) + "!";

        signButonsPage.registerPageUserNameBox.
                sendKeys(faker.name().lastName());
        signButonsPage.registerPageEmailBox.
                sendKeys(faker.internet().emailAddress());
        signButonsPage.registerPagePasswordBox.
                sendKeys(noDigitPassword);
        signButonsPage.registerPagePasswordConfirmBox.
                sendKeys(noDigitPassword);

        //Sayfadaki Sign Up butonuna tıklayın

        ReusableMethods.bekle(3);
        signButonsPage.registerPageSignUpBox.click();

        ReusableMethods.bekle(3);

        //Kayıt işleminin gerçekleşmediğini doğrulayın

        softAssert.assertTrue(signButonsPage.registerPageSignUpBox.isDisplayed(),
                "Şifre en az bir rakam içermediği halde sistem kayıt işlemini gerçekleştirdi.");
        ReusableMethods.bekle(1);
        softAssert.assertAll();
    }

    @Test (priority = 7)
    public void US07_TC07_HarfIcermeyenPasswordKontrolu() {
        Driver.getDriver().get(ConfigReader.getProperty("RegUrl"));
        extentTest = extentReports.createTest("US07_TC07_HarfIcermeyenPasswordKontrolu");

//Register formunda password alanına en az 1 rakam ve 1 özel karakter içeren
// 8 karakterli bir şifre ,diğer alanlara geçerli bilgiler girin.

        String noLetterPassword = faker.number().digits(7) + "!";

        signButonsPage.registerPageUserNameBox.
                sendKeys(faker.name().lastName());
        signButonsPage.registerPageEmailBox.
                sendKeys(faker.internet().emailAddress());
        signButonsPage.registerPagePasswordBox.
                sendKeys(noLetterPassword);
        signButonsPage.registerPagePasswordConfirmBox.
                sendKeys(noLetterPassword);

        //Sayfadaki Sign Up butonuna tıklayın

        signButonsPage.registerPageSignUpBox.click();
        ReusableMethods.bekle(5);

        //Kayıt işleminin gerçekleşmediğini doğrulayın

        softAssert.assertTrue(signButonsPage.registerPageSignUpBox.isDisplayed(),
                "Şifre en az bir harf içermediği halde sistem kayıt işlemini gerçekleştirdi.");
        ReusableMethods.bekle(1);
        softAssert.assertAll();
    }

    @Test (priority = 8)
    public void US07_TC08_GecersizEmailFormatiKontrolu() {
        Driver.getDriver().get(ConfigReader.getProperty("RegUrl"));
        extentTest = extentReports.createTest("US07_TC08_GecersizEmailFormatiKontrolu");

// GEÇERSİZ EMAİL ADRESİNE RAĞMEN KAYIT TAMAMLANMAKTADIR.
//Register formunda password alanına geçersiz formatta email adresi, diğer alanlara geçerli veriler girin.

        String wrongEmailFormat = faker.name().lastName().toLowerCase() + "com@gmail";
        signButonsPage.registerPageUserNameBox.
                sendKeys(faker.name().lastName());
        signButonsPage.registerPageEmailBox.
                sendKeys(wrongEmailFormat);
        signButonsPage.registerPagePasswordBox.
                sendKeys(ConfigReader.getProperty("T07UserPassword"));
        signButonsPage.registerPagePasswordConfirmBox.
                sendKeys(ConfigReader.getProperty("T07UserPassword"));

        //Sayfadaki Sign Up butonuna tıklayın
        ReusableMethods.bekle(3);

        signButonsPage.registerPageSignUpBox.click();
        ReusableMethods.bekle(5);

        //Kayıt işleminin gerçekleşmediğini doğrulayın

        String expectedUrl = "https://loyalfriendcare.com/register";
        String actualUrl = Driver.getDriver().getCurrentUrl();

        softAssert.assertEquals(actualUrl, expectedUrl,
                "Geçersiz email formatı ile kayıt başarılı oldu," +
                        "sistemin email format validasyonu yapmadığı görüldü.");

        softAssert.assertAll();

    }

    @Test ( priority = 9)
    public void US07_TC09_PasswordIlePasswordConfirmUyusmazlikKontrolu() {
        Driver.getDriver().get(ConfigReader.getProperty("RegUrl"));
        extentTest = extentReports.createTest("US07_TC09_PasswordIlePasswordConfirmUyusmazlikKontrolu");


        signButonsPage.registerPageUserNameBox.
                sendKeys(faker.name().lastName());
        signButonsPage.registerPageEmailBox.
                sendKeys(faker.internet().emailAddress());
        signButonsPage.registerPagePasswordBox.
                sendKeys(ConfigReader.getProperty("T07AdminPassword"));
        signButonsPage.registerPagePasswordConfirmBox.
                sendKeys(faker.lorem().
                        characters(7, true, true) + "!");

        signButonsPage.registerPageSignUpBox.click();

        ReusableMethods.bekle(5);


        String actualUrl = Driver.getDriver().getCurrentUrl();
        String expectedUrlContent = "register";

        softAssert.assertTrue(actualUrl.contains(expectedUrlContent),
                "Sistem şifre uyuşmazlığına rağmen kayıt işlemini gerçekleştirdi.");
        ReusableMethods.bekle(1);
        softAssert.assertAll();
    }

    @Test (priority = 10)
    public void US07_TC_10_BosRegisterFormKontrolu() {
        Driver.getDriver().get(ConfigReader.getProperty("RegUrl"));
        extentTest = extentReports.createTest("US07_TC_10_BosRegisterFormKontrolu");


        //Register formundaki tüm alanları boş bırakın ve Sign Up butonuna tıklayın

        signButonsPage.registerPageSignUpBox.click();

        ReusableMethods.bekle(7);

        //Başarılı şekilde kayıt yapılamadığını doğrulayın

        String actualUrl = Driver.getDriver().getCurrentUrl();
        String expectedUrlContent = "register";

        softAssert.assertTrue(actualUrl.contains(expectedUrlContent),
                "Sistem boş register form ile kayıt işlemini gerçekleştirdi.");
        ReusableMethods.bekle(1);
        softAssert.assertAll();
    }

    @Test (priority = 11)
    public void US07_TC_11_YetersizKarakterliUserNameKontrolu() {
        Driver.getDriver().get(ConfigReader.getProperty("RegUrl"));
        extentTest = extentReports.createTest("US07_TC_11_YetersizKarakterliUserNameKontrolu");

        //Register formundaki username alanına 2 karakterli bir veri girişi yapın.
        //Formdaki diğer alanları geçerli bilgilerle doldurun.

        String shortUserName = faker.lorem().characters(2);

        signButonsPage.registerPageUserNameBox
                .sendKeys(shortUserName);
        signButonsPage.registerPageEmailBox
                .sendKeys(faker.internet().emailAddress());
        signButonsPage.registerPagePasswordBox
                .sendKeys(ConfigReader.getProperty("T07UserPassword"));
        signButonsPage.registerPagePasswordConfirmBox
                .sendKeys(ConfigReader.getProperty("T07UserPassword"));

        ReusableMethods.bekle(3);

        //Sign Up butonuna tıklayın.
        signButonsPage.registerPageSignUpBox.click();
        ReusableMethods.bekle(4);

        //Kayıt işleminin gerçekleşmediğini doğrulayın
        String actualUrl = Driver.getDriver().getCurrentUrl();
        String expectedUrlContent = "register";

        softAssert.assertTrue(actualUrl.contains(expectedUrlContent),
                "Sistem,user name 3 karakterden az olduğu halde kayıt işlemini gerçekleştirdi.");
        ReusableMethods.bekle(1);
        softAssert.assertAll();

    }

    @Test (priority = 12)
    public void US07_TC_12_LimitUstuKarakterliUserNameKontrolu() {
        Driver.getDriver().get(ConfigReader.getProperty("RegUrl"));
        extentTest = extentReports.createTest("US07_TC_12_LimitUstuKarakterliUserNameKontrolu");

        //Register formundaki username alanına maksimum limit olan 20 üstü karakter içeren bir veri girişi yapın.
        //Formdaki diğer alanları geçerli bilgilerle doldurun.

        String longUserName = faker.lorem().characters(81);

        signButonsPage.registerPageUserNameBox
                .sendKeys(longUserName);
        signButonsPage.registerPageEmailBox
                .sendKeys(faker.internet().emailAddress());
        signButonsPage.registerPagePasswordBox
                .sendKeys(ConfigReader.getProperty("T07UserPassword"));
        signButonsPage.registerPagePasswordConfirmBox
                .sendKeys(ConfigReader.getProperty("T07UserPassword"));

        ReusableMethods.bekle(3);

        //Sign Up butonuna tıklayın.
        signButonsPage.registerPageSignUpBox.click();
        ReusableMethods.bekle(4);

        //Kayıt işleminin gerçekleşmediğini doğrulayın
        String actualUrl = Driver.getDriver().getCurrentUrl();
        String expectedUrlContent = "register";

        softAssert.assertTrue(actualUrl.contains(expectedUrlContent),
                "Sistem,user name 20 karakterden fazla olduğu halde kayıt işlemini gerçekleştirdi.");

        ReusableMethods.bekle(1);
        softAssert.assertAll();

    }

    @Test(priority = 13, description = "Login ayfası temel öğeler görünürlük ve doğrualama testi")
    public void US20_TC01_LoginPage_Function_Validation_Test() {
        extentTest = extentReports.createTest("US20_TC01_LoginPage_Function_Validation_Test");
        Driver.getDriver().get(ConfigReader.getProperty("LoginUrl"));

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
        ReusableMethods.bekle(3);
        softAssert.assertAll();

    }

    @Test(priority = 14, description = "Login sayfasında tümü boş bilgilerle giriş denemesi")
    public void US20_TC02_LoginPage_Negative_Function_Validation_Test_1() {
        extentTest = extentReports.createTest("US20_TC01_LoginPage_Function_Validation_Test");
        Driver.getDriver().get(ConfigReader.getProperty("LoginUrl"));
        signButonsPage.emailKutusu.clear();
        signButonsPage.passwordKutusu.clear();
        signButonsPage.signInButtonOnay.click();

        //Mesaj uyarısını String olarak tanımladık.
        String validationMessage = signButonsPage.resetPasswordPageEmailTextBox.getAttribute("validationMessage");

        //Mesajı yazdıralım
        System.out.println(validationMessage);
        ReusableMethods.bekle(5);
        softAssert.assertTrue(!validationMessage.isEmpty(), "Validation Message is not Displayed");

        ReusableMethods.tarihliTumSayfaResimCek(Driver.getDriver(), "US20_TC02_ValidationMessage1");
        softAssert.assertAll();
    }

    @Test(priority = 15, description = "Login sayfasında admin geçerli mail ve pass boş bilgilerle giriş denemesi")
    public void US20_TC02_LoginPage_Negative_Function_Validation_Test_2() {
        extentTest = extentReports.createTest("US20_TC01_LoginPage_Function_Validation_Test");
        Driver.getDriver().get(ConfigReader.getProperty("LoginUrl"));
        signButonsPage.emailKutusu.clear();
        signButonsPage.emailKutusu.sendKeys(ConfigReader.getProperty("T09AdminMail"));
        signButonsPage.passwordKutusu.clear();
        signButonsPage.signInButtonOnay.click();
        ReusableMethods.bekle(3);

        //Mesaj uyarısını String olarak tanımladık.
        String validationMessage = signButonsPage.passwordKutusu.getAttribute("validationMessage");

        //Mesajı yazdıralım
        System.out.println(validationMessage);

        softAssert.assertTrue(!validationMessage.isEmpty(), "Validation Message is not Displayed");

        ReusableMethods.tarihliTumSayfaResimCek(Driver.getDriver(), "US20_TC02_ValidationMessage2");
        softAssert.assertAll();
    }

    @Test(priority = 16, description = "Login sayfasında boş mail ve geçerli admin yetkili maile ait pass bilgilerle giriş denemesi")
    public void US20_TC02_LoginPage_Negative_Function_Validation_Test_3() {
        extentTest = extentReports.createTest("US20_TC02_LoginPage_Function_Validation_Test");
        Driver.getDriver().get(ConfigReader.getProperty("LoginUrl"));
        signButonsPage.emailKutusu.clear();
        signButonsPage.passwordKutusu.clear();
        signButonsPage.passwordKutusu.sendKeys(ConfigReader.getProperty("T09AdminPassword"));
        signButonsPage.signInButtonOnay.click();

        //Mesaj uyarısını String olarak tanımladık.
        String validationMessage = signButonsPage.emailKutusu.getAttribute("validationMessage");

        //Mesajı yazdıralım
        System.out.println(validationMessage);
        ReusableMethods.bekle(3);
        softAssert.assertTrue(!validationMessage.isEmpty(), "Validation Message is not Displayed");

        ReusableMethods.tarihliTumSayfaResimCek(Driver.getDriver(), "US20_TC02_ValidationMessage3");
        softAssert.assertAll();
    }

    @Test(priority = 17, description = "Login sayfasında admin geçerli mail ve geçersiz pass ile giriş denemesi")
    public void US20_TC03_04_LoginPage_Negative_Function_Validation_Test_1() {
        extentTest = extentReports.createTest("US20_TC03_04_LoginPage_Negative_Function_Validation_Test_1");
        Driver.getDriver().get(ConfigReader.getProperty("LoginUrl"));
        signButonsPage.emailKutusu.clear();
        signButonsPage.emailKutusu.sendKeys(ConfigReader.getProperty("T09AdminMail"));
        signButonsPage.passwordKutusu.clear();
        signButonsPage.passwordKutusu.sendKeys(faker.internet().password());
        signButonsPage.signInButtonOnay.click();

        //Beklenen ve gerçekleşen sonuçları belirtelim ve Assert ile denetleyelim.
        ReusableMethods.bekle(3);

        String actualInvalidPasswordMessage = signButonsPage.signInPageInvalidPasswordFeedbackMessage.getText();

        //çıkan alerti consola yazdır
        System.out.println("Görünen uyarı : " + actualInvalidPasswordMessage);

        softAssert.assertTrue(!actualInvalidPasswordMessage.isEmpty(), "Validation Message is not Displayed");

        ReusableMethods.tarihliTumSayfaResimCek(Driver.getDriver(), "US20_TC03_PozitifMailNegatifPass_Test");
        softAssert.assertAll();
    }

    @Test(priority = 18, description = "Login sayfasında admin geçerli pass ve geçersiz mail ile giriş denemesi 2")
    public void US20_TC03_04_LoginPage_Negative_Function_Validation_Test_2() {
        extentTest = extentReports.createTest("US20_TC03_04_LoginPage_Negative_Function_Validation_Test_2");
        Driver.getDriver().get(ConfigReader.getProperty("LoginUrl"));
        signButonsPage.emailKutusu.clear();
        signButonsPage.emailKutusu.sendKeys(faker.internet().emailAddress());
        signButonsPage.passwordKutusu.clear();
        signButonsPage.passwordKutusu.sendKeys(ConfigReader.getProperty("T09AdminPassword"));
        signButonsPage.signInButtonOnay.click();
        wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));

        String actualInvalidPasswordMessage = signButonsPage.signInPageInvalidPasswordFeedbackMessage.getText();
        ReusableMethods.bekle(3);
        //çıkan uarıyı consola yazdır
        System.out.println("Görünen uyarı : " + actualInvalidPasswordMessage);

        softAssert.assertTrue(!actualInvalidPasswordMessage.isEmpty(), "Validation Message is not Displayed");

        ReusableMethods.tarihliTumSayfaResimCek(Driver.getDriver(), "US20_TC03_PozitifPassNegatifMail_Test");
        softAssert.assertAll();
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
        ReusableMethods.bekle(3);
        //Sign in butonuna bas
        //wait.until(ExpectedConditions.elementToBeClickable(signButonsPage.signInButtonOnay)).click();
        signButonsPage.signInButtonOnay.click();
        ReusableMethods.bekle(3);

        // Login sonrası URL bekle
        String expectedHomeUrl = ConfigReader.getProperty("LfcUrl");
//        wait.until(ExpectedConditions.urlContains(expectedHomeUrl));

        String actualHomeUrl = Driver.getDriver().getCurrentUrl().toLowerCase();
        softAssert.assertTrue(actualHomeUrl.toLowerCase().contains(expectedHomeUrl), "Login To Homepage Failed");

        ReusableMethods.tarihliTumSayfaResimCek(Driver.getDriver(), "US20_TC05_Admin_Account_SignIn_to_Homepage_Test");
        ReusableMethods.bekle(5);
//        softAssert.assertAll();
//    }
//
//    @Test(priority = 20, description = "Homepage sayfasından  admin dashboarda erişim sağlanması")
//    public void US20_TC05_Admin_Account_SignIn_to_Homepage_Test() {
//

        // Dashboard butonu görünür olana kadar bekle
        //wait.until(ExpectedConditions.visibilityOf(signButonsPage.headerUserName));
        //wait.until(ExpectedConditions.elementToBeClickable(signButonsPage.headerUserName)).click();
        signButonsPage.headerUserName.click();

        // Dashboard URL bekle
        String expectedDashboardUrl = ConfigReader.getProperty("DasUrl").toLowerCase();
        //wait.until(ExpectedConditions.urlContains(expectedDashboardUrl));

        String actualDashboardUrl = Driver.getDriver().getCurrentUrl().toLowerCase();
        softAssert.assertEquals(actualDashboardUrl, expectedDashboardUrl.toLowerCase(), "Login To Dashboard Failed");

        softAssert.assertAll();

    }

}

