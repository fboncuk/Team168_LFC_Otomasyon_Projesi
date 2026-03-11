package pages.LcfHomePage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class HomeBodySearchBoxPage {

    public HomeBodySearchBoxPage() {

        PageFactory.initElements(Driver.getDriver(), this);
    }
         // Arama kutusu
         @FindBy(xpath = "//*[@class='form-control']")
         public WebElement searchBox;

         // Arama (Büyüteç) butonu
         @FindBy(xpath = "//*[@type='submit']")
         public WebElement searchButton;

         // Arama sonuç başlığı veya sonuç metni (Örn: "Search Results for...")
         @FindBy(xpath = "//*[@class=\"img-fluid\"]")
         public WebElement resultText;

         // Hata mesajı (Özel karakter aramada çıkan uyarı)
         @FindBy(xpath = "//*[contains(text(),'Please enter a search term')]")
         public List<WebElement> errorMessageList;

    }



    








