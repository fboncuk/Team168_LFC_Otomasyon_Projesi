package pages.LcfAdminPage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class DashboardVaccinationsPage {

    public DashboardVaccinationsPage(){

        PageFactory.initElements(Driver.getDriver(),this);
    }

    // Aşağıdaki locator ornektir
    @FindBy(id = "global-search")
    public WebElement aramaKutusu;

    //Dashboard açılır menüdeki Vaccinations linki
    @FindBy (xpath = "(//*[.='Vaccinations'])[2]")
    public WebElement vaccinationsSideBar;

    //Aşı sayfasındaki aşı ekleme butonu
    @FindBy (xpath = "//*[@class='btn btn-tag btn-success btn-tag-rounded']")
    public WebElement addVaccinationButton;

    //Aşı ekleme formunun başlığı
    @FindBy (tagName = "h3")
    public WebElement HeaderTitle;

    //Aşı ekleme formu title kutusu
    @FindBy (id = "Title_en")
    public WebElement titleBox;

    //Aşı ekleme formu content kutusu
    @FindBy (id = "body_en")
    public WebElement contentBox;

    //Aşı ekleme formu price kutusu
    @FindBy (id = "price")
    public WebElement priceBox;

    //Aşı ekleme sayfası save butonu
    @FindBy (xpath = "//*[*='save']")
    public WebElement vaccinationSaveButton;

    //Aşı ekleme sayfası file drop box
    @FindBy (xpath = "//*[@class='dz-default dz-message']")
    public WebElement fileDropBox;












}

