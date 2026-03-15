package pages.LcfHomePage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class HomeBodyFooterLinksPage {

    public HomeBodyFooterLinksPage(){

        PageFactory.initElements(Driver.getDriver(),this);
    }

    // Aşağıdaki locator ornektir
    @FindBy(id = "global-search")
    public WebElement aramaKutusu;

    // Anasayfa altındaki Footerde yer alan Departments Title
    @FindBy(xpath = "//h3[@data-target='#collapse_ft_2']")
    public WebElement departmentsTitle;

    // Anasayfa altındaki Footerde yer alan Wellness Link
    @FindBy(xpath = "(//a[@href='https://qa.loyalfriendcare.com/Departments/wellness'])[3]")
    public WebElement departmentsWellnessLink;

    // Anasayfa altındaki Footerde yer alan Dental Care Link
    @FindBy(xpath = "(//a[@href='https://qa.loyalfriendcare.com/Departments/dental-care'])[3]")
    public WebElement departmentsDentalCareLink;

    // Anasayfa altındaki Footerde yer alan Anaesthesia Link
    @FindBy(xpath = "(//a[@href='https://qa.loyalfriendcare.com/Departments/anaesthesia'])[3]")
    public WebElement departmentsAnaesthesiaLink;

    // Anasayfa altındaki Footerde yer alan Dermatology Link
    @FindBy(xpath = "(//a[@href='https://qa.loyalfriendcare.com/Departments/dermatology'])[3]")
    public WebElement departmentsDermatologyLink;

    // Anasayfa altındaki Footerde yer alan Diagnostics Link
    @FindBy(xpath = "(//a[@href='https://qa.loyalfriendcare.com/Departments/diagnostics'])[3]")
    public WebElement departmentsDiagnosticsLink;


    // Anasayfa altındaki Footerde yer alan Follow Us Title
    @FindBy(xpath = "//h3[@data-target='#collapse_ft_4']")
    public WebElement followUsTitle;

    // Anasayfa altındaki Footerde yer alan Facebook linki
    @FindBy(xpath = "//a[i[@class='fab fa-facebook-square']]")
    public WebElement facebookIconLinki;

    // Anasayfa altındaki Footerde yer alan Twitter linki
    @FindBy(xpath = "//a[i[@class='fab fa-twitter-square']]")
    public WebElement twitterIconLinki;

    // Anasayfa altındaki Footerde yer alan Youtube linki
    @FindBy(xpath = "//a[i[@class='fab fa-youtube-square']]")
    public WebElement youtubeIconLinki;

    // Anasayfa altındaki Footerde yer alan Pinterest linki
    @FindBy(xpath = "//a[i[@class='fab fa-pinterest-square']]")
    public WebElement pinterestIconLinki;

    // Anasayfa altındaki Footerde yer alan Instagram linki
    @FindBy(xpath = "//a[i[@class='fab fa-instagram']]")
    public WebElement instagramIconLinki;

    @FindBy(xpath = "//footer//a[i[contains(@class,'fa')]]")
    public List<WebElement> socialMediaLinks;


    // Anasayfa altındaki Footerde yer alan Contacts Title
    @FindBy(xpath = "//h3[@data-target='#collapse_ft_3']")
    public WebElement contactsTitle;

    // Anasayfa altındaki Footerde yer alan Adres metni
    @FindBy(xpath = "(//ul[@class='contacts']/li)[1]")
    public WebElement adressText;

    // Anasayfa altındaki Footerde yer alan Telefon numarası
    @FindBy(xpath = "(//ul[@class='contacts']/li)[2]")
    public WebElement telephoneText;

    // Anasayfa altındaki Footerde yer alan E-mail adresi
    @FindBy(xpath = "(//ul[@class='contacts']/li)[3]")
    public WebElement emailText;


    // Anasayfa altındaki Footerde yer alan Terms and conditions linki
    @FindBy(xpath = "//a[text()='Terms and conditions']")
    public WebElement termsAndConditionLinki;

    // Anasayfa altındaki Footerde yer alan Faq linki
    @FindBy(xpath = "//a[text()='Faq']")
    public WebElement faqLinki;

    // Anasayfa altındaki Footerde yer alan @2023 linki
    @FindBy(xpath = "//a[text()='© 2023']")
    public WebElement add2023Linki;



}

