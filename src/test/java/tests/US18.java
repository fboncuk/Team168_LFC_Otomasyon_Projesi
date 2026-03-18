package tests;

import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.LcfHomePage.AppointmentBookingPage;
import pages.LcfHomePage.MedicinesMainPage;
import pages.LcfHomePage.SignButonsPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;
import utilities.Listeners;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


//@Listeners(utilities.Listeners.class)

@org.testng.annotations.Listeners(Listeners.class)


public class US18 {
    SoftAssert softAssert;
    SignButonsPage signButonsPage;
    MedicinesMainPage medicinesMainPage;
    AppointmentBookingPage appointmentBookingPage;

    @BeforeClass
    public void setUpClass() {

        signButonsPage = new SignButonsPage();
        medicinesMainPage = new MedicinesMainPage();
        appointmentBookingPage = new AppointmentBookingPage();

        Driver.getDriver().manage().deleteAllCookies();
        Driver.getDriver().get(ConfigReader.getProperty("LcfUrl"));

        signButonsPage.signInLinki
                .click();
        signButonsPage.emailKutusu
                .sendKeys(ConfigReader.getProperty("T07UserMail"));
        signButonsPage.passwordKutusu
                .sendKeys(ConfigReader.getProperty("T07UserPassword"));
        signButonsPage.signInButtonOnay
                .click();
    }

    @BeforeMethod
    public void setUp() {
        softAssert = new SoftAssert();
        Driver.getDriver().get(ConfigReader.getProperty("MedUrl"));
    }

    @Test
    public void US18_TC01_AnaSayfadanIlaclarSayfasinaErisimKontrolu() {
        //İlaçlar sayfasında bulunduğunuzu doğrulayın

        String expectedUrlContent = "Medicines";
        String actualUrl = Driver.getDriver().getCurrentUrl();

        softAssert.assertTrue(actualUrl.contains(expectedUrlContent),
                "Kullanıcı medicines sayfasına erişemedi.");
        softAssert.assertAll();
    }

    @Test
    public void US18_TC02_IlaclarBilgileriGorunurlukKontrolu() {
        //İlaçların listenebildiğini doğrulayın

        List<String> expectedMedicinesNames = Arrays.asList(
                "Rimadyl (Carprofen)",
                "Revolution (Selamectin)",
                "Baytril (Enrofloxacin)",
                "Apoquel (Oclacitinib)",
                "Metacam (Meloxicam)");

        List<String> actualMedicinesNames = new ArrayList<>();
        for (int i = 0; i < 5 && i < medicinesMainPage.medicinesSideBarList
                .size(); i++) {
            actualMedicinesNames.add(medicinesMainPage.medicinesSideBarList
                    .get(i)
                    .getText()
                    .trim());
        }

        softAssert.assertEquals(actualMedicinesNames, expectedMedicinesNames,
                "Sayfadaki tüm ilaçlar görüntülenemiyor.");
        softAssert.assertAll();
    }

    @Test
    public void US18_TC03_IlacSecilebilirlikKontrolu() {
        //Sidebar'dan "Revolution (Selamectin)" secin
        medicinesMainPage.medicinesRevolutionSideBarLink
                .click();
        //Seçilen ilaçla detay sayfası açılan ilacın aynı olduğunu doğrulayın.
        String expectedMedicineName = "Revolution (Selamectin)";
        String actualMedicineName = medicinesMainPage.medicinesRevolutionTitle
                .getText();

        softAssert.assertEquals(actualMedicineName, expectedMedicineName,
                "Seçilen ilaç ile görünen ilaç birbirinden farklıdır.");
        softAssert.assertAll();
    }

    @Test
    public void US18_TC04_IlacIcinRandevuOlusturma() {
        //Medicines Body'de "Baytril (Enrofloxacin)" tıklayın
        medicinesMainPage.medicinesBodyBaytrilLink.click();

        //Seçilen ilacin appointment booking departments dd menüsünde olduğunu doğrulayın
        String expectedMedicineName = "Baytril (Enrofloxacin)";

        Actions actions = new Actions(Driver.getDriver());
        actions.moveToElement(appointmentBookingPage.departmentDropdownKutusu)
                .click().perform();
        String departmentContent = appointmentBookingPage.departmentDropdownKutusu
                .getText();
        softAssert.assertTrue(departmentContent
                .contains(expectedMedicineName));
        ReusableMethods.bekle(2);

        //Seçilen ilacin appointment booking doctors dd menüsünde olduğunu doğrulayın
        actions.sendKeys(Keys.ESCAPE)
                .perform();

        actions.moveToElement(appointmentBookingPage.doctorDropdownKutusu)
                .click()
                .perform();
        String doctorsContent = appointmentBookingPage.doctorDropdownKutusu
                .getText();
        softAssert.assertTrue(doctorsContent.contains(expectedMedicineName));
        ReusableMethods.bekle(2);

        String formText = appointmentBookingPage.appointmentFormContainer
                .getText();
        softAssert.assertTrue(formText.contains(expectedMedicineName));



        softAssert.assertAll();


    }

    @AfterClass
    public void tearDown() {
        Driver.quitDriver();
    }


}