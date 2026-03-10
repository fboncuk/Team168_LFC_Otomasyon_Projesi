package pages.LcfHomePage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class HomeBodyMedicinesPage {

    public HomeBodyMedicinesPage(){

        PageFactory.initElements(Driver.getDriver(),this);
    }

    // Aşağıdaki locator ornektir
    @FindBy(id = "global-search")
    public WebElement aramaKutusu;


    






}

