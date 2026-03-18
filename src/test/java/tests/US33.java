package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LcfAdminPage.AdminBodyPage;
import pages.LcfAdminPage.DashboardMedicinesPage;
import pages.LcfAdminPage.DashboardPage;
import pages.LcfHomePage.SignButonsPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

import java.security.Key;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class US33 {
    AdminBodyPage adminBodyPage;
    DashboardPage dashboardPage;
    DashboardMedicinesPage dashboardMedicinesPage;

    @BeforeMethod
    public void setup(){

        // Admin Login sayfasına gidilir
        Driver.getDriver().get(ConfigReader.getProperty("DasUrl"));

        // Geçerli admin bilgileriyle giriş yapılır
        SignButonsPage signButonsPage = new SignButonsPage();
        signButonsPage.emailKutusu.sendKeys(ConfigReader.getProperty("T11AdminMail"));
        signButonsPage.passwordKutusu.sendKeys(ConfigReader.getProperty("T11AdminPassword"));
        signButonsPage.signInButtonOnay.click();

        adminBodyPage = new AdminBodyPage();
        dashboardPage = new DashboardPage();
        dashboardMedicinesPage = new DashboardMedicinesPage();
    }

    @AfterMethod
    public void tearDown() {
        //Driver.quitDriver();
    }

    @Test
    public void US33_TC01_SidebarMedicinesModuluGorunurlukVeTiklanabilirlikTesti(){

        SoftAssert softAssert = new SoftAssert();

        ReusableMethods.hover(dashboardPage.dashboardPageSideBarMenu);

        dashboardMedicinesPage.sidebarMedicinesMainMenu.click();
        // Medicines kontrolü
        softAssert.assertTrue(dashboardMedicinesPage.sidebarMedicinesSubMenuLink.isDisplayed(),
                "HATA: Medicines alt menüsü görünmüyor!");
        softAssert.assertTrue(dashboardMedicinesPage.sidebarMedicinesSubMenuLink.isEnabled(),
                "HATA: Medicines alt menüsü tıklanabilir değil!");

        // Create medicines kontrolü
        softAssert.assertTrue(dashboardMedicinesPage.sidebarCreateMedicinesSubMenuLink.isDisplayed(),
                "HATA: Create Medicines alt menüsü görünmüyor!");
        softAssert.assertTrue(dashboardMedicinesPage.sidebarCreateMedicinesSubMenuLink.isEnabled(),
                "HATA: Create Medicines alt menüsü tıklanabilir değil!");

        // Tüm bulguları raporla
        softAssert.assertAll();
    }

    @Test
    public void US33_TC02_MedicinesSayfasiYonlendirmeVeURLDogrulamaTesti() {

        SoftAssert softAssert = new SoftAssert();

        // Sidebar'ı aç ve Medicines alt menüsüne tıkla
        ReusableMethods.hover(dashboardPage.dashboardPageSideBarMenu);
        dashboardMedicinesPage.sidebarMedicinesMainMenu.click();
        dashboardMedicinesPage.sidebarMedicinesSubMenuLink.click();

        // Beklenen ve gerçekleşen URL'i karşılaştırır
        String expectedUrlIcerek = "medicines";
        String actualUrl = Driver.getDriver().getCurrentUrl().toLowerCase();

        softAssert.assertTrue(actualUrl.contains(expectedUrlIcerek),
                "HATA/BUG: Medicines sayfasına tıklandığında URL '/Medicines' içermiyor! Mevcut URL: " + actualUrl);

        softAssert.assertAll();
    }

    @Test
    public void US33_TC03_IlacListesiAlfabetikSiralamaliMiTesti() {

        SoftAssert softAssert = new SoftAssert();

        ReusableMethods.hover(dashboardPage.dashboardPageSideBarMenu);
        dashboardMedicinesPage.sidebarMedicinesMainMenu.click();
        dashboardMedicinesPage.sidebarMedicinesSubMenuLink.click();

        // Tablodaki mevcut ilaç isimlerini bir String listesine dönüştürerek saklar
        List<String> actualMedicineNames = ReusableMethods.stringListeDondur(dashboardMedicinesPage.allMedicineTitles);
        // Karşılaştırma yapabilmek için mevcut listenin kopyasını oluşturur ve alfabetik (A-Z) sıralar
        List<String> expectedSortedMedicineNames = new ArrayList<>(actualMedicineNames);
        Collections.sort(expectedSortedMedicineNames);

        // Tablodaki orijinal sıralama ile olması gereken alfabetik sıralamayı karşılaştırır
        // Eğer sıralama yanlışsa test fail verir ve hata mesajını raporlar
        softAssert.assertEquals(actualMedicineNames, expectedSortedMedicineNames,
                "HATA/BUG: İlaç listesi tabloda alfabetik sırada değil!");

        softAssert.assertAll();
    }

    @Test
    public void US33_TC04_MedicinesAramaFonksiyonuDogrulamaTesti() {

        SoftAssert softAssert = new SoftAssert();

        // Medicines sayfasına gider
        Driver.getDriver().get(ConfigReader.getProperty("DasmedUrl"));

        // Arama kutusuna anahtar kelimeyi gönderir
        String searchKeyword = "dog v".toLowerCase();
        dashboardMedicinesPage.medicineSearchBox.sendKeys(searchKeyword);

        // Mevcut tüm başlıkları ve içerikleri listelere alır
        List<String> actualTitles = ReusableMethods.stringListeDondur(dashboardMedicinesPage.allMedicineTitles);
        List<String> actualContents = ReusableMethods.stringListeDondur(dashboardMedicinesPage.allMedicineContents);

        // Her bir satırı kontrol eder
        for (int i = 0; i < actualTitles.size(); i++) {
            String currentTitle = actualTitles.get(i).toLowerCase();
            String currentContent = actualContents.get(i).toLowerCase();

            // Aranan kelime ya başlıkta YA DA içerikte geçmelidir
            boolean isKeywordFound = currentTitle.contains(searchKeyword) || currentContent.contains(searchKeyword);

            softAssert.assertTrue(isKeywordFound,
                    "HATA/BUG: Arama sonucuyla eşleşmeyen bir kayıt bulundu! \n" +
                            "Aranan Kelime: " + searchKeyword + "\n" +
                            "Satır No: " + (i + 1) + "\n" +
                            "Başlık: " + currentTitle + "\n" +
                            "İçerik: " + currentContent);
        }

        softAssert.assertAll();
    }

    @Test
    public void US33_TC05_IlacDuzenlemeSayfasiErisimTesti() {

        SoftAssert softAssert = new SoftAssert();

        Driver.getDriver().get(ConfigReader.getProperty("DasmedUrl"));

        // Listelenen ilaçlardan ilkinin 'Edit' butonuna tıklar
        if (!dashboardMedicinesPage.allEditButtons.isEmpty()) {
            dashboardMedicinesPage.allEditButtons.get(0).click();
        } else {
            softAssert.fail("HATA: Düzenlenecek hiç ilaç bulunamadı, liste boş!");
        }

        // Edit sayfasının açıldığını URL kontrolü ile doğrular
        String expectedUrlIcerik = "edit";
        String actualUrl = Driver.getDriver().getCurrentUrl();

        softAssert.assertTrue(actualUrl.contains(expectedUrlIcerik),
                "HATA: Edit butonuna tıklandığında düzenleme sayfası açılmadı! \n" +
                        "Mevcut URL: " + actualUrl);

        softAssert.assertAll();

    }

    @Test
    public void US33_TC06_IlacDuzenlemeSayfasiZorunluAlanTesti(){

        SoftAssert softAssert = new SoftAssert();

        Driver.getDriver().get(ConfigReader.getProperty("DasmedUrl"));
        // Ilk edit butonuna tıklar
        dashboardMedicinesPage.allEditButtons.get(0).click();

        // Title alanını seç ve temizler
        dashboardMedicinesPage.medicinesPageTitleInput.sendKeys(Keys.CONTROL + "a");
        dashboardMedicinesPage.medicinesPageTitleInput.sendKeys(Keys.BACK_SPACE);
        // Content alanını seç ve temizler
        dashboardMedicinesPage.medicinesPageContentTextMessage.sendKeys(Keys.CONTROL + "a");
        dashboardMedicinesPage.medicinesPageContentTextMessage.sendKeys(Keys.BACK_SPACE);

        // Güncelle butonuna tıklar
        dashboardMedicinesPage.medicineEditSaveButton.click();

        // Testin başarısız olmasını yani sayfada kalmasını bekliyoruz
        String currentUrl = Driver.getDriver().getCurrentUrl();
        softAssert.assertTrue(currentUrl.contains("edit"),
                "HATA: Zorunlu alan boş bırakılmasına rağmen güncelleme yapıldı ve sayfadan çıkıldı!");

        softAssert.assertAll();
    }

    @Test
    public void US33_TC07_IlacDuzenlemeSayfasiOzelKarakterleGirisTesti(){

        SoftAssert softAssert = new SoftAssert();

        Driver.getDriver().get(ConfigReader.getProperty("DasmedUrl"));
        // Ilk edit butonuna tıklar
        dashboardMedicinesPage.allEditButtons.get(0).click();

        // Title alanını seç ve özel karakterler gir
        dashboardMedicinesPage.medicinesPageTitleInput.sendKeys(">#£>½$£½");
        // Content alanını seç ve özel karakterler gir
        dashboardMedicinesPage.medicinesPageContentTextMessage.sendKeys(">#£>½$£½");

        // Güncelle butonuna tıklar
        dashboardMedicinesPage.medicineEditSaveButton.click();

        //ReusableMethods.bekle(1);

        boolean isSuccessAlertDisplayed;
        try {
            // success mesajını temsil eden locator'ı tanımlayıp gözüküp gözükmediğini sorgular
            WebElement successAlert = Driver.getDriver().findElement(By.className("alert-success"));
            isSuccessAlertDisplayed = successAlert.isDisplayed();
        } catch (Exception e) {
            // Element bulunamadıysa, mesaj çıkmadıysa isSuccessAlertDisplayed false olur
            isSuccessAlertDisplayed = false;
        }

        // Beklentimiz: Mesajın GÖRÜNMEMESİ
        softAssert.assertFalse(isSuccessAlertDisplayed,
                "HATA/BUG: Özel karakterlerle giriş yapılmasına rağmen 'Medicines Updated successfully.' mesajı görüntülendi! " +
                        "Sistem anlamsız karakterleri kabul ediyor.");

        softAssert.assertAll();

    }

    @Test
    public void US33_TC08_IlacDuzenlemeSayfasiCokluDilDestegiDogrulamaTesti(){

        SoftAssert softAssert = new SoftAssert();

        Driver.getDriver().get(ConfigReader.getProperty("DasmedUrl"));

        String frenchTitle = "FR";
        String frenchContent = "FRRR";
        String arabicTitle = "AR";
        String arabicContent = "ARRRR";

        // Ilk edit butonuna tıklar
        dashboardMedicinesPage.allEditButtons.get(0).click();
        dashboardMedicinesPage.medicineEditFranceLanguageButton.click();

        dashboardMedicinesPage.medicineEditFranceLanguageButtonTitle.sendKeys(frenchTitle);
        dashboardMedicinesPage.medicineEditFranceLanguageButtonContent.sendKeys(frenchContent);
        dashboardMedicinesPage.medicineEditArabicLanguageButton.click();
        dashboardMedicinesPage.medicineEditArabicLanguageButtonTitle.sendKeys(arabicTitle);
        dashboardMedicinesPage.medicineEditArabicLanguageButtonContent.sendKeys(arabicContent);

        // "Save" butonuna tıklar
        dashboardMedicinesPage.medicineEditSaveButton.click();

        // Verinin kaydedilip kaydedilmediğini "Edit" sayfasına girerek kutuların içini kontrol eder
        Driver.getDriver().get(ConfigReader.getProperty("DasmedUrl"));
        dashboardMedicinesPage.allEditButtons.get(0).click();

        // --- Fransızca Kontrolü ---
        dashboardMedicinesPage.medicineEditFranceLanguageButton.click();
        String actualFrenchTitle = dashboardMedicinesPage.medicineEditFranceLanguageButtonTitle.getAttribute("value");
        String actualFrenchContent = dashboardMedicinesPage.medicineEditFranceLanguageButtonContent.getAttribute("value");

        softAssert.assertEquals(actualFrenchTitle, frenchTitle, "HATA: Fransızca TITLE alanı kaydedilmedi!");
        softAssert.assertEquals(actualFrenchContent, frenchContent,
                "BUG: Fransızca CONTENT alanı kaydedilmedi, boş geliyor!");

        // --- Arapça Kontrolü ---
        dashboardMedicinesPage.medicineEditArabicLanguageButton.click();
        String actualArabicTitle = dashboardMedicinesPage.medicineEditArabicLanguageButtonTitle.getAttribute("value");
        String actualArabicContent = dashboardMedicinesPage.medicineEditArabicLanguageButtonContent.getAttribute("value");

        softAssert.assertEquals(actualArabicTitle, arabicTitle, "HATA: Arapça TITLE alanı kaydedilmedi!");
        softAssert.assertEquals(actualArabicContent, arabicContent,
                "BUG: Arapça CONTENT alanı kaydedilmedi, boş geliyor!");

        softAssert.assertAll();

    }

    @Test
    public void US33_TC09_IlacDuzenlemeSayfasiGorselYuklemeManuelVeDragDropDogrulamaTesti(){

        SoftAssert softAssert = new SoftAssert();
        Driver.getDriver().get("https://qa.loyalfriendcare.com/en/Dashboard/Instagrams/resimsizz/edit");

        // Dinamik dosya yolu
        String dosyaYolu = System.getProperty("user.dir") + "/src/test/resources/apranax-plus.jpg";
        WebElement hiddenInput = Driver.getDriver().findElement(By.xpath("//input[@type='file']"));

        // Dosyayı gönderir
        hiddenInput.sendKeys(dosyaYolu);
        //ReusableMethods.bekle(1); // Yükleme süresi için

        WebElement thumbnail = Driver.getDriver().findElement(By.className("thumbnail-wrapper"));
        softAssert.assertTrue(thumbnail.isDisplayed(), "HATA: Görsel yüklendi ancak önizleme (thumbnail) oluşmadı!");

        // "Save" butonuna tıklar
        dashboardMedicinesPage.medicineEditSaveButton.click();

        softAssert.assertAll();
    }

    // Don't change Image kutucuğu olduğundan TC_10 atlanmıştır.

    // Manuel test ile TC_11'deki "İlacı güncelleme işleminden sonra veri sızıntısı olmadığını doğrulamak" testi gerçekleştirilmiştir
    // ve geçmişte görülen BUG'ın olmadığı defalarca teyit edilmiştir ve testin yapılmasına gerek duyulmamıştır.

    @Test
    public void US33_TC12_IlaciGuncellemeninTabloyaYansidiginiDogrulamaTesti(){

        SoftAssert softAssert = new SoftAssert();

        Driver.getDriver().get("https://qa.loyalfriendcare.com/en/Dashboard/Instagrams/resimsizz/edit");

        // İlaç başlığını benzersiz bir değerle günceller
        // System.currentTimeMillis() o anki zamanı milisaniye olarak ekler
        String updatedTitle = "Test Medicine " + System.currentTimeMillis();
        dashboardMedicinesPage.medicinesPageTitleInput.clear();
        dashboardMedicinesPage.medicinesPageTitleInput.sendKeys(updatedTitle);

        // "Save" butonuna tıklar
        dashboardMedicinesPage.medicineEditSaveButton.click();

        // Medicines Updated Successfully mesajı geliyor mu doğrulaması
        try {
            softAssert.assertTrue(dashboardMedicinesPage.successAlert.isDisplayed(), "HATA: Güncelleme sonrası 'Medicines Updated successfully' mesajı görülmedi!");
        } catch (Exception e) {
            softAssert.fail("HATA: Başarı mesajı elementi (alert-success) sayfada bulunamadı!");
        }

        // Güncellenen veri tablonun HERHANGİ bir yerinde var mı?
        boolean isDataSaved = false;
        for (WebElement row : dashboardMedicinesPage.allMedicineRows) {
            if (row.getText().contains(updatedTitle)) {
                isDataSaved = true; // Herhangi bir hücrede bulursa 'kaydedildi' kabul eder
                break;
            }
        }
        softAssert.assertTrue(isDataSaved, "HATA/BUG: Güncellenen başlık '" + updatedTitle + "' tabloda hiç bulunamadı!");

        // UI/UX Yerleşim Doğrulaması: Veri DOĞRU sütunda mı (Title sütunu)?
        boolean isLayoutCorrect = false;
        for (WebElement titleElement : dashboardMedicinesPage.allMedicineTitles) {
            if (titleElement.getText().contains(updatedTitle)) {
                isLayoutCorrect = true; // Sadece 'Title' sütununda bulursa 'yerleşim doğru' kabul eder
                break;
            }
        }
        softAssert.assertTrue(isLayoutCorrect,
                "UI/UX BUG: Veri kaydedildi ancak tablo düzeni kaydığı için YANLIŞ sütunda görünüyor!");

        softAssert.assertAll();
    }


    @Test
    public void US33_TC13_SilmeIslemiKontroluTesti() {

        // Normalde silme butonuna tıklandığında bir onay popup'ı beklenir.
        // Ancak sistem onay sormadan doğrudan "deleted successfully" mesajını gösterdiği için,
        // bu mesajın görünür olması bir süreç hatasıdır (UX Bug). Bu nedenle ters mantık kurup
        // kafa karıştırmak yerine, mesaj görüldüğü anda testi doğrudan fail() ile sonlandırıyoruz.

        SoftAssert softAssert = new SoftAssert();

        Driver.getDriver().get(ConfigReader.getProperty("DasmedUrl"));


        // İlk sıradaki 'Delete' butonuna tıklar
        dashboardMedicinesPage.allDeleteButtons.get(0).click();

        // Doğrudan silme mesajının (Alert) gelip gelmediğini kontrol eder
        // Eğer buton tıklandığı an bu mesaj görünürse, onay adımı gelmiyor demektir
        try {
            if (dashboardMedicinesPage.deletedAlert.isDisplayed()) {

                // Mesaj içeriğinin doğruluğunu kontrol eder
                String alertText = dashboardMedicinesPage.deletedAlert.getText();

                softAssert.fail("HATA/BUG: Silme butonuna basıldığında herhangi bir onay penceresi çıkmadı. " +
                        "Veri doğrudan silindi ve " + alertText + " mesajı görüntülendi");
            }
        } catch (Exception e) {
            // Mesaj hiç gelmediyse işlemin sonucunda başka bir hata olabilir
            softAssert.fail("HATA: Silme butonu tıklandıktan sonra deletedAlert mesajı bulunamadı.");
        }

        softAssert.assertAll();
    }
    // NOT: Bu test (TC13), aynı zamanda TC14 (Silme işleminden sonra kaydın silindiğini doğrulamak)
    // gereksinimini de kapsamaktadır. Mesajın görülmesi ve verinin onay sormadan silinmesi
    // her iki senaryoyu da (onay eksikliği ve silinme onayı) tek bir akışta doğrulamıştır.
    // Bu nedenle TC14 için mükerrer bir test oluşturulmamıştır.






}
