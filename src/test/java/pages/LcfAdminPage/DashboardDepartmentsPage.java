package pages.LcfAdminPage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class DashboardDepartmentsPage {

    public DashboardDepartmentsPage(){

        PageFactory.initElements(Driver.getDriver(),this);
    }

    // Aşağıdaki locator ornektir
    @FindBy(id = "global-search")
    public WebElement aramaKutusu;

    //Sol açılır menüdeki Departments başlık seçeneği
    @FindBy (xpath = "(//*[.='Departments'])[1]")
    public WebElement sidebarDepartmentsHeader;

    //Sol açılır menüdeki Departments başlığı altında yer alan departments linki
    @FindBy (xpath = "//a[.='Departments']")
    public WebElement departmentsListLink;

    //Department sayfasında yer alan 10 departmanı içeren liste
    @FindBy (xpath = "//table[@id='tableWithSearch']/tbody/tr/td[3]/p")
    public WebElement departmentsList;

    //Departments sayfası search kutusu
    @FindBy (id = "search-table")
    public WebElement searchBox;

    //Departments sayfası edit butonu
    @FindBy (xpath = "(//*[@class='btn btn-complete btn-cons btn-animated from-left fa fa-edit'])[1]")
    public WebElement editButton;

    //Departments sayfası delete butonu
    @FindBy (xpath = "//button[@type='submit']")
    public WebElement deleteButonu;

    //Departments sayfası next butonu
    @FindBy (xpath = "//*[@rel='next']")
    public WebElement nextButton;

    //Departments sayfası previous butonu
    @FindBy (className = "flex justify-between")
    public WebElement previousButton;












}

