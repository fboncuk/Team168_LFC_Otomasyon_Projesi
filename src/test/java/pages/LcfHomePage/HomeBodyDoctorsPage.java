package pages.LcfHomePage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class HomeBodyDoctorsPage {

    public HomeBodyDoctorsPage(){

        PageFactory.initElements(Driver.getDriver(),this);
    }

    // Aşağıdaki locator ornektir
    @FindBy(id = "global-search")
    public WebElement aramaKutusu;

   @FindBy(id = "div.doctors")

   public WebElement doctorCard;

   @FindBy(id = "//a[contains(text(),'Doctors')]")
    public WebElement doctorCard2;

   @FindBy(id = "//div[contains(@class,'doctors')]")
    public WebElement doctorCard3;

   @FindBy(id = "//h3[contains(text(),'Alejandro Martinez')]")
    public WebElement doctorCard4; //doktor ismi









}

