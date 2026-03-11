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
    public WebElement userNameBox;

    //Kayıt formu email kutusu
    @FindBy (id = "email")
    public WebElement registerEmailBox;

    //Kayıt formu password kutusu
    @FindBy (xpath = "//*[@id='password']")
    public WebElement registerPasswordBox;

    //Kayıt formu password confirm kutusu
    @FindBy (id = "password-confirm")
    public WebElement passwordConfirmBox;

    //Kayıt formu Sign Up butonu
    @FindBy (xpath = "//*[@class='btn btn-primary btn-cons m-t-10']")
    public WebElement registerSignUpBox;










    






}

