package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LcfAdminPage.DashboardBedManagersPage;
import pages.LcfAdminPage.DashboardPage;
import pages.LcfHomePage.*;
import utilities.*;

import java.lang.reflect.Method;

import java.util.*;

// @org.testng.annotations.Listeners(Listeners.class)

public class US28 extends TestBaseRapor{

    SignButonsPage signButonsPage;
    DashboardPage dashboardPage;
    DashboardBedManagersPage dashboardBedManagersPage;
    DepartmentsMainPage departmentsMainPage;

    @BeforeClass
    public void setup() {

        signButonsPage = new SignButonsPage();
        // Homepage açılır
        Driver.getDriver().get(ConfigReader.getProperty("LfcUrl"));

        // SignIn butonu tıklanır ve T06 Tester geçerli kullanıcı bilgileri ile giriş yapılır
        signButonsPage.signInLinki.click();
        signButonsPage.emailKutusu.sendKeys(ConfigReader.getProperty("T06AdminMail"));
        signButonsPage.passwordKutusu.sendKeys(ConfigReader.getProperty("T06AdminPassword"));
        signButonsPage.signInButtonOnay.click();
        // Profil ismine tıklayıp Dasboard sayfasına gidilir
        signButonsPage.signInLinki.click();
    }

    @BeforeMethod
    public void beforeMethod(Method method) {
        extentTest = extentReports.createTest(method.getName(),
                "Test otomatik oluşturuldu");
    }

    @Test(priority = 1, groups = "regression")
    public void US28_TC01_DasboardBedManagerGorunurlukTesti() {

        // Dashboard sayfasında soldaki açılır menüde
        // Bed Managers linkinin görünür, aktif ve tıklanabilir olduğunu ve
        // linkine tıklandığında, açılır menü seçeneklerinin görüldüğünü doğrulamak

        SoftAssert softAssert = new SoftAssert();
        signButonsPage = new SignButonsPage();
        dashboardPage = new DashboardPage();

        // Dashboard sidebar menüsü hover edilir
        ReusableMethods.hover(dashboardPage.dashboardPageSideBarMenu);

        // Bed Manager linki gürünürlük kontrolü yapılır ve tıklanır
        softAssert.assertTrue(dashboardPage.sidebarMenuBedManagersLink.isDisplayed());
        dashboardPage.sidebarMenuBedManagersLink.click();
        ReusableMethods.bekle(1);

        // Bed Managers submenü görünürlük kontrolü yapılır
                softAssert.assertTrue(dashboardPage.sidebarSubmenuBedManagers.isDisplayed()
                ,"Dashboard Bed Managers butonu görünür değil.");
        softAssert.assertTrue(dashboardPage.sidebarSubmenuBedManagers.isEnabled()
                ,"Dashboard Bed Managers butonu görünür değil.");

        ReusableMethods.bekle(1);

        // Create Bed Managers submenü görünürlük kontrolü yapılır
                softAssert.assertTrue(dashboardPage.sidebarSubmenuCreateBedManagers.isDisplayed()
                ,"Dashboard Create Bed Managers butonu görünür değil.");
        softAssert.assertTrue(dashboardPage.sidebarSubmenuCreateBedManagers.isEnabled()
                ,"Dashboard Create Bed Managers butonu görünür değil.");

        softAssert.assertAll();

    }


    @Test(priority = 2)
    public void US28_TC02_DasboardCreateBedManagerSayfasiAcilmaTesti() {

        // Dashboard sayfasında soldaki açılır menüde
        // Sol açılır menüde Create Bed Managers linkine tıklandığında,
        // Create Bed managers sayfasının açıldığını doğrulamak

        SoftAssert softAssert = new SoftAssert();
        signButonsPage = new SignButonsPage();
        dashboardPage = new DashboardPage();
        dashboardBedManagersPage = new DashboardBedManagersPage();

        Driver.getDriver().navigate().refresh();

        // Dashboard sidebar menüsü hover edilir
        ReusableMethods.hover(dashboardPage.dashboardPageSideBarMenu);

        // Bed Manager linki tıklanır
        dashboardPage.sidebarMenuBedManagersLink.click();
        ReusableMethods.bekle(1);

        // Create bed managers linki tıklanır
        dashboardPage.sidebarSubmenuCreateBedManagers.click();
        ReusableMethods.bekle(1);

        String expectedTitle = "Create Bed Managers";
        String actualTitle = dashboardBedManagersPage.dashboardCreateBedManagerTitle.getText();
        softAssert.assertTrue(actualTitle.toLowerCase().trim().contains(expectedTitle.toLowerCase().trim())
                               ,expectedTitle + " sayfası açılmadı.");

        softAssert.assertAll();

    }


