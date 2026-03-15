package tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LcfHomePage.HomeBodyHeaderSectionPage;
import pages.LcfHomePage.HomeBodyPage;
import pages.LcfHomePage.MedicinesMainPage;
import pages.LcfHomePage.SignButonsPage;
import utilities.ConfigReader;
import utilities.Driver;

public class US17 {

    // US17- Bir kayıtlı kullanıcı olarak,
    // Home Page sayfasından Aşılar sayfasına erişebilmeli,
    // Aşılar sayfasındaki aşı bilgilerini inceleyebilmeli ve
    // seçtiğim aşının sayfasına erişerek o aşı için randevu talebi oluşturabilmeliyim

    SignButonsPage signButonsPage = new SignButonsPage();
    HomeBodyHeaderSectionPage homeBodyHeaderSectionPage = new HomeBodyHeaderSectionPage();
    MedicinesMainPage medicinesMainPage = new MedicinesMainPage();
    SoftAssert softAssert = new SoftAssert();

    @BeforeClass
    public void setup() {
        // Homepage açılır
        Driver.getDriver().get(ConfigReader.getProperty("LcfUrl"));

        // SignIn butonu tıklanır ve T06 Tester geçerli kullanıcı bilgileri ile giriş yapılır
        signButonsPage.signInLinki.click();
        signButonsPage.emailKutusu.sendKeys(ConfigReader.getProperty("T06UserMail"));
        signButonsPage.passwordKutusu.sendKeys(ConfigReader.getProperty("T06UserPassword"));
        signButonsPage.signInButtonOnay.click();
    }


    @Test(priority = 1)
    public void US17_TC01_VaccinationsLinkiHoverTesti() {
        // Aşılar (Vaccinations) linki ve açılır menülerinin fare ile hover edildiğinde
        // görsel geri bildirim verdiğini doğrulamak.
        homeBodyHeaderSectionPage.

    }







//    @AfterClass
//    public void tearDown() {
//        Driver.quitDriver();
//    }

}
