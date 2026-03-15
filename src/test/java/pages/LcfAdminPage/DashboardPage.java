package pages.LcfAdminPage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class DashboardPage {

    public DashboardPage(){

        PageFactory.initElements(Driver.getDriver(),this);
    }

    // Aşağıdaki locator ornektir
    @FindBy(id = "global-search")
    public WebElement aramaKutusu;



    //### Bu Bölümdeki Locateler Dashboard Anasayfa Summary Cards vs Locateleri   ###
    //DashboardPageSummaryCards Users Panel Linki
    @FindBy(xpath = "(//a[@class='text-white'])[1]")
    public WebElement DashboardPageSummaryCardsUsersLink;

    //DashboardPageSummaryCards BedManager Panel Linki
    @FindBy(xpath = "(//a[@class='text-white'])[2]")
    public WebElement DashboardPageSummaryCardsBedManagersLink;

    //DashboardPageSummaryCards Roles Panel Linki
    @FindBy(xpath = "(//a[@class='text-white'])[3]")
    public WebElement DashboardPageSummaryCardsRolesLink;

    //DashboardPageSummaryCards Settings Panel Linki
    @FindBy(xpath = "(//a[@class='text-white'])[4]")
    public WebElement DashboardPageSummaryCardsSettingsLink;

    //DashboardPageSummaryCards Messages Panel Linki
    @FindBy(xpath = "(//a[@class='text-white'])[5]")
    public WebElement DashboardPageSummaryCardsMessagesLink;

    //DashboardPageSummaryCards Google Advertisement Panel Linki
    @FindBy(xpath = "(//a[@class='text-white'])[6]")
    public WebElement DashboardPageSummaryCardsAdvertisementLink;

    //DashboardPageSummaryCards Medicines Panel Linki
    @FindBy(xpath = "(//a[@class='text-white'])[7]")
    public WebElement DashboardPageSummaryCardsMedidinesLink;


    //### Bu Bölümdeki Locateler Dashboard Anasayfa Users Summary Card
    // altındaki sosyal medya ikon Locateleri   ###
    //DashboardPageSummaryCards Facebook Icon
    @FindBy(xpath = "(//*[@*='nav-item'])[1]")
    public WebElement DashboardPageFacebookIcon;

    //DashboardPageSummaryCards Facebook Icon
    @FindBy(xpath = "(//*[@*='nav-item'])[2]")
    public WebElement DashboardPageTwitterIcon;

    //DashboardPageSummaryCards pinterest Icon
    @FindBy(xpath = "(//*[@*='nav-item'])[3]")
    public WebElement DashboardPagePinterestIcon;

    //DashboardPageSummaryCards Instagram Icon
    @FindBy(xpath = "(//*[@*='nav-item'])[4]")
    public WebElement DashboardPageInstagramIcon;

    //DashboardPageSummaryCards Linkedln Icon
    @FindBy(xpath = "(//*[@*='nav-item'])[5]")
    public WebElement DashboardPageLinkedinIcon;

    //DashboardPageSummaryCards Tumblr Icon
    @FindBy(xpath = "(//*[@*='nav-item'])[6]")
    public WebElement DashboardPageTumblrIcon;

    //DashboardPageSummaryCards Github Icon
    @FindBy(xpath = "(//*[@*='nav-item'])[7]")
    public WebElement DashboardPageGithubIcon;


    //### Bu Bölümdeki Locateler Dashboard Anasayfa Summary Cards
    // altındaki Mesaj Kutuları Locateleri   ###
    //DashboardPage social card items
    @FindBy(className = "via")
    public WebElement DashboardPageSocialCardsViaLink;



    //### Bu Bölümdeki Locateler Dashboard Anasayfa Sol Açılır Menü Locateleri   ###
    //DasboardPage Left Menu Dashboard link
    @FindBy (className = "detailed")
    public WebElement DashboardPageLeftMenuDashboardLink;

    //DasboardPage Left Menu Dashboard link Title ile
    @FindBy (xpath = "(//*[@class='title'])[1]")
    public WebElement DashboardPageLeftMenuDashboardLink2;

    //DasboardPage Left Menu Roles link
    @FindBy (xpath = "(//*[@class='title'])[2]")
    public WebElement DashboardPageLeftMenuRolesLink;

    //DasboardPage Left Menu Users link
    @FindBy (xpath = "(//*[@class='title'])[3]")
    public WebElement DashboardPageLeftMenuUsersLink;

    //DasboardPage Left Menu Bad Managers link
    @FindBy (xpath = "(//*[@class='title'])[4]")
    public WebElement DashboardPageLeftMenuBahManagersLink;

    //DasboardPage Left Menu Departments link
    @FindBy (xpath = "(//*[@class='title'])[5]")
    public WebElement DashboardPageLeftMenuDepartmentsLink;

    //DasboardPage Left Menu Doctors link
    @FindBy (xpath = "(//*[@class='title'])[6]")
    public WebElement DashboardPageLeftMenuDoctorsLink;

    //DasboardPage Left Menu Medicines link
    @FindBy (xpath = "(//*[@class='title'])[7]")
    public WebElement DashboardPageLeftMenuMedicinesLink;

    //DasboardPage Left Menu Pets Adsense link
    @FindBy (xpath = "(//*[@class='title'])[8]")
    public WebElement DashboardPageLeftMenuPetAdsenseLink;

    //DasboardPage Left Menu Tickets link
    @FindBy (xpath = "(//*[@class='title'])[12]")
    public WebElement DashboardPageLeftMenuTicketsLink;

    //DasboardPage Left Menu Vaccinations link
    @FindBy (xpath = "(//*[@class='title'])[13]")
    public WebElement DashboardPageLeftMenuVaccinationssLink;



    //### Bu Bölümdeki Locateler Dashboard Anasayfa Sol Açılır
    // Menülere Tıklanınca açılan submenuler Locateleri   ###
    //Dashboard Left Menu -> Roles -> Roles tıklama
    @FindBy(linkText= "Roles")
    public WebElement DashboardPageLeftMenuSubmenuRoles;

    //Dashboard Left Menu -> Roles -> Create Role tıklama
    @FindBy(linkText= "Create Role")
    public WebElement DashboardPageLeftMenuSubmenuCreateRole;

    //Dashboard Left Menu -> Users -> Users tıklama
    @FindBy(linkText= "Users")
    public WebElement DashboardPageLeftMenuSubmenuUsers;

    //Dashboard Left Menu -> Users -> Create User tıklama
    @FindBy(linkText= "Create User")
    public WebElement DashboardPageLeftMenuSubmenuCreateUser;

    //Dashboard Left Menu -> Bed Managers -> Bed Managers tıklama
    @FindBy(linkText= "Bed Managers")
    public WebElement DashboardPageLeftMenuSubmenuBedManagers;

    //Dashboard Left Menu -> Bed Managers -> Creat Bed Managers  tıklama
    @FindBy(linkText= "Create Bed Managers")
    public WebElement DashboardPageLeftMenuSubmenuCreateBedManagers;

    //Dashboard Left Menu -> Departments -> Departments tıklama
    @FindBy(linkText= "Departments")
    public WebElement DashboardPageLeftMenuSubmenuDepartments;

    //Dashboard Left Menu -> Departments -> Create Departments  tıklama
    @FindBy(linkText= "Create Departmentss")
    public WebElement DashboardPageLeftMenuSubmenuCreateDepartments;

    //Dashboard Left Menu -> Doctors -> Doctors tıklama
    @FindBy(linkText= "Doctors")
    public WebElement DashboardPageLeftMenuSubmenuDoctors;

    //Dashboard Left Menu -> Doctors -> Create Doctors  tıklama
    @FindBy(linkText= "Create Doctors")
    public WebElement DashboardPageLeftMenuSubmenuCreateDoctors;

    //Dashboard Left Menu -> Medicines -> Medicines tıklama
    @FindBy(linkText= "Medicines")
    public WebElement DashboardPageLeftMenuSubmenuMedicines;

    //Dashboard Left Menu -> Medicines -> Create Medicines  tıklama
    @FindBy(linkText= "Create Medicines")
    public WebElement DashboardPageLeftMenuSubmenuCreateMedicines;

    //Dashboard Left Menu -> Pets adsense -> Pets adsense tıklama
    @FindBy(linkText= "Pets adsense")
    public WebElement DashboardPageLeftMenuSubmenuPetsAdsense;

    //Dashboard Left Menu -> Pets adsense -> Create Pets adsense  tıklama
    @FindBy(linkText= "Create Pets adsense")
    public WebElement DashboardPageLeftMenuSubmenuCreatePetsAdsense;


    //Dashboard Left Menu -> Tickets tıklama
    @FindBy(linkText= "Tickets")
    public WebElement DashboardPageLeftMenuSubmenuTickets;


    //Dashboard Left Menu -> Vaccinations tıklama
    @FindBy(linkText= "Vaccinations")
    public WebElement DashboardPageLeftMenuSubmenuVaccinations;

    // Admin dashboard'da sol menününün locator'ı
    @FindBy(className = "sidebar-menu")
    public WebElement dashboardPageSideBarMenu;























}

