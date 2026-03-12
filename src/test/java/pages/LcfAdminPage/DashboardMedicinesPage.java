package pages.LcfAdminPage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class DashboardMedicinesPage {

    public DashboardMedicinesPage(){

        PageFactory.initElements(Driver.getDriver(),this);
    }

    // Sol menüde yer alan "Medicines" ana menü (Açılır menü için)
    @FindBy(xpath = "//span[@class='title' and text()='Medicines']")
    public WebElement sidebarMedicinesMainMenu;

    // Ana menüye tıkladıktan sonra açılan alt menüdeki "Medicines" linki
    @FindBy(xpath = "//ul[@class='sub-menu']//a[contains(text(), 'Medicines')]")
    public WebElement sidebarMedicinesSubMenuLink;

    // Ana menüye tıkladıktan sonra açılan alt menüdeki "Create Medicines" linki
    @FindBy(xpath = "//ul[@class='sub-menu']//a[contains(text(), 'Create Medicines')]")
    public WebElement sidebarCreateMedicinesSubMenuLink;

    // İlaç eklerken ilacın adının girildiği "Title" veri kutusu
    @FindBy(xpath = "//input[@id='Title_en']")
    public WebElement medicinesPageTitleInput;

    // İlaç eklerken ilacın açıklamasının yazıldığı "Content" metin kutusu
    @FindBy(xpath = "//input[@id='body_en']")
    public WebElement medicinesPageContentTextMessage;

    // İlacın dosyasının/fotoğrafının yüklendiği "File Drop Box" alanı
    @FindBy(xpath = "//form[@id='dropzone']")
    public WebElement medicinesPageFileDropBox;

    // Girilen ilaç bilgilerini sisteme kaydetmek için kullanılan "Save Medicines" butonu
    @FindBy(xpath = "//span[text()='Save Medicines']")
    public WebElement medicinesPageSaveButton;













}

