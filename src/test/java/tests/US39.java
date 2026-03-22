package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LcfAdminPage.DashboardPage;
import pages.LcfAdminPage.DashboardVaccinationsPage;
import pages.LcfHomePage.*;
import utilities.*;

import java.lang.reflect.Method;
import java.util.*;

// @org.testng.annotations.Listeners(Listeners.class)


public class US39 extends TestBaseRapor{

    SignButonsPage signButonsPage;
    DashboardPage dashboardPage;
    DashboardVaccinationsPage dashboardVaccinationsPage;
    Actions actions;

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
    public void US39_TC01_DasboardVaccinationsGorunurlukTesti() {

        // Dashboard sayfasında soldaki açılır menüde
        // Vaccinations linki görünür, aktif ve tıklanabilir olmalıdır.

        SoftAssert softAssert = new SoftAssert();
        signButonsPage = new SignButonsPage();
        dashboardPage = new DashboardPage();

        // Dashboard sidebar menüsü hover edilir
        ReusableMethods.hover(dashboardPage.dashboardPageSideBarMenu);

        // Vaccinations linki gürünürlük kontrolü yapılır ve tıklanır
        softAssert.assertTrue(dashboardPage.sidebarMenuVaccinationsLink
                .isDisplayed(),"Dashboard Vaccinations butonu görünür değil.");
        dashboardPage.sidebarMenuVaccinationsLink.click();
        ReusableMethods.bekle(1);

        // Vaccinations sayfasının açıldığı doğrulanır
        String expectedUrl = ConfigReader.getProperty("DasvacUrl");
        String actualUrl = Driver.getDriver().getCurrentUrl();

        try {
            softAssert.assertTrue(actualUrl.equals(expectedUrl));
        } catch (Exception e) {
            System.out.println("Dasboard Vaccinations sayfası açılmadı!");
        }

        softAssert.assertAll();

    }


    @Test(priority = 2, groups = "regression")
    public void US39_TC02_DasboardVaccinationsAramaTesti() {

        // Dashboard/Vaccinations sayfasındaki aşı listesinde
        // arama yapılabildiğini doğrulamak

        SoftAssert softAssert = new SoftAssert();
        signButonsPage = new SignButonsPage();
        dashboardPage = new DashboardPage();
        dashboardVaccinationsPage = new DashboardVaccinationsPage();

        // Vaccinations sayfası açılır
        Driver.getDriver().get(ConfigReader.getProperty("DasvacUrl"));

        // Halen aşı listesinde olan bir ürün aratılır
        String aranacakAsi = "Feline";
        dashboardVaccinationsPage.vaccinationsAramaKutusu.sendKeys(aranacakAsi);

        // Arama sonucunda kaç tane ürün bulunduğu kontrol edilir
        List<WebElement> bulunanElementListesi = Driver.getDriver()
                .findElements(By.xpath("//td[@class='v-align-middle semi-bold']"));

        softAssert.assertTrue(!bulunanElementListesi.isEmpty(),"Arama işlemi başarısız!");

        softAssert.assertAll();

    }


