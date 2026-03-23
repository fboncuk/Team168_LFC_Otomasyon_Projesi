package pages.LcfAdminPage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class DashboardTicketsPage {

    public DashboardTicketsPage(){

        PageFactory.initElements(Driver.getDriver(),this);
    }

    // Aşağıdaki locator ornektir
    @FindBy(id = "global-search")
    public WebElement aramaKutusu;

    // Sidebar header menüsünü açma
    @FindBy(xpath ="//*[@class ='page-sidebar']")
    public WebElement sidebarMenu;

    // Tickets Linki
    @FindBy (xpath = "(//*[.='Tickets'])[2]")
    public WebElement ticketsLink;

    // Randevu olusturulduguna dair bilgi ekrani
    @FindBy (xpath ="(//*[@class = 'card-header clearfix'])[1]")
    public WebElement randevuKayitBilgiEkrani;


    // Olusturulan randevularin siralamasinin kontrol edilmesi
    @FindBy (xpath = "(//div[@data-social='item']//h5)[1]")
    public WebElement ilkRandevuTarihBilgisi;

    // Olusturulan randevunun tarih,mesaj,doktor bilgilerinin goruntulenmesi
    @FindBy (xpath = "(//*[@class ='card social-card share col2 m-t-30 m-b-30 m-r-30'])[1]")
    public WebElement randevuDoktorTarihMesajGoruntuleme;












}

