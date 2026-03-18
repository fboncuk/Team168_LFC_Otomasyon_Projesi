package tests;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.LcfAdminPage.AdminBodyPage;
import pages.LcfHomePage.AppointmentBookingPage;
import pages.LcfHomePage.DepartmentsMainPage;
import pages.LcfHomePage.SignButonsPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class US11 {

    SignButonsPage signButonsPage = new SignButonsPage();
    DepartmentsMainPage departmentsMainPage;
    AppointmentBookingPage appointmentBookingPage;

    @BeforeMethod
    public void setup(){

        signButonsPage = new SignButonsPage();
        // Ana sayfaya gidilir
        Driver.getDriver().get(ConfigReader.getProperty("LcfUrl"));

        // SignIn butonu tıklanır ve T06 Tester geçerli kullanıcı bilgileri ile giriş yapılır
        signButonsPage.signInLinki.click();
        signButonsPage.emailKutusu.sendKeys(ConfigReader.getProperty("T11AdminMail"));
        signButonsPage.passwordKutusu.sendKeys(ConfigReader.getProperty("T11AdminPassword"));
        signButonsPage.signInButtonOnay.click();
        departmentsMainPage = new DepartmentsMainPage();
        appointmentBookingPage = new AppointmentBookingPage();
    }

    @AfterMethod
    public void tearDown() {
        Driver.quitDriver();
    }

    @Test
    public void US11_TC01_DepartmanKartlariGorunurlukVeTiklanabilirlikTesti() {

        SoftAssert softAssert = new SoftAssert();

        // Departmanlar bölümüne kaydır
        // İlk departman kartını referans alarak kaydır
        Actions actions = new Actions(Driver.getDriver());
        actions.moveToElement(departmentsMainPage.allDepartmentCards.get(0)).perform();

        // Doğrulama için:
        System.out.println("--- Doğrulanan Departmanlar ---");
        for (WebElement department : departmentsMainPage.allDepartmentCards) {
            String deptName = department.getText().trim();

            if (!deptName.isEmpty()) {
                // Görünürlük kontrolü
                softAssert.assertTrue(department.isDisplayed(), "HATA: " + deptName + " kartı görünmüyor!");

                // Tıklanabilirlik kontrolü
                softAssert.assertTrue(department.isEnabled(), "HATA: " + deptName + " kartı aktif değil!");

                System.out.println("Başarılı: " + deptName);
            }
        }

        softAssert.assertAll();
    }

    @Test
    public void US11_TC02_DepartmanDetaySayfalarinaYonlendirmeDogrulamasiTesti(){

        SoftAssert softAssert = new SoftAssert();
        Actions actions = new Actions(Driver.getDriver());

        // Toplam kart sayısını değişkene atar
        int totalCards = departmentsMainPage.allDepartmentCards.size();

        for (int i = 0; i < totalCards; i++) {
            // Sayfa yenilendiği için elementler her döngüde taze olarak alınır
            WebElement card = departmentsMainPage.allDepartmentCards.get(i);
            String departmentName = card.getText().trim();

            // Karta kaydır ve tıklar
            actions.moveToElement(card).perform();
//            ReusableMethods.bekle(1);
            card.click();

            // URL doğrulaması için mevcut URL alınır İngilizce karakter setine göre küçültülür
            String currentUrl = Driver.getDriver().getCurrentUrl().toLowerCase(Locale.ENGLISH);

            // İsimdeki ilk 3 harfi anahtar kelime olarak belirler
            String searchKey = departmentName.substring(0, 3).toLowerCase(Locale.ENGLISH);

            // URL'in ilgili departman isminin parçasını içerip içermediği kontrol edilir
            softAssert.assertTrue(currentUrl.contains(searchKey),
                    "HATA: " + departmentName + " kartına tıklandı ancak URL uyuşmadı! \nURL: " + currentUrl);

            // Konsol çıktısı ile süreç takibi
            System.out.println("Doğrulandı: " + departmentName);

            // Bir sonraki kartı test etmek için ana sayfaya geri döner
            Driver.getDriver().navigate().back();
        }

        softAssert.assertAll();
    }

    @Test
    public void US11_TC03_GecerliVerilerleBasariliRandevuOlusturmaDogrulamasiTesti(){

        SoftAssert softAssert = new SoftAssert();
        departmentsMainPage.WelnessDepartments.click();

        // 5 gün sonraki tarihe randevu alır
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String validDate = LocalDate.now().plusDays(5).format(format);

        // Telefon numarası oluştur
        String validPhoneNumber = "0544" + (int)(Math.random() * 10000000);

        // Randevu metni
        String appointmentMessage = "Köpeğimin parazit aşısını yaptırmak istiyorum";

        // Randevu kutularına bilgileri girer
        appointmentBookingPage.dateInput.sendKeys(validDate);
        appointmentBookingPage.phoneBox.sendKeys(validPhoneNumber);
        appointmentBookingPage.departmentDropdownKutusu.click();
        appointmentBookingPage.departmentDropDownWellness.click();
        appointmentBookingPage.doctorDropdownKutusu.click();
        appointmentBookingPage.doktorSecenegi.click();
        appointmentBookingPage.messageBox.sendKeys(appointmentMessage);

        // Randevu oluştur
        appointmentBookingPage.appointmentBookingButton.click();

        // Randevu oluşturma için alert mesajı
        String successMessage = "Congratulations on your well-deserved success.";
        String actualMessage = appointmentBookingPage.randevuAlertMesaji.getText();

        // Randevu oluşturulduğu doğrulanır
        softAssert.assertTrue(actualMessage.contains(successMessage),
                "HATA: Randevu başarı mesajı görüntülenemedi! Alınan mesaj: " + actualMessage);

        softAssert.assertAll();

    }

    @Test
    public void US11_TC04_ZorunluAlanlarBosBirakildigindaHataMesajiDogrulamasi(){

        SoftAssert softAssert = new SoftAssert();

        // Randevu formuna gider
        departmentsMainPage.WelnessDepartments.click();
        // Hiçbir kutyu doldurmadan randevu oluştur
        appointmentBookingPage.appointmentBookingButton.click();

        // Başarı mesajı GÖRÜNMEMELİ, hata mesajı GÖRÜNMELİ

        String successMessage = "Congratulations on your well-deserved success.";
        String actualMessage = "";

        try {
            actualMessage = appointmentBookingPage.randevuAlertMesaji.getText();
        } catch (Exception e) {
            // Eğer mesaj hiç çıkmazsa hata almamak için
            System.out.println("Mesaj kutusu bulunamadı bu beklenen bir durum olabilir.");
        }

        // Başarı mesajı ekranda OLMAMALI
        softAssert.assertFalse(actualMessage.contains(successMessage),
                "HATA/BUG: Zorunlu alanlar boş olmasına rağmen randevu başarıyla oluşturuldu!");

        softAssert.assertAll();
    }

    @Test
    public void US11_TC05_TC06_GecersizVerilerleRandevuOlusturulamadiginiDogrulama() {

        // TC06'da içerisinde test edilmiştir.

        SoftAssert softAssert = new SoftAssert();

        // Randevu formuna gider
        departmentsMainPage.WelnessDepartments.click();

        // 5 gün öncesi tarihe girilir
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String pastDate = LocalDate.now().minusDays(5).format(formatter);

        // Harf ve özel karakter içeren bir numara girilir
        String invalidPhone = "0544-ABCD!#";

        // Formu geçersiz verilerle doldur
        appointmentBookingPage.dateInput.sendKeys(pastDate);
        appointmentBookingPage.phoneBox.sendKeys(invalidPhone);

        // Departman ve doktor seçer
        appointmentBookingPage.departmentDropdownKutusu.click();
        appointmentBookingPage.departmentDropDownWellness.click();
        appointmentBookingPage.doctorDropdownKutusu.click();
        appointmentBookingPage.doktorSecenegi.click();

        // Randevu oluşturur
        appointmentBookingPage.appointmentBookingButton.click();

        ReusableMethods.bekle(3); // Bekleme olmayınca test passed oluyor.

        // Sistem başarı mesajı VERMEMELİ
        String successMessage = "Congratulations on your well-deserved success.";
        String actualMessage = "";

        try {
            actualMessage = appointmentBookingPage.randevuAlertMesaji.getText();
        } catch (Exception e) {
            System.out.println("Bilgi: Başarı mesajı görünmedi.");
        }

        // Eğer actualMessage başarı mesajını içeriyorsa test fail olur
        softAssert.assertFalse(actualMessage.contains(successMessage),
                "BUG BULUNDU: Geçmiş tarih (" + pastDate + ") veya geçersiz telefon (" + invalidPhone +
                        ") ile randevu oluşturulabildi!");

        softAssert.assertAll();
    }

    @Test
    public void TC07_SecilenDepartmanIleRandevuFormuUyumlulukDogrulamasi() {
        // Doğrulama sonuçları için SoftAssert
        SoftAssert softAssert = new SoftAssert();

        // Ana sayfadan spesifik bir departman seçilir
        // index 0: Wellness, index 1: Dental Care
        WebElement selectedDepartmentCard = departmentsMainPage.allDepartmentCards.get(1);
        String selectedDepartmentName = selectedDepartmentCard.getText().trim();

        // Karta tıklar ve randevu formuna gider
        selectedDepartmentCard.click();

        // Not: Dropdown henüz tıklanmadan üzerindeki mevcut (default) metni alıyoruz
        String displayedDepartmentInForm = appointmentBookingPage.departmentDropdownKutusu.getText().trim();

        // Karttaki isim ile formdaki isim eşleşiyor mu?
        softAssert.assertEquals(displayedDepartmentInForm, selectedDepartmentName,
                "BUG BULUNDU: Karttan '" + selectedDepartmentName + "' seçildi ama formda '"
                        + displayedDepartmentInForm + "' görünüyor!");

        softAssert.assertAll();
    }
}
