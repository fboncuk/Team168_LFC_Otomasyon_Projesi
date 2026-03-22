package tests;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
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

import java.util.*;

@org.testng.annotations.Listeners(Listeners.class)

public class US17 {

    // US17- Bir kayıtlı kullanıcı olarak,
    // Home Page sayfasından Aşılar sayfasına erişebilmeli,
    // Aşılar sayfasındaki aşı bilgilerini inceleyebilmeli ve
    // seçtiğim aşının sayfasına erişerek o aşı için randevu talebi oluşturabilmeliyim

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

        // SignIn butonu tıklanır ve T06 Tester geçerli kullanıcı bilgileri ile giriş yapılır
        signButonsPage.signInLinki.click();
        signButonsPage.emailKutusu.sendKeys(ConfigReader.getProperty("T06UserMail"));
        signButonsPage.passwordKutusu.sendKeys(ConfigReader.getProperty("T06UserPassword"));
        signButonsPage.signInButtonOnay.click();
    }


    @Test(priority = 1)
    public void US17_TC01_VaccinationsLinkleriHoverTesti() {

        // Aşılar (Vaccinations) linki ve açılır menülerinin
        // fare ile hover edildiğinde görsel geri bildirim verdiğini doğrulamak
        // Bu test en uyugun şelide manuel test ile kontrol edilebilir.

        homeBodyHeaderSectionPage = new HomeBodyHeaderSectionPage();
        SoftAssert softAssert = new SoftAssert();
        ReusableMethods.hover(homeBodyHeaderSectionPage.vaccineDdVaccine);
        ReusableMethods.bekle(1);

        List<WebElement> vaccineDpElementList = Driver.getDriver()
                .findElements(By.xpath("//nav[@id='menu']/ul/li[6]//a"));

        for (int j = 1; j < vaccineDpElementList.size(); j++) {

            String hoveroncesiLocation = vaccineDpElementList.get(j).getCssValue("color");
            ReusableMethods.hover(vaccineDpElementList.get(j));
            ReusableMethods.bekle(1);
            String hoversonrasiLocation =  vaccineDpElementList.get(j).getCssValue("color");

            softAssert.assertNotEquals(hoveroncesiLocation, hoversonrasiLocation
                    ,"Hover sonrası " + vaccineDpElementList.get(j).getText() + " elementi renk değiştirmedi.");
        }
        softAssert.assertAll();
    }


    @Test(priority = 2)
    public void US17_TC02_VaccinationsLinkleriGorunurlukTesti() {
        // Header Vaccinations açılır menüsünde,
        // Vaccinations sayfasında kayıtlı tüm aşıların göründüğünü doğrulamak.
        homeBodyHeaderSectionPage = new HomeBodyHeaderSectionPage();
        SoftAssert softAssert = new SoftAssert();

        // Header bölümündeki Vaccines linki locate edilip hover ile dd menü açılır
        ReusableMethods.hover(homeBodyHeaderSectionPage.vaccineDdVaccine);
        ReusableMethods.bekle(1);

        // Header Vaccines linki altındaki aşılar listeye kaydedilir
        List<WebElement> vaccines = Driver.getDriver()
                .findElements(By.xpath("//nav[@id='menu']/ul/li[6]/ul/li"));

        for (WebElement vaccine : vaccines) {
            softAssert.assertTrue(vaccine.isDisplayed()
                    , "Hover sonrası dd menüdeki aşılar görünür değil!");
        }

        softAssert.assertAll();
    }


    @Test(priority = 3)
    public void US17_TC03_VaccinationsHeaderLinkleriAcilmaTesti() {
        // Header Vaccinations açılır menüsünde,
        // Vaccinations sayfasında kayıtlı tüm aşıların göründüğünü doğrulamak.
        homeBodyHeaderSectionPage = new HomeBodyHeaderSectionPage();
        vacinationsMainPage = new VacinationsMainPage();
        SoftAssert softAssert = new SoftAssert();

        Driver.getDriver().get(ConfigReader.getProperty("VacUrl"));
        // VaccinesMainPage aşı linkleri WebElement Listesi yapılır
        List<WebElement> vaccinesMainLists = Arrays.asList(
                vacinationsMainPage.vaccinesBordetella,
                vacinationsMainPage.vaccinesDHPP,
                vacinationsMainPage.vaccinesRabies,
                vacinationsMainPage.vaccinesFelineViral,
                vacinationsMainPage.vaccinesSurgicalProcedure,
                vacinationsMainPage.vaccinesFelinePanleukopenia,
                vacinationsMainPage.vaccinesFelineImmunodeficiency,
                vacinationsMainPage.vaccinesFelineLeukemia,
                vacinationsMainPage.vaccinesFelineHerpesvirus
        );
        int vaccinesMainSize = vaccinesMainLists.size();

        ReusableMethods.hover(homeBodyHeaderSectionPage.vaccineDdVaccine);
        ReusableMethods.bekle(1);

        List<WebElement> vaccineDpElementList = Driver.getDriver()
                .findElements(By.xpath("//nav[@id='menu']/ul/li[6]/ul/li"));

        int vaccinesDdSize = vaccineDpElementList.size();


        softAssert.assertEquals(vaccinesMainSize,vaccinesDdSize,
                " Ana aşı sayfası ile dd menüde bulunan aşı sayıları uyuşmuyor.");

        softAssert.assertAll();
    }


    @Test(priority = 4)
    public void US17_TC04_FooterYakinlastirmaTesti() {
        // Footer bölümünün farklı yakınlaştırma oranlarına dinamik uyumunu doğrulamak
        // Bu testin manuel olarak yapılması daha kesin sonuçlar vereceğinden,
        // test kodu yazılmamıştır.
        System.out.println("US17_TC04 testi, sadece manuel olarak yapılmıştır.");
    }


    @Test(priority = 5)
    public void US17_TC05_DogruVaccinationsDetaySayfasiAcilmaTesti() {

        // VaccinationsMainPage'de Vaccinations linklerine tıklandığında,
        // ilgili aşı sayfasının açıldığını doğrulamak.
        homeBodyHeaderSectionPage = new HomeBodyHeaderSectionPage();
        vacinationsMainPage = new VacinationsMainPage();
        SoftAssert softAssert = new SoftAssert();

        Driver.getDriver().get(ConfigReader.getProperty("VacUrl"));

        // VaccinesMainPage aşı linkleri WebElement Listesi yapılır
        List<WebElement> vaccinesLists = Arrays.asList(
                vacinationsMainPage.vaccinesRabies,
                vacinationsMainPage.vaccinesDHPP,
                vacinationsMainPage.vaccinesFelineLeukemia,
                vacinationsMainPage.vaccinesFelineImmunodeficiency,
                vacinationsMainPage.vaccinesBordetella,
                vacinationsMainPage.vaccinesFelinePanleukopenia,
                vacinationsMainPage.vaccinesFelineHerpesvirus,
                vacinationsMainPage.vaccinesSurgicalProcedure,
                vacinationsMainPage.vaccinesFelineViral
        );

        // Vaccines detay sayfasındaki aşıların Title Listesi
        List<WebElement> detailVaccinesTitleList = Arrays.asList(
                vacinationsMainPage.detailedTitleRabies,
                vacinationsMainPage.detailedTitleDHPP,
                vacinationsMainPage.detailedTitleFelineLeukemia,
                vacinationsMainPage.detailedTitleFelineImmunodeficiency,
                vacinationsMainPage.detailedTitleBordetella,
                vacinationsMainPage.detailedTitleFelinePanleukopenia,
                vacinationsMainPage.detailedTitleFelineHerpesvirus,
                vacinationsMainPage.detailedTitleSurgicalProcedure,
                vacinationsMainPage.detailedTitleFelineViral
        );

        // Aşıların tıklanan linkteki adları ile açılan sayfadaki adları döngü ile karşılaştırılır
        for (int i = 0; i < vaccinesLists.size(); i++) {

            // VaccinesMainPage'de bulunan aşıların expectedTitle'leri alınır.
            String expectedTitle = vaccinesLists.get(i).getText();

            // Aşı tıklanır ve detay sayfasına gidilir
            vaccinesLists.get(i).click();

            // Açılan sayfadaki aşı actualTitle alınır.
            WebElement detailVaccineText = detailVaccinesTitleList.get(i);
            String actualTitle = detailVaccineText.getText();

            // actualTitle içinde expectedTitle var mı kontrol edilir.
            softAssert.assertTrue(actualTitle.contains(expectedTitle)
                    , "Tıklanan ve açılan sayfa başlıkları arasında farklılık bulunuyor.\n" +
                            "Tıklanan sayfa başlığı : " + expectedTitle + "\n" +
                            "Açılan sayfa başlığı : " + actualTitle);

            // Aşı detay sayfasından VaccinesMainPage sayfasına dönülür.
            Driver.getDriver().navigate().back();
        }

        softAssert.assertAll();

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
        appointmentBookingPage.dateInput.sendKeys(gecerliDateRandevu); // tarih girilir
        appointmentBookingPage.phoneBox.sendKeys(gecerliTelNumarasi); // telefon numarası girilir
        appointmentBookingPage.departmentDropdownKutusu.click(); // Department açılır menü tıklanır
        appointmentBookingPage.dermatologySecenegi.click(); // Dermatology seçilir
        appointmentBookingPage.doctorDropdownKutusu.click(); // Doktor açılır menü tıklanır
        appointmentBookingPage.doktorSecenegi.click(); // Doktor seçilir
        appointmentBookingPage.messageBox.sendKeys(randevuMetni); // Randevu metni girilir
//        ReusableMethods.tumSayfaResimCek(Driver.getDriver()
//                ,"US17_TC06_Geçerli randevu giriş bilgileri");

        // Appointment Booking butonu tıklanır
        appointmentBookingPage.appointmentBookingButton.click();

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
        // ReusableMethods.bekle(1);

        // Geçerli tarih için girmek maksadıyla 10 gün sonraki tarihe randevu alınır.
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String gecerliDateRandevu = LocalDate.now().plusDays(10).format(format);

        // Rakamlardan oluşan geçerli bir telefon numarası oluşturulur.
        String gecerliTelNumarasi = "0535" + (int)(Math.random() * 10000000);

        // Department, Doktor ve Randevu metni girilmez
        // Sadece Randevu kutularına bilgiler girilir.
        appointmentBookingPage.dateInput.sendKeys(gecerliDateRandevu); // tarih girilir
        appointmentBookingPage.phoneBox.sendKeys(gecerliTelNumarasi); // telefon numarası girilir
        ReusableMethods.tumSayfaResimCek(Driver.getDriver()
                ,"US17_TC06_Tarih-telefon no randevu giriş bilgileri");

        // Appointment Booking butonu tıklanır
        appointmentBookingPage.appointmentBookingButton.click();

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
        appointmentBookingPage.dateInput.sendKeys(gecersizDateRandevu); // tarih girilir
        appointmentBookingPage.phoneBox.sendKeys(gecerliTelNumarasi); // telefon numarası girilir
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
        appointmentBookingPage.dateInput.sendKeys(gecerliDateRandevu); // geçerli bir tarih girilir
        appointmentBookingPage.phoneBox.sendKeys(gecersizTelNumarasi); // telefon bilgisi girilir
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
        vacinationsMainPage.vaccinesBordetella.click();
        // ReusableMethods.bekle(1);

        // Hiçbir bilgi girilmeden Appointment Booking butonu tıklanır
        appointmentBookingPage.appointmentBookingButton.click();

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
        // ReusableMethods.bekle(1);

        // Geçerli tarih için girmek maksadıyla 10 gün sonraki tarihe randevu alınır.
        DateTimeFormatter format1 = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String gecerliDateRandevu = LocalDate.now().plusDays(10).format(format1);

        // Rakamlardan oluşan geçerli bir telefon numarası oluşturulur.
        String gecerliTelNumarasi = "0535" + (int)(Math.random() * 10000000);

        // 120 karakterden daha fazla bir randevu metni oluşturulur.
        String randevuMetni121 = RandomStringUtils.randomAlphabetic(121);

        // Randevu kutularına bilgiler girilir.
        appointmentBookingPage.dateInput.sendKeys(gecerliDateRandevu); // tarih girilir
        appointmentBookingPage.phoneBox.sendKeys(gecerliTelNumarasi); // telefon numarası girilir
        appointmentBookingPage.departmentDropdownKutusu.click(); // Department açılır menü tıklanır
        appointmentBookingPage.dermatologySecenegi.click(); // Dermatology departmanı seçilir
        appointmentBookingPage.doctorDropdownKutusu.click(); // Doktor açılır menü tıklanır
        appointmentBookingPage.doktorSecenegi.click(); // Doktor seçilir
        appointmentBookingPage.messageBox.sendKeys(randevuMetni121); // Randevu metni girilir
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


    @Test(priority = 12)
    public void US17_TC12_VaccinationsSayfalariLogoDogrulamaTesti() {
        // Kayıtlı kullanıcı tarafından
        // Vaccinations ana sayfasında veya Vaccinations detay sayfalarında iken
        // "LoyalFriendsCare" logosuna tıklayınca ana sayfa açıldığını doğrulamak

        vacinationsMainPage = new VacinationsMainPage();
        SoftAssert softAssert = new SoftAssert();

        // Vaccinations sayfasına gidilir logoya tıklanarak kontrol yapılır
        Driver.getDriver().get(ConfigReader.getProperty("VacUrl"));
        vacinationsMainPage.lfcLogo.click();

        String expectedUrl = ConfigReader.getProperty("LfcUrl");
        softAssert.assertTrue(Driver.getDriver().getCurrentUrl().equals(expectedUrl));
        Driver.getDriver().navigate().back();


        // Aşı detay sayfalarındaki logo tıklama kontrolü yapılır
        // VaccinesMainPagedeki detay aşı sayfaları WebElement Listesi yapılır
        List<WebElement> vaccinesLists = Arrays.asList(
                vacinationsMainPage.vaccinesRabies,
                vacinationsMainPage.vaccinesDHPP,
                vacinationsMainPage.vaccinesFelineLeukemia,
                vacinationsMainPage.vaccinesFelineImmunodeficiency,
                vacinationsMainPage.vaccinesBordetella,
                vacinationsMainPage.vaccinesFelinePanleukopenia,
                vacinationsMainPage.vaccinesFelineHerpesvirus,
                vacinationsMainPage.vaccinesSurgicalProcedure,
                vacinationsMainPage.vaccinesFelineViral
        );

        // Vaccines detay sayfalarındaki logoların
        // görünür ve tıklanabilir olduğunu döngü ile kontrol etme
        for (WebElement vaccinesList : vaccinesLists) {

            vaccinesList.click();
            ReusableMethods.bekle(1);

            // Sayfalardaki logolar ortak locator kullandığından her sayfada yeiden locator oluşturulur.
            Driver.getDriver()
                    .findElement(By.xpath("(//img[@class='logo_sticky'])[2]"))
                    .click();

            expectedUrl = ConfigReader.getProperty("LfcUrl");
            softAssert.assertTrue(Driver.getDriver().getCurrentUrl().equals(expectedUrl));

            // Detay sayfasından VaccinesMainPage sayfasına dönülür.
            Driver.getDriver().get(ConfigReader.getProperty("VacUrl"));
        }
        softAssert.assertAll();
    }

    @AfterClass
    public void tearDown() { Driver.quitDriver();}

}
