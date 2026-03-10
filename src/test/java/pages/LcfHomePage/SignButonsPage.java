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

    @FindBy (xpath = "//*[@id='name']")
    public WebElement userNameKutusu;

    @FindBy (id = "email")
    public WebElement registerEmailKutusu;

    @FindBy (xpath = "//*[@id='password']")
    public WebElement registerPasswordKutusu;

    @FindBy (id = "password-confirm")
    public WebElement passwordConfirmKutusu;

    @FindBy (xpath = "//*[@class='btn btn-primary btn-cons m-t-10']")
    public WebElement registerSignUpButonu;










    






}

