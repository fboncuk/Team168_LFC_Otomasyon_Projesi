package tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LcfHomePage.HomeBodyFooterLinksPage;
import utilities.ConfigReader;
import utilities.Driver;

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

        String actualDepartmentText = homeBodyFooterLinksPage.footerDepartmentsTitle.getText();
        String expectedDepartmentText = "Departments";
        softAssert.assertEquals(actualDepartmentText, expectedDepartmentText, "Departments metni hatalı!");
        softAssert.assertTrue(homeBodyFooterLinksPage.footerDepartmentsTitle.isDisplayed(), "Department metni görünmüyor!");

        String actualWellnessText = homeBodyFooterLinksPage.footerDepartmentsWellnessLink.getText();
        String expectedWellnessText = "Wellness";
        softAssert.assertEquals(actualWellnessText, expectedWellnessText, "Wellness metni hatalı!");
        softAssert.assertTrue(homeBodyFooterLinksPage.footerDepartmentsWellnessLink.isDisplayed(), "Wellness metni görünmüyor!");

        String actualDentalCareText = homeBodyFooterLinksPage.footerDepartmentsDentalCareLink.getText();
        String expectedDentalCareText = "Dental Care";
        softAssert.assertEquals(actualDentalCareText, expectedDentalCareText, "Dental Care metni hatalı!");
        softAssert.assertTrue(homeBodyFooterLinksPage.footerDepartmentsDentalCareLink.isDisplayed(), "Dental Care metni görünmüyor!");

        String actualAnaesthesiaText = homeBodyFooterLinksPage.footerDepartmentsAnaesthesiaLink.getText();
        String expectedAnaesthesiaText = "Anaesthesia";
        softAssert.assertEquals(actualAnaesthesiaText, expectedAnaesthesiaText, "Anaesthesia metni hatalı!");
        softAssert.assertTrue(homeBodyFooterLinksPage.footerDepartmentsAnaesthesiaLink.isDisplayed(), "Anaesthesia metni görünmüyor!");

        String actualDermatologyText = homeBodyFooterLinksPage.footerDepartmentsDermatologyLink.getText();
        String expectedDermatologyText = "Dermatology";
        softAssert.assertEquals(actualDermatologyText, expectedDermatologyText, "Dermatology metni hatalı!");
        softAssert.assertTrue(homeBodyFooterLinksPage.footerDepartmentsDermatologyLink.isDisplayed(), "Dermatology metni görünmüyor!");

        String actualDiagnosticsText = homeBodyFooterLinksPage.footerDepartmentsDiagnosticsLink.getText();
        String expectedDiagnosticsText = "Diagnostics";
        softAssert.assertEquals(actualDiagnosticsText, expectedDiagnosticsText, "Diagnostics metni hatalı!");
        softAssert.assertTrue(homeBodyFooterLinksPage.footerDepartmentsDiagnosticsLink.isDisplayed(), "Diagnostics metni görünmüyor!");

        String actualFollowUsText = homeBodyFooterLinksPage.footerFollowUsTitle.getText();
        String expectedFollowUsText = "Follow Us";
        softAssert.assertEquals(actualFollowUsText, expectedFollowUsText, "Follow Us metni hatalı!");
        softAssert.assertTrue(homeBodyFooterLinksPage.footerFollowUsTitle.isDisplayed(), "Follow Us metni görünmüyor!");

        String actualContactsText = homeBodyFooterLinksPage.footerContactsTitle.getText();
        String expectedContactsText = "Contacts";
        softAssert.assertEquals(actualContactsText, expectedContactsText, "Contacts metni hatalı!");
        softAssert.assertTrue(homeBodyFooterLinksPage.footerContactsTitle.isDisplayed(), "Contacts metni görünmüyor!");

        String actualAdressText = homeBodyFooterLinksPage.footerAdressText.getText();
        String expectedAdressText = "7634 S Reed Ave, Reedley, CA 93654";
        softAssert.assertTrue(actualAdressText.contains(expectedAdressText), "Adres metni hatalı!");
        softAssert.assertTrue(homeBodyFooterLinksPage.footerAdressText.isDisplayed(), "Adres metni görünmüyor!");

        String actualTelephoneText = homeBodyFooterLinksPage.footerTelephoneText.getText();
        String expectedTelephoneText = "+15596938754";
        softAssert.assertEquals(actualTelephoneText, expectedTelephoneText, "Telefon numarası hatalı!");
        softAssert.assertTrue(homeBodyFooterLinksPage.footerTelephoneText.isDisplayed(), "Telefon numarası görünmüyor!");

        String actualEmailText = homeBodyFooterLinksPage.footerEmailText.getText();
        String expectedEmailText = "info@loyalfriendcare.com";
        softAssert.assertEquals(actualEmailText, expectedEmailText, "E-mail adresi hatalı!");
        softAssert.assertTrue(homeBodyFooterLinksPage.footerEmailText.isDisplayed(), "E-mail adresi görünmüyor!");

        softAssert.assertAll();
        Driver.quitDriver();

    }


    @Test
    public void US06_TC02_FooterYakinlastirmaTesti(){

    }



    @Test
    public void US06_TC03_FooterHoverTesti(){


    }


    @Test (priority = 1)
    public void US06_TC04_FooterTiklamaTesti(){

    }



    @AfterClass
    public void tearDown(){
        Driver.quitDriver();
    }


}
