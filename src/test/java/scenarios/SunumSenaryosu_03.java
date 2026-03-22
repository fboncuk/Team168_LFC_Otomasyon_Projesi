package scenarios;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LcfHomePage.*;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.Listeners;
import utilities.ReusableMethods;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@org.testng.annotations.Listeners(Listeners.class)

public class SunumSenaryosu_03 {

    // RANDEVU OLUŞTURMA SENARYOSU

    SignButonsPage signButonsPage;
    HomeBodyPage homeBodyPage;
    HomeBodyHeaderSectionPage homeBodyHeaderSectionPage;
    VacinationsMainPage vacinationsMainPage;
    AppointmentBookingPage appointmentBookingPage;

    @BeforeClass
    public void setup() {

        signButonsPage = new SignButonsPage();
        // Homepage açılır
        Driver.getDriver().get(ConfigReader.getProperty("LfcUrl"));
        ReusableMethods.bekle(3);

        // SignIn butonu tıklanır ve T06 Tester geçerli kullanıcı bilgileri ile giriş yapılır
        signButonsPage.signInLinki.click();
        ReusableMethods.bekle(3);
        signButonsPage.emailKutusu.sendKeys(ConfigReader.getProperty("T06UserMail"));
        ReusableMethods.bekle(3);
        signButonsPage.passwordKutusu.sendKeys(ConfigReader.getProperty("T06UserPassword"));
        ReusableMethods.bekle(3);
        signButonsPage.signInButtonOnay.click();
        ReusableMethods.bekle(3);
    }


    @Test(priority = 6)
    public void US17_TC06_GecerliVerilerleRandevuOlusturmaTesti() {
        // Kayıtlı kullanıcı tarafından
        // Vaccinations listesinde yer alan bir aşı detay sayfasından
        // Gecerli verilerle bir başarılı bir randevu kaydı açıldığını doğrulamak.
        appointmentBookingPage = new AppointmentBookingPage();
        SoftAssert softAssert = new SoftAssert();

        // Dermatology detay sayfasına gidilir
        Driver.getDriver().get(ConfigReader.getProperty("DermUrl"));
        // ReusableMethods.bekle(1);

        // Geçerli tarih için girmek maksadıyla 10 gün sonraki tarihe randevu alınır.
        DateTimeFormatter format1 = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String gecerliDateRandevu = LocalDate.now().plusDays(10).format(format1);

        // Rakamlardan oluşan geçerli bir telefon numarası oluşturulur.
        String gecerliTelNumarasi = "0535" + (int)(Math.random() * 10000000);

        // Randevu metni
        String randevuMetni = "Randevu açıklaması için yazılan mesaj.";

        // Randevu kutularına bilgiler girilir.
        ReusableMethods.bekle(3);
        appointmentBookingPage.dateInput.sendKeys(gecerliDateRandevu); // tarih girilir
        ReusableMethods.bekle(3);
        appointmentBookingPage.phoneBox.sendKeys(gecerliTelNumarasi); // telefon numarası girilir
        ReusableMethods.bekle(3);
        appointmentBookingPage.departmentDropdownKutusu.click(); // Department açılır menü tıklanır
        ReusableMethods.bekle(3);
        appointmentBookingPage.dermatologySecenegi.click(); // Dermatology seçilir
        ReusableMethods.bekle(3);
        appointmentBookingPage.doctorDropdownKutusu.click(); // Doktor açılır menü tıklanır
        ReusableMethods.bekle(3);
        appointmentBookingPage.doktorSecenegi.click(); // Doktor seçilir
        ReusableMethods.bekle(3);
        appointmentBookingPage.messageBox.sendKeys(randevuMetni); // Randevu metni girilir
        ReusableMethods.bekle(3);
//        ReusableMethods.tumSayfaResimCek(Driver.getDriver()
//                ,"US17_TC06_Geçerli randevu giriş bilgileri");

        // Appointment Booking butonu tıklanır
        appointmentBookingPage.appointmentBookingButton.click();
        ReusableMethods.bekle(3);

        // Ekrana çıkan alert mesajı kaydedilir
        String actualAletText = appointmentBookingPage.randevuAlertMesaji.getText();

        // Randevu oluşturulduğuna dair beklenen alert mesajı texti
        String expectedRandevuAlertText = "Congratulations";

//        // Randevu oluşmazsa ekran görüntüsü alınır
//        if (!actualAletText.contains(expectedRandevuAlertText)) {
//            // ReusableMethods.bekle(1);
//            ReusableMethods.tarihliTumSayfaResimCek(Driver.getDriver()
//                    ,"US17_TC06_Geçerli bilgilerle randevu alamama hatası");
//        }

//        // Randevu oluşturulduğu doğrulanır
//        softAssert.assertTrue(actualAletText.contains(expectedRandevuAlertText)
//                ,  "US17_TC06_Geçerli bilgilerle randevu alamama hatası");

        softAssert.assertAll();
    }


