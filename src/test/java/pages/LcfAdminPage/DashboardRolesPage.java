package pages.LcfAdminPage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class DashboardRolesPage {

    public DashboardRolesPage(){

        PageFactory.initElements(Driver.getDriver(),this);
    }


    // Sol menüde yer alan "Roles" ana menü başlığı (Menüyü açmak için kullanılan)
    @FindBy(xpath = "//span[@class='title' and text()='Roles']")
    public WebElement sidebarRolesMainMenu;

    // Roles ana menüsü altındaki, roller sayfasına yönlendiren "Roles" linki
    @FindBy(xpath = "//a[text()='Roles']")
    public WebElement sidebarRolesSubMenuLink;

    // Roles sayfasının başarıyla açıldığını doğrulamak için kullanılan başlık kilit ikonu
    @FindBy(xpath = "//i[@class='pg-lock'][1]")
    public WebElement rolesPageHeaderLockIcon;

    // Roles sayfasında mevcut rolleri filtrelemek/bulmak için kullanılan "Search" arama kutusu
    @FindBy(xpath = "//input[@id='search-table']")
    public WebElement rolesPageSearchBox;

    // Rol listesinde yer alan "admin" rolü metni
    @FindBy(xpath = "//p[text()='admin']")
    public WebElement roleListAdminText;

    // Rol listesinde yer alan "doctors" rolü metni
    @FindBy(xpath = "//p[text()='doctors']")
    public WebElement roleListDoctorsText;

    // Rol listesinde yer alan "John Doe" rolü metni
    @FindBy(xpath = "//p[text()='John Doe']")
    public WebElement roleListJohnDoeText;

    // Rol listesinde yer alan "Kirstie Kilback" rolü metni
    @FindBy(xpath = "//p[text()='Kirstie Kilback']")
    public WebElement roleListKirstieKilbackText;

    // Rol listesinde yer alan "Miss Jarod Trantow" rolü metni
    @FindBy(xpath = "//p[text()='Miss Jarod Trantow']")
    public WebElement roleListMissJarodTrantowText;

    // Rol listesinde yer alan "Nicky Rempel" rolü metni
    @FindBy(xpath = "//p[text()='Nicky Rempel']")
    public WebElement roleListNickyRempelText;

    // Rol listesinde yer alan "Randy Cassin" rolü metni
    @FindBy(xpath = "//p[text()='Randy Cassin']")
    public WebElement roleListRandyCassinText;

    // Rol listesinde yer alan "Subscriber" rolü metni
    @FindBy(xpath = "//p[text()='Subscriber']")
    public WebElement roleListSubscriberText;

    // Rol listesinde yer alan "Super-Admin" rolü metni
    @FindBy(xpath = "//p[text()='Super-Admin']")
    public WebElement roleListSuperAdminText;

    // Rol listesinde yer alan "User" rolü metni
    @FindBy(xpath = "//p[text()='User']")
    public WebElement roleListUserText;

    // Rol listesinde yer alan "test" rolü metni
    @FindBy(xpath = "//p[text()='test']")
    public WebElement roleListtestText;

    // Rol listesinde yer alan "yeni rol" rolü metni
    @FindBy(xpath = "//p[text()='yeni rol']")
    public WebElement roleListyenirolText;

    // Rol listesinde yer alan bir rolü silmek için kullanılan "Delete" butonu
    @FindBy(xpath = "(//span[text()='Delete'])[1]")
    public WebElement rolesPageFirstDeleteButton;

    // Rol silindiğinde çıkan başarılı silme işlemi mesajı
    @FindBy(xpath = "(//span[text()='Role deleted successfully']")
    public WebElement basariliSilindiMesaji;
















}

