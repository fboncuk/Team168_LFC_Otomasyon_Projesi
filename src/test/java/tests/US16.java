package tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LcfHomePage.SignButonsPage;
import utilities.ConfigReader;
import utilities.Driver;

public class US16 {

   // System.out.printf("Hadi BISMILLAH baslasinn >>>>>>>>>");


    //Bir kayıtlı kullanıcı olarak,
    // Home Page sayfasından Doctors
    // sayfasına erişebilmeli, Doctors
    // sayfasındaki doktor bilgilerini
    // inceleyebilmeli ve seçtiğim doktorun
    // sayfasına erişerek o doktordan randevu talebi oluşturabilmeliyim

    SignButonsPage signButonsPage;



    @BeforeClass
    public void setup() {
        Driver.getDriver().get(ConfigReader.getProperty("LcfUrl"));

        signButonsPage=new SignButonsPage();


    }





    @Test

    //Kayıtlı kullanıcının
    //Home Page’den Doctors sayfasına erişimini doğrulama






}

