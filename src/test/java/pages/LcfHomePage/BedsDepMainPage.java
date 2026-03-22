package pages.LcfHomePage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class BedsDepMainPage {

    public BedsDepMainPage(){

        PageFactory.initElements(Driver.getDriver(),this);
    }

    // Aşağıdaki locator ornektir
    @FindBy(id = "global-search")
    public WebElement aramaKutusu;

    // Anasayfa Header alanında yer alan Departments Butonu
    @FindBy(xpath = "(//a[@href='https://qa.loyalfriendcare.com/Departments'])[3]")
    public WebElement DepartmentsMainButton;

    // Anasayfa Header alanında yer alan Departments Butonu başlığının altında yer alan Wellness
    @FindBy(xpath = "(//a[@href='https://qa.loyalfriendcare.com/Departments/wellness'])[4]")
    public WebElement WellnesLinkButton;

    // Anasayfa Header alanında yer alan Departments Butonu başlığının altında yer alan Wellness başlığı altında bulunan Wellness Room başlığı
    @FindBy(xpath = "//img[@alt='Wellness Room']")
    public WebElement WellnessRoomLink;

    // Anasayfa Header alanında yer alan Departments Butonu başlığının altında yer alan Dental Care
    @FindBy(xpath = "(//a[@href='https://qa.loyalfriendcare.com/Departments/dental-care'])[4]")
    public WebElement DentalCareLinkButton;

    // Anasayfa Header alanında yer alan Departments Butonu başlığının altında yer alan Dental Care başlığı altında bulunan Dental Care Room başlığı
    @FindBy(xpath = "//img[@alt='Dental Care Room']")
    public WebElement DentalCareRoomLink;

    // Anasayfa Header alanında yer alan Departments Butonu başlığının altında yer alan Dental Care başlığı altında bulunan Intensive Care Room başlığı
    @FindBy(xpath = "//img[@alt='Intensive Care Room']")
    public WebElement IntensiveCareRoomLink;

    // Anasayfa Header alanında yer alan Departments Butonu başlığının altında yer alan Anaesthesia
    @FindBy(xpath = "(//a[@href='https://qa.loyalfriendcare.com/Departments/anaesthesia'])[6]")
    public WebElement AnaesthesiaLinkButton;

    // Anasayfa Header alanında yer alan Departments Butonu başlığının altında yer alan Anaesthesia başlığı altında bulunan Anaesthesia Room başlığı
    @FindBy(xpath = "//img[@alt='Anaesthesia Room']")
    public WebElement AnaesthesiaRoomLink;

    // Anasayfa Header alanında yer alan Departments Butonu başlığının altında yer alan Dermatology
    @FindBy(xpath = "(//a[@href='https://qa.loyalfriendcare.com/Departments/dermatology'])[6]")
    public WebElement DermatologyLinkButton;

    // Anasayfa Header alanında yer alan Departments Butonu başlığının altında yer alan Dermatology başlığı altında bulunan Dermatology Room başlığı
    @FindBy(xpath = "//img[@alt='Dermatology Room']")
    public WebElement DermatologyRoomLink;

    // Anasayfa Header alanında yer alan Departments Butonu başlığının altında yer alan Diagnostics
    @FindBy(xpath = "(//a[@href='https://qa.loyalfriendcare.com/Departments/diagnostics'])[6]")
    public WebElement DiagnosticsLinkButton;

    // Anasayfa Header alanında yer alan Departments Butonu başlığının altında yer alan Diagnostics başlığı altında bulunan Diagnostics Room başlığı
    @FindBy(xpath = "//img[@alt='Diagnostics Room']")
    public WebElement DiagnosticsRoomLink;

    // Anasayfa Header alanında yer alan Departments Butonu başlığının altında yer alan Vaccinations başlığı altında bulunan Vaccinations Room başlığı
    @FindBy(xpath = "//img[@alt='Vaccinations Room']")
    public WebElement VaccinationsRoomLink;

    // Departman detay sayfasında departman adı (başlık)
    @FindBy(xpath = "//div[contains(@class,'single_facilities')]//h2 | //div[contains(@class,'breadcrumb')]//h2 | //h1")
    public WebElement departmanDetayAdi;

    // Departman detay sayfasında açıklama metni
    @FindBy(xpath = "//div[contains(@class,'single_facilities')]//p | //div[contains(@class,'detail')]//p[1]")
    public WebElement departmanDetayAciklama;

    // Departman detay sayfasında sunulan hizmetler bölümü
    @FindBy(xpath = "//div[contains(@class,'facilities_list')] | //ul[contains(@class,'list')] | //div[contains(@class,'service')]")
    public WebElement departmanDetaySunulanHizmetler;

}