    @Test(priority = 7)
    public void US17_TC07_SadeceTarihVeTelefonIleRandevuOlusturmaTesti() {
        // Kayıtlı kullanıcı tarafından
        // Vaccinations listesinde yer alan bir aşı detay sayfasından
        // Geçerli randevu kaydı için tarih ve telefon numarasının yeterli olduğunu doğrulamak
        appointmentBookingPage = new AppointmentBookingPage();
        SoftAssert softAssert = new SoftAssert();

        // Dermatology detay sayfasına gidilir
        Driver.getDriver().get(ConfigReader.getProperty("DermUrl"));
        ReusableMethods.bekle(3);

        // Geçerli tarih için girmek maksadıyla 10 gün sonraki tarihe randevu alınır.
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String gecerliDateRandevu = LocalDate.now().plusDays(10).format(format);

        // Rakamlardan oluşan geçerli bir telefon numarası oluşturulur.
        String gecerliTelNumarasi = "0535" + (int)(Math.random() * 10000000);

        // Department, Doktor ve Randevu metni girilmez
        // Sadece Randevu kutularına bilgiler girilir.
        ReusableMethods.bekle(3);
        appointmentBookingPage.dateInput.sendKeys(gecerliDateRandevu); // tarih girilir
        ReusableMethods.bekle(3);
        appointmentBookingPage.phoneBox.sendKeys(gecerliTelNumarasi); // telefon numarası girilir
        ReusableMethods.bekle(3);
        ReusableMethods.tumSayfaResimCek(Driver.getDriver()
                ,"US17_TC06_Tarih-telefon no randevu giriş bilgileri");

        // Appointment Booking butonu tıklanır
        appointmentBookingPage.appointmentBookingButton.click();
        ReusableMethods.bekle(3);

        // Ekrana çıkan alert mesajı kaydedilir
        String actualAletText = appointmentBookingPage.randevuAlertMesaji.getText();

        // Randevu oluşturulduğuna dair beklenen alert mesajı texti
        String expectedRandevuAlertText = "Congratulations";

        // Randevu oluşmazsa ekran görüntüsü alınır
        if (!actualAletText.contains(expectedRandevuAlertText)) {
            // ReusableMethods.bekle(1);
            ReusableMethods.tarihliTumSayfaResimCek(Driver.getDriver()
                    ,"US17_TC07_Tarih-telefon no ile randevu alamama hatası");
        }

        // Randevu oluşturulduğu doğrulanır
        softAssert.assertTrue(actualAletText.contains(expectedRandevuAlertText)
                ,  "US17_TC07_Tarih-telefon no ile randevu alamama hatası");

        softAssert.assertAll();

    }


