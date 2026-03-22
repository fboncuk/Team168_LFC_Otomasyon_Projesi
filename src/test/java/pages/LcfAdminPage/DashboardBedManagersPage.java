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


    //BedManager sayfasi Title text
    @FindBy(xpath = "//h3/text()[normalize-space()]")
    public WebElement bedManagersPageTitleText;

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

    // Admin dashboard Add Bed managers butonu
    @FindBy(xpath = "//a[@class='btn btn-tag btn-success btn-tag-rounded']")
    public WebElement dashboardAddBedManagerButonu;

    // Admin dashboard Create Bed Managers Title Kutusu
    @FindBy(xpath = "//input[@id=\"Title_en\"]")
    public WebElement createBedManagerTitleKutusu;

    // Admin dashboard Create Bed Managers Content Kutusu
    @FindBy(xpath = "//div[@class=\"note-editable\"]")
    public WebElement createBedManagerContent;

    // Admin dashboard Create Bed Managers Save Butonu
    @FindBy(xpath = "//button[@type=\"submit\"]")
    public WebElement createBedManagerSaveButonu;

    // Admin dashboard Create Bed Managers Department Dropdown
    @FindBy(xpath = "(//div[@class='form-group form-group-default form-group-default-select2 required'])[1]")
    public WebElement createBedManagerDepDropdown;

    // Admin dashboard Create Bed Managers Doctor Dropdown
    @FindBy(xpath = "(//div[@class='form-group form-group-default form-group-default-select2 required'])[2]")
    public WebElement createBedManagerDoctorDropdown;

    // Admin dashboard Create Bed Managers Medicine Dropdown
    @FindBy(xpath = "(//div[@class='form-group form-group-default form-group-default-select2 required'])[3]")
    public WebElement createBedManagerMedicineDropdown;

    // Admin dashboard Create Bed Managers Price Kutusu
    @FindBy(xpath = "(//input[@class='form-control'])[3]")
    public WebElement createBedManagerPriceBox;

    // Admin dashboard Create Bed Managers File Upload
    @FindBy(xpath = "(//input[@type='hidden'])[3]")
    public WebElement createBedManagerFileUpload;


    // Yeni yatak oluşturma sağ dd menü locatorleri
    // Sağ Content Department dd menu Wellness
    @FindBy (xpath = "//li[@role='treeitem'][1]")
    public WebElement departmentDdMenuWellness;

    // Sağ Content Department dd menu Dental Care
    @FindBy (xpath = "//li[@role='treeitem'][2]")
    public WebElement departmentDdMenuDentalCare;

    // Sağ Content Department dd menu Anaesthesia
    @FindBy (xpath = "//li[@role='treeitem'][3]")
    public WebElement departmentDdMenuAnaesthesia;

    // Sağ Content Department dd menu Dermatology
    @FindBy (xpath = "//li[@role='treeitem'][4]")
    public WebElement departmentDdMenuDermatology;

    // Sağ Content Department dd menu Diagnostics
    @FindBy (xpath = "//li[@role='treeitem'][5]")
    public WebElement departmentDdMenuDiagnostics;

    // Sağ Content Department dd menu Vaccinations
    @FindBy (xpath = "//li[@role='treeitem'][6]")
    public WebElement departmentDdMenuVaccinations;

    // Sağ Content Department dd menu Pain Control
    @FindBy (xpath = "//li[@role='treeitem'][7]")
    public WebElement departmentDdMenuPainControl;

    // Sağ Content Department dd menu Boarding
    @FindBy (xpath = "//li[@role='treeitem'][8]")
    public WebElement departmentDdMenuBoarding;


    // Yeni yatak oluşturma sağ dd menü doktorlar
    // Sağ Content Doctors dd menu Dr. Alejandro Martinez
    @FindBy (xpath = "//li[@class='select2-results__option'][1]")
    public WebElement doctorsDdMenuDrAlejandro;

    // Sağ Content Doctors dd menu Dr. Marcus Rodriguez
    @FindBy (xpath = "//li[@class='select2-results__option'][2]")
    public WebElement doctorsDdMenuDrMarcus;

    // Sağ Content Doctors dd menu Dr. Olivia Bennett
    @FindBy (xpath = "//li[@class='select2-results__option'][3]")
    public WebElement doctorsDdMenuDrOlivia;

    // Sağ Content Doctors dd menu Dr. Emily Chang
    @FindBy (xpath = "//li[@class='select2-results__option'][4]")
    public WebElement doctorsDdMenuDrEmily;

    // Sağ Content Doctors dd menu Dr. Nathan Patel
    @FindBy (xpath = "//li[@class='select2-results__option'][5]")
    public WebElement doctorsDdMenuDrNathan;

    // Sağ Content Doctors dd menu Dr. Isabella Wong
    @FindBy (xpath = "//li[@class='select2-results__option'][6]")
    public WebElement doctorsDdMenuDrIsabella;

    // Sağ Content Doctors dd menu Dr. Liam O'Connor
    @FindBy (xpath = "//li[@class='select2-results__option'][7]")
    public WebElement doctorsDdMenuDrLiam;

    // Sağ Content Doctors dd menu Dr. Sophia Kim
    @FindBy (xpath = "//li[@class='select2-results__option'][8]")
    public WebElement doctorsDdMenuDrSophia;



    // Yeni yatak oluşturma sağ menü medicines
    // Sağ Content Medicines dd menu Rimadyl (Carprofen)
    @FindBy (xpath = "//li[@class='select2-results__option'][1]")
    public WebElement medicinesDdMenuRimadyl;

    // Sağ Content Medicines dd menu Revolution (Selamectin)
    @FindBy (xpath = "//li[@class='select2-results__option'][2]")
    public WebElement medicinesDdMenuRevolution;

    // Sağ Content Medicines dd menu Baytril (Enrofloxacin)
    @FindBy (xpath = "//li[@class='select2-results__option'][3]")
    public WebElement medicinesDdMenuBaytril;

    // Sağ Content Medicines dd menu Apoquel (Oclacitinib)
    @FindBy (xpath = "//li[@class='select2-results__option'][4]")
    public WebElement medicinesDdMenuApoquel;

    // Sağ Content Medicines dd menu Metacam (Meloxicam)
    @FindBy (xpath = "//li[@class='select2-results__option'][5]")
    public WebElement medicinesDdMenuMetacam;



}

