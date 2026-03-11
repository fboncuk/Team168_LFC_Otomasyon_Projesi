package pages.LcfHomePage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class HomeBodyPage {

    public HomeBodyPage(){

        PageFactory.initElements(Driver.getDriver(),this);
    }

    // Sitenin sol üst köşesinde yer alan, anasayfaya yönlendiren ana Logo
    @FindBy(xpath = "//img[@alt='LoyalFriendCare']")
    public WebElement headerLogo;

    // Üst menüde yer alan "Home" linki
    @FindBy(xpath = "//a[text()='Home']")
    public WebElement navbarHomeLink;

    // Üst menüde yer alan "About Us" linki
    @FindBy(xpath = "//a[text()='About Us']")
    public WebElement navbarAboutUsLink;

    // Üst menüde yer alan "Departments" linki
    @FindBy(xpath = "//a[text()='Departments']")
    public WebElement navbarDepartmentsLink;

    // Üst menüde yer alan "Doctors" linki
    @FindBy(xpath = "//a[text()='Doctors']")
    public WebElement navbarDoctorsLink;

    // Üst menüde yer alan "Medicines" linki
    @FindBy(xpath = "//a[text()='Medicines']")
    public WebElement navbarMedicinesLink;

    // Üst menüde yer alan "Vaccinations" linki
    @FindBy(xpath = "//a[text()='Vaccinations']")
    public WebElement navbarVaccinationsLink;



    






}

