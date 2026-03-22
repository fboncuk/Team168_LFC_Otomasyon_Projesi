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
import utilities.TestBaseRapor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class US11 extends TestBaseRapor {

    DepartmentsMainPage departmentsMainPage;
    AppointmentBookingPage appointmentBookingPage;
    SignButonsPage signButonsPage;

    @BeforeMethod
    public void setup(){
        signButonsPage = new SignButonsPage();
        departmentsMainPage = new DepartmentsMainPage();
        appointmentBookingPage = new AppointmentBookingPage();

        // Ana sayfaya gidilir
        Driver.getDriver().get(ConfigReader.getProperty("LfcUrl"));

        // Beklemeyi geçici olarak sıfırla
        Driver.getDriver().manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(0));

        try {
            // Eğer SignIn linki görünüyorsa (yani login değilsek) giriş yap
            if (signButonsPage.signInLinki.isDisplayed()) {
                signButonsPage.signInLinki.click();

                // Login bilgilerini gir
                signButonsPage.emailKutusu.sendKeys(ConfigReader.getProperty("T11UserMail"));
                signButonsPage.passwordKutusu.sendKeys(ConfigReader.getProperty("T11AdminPassword"));
                signButonsPage.signInButtonOnay.click();

                // Login sonrası sayfanın oturması için kısa bir bekleme
//                ReusableMethods.bekle(1);
            }
        } catch (Exception e) {
            // Eğer SignIn linki yoksa zaten giriş yapılmıştır, devam et
            System.out.println("Zaten giriş yapılmış, direkt teste geçiliyor.");
        }


        Driver.getDriver().manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(15));
    }

    @AfterMethod
    public void tearDown() {
        //signButonsPage.homePageSignOut.click();
        //Driver.quitDriver();
    }

    @Test(priority = 1)
    public void US11_TC01_DepartmanKartlariGorunurlukVeTiklanabilirlikTesti() {

        extentTest = extentReports.createTest("TC01 - Departman Kartları Görünürlük Testi");
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

    @Test(priority = 2)
    public void US11_TC02_DepartmanDetaySayfalarinaYonlendirmeDogrulamasiTesti(){

        extentTest = extentReports.createTest("TC02 - Departman Detay Sayfalarına Yönlendirme Testi");
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

    @Test(priority = 3)
    public void US11_TC03_GecerliVerilerleBasariliRandevuOlusturmaDogrulamasiTesti(){

        extentTest = extentReports.createTest("TC03 - Randevu Formunda Geçerli Verilerle Randevu Oluşturma Testi");
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

    @Test(priority = 4)
    public void US11_TC04_ZorunluAlanlarBosBirakildigindaHataMesajiDogrulamasi(){

        extentTest = extentReports.createTest("TC04 - Randevu Formunda Zorunlu Alanların Boş Bırakılmasıyla Hata Alma Testi");
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
        ReusableMethods.bekle(2);

        // Başarı mesajı ekranda OLMAMALI
        softAssert.assertFalse(actualMessage.contains(successMessage),
                "HATA/BUG: Zorunlu alanlar boş olmasına rağmen randevu başarıyla oluşturuldu!");

        softAssert.assertAll();
    }

    @Test(priority = 5)
    public void US11_TC05_TC06_GecersizVerilerleRandevuOlusturulamadiginiDogrulama() {

        // TC06'da içerisinde test edilmiştir.

        extentTest = extentReports.createTest("TC05_TC06 - Randevu Formunda Geçersiz Verilerle Randevu Oluşturulamadığını Doğrulama Testi");
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

    @Test(priority = 6)
    public void US11_TC07_SecilenDepartmanIleRandevuFormuUyumlulukDogrulamasi() {

        extentTest = extentReports.createTest("TC07 - Seçilen Departman İsminin Randevu Formunda Gözükmesi Testi");
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

    @Test(priority = 7)
    public void US11_TC08_NavigasyonSonrasiFormVerilerininKorunumuDogrulamasiTesti(){

        extentTest = extentReports.createTest("TC08 - Navigasyon Sonrasi Randevu Formu Verilerinin Korunması Testi  ");
        SoftAssert softAssert = new SoftAssert();

        departmentsMainPage.WelnessDepartments.click();

        // Telefon numarası oluştur
        String expectedPhone = "0544" + (int)(Math.random() * 10000000);

        // Randevu metni
        String expectedMessage = "Köpeğimin parazit aşısını yaptırmak istiyorum";

        // Randevu kutularına bilgileri girer
        appointmentBookingPage.phoneBox.sendKeys(expectedPhone);
        appointmentBookingPage.messageBox.sendKeys(expectedMessage);

        // Geri gider ve sonra tekrar ileri gelir
        Driver.getDriver().navigate().back();
        Driver.getDriver().navigate().forward();

        // DOĞRULAMA: Veriler hala orada mı doğrulaması yapılır
        String actualDate = appointmentBookingPage.dateInput.getAttribute("value");
        String actualPhone = appointmentBookingPage.phoneBox.getAttribute("value");
        String actualMessage = appointmentBookingPage.messageBox.getAttribute("value");

        softAssert.assertEquals(actualPhone, expectedPhone, "HATA: Navigasyon sonrası telefon verisi silinmiş!");
        softAssert.assertEquals(actualMessage, expectedMessage, "HATA: Navigasyon sonrası mesaj verisi silinmiş!");

        softAssert.assertAll();

    }

    @Test(priority = 8)
    public void TC09_KlavyeTabNavigasyonuVeOdaklanmaKontroluDogrulamasiTesti() {

        extentTest = extentReports.createTest("TC09 - Randevu Formunun 'TAB' Tuşuyla Odaklanma Kontrolü Testi");
        SoftAssert softAssert = new SoftAssert();

        // Form sayfasına gidilir
        departmentsMainPage.WelnessDepartments.click();

        // Başlangıç noktasını Telefon kutusu olarak belirliyoruz
        appointmentBookingPage.phoneBox.click();

        // Telefon kutusunun şu an odakta olduğunu doğrular
        WebElement currentFocus = Driver.getDriver().switchTo().activeElement();
        softAssert.assertEquals(currentFocus, appointmentBookingPage.phoneBox,
                "HATA: Başlangıçta odak Telefon kutusunda değil!");

        Actions actions = new Actions(Driver.getDriver());

        // TAB tuşuna basarak Departman Dropdown'una geçer
        actions.sendKeys(org.openqa.selenium.Keys.TAB).perform();

        currentFocus = Driver.getDriver().switchTo().activeElement();
        softAssert.assertEquals(currentFocus, appointmentBookingPage.departmentDropdownKutusu,
                "HATA: Telefon kutusundan sonra TAB ile Departman seçimine geçilemedi!");

        // TAB tuşuna basarak Doktor Dropdown'una geçer
        actions.sendKeys(org.openqa.selenium.Keys.TAB).perform();

        currentFocus = Driver.getDriver().switchTo().activeElement();
        softAssert.assertEquals(currentFocus, appointmentBookingPage.doctorDropdownKutusu,
                "HATA: Departman seçiminden sonra TAB ile Doktor seçimine geçilemedi!");

        // TAB tuşuna basarak Mesaj kutusuna geçer
        actions.sendKeys(org.openqa.selenium.Keys.TAB).perform();

        currentFocus = Driver.getDriver().switchTo().activeElement();
        softAssert.assertEquals(currentFocus, appointmentBookingPage.messageBox,
                "HATA: Doktor seçiminden sonra TAB ile Mesaj kutusuna geçilemedi!");

        softAssert.assertAll();
    }

    /*
    TC10 için;
    Admin panelinde filtreleme veya arama özelliği bulunmadığı için,
    binlerce kayıt arasından yeni oluşturulan randevuyu bulmak testin performansını negatif etkilemektedir.
    Bu senaryo, manuel test aşamasında 'Veritabanı kontrolü' veya 'Filtreleme geliştirme talebi' olarak değerlendirilmelidir.
     */
}
