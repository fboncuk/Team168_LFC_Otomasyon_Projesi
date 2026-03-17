package tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LcfHomePage.HomeBodyPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

public class US01 {

    HomeBodyPage homeBodyPage;

    @BeforeMethod
    public void setUp() {
        homeBodyPage = new HomeBodyPage();
        Driver.getDriver().get(ConfigReader.getProperty("LcfUrl"));
    }

    @Test(priority = 1, groups = "smoke", description = "SMOKE: Ana sayfa yüklendiğinde sekme isminin kontrolü")
    public void US01_TC01_AnaSayfaTitleTesti() {

        SoftAssert softAssert = new SoftAssert();

        ReusableMethods.bekle(2);

        String expectedTitle = "LoyalFriendCare";
        String actualTitle = Driver.getDriver().getTitle();

        softAssert.assertEquals(actualTitle, expectedTitle, "HATA: Sayfa başlığı beklenenden farklı!");

        ReusableMethods.tumSayfaResimCek(Driver.getDriver(), "US01_TC01_AnaSayfa_Title_Basarili");

        softAssert.assertAll();
    }

    @Test(priority = 2, groups = "smoke", description = "SMOKE: Site adresinin HTTPS protokolü ile açıldığının kontrolü")
    public void US01_TC02_HttpsProtokolTesti() {

        SoftAssert softAssert = new SoftAssert();

        ReusableMethods.bekle(2);

        String actualUrl = Driver.getDriver().getCurrentUrl();

        softAssert.assertTrue(actualUrl.startsWith("https"), "HATA: Site güvenli bağlantı (https) içermiyor!");

        ReusableMethods.tumSayfaResimCek(Driver.getDriver(), "US01_TC02_Https_Protokol_Basarili");

        softAssert.assertAll();
    }

    @Test(priority = 3, groups = "regression", description = "REGRESSION: Ana sayfa Body bölümündeki tüm elementlerin görünürlük kontrolü")
    public void US01_TC03_BodyEksiksizGorunurlukTesti() {

        SoftAssert softAssert = new SoftAssert();

        ReusableMethods.bekle(2);

        softAssert.assertTrue(homeBodyPage.bodyMainSearchBox.isDisplayed(), "Arama kutusu görünmüyor!");
        softAssert.assertTrue(homeBodyPage.bodyMainSearchButton.isDisplayed(), "Arama butonu görünmüyor!");
        softAssert.assertTrue(homeBodyPage.bodyScrollToTopButton.isDisplayed(), "Yukarı çık butonu görünmüyor!");

        softAssert.assertTrue(homeBodyPage.bodyDepartmentsSectionTitle.isDisplayed(), "Departments başlığı yok!");
        softAssert.assertTrue(homeBodyPage.bodyDepartmentsSectionLink.isDisplayed(), "Departments linki yok!");
        softAssert.assertTrue(homeBodyPage.bodyDepartmentWellnessLink.isDisplayed(), "Wellness linki yok!");
        softAssert.assertTrue(homeBodyPage.bodyDepartmentDentalCareLink.isDisplayed(), "Dental Care linki yok!");
        softAssert.assertTrue(homeBodyPage.bodyDepartmentAnaesthesiaLink.isDisplayed(), "Anaesthesia linki yok!");
        softAssert.assertTrue(homeBodyPage.bodyDepartmentDermatologyLink.isDisplayed(), "Dermatology linki yok!");
        softAssert.assertTrue(homeBodyPage.bodyDepartmentDiagnosticsLink.isDisplayed(), "Diagnostics linki yok!");
        softAssert.assertTrue(homeBodyPage.bodyDepartmentVaccinationsLink.isDisplayed(), "Vaccinations linki yok!");
        softAssert.assertTrue(homeBodyPage.bodyDepartmentPainControlLink.isDisplayed(), "Pain Control linki yok!");
        softAssert.assertTrue(homeBodyPage.bodyDepartmentBoardingLink.isDisplayed(), "Boarding linki yok!");

        softAssert.assertTrue(homeBodyPage.bodyDoctorsSectionTitle.isDisplayed(), "Popular Doctors başlığı yok!");
        softAssert.assertTrue(homeBodyPage.bodyDoctorsSectionLink.isDisplayed(), "Doctors linki yok!");
        softAssert.assertTrue(homeBodyPage.bodyDoctorAlejandroLink.isDisplayed(), "Dr. Alejandro görünmüyor!");
        softAssert.assertTrue(homeBodyPage.bodyDoctorMarcusLink.isDisplayed(), "Dr. Marcus görünmüyor!");
        softAssert.assertTrue(homeBodyPage.bodyDoctorOliviaLink.isDisplayed(), "Dr. Olivia görünmüyor!");
        softAssert.assertTrue(homeBodyPage.bodyDoctorEmilyLink.isDisplayed(), "Dr. Emily görünmüyor!");
        softAssert.assertTrue(homeBodyPage.bodyDoctorNathanLink.isDisplayed(), "Dr. Nathan görünmüyor!");
        softAssert.assertTrue(homeBodyPage.bodyDoctorIsabellaLink.isDisplayed(), "Dr. Isabella görünmüyor!");
        softAssert.assertTrue(homeBodyPage.bodyDoctorLiamLink.isDisplayed(), "Dr. Liam görünmüyor!");
        softAssert.assertTrue(homeBodyPage.bodyDoctorSophiaLink.isDisplayed(), "Dr. Sophia görünmüyor!");

        softAssert.assertTrue(homeBodyPage.bodyVaccinationsSectionTitle.isDisplayed(), "Vaccinations başlığı yok!");
        softAssert.assertTrue(homeBodyPage.bodyVaccinationsSectionLink.isDisplayed(), "Vaccinations linki yok!");
        softAssert.assertTrue(homeBodyPage.bodyVaccinationRabiesLink.isDisplayed(), "Rabies aşısı görünmüyor!");
        softAssert.assertTrue(homeBodyPage.bodyVaccinationDHPPLink.isDisplayed(), "DHPP aşısı görünmüyor!");
        softAssert.assertTrue(homeBodyPage.bodyVaccinationLeukemiaLink.isDisplayed(), "Feline Leukemia aşısı görünmüyor!");
        softAssert.assertTrue(homeBodyPage.bodyVaccinationFIVLink.isDisplayed(), "FIV aşısı görünmüyor!");
        softAssert.assertTrue(homeBodyPage.bodyVaccinationBordetellaLink.isDisplayed(), "Bordetella aşısı görünmüyor!");
        softAssert.assertTrue(homeBodyPage.bodyVaccinationPanleukopeniaLink.isDisplayed(), "Panleukopenia aşısı görünmüyor!");
        softAssert.assertTrue(homeBodyPage.bodyVaccinationHerpesvirusLink.isDisplayed(), "Herpesvirus aşısı görünmüyor!");
        softAssert.assertTrue(homeBodyPage.bodyVaccinationSurgicalLink.isDisplayed(), "Surgical Procedure görünmüyor!");

        ReusableMethods.tumSayfaResimCek(Driver.getDriver(), "US01_TC03_Body_Elementleri_Basarili");

        softAssert.assertAll();
    }

    @AfterMethod
    public void teardown() {
        Driver.quitDriver();
    }
}