    @Test(priority = 3, groups = "regression")
    public void US39_TC03_DasboardVaccinationsSilmeTesti() {

        // Dashboard/Vaccinations sayfasındaki aşı listesinde
        // silme işlemi yapılabildiğini doğrulamak

        SoftAssert softAssert = new SoftAssert();
        signButonsPage = new SignButonsPage();
        dashboardPage = new DashboardPage();
        dashboardVaccinationsPage = new DashboardVaccinationsPage();
        actions = new Actions(Driver.getDriver());

        // Vaccinations sayfası açılır
        Driver.getDriver().get(ConfigReader.getProperty("DasvacUrl"));

        // Test için listeye geçerli bilgilerle yeni bir aşı eklenir
        String newVaccinationsTitle = "Pet Karma Aşısı";
        String newVaccinationsContent = "Pet Karma Aşısı\nBu metin aşı içerik kutusu testi için girilmiştir.";
        String newVaccinationsPrices = "1000TL";

        // Add vaccinations linkine tıklanarak aşı ekleme sayfası açılır
        dashboardVaccinationsPage.addVaccinationButton.click();

        // yeni aşı bilgileri girilir
        dashboardVaccinationsPage.addVaccinationFormTitleBox.sendKeys(newVaccinationsTitle); // aşı başlığı
        dashboardVaccinationsPage.addVaccinationFormContentBox.sendKeys(newVaccinationsContent); // içerik
        dashboardVaccinationsPage.addVaccinationFormPriceBox.sendKeys(newVaccinationsPrices); // fiyat
        dashboardVaccinationsPage.addVaccinationSaveButton.click();
        ReusableMethods.bekle(1);
        actions.sendKeys(Keys.PAGE_DOWN).perform();
        ReusableMethods.bekle(1);

        // Dasboard aşı sayfasına gidilir
        Driver.getDriver().get(ConfigReader.getProperty("DasvacUrl"));
        actions.sendKeys(Keys.PAGE_DOWN).perform();
        ReusableMethods.bekle(1);

        // Yeni aşı listeye eklenmiş mi kontrol edilir
        WebElement row = null;
        try {
            row = Driver.getDriver().findElement(
                    By.xpath("//tr[.//p[normalize-space()='"
                            + newVaccinationsTitle
                            + "']]")
            );
        } catch (Exception e) {
            // eleman bulunamadı
            row = null; // zaten null
        }

        // row null ise  ve görünür değilse soft assert fail
        if (row == null ) {
            softAssert.fail("Yeni oluşturulan aşı Vaccinations listesine eklenmemiş!");
        }

        // Delete butonunu sadece row bulunduysa tıklanır
        if (row != null) {

            try {
                WebElement deleteButonu = row.findElement(By.xpath(".//button[@type='submit']"));
                deleteButonu.click();
                ReusableMethods.bekle(1);
                actions.sendKeys(Keys.PAGE_DOWN).perform();
                ReusableMethods.bekle(1);
            } catch (Exception e) {
                softAssert.fail("HATA: Delete butonu bulunamadı!");
            }
        }
        softAssert.assertAll();

    }

    @Test(priority = 4, groups = "regression")
    public void US39_TC04_DasboardVaccinationsSilmeDoğrulamaTesti() {

        // Dashboard/Vaccinations sayfasındaki
        // Silinen bir aşının Vaccinations sayfasındaki listede olmadığını doğrulamak
        // Negatif Test

        SoftAssert softAssert = new SoftAssert();
        signButonsPage = new SignButonsPage();
        dashboardPage = new DashboardPage();
        dashboardVaccinationsPage = new DashboardVaccinationsPage();
        actions = new Actions(Driver.getDriver());

        // Vaccinations sayfası açılır
        Driver.getDriver().get(ConfigReader.getProperty("DasvacUrl"));

        // Test için listeye geçerli bilgilerle yeni bir aşı eklenir
        String newVaccinationsTitle = "Pet Parazit Aşısı";
        String newVaccinationsContent = "Pet Parazit Aşısı\nBu metin aşı içerik kutusu testi için girilmiştir.";
        String newVaccinationsPrices = "1000TL";

        // Add vaccinations linkine tıklanarak aşı ekleme sayfası açılır
        dashboardVaccinationsPage.addVaccinationButton.click();

        // yeni aşı bilgileri girilir
        dashboardVaccinationsPage.addVaccinationFormTitleBox.sendKeys(newVaccinationsTitle); // aşı başlığı
        dashboardVaccinationsPage.addVaccinationFormContentBox.sendKeys(newVaccinationsContent); // içerik
        dashboardVaccinationsPage.addVaccinationFormPriceBox.sendKeys(newVaccinationsPrices); // fiyat
        dashboardVaccinationsPage.addVaccinationSaveButton.click();
        ReusableMethods.bekle(1);
        actions.sendKeys(Keys.PAGE_DOWN).perform();
        ReusableMethods.bekle(1);

        // Dasboard aşı sayfasına gidilir
        Driver.getDriver().get(ConfigReader.getProperty("DasvacUrl"));
        actions.sendKeys(Keys.PAGE_DOWN).perform();
        ReusableMethods.bekle(1);

        // Yeni aşı listeye eklenmiş mi kontrol edilir
        WebElement row = null;
        try {
            row = Driver.getDriver().findElement(
                    By.xpath("//tr[.//p[normalize-space()='"
                            + newVaccinationsTitle
                            + "']]")
            );
        } catch (Exception e) {
            // eleman bulunamadı
            row = null; // zaten null
        }

        // row null ise  ve görünür değilse soft assert fail
        if (row == null ) {
            softAssert.fail("Yeni oluşturulan aşı Vaccinations listesine eklenmemiş!");
        }

        // Delete butonunu sadece row bulunduysa tıklanır
        if (row != null) {

            try {
                WebElement deleteButonu = row.findElement(By.xpath(".//button[@type='submit']"));
                deleteButonu.click();
                ReusableMethods.bekle(1);
                actions.sendKeys(Keys.PAGE_DOWN).perform();
                ReusableMethods.bekle(1);
            } catch (Exception e) {
                softAssert.fail("HATA: Delete butonu bulunamadı!");
            }
        }
        softAssert.assertAll();

    }


