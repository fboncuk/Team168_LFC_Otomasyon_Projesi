package pages.LcfHomePage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class AppointmentBookingPage {

    public AppointmentBookingPage(){

        PageFactory.initElements(Driver.getDriver(),this);
    }

    // 1. Tarih Seçimi (Date)
    @FindBy(id = "Date")
    public WebElement dateInput;

    // 2. Telefon Numarası (Phone)
    @FindBy(id = "serial")
    public WebElement phoneBox;

    // 3. Departman Seçimi (Department Dropdown)

    // Önce kutuyu açmak için buna tıkla
    @FindBy(xpath = "(//div[@class='nice-select form-control'])[1]")
    public WebElement departmentDropdownKutusu;

    // Açılan listeden Dermatology'ı(Wellness vb. seçilecekse locatedeki kısmı değişin) seçmek için buna tıkla
    @FindBy(xpath = "//ul[@class='list']//li[text()='Dermatology']")
    public WebElement DermatologySecenegi;

    // 4. Doktor Seçimi (Doctor Dropdown)

    // Önce kutuyu açmak için buna tıkla
    @FindBy(xpath = "(//div[@class='nice-select form-control'])[2]")
    public WebElement doctorDropdownKutusu;

    // Açılan listeden doktoru seçmek için buna tıkla
    @FindBy(xpath = "//ul[@class='list']//li[text()='Dr. Alejandro Martinez']")
    public WebElement doktorSecenegi;

    // 5. Mesaj Kutusu (Message Box)
    @FindBy(name = "problem")
    public WebElement messageBox;

    // 6. Randevu Al Butonu (Submit Button)
    @FindBy(id = "submit-contact-detail")
    public WebElement appointmentBookingButton;

    






}

