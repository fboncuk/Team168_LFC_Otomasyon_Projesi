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

public class US21 {


    // Sayfa nesnelerimizi (Page Objects) tanımlıyoruz
    HomeBodyHeaderSectionPage headerPage = new HomeBodyHeaderSectionPage();
    SignButonsPage signPage = new SignButonsPage();
    pages.LcfAdminPage.AdminBodyPage adminPage = new pages.LcfAdminPage.AdminBodyPage();

    WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
    String anasayfaUrl = "https://qa.loyalfriendcare.com/en";

    // =========================================================================
    // YARDIMCI METOT: ADMIN GİRİŞİ
    // =========================================================================
    private void basariliAdminGirisYap() {
        Driver.getDriver().get(ConfigReader.getProperty("LfcUrl"));
        wait.until(ExpectedConditions.elementToBeClickable(signPage.signInLinki)).click();

        wait.until(ExpectedConditions.visibilityOf(signPage.emailKutusu)).sendKeys("mehmet.admin@loyalfriendcare.com");
        signPage.passwordKutusu.sendKeys("Loyal.123123");
        signPage.signInButtonOnay.click();

        // Gizem çözüldü! Admin sayfası değil, normal Sign Out'u bekliyoruz
        wait.until(ExpectedConditions.visibilityOf(signPage.homePageSignOut));
    }

    @Test(priority = 1)
    public void TC01_AdminZiyaretciGorunumuTesti() {
        Driver.getDriver().get(ConfigReader.getProperty("LfcUrl"));
        Assert.assertTrue(headerPage.siteLogo.isDisplayed(), "HATA: Logo görünmüyor!");
        Assert.assertTrue(signPage.signInLinki.isDisplayed(), "HATA: Sign In butonu görünmüyor!");
    }

    @Test(priority = 2)
    public void TC02_AdminBasariliGirisTesti() {
        // 1. Giriş adımlarını manuel olarak burada yapıyoruz
        Driver.getDriver().get(ConfigReader.getProperty("LfcUrl"));
        wait.until(ExpectedConditions.elementToBeClickable(signPage.signInLinki)).click();

        wait.until(ExpectedConditions.visibilityOf(signPage.emailKutusu)).sendKeys("mehmet.admin@loyalfriendcare.com");
        signPage.passwordKutusu.sendKeys("Loyal.123123");
        signPage.signInButtonOnay.click();

        // 2. BUG KONTROLÜ: Ekranda o kırmızı hata mesajı (invalid-feedback) çıkıyor mu?
        try {
            // Ekran görüntündeki class name olan "invalid-feedback"i arıyoruz
            WebElement hataMesaji = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("invalid-feedback")));
            String gerceklesenHata = hataMesaji.getText();

            // Eğer buraya düştüysek BUG var demektir! Testi bilerek patlatıp raporluyoruz.
            System.out.println("🚨 KRİTİK BUG YAKALANDI: Doğru admin bilgileri girilmesine rağmen sistem hata veriyor!");
            System.out.println("Ekranda Çıkan Hata: " + gerceklesenHata);

            Assert.fail("BUG: Geçerli admin bilgileriyle giriş YAPILAMADI! Sistem şu hatayı verdi: " + gerceklesenHata);

        } catch (Exception e) {
            // Eğer 10 saniye boyunca o kırmızı yazıyı bulamazsa (yani bug düzeltilmişse)
            // giriş başarılı olmuş demektir. Normal testimize devam ederiz.
            Assert.assertTrue(signPage.homePageSignOut.isDisplayed(), "HATA: Admin girişi başarısız, Sign Out görünmüyor!");
            System.out.println("Giriş başarılı ");
        }
    }

    @Test(priority = 3)
    public void TC03_AdminHeaderElementleriTesti() {
        basariliAdminGirisYap();
        Assert.assertTrue(headerPage.siteLogo.isDisplayed());
        Assert.assertTrue(signPage.homePageSignOut.isDisplayed());
        // Profil dropdown değil, normal kullanıcı adı elementini arıyoruz
        Assert.assertTrue(signPage.headerUserName.isDisplayed());
    }

    // =========================================================================
    // Logo'ya tıklayınca tekrar Anasayfaya dönüş kontrol testi
    // =========================================================================
    @Test(priority = 4)
    public void TC04_AdminLogoNavigasyonTesti() throws InterruptedException {
        basariliAdminGirisYap();
        wait.until(ExpectedConditions.elementToBeClickable(headerPage.siteLogo)).click();
        Thread.sleep(1000);
        Assert.assertEquals(Driver.getDriver().getCurrentUrl(), anasayfaUrl, "HATA: Logoya tıklayınca anasayfaya dönmedi!");
    }

    @Test(priority = 5)
    public void TC05_AdminBasariliCikisTesti() {
        basariliAdminGirisYap();
        // Karmaşık admin menüsü yerine direkt Sign Out linkine tıklıyoruz!
        wait.until(ExpectedConditions.elementToBeClickable(signPage.homePageSignOut)).click();

        wait.until(ExpectedConditions.visibilityOf(signPage.signInLinki));
        Assert.assertTrue(signPage.signInLinki.isDisplayed(), "HATA: Çıkış sonrası anasayfaya dönülemedi!");
    }
    // =========================================================================
    // EKSTRA TEST: Negatif Giriş Testi (Kasten yanlış şifre ile deneme)
    // =========================================================================
    @Test(priority = 6)
    public void TC06_AdminNegatifGirisTesti() {

        Driver.getDriver().get(ConfigReader.getProperty("LfcUrl"));
        wait.until(ExpectedConditions.elementToBeClickable(signPage.signInLinki)).click();

        // Doğru e-posta ama KASTEN YANLIŞ bir şifre giriyoruz!
        wait.until(ExpectedConditions.visibilityOf(signPage.emailKutusu)).sendKeys("mehmet.admin@loyalfriendcare.com");
        signPage.passwordKutusu.sendKeys("YanlisSifre123!");
        signPage.signInButtonOnay.click();

        // 1. Doğrulama: O kırmızı hata mesajı (invalid-feedback) ekranda belirdi mi?
        WebElement hataMesaji = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("invalid-feedback")));
        Assert.assertTrue(hataMesaji.isDisplayed(), "HATA: Yanlış şifre girildiğinde hiçbir uyarı mesajı çıkmadı!");

        // 2. Doğrulama: Çıkan mesajın metni, BİZDEN İSTENEN metinle birebir aynı mı?
        // (Buraya AC'de veya gereksinimlerde yazan OLMASI GEREKEN mesajı yazıyoruz)
        String beklenenMesaj = "Admin veya password bilgileri yanlış"; // Veya İngilizce karşılığı ne isteniyorsa

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
    }

