package pages.LcfAdminPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class AdminBodyPage {

    public AdminBodyPage(){

        PageFactory.initElements(Driver.getDriver(),this);
    }

    // Admin ana sayfasındaki "Users" kartı içerisinde yer alan yönlendirme linki
    @FindBy(xpath = "//*[text()='Learn More at Users']")
    public WebElement learnMoreAtUsersLink;

    // Admin ana sayfasındaki "Messages" kartı içerisinde yer alan yönlendirme linki
    @FindBy(xpath = "//*[text()='Learn More at Messages']")
    public WebElement learnMoreAtMessagesLink;

    // Admin ana sayfasındaki "Roles" kartı içerisinde yer alan yönlendirme linki
    @FindBy(xpath = "//*[text()='Learn More at Roles']")
    public WebElement learnMoreAtRolesLink;

    // Admin ana sayfasındaki "Settings" kartı içerisinde yer alan yönlendirme linki
    @FindBy(xpath = "//*[text()='Learn More at Settings']")
    public WebElement learnMoreAtSettingsLink;

    // Admin ana sayfasındaki "Google Advertisement" kartı içerisinde yer alan yönlendirme linki
    @FindBy(xpath = "//*[text()='Learn More at Google Advertisement']")
    public WebElement learnMoreAtGoogleAdvertisementLink;

    // Sayfanın alt kısmındaki Facebook ikonuna tıklar ve Facebook sayfasına yönlendirir
    @FindBy(xpath = "//*[@class='fab fa-facebook-square']")
    public WebElement facebookIconLink;

    // Sayfanın alt kısmındaki Twitter ikonuna tıklar ve Facebook sayfasına yönlendirir
    @FindBy(xpath = "//*[@class='fab fa-twitter-square']")
    public WebElement twitterIconLink;

    // Sayfanın alt kısmındaki Pinterest ikonuna tıklar ve Facebook sayfasına yönlendirir
    @FindBy(xpath = "//*[@class='fab fa-pinterest-square']")
    public WebElement pinterestIconLink;

    // Sayfanın alt kısmındaki Instagram ikonuna tıklar ve Facebook sayfasına yönlendirir
    @FindBy(xpath = "//*[@class='fab fa-instagram-square']")
    public WebElement instagramIconLink;

    // Sayfanın alt kısmındaki Linkedin ikonuna tıklar ve Facebook sayfasına yönlendirir
    @FindBy(xpath = "//*[@class='fab fa-linkedin-square']")
    public WebElement linkedinIconLink;

    // Sayfanın alt kısmındaki Tumblr ikonuna tıklar ve Facebook sayfasına yönlendirir
    @FindBy(xpath = "//*[@class='fab fa-tumblr-square']")
    public WebElement tumblrIconLink;

    // Sayfanın alt kısmındaki Github ikonuna tıklar ve Facebook sayfasına yönlendirir
    @FindBy(xpath = "//*[@class='fab fa-github']")
    public WebElement githubIconLink;

    // Sağ üstteki profil ikonu; tıklandığında Settings, Edit Profile ve Logout seçeneklerini açar
    @FindBy(className = "profile-dropdown-toggle")
    public WebElement profileDropdownButon;

    // Profil dropdown menüsü açıldıktan sonra görünen "Settings" seçeneği
    @FindBy(xpath = "//*[@class='dropdown-item'][1]")
    public WebElement profileSettingsOption;

    // Profil dropdown menüsü açıldıktan sonra görünen "Edit Profile" seçeneği
    @FindBy(xpath = "//*[@class='dropdown-item'][2]")
    public WebElement profileEditProfileOption;

    // Profil dropdown menüsü açıldıktan sonra görünen "Logout" seçeneği
    @FindBy(xpath = "//*[.='Logout']")
    public WebElement profileLogoutOption;

















}

