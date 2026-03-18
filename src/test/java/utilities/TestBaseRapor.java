package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public abstract class TestBaseRapor {

    protected static ExtentReports extentReports; //extent report'a ilk atamayi yapar
    protected static ExtentSparkReporter extentSparkReporter; // Html raporu duzenler
    protected static ExtentTest extentTest; // test pass veya failed gibi bilgileri kaydeder.
    // Ayrica ekran resmi icin de kullaniriz

    // Test işlemine başlamadan hemen önce (test methodundan önce değil, tüm test işleminden önce)
    // calisir ve rapor icin gerekli atamalari ve ayarlari yapar
    @BeforeTest(alwaysRun = true) // alwaysRun : her zaman çalıştır.
    public void setUpTest() {
        Locale.setDefault(new Locale("en", "US"));
        extentReports = new ExtentReports(); // Raporlamayi baslatir
        //rapor oluştuktan sonra raporunuz nereye eklensin istiyorsanız buraya yazıyorsunuz.
        String date = new SimpleDateFormat("_yyMMdd_HHmmss").format(new Date());
        String filePath = System.getProperty("user.dir") + "/test-output/Rapor"+date+".html";
        //oluşturmak istediğimiz raporu (html formatında) başlatıyoruz,
        // filePath ile dosya yolunu belirliyoruz.
        // date class'i ile raporumuza tarih etiketi ekliyoruz
        extentSparkReporter = new ExtentSparkReporter(filePath);
        extentReports.attachReporter(extentSparkReporter);

        // Raporun kapak sayfasinda gorunmesini istediğiniz bilgileri buraya ekleyebilirsiniz.
        extentReports.setSystemInfo("Environment",ConfigReader.getProperty("environment"));
        extentReports.setSystemInfo("Browser", ConfigReader.getProperty("browser")); // chrome, firefox
        extentReports.setSystemInfo("Automation Engineer", ConfigReader.getProperty("tester_name"));
        extentSparkReporter.config().setDocumentTitle("LoyalFriendCare Test Raporlari");
        extentSparkReporter.config().setReportName("Html Reports");
    }


    // Her test methodundan sonra eğer assertion failed olursa, ekran görüntüsü alıp rapora ekliyor
    @AfterMethod(alwaysRun = true)
    public void tearDownMethod(ITestResult result) throws IOException {

        if (result.getStatus() == ITestResult.FAILURE) { // eğer testin sonucu başarısızsa
            ReusableMethods.waitForPageToLoad(2);
            String resimYolu = ReusableMethods.getScreenshot(result.getName());
            extentTest.fail(result.getName());
            extentTest.addScreenCaptureFromPath(resimYolu);
            extentTest.fail(result.getThrowable());
        } else if (result.getStatus() == ITestResult.SKIP) { // eğer test çalıştırılmadan geçilmezse
            extentTest.skip("Test Case is skipped: " + result.getName()); // Ignore olanlar
        }
    }


    // Raporlandırmayı sonlandırmak icin
    @AfterTest(alwaysRun = true)
    public void tearDownTest() {
        if (extentReports != null) {
            extentReports.flush();
        }
        Driver.quitDriver();
    }
}