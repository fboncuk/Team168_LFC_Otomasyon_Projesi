package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LcfHomePage.*;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.Arrays;
import java.util.List;

public class US17 {

    // US17- Bir kayıtlı kullanıcı olarak,
    // Home Page sayfasından Aşılar sayfasına erişebilmeli,
    // Aşılar sayfasındaki aşı bilgilerini inceleyebilmeli ve
    // seçtiğim aşının sayfasına erişerek o aşı için randevu talebi oluşturabilmeliyim

    SignButonsPage signButonsPage;
    HomeBodyHeaderSectionPage homeBodyHeaderSectionPage;
    VacinationsMainPage vacinationsMainPage;
    AppointmentBookingPage appointmentBookingPage;

    @BeforeClass
    public void setup() {

        signButonsPage = new SignButonsPage();
        // Homepage açılır
        Driver.getDriver().get(ConfigReader.getProperty("LcfUrl"));

        // SignIn butonu tıklanır ve T06 Tester geçerli kullanıcı bilgileri ile giriş yapılır
        signButonsPage.signInLinki.click();
        signButonsPage.emailKutusu.sendKeys(ConfigReader.getProperty("T06UserMail"));
        signButonsPage.passwordKutusu.sendKeys(ConfigReader.getProperty("T06UserPassword"));
        signButonsPage.signInButtonOnay.click();
    }


    @Test(priority = 1)
    public void US17_TC01_VaccinationsLinkleriHoverTesti() {
        // Header bölümündeki aşılar (Vaccinations) linki ve açılır menülerinin fare ile hover edildiğinde
        // görsel geri bildirim verdiğini doğrulamak.
        homeBodyHeaderSectionPage = new HomeBodyHeaderSectionPage();
    }

    @Test(priority = 2)
    public void US17_TC02_VaccinationsLinkleriGorunurlukTesti() {
        // Header Vaccinations açılır menüsünde,
        // Vaccinations sayfasında kayıtlı tüm aşıların göründüğünü doğrulamak.
        homeBodyHeaderSectionPage = new HomeBodyHeaderSectionPage();
    }

    @Test(priority = 3)
    public void US17_TC03_VaccinationsHeaderLinkleriAcilmaTesti() {
        // Header Vaccinations açılır menüsünde,
        // Vaccinations sayfasında kayıtlı tüm aşıların göründüğünü doğrulamak.
        homeBodyHeaderSectionPage = new HomeBodyHeaderSectionPage();
    }

    @Test(priority = 4)
    public void US17_TC04_FooterYakinlastirmaTesti() {
        // Footer bölümünün farklı yakınlaştırma oranlarına dinamik uyumunu doğrulamak
        // Bu testin manuel olarak yapılması daha kesin sonuçlar vereceğinden,
        // test kodu yazılmamıştır.
    }


    @Test(priority = 5)
    public void US17_TC05_DogruVaccinationsDetaySayfasiAcilmaTesti() {

        // VaccinationsMainPage'de Vaccinations linklerine tıklandığında,
        // ilgili aşı sayfasının açıldığını doğrulamak.
        vacinationsMainPage = new VacinationsMainPage();
        SoftAssert softAssert = new SoftAssert();

        // Vaccinations sayfasına gidilir
        Driver.getDriver().get(ConfigReader.getProperty("VacUrl"));
        ReusableMethods.bekle(1);

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

        // Vaccines detay syfasındaki aşıların Title Listesi
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

        // Aşıların tıklanan linkteki adları ile aşılan sayfadaki adları döngü ile karşılaştırılır
        for (int i = 0; i < vaccinesLists.size(); i++) {

            // VaccinesMainPage'de bulunan aşoların expectedTitle'leri alınır.
            String expectedTitle = vaccinesLists.get(i).getText();

            // Aşı tıklanır ve detay sayfasına gidilir
            vaccinesLists.get(i).click();
            ReusableMethods.bekle(1);

            // Açılan sayfadaki aşı actualTitle alınır.
            String actualTitle = detailVaccinesTitleList.get(i).getText();

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
        ReusableMethods.bekle(1);

        // Geçerli tarih için girmek maksadıyla 10 gün sonraki tarihe randevu alınır.
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String gecerliDateRandevu = LocalDate.now().plusDays(10).format(format);
        System.out.println(gecerliDateRandevu);

        // Rakamlardan oluşan geçerli bir telefon numarası oluşturulur.
        String gecerliTelNumarasi = "0535" + (int)(Math.random() * 10000000);
        System.out.println(gecerliTelNumarasi);

        // Randevu metni
        String randevuMetni = "Randevu oluşturma mesaj kutusu.";
        System.out.println(randevuMetni);

        // Randevu oluşturulduğuna dair alert mesajı texti
        String expectedRandevuAlertText = "Congratulations success.";
        System.out.println(expectedRandevuAlertText);

        // Randevu kutularına bilgiler girilir.
        appointmentBookingPage.dateInput.sendKeys(gecerliDateRandevu); // tarih
        appointmentBookingPage.phoneBox.sendKeys(gecerliTelNumarasi); // telefon numarası
        appointmentBookingPage.departmentDropdownKutusu.click(); // Department açılır menü tıklanır
        appointmentBookingPage.dermatologySecenegi.click(); // Dermatology seçilir
        appointmentBookingPage.doctorDropdownKutusu.click(); // Department açılır menü tıklanır
        appointmentBookingPage.doktorSecenegi.click(); // Doktor seçilir
        appointmentBookingPage.messageBox.sendKeys(randevuMetni); // Randevu metni girilir

        // Appointment Booking
        appointmentBookingPage.appointmentBookingButton.click();

        // Ekranaçıkan alert mesajı kaydedilir
        String actualAletText = appointmentBookingPage.randevuAlertMesaji.getText();
        System.out.println(actualAletText);

        // Randevu oluşturulduğu doğrulanır
        softAssert.assertTrue(actualAletText.contains(expectedRandevuAlertText)
                ,  "Ekranda randevu oluşturulduğu bilgi mesajı alınmadı.");


    }


    @AfterClass
    public void tearDown() {
        Driver.quitDriver();
    }

}