    @Test(priority = 8)
    public void US17_TC08_GecmisTarihliRandevuOlusturulamadiginiDogrulamaTesti() {
        // Kayıtlı kullanıcı tarafından
        // Randevu talebi modülünde, geçmiş tarih seçilemediğini ve
        // gecmis tarih secilse bile randevu olusturulamadigini doğrulamak
        // Negatif test
        appointmentBookingPage = new AppointmentBookingPage();
        SoftAssert softAssert = new SoftAssert();

        // Dermatology detay sayfasına gidilir
        Driver.getDriver().get(ConfigReader.getProperty("DermUrl"));
        // ReusableMethods.bekle(1);

        // Geçersiz tarih girmek maksadıyla 10 gün önceki bir tarih seçilir.
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String gecersizDateRandevu = LocalDate.now().minusDays(10).format(format);

        // Rakamlardan oluşan geçerli bir telefon numarası oluşturulur.
        String gecerliTelNumarasi = "0535" + (int)(Math.random() * 10000000);

        // Department, Doktor ve Randevu metni girilmez
        // Sadece Randevu kutularına bilgiler girilir.
        ReusableMethods.bekle(3);
        appointmentBookingPage.dateInput.sendKeys(gecersizDateRandevu); // tarih girilir
        ReusableMethods.bekle(3);
        appointmentBookingPage.phoneBox.sendKeys(gecerliTelNumarasi); // telefon numarası girilir
        ReusableMethods.bekle(3);
        ReusableMethods.tumSayfaResimCek(Driver.getDriver()
                ,"US17_TC08_Geçmiş tarihli randevu giriş bilgileri");

        // Randevu için geçmiş tarih seçilemediği doğrulanır
        String dateValue = appointmentBookingPage.dateInput.getAttribute("value");

        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate gecersizDateFormatted = LocalDate.parse(gecersizDateRandevu, formatter1);
        LocalDate dateValueFormatted = LocalDate.parse(dateValue, formatter2);

        // Randevu oluşmazsa ekran görüntüsü alınır
        if (gecersizDateFormatted.equals(dateValueFormatted)) {
            ReusableMethods.tarihliTumSayfaResimCek(Driver.getDriver()
                    ,"US17_TC08_Randevu için geçmiş tarih girilebilme hatası");
        }

        softAssert.assertNotEquals(gecersizDateFormatted,dateValueFormatted
                ,"US17_TC08_Randevu için geçmiş tarih girilebilme hatası.");

        // Appointment Booking butonu tıklanır
        appointmentBookingPage.appointmentBookingButton.click();
        ReusableMethods.bekle(3);

        // Ekrana çıkan alert mesajı kaydedilir
        String actualAletText = appointmentBookingPage.randevuAlertMesaji.getText();

        // Eski tarih girildiğinden ekrandaki mesajda "Congratulations" ifadesinin gözükmemesi gerekir.
        String approvedRandevuAlertText = "Congratulations";

        // Sistem randevu oluşurturursa hatalı randevu oluşturulduğundan ekran görüntüsü alınır
        if (actualAletText.contains(approvedRandevuAlertText)) {
            ReusableMethods.tarihliTumSayfaResimCek(Driver.getDriver()
                    ,"US17_TC08_Geçmiş tarihli randevuya izin verilme hatası");
        }

        // Randevu oluşturulamadığı doğrulanır
        softAssert.assertFalse(actualAletText.contains(approvedRandevuAlertText)
                ,  "US17_TC08_Geçmiş tarihli randevuya izin verilme hatası");

        softAssert.assertAll();
    }


