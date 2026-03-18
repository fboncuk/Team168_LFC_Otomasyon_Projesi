package tests;

import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.LcfHomePage.AppointmentBookingPage;
import pages.LcfHomePage.DepartmentsMainPage;
import pages.LcfHomePage.SignButonsPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;
import java.util.Arrays;
import java.util.List;

@Listeners(utilities.Listeners.class)

public class US15 {

    SignButonsPage loginPage;
    DepartmentsMainPage departmentsPage;
    AppointmentBookingPage appointmentPage;
    Actions actions;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {

        Driver.getDriver().get(ConfigReader.getProperty("LcfUrl"));

        loginPage = new SignButonsPage();
        departmentsPage = new DepartmentsMainPage();
        appointmentPage = new AppointmentBookingPage();
        actions = new Actions(Driver.getDriver());

        ReusableMethods.bekle(1);
        loginPage.signInLinki
                .click();
        loginPage.emailKutusu
                .sendKeys(ConfigReader.getProperty("T04UserMail"));
        loginPage.passwordKutusu
                .sendKeys(ConfigReader.getProperty("T04UserPassword"));
        loginPage.signInButtonOnay
                .click();

        ReusableMethods.bekle(2);
    }

    @Test(groups = {"smoke", "regression"}, description = "Geçerli verilerle başarılı randevu oluşturma testi")
    public void TC001_PositiveAppointmentTest() {

        List<String> expectedDepartments = Arrays.asList(
                "Wellness", "Dental Care", "Anaesthesia", "Dermatology",
                "Diagnostics", "Vaccinations", "Pain Control", "Boarding"
        );

        List<String> actualDepartments = ReusableMethods.stringListeDondur(departmentsPage.allDepartments);

        Assert.assertTrue(actualDepartments.containsAll(expectedDepartments),
                "HATA: Beklenen kategorilerden bazıları sayfada bulunamadı!");

        Assert.assertTrue(departmentsPage.Dermatology.isDisplayed(), "Dermatoloji kategorisi görünür değil!");

        departmentsPage.Dermatology
                .click();
        appointmentPage.dateInput
                .sendKeys("03.03.2027");
        appointmentPage.phoneBox
                .sendKeys("5121231212");
        appointmentPage.departmentDropdownKutusu
                .click();
        appointmentPage.dermatologySecenegi
                .click();
        appointmentPage.doctorDropdownKutusu
                .click();
        appointmentPage.doktorSecenegi
                .click();
        appointmentPage.messageBox
                .sendKeys("Test Message");
        appointmentPage.appointmentBookingButton
                .click();

        String successMessage = "Congratulations on your well-deserved success.";
        Assert.assertTrue(Driver.getDriver().getPageSource().contains(successMessage));
    }

    @Test(groups={"regression"}, description ="Zorunlu alanlar (Tarih ve Telefon) boş bırakıldığında hata mesajı doğrulama")
    public void tc002_NegativeMissingDataTest() {

        departmentsPage.Dermatology
                .click();
        appointmentPage.departmentDropdownKutusu
                .click();
        appointmentPage.dermatologySecenegi
                .click();
        appointmentPage.doctorDropdownKutusu
                .click();
        appointmentPage.doktorSecenegi
                .click();
        appointmentPage.messageBox
                .sendKeys("Test Message");
        appointmentPage.appointmentBookingButton
                .click();


        Assert.assertTrue(Driver.getDriver().getPageSource().contains("This field is required."),
                "Bug012: Hata mesajı görüntülenemedi!");
    }

    @Test(groups={"regression"}, description="Geçmiş bir tarih seçildiğinde sistemin randevu oluşturmadığını doğrulama")
    public void tc003_NegativePastDateTest() {

        departmentsPage.Dermatology
                .click();
        appointmentPage.dateInput
                .sendKeys("01.01.2026");
        appointmentPage.phoneBox
                .sendKeys("5121231212");
        appointmentPage.appointmentBookingButton
                .click();
        ReusableMethods.bekle(1);

        boolean isSuccessVisible = Driver.getDriver().getPageSource().contains("Congratulations");

        Assert.assertFalse(isSuccessVisible, "BUG013: Sistem geçmiş tarihe randevu oluşturdu!");
    }

    @AfterMethod(alwaysRun = true)
    public void teardown() {
        Driver.quitDriver();
    }
}