package tests;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.LcfAdminPage.DashboardDepartmentsPage;
import pages.LcfHomePage.SignButonsPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;
import java.util.Arrays;
import java.util.List;

@Listeners(utilities.Listeners.class)

public class US29 {

    SoftAssert softAssert;
    SignButonsPage signButonsPage;
    DashboardDepartmentsPage dashboardDepartmentsPage;

    @BeforeClass
    public void setUpClass() {
        signButonsPage = new SignButonsPage();
        dashboardDepartmentsPage = new DashboardDepartmentsPage();
        Driver.getDriver()
                .get(ConfigReader.getProperty("DasUrl"));
        signButonsPage.emailKutusu
                .sendKeys(ConfigReader.getProperty("T07AdminMail"));
        signButonsPage.passwordKutusu
                .sendKeys(ConfigReader.getProperty("T07AdminPassword"));
        signButonsPage.signInButtonOnay
                .click();
    }

    @BeforeMethod
    public void setUp() {
        softAssert = new SoftAssert();
        Driver.getDriver().get(ConfigReader.getProperty("DasdepUrl"));
    }

    @Test
    public void US29_TC01_DepartmantsVeAltMenuErisilebilirlikKontrolu() {

        Actions actions = new Actions(Driver.getDriver());

        //Admin sayfasında sol açılır dashboard alanına hover over edin.
        actions.moveToElement(dashboardDepartmentsPage.sideBarDashboardDepartmentsIcon)
                .perform();

        //Departments dropdown menüsünün görünür ve aktif olduğunu doğrulayın.
        softAssert.assertTrue(dashboardDepartmentsPage.dashboardDepartmentText
                        .isDisplayed(),
                "Departmants linki görüntülenemedi.");
        softAssert.assertTrue(dashboardDepartmentsPage.dashboardDepartmentText
                        .isEnabled(),
                "Departmants linki aktif değil.");
        softAssert.assertAll();

        //Departmants alt menülerine erişilebildiğini doğrulayın.
        dashboardDepartmentsPage.dashboardDepartmentText.click();

        softAssert.assertTrue(dashboardDepartmentsPage.DepartmentsButon
                .isEnabled());
        softAssert.assertTrue(dashboardDepartmentsPage.sidebarCreateDepartments
                .isEnabled());
        softAssert.assertAll();
    }

    @Test
    public void US29_TC02_DepartmantsSayfasininGoruntulenebilirlikKontrolu() {

        //Depatrments sayfasında olduğunuz doğrulayın.
        softAssert.assertTrue(dashboardDepartmentsPage.departmentsMainTitle.isDisplayed(),
                "Başlık görünmüyor.");
        //Sayfada departmanların listelendiğini doğrulayın.
        List<String> expectedDepartmentNames = Arrays.asList(
                "Wellness",
                "Dental Care",
                "Anaesthesia",
                "Dermatology",
                "Diagnostics"
        );
        List<String> actualDepartmentsNames = ReusableMethods.stringListeDondur(dashboardDepartmentsPage.departmentNamesList);

        softAssert.assertTrue(actualDepartmentsNames.containsAll(expectedDepartmentNames),
                "Mevcut liste beklenen tüm departmanları içermiyor");
        softAssert.assertAll();
    }

    @Test
    public void US29_T03_GecerliDepartmanAdiIleAramaFonksiyonuKontrolu() {

        //Geçerli bir departman adıyla arama yapın
        dashboardDepartmentsPage.departmentsSearchBox
                .sendKeys("Wellness");
        //Arama sonucu departmanın listelendiğini doğrulayın
        softAssert.assertTrue(dashboardDepartmentsPage.departmantsDetailPageWellness.isDisplayed(),
                "Aranan departmant bulunamadı.");
        softAssert.assertAll();
    }

    @Test
    public void US29_04_GecersizDepartmanAdiylaAramaFonksiyonuKontrolu() {

        //Geçersiz bir departman adıyla arama yapın
        dashboardDepartmentsPage.departmentsSearchBox
                .sendKeys("Wellnesss");
        //Arama sonucu departmanın listelenmediğini doğrulayın
        softAssert.assertTrue(dashboardDepartmentsPage.noResultText
                .isDisplayed(), "Geçersiz aramada sonuç bulundu!");
        softAssert.assertAll();
    }

    @Test
    public void US29_05_SayfalamadanBagimsizAramaFonksiyonuKontrolu() {

        //Sayfa 1’de departmanın adını arayın
        dashboardDepartmentsPage.departmentsSearchBox
                .sendKeys("Wellness");
        //Departmanın listelendiğini doğrulayın
        softAssert.assertTrue(dashboardDepartmentsPage.departmantsDetailPageWellness
                .isDisplayed());

        //Next butonu ile Sayfa 2’ye geçin
        dashboardDepartmentsPage.departmentsNextButton
                .click();

//Search alanını temizleyerek Sayfa 1’de aradığınız departmanın adını tekrar arayın
        dashboardDepartmentsPage.departmentsSearchBox
                .clear();
        dashboardDepartmentsPage.departmentsSearchBox
                .sendKeys("Wellness");
        //Departmanın Sayfa 2’de de listelendiğini doğrulayın
        softAssert.assertFalse(dashboardDepartmentsPage.noResultText
                .isDisplayed(),
                "Sayfa 2'de aranan departmant bulunamadı.");
        softAssert.assertAll();
    }

    @Test
    public void US29_TC06_HerSatirdaDuzenlemeButonuDogrulamaTesti() {

        //Departmants liste elemanlarının bulunduğu her satırda edit butonunun
        // görünür ve aktif olduğunu doğrulayın

        int expectedCount = dashboardDepartmentsPage.departmentNamesList.size();
        int actualEditCount = dashboardDepartmentsPage.departmentsEditButtonList.size();

        softAssert.assertEquals(actualEditCount, expectedCount,
                "Edit buton sayısı eksik");
        for (WebElement editButton : dashboardDepartmentsPage.departmentsEditButtonList) {
            softAssert.assertTrue(editButton.isDisplayed(),
                    "Edit butonu görüntülenemiyor.");
            softAssert.assertTrue(editButton.isEnabled(),
                    "Edit butonu etkin değil.");
        }
        softAssert.assertAll();
    }

    @Test
    public void US29_TC07_HerSatirdaSilmeButonuDogrulamaTesti() {

        //Departmants liste elemanlarının bulunduğu her satırda delete butonunun
        // görünür ve aktif olduğunu doğrulayın
        int expectedCount = dashboardDepartmentsPage.departmentNamesList.size();
        int actualDeleteCount = dashboardDepartmentsPage.departmentsDeleteButtonList.size();

        softAssert.assertEquals(actualDeleteCount, expectedCount,
                "Delete buton sayısı eksik");
        for (WebElement deleteButton : dashboardDepartmentsPage.departmentsDeleteButtonList) {
            softAssert.assertTrue(deleteButton.isDisplayed(),
                    "Delete butonu görüntülenemiyor.");
            softAssert.assertTrue(deleteButton.isEnabled(),
                    "Delete butonu etkin değil.");
        }
        softAssert.assertAll();
    }

    @AfterClass
    public void tearDown() {
        Driver.quitDriver();
    }
}
