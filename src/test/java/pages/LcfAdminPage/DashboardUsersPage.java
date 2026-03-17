package pages.LcfAdminPage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class DashboardUsersPage {

    public DashboardUsersPage(){

        PageFactory.initElements(Driver.getDriver(),this);
    }

    // --- 1. SOL MENÜ (Sidebar) ---
    @FindBy(xpath = "//span[text()='Users']")
    public WebElement usersMenuButton;

    @FindBy(xpath = "//a[contains(text(),'Create User')]")
    public WebElement createUsersSubMenu;

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

