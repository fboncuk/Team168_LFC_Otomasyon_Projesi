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
    public WebElement SignOutlinki;



















    






}

