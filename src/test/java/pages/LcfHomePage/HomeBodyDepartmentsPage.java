package pages.LcfHomePage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class HomeBodyDepartmentsPage {

    public HomeBodyDepartmentsPage(){

        PageFactory.initElements(Driver.getDriver(),this);
    }

    // Aşağıdaki locator ornektir
    @FindBy(id = "global-search")
    public WebElement aramaKutusu;

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

