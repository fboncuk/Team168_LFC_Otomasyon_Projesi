package tests;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LcfAdminPage.AdminBodyPage;
import pages.LcfAdminPage.DashboardPage;
import pages.LcfHomePage.*;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.Listeners;
import utilities.ReusableMethods;

import java.util.*;

@org.testng.annotations.Listeners(Listeners.class)

public class US28 {

    SignButonsPage signButonsPage;
    AdminBodyPage adminBodyPage;
    DashboardPage dashboardPage;

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
    }

    @Test(priority = 1)
    public void US28_TC01_DasboardBedManagerGornurlukTesti() {

        // Dashboard sayfasında soldaki açılır menüde
        // Bed Managers linkinin görünür, aktif ve tıklanabilir olduğunu ve
        // linkine tıklandığında, açılır menü seçeneklerinin görüldüğünü doğrulamak

        SoftAssert softAssert = new SoftAssert();
        signButonsPage = new SignButonsPage();
        adminBodyPage = new AdminBodyPage();
        dashboardPage = new DashboardPage();

        // Profil ismine tıklayıp Dasboard sayfasına gidilir
        signButonsPage.signInLinki.click();

        // Dashboard sidebar menüsü hover edilir ve Bed Manager tıklanır
        ReusableMethods.hover(dashboardPage.dashboardPageSideBarMenu);
        dashboardPage.DashboardPageLeftMenuBedManagersLink.click();


        ReusableMethods.hover(dashboardPage.DashboardPageLeftMenuSubmenuBedManagers);
        softAssert.assertTrue(dashboardPage.DashboardPageLeftMenuSubmenuBedManagers.isDisplayed()
                ,"Dashboard Bed Managers butonu görünür değil.");

        ReusableMethods.hover(dashboardPage.DashboardPageLeftMenuSubmenuCreateBedManagers);
        softAssert.assertTrue(dashboardPage.DashboardPageLeftMenuSubmenuCreateBedManagers.isDisplayed()
                ,"Dashboard Create Bed Managers butonu görünür değil.");






    }



















    @AfterClass
    public void tearDown() { Driver.quitDriver();}

}