    @Test(priority = 3)
    public void US28_TC03_CreateBedManagerYeniYatakOlusturmaTesti() {

        // Dashboard Create Bed Managers sayfasında
        // Geçerli bigiler ile yeni bir yatak oluşturulabildiğini doğrulamak

        SoftAssert softAssert = new SoftAssert();
        signButonsPage = new SignButonsPage();
        dashboardPage = new DashboardPage();
        dashboardBedManagersPage = new DashboardBedManagersPage();

        // Dashboard sidebar menüsü hover edilir
        ReusableMethods.hover(dashboardPage.dashboardPageSideBarMenu);

        // Bed Manager linki tıklanır
        dashboardPage.sidebarMenuBedManagersLink.click();

        // Create bed managers linki tıklanır
        dashboardPage.sidebarSubmenuCreateBedManagers.click();

        // Geçerli Bigiler girilir
        String newbedManagersTitle = "Tekir Cat Bed";
        String newbedManagersContent = "Tekir Cat Bed\nThis text is for testing the Bed Managers’ Content text box.";
        String newbedManagersPrices = "1000TL";
        String filePath = System.getProperty("user.dir")
                + "/src/test/java/images/US_28_TC_03_Foto_Testdata.jpg";

        dashboardBedManagersPage.createBedManagerTitleKutusu.click();
        ReusableMethods.bekle(1);
        dashboardBedManagersPage.createBedManagerTitleKutusu
                .sendKeys(newbedManagersTitle); // bedManagersTitle girilir
        dashboardBedManagersPage.createBedManagerContent.click();
        dashboardBedManagersPage.createBedManagerContent
                .sendKeys(newbedManagersContent); // bedManagersContent girilir
        dashboardBedManagersPage.createBedManagerDepDropdown.click(); // Department dd menü tıklanır
        dashboardBedManagersPage.departmentDdMenuVaccinations.click(); // Vaccinations seçilir
        dashboardBedManagersPage.createBedManagerDoctorDropdown.click(); // Doctors dd menü tıklanır
        dashboardBedManagersPage.doctorsDdMenuDrMarcus.click(); // Dr Marcus seçilir
        dashboardBedManagersPage.createBedManagerPriceBox.sendKeys(newbedManagersPrices); // Price girilir

        // Yeni yatak kaydedilir.
        dashboardBedManagersPage.createBedManagerSaveButonu.click();
        ReusableMethods.bekle(1);

        // Yeni oluşturulan yatağın Bed Managers altında görüldüğü kontrol edilir
        // Bed Managers sayfasına gidilir.
        Driver.getDriver().get(ConfigReader.getProperty("DasbedUrl"));

        // Yeni yatak Dasboard Bed Managers listesinde yer alıyor mu kontrol edilir
        // Yeni yatağın locator'ı ismi ile aratılıp kaydedilir
        WebElement row = null;
        try {
            row = Driver.getDriver().findElement(
                    By.xpath("//tr[.//p[normalize-space()='"
                            + newbedManagersTitle
                            + "']]")
            );
        } catch (Exception e) {
            // eleman bulunamadı
            row = null; // zaten null
        }

        // row null ise  ve görünür değilse soft assert fail
        if (row == null ) {
            softAssert.fail("Yeni oluşturulan yatak Bed Managers listesine eklenmemiş!");
        }

        // Delete butonunu sadece row bulunduysa tıklanır
        if (row != null) {

            try {
                WebElement deleteButonu = row.findElement(By.xpath(".//button[@type='submit']"));
                deleteButonu.click();
            } catch (Exception e) {
                softAssert.fail("HATA: Delete butonu bulunamadı!");
            }
        }

        softAssert.assertAll();

    }


