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

import java.util.List;
import java.util.Set;

public class US06 {

    // US06 Bir ziyaretçi olarak,
    // Home page sayfasının footer bölümündeki tüm textlerin okunabilir olduğunu ve
    // butonların/linklerin aktif çalıştığını görmek istiyorum.

    HomeBodyFooterLinksPage homeBodyFooterLinksPage = new HomeBodyFooterLinksPage();
    SoftAssert softAssert = new SoftAssert();

    @BeforeClass
    public void setup(){
        Driver.getDriver().get(ConfigReader.getProperty("LcfUrl"));
    }




    @Test (priority = 1)
    public void US06_TC01_FooterGorunurlukTesti(){

        // Footer bölümündeki metin ve linklerin imla doğruluğu ve görünürlüğünün test edilmesi.

        String actualDepartmentText = homeBodyFooterLinksPage.departmentsTitle.getText();
        String expectedDepartmentText = "Departments";
        softAssert.assertEquals(actualDepartmentText, expectedDepartmentText, "Departments metni hatalı!");
        softAssert.assertTrue(homeBodyFooterLinksPage.departmentsTitle.isDisplayed(), "Department metni görünmüyor!");

        String actualWellnessText = homeBodyFooterLinksPage.departmentsWellnessLink.getText();
        String expectedWellnessText = "Wellness";
        softAssert.assertEquals(actualWellnessText, expectedWellnessText, "Wellness metni hatalı!");
        softAssert.assertTrue(homeBodyFooterLinksPage.departmentsWellnessLink.isDisplayed(), "Wellness metni görünmüyor!");

        String actualDentalCareText = homeBodyFooterLinksPage.departmentsDentalCareLink.getText();
        String expectedDentalCareText = "Dental Care";
        softAssert.assertEquals(actualDentalCareText, expectedDentalCareText, "Dental Care metni hatalı!");
        softAssert.assertTrue(homeBodyFooterLinksPage.departmentsDentalCareLink.isDisplayed(), "Dental Care metni görünmüyor!");

        String actualAnaesthesiaText = homeBodyFooterLinksPage.departmentsAnaesthesiaLink.getText();
        String expectedAnaesthesiaText = "Anaesthesia";
        softAssert.assertEquals(actualAnaesthesiaText, expectedAnaesthesiaText, "Anaesthesia metni hatalı!");
        softAssert.assertTrue(homeBodyFooterLinksPage.departmentsAnaesthesiaLink.isDisplayed(), "Anaesthesia metni görünmüyor!");

        String actualDermatologyText = homeBodyFooterLinksPage.departmentsDermatologyLink.getText();
        String expectedDermatologyText = "Dermatology";
        softAssert.assertEquals(actualDermatologyText, expectedDermatologyText, "Dermatology metni hatalı!");
        softAssert.assertTrue(homeBodyFooterLinksPage.departmentsDermatologyLink.isDisplayed(), "Dermatology metni görünmüyor!");

        String actualDiagnosticsText = homeBodyFooterLinksPage.departmentsDiagnosticsLink.getText();
        String expectedDiagnosticsText = "Diagnostics";
        softAssert.assertEquals(actualDiagnosticsText, expectedDiagnosticsText, "Diagnostics metni hatalı!");
        softAssert.assertTrue(homeBodyFooterLinksPage.departmentsDiagnosticsLink.isDisplayed(), "Diagnostics metni görünmüyor!");

        String actualFollowUsText = homeBodyFooterLinksPage.followUsTitle.getText();
        String expectedFollowUsText = "Follow Us";
        softAssert.assertEquals(actualFollowUsText, expectedFollowUsText, "Follow Us metni hatalı!");
        softAssert.assertTrue(homeBodyFooterLinksPage.followUsTitle.isDisplayed(), "Follow Us metni görünmüyor!");

        String actualContactsText = homeBodyFooterLinksPage.contactsTitle.getText();
        String expectedContactsText = "Contacts";
        softAssert.assertEquals(actualContactsText, expectedContactsText, "Contacts metni hatalı!");
        softAssert.assertTrue(homeBodyFooterLinksPage.contactsTitle.isDisplayed(), "Contacts metni görünmüyor!");

        String actualAdressText = homeBodyFooterLinksPage.adressText.getText();
        String expectedAdressText = "7634 S Reed Ave, Reedley, CA 93654";
        softAssert.assertTrue(actualAdressText.contains(expectedAdressText), "Adres metni hatalı!");
        softAssert.assertTrue(homeBodyFooterLinksPage.adressText.isDisplayed(), "Adres metni görünmüyor!");

        String actualTelephoneText = homeBodyFooterLinksPage.telephoneText.getText();
        String expectedTelephoneText = "+15596938754";
        softAssert.assertEquals(actualTelephoneText, expectedTelephoneText, "Telefon numarası hatalı!");
        softAssert.assertTrue(homeBodyFooterLinksPage.telephoneText.isDisplayed(), "Telefon numarası görünmüyor!");

        String actualEmailText = homeBodyFooterLinksPage.emailText.getText();
        String expectedEmailText = "info@loyalfriendcare.com";
        softAssert.assertEquals(actualEmailText, expectedEmailText, "E-mail adresi hatalı!");
        softAssert.assertTrue(homeBodyFooterLinksPage.emailText.isDisplayed(), "E-mail adresi görünmüyor!");

        softAssert.assertAll();

    }




