package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LcfAdminPage.DashboardDepartmentsPage;
import pages.LcfHomePage.SignButonsPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

import java.util.List;

public class US30 {

    SoftAssert softAssert;
    SignButonsPage signButonsPage;
    DashboardDepartmentsPage dashboardDepartmentsPage;

    static final String CREATE_URL = "https://qa.loyalfriendcare.com/en/Dashboard/Categories/create";
    static final String yeniDepartmanAdi = "TestDept_US30";

    @BeforeClass
    public void setUpClass() {
        signButonsPage = new SignButonsPage();
        dashboardDepartmentsPage = new DashboardDepartmentsPage();

        Driver.getDriver().get(ConfigReader.getProperty("DasUrl"));
        signButonsPage.emailKutusu.sendKeys(ConfigReader.getProperty("T03AdminMail"));
        signButonsPage.passwordKutusu.sendKeys(ConfigReader.getProperty("T03AdminPassword"));
        signButonsPage.signInButtonOnay.click();
        ReusableMethods.bekle(2);
    }

    @BeforeMethod
    public void setUp() {
        softAssert = new SoftAssert();
    }

    @Test(priority = 1)
    public void US30_TC01_YalnizYoneticiDepartmanOlusturabilir() {

        // Oturumu kapat (cookie sil) ve Create URL'ine anonim olarak git
        Driver.getDriver().manage().deleteAllCookies();
        Driver.getDriver().get(CREATE_URL);
        ReusableMethods.bekle(2);

        String currentUrl = Driver.getDriver().getCurrentUrl();

        // Yönetici olmayan / oturum açmamış kullanıcı yönlendirilmeli
        softAssert.assertFalse(currentUrl.contains("Categories/create"),
                "HATA: Oturum açmamış kullanıcı departman oluşturma sayfasına erişebildi! URL: " + currentUrl);

        ReusableMethods.tarihliTumSayfaResimCek(Driver.getDriver());

        // Sonraki testler için admin olarak tekrar giriş yap
        Driver.getDriver().get(ConfigReader.getProperty("DasUrl"));
        ReusableMethods.bekle(1);
        signButonsPage = new SignButonsPage();
        signButonsPage.emailKutusu.sendKeys(ConfigReader.getProperty("T03AdminMail"));
        signButonsPage.passwordKutusu.sendKeys(ConfigReader.getProperty("T03AdminPassword"));
        signButonsPage.signInButtonOnay.click();
        ReusableMethods.bekle(2);

        softAssert.assertAll();
    }

    @Test(priority = 2)
    public void US30_TC02_AdminCreateDepartmentFormunuDoldurabilir() {

        // Create Department sayfasına git
        Driver.getDriver().get(CREATE_URL);
        ReusableMethods.bekle(2);

        dashboardDepartmentsPage = new DashboardDepartmentsPage();

        // Parent Department alanını doldur
        dashboardDepartmentsPage.ParentDepartmentsSpinButton.clear();
        dashboardDepartmentsPage.ParentDepartmentsSpinButton.sendKeys("111");

        // Order Department alanını doldur
        dashboardDepartmentsPage.OrderDepartmentsSpinButton.clear();
        dashboardDepartmentsPage.OrderDepartmentsSpinButton.sendKeys("12");

        // Title Department alanını doldur
        dashboardDepartmentsPage.TitleDepartmentsTextBox.clear();
        dashboardDepartmentsPage.TitleDepartmentsTextBox.sendKeys(yeniDepartmanAdi);

        // Form alanlarının dolu olduğunu doğrula
        String titleDegeri = dashboardDepartmentsPage.TitleDepartmentsTextBox.getAttribute("value");
        softAssert.assertEquals(titleDegeri, yeniDepartmanAdi,
                "Title alanı beklenen değeri içermiyor!");

        // Save butonuna tıkla → otomatik olarak Categories listesine yönlendirir
        dashboardDepartmentsPage.SaveButton.click();
        ReusableMethods.bekle(2);

        ReusableMethods.tarihliTumSayfaResimCek(Driver.getDriver());
        softAssert.assertAll();
    }

    @Test(priority = 3)
    public void US30_TC03_DepartmanKaydedilipListedeGoruntulenebilir() {

        // Save sonrası otomatik yönlendirilen URL'nin Categories olduğunu doğrula
        String currentUrl = Driver.getDriver().getCurrentUrl();
        softAssert.assertTrue(currentUrl.equals("https://qa.loyalfriendcare.com/en/Dashboard/Categories"),
                "Save sonrası Departments listesine yönlendirilemedi! URL: " + currentUrl);

        // Next butonuna tıklayarak 2. sayfaya geç (yeni departman 2. sayfada yer alıyor)
        dashboardDepartmentsPage = new DashboardDepartmentsPage();
        dashboardDepartmentsPage.departmentsNextButton.click();
        ReusableMethods.bekle(2);

        // 2. sayfada olduğumuzu doğrula
        String page2Url = Driver.getDriver().getCurrentUrl();
        softAssert.assertTrue(page2Url.contains("page=2"),
                "2. sayfaya geçilemedi! URL: " + page2Url);

        // 2. sayfada oluşturulan 12 order numaralı departmanı ara
        dashboardDepartmentsPage = new DashboardDepartmentsPage();
        dashboardDepartmentsPage.departmentsSearchBox.sendKeys(yeniDepartmanAdi);
        ReusableMethods.bekle(1);

        List<WebElement> sonuclar = Driver.getDriver()
                .findElements(By.xpath("//table[@id='tableWithSearch']//p[contains(text(),'" + yeniDepartmanAdi + "')]"));

        softAssert.assertTrue(sonuclar.size() > 0,
                "Oluşturulan departman 2. sayfada görüntülenemedi: " + yeniDepartmanAdi);

        ReusableMethods.tarihliTumSayfaResimCek(Driver.getDriver());
        softAssert.assertAll();
    }

    @AfterClass
    public void tearDown() {
        Driver.quitDriver();
    }
}
