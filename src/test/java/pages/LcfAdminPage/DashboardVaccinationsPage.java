package pages.LcfAdminPage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class DashboardVaccinationsPage {

    public DashboardVaccinationsPage(){

        PageFactory.initElements(Driver.getDriver(),this);
    }

    // Admin dashboard sayfasında sidebar menü Vaccinations Linki
    @FindBy(xpath = "//span[text()='Vaccinations']")
    public WebElement dashboardVaccinations;

    // Admin dashboard Vaccinations sayfasında Vaccinations Title
    @FindBy(xpath = "//span[text()='Vaccinations']")
    public WebElement dashboardVaccinationsTitle;

    //Dashboard açılır menüdeki Vaccinations linki
    @FindBy (xpath = "(//*[.='Vaccinations'])[2]")
    public WebElement dashboardVaccinationSideBar;

    //Aşı sayfasındaki aşı ekleme butonu
    @FindBy (xpath = "//*[@class='btn btn-tag btn-success btn-tag-rounded']")
    public WebElement addVaccinationButton;

    //Aşı ekleme formunun başlığı
    @FindBy (tagName = "h3")
    public WebElement addVaccinationFormHeaderTitle;

    //Aşı ekleme formu title kutusu
    @FindBy (xpath = "//input[@name='Title_en']")
    public WebElement addVaccinationFormTitleBox;

    //Aşı ekleme formu content kutusu
    @FindBy (xpath = "//input[@name='body_en']")
    public WebElement addVaccinationFormContentBox;

    //Aşı ekleme formu price kutusu
    @FindBy (xpath = "//input[@name='price']")
    public WebElement addVaccinationFormPriceBox;

    //Aşı ekleme sayfası save butonu
    @FindBy (xpath = "//*[*='save']")
    public WebElement addVaccinationSaveButton;

    //Aşı ekleme sayfası file drop box
    @FindBy (xpath = "//*[@class='dz-default dz-message']")
    public WebElement addVaccinationFileDropBox;






    // Admin dashboard Vaccinations sayfası Vaccination listesi
    @FindBy(xpath = "(//td[@class='v-align-middle semi-bold'])[1]")
    public WebElement dashboardVaccinationsUrun1;

    // Admin dashboard Vaccinations sayfası Vaccination listesi
    @FindBy(xpath = "(//td[@class='v-align-middle semi-bold'])[2]")
    public WebElement dashboardVaccinationsUrun2;

    // Admin dashboard Vaccinations sayfası Vaccination listesi
    @FindBy(xpath = "(//td[@class='v-align-middle semi-bold'])[3]")
    public WebElement dashboardVaccinationsUrun3;

    // Admin dashboard Vaccinations sayfası Vaccination listesi
    @FindBy(xpath = "(//td[@class='v-align-middle semi-bold'])[4]")
    public WebElement dashboardVaccinationsUrun4;

    // Admin dashboard Vaccinations sayfası Vaccination listesi
    @FindBy(xpath = "(//td[@class='v-align-middle semi-bold'])[5]")
    public WebElement dashboardVaccinationsUrun5;

    // Admin dashboard Vaccinations sayfası Vaccination listesi
    @FindBy(xpath = "(//td[@class='v-align-middle semi-bold'])[6]")
    public WebElement dashboardVaccinationsUrun6;

    // Admin dashboard Vaccinations sayfası Vaccination listesi
    @FindBy(xpath = "(//td[@class='v-align-middle semi-bold'])[7]")
    public WebElement dashboardVaccinationsUrun7;

    // Admin dashboard Vaccinations sayfası Vaccination listesi
    @FindBy(xpath = "(//td[@class='v-align-middle semi-bold'])[8]")
    public WebElement dashboardVaccinationsUrun8;

    // Admin dashboard Vaccinations sayfası Vaccination listesi
    @FindBy(xpath = "(//td[@class='v-align-middle semi-bold'])[9]")
    public WebElement dashboardVaccinationsUrun9;

    // Admin dashboard Vaccinations sayfası Vaccination listesi
    @FindBy(xpath = "(//td[@class='v-align-middle semi-bold'])[10]")
    public WebElement dashboardVaccinationsUrun10;


    // Admin dashboard Vaccinations sayfası Vaccination listesi Edit Butonu
    @FindBy(xpath = "//*[@id=\"tableWithSearch\"]/tbody/tr[1]/td[4]")
    public WebElement dashboardVaccinationsEditUrun1;

    // Admin dashboard Vaccinations sayfası Vaccination listesi Edit Butonu
    @FindBy(xpath = "//*[@id=\"tableWithSearch\"]/tbody/tr[2]/td[4]")
    public WebElement dashboardVaccinationsEditUrun2;

    // Admin dashboard Vaccinations sayfası Vaccination listesi Edit Butonu
    @FindBy(xpath = "//*[@id=\"tableWithSearch\"]/tbody/tr[3]/td[4]")
    public WebElement dashboardVaccinationsEditUrun3;

    // Admin dashboard Vaccinations sayfası Vaccination listesi Edit Butonu
    @FindBy(xpath = "//*[@id=\"tableWithSearch\"]/tbody/tr[4]/td[4]")
    public WebElement dashboardVaccinationsEditUrun4;

    // Admin dashboard Vaccinations sayfası Vaccination listesi Edit Butonu
    @FindBy(xpath = "//*[@id=\"tableWithSearch\"]/tbody/tr[5]/td[4]")
    public WebElement dashboardVaccinationsEditUrun5;

    // Admin dashboard Vaccinations sayfası Vaccination listesi Edit Butonu
    @FindBy(xpath = "//*[@id=\"tableWithSearch\"]/tbody/tr[6]/td[4]")
    public WebElement dashboardVaccinationsEditUrun6;

    // Admin dashboard Vaccinations sayfası Vaccination listesi Edit Butonu
    @FindBy(xpath = "//*[@id=\"tableWithSearch\"]/tbody/tr[7]/td[4]")
    public WebElement dashboardVaccinationsEditUrun7;

    // Admin dashboard Vaccinations sayfası Vaccination listesi Edit Butonu
    @FindBy(xpath = "//*[@id=\"tableWithSearch\"]/tbody/tr[8]/td[4]")
    public WebElement dashboardVaccinationsEditUrun8;

    // Admin dashboard Vaccinations sayfası Vaccination listesi Edit Butonu
    @FindBy(xpath = "//*[@id=\"tableWithSearch\"]/tbody/tr[9]/td[4]")
    public WebElement dashboardVaccinationsEditUrun9;

    // Admin dashboard Vaccinations sayfası Vaccination listesi Edit Butonu
    @FindBy(xpath = "//*[@id=\"tableWithSearch\"]/tbody/tr[10]/td[4]")
    public WebElement dashboardVaccinationsEditUrun10;



    // Admin dashboard Vaccinations sayfası Vaccination listesi Delete Butonu
    @FindBy(xpath = "//*[@id=\"tableWithSearch\"]/tbody/tr[1]/td[5]")
    public WebElement dashboardVaccinationsDeleteUrun1;

    // Admin dashboard Vaccinations sayfası Vaccination listesi Delete Butonu
    @FindBy(xpath = "//*[@id=\"tableWithSearch\"]/tbody/tr[2]/td[5]")
    public WebElement dashboardVaccinationsDeleteUrun2;

    // Admin dashboard Vaccinations sayfası Vaccination listesi Delete Butonu
    @FindBy(xpath = "//*[@id=\"tableWithSearch\"]/tbody/tr[3]/td[5]")
    public WebElement dashboardVaccinationsDeleteUrun3;

    // Admin dashboard Vaccinations sayfası Vaccination listesi Delete Butonu
    @FindBy(xpath = "//*[@id=\"tableWithSearch\"]/tbody/tr[4]/td[5]")
    public WebElement dashboardVaccinationsDeleteUrun4;

    // Admin dashboard Vaccinations sayfası Vaccination listesi Delete Butonu
    @FindBy(xpath = "//*[@id=\"tableWithSearch\"]/tbody/tr[5]/td[5]")
    public WebElement dashboardVaccinationsDeleteUrun5;

    // Admin dashboard Vaccinations sayfası Vaccination listesi Delete Butonu
    @FindBy(xpath = "//*[@id=\"tableWithSearch\"]/tbody/tr[6]/td[5]")
    public WebElement dashboardVaccinationsDeleteUrun6;

    // Admin dashboard Vaccinations sayfası Vaccination listesi Delete Butonu
    @FindBy(xpath = "//*[@id=\"tableWithSearch\"]/tbody/tr[7]/td[5]")
    public WebElement dashboardVaccinationsDeleteUrun7;

    // Admin dashboard Vaccinations sayfası Vaccination listesi Delete Butonu
    @FindBy(xpath = "//*[@id=\"tableWithSearch\"]/tbody/tr[8]/td[5]")
    public WebElement dashboardVaccinationsDeleteUrun8;

    // Admin dashboard Vaccinations sayfası Vaccination listesi Delete Butonu
    @FindBy(xpath = "//*[@id=\"tableWithSearch\"]/tbody/tr[9]/td[5]")
    public WebElement dashboardVaccinationsDeleteUrun9;

    // Admin dashboard Vaccinations sayfası Vaccination listesi Delete Butonu
    @FindBy(xpath = "//*[@id=\"tableWithSearch\"]/tbody/tr[10]/td[5]")
    public WebElement dashboardVaccinationsDeleteUrun10;

    //Admin dashboard vaccination ikonu
    @FindBy (xpath = "(//*[@class='bg-complete icon-thumbnail'])[11]")
    public WebElement sideBarDashboardvaccinationsIcon;

    //Admin dashboard vaccination arama kutusu
    @FindBy(xpath = "//input[@id='search-table']")
    public WebElement vaccinationsAramaKutusu;

    //Admin dashboard vaccination edit sayfası Dont Change Image checkbox
    @FindBy( xpath = "//input[@type='checkbox']")
    public WebElement editDontChangeImageCheckBox;



}

