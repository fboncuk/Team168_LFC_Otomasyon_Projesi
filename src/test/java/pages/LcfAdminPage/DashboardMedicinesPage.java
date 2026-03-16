package pages.LcfAdminPage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class DashboardMedicinesPage {

    public DashboardMedicinesPage(){

        PageFactory.initElements(Driver.getDriver(),this);
    }

    // Sol menüde yer alan "Medicines" ana menü (Açılır menü için)
    @FindBy(xpath = "//span[@class='title' and text()='Medicines']")
    public WebElement sidebarMedicinesMainMenu;

    // Ana menüye tıkladıktan sonra açılan alt menüdeki "Medicines" linki
    @FindBy(xpath = "//ul[@class='sub-menu']//a[contains(text(), 'Medicines')]")
    public WebElement sidebarMedicinesSubMenuLink;

    // Ana menüye tıkladıktan sonra açılan alt menüdeki "Create Medicines" linki
    @FindBy(xpath = "//ul[@class='sub-menu']//a[contains(text(), 'Create Medicines')]")
    public WebElement sidebarCreateMedicinesSubMenuLink;

    // İlaç eklerken ilacın adının girildiği "Title" veri kutusu
    @FindBy(xpath = "//input[@id='Title_en']")
    public WebElement medicinesPageTitleInput;

    // İlaç eklerken ilacın açıklamasının yazıldığı "Content" metin kutusu
    @FindBy(xpath = "//input[@id='body_en']")
    public WebElement medicinesPageContentTextMessage;

    // İlacın dosyasının/fotoğrafının yüklendiği "File Drop Box" alanı
    @FindBy(xpath = "//form[@id='dropzone']")
    public WebElement medicinesPageFileDropBox;

    // Girilen ilaç bilgilerini sisteme kaydetmek için kullanılan "Save Medicines" butonu
    @FindBy(xpath = "//span[text()='Save Medicines']")
    public WebElement medicinesPageSaveButton;

    // Medicines sayfasındaki arama kutusunu temsil eder
    @FindBy(id = "search-table")
    public WebElement medicineSearchBox;

    // Medicines sayfasındaki tablonun tüm veri satırlarını temsil eder
    @FindBy(xpath = "//tbody/tr[@role='row']")
    public List<WebElement> allMedicineRows;

    // Tablodaki ilaçlara ait küçük görsellerin listesidir
    @FindBy(xpath = "//tr[@role='row']//img")
    public List<WebElement> allMedicineImages;

    // İlaç isimlerinin bulunduğu sütundaki elementleri döndürür
    @FindBy(xpath = "//tr[@role='row']/td[2]/p")
    public List<WebElement> allMedicineTitles;

    // İlaçların detaylı açıklama metinlerini içeren hücrelerdir
    @FindBy(xpath = "//tr[@role='row']/td[3]")
    public List<WebElement> allMedicineContents;

    // Her bir ilaç satırında bulunan 'Edit' butonlarının listesidir
    @FindBy(xpath = "//*[@class='btn btn-complete btn-cons btn-animated from-left fa fa-edit']")
    public List<WebElement> allEditButtons;

    // Her bir ilaç satırında bulunan 'Delete' butonlarının listesidir
    @FindBy(xpath = "//*[@class='btn btn-danger btn-cons btn-animated from-top fa  fa-remove']")
    public List<WebElement> allDeleteButtons;

    // İlaç düzenleme sayfasındaki "Save" butonu
    @FindBy(xpath = "//*[@class='btn btn-success btn-cons btn-animated from-left fa fa-save pull-right']")
    public WebElement medicineEditSaveButton;

    // İlaç düzenleme sayfasındaki "France Language" butonu
    @FindBy(xpath = "//*[.=' France language']")
    public WebElement medicineEditFranceLanguageButton;

    // İlaç düzenleme sayfasındaki "France Title" içeriği
    @FindBy(xpath = "//*[@name='Title_fr']")
    public WebElement medicineEditFranceLanguageButtonTitle;

    // İlaç düzenleme sayfasındaki "France Content" içeriği
    @FindBy(xpath = "//*[@id=\"France\"]/div[2]/textarea")
    public WebElement medicineEditFranceLanguageButtonContent;

    // İlaç düzenleme sayfasındaki "Arabic Language" butonu
    @FindBy(xpath = "//*[.=' Arabic language']")
    public WebElement medicineEditArabicLanguageButton;

    // İlaç düzenleme sayfasındaki "Arabic Title" içeriği
    @FindBy(xpath = "//*[@name='Title_ar']")
    public WebElement medicineEditArabicLanguageButtonTitle;

    // İlaç düzenleme sayfasındaki "Arabic Content" içeriği
    @FindBy(xpath = "//*[@id=\"Arabic\"]/div[2]/textarea")
    public WebElement medicineEditArabicLanguageButtonContent;
















}

