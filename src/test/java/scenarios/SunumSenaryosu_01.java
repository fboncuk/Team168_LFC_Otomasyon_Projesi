package scenarios;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LcfHomePage.AppointmentBookingPage;
import pages.LcfHomePage.DepartmentsMainPage;
import pages.LcfHomePage.SignButonsPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

public class SunumSenaryosu_01 {

    SignButonsPage signButonsPage;
    DepartmentsMainPage departmentsMainPage;
    AppointmentBookingPage appointmentBookingPage;
    SoftAssert softAssert;

    @BeforeClass
    public void setUp() {
        softAssert = new SoftAssert();
        // Adım 1: Siteye git
        Driver.getDriver().get(ConfigReader.getProperty("LfcUrl"));
        signButonsPage = new SignButonsPage();
    }

    @Test(priority = 1)
    public void TC01_KayitOl() {

        // Sign Up butonuna tıkla
        signButonsPage.signUpLinki.click();
        ReusableMethods.bekle(5);

        // Username alanına "dtemizel" yaz
        signButonsPage.registerPageUserNameBox.sendKeys("dtemizel");
        ReusableMethods.bekle(2);

        // E-mail alanına "dtemizeltest002@hotmail.com" yaz
        signButonsPage.registerPageEmailBox.sendKeys("dtemizeltest002@hotmail.com");
        ReusableMethods.bekle(2);

        // Password alanına "Loyal.123123" yaz
        signButonsPage.registerPagePasswordBox.sendKeys("Loyal.123123");
        ReusableMethods.bekle(2);

        // Confirm Password alanına aynı şifreyi tekrar yaz
        signButonsPage.registerPagePasswordConfirmBox.sendKeys("Loyal.123123");
        ReusableMethods.bekle(2);

        // Sign Up butonuna tıkla
        signButonsPage.registerPageSignUpBox.click();
        ReusableMethods.bekle(5);

        //  Üyeliğin oluşturulduğunu doğrula (header'da kullanıcı adı görünmeli)
        softAssert.assertTrue(signButonsPage.headerUserName.isDisplayed(),
                "Üyelik oluşturulamadı - kullanıcı adı header bölümünde görüntülenemedi.");
        softAssert.assertAll();
    }

    @Test(priority = 2)
    public void TC02_WellnessRandevuAl() {

        departmentsMainPage = new DepartmentsMainPage();

        //  Ana sayfadaki header'da Departments dropdown menüsünü aç ve Wellness'a tıkla
        departmentsMainPage.DepartmentsMainButton.click();
        ReusableMethods.bekle(5);
        departmentsMainPage.WellnesLinkButton.click();
        ReusableMethods.bekle(5);

        appointmentBookingPage = new AppointmentBookingPage();

        // Appointment Booking - Tarih alanını 31.03.2026 olarak doldur
        appointmentBookingPage.dateInput.sendKeys("31.03.2026");
        ReusableMethods.bekle(2);

        // Phone number alanına 05325009123 yaz
        appointmentBookingPage.phoneBox.sendKeys("05325009123");
        ReusableMethods.bekle(2);

        // Department dropdown'ı aç ve Wellness'ı seç
        appointmentBookingPage.departmentDropdownKutusu.click();
        ReusableMethods.bekle(2);
        appointmentBookingPage.departmentDropDownWellness.click();
        ReusableMethods.bekle(2);

        // Doktor alanı varsayılan olarak Dr. Alejandro Martinez şeklinde bırakılır
        ReusableMethods.bekle(1);

        // Create message alanına mesajı yaz
        appointmentBookingPage.messageBox.sendKeys("evcil hayvanım için randevu talebimdir");
        ReusableMethods.bekle(2);

        // Randevu oluştur butonuna tıkla
        appointmentBookingPage.appointmentBookingButton.click();
        ReusableMethods.bekle(7);

        // "Congratulations on your well-deserved success." mesajının göründüğünü doğrula
        softAssert.assertTrue(appointmentBookingPage.randevuAlertMesaji.isDisplayed(),
                "Başarı mesajı görüntülenemedi.");
        softAssert.assertAll();

        // Ana sayfaya dön
        Driver.getDriver().get(ConfigReader.getProperty("LfcUrl"));
        ReusableMethods.bekle(5);
    }

