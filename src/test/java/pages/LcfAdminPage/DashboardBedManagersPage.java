package pages.LcfAdminPage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class DashboardBedManagersPage {

    public DashboardBedManagersPage(){

        PageFactory.initElements(Driver.getDriver(),this);
    }

    // Aşağıdaki locator ornektir
    @FindBy(id = "global-search")
    public WebElement aramaKutusu;


    //BedManager sayfasi taitl text
    @FindBy(tagName = "h3")
    public WebElement bedMenegersPageTitleText;

    //Bedmanagers Page Edit butonu
    @FindBy(xpath = "(//a[@class='btn btn-complete btn-cons btn-animated from-left fa fa-edit'])[1]")
    public WebElement bedManegerPageEditButon;

    // Admin dashboard Bed Managers sidebar link
    @FindBy(xpath = "//span[text()='Bed managers']")
    public WebElement bedManagersSidebarLink;


    // Admin dashboard Bed Managers sidebar sublink
    @FindBy(xpath = "//a[text()='Bed managers']")
    public WebElement bedManagersSidebarSubLink;

    // Admin dashboard Add Bed Managers sidebar sublink
    @FindBy(xpath = "//a[text()='Create Bed managers']")
    public WebElement addBedManagersSidebarSubLink;


    //BedManegers Page Title List
    @FindBy(xpath = "//*[@id=\"tableWithSearch\"]/tbody/tr[*]/td[2]/p")
    public WebElement bedMenegerPageListTitle;


    //BedManeger Page Serach box
    @FindBy(id = "search-table")
    public WebElement getBedMenegersPageSerachBox;

    //BedMeneger Page Delete Buton List
    @FindBy(xpath = "//*[@id=\"tableWithSearch\"]/tbody/tr[*]/td[6]")
    public WebElement getBedManegerPageDeleteButon;

    //Bed Meneger Page  Image List
    @FindBy(xpath = "    //*[@id=\"tableWithSearch\"]/tbody/tr[*]/td[1]/span\n")
    public WebElement bedMenegerPageImageList;


    // Admin dashboard Create Bed Managers Title
    @FindBy(xpath = "//h3[text()=' Create Bed managers ']")
    public WebElement dashboardCreateBedManagerTitle;


    // Admin dashboard Create Bed Managers Title Kutusu
    @FindBy(xpath = "//input[@id=\"Title_en\"]")
    public WebElement createBedManagerTitleKutusu;

    // Admin dashboard Create Bed Managers Content Kutusu
    @FindBy(xpath = "//div[@class=\"note-editable\"]")
    public WebElement createBedManagerContent;

    // Admin dashboard Create Bed Managers Save Butonu
    @FindBy(xpath = "//button[@type=\"submit\"]")
    public WebElement createBedManagerSaveButonu;

    // Admin dashboard Create Bed Managers Departman Dropdown
    @FindBy(id="select2-category_id-5r-container")
    public WebElement createBedManagerDepDropdown;

    // Admin dashboard Create Bed Managers Doctor Dropdown
    @FindBy(id="select2-category_id-5r-container")
    public WebElement createBedManagerDoctorDropdown;

    // Admin dashboard Create Bed Managers Medicine Dropdown
    @FindBy(id="select2-Instagram_id-bm-container")
    public WebElement createBedManagerMedicineDropdown;

    // Admin dashboard Create Bed Managers Price Kutusu
    @FindBy(xpath = "(//input[@class='form-control'])[3]")
    public WebElement createBedManagerPriceBox;

    // Admin dashboard Create Bed Managers File Upload
    @FindBy(xpath = "(//div[@class='dz-default dz-message'])[1]")
    public WebElement createBedManagerFileUpload;


}

