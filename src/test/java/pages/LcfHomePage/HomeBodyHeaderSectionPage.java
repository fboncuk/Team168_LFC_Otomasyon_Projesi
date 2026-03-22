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




    






}