    @Test(priority = 5, groups = "regression")
    public void US39_TC05_DasboardVaccinationsEditTesti() {

        // Dashboard/Vaccinations sayfasındaki
        // aşı bilgilerinde Edit işlemi yapılabildiğini doğrulamak
        // Negatif Test

        SoftAssert softAssert = new SoftAssert();
        signButonsPage = new SignButonsPage();
        dashboardPage = new DashboardPage();
        dashboardVaccinationsPage = new DashboardVaccinationsPage();
        actions = new Actions(Driver.getDriver());

        // Vaccinations sayfası açılır
        Driver.getDriver().get(ConfigReader.getProperty("DasvacUrl"));

        // Test için listeye geçerli bilgilerle yeni bir aşı eklenir
        String newVaccinationsTitle = "Pet Kuduz Aşısı";
        String newVaccinationsContent = "Pet Kuduz Aşısı\nBu metin aşı içerik kutusu testi için girilmiştir.";
        String newVaccinationsPrices = "1000TL";

        // Add vaccinations linkine tıklanarak aşı ekleme sayfası açılır
        dashboardVaccinationsPage.addVaccinationButton.click();

        // yeni aşı bilgileri girilir
        dashboardVaccinationsPage.addVaccinationFormTitleBox.sendKeys(newVaccinationsTitle); // aşı başlığı
        dashboardVaccinationsPage.addVaccinationFormContentBox.sendKeys(newVaccinationsContent); // içerik
        dashboardVaccinationsPage.addVaccinationFormPriceBox.sendKeys(newVaccinationsPrices); // fiyat
        dashboardVaccinationsPage.addVaccinationSaveButton.click();
        ReusableMethods.bekle(1);
        actions.sendKeys(Keys.PAGE_DOWN).perform();
        ReusableMethods.bekle(1);

        // Edit işlemi için Dasboard aşı sayfasına gidilir
        Driver.getDriver().get(ConfigReader.getProperty("DasvacUrl"));
        actions.sendKeys(Keys.PAGE_DOWN).perform();
        ReusableMethods.bekle(1);

        // Yeni aşı listeye eklenmiş mi kontrol edilir
        WebElement row = null;
        try {
            row = Driver.getDriver().findElement(
                    By.xpath("//tr[.//p[normalize-space()='"
                            + newVaccinationsTitle
                            + "']]")
            );
        } catch (Exception e) {
            // eleman bulunamadı
            row = null; // zaten null
        }

        // row null ise  ve görünür değilse soft assert fail
        if (row == null ) {
            softAssert.fail("Yeni oluşturulan aşı Vaccinations listesine eklenmemiş!");
        }

        // Edit butonunu sadece row bulunduysa tıklanır
        if (row != null) {

            try {
                WebElement editButonu = row.findElement(By.xpath(".//a/span[text()='Edit']"));
                editButonu.click();

                actions.sendKeys(Keys.PAGE_DOWN).perform();
                ReusableMethods.bekle(1);
            } catch (Exception e) {
                softAssert.fail("HATA: Edit butonu bulunamadı!");
            }
        }
        String vaccinTitle = dashboardVaccinationsPage.addVaccinationFormTitleBox
                                                                .getAttribute("value");

        String vaccinTitleEdit = vaccinTitle + " - " + "Edit Test";

        dashboardVaccinationsPage.addVaccinationFormTitleBox.clear();
        dashboardVaccinationsPage.addVaccinationFormTitleBox.sendKeys(vaccinTitleEdit);
        dashboardVaccinationsPage.editDontChangeImageCheckBox.click();
        dashboardVaccinationsPage.addVaccinationSaveButton.click();
        actions.sendKeys(Keys.PAGE_DOWN).perform();
        ReusableMethods.bekle(1);

        Driver.getDriver().get(ConfigReader.getProperty("DasvacUrl"));
        actions.sendKeys(Keys.PAGE_DOWN).perform();
        ReusableMethods.bekle(1);

        WebElement silinecekElement = null;
        try {
            silinecekElement = Driver.getDriver().findElement(
                    By.xpath("//tr[.//p[normalize-space()='"
                            + vaccinTitleEdit
                            + "']]")
            );
        } catch (TimeoutException e) {
            row = null; // zaten null
        }


        // Test için oluşturulan aşı silinir
        if (silinecekElement != null) {

            try {
                WebElement deleteButonu = silinecekElement.findElement(By.xpath(".//button[@type='submit']"));
                deleteButonu.click();
                ReusableMethods.bekle(1);
                actions.sendKeys(Keys.PAGE_DOWN).perform();
                ReusableMethods.bekle(1);
            } catch (Exception e) {
                softAssert.fail("HATA: Delete butonu bulunamadı!");
            }
        }
        softAssert.assertAll();

    }


