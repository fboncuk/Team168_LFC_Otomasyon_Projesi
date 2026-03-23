package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LcfHomePage.HomeBodyPage;
import pages.LcfHomePage.VacinationsMainPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

import java.util.Arrays;
import java.util.List;

public class US05 {

    HomeBodyPage homeBodyPage;
    VacinationsMainPage vacinationsMainPage;
    SoftAssert softAssert;

    @BeforeClass
    public void setUp() {
        homeBodyPage = new HomeBodyPage();
        vacinationsMainPage = new VacinationsMainPage();
        softAssert = new SoftAssert();
        Driver.getDriver().get(ConfigReader.getProperty("LfcUrl"));
        ReusableMethods.bekle(2);
    }

    @Test(priority = 1)
    public void US05_TC01_DepartmentsBolumuVeResimlerGorunurlukTesti() {

        softAssert.assertTrue(homeBodyPage.bodyDepartmentsSectionTitle.isDisplayed(), "Departments başlığı görünmüyor!");

        List<WebElement> departmanlar = Arrays.asList(
                homeBodyPage.bodyDepartmentWellnessLink,
                homeBodyPage.bodyDepartmentDentalCareLink,
                homeBodyPage.bodyDepartmentAnaesthesiaLink,
                homeBodyPage.bodyDepartmentDermatologyLink,
                homeBodyPage.bodyDepartmentDiagnosticsLink,
                homeBodyPage.bodyDepartmentVaccinationsLink,
                homeBodyPage.bodyDepartmentPainControlLink,
                homeBodyPage.bodyDepartmentBoardingLink
        );

        for (WebElement dept : departmanlar) {
            softAssert.assertTrue(dept.isDisplayed(), dept.getText() + " bölüm ismi görünmüyor!");
        }

        List<WebElement> departmentGorseller = Driver.getDriver()
                .findElements(By.xpath("//a[contains(@href,'/Departments/')]//img"));
        softAssert.assertTrue(departmentGorseller.size() > 0, "Departments bölümünde resim bulunamadı!");
        for (WebElement gorsel : departmentGorseller) {
            softAssert.assertTrue(gorsel.isDisplayed(), "Bir department resmi görünmüyor!");
        }

        ReusableMethods.tarihliTumSayfaResimCek(Driver.getDriver());
        softAssert.assertAll();
    }

    @Test(priority = 2)
    public void US05_TC02_DepartmentLinkleriAktiflikVeUyumlulukTesti() {

        softAssert.assertTrue(homeBodyPage.bodyDepartmentsSectionLink.isDisplayed(), "Departments bölüm linki görünmüyor!");
        softAssert.assertTrue(homeBodyPage.bodyDepartmentsSectionLink.isEnabled(), "Departments bölüm linki aktif değil!");

        List<String> sluglar = Arrays.asList(
                "wellness", "dental-care", "anaesthesia",
                "dermatology", "diagnostics", "vaccinations",
                "pain-control", "boarding"
        );
        List<String> isimler = Arrays.asList(
                "Wellness", "Dental Care", "Anaesthesia",
                "Dermatology", "Diagnostics", "Vaccinations",
                "Pain Control", "Boarding"
        );

        for (int i = 0; i < sluglar.size(); i++) {
            List<WebElement> eslesmeler = Driver.getDriver()
                    .findElements(By.xpath("//a[contains(@href,'/Departments/" + sluglar.get(i) + "')]"));
            softAssert.assertTrue(eslesmeler.size() > 0, isimler.get(i) + " için link bulunamadı!");
            if (!eslesmeler.isEmpty()) {
                WebElement link = eslesmeler.get(0);
                softAssert.assertTrue(link.isEnabled(), isimler.get(i) + " linki tıklanabilir değil!");
                String href = link.getAttribute("href");
                softAssert.assertTrue(href != null && href.contains(sluglar.get(i)),
                        isimler.get(i) + " linkinin href değeri slug ile uyumsuz! Bulunan: " + href);
            }
        }

        softAssert.assertAll();
    }

