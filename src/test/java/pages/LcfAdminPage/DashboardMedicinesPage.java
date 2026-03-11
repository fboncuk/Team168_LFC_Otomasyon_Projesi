package pages.LcfAdminPage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class DashboardMedicinesPage {

    public DashboardMedicinesPage(){

        PageFactory.initElements(Driver.getDriver(),this);
    }

    // Sol menüde yer alan "Medicines" sayfasına yönlendirme linki
    @FindBy(xpath = "//span[@class='title' and text()='Medicines']")
    public WebElement sidebarMedicinesLink;

    // İlaçlar sayfasında yeni ilaç eklemek için kullanılan "Create Medicines" butonu
    @FindBy(xpath = "//a[text()='Create Medicines']")
    public WebElement medicinesPageCreateButton;

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

