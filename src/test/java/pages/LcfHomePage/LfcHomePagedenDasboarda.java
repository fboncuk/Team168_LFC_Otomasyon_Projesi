package pages.LcfHomePage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class LfcHomePagedenDasboarda {

    public LfcHomePagedenDasboarda() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    // Giriş yaptıktan sonra sağ üstte çıkan turuncu Dashboard/Profil butonu
    @FindBy(xpath = "//a[contains(@class,'btn_add')]")
    public WebElement dashboardGirisButonu;
}