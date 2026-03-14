package tests;

import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LcfHomePage.HomeBodyFooterLinksPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

import java.util.Arrays;
import java.util.List;

public class US06 {

    // US06 Bir ziyaretçi olarak,
    // Home page sayfasının footer bölümündeki tüm textlerin okunabilir olduğunu ve
    // butonların/linklerin aktif çalıştığını görmek istiyorum.

    HomeBodyFooterLinksPage homeBodyFooterLinksPage = new HomeBodyFooterLinksPage();
    SoftAssert softAssert = new SoftAssert();
    Actions actions = new Actions(Driver.getDriver());

    @BeforeClass
    public void setup() {
        Driver.getDriver().get(ConfigReader.getProperty("LcfUrl"));
    }


    @Test(priority = 1)
    public void US06_TC01_FooterGorunurlukTesti() {

        // Footer bölümündeki metin ve linklerin imla doğruluğu ve görünürlüğünün test edilmesi.

        // Footer bölümündeki textlerin listesi
        List<WebElement> footerTextElements = Arrays.asList(
                homeBodyFooterLinksPage.departmentsTitle,
                homeBodyFooterLinksPage.departmentsWellnessLink,
                homeBodyFooterLinksPage.departmentsDentalCareLink,
                homeBodyFooterLinksPage.departmentsAnaesthesiaLink,
                homeBodyFooterLinksPage.departmentsDermatologyLink,
                homeBodyFooterLinksPage.departmentsDiagnosticsLink,
                homeBodyFooterLinksPage.followUsTitle,
                homeBodyFooterLinksPage.contactsTitle,
                homeBodyFooterLinksPage.adressText,
                homeBodyFooterLinksPage.telephoneText,
                homeBodyFooterLinksPage.emailText
        );

        // Beklenen Text Listesi
        List<String> expectedTextList = Arrays.asList(
                "departments", "wellness", "dental care", "anaesthesia", "dermatology", "diagnostics",
                "follow us",
                "contacts", "7634 S Reed Ave, Reedley, CA 93654", "+15596938754", "info@loyalfriendcare.com"
        );

        for (int i = 0; i < footerTextElements.size(); i++) {
            WebElement actualTextElement = footerTextElements.get(i);
            String actualalTextString = actualTextElement.getText().toLowerCase();
            String expectedText = expectedTextList.get(i).toLowerCase();

            softAssert.assertEquals(actualalTextString, expectedText, expectedText + " metni hatalı!");
            softAssert.assertTrue(actualTextElement.isDisplayed(), expectedText + " metni görünmüyor!");
        }
        softAssert.assertAll();

    }


    @Test(priority = 2)
    public void US06_TC02_FooterYakinlastirmaTesti() {
        // Footer bölümünün farklı yakınlaştırma oranlarına dinamik uyumunu doğrulamak
        // Bu testin manuel olarak yapılması daha kesin sonuçlar vereceğinden,
        // test kodu yazılmamıştır.
    }


    @Test(priority = 3)
    public void US06_TC03_FooterHoverTesti() {

        // Footer bölümündeki Department metinlerin hover durumunda renk ve hareket geri bildiriminin test edilmesi
        List<WebElement> footerTextElements = Arrays.asList(
                homeBodyFooterLinksPage.departmentsWellnessLink,
                homeBodyFooterLinksPage.departmentsDentalCareLink,
                homeBodyFooterLinksPage.departmentsAnaesthesiaLink,
                homeBodyFooterLinksPage.departmentsDermatologyLink,
                homeBodyFooterLinksPage.departmentsDiagnosticsLink
        );

        for (int i = 0; i < footerTextElements.size(); i++) {

            // Hover öncesi renk ve pozisyon bilgisi alma
            WebElement footerElement = footerTextElements.get(i);
            ReusableMethods.bekle(1);

            String beforeHoverColor = footerElement.getCssValue("color");
            Point beforeHoverPosition = footerElement.getLocation();

            actions.moveToElement(footerElement).perform();

            // Hover sonrası renk ve pozisyon bilgisi alma
            String afterHoverColor = footerElement.getCssValue("color");
            Point afterHoverPosition = footerElement.getLocation();

            softAssert.assertNotEquals(beforeHoverColor, afterHoverColor
                    , "Hover sonrası " + footerElement.getText()+ " metni rengi değişmedi!");
            softAssert.assertNotEquals(beforeHoverPosition, afterHoverPosition
                    , "Hover sonrası " + footerElement.getText() + " metni hareket etmedi!");

        }

        // Footer bölümündeki sosyal medya ikonlarının hover durumunda renk geri bildiriminin test edilmesi

        List<WebElement> footerMediaElements = Arrays.asList(
                homeBodyFooterLinksPage.facebookIconLinki,
                homeBodyFooterLinksPage.twitterIconLinki,
                homeBodyFooterLinksPage.youtubeIconLinki,
                homeBodyFooterLinksPage.pinterestIconLinki,
                homeBodyFooterLinksPage.instagramIconLinki
        );

        for (int j = 0; j < footerMediaElements.size(); j++) {

            // Hover öncesi renk bilgisi alma
            WebElement mediaElement = footerMediaElements.get(j);
            ReusableMethods.bekle(1);

            String beforeHoverColor = mediaElement.getCssValue("color");

            actions.moveToElement(mediaElement).perform();

            // Hover sonrası renk bilgisi alma
            String afterHoverColor = mediaElement.getCssValue("color");

            softAssert.assertNotEquals(beforeHoverColor, afterHoverColor
                    , "Hover sonrası medya ikonunun rengi değişmedi!");
        }

    softAssert.assertAll();

    }


    @Test(priority = 4)
    public void US06_TC04_FooterLinkTiklamaTesti() {

        // Footer linklerinin hatasız olduğunu ve tıklandıktan sonra beklenen sayfanın açıldığını doğrulama
        List<WebElement> footermediaLinks = Arrays.asList(
                homeBodyFooterLinksPage.facebookIconLinki,
                homeBodyFooterLinksPage.twitterIconLinki,
                homeBodyFooterLinksPage.youtubeIconLinki,
                homeBodyFooterLinksPage.pinterestIconLinki,
                homeBodyFooterLinksPage.instagramIconLinki
        );

        // Beklenen Url listesi
        List<String> expectedUrlList = Arrays.asList(
                "facebook.com", "twitter.com", "youtube.com", "pinterest.com", "instagram.com"
        );

        for (int i = 0; i < footermediaLinks.size(); i++) {
            WebElement actualmediaLink = footermediaLinks.get(i);
            String expectedUrl = expectedUrlList.get(i);

            // Görünürlük kontrolü
            softAssert.assertTrue(actualmediaLink.isDisplayed(), "Ikon görünmüyor" + actualmediaLink);

            // Href kontrolü
            String href = actualmediaLink.getAttribute("href");

            if (href == null) {
                softAssert.fail("Ikon href değeri yok: " + actualmediaLink);
            } else {
                softAssert.assertTrue(href.contains(expectedUrl),
                        "Ikona ait link yanlış: Beklenen: " + expectedUrl + ", Bulunan: " + href);
            }
        }

        softAssert.assertAll();

    }


    @AfterClass
    public void tearDown(){
    Driver.quitDriver();
    }

}