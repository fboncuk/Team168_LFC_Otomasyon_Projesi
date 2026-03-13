package tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LcfHomePage.HomeBodySearchBoxPage;
import pages.LcfHomePage.SignButonsPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

public class US04 {

    HomeBodySearchBoxPage homePage;
    SignButonsPage loginPage;

    @BeforeMethod
    public void setUp() {

        homePage = new HomeBodySearchBoxPage();
        loginPage = new SignButonsPage();
        Driver.getDriver().get(ConfigReader.getProperty("LcfUrl"));
    }

    @Test(groups = "smoke", description = "SMOKE: Temel arama fonksiyonu kontrolü")
    public void TC_001_SMOKE_PozitifAramaTesti() {

        homePage.searchBox
                .sendKeys("Rabies");
        homePage.searchButton
                .click();

        ReusableMethods.bekle(2);
        ReusableMethods.tarihliTumSayfaResimCek(Driver.getDriver());

        Assert.assertTrue(homePage.resultText
                .isDisplayed(), "Arama sonucu görüntülenemedi!");
    }

    @Test(groups = "E2E", description = "E2E: Kullanıcı girişi sonrası arama akışı")
    public void TC_002_E2E_LoginSonrasiAramaTesti() {

        loginPage.signInLinki
                .click();
        loginPage.emailKutucusu
                .sendKeys(ConfigReader.getProperty("T04UserMail"));
        loginPage.passwordKutucusu
                .sendKeys(ConfigReader.getProperty("T04UserPassword"));
        loginPage.signInButtonOnay
                .click();

        ReusableMethods.bekle(2);

        homePage.searchBox
                .sendKeys("Wellness");
        homePage.searchButton
                .click();

        ReusableMethods.bekle(2);
        ReusableMethods.tarihliTumSayfaResimCek(Driver.getDriver());

        Assert.assertTrue(homePage.resultText.isDisplayed(), "Login sonrası arama sonucu gelmedi!");
    }

    @Test(groups = "regression", description = "REGRESSION: Negatif karakterlerle arama ve hata mesajı kontrolü")
    public void TC_003_REGRESSION_NegatifAramaTesti() {

        homePage.searchBox
                .sendKeys("!!!");
        homePage.searchButton
                .click();

        ReusableMethods.bekle(2);
        ReusableMethods.tarihliTumSayfaResimCek(Driver.getDriver());

        Assert.assertTrue(homePage.errorMessageList.size() > 0, "BUG: Uyarı mesajı görüntülenemedi!");
    }

    @Test(groups = "regression", description = "REGRESSION: Arama motoru harf duyarlılığı testi")
    public void TC_004_REGRESSION_BuyukKucukHarfTesti() {

        homePage.searchBox
                .sendKeys("DEnTal");
        homePage.searchButton
                .click();

        String ilkSonuc = homePage.resultText.getText();
        Driver.getDriver().navigate().back();

        homePage.searchBox
                .clear();
        homePage.searchBox
                .sendKeys("dental");
        homePage.searchButton
                .click();


        String ikinciSonuc = homePage.resultText.getText();
        ReusableMethods.tarihliTumSayfaResimCek(Driver.getDriver());

        Assert.assertEquals(ilkSonuc.toLowerCase(), ikinciSonuc.toLowerCase(),
                "HATA: Büyük harf ('DEnTal') ve küçük harf ('dental') aramaları farklı sonuçlar döndürdü!");
    }

    @AfterMethod
    public void teardown() {
        Driver.quitDriver();
    }
}