    @Test(priority = 3)
    public void TC03_DentalCareRandevuAl() {

        departmentsMainPage = new DepartmentsMainPage();

        // Ana sayfadaki header'da Departments dropdown menüsünü aç ve Dental Care'e tıkla
        departmentsMainPage.DepartmentsMainButton.click();
        ReusableMethods.bekle(5);
        departmentsMainPage.DentalCareLinkButton.click();
        ReusableMethods.bekle(5);

        appointmentBookingPage = new AppointmentBookingPage();

        // Tarih alanını doldur
        appointmentBookingPage.dateInput.sendKeys("31.03.2026");
        ReusableMethods.bekle(2);

        // Phone number alanını doldur
        appointmentBookingPage.phoneBox.sendKeys("05325009123");
        ReusableMethods.bekle(2);

        // Department dropdown'ı aç ve Dental Care'i seç
        appointmentBookingPage.departmentDropdownKutusu.click();
        ReusableMethods.bekle(2);
        appointmentBookingPage.dentalCareSecenegi.click();
        ReusableMethods.bekle(2);

        // Create message alanına mesajı yaz
        appointmentBookingPage.messageBox.sendKeys("evcil hayvanım için randevu talebimdir");
        ReusableMethods.bekle(2);

        // Randevu oluştur butonuna tıkla
        appointmentBookingPage.appointmentBookingButton.click();
        ReusableMethods.bekle(7);

        // "Congratulations on your well-deserved success." mesajının göründüğünü doğrula
        softAssert.assertTrue(appointmentBookingPage.randevuAlertMesaji.isDisplayed(),
                "Başarı mesajı görüntülenemedi.");
        softAssert.assertAll();

        // Ana sayfaya dön
        Driver.getDriver().get(ConfigReader.getProperty("LfcUrl"));
        ReusableMethods.bekle(5);
    }

    @Test(priority = 4)
    public void TC04_AnaesthesiaRandevuAl() {

        departmentsMainPage = new DepartmentsMainPage();

        // Ana sayfadaki header'da Departments dropdown menüsünü aç ve Anaesthesia'ya tıkla
        departmentsMainPage.DepartmentsMainButton.click();
        ReusableMethods.bekle(5);
        departmentsMainPage.AnaesthesiaLinkButton.click();
        ReusableMethods.bekle(5);

        appointmentBookingPage = new AppointmentBookingPage();

        // Tarih alanını doldur
        appointmentBookingPage.dateInput.sendKeys("31.03.2026");
        ReusableMethods.bekle(2);

        // Phone number alanını doldur
        appointmentBookingPage.phoneBox.sendKeys("05325009123");
        ReusableMethods.bekle(2);

        // Department dropdown'ı aç ve Anaesthesia'yı seç
        appointmentBookingPage.departmentDropdownKutusu.click();
        ReusableMethods.bekle(2);
        appointmentBookingPage.anaesthesiaSecenegi.click();
        ReusableMethods.bekle(2);

        // Create message alanına mesajı yaz
        appointmentBookingPage.messageBox.sendKeys("evcil hayvanım için randevu talebimdir");
        ReusableMethods.bekle(2);

        // Randevu oluştur butonuna tıkla
        appointmentBookingPage.appointmentBookingButton.click();
        ReusableMethods.bekle(7);

        // "Congratulations on your well-deserved success." mesajının göründüğünü doğrula
        softAssert.assertTrue(appointmentBookingPage.randevuAlertMesaji.isDisplayed(),
                "Başarı mesajı görüntülenemedi.");
        softAssert.assertAll();

        // Ana sayfaya dön
        Driver.getDriver().get(ConfigReader.getProperty("LfcUrl"));
        ReusableMethods.bekle(5);
    }

