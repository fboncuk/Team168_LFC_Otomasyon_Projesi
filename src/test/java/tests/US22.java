package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import pages.LcfAdminPage.AdminBodyPage;
import pages.LcfAdminPage.DashboardMedicinesPage;
import pages.LcfHomePage.HomeBodyPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

public class US22 {

    @Test
    public void US22_TC01_AdminPaneliGoruntulemeTesti(){
        Driver.getDriver().get("https://qa.loyalfriendcare.com/en/admin");
        // Login'e yönlendirdi

        WebElement loginSayfasiEmailKutusu = Driver.getDriver().findElement(By.id("email"));
        loginSayfasiEmailKutusu.sendKeys("sinem.admin@loyalfriendcare.com" + Keys.ENTER);
        WebElement loginSayfasiPasswordKutusu = Driver.getDriver().findElement(By.id("password"));
        loginSayfasiPasswordKutusu.sendKeys("Loyal.123123" + Keys.ENTER);
        WebElement loginSayfasiLoginButonu = Driver.getDriver().findElement(By.xpath("//button[text()='Sign In']"));
        loginSayfasiLoginButonu.click();


        //Driver.quitDriver();

    }
}