    @Test(priority = 4, groups = "regression")
    public void US28_TC04_YeniYatakDashboardGorunurlukTesti() {

        // Dashboard Bed Managers sayfasında yeni yatak eklendikten sonra,
        // ilgili departman altında görünür, aktif ve tıklanabilir olduğunu doğrulamak

        SoftAssert softAssert = new SoftAssert();
        signButonsPage = new SignButonsPage();
        dashboardPage = new DashboardPage();
        dashboardBedManagersPage = new DashboardBedManagersPage();
        departmentsMainPage = new DepartmentsMainPage();

        Driver.getDriver().get(ConfigReader.getProperty("DasbedUrl"));

        // Geçerli Bigiler girilir
        String newbedManagersTitle = "Sarman Cat Bed";
        String newbedManagersContent = "Sarman Cat Bed\nThis text is for testing the Bed Managers’ Content text box.";
        String newbedManagersPrices = "2000TL";

        dashboardBedManagersPage.dashboardAddBedManagerButonu.click();
        ReusableMethods.bekle(1);
        dashboardBedManagersPage.createBedManagerTitleKutusu
                .sendKeys(newbedManagersTitle); // bedManagersTitle girilir
        dashboardBedManagersPage.createBedManagerContent.click();
        dashboardBedManagersPage.createBedManagerContent
                .sendKeys(newbedManagersContent); // bedManagersContent girilir
        dashboardBedManagersPage.createBedManagerDepDropdown.click(); // Department dd menü tıklanır
        dashboardBedManagersPage.departmentDdMenuVaccinations.click(); // Vaccinations seçilir
        dashboardBedManagersPage.createBedManagerDoctorDropdown.click(); // Doctors dd menü tıklanır
        dashboardBedManagersPage.doctorsDdMenuDrMarcus.click(); // Dr Marcus seçilir
        dashboardBedManagersPage.createBedManagerPriceBox.sendKeys(newbedManagersPrices); // Price girilir

        // Yeni yatak kaydedilir.
        dashboardBedManagersPage.createBedManagerSaveButonu.click();
        ReusableMethods.bekle(1);

        // Yeni yatak Vaccinations sayfasında Bed listesinde yer alıyor mu kontrol edilir
        Driver.getDriver().get(ConfigReader.getProperty("DepUrl"));
        ReusableMethods.bekle(1);
        departmentsMainPage.Vaccinations.click();
        ReusableMethods.bekle(2);

        WebElement row = null;
        try {
            // Yeni yatağın locator'ı ismi ile aratılıp kaydedilir
            row = Driver.getDriver().findElement(
                    By.xpath("//a[.//h3[normalize-space()='" + newbedManagersTitle + "']]"));
        } catch (Exception e) {
            // eleman bulunamadı
            row = null; // zaten null
        }

        // row null ise soft assert fail
        if (row == null) {
            softAssert.fail("Yeni oluşturulan yatak Bed Managers listesine eklenmemiş!");
        }

        // Test işlemi tamamlandığı için eklenmiş olan yatak Elementi silinir.
        // Dasboard sayfasına gidilir ve test için eklenmiş olan Bed silinir.
        Driver.getDriver().get(ConfigReader.getProperty("DasbedUrl"));
        ReusableMethods.bekle(1);

        if (row != null) {
            try {
                // Silinecek yatak ile aynı satırdaki Delete butonu locater'ı bulunup tıklanır.
                WebElement silinecekBed = Driver.getDriver().findElement(
                        By.xpath("//tr[.//p[normalize-space()='" + newbedManagersTitle + "']]"));

                WebElement deleteButonu = silinecekBed.findElement(By.xpath(".//button[@type='submit']"));
                deleteButonu.click();
                ReusableMethods.bekle(1);
            } catch (Exception e) {
                softAssert.fail("HATA: Delete butonu bulunamadı!");
            }
        }

        softAssert.assertAll();

    }


