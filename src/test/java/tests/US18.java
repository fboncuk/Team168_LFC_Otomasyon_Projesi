package tests;

import com.github.javafaker.Faker;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LcfHomePage.AppointmentBookingPage;
import pages.LcfHomePage.MedicinesMainPage;
import pages.LcfHomePage.SignButonsPage;
import utilities.ConfigReader;
import utilities.Driver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class US18 {
    WebDriver driver;
    SoftAssert softAssert;
    SignButonsPage signButonsPage;
    MedicinesMainPage medicinesMainPage;
    AppointmentBookingPage appointmentBookingPage;

    @BeforeMethod
    public void setUp() {

        Driver.getDriver().manage().deleteAllCookies();
        Driver.getDriver().get(ConfigReader.getProperty("LcfUrl"));

        softAssert = new SoftAssert();
        signButonsPage = new SignButonsPage();
        medicinesMainPage = new MedicinesMainPage();
        appointmentBookingPage = new AppointmentBookingPage();

        signButonsPage.signInLinki
                .click();
        signButonsPage.emailKutucusu
                .sendKeys(ConfigReader.getProperty("T07UserMail"));
        signButonsPage.passwordKutucusu
                .sendKeys(ConfigReader.getProperty("T07UserPassword"));
        signButonsPage.signInButtonOnay
                .click();

    }


    @Test (priority = 1)

    public void US18_TC01_AnaSayfadanIlaclarSayfasinaErisimKontrolu() {

        //Header Medicines bölümüne tıklayın

        medicinesMainPage.HomePageDdmMedicinesLink.click();


        //İlaçlar sayfasında bulunduğunuzu doğrulayın

        String expectedUrlContent = "Medicines";
        String actualUrl = Driver.getDriver().getCurrentUrl();

        softAssert.assertTrue(actualUrl.contains(expectedUrlContent),
                "Kullanıcı medicines sayfasına erişemedi.");

        softAssert.assertAll();


    }


    @Test (priority = 2)

    public void US18_TC02_IlaclarBilgileriGorunurlukKontrolu() {

        //Header Medicines bölümüne tıklayın
        medicinesMainPage.HomePageDdmMedicinesLink.click();


        //Medicines sidebar listedeki ilaç bilgilerinin görünürlüğünü doğrulayın

        List<String> expectedMedicinesNames = Arrays.asList(
                "Rimadyl (Carprofen)",
                "Revolution (Selamectin)",
                "Baytril (Enrofloxacin)",
                "Apoquel (Oclacitinib)",
                "Metacam (Meloxicam)");


        List<String> actualMedicinesNames = new ArrayList<>();

        for (int i = 0; i < 5 && i < medicinesMainPage.medicinesSideBarList.size(); i++) {

            actualMedicinesNames.add(medicinesMainPage.medicinesSideBarList.
                    get(i).
                    getText().
                    trim());
        }

        softAssert.assertEquals(actualMedicinesNames, expectedMedicinesNames,
                "Sayfadaki tüm ilaçlar görüntülenemiyor.");

        softAssert.assertAll();


    }

    @Test (priority = 3)

    public void US18_TC03_IlacSecilebilirlikKontrolu() {

        //Header Medicines bölümüne tıklayın

        medicinesMainPage.HomePageDdmMedicinesLink.click();

        //Sidebar'dan "Revolution (Selamectin)" secin

        medicinesMainPage.medicinesRevolutionSideBarLink.click();

        //Seçilen ilaçla detay sayfası açılan ilacın aynı olduğunu doğrulayın.

        String expectedMedicineName = "Revolution (Selamectin)";
        String actualMedicineName = medicinesMainPage.medicinesRevolutionTitle.getText();

        softAssert.assertEquals(actualMedicineName, expectedMedicineName,
                "Seçilen ilaç ile görünen ilaç birbirinden farklıdır.");

        softAssert.assertAll();

    }

    @Test (priority = 4)

    public void US18_TC04_IlacIcinRandevuOlusturma() {

        //Header Medicines bölümüne tıklayın

        medicinesMainPage.HomePageDdmMedicinesLink.click();

        //Medicines Body'de "Baytril (Enrofloxacin)" tıklayın

        medicinesMainPage.medicinesBodyBaytrilLink.click();

        //Seçilen ilacin appointment booking departments dd menüsünde olduğunu doğrulayın

        String expectedMedicineName = "Baytril (Enrofloxacin)";

        Actions actions = new Actions(Driver.getDriver());

        actions.moveToElement(appointmentBookingPage.departmentDropdownKutusu)
                        .click()
                        .perform();
        String departmentContent = appointmentBookingPage.departmentDropdownKutusu.getText();

        softAssert.assertFalse(departmentContent.contains(expectedMedicineName),

                "Departman menüsünde " + expectedMedicineName + " bulunmamalıydı ama bulundu.");

        //Seçilen ilacin appointment booking doctors dd menüsünde olduğunu doğrulayın

        actions.sendKeys(Keys.ESCAPE)
                        .perform();

        actions.moveToElement(appointmentBookingPage.doctorDropdownKutusu)
                        .click()
                        .perform();
        String doctorsContent = appointmentBookingPage.doctorDropdownKutusu.getText();

        softAssert.assertFalse(doctorsContent.contains(expectedMedicineName),

                "Doktor menüsünde " + expectedMedicineName + " bulunmamalıydı ama bulundu.");

        //Seçilen ilacın Appointment formun tamamında bulunduğunu doğrulayın

        String formText = appointmentBookingPage.appointmentFormContainer.getText();

        softAssert.assertTrue(formText.contains(expectedMedicineName),
                "Appointment Bookinf formunda '"
                        + expectedMedicineName + "' bilgisi bulunmadığı için randevu oluşturulamıyor.");

        softAssert.assertAll();


    }

    @AfterClass
    public void tearDown() {
        Driver.quitDriver();
    }


}