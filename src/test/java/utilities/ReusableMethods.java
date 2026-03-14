package utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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

    public static void tarihliTumSayfaResimCek (WebDriver driver){

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
    public static void adminDashboardLinkKontrol(WebElement linkElement, String ExpectedUrlIcerik) {

        // Linke tıkla
        linkElement.click();

        // URL'i al
        String actualUrl = Driver.getDriver().getCurrentUrl();

        // URL doğrula
        Assert.assertTrue(actualUrl.contains(ExpectedUrlIcerik),
                "HATA: " + ExpectedUrlIcerik + " modülüne yönlendirme yapılamadı! Gidilen URL: " + actualUrl);
    }

    public static WebElement waitForVisibility(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static WebElement waitForClickablity(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

}
