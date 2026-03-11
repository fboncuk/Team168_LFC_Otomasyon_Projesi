package pages.LcfHomePage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class HomeBodyDoctorsPage {

    public HomeBodyDoctorsPage(){

        PageFactory.initElements(Driver.getDriver(),this);
    }

    // Aşağıdaki locator ornektir
    @FindBy(id = "global-search")
    public WebElement aramaKutusu;

    // Doktorlar sayfasındaki tüm doktorın kartları
    @FindBy(xpath = "//div[@class='col-md-6']")
    public List<WebElement> allDoctorCards;

    // Doktorlar sayfasındaki tüm doktorların isimleri
    @FindBy(xpath = "//div[@class='wrapper']/h3/a")
    public List<WebElement> allDoctorNames;

    // Doktorlar sayfasındaki tüm doktorların bilgileri
    @FindBy(xpath = "//div[@class='wrapper']/p")
    public List<WebElement> allDoctorDetails;

    // Doktorlar sayfasındaki tüm doktorların görselleri
    @FindBy(xpath = "//div[@class='strip grid']//img")
    public List<WebElement> allDoctorImages;

}

