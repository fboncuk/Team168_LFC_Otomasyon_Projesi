package pages.LcfHomePage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class HomeBodyPage {

    public HomeBodyPage(){

        PageFactory.initElements(Driver.getDriver(),this);
    }

    // ANA ARAMA (SEARCH) BÖLÜMÜ

    // Anasayfanın ortasında yer alan ana arama metin kutusu
    @FindBy(xpath = "//input[@name='search']")
    public WebElement bodyMainSearchBox;

    // Arama kutusunun yanındaki turuncu "Search" butonu
    @FindBy(xpath = "//input[@value='Search']")
    public WebElement bodyMainSearchButton;

    //DİĞER BODY ELEMENTİ

    // Sayfanın sağ alt köşesine sabitlenmiş, tıklandığında en başa kaydıran "Yukarı Çık" butonu
    @FindBy(id = "toTop")
    public WebElement bodyScrollToTopButton;


    // DEPARTMENTS LİNKLERİ

    // Anasayfa gövdesindeki ana "Departments" başlığı (Görünürlük testi)
    @FindBy(xpath = "//h2[text()='Departments']")
    public WebElement bodyDepartmentsSectionTitle;

    // "Departments" başlığının sağında yer alan ve tüm başlık altındaki bölümlere giden tıklanabilir link
    @FindBy(xpath = "//div[@class='main_title_3']//a[text()='Departments']")
    public WebElement bodyDepartmentsSectionLink;

    @FindBy(xpath = "//h3[text()='Wellness']")
    public WebElement bodyDepartmentWellnessLink;

    @FindBy(xpath = "//h3[text()='Dental Care']")
    public WebElement bodyDepartmentDentalCareLink;

    @FindBy(xpath = "//h3[text()='Anaesthesia']")
    public WebElement bodyDepartmentAnaesthesiaLink;

    @FindBy(xpath = "//h3[text()='Dermatology']")
    public WebElement bodyDepartmentDermatologyLink;

    @FindBy(xpath = "//h3[text()='Diagnostics']")
    public WebElement bodyDepartmentDiagnosticsLink;

    @FindBy(xpath = "//h3[text()='Vaccinations']")
    public WebElement bodyDepartmentVaccinationsLink;

    @FindBy(xpath = "//h3[text()='Pain Control']")
    public WebElement bodyDepartmentPainControlLink;

    @FindBy(xpath = "//h3[text()='Boarding']")
    public WebElement bodyDepartmentBoardingLink;


   //  POPULAR DOCTORS LİNKLERİ

    // Anasayfa gövdesindeki ana "Popular Doctors" başlığı (Görünürlük testi)
    @FindBy(xpath = "//h2[text()='Popular Doctors']")
    public WebElement bodyDoctorsSectionTitle;

    // "Popular Doctors" başlığının sağında yer alan ve tüm doktorlara giden tıklanabilir link
    @FindBy(xpath = "//div[@class='main_title_3']//a[text()='Doctors']")
    public WebElement bodyDoctorsSectionLink;

    @FindBy(xpath = "//h3[text()='Dr. Alejandro Martinez']")
    public WebElement bodyDoctorAlejandroLink;

    @FindBy(xpath = "//h3[text()='Dr. Marcus Rodriguez']")
    public WebElement bodyDoctorMarcusLink;

    @FindBy(xpath = "//h3[text()='Dr. Olivia Bennett']")
    public WebElement bodyDoctorOliviaLink;

    @FindBy(xpath = "//h3[text()='Dr. Emily Chang']")
    public WebElement bodyDoctorEmilyLink;

    @FindBy(xpath = "//h3[text()='Dr. Nathan Patel']")
    public WebElement bodyDoctorNathanLink;

    @FindBy(xpath = "//h3[text()='Dr. Isabella Wong']")
    public WebElement bodyDoctorIsabellaLink;

    // Kesme işaretinin sorun yaratmaması için contains kullandım. (Dr. Liam O'Connor)
    @FindBy(xpath = "//h3[contains(text(), 'Dr. Liam')]")
    public WebElement bodyDoctorLiamLink;

    @FindBy(xpath = "//h3[text()='Dr. Sophia Kim']")
    public WebElement bodyDoctorSophiaLink;

    @FindBy(xpath = "//h3[text()='Dr. Paula Green']")
    public WebElement bodyDoctorPaulaGreenLink;



   // VACCINATIONS FOR PETS LİNKLERİ

    // Anasayfa gövdesindeki ana "Vaccinations for Pets" başlığı (Görünürlük testi)
    @FindBy(xpath = "//h2[text()='Vaccinations for Pets']")
    public WebElement bodyVaccinationsSectionTitle;

    // "Vaccinations for Pets" başlığının sağında yer alan ve tüm aşılara giden tıklanabilir link
    @FindBy(xpath = "//div[@class='main_title_3']//a[text()='Vaccinations']")
    public WebElement bodyVaccinationsSectionLink;

    @FindBy(xpath = "//h3[text()='Rabies Vaccine']")
    public WebElement bodyVaccinationRabiesLink;

    // İsim çok uzun veya parantezli olduğu için bazı yerlerde contains kullandım.
    @FindBy(xpath = "//h3[contains(text(), 'DHPP Vaccine')]")
    public WebElement bodyVaccinationDHPPLink;

    @FindBy(xpath = "//h3[text()='Feline Leukemia Vaccine']")
    public WebElement bodyVaccinationLeukemiaLink;

    @FindBy(xpath = "//h3[contains(text(), 'Immunodeficiency')]")
    public WebElement bodyVaccinationFIVLink;

    @FindBy(xpath = "//h3[contains(text(), 'Bordetella')]")
    public WebElement bodyVaccinationBordetellaLink;

    @FindBy(xpath = "//h3[text()='Feline Panleukopenia Vaccine']")
    public WebElement bodyVaccinationPanleukopeniaLink;

    @FindBy(xpath = "//h3[text()='Feline Herpesvirus Vaccine']")
    public WebElement bodyVaccinationHerpesvirusLink;

    @FindBy(xpath = "//h3[contains(text(), 'Surgical Procedure')]")
    public WebElement bodyVaccinationSurgicalLink;


    






}

