package pages.LcfHomePage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class DoctorsMainPage {

    public DoctorsMainPage(){

        PageFactory.initElements(Driver.getDriver(),this);
    }

    // Aşağıdaki locator ornektir
    @FindBy(id = "global-search")
    public WebElement aramaKutusu;

    //Home header bölümü medicines linki
    @FindBy (xpath = "(//a[.='Medicines'])[3]")
    public WebElement HomePageDdmMedicinesLink;

    //Medicines dropdownmenüsü Rimadly ilacı linki
    @FindBy (xpath = "(//a[contains(., 'Rimadyl')])[2]")
    public WebElement HomePageDdmRimadlyLink;

    //Medicines dropdownmenüsü Revolution ilacı linki
    @FindBy (xpath ="(//a[contains(., 'Revolution')])[4]" )
    public WebElement HomePageDdmRevolutionLink;

    //Medicines dropdownmenüsü Baytril ilacı linki
    @FindBy (xpath ="(//a[contains(., 'Baytril')])[2]" )
    public WebElement HomePageDdmBaytrilLink;

    //Medicines dropdownmenüsü Apoquel ilacı linki
    @FindBy (xpath ="(//a[contains(., 'Apoquel')])[2]" )
    public WebElement HomePageDdmApoquelLink;

    //Medicines dropdownmenüsü Metacam ilacı linki
    @FindBy (xpath ="(//a[contains(., 'Metacam')])[2]" )
    public WebElement HomePageDdmMetacamLink;

    //Medicines dropdown menüdeki tüm ilaçları içeren liste
    @FindBy (xpath = "//li[.//a[text()='Medicines']]//ul/li/a")
    public WebElement HomePageDdmMedicineSubLinksList;

//===================================================================================

    //İlaç detay sayfasında Rimadly ilacı için başlık linki
    @FindBy (xpath = "(//h1[contains(., 'Rimadyl')])[2]")
    public WebElement medicinesRimadlyTitle;

    //İlaç detay sayfasında Revolution ilacı için başlık linki
    @FindBy (xpath = "(//h1[contains(., 'Revolution')])[2]")
    public WebElement medicinesRevolutionTitle;

    //İlaç detay sayfasında Baytril ilacı için başlık linki
    @FindBy (xpath = "(//h1[contains(., 'Baytril')])[2]")
    public WebElement medicinesBaytrilTitle;

    //İlaç detay sayfasında Apoquel ilacı için başlık linki
    @FindBy (xpath = "(//h1[contains(., 'Apoquel')])[2]")
    public WebElement medicinesApoquelTitle;

    //İlaç detay sayfasında Metacam ilacı için başlık linki
    @FindBy (xpath = "(//h1[contains(., 'Metacam')])[2]")
    public WebElement medicinesMetacamTitle;

//===================================================================================
    /*
    İlaç detaylarının text locator'larının aynı olması sayfa yapısından kaynaklı.
    Herhangi bir hata yok
     */

    //İlaç detay sayfasında Rimadly ilacı için bilgiler linki
    @FindBy (xpath = "//section[div[@class='detail_title_1']]/p")
    public WebElement medicinesRimadlyDetailText;

    //İlaç detay sayfasında Revolution ilacı için bilgiler linki
    @FindBy (xpath = "//section[div[@class='detail_title_1']]/p")
    public WebElement medicinesRevolutionDetailText;

    //İlaç detay sayfasında Baytril ilacı için bilgiler linki
    @FindBy (xpath = "//section[div[@class='detail_title_1']]/p")
    public WebElement medicinesBaytrilDetailText;

    //İlaç detay sayfasında Apoquel ilacı için bilgiler linki
    @FindBy (xpath = "//section[div[@class='detail_title_1']]/p")
    public WebElement medicinesApoquelDetailText;

    //İlaç detay sayfasında Metacam ilacı için bilgiler linki
    @FindBy (xpath = "//section[div[@class='detail_title_1']]/p")
    public WebElement medicinesMetacamDetailText;

//===================================================================================
    //Medicines sayfası sidebar bölümündeki Rimadly ilacı için link
    @FindBy (xpath = "(//a[contains(., 'Rimadyl')])[2]")
    public WebElement medicinesRimadlySideBarLink;

    //Medicines sayfası sidebar bölümündeki Revolution ilacı için link
    @FindBy (xpath ="(//a[contains(., 'Revolution')])[2]" )
    public WebElement medicinesRevolutionSideBarLink;

    //Medicines sayfası sidebar bölümündeki Baytril ilacı için link
    @FindBy (xpath ="(//a[contains(., 'Baytril')])[2]" )
    public WebElement medicinesBaytrilSideBarLink;

    //Medicines sayfası sidebar bölümündeki Apoquel ilacı için link
    @FindBy (xpath ="(//a[contains(., 'Apoquel')])[2]" )
    public WebElement medicinesApoquelSideBarLink;

    //Medicines sayfası sidebar bölümündeki Metacam ilacı için link
    @FindBy (xpath ="(//a[contains(., 'Metacam')])[2]" )
    public WebElement medicinesMetacamSideBarLink;

//===================================================================================

    //Medicines sayfası body bölümündeki Rimadly ilacı linki
    @FindBy (xpath = "(//a[contains(., 'Rimadyl')])[3]")
    public WebElement medicinesBodyRimadlyLink;

    //Medicines sayfası body bölümündeki Revolution ilacı linki

    @FindBy (xpath ="(//a[contains(., 'Revolution')])[3]" )
    public WebElement medicinesBodyRevolutionLink;

    //Medicines sayfası body bölümündeki Baytril ilacı linki

    @FindBy (xpath ="(//a[contains(., 'Baytril')])[3]" )
    public WebElement medicinesBodyBaytrilLink;

    //Medicines sayfası body bölümündeki Apoquel ilacı linki

    @FindBy (xpath ="(//a[contains(., 'Apoquel')])[3]" )
    public WebElement medicinesBodyApoquelLink;

    //Medicines sayfası body bölümündeki Metacam ilacı linki
    @FindBy (xpath ="(//a[contains(., 'Metacam')])[3]" )
    public WebElement medicinesBodyMetacamLink;













}

