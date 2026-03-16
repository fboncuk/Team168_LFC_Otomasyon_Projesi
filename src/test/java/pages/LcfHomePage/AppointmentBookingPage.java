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

    // Önce kutuyu açmak için
    @FindBy(xpath = "//span[text()='Wellness']")
    public WebElement departmentDropdownKutusu;

    // Listeden seçim yapmak için
    @FindBy(xpath = "//li[text()='Dermatology']")
    public WebElement dermatologySecenegi;


    // 4. Doktor Seçimi (Doctor Dropdown)

    // Önce kutuyu açmak için
    @FindBy(xpath = "//span[contains(text(),'Dr. Alejandro')]")
    public WebElement doctorDropdownKutusu;

    // Listeden doktor seçmek için
    @FindBy(xpath = "//li[text()='Dr. Marcus Rodriguez']")
    public WebElement doktorSecenegi;


    // 5. Mesaj Kutusu (Message Box)
    @FindBy(name = "problem")
    public WebElement messageBox;

    // 6. Randevu Al Butonu (Submit Button)
    @FindBy(id = "submit-contact-detail")
    public WebElement appointmentBookingButton;


    // Randevu oluşturulduğuna dair alert mesajı
    // Congratulations on your well-deserved success.
    @FindBy(xpath = "//div[contains(@class,'alert-success')]")
    public WebElement randevuAlertMesaji;

    //Appointment formunun tamamı
    @FindBy (xpath = "//*[@class='box_detail booking']")
    public WebElement appointmentFormContainer;



}

