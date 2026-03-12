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

 //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    // Dr Alejendro detay sayfası
    @FindBy(xpath = "//div[@class='detail_title_1']")
    public WebElement detailPageDrAlejendro;


    // Dr Alejendro mesaj metni
    @FindBy (xpath = " //div")
    public WebElement detailPageDrAlejendroTextMessage;


    // Dr Marcus mesaj metni
    @FindBy (xpath = "//div[@class='detail_title_1']")
    public WebElement detailPageDrMarcus;


    // Dr Marcus mesaj metni
    @FindBy (xpath = "//div")
    public WebElement detailPageDrMarcusTextMessage;


    // Dr Olivia detay sayfas1
    @FindBy(xpath = "//div[@class='detail_title_1']")
    public WebElement detailPageDrOlivia;


    // Dr Olivia mesaj metni
    @FindBy(xpath ="//div")
    public WebElement detailPageDrOliviaTextMessage;



    // Dr Emily detay sayfas1
    @FindBy(xpath = "//div[@class='detail_title_1']")
    public WebElement detailPageDrEmily;


    // Dr Emily mesaj metni
    @FindBy(xpath ="//div")
    public WebElement detailPageDrEmilyTextMessage;


    // Dr Nathan detay sayfas1
    @FindBy(xpath = "//div[@class='detail_title_1']")
    public WebElement detailPageDrNathan;


    // Dr Nathan mesaj metni
    @FindBy(xpath ="//div")
    public WebElement detailPageDrNathanTextMessage;


    // Dr Isabella detay sayfas1
    @FindBy(xpath = "//div[@class='detail_title_1']")
    public WebElement detailPageDrIsabella;


    // Dr Isabella mesaj metni
    @FindBy(xpath ="//div")
    public WebElement detailPageDrIsabellaTextMessage;


    // Dr Liam  detay sayfas1
    @FindBy(xpath = "//div[@class='detail_title_1']")
    public WebElement detailPageDrLiam;


    // Dr Liam  mesaj metni
    @FindBy(xpath ="//div")
    public WebElement detailPageDrLiamTextMessage;


    // Dr Sophia   detay sayfas1
    @FindBy(xpath = "//div[@class='detail_title_1']")
    public WebElement detailPageDrSophia;


    // Dr Sophia   mesaj metni
    @FindBy(xpath ="//div")
    public WebElement detailPageDrSophiaTextMessage;





















}

