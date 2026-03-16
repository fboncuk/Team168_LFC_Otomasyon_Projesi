package pages.LcfHomePage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class VacinationsMainPage {

    public VacinationsMainPage(){

        PageFactory.initElements(Driver.getDriver(),this);
    }

//    // Vaccines Main sayfasında Rabies Vaccine linki
//    @FindBy(xpath = "(//a[normalize-space()='Rabies Vaccine'])[2]")
//    public WebElement vaccinesMainPageRabies;

    // Vaccines Main sayfasında Rabies Vaccine linki
    @FindBy(xpath = "(//div[@class=\"wrapper\"]/h3/a)[1]")
    public WebElement vaccinesRabies;

    // Vaccines Main sayfasında DHPP Vaccine
    // (Distemper, Hepatitis, Parainfluenza, Parvovirus Vaccine) linki
    @FindBy(xpath = "(//div[@class=\"wrapper\"]/h3/a)[2]")
    public WebElement vaccinesDHPP;

    // Vaccines Main sayfasında Feline Leukemia Vaccine linki
    @FindBy(xpath = "(//div[@class=\"wrapper\"]/h3/a)[3]")
    public WebElement vaccinesFelineLeukemia;

    // Vaccines Main sayfasında Feline Immunodeficiency Virus (FIV) Vaccine linki
    @FindBy(xpath = "(//div[@class=\"wrapper\"]/h3/a)[4]")
    public WebElement vaccinesFelineImmunodeficiency;

    // Vaccines Main sayfasında Bordetella (Kennel Cough) Vaccine linki
    @FindBy(xpath = "(//div[@class=\"wrapper\"]/h3/a)[5]")
    public WebElement vaccinesBordetella;

    // Vaccines Main sayfasında Feline Panleukopenia Vaccine linki
    @FindBy(xpath = "(//div[@class=\"wrapper\"]/h3/a)[6]")
    public WebElement vaccinesFelinePanleukopenia;

    // Vaccines Main sayfasında Feline Herpesvirus Vaccine linki
    @FindBy(xpath = "(//div[@class=\"wrapper\"]/h3/a)[7]")
    public WebElement vaccinesFelineHerpesvirus;

    // Vaccines Main sayfasında Surgical Procedure: Spaying (Ovariohysterectomy) linki
    @FindBy(xpath = "(//div[@class=\"wrapper\"]/h3/a)[8]")
    public WebElement vaccinesSurgicalProcedure;

    // Vaccines Main sayfasında Feline Viral Rhinotracheitis linki
    @FindBy(xpath = "(//div[@class=\"wrapper\"]/h3/a)[9]")
    public WebElement vaccinesFelineViral;



    // Vaccines Main sayfasında sol menü Rabies Vaccine linki
    @FindBy(xpath = "(//label[@class='container_check'])[1]")
    public WebElement vaccinesSidebarRabies;

    // Vaccines Main sayfasında sol menü DHPP Vaccine
    // (Distemper, Hepatitis, Parainfluenza, Parvovirus Vaccine) linki
    @FindBy(xpath = "(//label[@class='container_check'])[2]")
    public WebElement vaccinesSidebarDHPP;

    // Vaccines Main sayfasında sol menü Feline Leukemia Vaccine linki
    @FindBy(xpath = "(//label[@class='container_check'])[3]")
    public WebElement vaccinesSidebarFelineLeukemias;

    // Vaccines Main sayfasında sol menü Feline Immunodeficiency Virus (FIV) Vaccine linki
    @FindBy(xpath = "(//label[@class='container_check'])[4]")
    public WebElement vaccinesSidebarFelineImmunodeficiency;

    // Vaccines Main sayfasında sol menü Bordetella (Kennel Cough) Vaccine linki
    @FindBy(xpath = "(//label[@class='container_check'])[5]")
    public WebElement vaccinesSidebarBordetella;

    // Vaccines Main sayfasında sol menü Feline Panleukopenia Vaccine linki
    @FindBy(xpath = "(//label[@class='container_check'])[6]")
    public WebElement vaccinesSidebarFelinePanleukopenia;

    // Vaccines Main sayfasında sol menü Feline Herpesvirus Vaccine linki
    @FindBy(xpath = "(//label[@class='container_check'])[7]")
    public WebElement vaccinesSidebarFelineHerpesvirus;

    // Vaccines Main sayfasında sol menü Surgical Procedure: Spaying (Ovariohysterectomy) linki
    @FindBy(xpath = "(//label[@class='container_check'])[8]")
    public WebElement vaccinesSidebarSurgicalProcedure;

    // Vaccines Main sayfasında sol menü Feline Viral Rhinotracheitis linki
    @FindBy(xpath = "(//label[@class='container_check'])[9]")
    public WebElement vaccinesSidebarFelineViral;



    // Rabies Vaccine detay sayfası Title
    @FindBy(xpath = "//*[@id='description']/h1")
    public WebElement detailedTitleRabies;

    // DHPP Vaccine detay sayfası Title
    @FindBy(xpath = "//*[@id='description']/h1")
    public WebElement detailedTitleDHPP;

    // Feline Leukemia Vaccine detay sayfası Title
    @FindBy(xpath = "//*[@id='description']/h1")
    public WebElement detailedTitleFelineLeukemia;

    // Feline Immunodeficiency Vaccine detay sayfası Title
    @FindBy(xpath = "//*[@id='description']/h1")
    public WebElement detailedTitleFelineImmunodeficiency;

    // Bordetella Vaccine detay sayfası Title
    @FindBy(xpath = "//*[@id='description']/h1")
    public WebElement detailedTitleBordetella;

    // Feline Panleukopenia Vaccine detay sayfası Title
    @FindBy(xpath = "//*[@id='description']/h1")
    public WebElement detailedTitleFelinePanleukopenia;

    // Feline Herpesvirus Vaccine detay sayfası Title
    @FindBy(xpath = "//*[@id='description']/h1")
    public WebElement detailedTitleFelineHerpesvirus;

    // Surgical Procedure detay sayfası Title
    @FindBy(xpath = "//*[@id='description']/h1")
    public WebElement detailedTitleSurgicalProcedure;

    // Feline Viral detay sayfası Title
    @FindBy(xpath = "//*[@id='description']/h1")
    public WebElement detailedTitleFelineViral;










}

