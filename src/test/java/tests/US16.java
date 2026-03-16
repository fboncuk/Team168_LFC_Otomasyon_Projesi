package tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import pages.LcfHomePage.HomeBodyHeaderSectionPage;
import utilities.ConfigReader;
import utilities.Driver;


public class US16 {


//    public static void main(String[] args) {
//        System.out.printf("Hadi BISMILLAH baslasinn");
//}

    @Test
    public void US16_TC01_DoctorsLinkTesti(){

        Driver.getDriver().get(ConfigReader.getProperty("LcfUrl"));
        HomeBodyHeaderSectionPage homeBodyHeaderSectionPage = new HomeBodyHeaderSectionPage();
        homeBodyHeaderSectionPage.doctorsHeaderLink.click();
    }

    @AfterClass
    public void tearDown() {
        Driver.quitDriver();
    }

}
