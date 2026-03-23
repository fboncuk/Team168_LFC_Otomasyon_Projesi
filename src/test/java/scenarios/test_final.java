package scenarios;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LcfAdminPage.AdminBodyPage;
import pages.LcfHomePage.SignButonsPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;
import utilities.TestBaseRapor;

public class test_final extends TestBaseRapor {

    AdminBodyPage adminBodyPage;
    SignButonsPage signButonsPage;

    @BeforeMethod
    public void setup() {
        adminBodyPage = new AdminBodyPage();
        signButonsPage = new SignButonsPage();

        // 1. Admin Dashboard sayfasına git
        Driver.getDriver().get(ConfigReader.getProperty("DasUrl"));

        // HIZLANDIRMA: Beklemeyi geçici olarak kapat
        Driver.getDriver().manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(0));

        try {
            // 2. Eğer login kutusu varsa giriş yap
            if (signButonsPage.emailKutusu.isDisplayed()) {
                signButonsPage.emailKutusu.sendKeys(ConfigReader.getProperty("T11AdminMail"));
                signButonsPage.passwordKutusu.sendKeys(ConfigReader.getProperty("T11AdminPassword"));
                signButonsPage.signInButtonOnay.click();
            }
        } catch (Exception e) {
            // Zaten içerideyiz
            System.out.println("Oturum aktif, login atlandı.");
        }

        // 3. GÜVENLİK: Beklemeyi normale döndür
        Driver.getDriver().manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(15));
    }

    @AfterMethod
    public void tearDown() {
        //Driver.quitDriver();
    }

    @Test
    public void US44_TC01_ProfilMenusuTetiklemeTutarlilikTesti() {

        extentTest = extentReports.createTest("TC01 - Profil Dropdown Menü Tetikleme ve UI Tutarlılık Testi");
        SoftAssert softAssert = new SoftAssert();

        // Admin kullanıcısı isim alanına tıklanarak dropdown menünün açıldığı doğrulanır (Beklenen davranış)
        adminBodyPage.profileNameText.click();

        // İsim alanı tıklandığında menü açılmazsa bu durum bir UI tutarsızlığı (BUG) olarak kaydedilir
        softAssert.assertTrue(adminBodyPage.profileLogoutOption.isDisplayed(),
                "HATA/BUG: Admin kullanıcısı isim alanına tıklandığında menü açılmıyor!");

        // Menünün isim alanının yanındaki boşlukta tetiklenip tetiklenmediği kontrol edilir
        if (!adminBodyPage.profileLogoutOption.isDisplayed()) {

            System.out.println("BİLGİ: İsim tıklandığında menü açılmadı şimdi boşluk alanı deneniyor");

            // Boşluk alanına tıklanır
            adminBodyPage.profileDropdownButton.click();
            ReusableMethods.bekle(4);

            // Menünün sadece bu alanda çalışıp çalışmadığı teyit edilir
            boolean isMenuOpenedByClickingSpace = adminBodyPage.profileLogoutOption.isDisplayed();

            if (isMenuOpenedByClickingSpace) {
                System.out.println("TEYİT: Menü sadece boşluk alanında tetikleniyor.");
            }

            softAssert.assertTrue(isMenuOpenedByClickingSpace, "HATA: Boşluk alanı da menüyü tetiklemiyor!");
        }

        softAssert.assertAll();
    }

    @Test
    public void US44_TC02_AdminLogoutVeAnaSayfaYonlendirmeTesti() {

        extentTest = extentReports.createTest("TC02 - Güvenli Çıkış (Logout) ve Ana Sayfa Yönlendirme Testi");
        SoftAssert softAssert = new SoftAssert();

        // Profil menüsünü tetiklemek için dropdown alanına boşluk alanına tıklanır
        adminBodyPage.profileDropdownButton.click();
        // Açılan menüden 'Logout' seçeneği seçilerek oturum sonlandırılır
        adminBodyPage.profileLogoutOption.click();
        ReusableMethods.bekle(4);

        // Kullanıcının HomePage'e yönlendirilip yönlendirilmediği doğrulanır
        String expectedUrl = ConfigReader.getProperty("LfcUrl");
        String actualUrl = Driver.getDriver().getCurrentUrl();

        // URL doğrulaması yapılır eşleşmeme durumunda hata mesajı raporlanır
        softAssert.assertEquals(actualUrl,expectedUrl, "HATA: Logout işlemi sonrası ana sayfaya yönlendirme yapılamadı!");

        softAssert.assertAll();

    }
    @Test
    public void US44_TC03_LogoutSonrasiGeriButonuIleYetkisizErisimTesti() {

        extentTest = extentReports.createTest("TC03 - Logout Sonrası Geri Butonu ile Yetkisiz Erişim Güvenlik Testi");
        SoftAssert softAssert = new SoftAssert();

        // Profil menüsünü tetiklemek için dropdown alanına boşluk alanına tıklanır
        adminBodyPage.profileDropdownButton.click();
        ReusableMethods.bekle(4);
        // Açılan menüden 'Logout' seçeneği seçilerek oturum sonlandırılır
        adminBodyPage.profileLogoutOption.click();
        // Tarayıcıda 'Geri' butonuna basılarak önceki sayfaya dönülmeye çalışılır
        Driver.getDriver().navigate().back();
//        ReusableMethods.bekle(2);


        String actualUrl = Driver.getDriver().getCurrentUrl();

        // Eğer sistem güvenliyse, geri basıldığında URL "/admin" İÇERMEMELİDİR.
        // Eğer actualUrl hala "/admin" içeriyorsa bu test FAIL verecek ve mesajını yazacaktır.
        softAssert.assertFalse(actualUrl.toLowerCase().contains("/admin"),
                "BUG: Kullanıcı çıkış yaptıktan sonra geri butonu ile admin sayfasına tekrar erişebiliyor!");
        softAssert.assertAll();

    }

}
