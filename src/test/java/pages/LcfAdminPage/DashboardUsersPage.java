package pages.LcfAdminPage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class DashboardUsersPage {

    public DashboardUsersPage(){

        PageFactory.initElements(Driver.getDriver(),this);
    }

    // Sayfa body elementi - sidebar-visible class kontrolü için
    @FindBy(tagName = "body")
    public WebElement pageBody;

    // --- 1. SOL MENÜ (Sidebar) ---
    @FindBy(xpath = "//span[normalize-space()='Users']")
    public WebElement usersMenuButton;

    @FindBy(xpath = "//a[contains(text(),'Create User')]")
    public WebElement createUsersSubMenu;

    // Dashboard sol menü Users ikonu
    @FindBy(xpath = "(//*[@class='fas fa-users'])[1]")
    public WebElement sideBarDashboardUsersIcon;

    // Sol menüde Users başlık metni
    @FindBy(xpath = "//span[.='Users']")
    public WebElement dashboardUsersText;

    // Sol menü Users başlık bağlantısı
    @FindBy(xpath = "(//*[.='Users'])[1]")
    public WebElement dashboardUsersSidebarHeader;

    // Sol menü Users alt menü Users linki
    @FindBy(linkText = "Users")
    public WebElement dashboardUsersSidebarListLink;

    // Users sayfası başlığı
    @FindBy(tagName = "h3")
    public WebElement usersMainTitle;

    // Users sayfası kullanıcı isimleri listesi
    @FindBy(xpath = "//table[@id='tableWithSearch']//tr/td[3][@class='v-align-middle semi-bold']/p")
    public List<WebElement> userNamesList;

    // Users sayfası arama kutusu
    @FindBy(id = "search-table")
    public WebElement usersSearchBox;

    // Arama sonucu bulunamadı yazısı
    @FindBy(className = "odd")
    public WebElement noResultText;

    // Edit butonları listesi
    @FindBy(xpath = "//tbody//td[@class='v-align-middle text-right']//a[contains(.,'Edit')]")
    public List<WebElement> usersEditButtonList;

    // Delete butonları listesi
    @FindBy(xpath = "//tbody//td[@class='v-align-middle text-right']//a[contains(.,'Delete')]")
    public List<WebElement> usersDeleteButtonList;

    // Satır sayısı listesi
    @FindBy(xpath = "//tbody/tr")
    public List<WebElement> usersRowsList;

    // --- 2. CREATE USER FORMU (Form Sıralamasıyla) ---

    // Name alanı
    @FindBy(id = "name")
    public WebElement nameBox;

    // Phone alanı
    @FindBy(id = "Phone")
    public WebElement phoneBox;


    // Role / User Type
    @FindBy(xpath = "(//input[@name='roles[]'])[1]")
    public WebElement roleDropdownKutusu;

    // Password alanı
    @FindBy(id = "password")
    public WebElement passwordBox;

    // Password Confirmation alanı

    @FindBy(id = "password_confirmation")
    public WebElement passwordConfirmationBox;

    // E-mail alanı
    @FindBy(id = "email")
    public WebElement emailBox;

    // Avatar / Dosya Yükleme
    @FindBy(xpath = "//input[@type='file']")
    public WebElement avatarUploadInput;

    // --- 3. İŞLEM BUTONU ---

    // Kaydet (Save) Butonu
    @FindBy(xpath = "//button[@type='submit']")
    public WebElement saveButton;









}

