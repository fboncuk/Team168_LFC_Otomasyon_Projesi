package scenarios;

import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LcfHomePage.SignButonsPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

public class SunumSenaryosu_01 {


    WebDriver driver;
    SoftAssert softAssert;
    SignButonsPage signButonsPage;
    Faker faker;

    @BeforeMethod
    public void setUp() {

        Driver.getDriver().manage().deleteAllCookies();
        Driver.getDriver().get(ConfigReader.getProperty("RegUrl"));
        softAssert = new SoftAssert();
        signButonsPage = new SignButonsPage();
        faker = new Faker();
    }


    @Test(priority = 1)
    public void US07_TC01_RegisterFormuPozitifTest() {


        //Açılan sayfadaki Register formundaki User Name, E-Mail Address,Password,Confirm //Password alanlarına geçerli bilgileri girin.

        String validUserName = faker.name().lastName();

        signButonsPage.registerPageUserNameBox.
                sendKeys(validUserName);
        signButonsPage.registerPageEmailBox.
                sendKeys(faker.internet().emailAddress());
        signButonsPage.registerPagePasswordBox.
                sendKeys(ConfigReader.getProperty("T07AdminPassword"));
        signButonsPage.registerPagePasswordConfirmBox.
                sendKeys(ConfigReader.getProperty("T07AdminPassword"));

        //Sayfadaki Sign Up butonuna tıklayın

        signButonsPage.registerPageSignUpBox.click();
        ReusableMethods.bekle(3);

        //Header bölümünde username’in göründüğünü doğrulayın

        softAssert.assertTrue(signButonsPage.headerUserName.isDisplayed(),
                "Geçerli bilgilerle kayıt yapılmasına rağmen kullanıcı ismi header bölümünde görüntülenemedi.");

        softAssert.assertAll();

    }

    @AfterClass
    public void tearDown() {
        Driver.quitDriver();
    }

}
