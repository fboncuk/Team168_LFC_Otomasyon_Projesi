package pages.LcfAdminPage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class DashboardDepartmentsPage {

    public DashboardDepartmentsPage(){

        PageFactory.initElements(Driver.getDriver(),this);
    }

    // Aşağıdaki locator ornektir
    @FindBy(id = "global-search")
    public WebElement aramaKutusu;

    //Sol açılır menüdeki Departments başlık seçeneği
    @FindBy (xpath = "(//*[.='Departments'])[1]")
    public WebElement dashboardDepartmentsSidebarHeader;

    //Sol açılır menüdeki Departments başlığı altında yer alan departments linki
    @FindBy (xpath = "//a[.='Departments']")
    public WebElement dashboardDepartmentsSidebarListLink;

    //Department sayfasında yer alan 10 departmanı içeren liste
    @FindBy (xpath = "//table[@id='tableWithSearch']/tbody/tr/td[3]/p")
    public List<WebElement> departmentsSidebarList;

    //Departments sayfası search kutusu
    @FindBy (id = "search-table")
    public WebElement departmentsSearchBox;

    //Departments sayfası edit butonu
    @FindBy (xpath = "(//*[@class='btn btn-complete btn-cons btn-animated from-left fa fa-edit'])[1]")
    public WebElement departmentsEditButton;

    //Departments sayfası delete butonu
    @FindBy (xpath = "//button[@type='submit']")
    public WebElement departmentsDeleteButton;

    //Departments sayfası next butonu
    @FindBy (xpath = "//*[@rel='next']")
    public WebElement departmentsNextButton;

    //Departments sayfası previous butonu
    @FindBy (xpath = "//span[contains(text(),'Previous')]")
    public WebElement departmentsPreviousButton;

    //Admin anasayfasında yer alan side bar
    @FindBy(xpath = "(//nav[@data-pages='sidebar'])")
    public WebElement Sidebar;

    //Admin anasayfasında yer alan ''Departments'' kartı
    @FindBy(xpath = "(//*[@class='title'])[5]")
        public WebElement DepartmentsButonSideMenu;

    //Admin anasayfasında yeralan ''Deparments'' ana başlığı altındaki Departments butonu
    @FindBy(xpath = "(//a[@href='https://qa.loyalfriendcare.com/Dashboard/Categories'])[1]")
    public WebElement DepartmentsButon;

    //Admin anasayfasında yeralan ''Departments'' ana başlığı altındaki Create Departments butonu
    @FindBy(xpath = "(//a[@href='https://qa.loyalfriendcare.com/Dashboard/Categories/create']")
    public WebElement CreateDepartmentsButton;

    //Create Departments başlığı içinde yer alan Create Your Departments alanındaki Parent Departments alanı
    @FindBy(xpath = "(//input[@type='number'])[1]")
    public WebElement ParentDepartmentsSpinButton;

    //Create Departments başlığı içinde yer alan Create Your Departments alanındaki Order Departments alanı
    @FindBy(xpath = "(//input[@type='number'])[2]")
    public WebElement OrderDepartmentsSpinButton;

    //Create Departments başlığı içinde yer alan Create Your Departments alanındaki Title Departments alanı
    @FindBy(xpath = "(//input[@class='form-control'])[1]")
    public WebElement TitleDepartmentsTextBox;

    //Create Departments başlığı içinde yer alan Create Your Departments alanındaki  Departments Color alanı
    @FindBy(id= "select2-color-31-container")
    public WebElement DepartmentsColorGeneric;

    //Create Departments başlığı içinde yer alan Create Your Departments alanında yer alan Save Butonu
    @FindBy(xpath = "(//button[contains(@class,'fa-save')])")
    public WebElement SaveButton;

    //Create Departments başlığı içinde yer alan Create Your Departments alanındaki  File Drop Box alanı
    @FindBy(xpath = "(//div[@class='dz-default dz-message']")
    public WebElement FileDropBox;

    //Dashboard açılır menü departments ikonu
    @FindBy (xpath = "(//*[@class='fas fa-hotel'])[1]")
    public WebElement sideBarDashboardDepartmentsIcon;

    //Departments ana sayfa Departments başlığı
    @FindBy (tagName = "h3")
    public WebElement departmentsMainTitle;

    //Admin Departman sayfası departman isimlerini içeren Liste
    @FindBy (xpath = "//table[@id='tableWithSearch']//tr/td[3][@class='v-align-middle semi-bold']/p")
    public List<WebElement> departmentNamesList;

    //departments detay sayfası wellness elementi
    @FindBy (xpath = "//p[.='Wellness']")
    public WebElement departmantsDetailPageWellness;

    //Arama sonucu bulunamadı yazısı
    @FindBy (className = "odd")
    public WebElement noResultText;

    @FindBy (xpath = "//span[.='Departments']")
    public WebElement dashboardDepartmentText;

    //Edit butonları listesi
    @FindBy(xpath = "//tbody//td[@class='v-align-middle text-right']//a[contains(.,'Edit')]")
    public List<WebElement> departmentsEditButtonList;

    //Delete butonları listesi
    @FindBy(xpath = "//tbody//td[@class='v-align-middle text-right']//a[contains(.,'Delete')]")
    public List<WebElement> departmentsDeleteButtonList;

    //Edit ve delete için satır sayısı listesi
    @FindBy(xpath = "//tbody/tr")
    public List<WebElement> departmentRowsList;






















}

