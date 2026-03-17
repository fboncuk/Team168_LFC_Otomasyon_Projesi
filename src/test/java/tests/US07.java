package tests;

import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LcfHomePage.SignButonsPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.Listeners;
import utilities.ReusableMethods;

@org.testng.annotations.Listeners(Listeners.class)


public class US07 {

    WebDriver driver;
    SoftAssert softAssert;
    SignButonsPage signButonsPage;
    Faker faker;

    @BeforeMethod
    public void setUp() {

        Driver.getDriver().manage().deleteAllCookies();
        Driver.getDriver().get(ConfigReader.getProperty("RegUrl"));
        softAssert = new SoftAssert();
        signButonsPage = new SignButonsPage();
        faker = new Faker();
    }


    @Test
    public void US07_TC01_RegisterFormuPozitifTest() {


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

        //Sayfadaki Sign Up butonuna tıklayın

        signButonsPage.registerPageSignUpBox.click();

        //Header bölümünde username’in göründüğünü doğrulayın

        softAssert.assertTrue(signButonsPage.headerUserName.isDisplayed(),
                "Geçerli bilgilerle kayıt yapılmasına rağmen kullanıcı ismi header bölümünde görüntülenemedi.");

        softAssert.assertAll();

    }


    @Test

    public void US07_TC02_UserNameBenzersizlikKontrolu() {


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

        //Sayfadaki Sign Up butonuna tıklayın

        signButonsPage.registerPageSignUpBox.click();

        //Sign Up Butonunun görünür olmadığını doğrulayın.

        softAssert.assertFalse(signButonsPage.homePageSignUpButton.isDisplayed(),
                "Sistemde zaten kayıtlı olan kullanıcı için tekrar kayıt yapılmasına izin verildi," +
                        "kayıt sayfasından çıkış yapılmamalıydı");

        ReusableMethods.bekle(2);
        softAssert.assertAll();


    }

    @Test

    public void US07_TC03_EmailBenzersizlikKontrolu() {


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

        //Email adres kutusunda hata mesajı olduğunu doğrulayın.

        String expectedContent = "taken";
        String actualText = signButonsPage.emailTakenErrorMessage.getText();

        softAssert.assertTrue(actualText.contains(expectedContent),
                "Kayıtlı bir email adresi girilmesine rağmen sistem hata mesajı vermedi.");

        //Kayıt işleminin gerçekleşmediğini doğrulayın

        softAssert.assertTrue(signButonsPage.registerPageSignUpBox.isDisplayed(),"Kayıt işlemi gerçekleşti");

        softAssert.assertAll();

    }

    @Test


    public void US07_TC04_YetersizKarakterSayisiIlePasswordKontrolu() {

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

        //Kayıt işleminin gerçekleşmediğini doğrulayın

        softAssert.assertTrue(signButonsPage.registerPageSignUpBox.isDisplayed(),
                "Sistem 8 karakterden az olan şifreyi kabul ederek kaydı tamamladı," +
                        "kayıt butonunun aktif kalması gerekiyordu.");

        softAssert.assertAll();


    }

    @Test

    public void US07_TC05_LimitUstuPasswordKarakterKontrolu() {

    //Register formunun password alanına maximum karakter limiti olan 24'ten fazla olacak şekilde
    // en az 1 harf,1 rakam ve 1 özel karakter içeren bir şifre girin,diğer alanlara geçerli bilgiler girin.


        String longPassword = faker.lorem().characters(24, true, true)
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

        signButonsPage.registerPageSignUpBox.click();

        //Kayıt işleminin gerçekleşmediğini doğrulayın
        String actualUrl = Driver.getDriver().getCurrentUrl();
        String expectedUrlContent = "register";

        softAssert.assertTrue(actualUrl.contains(expectedUrlContent),
                "Şifre alanında belirlenen maksimum karakter limiti aşıldığı halde sistem kaydı onayladı.");


        softAssert.assertAll();


    }

    @Test


    public void US07_TC06_RakamIcermeyenPasswordKontrolu() {


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

        signButonsPage.registerPageSignUpBox.click();

        //Kayıt işleminin gerçekleşmediğini doğrulayın

        softAssert.assertTrue(signButonsPage.registerPageSignUpBox.isDisplayed(),
                "Şifre en az bir rakam içermediği halde sistem kayıt işlemini gerçekleştirdi.");
        ReusableMethods.bekle(1);
        softAssert.assertAll();
    }

