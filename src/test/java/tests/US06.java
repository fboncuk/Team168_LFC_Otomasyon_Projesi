package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LcfHomePage.HomeBodyPage;
import pages.LcfHomePage.VacinationsMainPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

import java.lang.annotation.Target;

public class US06 {

    @Test
    public void US06_TC01_FooterGorunurlukTesti(){
        Driver.getDriver().get(ConfigReader.getProperty("LcfUrl"));
        HomeBodyPage homeBodyPage = new HomeBodyPage();

        ReusableMethods.bekle(2);

        Driver.quitDriver();

    }


    @Test
    public void US06_TC02_FooterYakinlastirmaTesti(){

    }



    @Test
    public void US06_TC03_FooterHoverTesti(){


    }


    @Test
    public void US06_TC04_FooterTiklamaTesti(){
        VacinationsMainPage vacinationsMainPage = new VacinationsMainPage();
        Driver.getDriver().get("https://qa.loyalfriendcare.com/en/Pets/xatem-mitrum-facen");
        String text = vacinationsMainPage.detailedTitleRabiesVaccine.getText();
        System.out.println(text);
        Assert.assertTrue(text.contains("Rabies"));

        Driver.quitDriver();

    }






}
