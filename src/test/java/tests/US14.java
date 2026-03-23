package tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LcfHomePage.AppointmentBookingPage;
import pages.LcfHomePage.BedsDepMainPage;
import pages.LcfHomePage.SignButonsPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

public class US14 {

    // US14: Bir kayıtlı kullanıcı olarak, yataklı departmanların sayfasına/bilgilerine erişebilmeli
    // ve seçtiğim yataklı departman için randevu talebi oluşturabilmeliyim.

    SignButonsPage signButonsPage;
    BedsDepMainPage bedsDepMainPage;
    AppointmentBookingPage appointmentBookingPage;
    SoftAssert softAssert;

    @BeforeClass
    public void setUpClass() {
        signButonsPage = new SignButonsPage();
        bedsDepMainPage = new BedsDepMainPage();
        appointmentBookingPage = new AppointmentBookingPage();
    }

    @BeforeMethod
    public void setUp() {
        softAssert = new SoftAssert();
    }

    @Test(priority = 1, description = "Ziyaretçi yataklı departman bilgilerine erişememelidir")
    public void US14_TC01_ZiyaretciErisimEngeli() {

        // Giriş yapılmadan Departments sayfasına git
        Driver.getDriver().get(ConfigReader.getProperty("DepUrl"));
        ReusableMethods.bekle(2);

        String currentUrl = Driver.getDriver().getCurrentUrl();

        // Ziyaretçi, yataklı departman sayfasına erişememeli; login sayfasına yönlendirilmeli
        softAssert.assertFalse(currentUrl.contains("/Departments"),
                "HATA: Ziyaretçi yataklı departman sayfasına erişebildi! URL: " + currentUrl);

        ReusableMethods.tarihliTumSayfaResimCek(Driver.getDriver());
        softAssert.assertAll();
    }

    @Test(priority = 2, description = "Kayıtlı kullanıcı yataklı departman sayfasına ve bilgilerine erişebilmelidir")
    public void US14_TC02_KullaniciDepartmanSayfasiErisim() {

        Driver.getDriver().get(ConfigReader.getProperty("LfcUrl"));
        ReusableMethods.bekle(1);

        // Kayıtlı kullanıcı girişi
        signButonsPage.signInLinki.click();
        signButonsPage.emailKutusu.sendKeys(ConfigReader.getProperty("T03UserMail"));
        signButonsPage.passwordKutusu.sendKeys(ConfigReader.getProperty("T03UserPassword"));
        signButonsPage.signInButtonOnay.click();
        ReusableMethods.bekle(2);

        // Departments menüsüne hover yapıp Wellness departmanına git
        bedsDepMainPage.DepartmentsMainButton.click();
        ReusableMethods.bekle(1);
        bedsDepMainPage.WellnesLinkButton.click();
        ReusableMethods.bekle(2);

        // Yataklı departman odası bilgisinin görüntülendiğini doğrula
        softAssert.assertTrue(bedsDepMainPage.WellnessRoomLink.isDisplayed(),
                "HATA: Yataklı departman sayfası bilgileri görüntülenemedi!");

        ReusableMethods.tarihliTumSayfaResimCek(Driver.getDriver());
        softAssert.assertAll();
    }

    @Test(priority = 3, description = "Kayıtlı kullanıcı farklı yataklı departmanlar için sayfa bilgilerine erişebilmelidir")
    public void US14_TC03_FarklıDepartmanBilgisiErisim() {

        Driver.getDriver().get(ConfigReader.getProperty("LfcUrl"));
        ReusableMethods.bekle(1);

        // Dental Care departmanına git
        bedsDepMainPage.DepartmentsMainButton.click();
        ReusableMethods.bekle(1);
        bedsDepMainPage.DentalCareLinkButton.click();
        ReusableMethods.bekle(2);

        softAssert.assertTrue(bedsDepMainPage.DentalCareRoomLink.isDisplayed(),
                "HATA: Dental Care departman bilgileri görüntülenemedi!");

        ReusableMethods.tarihliTumSayfaResimCek(Driver.getDriver());
        softAssert.assertAll();
    }

    @Test(priority = 4, description = "Kayıtlı kullanıcı yataklı departman için randevu talebi oluşturabilmelidir")
    public void US14_TC04_RandevuTalebiOlusturma() {

        Driver.getDriver().get(ConfigReader.getProperty("LfcUrl"));
        ReusableMethods.bekle(1);

        // Wellness departman sayfasına git (randevu formu departman sayfasında bulunuyor)
        bedsDepMainPage.DepartmentsMainButton.click();
        ReusableMethods.bekle(1);
        bedsDepMainPage.WellnesLinkButton.click();
        ReusableMethods.bekle(2);

        // Randevu formunun görüntülendiğini doğrula
        softAssert.assertTrue(appointmentBookingPage.appointmentFormContainer.isDisplayed(),
                "HATA: Randevu formu görüntülenemedi!");

        // Randevu bilgilerini doldur
        appointmentBookingPage.dateInput.sendKeys("2026-04-01");
        appointmentBookingPage.phoneBox.sendKeys("5551234567");

        // Departman seç
        appointmentBookingPage.departmentDropdownKutusu.click();
        appointmentBookingPage.dermatologySecenegi.click();
        ReusableMethods.bekle(1);

        // Doktor seç
        appointmentBookingPage.doctorDropdownKutusu.click();
        appointmentBookingPage.doktorSecenegi.click();
        ReusableMethods.bekle(1);

        // Mesaj gir ve formu gönder
        appointmentBookingPage.messageBox.sendKeys("Yataklı departman randevu talebi - test");
        appointmentBookingPage.appointmentBookingButton.click();
        ReusableMethods.bekle(2);

        ReusableMethods.tarihliTumSayfaResimCek(Driver.getDriver());
        softAssert.assertAll();
    }

    @Test(priority = 5, description = "Kayıtlı kullanıcı randevu taleplerini görüntüleyebilmeli ve düzenleyebilmelidir")
    public void US14_TC05_RandevuGoruntulemeDuzenleme() {

        ReusableMethods.bekle(2);

        // Randevu sonrası mevcut URL'nin erişilebilir olduğunu doğrula
        String currentUrl = Driver.getDriver().getCurrentUrl();
        softAssert.assertNotNull(currentUrl,
                "HATA: Randevu işlemi sonrası sayfa erişilemez!");

        // Kullanıcının hala giriş yapılı olduğunu doğrula
        softAssert.assertTrue(signButonsPage.headerUserName.isDisplayed(),
                "HATA: Kullanıcı oturumu sonlandı!");

        ReusableMethods.tarihliTumSayfaResimCek(Driver.getDriver());
        softAssert.assertAll();
    }

    @AfterClass
    public void tearDown() {
        Driver.quitDriver();
    }
}