    @Test


    public void US07_TC07_HarfIcermeyenPasswordKontrolu() {

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

        //Kayıt işleminin gerçekleşmediğini doğrulayın

        softAssert.assertTrue(signButonsPage.registerPageSignUpBox.isDisplayed(),
                "Şifre en az bir harf içermediği halde sistem kayıt işlemini gerçekleştirdi.");
        ReusableMethods.bekle(1);
        softAssert.assertAll();
    }

    @Test


    public void US07_TC08_GecersizEmailFormatiKontrolu() {


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

        signButonsPage.registerPageSignUpBox.click();

        //Kayıt işleminin gerçekleşmediğini doğrulayın

        String expectedUrl = "https://loyalfriendcare.com/register";
        String actualUrl = Driver.getDriver().getCurrentUrl();

        softAssert.assertEquals(actualUrl, expectedUrl,
                "Geçersiz email formatı ile kayıt başarılı oldu," +
                        "sistemin email format validasyonu yapmadığı görüldü.");

        softAssert.assertAll();

    }

    @Test
    public void US07_TC09_PasswordIlePasswordConfirmUyusmazlikKontrolu() {


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


        String actualUrl = Driver.getDriver().getCurrentUrl();
        String expectedUrlContent = "register";

        softAssert.assertTrue(actualUrl.contains(expectedUrlContent),
                "Sistem şifre uyuşmazlığına rağmen kayıt işlemini gerçekleştirdi.");
        ReusableMethods.bekle(1);
        softAssert.assertAll();
    }
    @Test


    public void US07_TC_10_BosRegisterFormKontrolu() {


        //Register formundaki tüm alanları boş bırakın ve Sign Up butonuna tıklayın

        signButonsPage.registerPageSignUpBox.click();

        //Başarılı şekilde kayıt yapılamadığını doğrulayın

        String actualUrl = Driver.getDriver().getCurrentUrl();
        String expectedUrlContent = "register";

        softAssert.assertTrue(actualUrl.contains(expectedUrlContent),
                "Sistem boş register form ile kayıt işlemini gerçekleştirdi.");
        ReusableMethods.bekle(1);
        softAssert.assertAll();
    }


    @Test


    public void US07_TC_11_YetersizKarakterliUserNameKontrolu() {

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

        //Sign Up butonuna tıklayın.
        signButonsPage.registerPageSignUpBox.click();

        //Kayıt işleminin gerçekleşmediğini doğrulayın
        String actualUrl = Driver.getDriver().getCurrentUrl();
        String expectedUrlContent = "register";

        softAssert.assertTrue(actualUrl.contains(expectedUrlContent),
                "Sistem,user name 3 karakterden az olduğu halde kayıt işlemini gerçekleştirdi.");
        ReusableMethods.bekle(1);
        softAssert.assertAll();

    }

    @Test


    public void US07_TC_12_LimitUstuKarakterliUserNameKontrolu() {

        //Register formundaki username alanına maksimum limit olan 20 üstü karakter içeren bir veri girişi yapın.
        //Formdaki diğer alanları geçerli bilgilerle doldurun.

        String longUserName = faker.lorem().characters(21);

        signButonsPage.registerPageUserNameBox
                        .sendKeys(longUserName);
        signButonsPage.registerPageEmailBox
                        .sendKeys(faker.internet().emailAddress());
        signButonsPage.registerPagePasswordBox
                        .sendKeys(ConfigReader.getProperty("T07UserPassword"));
        signButonsPage.registerPagePasswordConfirmBox
                        .sendKeys(ConfigReader.getProperty("T07UserPassword"));

        //Sign Up butonuna tıklayın.
        signButonsPage.registerPageSignUpBox.click();

        //Kayıt işleminin gerçekleşmediğini doğrulayın
        String actualUrl = Driver.getDriver().getCurrentUrl();
        String expectedUrlContent = "register";

        softAssert.assertTrue(actualUrl.contains(expectedUrlContent),
                "Sistem,user name 20 karakterden fazla olduğu halde kayıt işlemini gerçekleştirdi.");

        ReusableMethods.bekle(1);
        softAssert.assertAll();

    }

    @AfterClass
    public void tearDown() {
        Driver.quitDriver();
    }

}