    @Test (priority = 2)
    public void US06_TC02_FooterYakinlastirmaTesti(){
        // Footer bölümünün farklı yakınlaştırma oranlarına dinamik uyumunu doğrulamak
        // Bu testin manuel olarak yapılması daha kesin sonuçlar vereceğinden,
        // test kodu yazılmamıştır.

    }




    @Test (priority = 3)
    public void US06_TC03_FooterHoverTesti(){

        // Footer bölümündeki metinlerin hover durumunda renk ve hareket geri bildiriminin test edilmesi
        Actions actions = new Actions(Driver.getDriver());

        // ***********************

        // Hover öncesi Wellness renk ve hareket kontrolü
        String beforeHoverColor = homeBodyFooterLinksPage.departmentsWellnessLink.getCssValue("color");
        Point beforeHoverPosition = homeBodyFooterLinksPage.departmentsWellnessLink.getLocation();

        actions.moveToElement(homeBodyFooterLinksPage.departmentsWellnessLink).perform();
        ReusableMethods.bekle(1);

        // Hover sonrası Wellness renk ve hareket kontrolü
        String afterHoverColor = homeBodyFooterLinksPage.departmentsTitle.getCssValue("color");
        Point afterHoverPosition = homeBodyFooterLinksPage.departmentsWellnessLink.getLocation();

        softAssert.assertNotEquals(beforeHoverColor, afterHoverPosition, "Hover sonrası Wellness metni rengi değişmedi!");
        softAssert.assertNotEquals(beforeHoverPosition,afterHoverPosition,"Hover sonrası Wellness metni hareket etmedi!");

        // ***********************

        // Hover öncesi Dental Care renk ve hareket kontrolü
        beforeHoverColor = homeBodyFooterLinksPage.departmentsDentalCareLink.getCssValue("color");
        beforeHoverPosition = homeBodyFooterLinksPage.departmentsDentalCareLink.getLocation();

        actions.moveToElement(homeBodyFooterLinksPage.departmentsDentalCareLink).perform();
        ReusableMethods.bekle(1);

        // Hover sonrası Dental Care renk ve hareket kontrolü
        afterHoverColor = homeBodyFooterLinksPage.departmentsDentalCareLink.getCssValue("color");
        afterHoverPosition = homeBodyFooterLinksPage.departmentsDentalCareLink.getLocation();

        softAssert.assertNotEquals(beforeHoverColor, afterHoverColor, "Hover sonrası Dental Care metni rengi değişmedi!");
        softAssert.assertNotEquals(beforeHoverPosition,afterHoverPosition,"Hover sonrası Dental Care metni hareket etmedi!");

        // ***********************

        // Hover öncesi Anaesthesia renk ve hareket kontrolü
        beforeHoverColor = homeBodyFooterLinksPage.departmentsAnaesthesiaLink.getCssValue("color");
        beforeHoverPosition = homeBodyFooterLinksPage.departmentsAnaesthesiaLink.getLocation();

        actions.moveToElement(homeBodyFooterLinksPage.departmentsAnaesthesiaLink).perform();
        ReusableMethods.bekle(1);

        // Hover sonrası Anaesthesia renk ve hareket kontrolü
        afterHoverColor = homeBodyFooterLinksPage.departmentsAnaesthesiaLink.getCssValue("color");
        afterHoverPosition = homeBodyFooterLinksPage.departmentsAnaesthesiaLink.getLocation();

        softAssert.assertNotEquals(beforeHoverColor, afterHoverColor, "Hover sonrası Anaesthesia metni rengi değişmedi!");
        softAssert.assertNotEquals(beforeHoverPosition,afterHoverPosition,"Hover sonrası Anaesthesia metni hareket etmedi!");

        // ***********************

        // Hover öncesi Dermatology renk ve hareket kontrolü
        beforeHoverColor = homeBodyFooterLinksPage.departmentsDermatologyLink.getCssValue("color");
        beforeHoverPosition = homeBodyFooterLinksPage.departmentsDermatologyLink.getLocation();

        actions.moveToElement(homeBodyFooterLinksPage.departmentsDermatologyLink).perform();
        ReusableMethods.bekle(1);

        // Hover sonrası Dermatology renk ve hareket kontrolü
        afterHoverColor = homeBodyFooterLinksPage.departmentsDermatologyLink.getCssValue("color");
        afterHoverPosition = homeBodyFooterLinksPage.departmentsDermatologyLink.getLocation();

        softAssert.assertNotEquals(beforeHoverColor, afterHoverColor, "Hover sonrası Dermatology metni rengi değişmedi!");
        softAssert.assertNotEquals(beforeHoverPosition,afterHoverPosition,"Hover sonrası Dermatology metni hareket etmedi!");

        // ***********************

        // Hover öncesi Diagnostics renk ve hareket kontrolü
        beforeHoverColor = homeBodyFooterLinksPage.departmentsDiagnosticsLink.getCssValue("color");
        beforeHoverPosition = homeBodyFooterLinksPage.departmentsDiagnosticsLink.getLocation();

        actions.moveToElement(homeBodyFooterLinksPage.departmentsDiagnosticsLink).perform();
        ReusableMethods.bekle(1);

        // Hover sonrası Diagnostics renk ve hareket kontrolü
        afterHoverColor = homeBodyFooterLinksPage.departmentsDiagnosticsLink.getCssValue("color");
        afterHoverPosition = homeBodyFooterLinksPage.departmentsDiagnosticsLink.getLocation();

        softAssert.assertNotEquals(beforeHoverColor, afterHoverColor, "Hover sonrası Diagnostics metni rengi değişmedi!");
        softAssert.assertNotEquals(beforeHoverPosition,afterHoverPosition,"Hover sonrası Diagnostics metni hareket etmedi!");

        // ***********************

        // Hover öncesi Facebook renk kontrolü
        beforeHoverColor = homeBodyFooterLinksPage.facebookIconLinki.getCssValue("color");

        actions.moveToElement(homeBodyFooterLinksPage.facebookIconLinki).perform();
        ReusableMethods.bekle(1);

        // Hover sonrası Facebook renk ve hareket kontrolü
        afterHoverColor = homeBodyFooterLinksPage.facebookIconLinki.getCssValue("color");

        softAssert.assertNotEquals(beforeHoverColor, afterHoverColor, "Hover sonrası Facebook ikonu rengi değişmedi!");

        // ***********************

        // Hover öncesi Twitter renk kontrolü
        beforeHoverColor = homeBodyFooterLinksPage.twitterIconLinki.getCssValue("color");

        actions.moveToElement(homeBodyFooterLinksPage.twitterIconLinki).perform();
        ReusableMethods.bekle(1);

        // Hover sonrası Twitter renk ve hareket kontrolü
        afterHoverColor = homeBodyFooterLinksPage.twitterIconLinki.getCssValue("color");

        softAssert.assertNotEquals(beforeHoverColor, afterHoverColor, "Hover sonrası Twitter ikonu rengi değişmedi!");

        // ***********************

        // Hover öncesi Youtube renk kontrolü
        beforeHoverColor = homeBodyFooterLinksPage.youtubeIconLinki.getCssValue("color");

        actions.moveToElement(homeBodyFooterLinksPage.youtubeIconLinki).perform();
        ReusableMethods.bekle(1);

        // Hover sonrası Youtube renk ve hareket kontrolü
        afterHoverColor = homeBodyFooterLinksPage.youtubeIconLinki.getCssValue("color");

        softAssert.assertNotEquals(beforeHoverColor, afterHoverColor, "Hover sonrası Youtube ikonu rengi değişmedi!");

        // ***********************

        // Hover öncesi Pinterest renk kontrolü
        beforeHoverColor = homeBodyFooterLinksPage.pinterestIconLinki.getCssValue("color");

        actions.moveToElement(homeBodyFooterLinksPage.pinterestIconLinki).perform();
        ReusableMethods.bekle(1);

        // Hover sonrası Pinterest renk ve hareket kontrolü
        afterHoverColor = homeBodyFooterLinksPage.pinterestIconLinki.getCssValue("color");

        softAssert.assertNotEquals(beforeHoverColor, afterHoverColor, "Hover sonrası Pinterest ikonu rengi değişmedi!");

        // ***********************

        // Hover öncesi Instagram renk kontrolü
        beforeHoverColor = homeBodyFooterLinksPage.instagramIconLinki.getCssValue("color");

        actions.moveToElement(homeBodyFooterLinksPage.instagramIconLinki).perform();
        ReusableMethods.bekle(1);

        // Hover sonrası Instagram renk ve hareket kontrolü
        afterHoverColor = homeBodyFooterLinksPage.instagramIconLinki.getCssValue("color");

        softAssert.assertNotEquals(beforeHoverColor, afterHoverColor, "Hover sonrası Instagram ikonu rengi değişmedi!");

        softAssert.assertAll();
    }




