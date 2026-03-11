package pages.LcfHomePage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class HomeBodyMedicinesPage {

    public HomeBodyMedicinesPage(){

        PageFactory.initElements(Driver.getDriver(),this);
    }

    // Aşağıdaki locator ornektir
    @FindBy(id = "global-search")
    public WebElement aramaKutusu;

    //Homepage header bölümü medicines linki
    @FindBy (xpath = "(//a[.='Medicines'])[3]")
    public WebElement medicinesMenuLink;

    //Medicines dropdownmenüsü Rimadly ilacı linki
    @FindBy (xpath = "(//a[contains(., 'Rimadyl')])[2]")
    public WebElement rimadlyLink;

    //Medicines dropdownmenüsü Revolution ilacı linki
    @FindBy (xpath ="(//a[contains(., 'Revolution')])[4]" )
    public WebElement revolutionLink;

    //Medicines dropdownmenüsü Baytril ilacı linki
    @FindBy (xpath ="(//a[contains(., 'Baytril')])[2]" )
    public WebElement baytrilLink;

    //Medicines dropdownmenüsü Apoquel ilacı linki
    @FindBy (xpath ="(//a[contains(., 'Apoquel')])[2]" )
    public WebElement apoquelLink;

    //Medicines dropdownmenüsü Metacam ilacı linki
    @FindBy (xpath ="(//a[contains(., 'Metacam')])[2]" )
    public WebElement metacamLink;

    //Medicines dropdown menüdeki tüm ilaçları içeren liste
    @FindBy (xpath = "//li[.//a[text()='Medicines']]//ul/li/a")
    public WebElement medicineSubLinksList;

//===================================================================================

    //İlaç detay sayfasında Rimadly ilacı için başlık linki
    @FindBy (xpath = "(//h1[contains(., 'Rimadyl')])[2]")
    public WebElement rimadlyTitle;

    //İlaç detay sayfasında Revolution ilacı için başlık linki
    @FindBy (xpath = "(//h1[contains(., 'Revolution')])[2]")
    public WebElement revolutionTitle;

    //İlaç detay sayfasında Baytril ilacı için başlık linki
    @FindBy (xpath = "(//h1[contains(., 'Baytril')])[2]")
    public WebElement baytrilTitle;

    //İlaç detay sayfasında Apoquel ilacı için başlık linki
    @FindBy (xpath = "(//h1[contains(., 'Apoquel')])[2]")
    public WebElement apoquelTitle;

    //İlaç detay sayfasında Metacam ilacı için başlık linki
    @FindBy (xpath = "(//h1[contains(., 'Metacam')])[2]")
    public WebElement metacamTitle;

//===================================================================================
    /*
    İlaç detaylarının text locator'larının aynı olması sayfa yapısından kaynaklı.
    Herhangi bir hata yok
     */

    //İlaç detay sayfasında Rimadly ilacı için bilgiler linki
    @FindBy (xpath = "//section[div[@class='detail_title_1']]/p")
    public WebElement rimadlyDetailText;

    //İlaç detay sayfasında Revolution ilacı için bilgiler linki
    @FindBy (xpath = "//section[div[@class='detail_title_1']]/p")
    public WebElement revolutionDetailText;

    //İlaç detay sayfasında Baytril ilacı için bilgiler linki
    @FindBy (xpath = "//section[div[@class='detail_title_1']]/p")
    public WebElement baytrilDetailText;

    //İlaç detay sayfasında Apoquel ilacı için bilgiler linki
    @FindBy (xpath = "//section[div[@class='detail_title_1']]/p")
    public WebElement apoquelDetailText;

    //İlaç detay sayfasında Metacam ilacı için bilgiler linki
    @FindBy (xpath = "//section[div[@class='detail_title_1']]/p")
    public WebElement metacamDetailText;

//===================================================================================
    //Medicines sayfası sidebar bölümündeki Rimadly ilacı için link
    @FindBy (xpath = "(//a[contains(., 'Rimadyl')])[2]")
    public WebElement rimadlySideBarLink;

    //Medicines sayfası sidebar bölümündeki Revolution ilacı için link
    @FindBy (xpath ="(//a[contains(., 'Revolution')])[2]" )
    public WebElement revolutionSideBarLink;

    //Medicines sayfası sidebar bölümündeki Baytril ilacı için link
    @FindBy (xpath ="(//a[contains(., 'Baytril')])[2]" )
    public WebElement baytrilSideBarLink;

    //Medicines sayfası sidebar bölümündeki Apoquel ilacı için link
    @FindBy (xpath ="(//a[contains(., 'Apoquel')])[2]" )
    public WebElement apoquelSideBarLink;

    //Medicines sayfası sidebar bölümündeki Metacam ilacı için link
    @FindBy (xpath ="(//a[contains(., 'Metacam')])[2]" )
    public WebElement metacamSideBarLink;

//===================================================================================

    //Medicines sayfası body bölümündeki Rimadly ilacı için detay yazısı
    @FindBy (xpath = "(//a[contains(., 'Rimadyl')])[3]")
    public WebElement rimadlyBodyLink;

    //Medicines sayfası body bölümündeki Revolution ilacı için detay yazısı

    @FindBy (xpath ="(//a[contains(., 'Revolution')])[3]" )
    public WebElement revolutionBodyLink;

    //Medicines sayfası body bölümündeki Baytril ilacı için detay yazısı

    @FindBy (xpath ="(//a[contains(., 'Baytril')])[3]" )
    public WebElement baytrilBodyLink;

    //Medicines sayfası body bölümündeki Apoquel ilacı için detay yazısı

    @FindBy (xpath ="(//a[contains(., 'Apoquel')])[3]" )
    public WebElement apoquelBodyLink;

    //Medicines sayfası body bölümündeki Metacam ilacı için detay yazısı

    @FindBy (xpath ="(//a[contains(., 'Metacam')])[3]" )
    public WebElement metacamSideBodyLink;













}

