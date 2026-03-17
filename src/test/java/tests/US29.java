package tests;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.LcfAdminPage.DashboardDepartmentsPage;
import pages.LcfAdminPage.DashboardPage;
import pages.LcfHomePage.SignButonsPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.Listeners;
import utilities.ReusableMethods;
import java.util.Arrays;
import java.util.List;
@org.testng.annotations.Listeners(Listeners.class)
public class US29 {


    WebDriver driver;
    SoftAssert softAssert;
    SignButonsPage signButonsPage;
    DashboardDepartmentsPage dashboardDepartmentsPage;
    DashboardPage dashboardPage;

    @BeforeClass
    public void setUpClass (){

        signButonsPage = new SignButonsPage();
        dashboardDepartmentsPage = new DashboardDepartmentsPage();
        dashboardPage = new DashboardPage();

        Driver.getDriver().get(ConfigReader.getProperty("DasUrl"));
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

    }
    @Test (priority = 1)

    public void US29_TC01_DepartmantsVeAltMenuErisilebilirlikKontrolu (){


        //Admin sayfasında sol açılır dashboard alanına hover over edin.
        Actions actions = new Actions(Driver.getDriver());
        actions.moveToElement(dashboardDepartmentsPage.sideBarDashboardDepartmentsIcon)
                .perform();

        //Departments dropdown menüsünün görünür ve aktif olduğunu doğrulayın.
        softAssert.assertTrue(dashboardDepartmentsPage.dashboardDepartmentText
                .isDisplayed()
                ,"Departmants linki görüntülenemedi.");
        softAssert.assertTrue(dashboardDepartmentsPage.dashboardDepartmentText
                .isEnabled()
                ,"Departmants linki aktif değil.");
        softAssert.assertAll();


    }

    @Test (priority = 2)
    public void US29_02_DepartmantsSayfasininGoruntulenebilirlikKontrolu (){

        //Sol açılır menüden Departments menüsüne tıklayın.

        Actions actions = new Actions(Driver.getDriver());
        actions.moveToElement(dashboardDepartmentsPage.sideBarDashboardDepartmentsIcon)
                .perform();
        dashboardDepartmentsPage.dashboardDepartmentsSidebarHeader
                .click();

        //Departments alt menüsündeki departmants'a tıklayın
        dashboardDepartmentsPage.dashboardDepartmentsSidebarListLink
                .click();

        //Departments sayfasının açıldığını ve sayfa başlığında
        // “Departments” metninin görünür olduğunu doğrulayın.

        softAssert.assertTrue(dashboardDepartmentsPage.departmentsMainTitle
                .isDisplayed());

        //Sayfada departmanların listelendiğini doğrulayın.

        List<String> expectedDepartmentNames = Arrays.asList(
                "Wellness",
                "Dental Care",
                "Anaesthesia",
                "Dermatology",
                "Diagnostics");


        List<String> actualDepartmentsNames = ReusableMethods.stringListeDondur
                                (dashboardDepartmentsPage.departmentNamesList);


        softAssert.assertTrue(actualDepartmentsNames.containsAll(expectedDepartmentNames),
                "Mevcut liste beklenen tüm departmanları içermiyor");

        softAssert.assertAll();
    }

    @Test (priority = 3)
    public void US29_03_GecerliDepartmanAdiIleAramaFonksiyonuKontrolu (){

        dashboardDepartmentsPage.departmentsSearchBox
                .sendKeys("Wellness");

        //Arama sonucunda departmanın listelendiğini doğrulayın.
        softAssert.assertTrue(dashboardDepartmentsPage.departmantsDetailPageWellness.isDisplayed(),
                "Aranan departmant bulunamadı.");
        softAssert.assertAll();

    }

    @Test (priority = 4)
    public void US29_04_GecersizDepartmanAdiylaAramaFonksiyonuKontrolu (){

        //Search alanına tıklayarak geçerli bir departman adı girin.
        dashboardDepartmentsPage.departmentsSearchBox
                .clear();
        dashboardDepartmentsPage.departmentsSearchBox
                .sendKeys("Wellnesss");

        //Arama sonucunda departmanın listelenmediğini doğrulayın.
        softAssert.assertTrue(dashboardDepartmentsPage.noResultText.isDisplayed());
        softAssert.assertAll();

    }

    @Test (priority = 5)
    public void US29_05_SayfalamadanBagimsizAramaFonksiyonuKontrolu (){

        //Sayfa 1’de departmanın adını arayın
        dashboardDepartmentsPage.departmentsSearchBox
                .clear();
        dashboardDepartmentsPage.departmentsSearchBox
                .sendKeys("Wellness");

        //Departmanın listelendiğini doğrulayın
        softAssert.assertTrue(dashboardDepartmentsPage.departmantsDetailPageWellness.isDisplayed(),
                "Aranan departmant bulunamadı.");

        //Next butonu ile Sayfa 2’ye geçin
        dashboardDepartmentsPage.departmentsNextButton
                .click();

        //Sayfa 1’de aradığınız departmanın adını tekrar arayın
        dashboardDepartmentsPage.departmentsSearchBox
                .sendKeys("Wellness");

        //Departmanın Sayfa 2’de de listelendiğini doğrulayın
        softAssert.assertFalse(dashboardDepartmentsPage.noResultText.isDisplayed(),
                "Aranan departmant bulunamadı.");

        softAssert.assertAll();
    }
    @Test (priority = 6)
    public void US29_06_HerSatirdaDuzenlemeButonuDogrulamaTesti (){

        //Departmants detay sayfasına gidin

        Driver.getDriver().get(ConfigReader.getProperty("DasdepUrl"));

        //Departmants liste elemanlarının bulunduğu her satırda edit butonunun
        // görünür ve aktif olduğunu doğrulayın

        int expectedCount = dashboardDepartmentsPage.departmentNamesList.size();
        int actualEditCount = dashboardDepartmentsPage.departmentsEditButtonList.size();

        softAssert.assertEquals(actualEditCount, expectedCount, "Edit buton sayısı eksik");

        for (WebElement editButton : dashboardDepartmentsPage.departmentsEditButtonList) {
            softAssert.assertTrue(editButton.isDisplayed(),
                    "Edit butonu görüntülenemiyor.");
            softAssert.assertTrue(editButton.isEnabled(),
                    "Edit butonu etkin değil.");
        }
        softAssert.assertAll();
    }



    @Test (priority = 7)
    public void US29_07_HerSatirdaSilmeButonuDogrulamaTesti (){

        //Departmants liste elemanlarının bulunduğu her satırda edit butonunun
        // görünür ve aktif olduğunu doğrulayın

        int expectedCount = dashboardDepartmentsPage.departmentNamesList
                .size();
        int actualDeleteCount = dashboardDepartmentsPage.departmentsDeleteButtonList
                .size();

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
        public void tearDown () {
            Driver.quitDriver();
        }


    }


