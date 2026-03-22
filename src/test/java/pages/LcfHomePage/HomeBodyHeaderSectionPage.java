package pages.LcfHomePage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class HomeBodyHeaderSectionPage {

    public HomeBodyHeaderSectionPage(){

        PageFactory.initElements(Driver.getDriver(),this);
    }

    // Aşağıdaki locator ornektir
    @FindBy(id = "global-search")
    public WebElement aramaKutusu;

    // Anasayfa Header Alanı (Sadece header etiketini arayacak)
    @FindBy(xpath = "//header")
    public WebElement headerAlani;

//    // Ana Sayfa Logosu
//    @FindBy(xpath = "//img[@alt='logo']")
//    public WebElement siteLogo;

    // Ana Sayfa Logosu (Sitenin gerçek HTML koduna göre güncellendi)
    @FindBy(xpath = "//img[@alt='LoyalFriendCare' and contains(@class, 'logo_normal')]")
    public WebElement siteLogo;


    @FindBy(xpath = "//a[@href='https://qa.loyalfriendcare.com/Doctors']")
    public WebElement doctorsHeaderLink;


    //Header Vaccine dd menu
    @FindBy(xpath = "(//a[text()='Vaccinations'])[4]")
    public WebElement vaccineDdVaccine;

    @FindBy(xpath = "(//a[contains(.,'Feline Viral')])[3]")
    public WebElement vaccineDdFelineViral;

    @FindBy(xpath = "(//a[contains(.,'Surgical')])[3]")
    public WebElement vaccineDdSurgical;

    @FindBy(xpath = "(//a[contains(.,'Feline Herpesvirus')])[3]")
    public WebElement vaccineDdFelineHerpesvirus;

    @FindBy(xpath = "(//a[contains(.,'Feline Panleukopenia')])[3]")
    public WebElement vaccineDdFelinePanleukopenia;

    @FindBy(xpath = "(//a[contains(.,'Bordetella')])[3]")
    public WebElement vaccineDdBordetella;

    @FindBy(xpath = "(//a[contains(.,'Feline Immunodeficiency')])[3]")
    public WebElement vaccineDdFelineImmunodeficiency;

    @FindBy(xpath = "(//a[contains(.,'Feline Leukemia')])[3]")
    public WebElement vaccineDdFelineLeukemia;

    @FindBy(xpath = "(//a[contains(.,'DHPP Vaccine')])[3]")
    public WebElement vaccineDdDHPPVaccine;

}