    @Test(priority = 6, groups = "regression")
    public void US39_TC06_DasboardVaccinationsGecerliFiyatTesti() {
        // Dashboard/Vaccinations sayfasındaki
        // aşı fiyat kutusuna sadece rakam girilebildiğini doğrulamak.
        //Negatif test

        SoftAssert softAssert = new SoftAssert();
        signButonsPage = new SignButonsPage();
        dashboardPage = new DashboardPage();
        dashboardVaccinationsPage = new DashboardVaccinationsPage();
        actions = new Actions(Driver.getDriver());

        // Vaccinations sayfası açılır
        Driver.getDriver().get(ConfigReader.getProperty("DasvacUrl"));

        // Test için listeye geçerli bilgilerle yeni bir aşı eklenir
        String validTitle = "Kedi Lösemi Aşısı";
        String validContent = "Kedi Lösemi Aşısı\nBu metin aşı içerik kutusu testi için girilmiştir.";
        String invalidPrice = "10+#%-";

        // Add vaccinations linkine tıklanarak aşı ekleme sayfası açılır
        dashboardVaccinationsPage.addVaccinationButton.click();

        // yeni aşı bilgileri girilir
        dashboardVaccinationsPage.addVaccinationFormTitleBox.sendKeys(validTitle); // aşı başlığı
        dashboardVaccinationsPage.addVaccinationFormContentBox.sendKeys(validContent); // içerik
        dashboardVaccinationsPage.addVaccinationFormPriceBox.sendKeys(invalidPrice); // fiyat
        dashboardVaccinationsPage.addVaccinationSaveButton.click();
        ReusableMethods.bekle(1);
        actions.sendKeys(Keys.PAGE_DOWN).perform();
        ReusableMethods.bekle(1);

        // Dasboard aşı sayfasına gidilir
        Driver.getDriver().get(ConfigReader.getProperty("DasvacUrl"));
        actions.sendKeys(Keys.PAGE_DOWN).perform();
        ReusableMethods.bekle(1);

        // Yeni aşı listeye eklenmiş mi kontrol edilir
        WebElement row = null;
        try {
            row = Driver.getDriver().findElement(
                    By.xpath("//tr[.//p[normalize-space()='"
                            + validTitle
                            + "']]")
            );
        } catch (Exception e) {
            // eleman bulunamadı
            row = null; // zaten null
        }

        WebElement actualAsiFiyatiElementi = row
                .findElement(By.xpath(".//td[@class='v-align-middle']"));

        String actulaAsiFiyatiString = actualAsiFiyatiElementi.getText();

        softAssert.assertNotEquals(actulaAsiFiyatiString,invalidPrice
                ,"Sistem geçersiz aşı fiyet bilgisini kabul etti!");

        ReusableMethods.bekle(3);

        // Test için oluşturulan aşı silinir
        if (row != null) {

            try {
                WebElement deleteButonu = row.findElement(By.xpath(".//button[@type='submit']"));
                deleteButonu.click();
                ReusableMethods.bekle(1);
                actions.sendKeys(Keys.PAGE_DOWN).perform();
                ReusableMethods.bekle(1);
            } catch (Exception e) {
                softAssert.fail("HATA: Delete butonu bulunamadı!");
            }
        }

        softAssert.assertAll();

    }

        @AfterClass
    public void tearDown() {Driver.quitDriver();}

}