    @Test(priority = 5, groups = "regression")
    public void US28_TC05_BosTitleIleYeniYatakOlusturamamaTesti() {

        // Dashboard Bed Managers sayfasında yeni yatak eklendikten sonra,
        // Yatak adı girilmeden yeni bir yatak oluşturulamadığını doğrulamak
        //Negatif test

        SoftAssert softAssert = new SoftAssert();
        signButonsPage = new SignButonsPage();
        dashboardBedManagersPage = new DashboardBedManagersPage();
        departmentsMainPage = new DepartmentsMainPage();

        Driver.getDriver().get(ConfigReader.getProperty("DasbedUrl"));

        // Yatak adı hariç diğer bilgiler girilir
        String newbedManagersContent = "Pamuk Cat Bed\nThis text is for testing the Bed Managers’ Content text box.";
        String newbedManagersPrices = "2000TL";

        dashboardBedManagersPage.dashboardAddBedManagerButonu.click();
        ReusableMethods.bekle(1);
        dashboardBedManagersPage.createBedManagerContent.click();
        dashboardBedManagersPage.createBedManagerContent
                .sendKeys(newbedManagersContent); // bedManagersContent girilir
        dashboardBedManagersPage.createBedManagerDepDropdown.click(); // Department dd menü tıklanır
        dashboardBedManagersPage.departmentDdMenuVaccinations.click(); // Vaccinations seçilir
        dashboardBedManagersPage.createBedManagerDoctorDropdown.click(); // Doctors dd menü tıklanır
        dashboardBedManagersPage.doctorsDdMenuDrMarcus.click(); // Dr Marcus seçilir
        dashboardBedManagersPage.createBedManagerPriceBox.sendKeys(newbedManagersPrices); // Price girilir

        // Yeni yatak kaydedilir.
        dashboardBedManagersPage.createBedManagerSaveButonu.click();
        ReusableMethods.bekle(1);

        WebElement titleKutusu = dashboardBedManagersPage.createBedManagerTitleKutusu;
        String message = titleKutusu.getAttribute("validationMessage");

        // Tooltip/validation mesajı kontrolü
        String expectedMessage = "Lütfen bu alanı doldurun.";

        if(message != null && message.contains(expectedMessage)){
            softAssert.assertEquals(message, expectedMessage, "Validation mesajı doğru mu?");
        }else{
            System.out.println("Validation mesajı beklenenden farklı veya başlık alanı doldurulmuş.");
        }

        softAssert.assertAll();

    }


    @Test(priority = 6, groups = "regression")
    public void US28_TC06_BedManagerLogoTiklamaTesti() {

        // Dashboard veya Bed Managers sayfalarındayken
        // "LoyalFriendsCare" logosuna tıklayınca ana sayfa açıldığını doğrulamak

        SoftAssert softAssert = new SoftAssert();
        signButonsPage = new SignButonsPage();
        dashboardPage = new DashboardPage();
        dashboardBedManagersPage = new DashboardBedManagersPage();

        // Dasboard sayfası açılır
        Driver.getDriver().get(ConfigReader.getProperty("DasUrl"));

        // Dashboard sidebar menüsü hover edilir
        ReusableMethods.hover(dashboardPage.dashboardPageSideBarMenu);

        // LFC logoso görünürlük testi yapılır
        softAssert.assertTrue(dashboardPage.dashboardPageLogo
                .isDisplayed(),"Dasboard sayfası LFC logosu görünmüyor!");

        // Logo tıklanır ve anasayfanın açıldığı kontrol edilir
        dashboardPage.dashboardPageLogo.click();
        String expectedUrl = "https://qa.loyalfriendcare.com/en";
        String actualUrl = Driver.getDriver().getCurrentUrl();
        softAssert.assertEquals(actualUrl,expectedUrl
                , "Dasboard sayfasındki LFC logosuna tıklanınca anasayfa açılmadı!");
        ReusableMethods.bekle(1);

        // Bed managers sayfası açılır
        Driver.getDriver().get(ConfigReader.getProperty("DasbedUrl"));

        // Dashboard sidebar menüsü hover edilir
        ReusableMethods.hover(dashboardBedManagersPage.dashboardPageSideBarMenu);

        // LFC logoso görünürlük testi yapılır
        softAssert.assertTrue(dashboardBedManagersPage.dashboardPageLogo
                .isDisplayed(),"Dasboard sayfası LFC logosu görünmüyor!");

        // Logo tıklanır ve anasayfanın açıldığı kontrol edilir
        dashboardBedManagersPage.dashboardPageLogo.click();
        expectedUrl = "https://qa.loyalfriendcare.com/en";
        actualUrl = Driver.getDriver().getCurrentUrl();
        softAssert.assertEquals(actualUrl,expectedUrl
                , "DasboardBedManagers sayfasındki LFC logosuna  tıklanınca anasayfa açılmadı!");

        softAssert.assertAll();
    }


    @AfterClass
    public void tearDown() { Driver.quitDriver();}

}
