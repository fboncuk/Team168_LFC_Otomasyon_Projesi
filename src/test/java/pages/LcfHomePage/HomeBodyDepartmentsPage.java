package pages.LcfHomePage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class HomeBodyDepartmentsPage {

    public HomeBodyDepartmentsPage(){

        PageFactory.initElements(Driver.getDriver(),this);
    }

    // 1. Wellness
    @FindBy(xpath = "//h3[text()='Wellness']")
    public WebElement WelnessDepartments;

    // 2. Dental Care
    @FindBy(xpath = "//h3[text()='Dental Care']")
    public WebElement DentalCare;

    // 3. Anaesthesia
    @FindBy(xpath = "//h3[text()='Anaesthesia']")
    public WebElement Anaesthesia;

    // 4. Dermatology
    @FindBy(xpath = "//h3[text()='Dermatology']")
    public WebElement Dermatology;

    // 5. Diagnostics
    @FindBy(xpath = "//h3[text()='Diagnostics']")
    public WebElement Diagnostics;

    // 6. Vaccinations
    @FindBy(xpath = "//h3[text()='Vaccinations']")
    public WebElement Vaccinations;

    // 7. Pain Control
    @FindBy(xpath = "//h3[text()='Pain Control']")
    public WebElement PainControl;

    // 8. Boarding
    @FindBy(xpath = "//h3[text()='Boarding']")
    public WebElement Boarding;

    




}

