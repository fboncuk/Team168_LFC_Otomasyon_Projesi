package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LcfHomePage.HomeBodyHeaderSectionPage;
import pages.LcfHomePage.SignButonsPage;
import utilities.ConfigReader;
import utilities.Driver;
import java.time.Duration;

public class US10 {


    // Kullanacağımız Page (Sayfa) Nesneleri
    HomeBodyHeaderSectionPage headerPage = new HomeBodyHeaderSectionPage();
    SignButonsPage signPage = new SignButonsPage();

    // Test edeceğimiz URL
    String anasayfaUrl = "https://qa.loyalfriendcare.com/en";

    // Elementleri beklemek için WebDriverWait
    WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));





    // =========================================================================
    // TC01 (AC1): Ziyaretçi anasayfasında Logo ve Sign In butonu görünürlüğü
    // =========================================================================
    @Test(priority = 1)
    public void TC01_ZiyaretciHeaderGorunurlukTesti() {

        // 1. Siteye (anasayfaya) gidiyoruz
        Driver.getDriver().get(ConfigReader.getProperty("LfcUrl"));

        // 2. Logonun görünürlüğünü bekleyip doğruluyoruz
        wait.until(ExpectedConditions.visibilityOf(headerPage.siteLogo));
        Assert.assertTrue(headerPage.siteLogo.isDisplayed(), "HATA: Loyal Friend Care logosu görünmüyor!");

        // 3. Sign In butonunun görünürlüğünü bekleyip doğruluyoruz
        wait.until(ExpectedConditions.visibilityOf(signPage.signInLinki));
        Assert.assertTrue(signPage.signInLinki.isDisplayed(), "HATA: Header'da Sign In butonu görünmüyor!");
    }

    // =========================================================================
    // TC02 (AC2): Doğru bilgilerle sisteme başarılı giriş yapılması
    // =========================================================================
    @Test(priority = 2)
    public void TC02_BasariliGirisTesti() {

        // 1. Anasayfaya gidiyoruz
        Driver.getDriver().get(ConfigReader.getProperty("LfcUrl"));

        // 2. Header'daki Sign In butonuna tıklıyoruz
        wait.until(ExpectedConditions.elementToBeClickable(signPage.signInLinki)).click();

        // 3. Email ve şifre kutularına doğru bilgileri giriyoruz
        wait.until(ExpectedConditions.visibilityOf(signPage.emailKutusu)).sendKeys("mehmet.user@loyalfriendcare.com");
        signPage.passwordKutusu.sendKeys("Loyal.123123");

        // 4. Giriş (Sign In) onay butonuna tıklıyoruz
        signPage.signInButtonOnay.click();

        // 5. Başarılı girişi doğrulamak için "Sign Out" butonunun görünür olmasını bekliyoruz
        wait.until(ExpectedConditions.visibilityOf(signPage.homePageSignOut));
        Assert.assertTrue(signPage.homePageSignOut.isDisplayed(), "HATA: Giriş yapılamadı, Sign Out butonu sayfada bulunamadı!");
    }




        // =========================================================================
        // TC03 (AC3): Kullanıcı girişi sonrası Header elementlerinin görünürlüğü
        // =========================================================================
        @Test(priority = 3)
        public void TC03_KullaniciHeaderGorunurlukTesti() {

            // 1. Sisteme giriş yapıyoruz
            basariliGirisYap();

            // 2. Logonun görünür olduğunu doğruluyoruz
            Assert.assertTrue(headerPage.siteLogo.isDisplayed(), "HATA: Logo görünmüyor!");

            // 3. Sign Out butonunun görünür olduğunu doğruluyoruz
            Assert.assertTrue(signPage.homePageSignOut.isDisplayed(), "HATA: Sign Out butonu görünmüyor!");

            // 4. Kullanıcı adının görünür olduğunu doğruluyoruz
            Assert.assertTrue(signPage.headerUserName.isDisplayed(), "HATA: Kullanıcı adı görünmüyor!");
        }

    // =========================================================================
    // TC04 (AC4): Logoya tıklanınca anasayfaya yönlendirme ve header kontrolü
    // =========================================================================
    @Test(priority = 4)
    public void TC04_LogoNavigasyonTesti() throws InterruptedException {

        // 1. Sisteme başarılı bir şekilde giriş yapıyoruz
        basariliGirisYap();

        // 2. Logoyu bulup tıklıyoruz
        wait.until(ExpectedConditions.elementToBeClickable(headerPage.siteLogo)).click();

        // 3. Yönlendirmenin tamamlanması için kısa bir bekleme
        Thread.sleep(2000);

        // 4. Gidilen URL'i alıp incelemek için konsola yazdırıyoruz
        String guncelUrl = Driver.getDriver().getCurrentUrl();
        System.out.println("Logoya tıklandıktan sonra gidilen URL: " + guncelUrl);

        // 5. Doğrulama: URL anasayfa ile aynı mı? (Bug varsa burada belli olur)
        Assert.assertEquals(guncelUrl, "https://qa.loyalfriendcare.com/en", "HATA: Logoya tıklayınca ana sayfaya dönmedi!");

        // 6. Doğrulama: Sign Out ve Kullanıcı Adı hala yerinde mi?
        Assert.assertTrue(signPage.homePageSignOut.isDisplayed(), "HATA: Sign Out butonu görünmüyor!");
        Assert.assertTrue(signPage.headerUserName.isDisplayed(), "HATA: Kullanıcı adı görünmüyor!");
    }

    // =========================================================================
    // TC05 (AC5): Başarılı çıkış (Sign Out) ve ziyaretçi görünümüne dönüş
    // =========================================================================
    @Test(priority = 5)
    public void TC05_BasariliCikisTesti() {

        // 1. Önce sisteme giriş yapıyoruz
        basariliGirisYap();

        // 2. Sign Out (Çıkış Yap) butonuna tıklıyoruz
        wait.until(ExpectedConditions.elementToBeClickable(signPage.homePageSignOut)).click();

        // 3. Çıkış işleminin tamamlandığından emin olmak için Sign In butonunun gelmesini bekliyoruz
        wait.until(ExpectedConditions.visibilityOf(signPage.signInLinki));

        // 4. Doğrulamalar (Assertions): Çıkış yaptıktan sonra ziyaretçi anasayfasında mıyız?
        Assert.assertTrue(headerPage.siteLogo.isDisplayed(), "HATA: Çıkış sonrası Logo ekranda görünmüyor!");
        Assert.assertTrue(signPage.signInLinki.isDisplayed(), "HATA: Çıkış sonrası Sign In butonu geri gelmedi!");
    }

    // =========================================================================
    // TC06 (AC6): Çıkış Sonrası Geri Tuşu Güvenlik Testi (Session Control)
    // =========================================================================
    @Test(priority = 6)
    public void TC06_OturumKapanmaGuvenlikTesti() throws InterruptedException {

        // 1. Önce sisteme giriş yapıyoruz
        basariliGirisYap();

        // 2. Çıkış yapıyoruz (Sign Out)
        wait.until(ExpectedConditions.elementToBeClickable(signPage.homePageSignOut)).click();

        // 3. Çıkış işleminin tamamlanmasını bekliyoruz
        wait.until(ExpectedConditions.visibilityOf(signPage.signInLinki));

        // 4. Kritik Adım: Tarayıcının "Geri" (Back) tuşuna basıyoruz!
        Driver.getDriver().navigate().back();

        // 5. Garantiye almak için sayfayı yeniliyoruz (Browser cache'inden kurtulmak için)
        Driver.getDriver().navigate().refresh();

        // 6. Doğrulama (Assertion): Geri tuşuna bastık ama sistem bizi içeri almamalı!
        // Ekranda "Sign Out" butonunun OLMADIĞINI kanıtlayacağız.
        boolean isSignOutVisible = false;
        try {
            isSignOutVisible = signPage.homePageSignOut.isDisplayed();
        } catch (Exception e) {
            // Eğer Selenium butonu bulamayıp hata verirse, bu bloğa düşer.
            // Ve evet, bizim de tam olarak istediğimiz şey butonu bulamaması!
            isSignOutVisible = false;
        }

        // Eğer isSignOutVisible "false" ise test geçer, "true" ise güvenlik açığı var demektir!
        Assert.assertFalse(isSignOutVisible, "GÜVENLİK AÇIĞI: Geri tuşuyla yetkisiz oturuma dönüldü!");
    }
    // =========================================================================
    // TC07: Negatif Giriş Testi (Kasten yanlış şifre ile kullanıcı denemesi)
    // =========================================================================
    @Test(priority = 7)
    public void TC07_KullaniciNegatifGirisTesti() {

        Driver.getDriver().get(ConfigReader.getProperty("LfcUrl"));
        wait.until(ExpectedConditions.elementToBeClickable(signPage.signInLinki)).click();

        // Doğru KULLANICI e-postası ama KASTEN YANLIŞ bir şifre giriyoruz!
        wait.until(ExpectedConditions.visibilityOf(signPage.emailKutusu)).sendKeys("mehmet.user@loyalfriendcare.com");
        signPage.passwordKutusu.sendKeys("YanlisSifre123!");
        signPage.signInButtonOnay.click();

        // 1. Doğrulama: O kırmızı hata mesajı (invalid-feedback) ekranda belirdi mi?
        WebElement hataMesaji = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("invalid-feedback")));
        Assert.assertTrue(hataMesaji.isDisplayed(), "HATA: Yanlış şifre girildiğinde hiçbir uyarı mesajı çıkmadı!");

        // 2. Doğrulama: Çıkan mesajın metni, BİZDEN İSTENEN metinle birebir aynı mı?
        // (Gereksinimlerde yazan OLMASI GEREKEN mesajı yazıyoruz)
        String beklenenMesaj = "Kullanıcı veya password bilgileri yanlış"; // Veya İngilizce karşılığı ne isteniyorsa

        // Eğer sitede çıkan mesaj, bizim beklediğimiz mesajla aynı DEĞİLSE test FAİL olur!
        Assert.assertEquals(hataMesaji.getText(), beklenenMesaj, "BUG BULUNDU: Hata mesajı metni istenenden farklı veya çevrilmemiş!");
    }
    // =========================================================================
    // HER TESTTEN SONRA ÇALIŞIP TARAYICIYI OTOMATİK KAPATMA
    // =========================================================================
    @org.testng.annotations.AfterMethod
    public void tearDown() {
        Driver.quitDriver(); // Eğer kırmızı yanarsa "Driver.closeDriver();" yapabilirsin
    }

    // =========================================================================
    // YARDIMCI METOT: Tekrardan Kurtarıcı Ortak Giriş Metodumuz
    // =========================================================================
    private void basariliGirisYap() {
        Driver.getDriver().get(ConfigReader.getProperty("LfcUrl"));
        wait.until(ExpectedConditions.elementToBeClickable(signPage.signInLinki)).click();
        wait.until(ExpectedConditions.visibilityOf(signPage.emailKutusu)).sendKeys("mehmet.user@loyalfriendcare.com");
        signPage.passwordKutusu.sendKeys("Loyal.123123");
        signPage.signInButtonOnay.click();
        wait.until(ExpectedConditions.visibilityOf(signPage.homePageSignOut));
    }
}