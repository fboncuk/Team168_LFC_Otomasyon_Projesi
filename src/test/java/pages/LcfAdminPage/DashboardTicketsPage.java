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












}

