package pages.LcfHomePage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class HomeBodyDepartmentsPage {

    public HomeBodyDepartmentsPage(){

        PageFactory.initElements(Driver.getDriver(),this);
    }

    // 1. Wellness
    @FindBy(xpath = "//h3[text()='Wellness']")
    public WebElement WelnessDepartments;

    // 2. Dental Care
    @FindBy(xpath = "//h3[text()='Dental Care']")
    public WebElement DentalCare;

    // 3. Anaesthesia
    @FindBy(xpath = "//h3[text()='Anaesthesia']")
    public WebElement Anaesthesia;

    // 4. Dermatology
    @FindBy(xpath = "//h3[text()='Dermatology']")
    public WebElement Dermatology;

    // 5. Diagnostics
    @FindBy(xpath = "//h3[text()='Diagnostics']")
    public WebElement Diagnostics;

    // 6. Vaccinations
    @FindBy(xpath = "//h3[text()='Vaccinations']")
    public WebElement Vaccinations;

    // 7. Pain Control
    @FindBy(xpath = "//h3[text()='Pain Control']")
    public WebElement PainControl;

    // 8. Boarding
    @FindBy(xpath = "//h3[text()='Boarding']")
    public WebElement Boarding;

    
    // Anasayfa Header alanında yer alan Departments Butonu
    @FindBy(xpath = "(//a[@href='https://qa.loyalfriendcare.com/Departments'])[3]")
    public WebElement DepartmentsMainButton;

    //Anasayfa Header alanında yer alan Departments Butonu içinde yer alan Wellness Linki
    @FindBy(xpath = "//a[@href='https://qa.loyalfriendcare.com/Departments/wellness'])[2]")
    public WebElement WellnesLinkButton;

    //Anasayfa Header alanında yer alan Departments Butonu içinde yer alan Dental Care Linki
    @FindBy(xpath = "//a[@href='https://qa.loyalfriendcare.com/Departments/dental-care'])[2]")
    public WebElement DentalCareLinkButton;

    //Anasayfa Header alanında yer alan Departments Butonu içinde yer alan Anaesthesia Linki
    @FindBy(xpath = "//a[@href='https://qa.loyalfriendcare.com/Departments/anaesthesia'])[2]")
    public WebElement AnaesthesiaLinkButton;

    //Anasayfa Header alanında yer alan Departments Butonu içinde yer alan Dermatology Linki
    @FindBy(xpath = "//a[@href='https://qa.loyalfriendcare.com/Departments/dermatology'])[2]")
    public WebElement DermatologyLinkButton;

    //Anasayfa Header alanında yer alan Departments Butonu içinde yer alan Diagnostics Linki
    @FindBy(xpath = "(//a[@href='https://qa.loyalfriendcare.com/Departments/diagnostics'])[2]")
    public WebElement DiagnosticsLinkButton;

    //Anasayfa Header alanında yer alan Departments Butonu içinde yer alan Vaccinations Linki
    @FindBy(xpath = "(//a[@href='https://qa.loyalfriendcare.com/Departments/vaccinations'])[1]")
    public WebElement VaccinationsLinkButton;

    //Anasayfa Header alanında yer alan Departments Butonu içinde yer alan Pain Control Linki
    @FindBy(xpath = "(//a[@href='https://qa.loyalfriendcare.com/Departments/pain-control'])[1]")
    public WebElement PainControlLinkButton;

    //Anasayfa Header alanında yer alan Departments Butonu içinde yer alan Boarding Linki
    @FindBy(xpath = "(//a[@href='https://qa.loyalfriendcare.com/Departments/boarding'])[1]")
    public WebElement BoardingLinkButton;


}