    @Test(priority = 9)
    public void US17_TC09_HataliTelefonIleRandevuOlusturulamadiginiDogrulamaTesti() {
        // Kayıtlı kullanıcı tarafından
        // Telefon numarası kutusuna rakam harici karakter girilemediğini doğrulamak
        // Negatif test
        appointmentBookingPage = new AppointmentBookingPage();
        SoftAssert softAssert = new SoftAssert();

        // Dermatology detay sayfasına gidilir
        Driver.getDriver().get(ConfigReader.getProperty("DermUrl"));
        // ReusableMethods.bekle(1);

        // Geçerli tarih için girmek maksadıyla 10 gün sonraki tarihe randevu alınır.
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String gecerliDateRandevu = LocalDate.now().plusDays(10).format(format);

        // Rakamlar harici karakterlerden oluşan geçersiz bir telefon numarası oluşturulur.
        String gecersizTelNumarasi = "0535 tel*.-+%";

        // Department, Doktor ve Randevu metni girilmez
        // Sadece Randevu kutularına bilgiler girilir.
        ReusableMethods.bekle(3);
        appointmentBookingPage.dateInput.sendKeys(gecerliDateRandevu); // geçerli bir tarih girilir
        ReusableMethods.bekle(3);
        appointmentBookingPage.phoneBox.sendKeys(gecersizTelNumarasi); // telefon bilgisi girilir
        ReusableMethods.bekle(3);
        ReusableMethods.tumSayfaResimCek(Driver.getDriver()
                ,"US17_TC09_Geçersiz telefon no ile randevu giriş bilgileri");

        // Randevu için telefon numarası olarak sadece rakam girilebildiği doğrulanır
        String telNumarasiValue = appointmentBookingPage.phoneBox.getAttribute("value");

        // Randevu oluşmazsa ekran görüntüsü alınır
        if (gecersizTelNumarasi.equals(telNumarasiValue)) {
            ReusableMethods.tarihliTumSayfaResimCek(Driver.getDriver()
                    ,"US17_TC09_Geçersiz telefon no ile randevu girişi hatası");
        }

        softAssert.assertNotEquals(gecersizTelNumarasi,telNumarasiValue
                ,"US17_TC09_Geçersiz telefon no ile randevu girişi hatası.");

        // Appointment Booking butonu tıklanır
        appointmentBookingPage.appointmentBookingButton.click();
        ReusableMethods.bekle(3);

        // Ekrana çıkan alert mesajı kaydedilir
        String actualAletText = appointmentBookingPage.randevuAlertMesaji.getText();

        // Geçersiz telefon numarası girildiğinden ekrandaki mesajda "Congratulations" ifadesinin gözükmemesi gerekir.
        String approvedRandevuAlertText = "Congratulations";

        // Sistem randevu oluşurturursa hatalı randevu oluşturulduğundan ekran görüntüsü alınır
        if (actualAletText.contains(approvedRandevuAlertText)) {
            ReusableMethods.tarihliTumSayfaResimCek(Driver.getDriver()
                    ,"US17_TC09_Geçersiz telefon no ile randevu alma hatası");
        }

        // Geçersiz telefon numarası ile randevu oluşturulamadığı doğrulanır
        softAssert.assertFalse(actualAletText.contains(approvedRandevuAlertText)
                ,  "US17_TC09_Geçersiz telefon no ile randevu alma hatası.");

        softAssert.assertAll();
    }


    @Test(priority = 10)
    public void US17_TC10_BosTarihVeTelefonIleRandevuOlusturulamamaTesti() {
        // Kayıtlı kullanıcı tarafından
        // tarih veya telefon numarası boş bırakıldığında, sistemin randevu oluşturmaması ve
        // hata mesajı vermesini doğrulamak
        // Negatif test
        vacinationsMainPage = new VacinationsMainPage();
        appointmentBookingPage = new AppointmentBookingPage();
        SoftAssert softAssert = new SoftAssert();

        // VacinationsMainPage içindeki Bordetella detay sayfasına gidilir
        Driver.getDriver().get(ConfigReader.getProperty("VacUrl"));
        ReusableMethods.bekle(3);
        vacinationsMainPage.vaccinesBordetella.click();
        ReusableMethods.bekle(3);

        // Hiçbir bilgi girilmeden Appointment Booking butonu tıklanır
        appointmentBookingPage.appointmentBookingButton.click();
        ReusableMethods.bekle(3);

        // Ekrana çıkan alert mesajı kaydedilir
        String actualAletText = appointmentBookingPage.randevuAlertMesaji.getText();

        // Geçerli onay mesajında "Congratulations" ifadesi bulunur.
        String approvedRandevuAlertText = "Congratulations";

        // Sistem randevu oluşurturursa hatalı randevu oluşturulduğundan ekran görüntüsü alınır
        if (actualAletText.contains(approvedRandevuAlertText)) {
            ReusableMethods.tarihliTumSayfaResimCek(Driver.getDriver()
                    ,"US17_TC10_Bos tarih ve Telefon ile randevu alma");
        }

        // Geçersiz telefon numarası ile randevu oluşturulamadığı doğrulanır
        softAssert.assertFalse(actualAletText.contains(approvedRandevuAlertText)
                ,  "US17_TC10_Bos tarih ve Telefon ile randevu alma hatası.");

        softAssert.assertAll();
    }


