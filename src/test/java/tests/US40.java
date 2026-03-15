package tests;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LcfAdminPage.DashboardDepartmentsPage;
import pages.LcfAdminPage.DashboardVaccinationsPage;
import pages.LcfHomePage.SignButonsPage;
import utilities.ConfigReader;
import utilities.Driver;

public class US40 {

    WebDriver driver;
    SoftAssert softAssert;
    SignButonsPage signButonsPage;
    DashboardVaccinationsPage dashboardVaccinationsPage;
    DashboardDepartmentsPage dashboardDepartmentsPage;

    @BeforeMethod
    public void setUp() {

        softAssert = new SoftAssert();
        signButonsPage = new SignButonsPage();
        dashboardDepartmentsPage = new DashboardDepartmentsPage();
        dashboardVaccinationsPage = new DashboardVaccinationsPage();

        Driver.getDriver().manage().deleteAllCookies();
        Driver.getDriver().get(ConfigReader.getProperty("DasUrl"));
        signButonsPage.emailKutusu
                .sendKeys(ConfigReader.getProperty("T07AdminMail"));
        signButonsPage.passwordKutusu
                .sendKeys(ConfigReader.getProperty("T07AdminPassword"));
        signButonsPage.signInButtonOnay
                .click();

    }


    @Test
    public void US40_TC01_AsiEklemeFonksiyonuKontrolu (){


        //Admin sayfasında sol açılır dashboard alanına hover over edin.
        Actions actions = new Actions(Driver.getDriver());
        actions.moveToElement(dashboardVaccinationsPage.sideBarDashboardvaccinationsIcon)
                .perform();

        //Vaccinations butonunun görünür ve aktif olduğunu doğrulayın.

        softAssert.assertTrue(dashboardVaccinationsPage.dashboardVaccinationsTitle.isDisplayed());
        softAssert.assertTrue(dashboardVaccinationsPage.dashboardVaccinationsTitle.isEnabled());

        //Vaccinations butonunun tıklayın.
        dashboardVaccinationsPage.dashboardVaccinationsTitle.click();

        //Açılan sayfada Add Vaccinations butonunun görünür ve aktif olduğunu doğrulayın.

        dashboardVaccinationsPage.addVaccinationButton.isDisplayed();
        dashboardVaccinationsPage.addVaccinationButton.isEnabled();

        //Add vaccinations butonuna tıklayın.
        dashboardVaccinationsPage.addVaccinationButton.click();

        //Add vaccinations sayfasında olduğunuzu doğrulayın.
        String expectedTitle ="Vaccination Add";
        String actualTitle = dashboardVaccinationsPage.addVaccinationFormHeaderTitle.getText();

        softAssert.assertEquals(actualTitle,expectedTitle,
                "Aşı Ekle butonuna basıldığında yanlış sayfaya yönlendirme yapıldı." +
                        " Beklenen Sayfa: "+ expectedTitle + " Mevcut Sayfa:  "+ actualTitle);


        softAssert.assertAll();
    }

    @AfterClass
    public void tearDown () {
        Driver.quitDriver();
    }
}
