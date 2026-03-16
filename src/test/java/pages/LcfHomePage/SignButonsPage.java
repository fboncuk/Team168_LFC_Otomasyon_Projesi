package pages.LcfHomePage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class SignButonsPage {

    public SignButonsPage(){

        PageFactory.initElements(Driver.getDriver(),this);
    }

    // Aşağıdaki locator ornektir
    @FindBy(id = "global-search")
    public WebElement aramaKutusu;

    //Kayıt formu username kutusu
    @FindBy (xpath = "//*[@id='name']")
    public WebElement registerPageUserNameBox;

    //Kayıt formu email kutusu
    @FindBy (id = "email")
    public WebElement registerPageEmailBox;

    //Kayıt formu password kutusu
    @FindBy (xpath = "//*[@id='password']")
    public WebElement registerPagePasswordBox;

    //Kayıt formu password confirm kutusu
    @FindBy (id = "password-confirm")
    public WebElement registerPagePasswordConfirmBox;

    //Kayıt formu Sign Up butonu
    @FindBy (xpath = "//*[@class='btn btn-primary btn-cons m-t-10']")
    public WebElement registerPageSignUpBox;

    //AnaSayfa header bölümü username butonu
    @FindBy (xpath = "(//*[@class='btn_add'])[1]")
    public WebElement headerUserName;

    //Anasayfa signout butonu
    @FindBy (xpath = "(//*[@class='btn_add'])[2]")
    public WebElement homePageSignOut;

    @FindBy (xpath = "//*[.='The email has already been taken.']")
    public WebElement emailTakenErrorMessage;

    //Ana sayfadaki SignUp Butonu
    @FindBy (xpath = "(//*[@class='btn_add'])[2]")
    public WebElement homePageSignUpButton;






    // Anasayfada sisteme giriş yapmak için kullanilacak olan Sign In linki
    @FindBy(xpath = "(//*[@class='btn_add'])[1]")
    public WebElement signInLinki;

    // Anasayfada sisteme yeni kayit olmak icin kullanilacak olan Sign Up linki
    @FindBy (xpath = "(//*[@class='btn_add'])[2]")
    public WebElement signUpLinki;

    // Sisteme giris yapabilmek icin e-mail kutucugu
    @FindBy(id = "email")
    public WebElement emailKutusu;

    // Sisteme giris yapabilmek icin password kutucugu
    @FindBy(id = "password")
    public WebElement passwordKutusu;

    // Remember Me Kutusu
    @FindBy(id = "checkbox1")
    public WebElement rememberMeKutusu;

    // Forgot Password Linki
    @FindBy(xpath = "//*[@class='text-info small']")
    public WebElement forgotPasswordLink;

    // Sign In onay butonu
    @FindBy (xpath = "//*[@class='btn btn-primary btn-cons m-t-10']")
    public WebElement signInButtonOnay;

    // Sitenin giris sayfasina donmek icin kullanilacak Sign Out linki
    @FindBy(id = "logout-form")
    public WebElement signOutlinki;



    //Kerim TIRPAN US9 için
    //Reset Password sayfa başlığı
    @FindBy (tagName = "title")
    public WebElement passwordResetPageTitle;

    //Reset Password sayfasındaki header logo yerine bulunan yazı
    @FindBy (className = "navbar-brand")
    public  WebElement passwordResetPageTextInsteadOfLogo;

    //Reset Password sayfasindaki email text box
    @FindBy (id ="email")
    public  WebElement resetPasswordPageEmailTextBox;

    //Reset Password email box label text
    @FindBy (xpath = "//label[@*='email']")
    public  WebElement resetPasswordPageEmailTextBoxLabel;

    //Reset Password sayfasindaki "Send Password Reset Link"
    @FindBy(xpath = "//button[@type='submit']")
    public  WebElement resetPasswordPageSendPasswordResetButton;

    //Reset Password hatalı email uyarı mesajı
    @FindBy (className = "invalid-feedback")
    public  WebElement feedbackMessageInvalidEmailInResetPageTextBox;

    //Reset Email hata popup
    @FindBy(xpath = "//*[contains(text(),'Email could not be sent')]")
    WebElement passwordResetErrorPopup;

    //Reset email hata popup alternatif
    @FindBy(css = ".modal-content")
    WebElement getPasswordResetErrorPopup;

    //Reset password sayfası Login Link
    @FindBy(linkText = "Login")
    public WebElement passwordResetPageLoginLink;



    //Reset password sayfası Register Link
    @FindBy(linkText = "Register")
    public WebElement passwordResetPageRegisterLink;
















    






}