    @Test (priority = 4)
    public void US06_TC04_FooterLinkTiklamaTesti(){
        // Footer linklerinin hatasız olduğunu ve tıklandıktan sonra beklenen sayfanın açıldığını doğrulama

        // Facebook link doğrulaması
        String href = homeBodyFooterLinksPage.facebookIconLinki.getAttribute("href");
        softAssert.assertTrue(href.toLowerCase().contains("facebook"),"Link, facebook ifadesi içermemektedir!");
        homeBodyFooterLinksPage.facebookIconLinki.click();
        ReusableMethods.bekle(2);
        softAssert.assertTrue(Driver.getDriver().getCurrentUrl()
                .contains("facebook"), "Link tıklandı ama facebook sayfası açılmadı!");
        ReusableMethods.tarihliTumSayfaResimCek(Driver.getDriver(),"facebook raporu");
        Driver.getDriver().navigate().back();

        // ***********************

        // X medya link doğrulaması
        href = homeBodyFooterLinksPage.twitterIconLinki.getAttribute("href");
        softAssert.assertTrue(href.toLowerCase().contains("x.com"),"Link, x.com ifadesi içermemektedir!");
        homeBodyFooterLinksPage.twitterIconLinki.click();
        ReusableMethods.bekle(2);
        softAssert.assertTrue(Driver.getDriver().getCurrentUrl()
                .contains("x.com"), "Link tıklandı ama X medya sayfası açılmadı!");
        ReusableMethods.tarihliTumSayfaResimCek(Driver.getDriver(),"twitter raporu");
        Driver.getDriver().navigate().back();

        // ***********************

        // Youtube link doğrulaması
        href = homeBodyFooterLinksPage.youtubeIconLinki.getAttribute("href");
        softAssert.assertTrue(href.toLowerCase().contains("youtube"),"Link, youtube ifadesi içermemektedir!");
        homeBodyFooterLinksPage.youtubeIconLinki.click();
        ReusableMethods.bekle(2);
        softAssert.assertTrue(Driver.getDriver().getCurrentUrl()
                .contains("youtube"), "Link tıklandı ama Youtube medya sayfası açılmadı!");
        Driver.getDriver().navigate().back();

        // ***********************

        // Pinterest link doğrulaması
        href = homeBodyFooterLinksPage.pinterestIconLinki.getAttribute("href");
        softAssert.assertTrue(href.toLowerCase().contains("pinterest"),"Link, pinterest ifadesi içermemektedir!");
        homeBodyFooterLinksPage.pinterestIconLinki.click();
        ReusableMethods.bekle(2);
        softAssert.assertTrue(Driver.getDriver().getCurrentUrl()
                .contains("pinterest"), "Link tıklandı ama Pinterest medya sayfası açılmadı!");
        Driver.getDriver().navigate().back();

        // ***********************

        // Instagram link doğrulaması
        href = homeBodyFooterLinksPage.instagramIconLinki.getAttribute("href");
        softAssert.assertTrue(href.toLowerCase().contains("instagram"),"Link, instagram ifadesi içermemektedir!");
        homeBodyFooterLinksPage.instagramIconLinki.click();
        ReusableMethods.bekle(2);
        softAssert.assertTrue(Driver.getDriver().getCurrentUrl()
                .contains("instagram"), "Link tıklandı ama Instagram medya sayfası açılmadı!");
        Driver.getDriver().navigate().back();

        softAssert.assertAll();

    }


    @AfterClass
    public void tearDown(){
        Driver.quitDriver();
    }


}