    @Test(priority = 5)
    public void TC05_DermatologyRandevuAl() {

        departmentsMainPage = new DepartmentsMainPage();

        // Ana sayfadaki header'da Departments dropdown menüsünü aç ve Dermatology'ye tıkla
        departmentsMainPage.DepartmentsMainButton.click();
        ReusableMethods.bekle(5);
        departmentsMainPage.DermatologyLinkButton.click();
        ReusableMethods.bekle(5);

        appointmentBookingPage = new AppointmentBookingPage();

        appointmentBookingPage.dateInput.sendKeys("31.03.2026");
        ReusableMethods.bekle(2);
        appointmentBookingPage.phoneBox.sendKeys("05325009123");
        ReusableMethods.bekle(2);

        // Department dropdown'ı aç ve Dermatology'yi seç
        appointmentBookingPage.departmentDropdownKutusu.click();
        ReusableMethods.bekle(2);
        appointmentBookingPage.dermatologySecenegi.click();
        ReusableMethods.bekle(2);

        appointmentBookingPage.messageBox.sendKeys("evcil hayvanım için randevu talebimdir");
        ReusableMethods.bekle(2);

        // Randevu oluştur butonuna tıkla
        appointmentBookingPage.appointmentBookingButton.click();
        ReusableMethods.bekle(7);

        // "Congratulations on your well-deserved success." mesajının göründüğünü doğrula
        softAssert.assertTrue(appointmentBookingPage.randevuAlertMesaji.isDisplayed(),
                "Başarı mesajı görüntülenemedi.");
        softAssert.assertAll();

        // Ana sayfaya dön
        Driver.getDriver().get(ConfigReader.getProperty("LfcUrl"));
        ReusableMethods.bekle(5);
    }

    @Test(priority = 6)
    public void TC06_DiagnosticsRandevuAl() {

        departmentsMainPage = new DepartmentsMainPage();

        // Ana sayfadaki header'da Departments dropdown menüsünü aç ve Diagnostics'e tıkla
        departmentsMainPage.DepartmentsMainButton.click();
        ReusableMethods.bekle(5);
        departmentsMainPage.DiagnosticsLinkButton.click();
        ReusableMethods.bekle(5);

        appointmentBookingPage = new AppointmentBookingPage();

        appointmentBookingPage.dateInput.sendKeys("31.03.2026");
        ReusableMethods.bekle(2);
        appointmentBookingPage.phoneBox.sendKeys("05325009123");
        ReusableMethods.bekle(2);

        // Department dropdown'ı aç ve Diagnostics'i seç
        appointmentBookingPage.departmentDropdownKutusu.click();
        ReusableMethods.bekle(2);
        appointmentBookingPage.diagnosticsSecenegi.click();
        ReusableMethods.bekle(1);

        appointmentBookingPage.messageBox.sendKeys("evcil hayvanım için randevu talebimdir");
        ReusableMethods.bekle(2);

        // Randevu oluştur butonuna tıkla
        appointmentBookingPage.appointmentBookingButton.click();
        ReusableMethods.bekle(7);

        // "Congratulations on your well-deserved success." mesajının göründüğünü doğrula
        softAssert.assertTrue(appointmentBookingPage.randevuAlertMesaji.isDisplayed(),
                "Başarı mesajı görüntülenemedi.");
        softAssert.assertAll();

        // Ana sayfaya dön
        Driver.getDriver().get(ConfigReader.getProperty("LfcUrl"));
        ReusableMethods.bekle(5);
    }

    @Test(priority = 7)
    public void TC07_VaccinationsRandevuAl() {

        departmentsMainPage = new DepartmentsMainPage();

        // Ana sayfadaki header'da Departments dropdown menüsünü aç ve Vaccinations'a tıkla
        departmentsMainPage.DepartmentsMainButton.click();
        ReusableMethods.bekle(5);
        departmentsMainPage.VaccinationsLinkButton.click();
        ReusableMethods.bekle(5);

        appointmentBookingPage = new AppointmentBookingPage();

        appointmentBookingPage.dateInput.sendKeys("31.03.2026");
        ReusableMethods.bekle(2);
        appointmentBookingPage.phoneBox.sendKeys("05325009123");
        ReusableMethods.bekle(2);

        // Department dropdown'ı aç ve Vaccinations'ı seç
        appointmentBookingPage.departmentDropdownKutusu.click();
        ReusableMethods.bekle(2);
        appointmentBookingPage.vaccinationsSecenegi.click();
        ReusableMethods.bekle(1);

        appointmentBookingPage.messageBox.sendKeys("evcil hayvanım için randevu talebimdir");
        ReusableMethods.bekle(2);

        // Randevu oluştur butonuna tıkla
        appointmentBookingPage.appointmentBookingButton.click();
        ReusableMethods.bekle(7);

        // "Congratulations on your well-deserved success." mesajının göründüğünü doğrula
        softAssert.assertTrue(appointmentBookingPage.randevuAlertMesaji.isDisplayed(),
                "Başarı mesajı görüntülenemedi.");
        softAssert.assertAll();

        // Ana sayfaya dön
        Driver.getDriver().get(ConfigReader.getProperty("LfcUrl"));
        ReusableMethods.bekle(5);
    }

