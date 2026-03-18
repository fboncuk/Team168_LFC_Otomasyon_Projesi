package utilities;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listeners implements ITestListener {

    @Override
    public void onTestSuccess(ITestResult result) {

        // Test başarılıysa: Tarihsiz metod çalışır, eski dosyanın üzerine yazar.
        ReusableMethods.tumSayfaResimCek(Driver.getDriver(),result.getName() + "_Basarili");
        System.out.println("✅ TEST PASSED: " + result.getName());
    }


    @Override
    public void onTestFailure(ITestResult result) {

        // Test hata alırsa: Tarihli metod çalışır, benzersiz hata kaydı oluşturur.
        ReusableMethods.tarihliTumSayfaResimCek(Driver.getDriver(),result.getName() + "_HATA");

        // Hata mesajı
        Throwable error = result.getThrowable();
        System.out.println("❌ TEST FAILED: " + result.getName());
    }
}