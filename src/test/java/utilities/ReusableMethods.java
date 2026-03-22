package utilities;

import com.aventstack.extentreports.ExtentTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class ReusableMethods {

    public static void bekle (int saniye) {
        try {
            Thread.sleep(saniye*1000);
        } catch (InterruptedException e) {
            System.out.println("Thread.sleep komutu çalışmadı");;
        }
    }
    // *********************************

    public static List<String> stringListeDondur(List<WebElement> webElementListesi) {

        List<String> stringList = new ArrayList<>();

        for (WebElement eachElement : webElementListesi) {
            stringList.add(eachElement.getText());
        }
    return stringList;
    }
    // *********************************

    public static void titleIleWindowGecisYap (WebDriver driver, String hedefWindowunTitle){

    Set<String> acikWindowlarinWhdleri = driver.getWindowHandles();
        System.out.println(acikWindowlarinWhdleri);

        for (String eachWhd : acikWindowlarinWhdleri){
            driver.switchTo().window(eachWhd);

            if (driver.getTitle().equals(hedefWindowunTitle)){
                break;
            }
        }

    }
    // *********************************

    public static void urlIleWindowGecisYap(WebDriver driver, String hedefUrl){

        Set<String> acikTumWindowsWhdSeti = driver.getWindowHandles();

        for (String eachWhd : acikTumWindowsWhdSeti){
            driver.switchTo().window(eachWhd);

            if (driver.getCurrentUrl().equals(hedefUrl)){
                break;
            }
        }
    }
    // *********************************

    public static void tumSayfaResimCek (WebDriver driver){

        TakesScreenshot takescreenshot = (TakesScreenshot) driver;

        String dosyaYolu = "target/screenshots/tumSayfaResmi.jpeg";
        File tumSayfaResmi = new File(dosyaYolu);

        File geciciDosya = takescreenshot.getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.copyFile(geciciDosya,tumSayfaResmi);
        } catch (IOException e) {
            System.out.println("Resim çekilemedi");
        }
    }
    // *********************************

    public static void tumSayfaResimCek (WebDriver driver, String raporIsmi){

        TakesScreenshot takescreenshot = (TakesScreenshot) driver;

        String dosyaYolu = "target/screenshots/" + raporIsmi + ".jpeg";
        File tumSayfaResmi = new File(dosyaYolu);

        File geciciDosya = takescreenshot.getScreenshotAs(OutputType.FILE);

            try {
            FileUtils.copyFile(geciciDosya,tumSayfaResmi);
        } catch (IOException e) {
            System.out.println("Resim çekilemedi");
        }
    }
    // *********************************

    public static String tarihliTumSayfaResimCek (WebDriver driver){

        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("_yyMMdd_HHmmss");
        String tarihEtiketi = localDateTime.format(format); // _260215_225020

        TakesScreenshot takescreenshot = (TakesScreenshot) driver;

        String dosyaYolu = "target/screenshots/tumSayfaResmi" + tarihEtiketi + ".jpeg";
        File tumSayfaResmi = new File(dosyaYolu);

        File geciciDosya = takescreenshot.getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.copyFile(geciciDosya,tumSayfaResmi);
        } catch (IOException e) {
            System.out.println("Resim çekilemedi");
        }
        return tarihEtiketi;
    }
    // *********************************

    public static void tarihliTumSayfaResimCek (WebDriver driver, String raporIsmi){

        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("_yyMMdd_HHmmss");
        String tarihEtiketi = localDateTime.format(format); // _260215_225020

        TakesScreenshot takescreenshot = (TakesScreenshot) driver;

        String dosyaYolu = "target/screenshots/" + raporIsmi + tarihEtiketi + ".jpeg";
        File tumSayfaResmi = new File(dosyaYolu);

        File geciciDosya = takescreenshot.getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.copyFile(geciciDosya,tumSayfaResmi);
        } catch (IOException e) {
            System.out.println("Resim çekilemedi");
        }
    }
    // *********************************

    public static void webElementResimCek(WebElement webElement){

        String dosyaYolu = "target/screenshots/webElementResmi.jpeg";
        File webElementResim = new File(dosyaYolu);

        File geciciDosya = webElement.getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.copyFile(geciciDosya,webElementResim);
        } catch (IOException e) {
            System.out.println("Webelement resmi çekilemedi");
        }
    }
    // *********************************

    public static void webElementResimCek(WebElement webElement,String raporIsmi){

        String dosyaYolu = "target/screenshots/" + raporIsmi + ".jpeg";
        File webElementResim = new File(dosyaYolu);

        File geciciDosya = webElement.getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.copyFile(geciciDosya,webElementResim);
        } catch (IOException e) {
            System.out.println("Webelement resmi çekilemedi");
        }
    }
    // *********************************

    public static void tarihliWebElementResimCek(WebElement webElement){
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("_yyMMdd_HHmmss");
        String tarihEtiketi = localDateTime.format(format); // _260215_225020

        String dosyaYolu = "target/screenshots/webElementResmi" + tarihEtiketi + ".jpeg";
        File webElementResim = new File(dosyaYolu);

        File geciciDosya = webElement.getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.copyFile(geciciDosya,webElementResim);
        } catch (IOException e) {
            System.out.println("Webelement resmi çekilemedi");
        }
    }
    // *********************************

    public static void tarihliWebElementResimCek(WebElement webElement,String raporIsmi){

        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("_yyMMdd_HHmmss");
        String tarihEtiketi = localDateTime.format(format); // _260215_225020

        String dosyaYolu = "target/screenshots/" + raporIsmi + tarihEtiketi + ".jpeg";
        File webElementResim = new File(dosyaYolu);

        File geciciDosya = webElement.getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.copyFile(geciciDosya,webElementResim);
        } catch (IOException e) {
            System.out.println("Webelement resmi çekilemedi");
        }
    }
    // *********************************

    /*
     * Dashboard üzerindeki 'Learn More' linklerinin fonksiyonelliğini test eder.
     * Verilen elemente tıklar, yönlendirme yapılan URL'in beklenen içeriği
     * barındırıp barındırmadığını kontrol eder.
     * @param linkElement ---> Tıklanacak olan 'Learn More' butonu (WebElement)
     * @param ExpectedUrlIcerik ---> URL içinde geçmesi beklenen anahtar kelime (String)
     */
    public static void adminDashboardLinkKontrol(WebElement linkElement, String ExpectedUrlIcerik, ExtentTest extentTest) {

        SoftAssert softAssert = new SoftAssert();

        // Rapora bilgi veriyoruz
        extentTest.info(ExpectedUrlIcerik + " linkine tıklanıyor...");

        // Linke tıkla
        linkElement.click();

        // URL'i al
        String actualUrl = Driver.getDriver().getCurrentUrl();

        // URL doğrula
        boolean isUrlCorrect = actualUrl.contains(ExpectedUrlIcerik);

        if (isUrlCorrect) {
            extentTest.pass("Yönlendirme başarılı. Gidilen URL: " + actualUrl);
        } else {
            extentTest.fail("HATA: Beklenen URL içeriği: " + ExpectedUrlIcerik + " | Gidilen URL: " + actualUrl);
        }

        softAssert.assertTrue(isUrlCorrect,
                "HATA: " + ExpectedUrlIcerik + " modülüne yönlendirme yapılamadı! Gidilen URL: " + actualUrl);

        softAssert.assertAll();
    }

    public static WebElement waitForVisibility(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static WebElement waitForClickablity(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }


    // Alert açıkken ekran görüntüsü alma
//    public static String alertVarkenScreenshot(String dosyaAdi) {
//        try {
//            LocalDateTime now = LocalDateTime.now();
//            String tarih = now.format(DateTimeFormatter.ofPattern("_yyMMdd_HHmmss"));
//            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//            Rectangle rect = new Rectangle(screenSize);
//            BufferedImage image = new Robot().createScreenCapture(rect);
//            String dosyaYolu = "target/screenshots/" + dosyaAdi + tarih + ".png";
//            ImageIO.write(image, "png", new File(dosyaYolu));
//            System.out.println("Alert açıkken screenshot alindi: " + dosyaYolu);
//        } catch (Exception e) {
//            System.out.println("Alert anında screenshot alınamadı: " + e.getMessage());
//        }
//        return dosyaAdi;
//    }

    // Alert açıkken ekran görüntüsü alma
    public static void alertVarkenScreenshot(String dosyaAdi) {
        try {
            LocalDateTime now = LocalDateTime.now();
            String tarih = now.format(DateTimeFormatter.ofPattern("_yyMMdd_HHmmss"));
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            Rectangle rect = new Rectangle(screenSize);
            BufferedImage image = new Robot().createScreenCapture(rect);
            String dosyaYolu = "target/screenshots/" + dosyaAdi + tarih + ".png";
            ImageIO.write(image, "png", new File(dosyaYolu));
            System.out.println("Alert açıkken screenshot alindi: " + dosyaYolu);
        } catch (Exception e) {
            System.out.println("Alert anında screenshot alınamadı: " + e.getMessage());
        }
    }

//    public static String alertVarkenScreenshot(String dosyaAdi) {
//        try {
//            LocalDateTime now = LocalDateTime.now();
//            String tarih = now.format(DateTimeFormatter.ofPattern("_yyMMdd_HHmmss"));
//            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//            Rectangle rect = new Rectangle(screenSize);
//            BufferedImage image = new Robot().createScreenCapture(rect);
//            String dosyaYolu = "target/screenshots/" + dosyaAdi + tarih + ".png";
//            ImageIO.write(image, "png", new File(dosyaYolu));
//            return dosyaYolu;
//        } catch (Exception e) {
//            System.out.println("Screenshot alınamadı: " + e.getMessage());
//            return null;
//        }
//    }



//    public static String alertVarkenScreenshot(String isim) {
//        String tarih = new SimpleDateFormat("_yyMMdd_HHmmss").format(new Date());
//        // Raporun içine yazılacak relative path
//        String relativePath = "screenshots/" + isim + tarih + ".png";
//        // Dosyanın kaydedileceği tam yol
//        String fullPath = System.getProperty("user.dir") + "/test-output/" + relativePath;
//
//        try {
//            // SELENIUM'A HİÇBİR ŞEY SORMUYORUZ (Hatanın sebebi buydu)
//            // Doğrudan Java Robot ile ekranı yakalıyoruz.
//            Robot robot = new Robot();
//
//            // Ekran boyutunu Windows'tan alıyoruz
//            java.awt.Rectangle screenRect = new java.awt.Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
//            BufferedImage screenFullImage = robot.createScreenCapture(screenRect);
//
//            File destFile = new File(fullPath);
//            destFile.getParentFile().mkdirs(); // Klasör yoksa oluştur
//            ImageIO.write(screenFullImage, "png", destFile);
//
//            System.out.println("Robot Screenshot (Tam Ekran) başarıyla alındı: " + fullPath);
//        } catch (Exception e) {
//            System.out.println("Robot Screenshot Hatası: " + e.getMessage());
//        }
//        return relativePath; // Raporun dosyayı bulması için dönen yol
//    }




    public static void hover(WebElement targetElement) {
        /*
         * Parametre olarak gelen WebElement'in üzerine mouse imlecini götürür.
         * Özellikle mouse hover (üzerine gelince açılan) menüleri tetiklemek için kullanılır.
         * @param targetElement üzerine gidilecek olan WebElement
         * Yüklenme zaman alırsa testinize bekleme methodu ekleyebilirsiniz.
         */
        Actions actions = new Actions(Driver.getDriver());
        actions.moveToElement(targetElement).perform();
    }

    public static String getScreenshot(String name) throws IOException {
        // naming the screenshot with the current date to avoid duplication
        String date = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        // TakesScreenshot is an interface of selenium that takes the screenshot
        TakesScreenshot ts = (TakesScreenshot) Driver.getDriver();
        File source = ts.getScreenshotAs(OutputType.FILE);
        // full path to the screenshot location
        String target = System.getProperty("user.dir") + "/test-output/Screenshots/" + name + date + ".png";
        File finalDestination = new File(target);
        // save the screenshot to the path given
        FileUtils.copyFile(source, finalDestination);
        String relativePath = "Screenshots/" + name + date + ".png";

        return relativePath;
    }

    public static void waitForPageToLoad(long timeout) {
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        };
        try {
            System.out.println("Waiting for page to load...");
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
            wait.until(expectation);
        } catch (Throwable error) {
            System.out.println(
                    "Timeout waiting for Page Load Request to complete after " + timeout + " seconds");
        }
    }


    public static String screenshotWithAlert(String screenshotName) {
        try {
            // alert açıkken ekran görüntüsü al
            TakesScreenshot ts = (TakesScreenshot) Driver.getDriver();
            String date = new SimpleDateFormat("ddMMyy_HHmmss").format(new Date());
            String path = "target/screenshots/" + screenshotName + "_" + date + ".png";
            File src = ts.getScreenshotAs(OutputType.FILE);
            File dest = new File(path);
            FileUtils.copyFile(src, dest);
            return path;
        } catch (Exception e) {
            System.out.println("Alert varken screenshot alınamadı: " + e.getMessage());
            return null;
        }
    }

    public static boolean isReallyClickable(WebDriver driver, WebElement element, int timeoutInSeconds) {
        try {
            if (!element.isDisplayed() || !element.isEnabled()) return false;

            // JS üzerinden click olayı tetikle
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);

            // opsiyonel: tıklamadan sonra beklenen UI değişikliğini doğrula
            // Örneğin URL değişti mi veya overlay kayboldu mu
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    }

