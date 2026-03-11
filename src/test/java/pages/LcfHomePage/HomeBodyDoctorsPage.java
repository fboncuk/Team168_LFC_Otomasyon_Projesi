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

<<<<<<< Updated upstream
    // Aşağıdaki locator ornektir
    @FindBy(id = "global-search")
    public WebElement aramaKutusu;

    //Anasayfa Header alanında yer alan Doctors Başlığı
    @FindBy(xpath = "(//a[@href='https://qa.loyalfriendcare.com/Doctors'])[3]")
    public WebElement DoctorsButton;

    //Anasayfa Header alanında yer alan Doctors Başlığı altındaki Dr. Alejandro Martinez butonu
    @FindBy(xpath = "(//a[@href='https://qa.loyalfriendcare.com/Doctors/dawood-gay'])[3]")
    public WebElement DrAlejandroMartinezButton;

    //Anasayfa Header alanında yer alan Doctors Başlığı altındaki Dr. Marcus Rodriguez butonu
    @FindBy(xpath = "(//a[@href='https://qa.loyalfriendcare.com/Doctors/miya-hansen'])[3]")
    public WebElement DrMarcusRodriguezButton;

    //Anasayfa Header alanında yer alan Doctors Başlığı altındaki Dr. Olivia Bennett butonu
    @FindBy(xpath = "(//a[@href='https://qa.loyalfriendcare.com/Doctors/liya-pickett'])[3]")
    public WebElement DrOliviaBennetButton;

    //Anasayfa Header alanında yer alan Doctors Başlığı altındaki Dr. Emily Chang butonu
    @FindBy(xpath = "(//a[@href='https://qa.loyalfriendcare.com/Doctors/shawn-beltran'])[3]")
    public WebElement DrEmilyChangButton;

    //Anasayfa Header alanında yer alan Doctors Başlığı altındaki Dr. Nathan Patel butonu
    @FindBy(xpath = " (//a[@href='https://qa.loyalfriendcare.com/Doctors/better-life'])[3]")
    public WebElement DrNathanPatelButton;

    //Anasayfa Header alanında yer alan Doctors Başlığı altındaki Dr. Isabella Wong butonu
    @FindBy(xpath = "(//a[@href='https://qa.loyalfriendcare.com/Doctors/pet-band'])[3]")
    public WebElement DrIsabellaWongButton;

    //Anasayfa Header alanında yer alan Doctors Başlığı altındaki Dr. Liam O'Connor butonu
    @FindBy(xpath = "(//a[@href='https://qa.loyalfriendcare.com/Doctors/rabies-vaccine'])[3]")
    public WebElement DrLiamOConnorButton;

    //Anasayfa Header alanında yer alan Doctors Başlığı altındaki Dr. Sophia Kim butonu
    @FindBy(xpath = "(//a[@href='https://qa.loyalfriendcare.com/Doctors/meows'])[3]")
    public WebElement DrSophiaKimButton;
=======
    // Doktorlar sayfasındaki tüm doktorın kartları
    @FindBy(xpath = "//div[@class='col-md-6']")
    public List<WebElement> allDoctorCards;

    // Doktorlar sayfasındaki tüm doktorların isimleri
    @FindBy(xpath = "//div[@class='wrapper']/h3/a")
    public List<WebElement> allDoctorNames;
  
    // Doktorlar sayfasındaki tüm doktorların bilgileri
    @FindBy(xpath = "//div[@class='wrapper']/p")
    public List<WebElement> allDoctorDetails;
>>>>>>> Stashed changes

    // Doktorlar sayfasındaki tüm doktorların görselleri
    @FindBy(xpath = "//div[@class='strip grid']//img")
    public List<WebElement> allDoctorImages;
  
    //Anasayfa Header alanında yer alan Doctors Başlığı
    @FindBy(xpath = "(//a[@href='https://qa.loyalfriendcare.com/Doctors'])[3]")
    public WebElement DoctorsButton;

    //Anasayfa Header alanında yer alan Doctors Başlığı altındaki Dr. Alejandro Martinez butonu
    @FindBy(xpath = "(//a[@href='https://qa.loyalfriendcare.com/Doctors/dawood-gay'])[3]")
    public WebElement DrAlejandroMartinezButton;

    //Anasayfa Header alanında yer alan Doctors Başlığı altındaki Dr. Marcus Rodriguez butonu
    @FindBy(xpath = "(//a[@href='https://qa.loyalfriendcare.com/Doctors/miya-hansen'])[3]")
    public WebElement DrMarcusRodriguezButton;

    //Anasayfa Header alanında yer alan Doctors Başlığı altındaki Dr. Olivia Bennett butonu
    @FindBy(xpath = "(//a[@href='https://qa.loyalfriendcare.com/Doctors/liya-pickett'])[3]")
    public WebElement DrOliviaBennetButton;

    //Anasayfa Header alanında yer alan Doctors Başlığı altındaki Dr. Emily Chang butonu
    @FindBy(xpath = "(//a[@href='https://qa.loyalfriendcare.com/Doctors/shawn-beltran'])[3]")
    public WebElement DrEmilyChangButton;

    //Anasayfa Header alanında yer alan Doctors Başlığı altındaki Dr. Nathan Patel butonu
    @FindBy(xpath = " (//a[@href='https://qa.loyalfriendcare.com/Doctors/better-life'])[3]")
    public WebElement DrNathanPatelButton;

    //Anasayfa Header alanında yer alan Doctors Başlığı altındaki Dr. Isabella Wong butonu
    @FindBy(xpath = "(//a[@href='https://qa.loyalfriendcare.com/Doctors/pet-band'])[3]")
    public WebElement DrIsabellaWongButton;

    //Anasayfa Header alanında yer alan Doctors Başlığı altındaki Dr. Liam O'Connor butonu
    @FindBy(xpath = "(//a[@href='https://qa.loyalfriendcare.com/Doctors/rabies-vaccine'])[3]")
    public WebElement DrLiamOConnorButton;

    //Anasayfa Header alanında yer alan Doctors Başlığı altındaki Dr. Sophia Kim butonu
    @FindBy(xpath = "(//a[@href='https://qa.loyalfriendcare.com/Doctors/meows'])[3]")
    public WebElement DrSophiaKimButton;



 

}