    @Test(priority = 8)
    public void TC08_PainControlRandevuAl() {

        departmentsMainPage = new DepartmentsMainPage();

        // Ana sayfadaki header'da Departments dropdown menüsünü aç ve Pain Control'e tıkla
        departmentsMainPage.DepartmentsMainButton.click();
        ReusableMethods.bekle(5);
        departmentsMainPage.PainControlLinkButton.click();
        ReusableMethods.bekle(5);

        appointmentBookingPage = new AppointmentBookingPage();

        appointmentBookingPage.dateInput.sendKeys("31.03.2026");
        ReusableMethods.bekle(2);
        appointmentBookingPage.phoneBox.sendKeys("05325009123");
        ReusableMethods.bekle(2);

        // Department dropdown'ı aç ve Pain Control'ü seç
        appointmentBookingPage.departmentDropdownKutusu.click();
        ReusableMethods.bekle(2);
        appointmentBookingPage.painControlSecenegi.click();
        ReusableMethods.bekle(1);

        appointmentBookingPage.messageBox.sendKeys("evcil hayvanım için randevu talebimdir");
        ReusableMethods.bekle(2);

        // Randevu oluştur butonuna tıkla
        appointmentBookingPage.appointmentBookingButton.click();
        ReusableMethods.bekle(7);

        // "Congratulations on your well-deserved success." mesajının göründüğünü doğrula
        softAssert.assertTrue(appointmentBookingPage.randevuAlertMesaji.isDisplayed(),
                "Başarı mesajı görüntülenemedi.");
        softAssert.assertAll();

        // Ana sayfaya dön
        Driver.getDriver().get(ConfigReader.getProperty("LfcUrl"));
        ReusableMethods.bekle(5);
    }

    @Test(priority = 9)
    public void TC09_BoardingRandevuAl() {

        departmentsMainPage = new DepartmentsMainPage();

        // Ana sayfadaki header'da Departments dropdown menüsünü aç ve Boarding'e tıkla
        departmentsMainPage.DepartmentsMainButton.click();
        ReusableMethods.bekle(5);
        departmentsMainPage.BoardingLinkButton.click();
        ReusableMethods.bekle(5);

        appointmentBookingPage = new AppointmentBookingPage();

        appointmentBookingPage.dateInput.sendKeys("31.03.2026");
        ReusableMethods.bekle(2);
        appointmentBookingPage.phoneBox.sendKeys("05325009123");
        ReusableMethods.bekle(2);

        // Department dropdown'ı aç ve Boarding'i seç
        appointmentBookingPage.departmentDropdownKutusu.click();
        ReusableMethods.bekle(2);
        appointmentBookingPage.boardingSecenegi.click();
        ReusableMethods.bekle(1);

        appointmentBookingPage.messageBox.sendKeys("evcil hayvanım için randevu talebimdir");
        ReusableMethods.bekle(2);

        // Randevu oluştur butonuna tıkla
        appointmentBookingPage.appointmentBookingButton.click();
        ReusableMethods.bekle(7);

        // "Congratulations on your well-deserved success." mesajının göründüğünü doğrula
        softAssert.assertTrue(appointmentBookingPage.randevuAlertMesaji.isDisplayed(),
                "Başarı mesajı görüntülenemedi.");
        softAssert.assertAll();

        // Ana sayfaya dön
        Driver.getDriver().get(ConfigReader.getProperty("LfcUrl"));
        ReusableMethods.bekle(5);
    }

    @AfterClass
    public void tearDown() {
        Driver.quitDriver();
    }
}