    @Test(priority = 3)
    public void US05_TC03_PopularDoctorsBolumuGorunurlukVeLinkUyumlulukTesti() {

        softAssert.assertTrue(homeBodyPage.bodyDoctorsSectionTitle.isDisplayed(), "Popular Doctors başlığı görünmüyor!");
        softAssert.assertTrue(homeBodyPage.bodyDoctorsSectionLink.isDisplayed(), "Doctors linki görünmüyor!");
        softAssert.assertTrue(homeBodyPage.bodyDoctorsSectionLink.isEnabled(), "Doctors linki aktif değil!");

        String doctorsLinkHref = homeBodyPage.bodyDoctorsSectionLink.getAttribute("href");
        softAssert.assertTrue(doctorsLinkHref != null && doctorsLinkHref.toLowerCase().contains("doctors"),
                "Doctors linki 'doctors' içermiyor! Bulunan: " + doctorsLinkHref);

        List<WebElement> doktorlar = Arrays.asList(
                homeBodyPage.bodyDoctorAlejandroLink,
                homeBodyPage.bodyDoctorMarcusLink,
                homeBodyPage.bodyDoctorOliviaLink,
                homeBodyPage.bodyDoctorEmilyLink,
                homeBodyPage.bodyDoctorNathanLink,
                homeBodyPage.bodyDoctorIsabellaLink,
                homeBodyPage.bodyDoctorLiamLink,
                homeBodyPage.bodyDoctorSophiaLink
        );

        for (WebElement doktor : doktorlar) {
            softAssert.assertTrue(doktor.isDisplayed(), doktor.getText() + " doktor ismi görünmüyor!");
        }

        List<WebElement> doktorLinkler = Driver.getDriver()
                .findElements(By.xpath("//a[contains(@href,'/Doctors/')]//h3"));
        softAssert.assertTrue(doktorLinkler.size() > 0, "Doktor linkleri bulunamadı!");
        for (WebElement doktorLink : doktorLinkler) {
            WebElement anchor = doktorLink.findElement(By.xpath("ancestor::a[1]"));
            String href = anchor.getAttribute("href");
            softAssert.assertTrue(href != null && href.toLowerCase().contains("doctors"),
                    doktorLink.getText() + " doktorunun linki /Doctors/ içermiyor! href: " + href);
        }

        ReusableMethods.tarihliTumSayfaResimCek(Driver.getDriver());
        softAssert.assertAll();
    }

    @Test(priority = 4)
    public void US05_TC04_VaccinationsForPetsBolumuGorunurlukTesti() {

        softAssert.assertTrue(homeBodyPage.bodyVaccinationsSectionTitle.isDisplayed(), "Vaccinations for Pets başlığı görünmüyor!");
        softAssert.assertTrue(homeBodyPage.bodyVaccinationsSectionLink.isDisplayed(), "Vaccinations linki görünmüyor!");
        softAssert.assertTrue(homeBodyPage.bodyVaccinationsSectionLink.isEnabled(), "Vaccinations linki aktif değil!");

        ReusableMethods.tarihliTumSayfaResimCek(Driver.getDriver());
        softAssert.assertAll();
    }

    @Test(priority = 5)
    public void US05_TC05_VaccinationsLinkiSayfaUyumlulukTesti() {

        homeBodyPage.bodyVaccinationsSectionLink.click();
        ReusableMethods.bekle(2);

        String aktifUrl = Driver.getDriver().getCurrentUrl();
        softAssert.assertTrue(aktifUrl.toLowerCase().contains("Vaccination"),
                "Vaccinations linkine tıklandıktan sonra açılan URL beklenen 'vaccination' içermiyor! URL: " + aktifUrl);

        // Vaccinations linkine tıklandıktan sonra açılan URL beklenen 'vaccination' içermiyor! URL: https://qa.loyalfriendcare.com/en/Pets

        ReusableMethods.tarihliTumSayfaResimCek(Driver.getDriver());

        Driver.getDriver().navigate().back();
        ReusableMethods.bekle(2);

        softAssert.assertAll();
    }

    @Test(priority = 6)
    public void US05_TC06_VaccinationsAltGrubuGorunurlukTiklamaVeUyumlulukTesti() {

        homeBodyPage.bodyVaccinationsSectionLink.click();
        ReusableMethods.bekle(2);

        vacinationsMainPage = new VacinationsMainPage();

        List<WebElement> sidebarAsilar = Arrays.asList(
                vacinationsMainPage.vaccinesSidebarRabies,
                vacinationsMainPage.vaccinesSidebarDHPP,
                vacinationsMainPage.vaccinesSidebarFelineLeukemias,
                vacinationsMainPage.vaccinesSidebarFelineImmunodeficiency,
                vacinationsMainPage.vaccinesSidebarBordetella,
                vacinationsMainPage.vaccinesSidebarFelinePanleukopenia,
                vacinationsMainPage.vaccinesSidebarFelineHerpesvirus,
                vacinationsMainPage.vaccinesSidebarSurgicalProcedure,
                vacinationsMainPage.vaccinesSidebarFelineViral
        );

        for (WebElement asi : sidebarAsilar) {
            softAssert.assertTrue(asi.isDisplayed(), asi.getText() + " sidebar aşısı görünmüyor!");
        }

        String vaccinationsPageUrl = Driver.getDriver().getCurrentUrl();

        for (WebElement asi : sidebarAsilar) {
            String asiIsmi = asi.getText();
            softAssert.assertTrue(asi.isEnabled(), asiIsmi + " tıklanabilir değil!");
            asi.click();
            ReusableMethods.bekle(1);
            String aktifUrl = Driver.getDriver().getCurrentUrl();
            softAssert.assertTrue(aktifUrl.toLowerCase().contains("vaccination"),
                    asiIsmi + " tıklandıktan sonra açılan URL beklenen '"+asiIsmi+"' içermiyor! URL: " + aktifUrl);
            Driver.getDriver().navigate().to(vaccinationsPageUrl);
            ReusableMethods.bekle(1);
            vacinationsMainPage = new VacinationsMainPage();
        }
        //Aşı isimleri istenilen URL değerini vermiyor. URLlerin düzenlenmesi gerekmekte.


        ReusableMethods.tarihliTumSayfaResimCek(Driver.getDriver());
        softAssert.assertAll();
    }

    @AfterClass
    public void tearDown() {
        Driver.quitDriver();
    }
}
