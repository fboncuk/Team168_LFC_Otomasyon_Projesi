package pages.LcfAdminPage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class DashboardBedManagersPage {

    public DashboardBedManagersPage(){

        PageFactory.initElements(Driver.getDriver(),this);
    }

    // Aşağıdaki locator ornektir
    @FindBy(id = "global-search")
    public WebElement aramaKutusu;

    //BedManager sayfasi taitl text
    @FindBy(tagName = "h3")
    public WebElement bedMenegersPageTitleText;

    //Bedmanagers Page Edit butonu
    @FindBy(xpath = "(//a[@class='btn btn-complete btn-cons btn-animated from-left fa fa-edit'])[1]")
    public WebElement bedManegerPageEditButon;



    //BedManegers Page Title List
    @FindBy(xpath = "//*[@id=\"tableWithSearch\"]/tbody/tr[*]/td[2]/p")
    public WebElement bedMenegerPageListTitle;


    //BedManeger Page Serach box
    @FindBy(id = "search-table")
    public WebElement getBedMenegersPageSerachBox;

    //BedMeneger Page Delete Buton List
    @FindBy(xpath = "//*[@id=\"tableWithSearch\"]/tbody/tr[*]/td[6]")
    public WebElement getBedManegerPageDeleteButon;

    //Bed Meneger Page  Image List
    @FindBy(xpath = "    //*[@id=\"tableWithSearch\"]/tbody/tr[*]/td[1]/span\n")
    public WebElement bedMenegerPageImageList;






}