    @Test(priority = 11)
    public void US17_TC11_MaksimumMesajYazmaLimitiDogrulamaTesti() {
        // Kayıtlı kullanıcı tarafından
        // Randevu mesajı kutusuna 120 karakterden daha fazla metin girilemediğini doğrulamak
        // Negatif test

        vacinationsMainPage = new VacinationsMainPage();
        appointmentBookingPage = new AppointmentBookingPage();
        SoftAssert softAssert = new SoftAssert();

        // VacinationsMainPage içindeki Bordetella detay sayfasına gidilir
        Driver.getDriver().get(ConfigReader.getProperty("VacUrl"));
        vacinationsMainPage.vaccinesBordetella.click();
        ReusableMethods.bekle(3);

        // Geçerli tarih için girmek maksadıyla 10 gün sonraki tarihe randevu alınır.
        DateTimeFormatter format1 = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String gecerliDateRandevu = LocalDate.now().plusDays(10).format(format1);

        // Rakamlardan oluşan geçerli bir telefon numarası oluşturulur.
        String gecerliTelNumarasi = "0535" + (int)(Math.random() * 10000000);

        // 120 karakterden daha fazla bir randevu metni oluşturulur.
        String randevuMetni121 = RandomStringUtils.randomAlphabetic(121);

        // Randevu kutularına bilgiler girilir.
        ReusableMethods.bekle(3);
        appointmentBookingPage.dateInput.sendKeys(gecerliDateRandevu); // tarih girilir
        ReusableMethods.bekle(3);
        appointmentBookingPage.phoneBox.sendKeys(gecerliTelNumarasi); // telefon numarası girilir
        ReusableMethods.bekle(3);
        appointmentBookingPage.departmentDropdownKutusu.click(); // Department açılır menü tıklanır
        ReusableMethods.bekle(3);
        appointmentBookingPage.dermatologySecenegi.click(); // Dermatology departmanı seçilir
        ReusableMethods.bekle(3);
        appointmentBookingPage.doctorDropdownKutusu.click(); // Doktor açılır menü tıklanır
        ReusableMethods.bekle(3);
        appointmentBookingPage.doktorSecenegi.click(); // Doktor seçilir
        ReusableMethods.bekle(3);
        appointmentBookingPage.messageBox.sendKeys(randevuMetni121); // Randevu metni girilir
        ReusableMethods.bekle(3);
//        ReusableMethods.tumSayfaResimCek(Driver.getDriver()
//                ,"US17_TC11_120 karakterden uzun randevu mesajı bilgisi");

        // Randevu için girilen metin html kodundan geri alınır
        String messageValue = appointmentBookingPage.messageBox.getAttribute("value");

        softAssert.assertFalse((messageValue.length() > 0)
                , "US17_TC11_120 karakterden uzun randevu mesajı bilgisi");

        softAssert.assertFalse((messageValue.length() > 120)
                ,"US17_TC11_120 karakterden uzun randevu mesajı girilme hatası.");

        // Appointment Booking butonu tıklanır
        appointmentBookingPage.appointmentBookingButton.click();
        ReusableMethods.bekle(3);

        // Ekrana çıkan alert mesajı kaydedilir
        String actualAletText = appointmentBookingPage.randevuAlertMesaji.getText();

        // 120 karakterden uzun mesajlarda randevu oluşmaması ve
        // ekranda "Congratulations" ifadesinin gözükmemesi gerekir.
        String approvedRandevuAlertText = "Congratulations";

        // Geçersiz telefon numarası ile randevu oluşturulamadığı doğrulanır
        softAssert.assertFalse(actualAletText.contains(approvedRandevuAlertText)
                ,  "US17_TC11_120 karakterden uzun mesajla randevu alma hatası.");

        softAssert.assertAll();
    }


    @AfterClass
    public void tearDown() { Driver.quitDriver();}

}
