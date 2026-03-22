package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LcfHomePage.SignButonsPage;
import utilities.ConfigReader;
import utilities.Driver;

import java.time.Duration;
import java.util.List;

public class US27 {
    public WebDriver driver;
    public WebDriverWait wait;
    public final String BASE_URL = "https://qa.loyalfriendcare.com/en";


    @BeforeClass
    public void setUp() {
        Driver.getDriver().get(ConfigReader.getProperty("DasUrl"));

        SignButonsPage signButonsPage = new SignButonsPage();
        signButonsPage.emailKutusu.sendKeys(ConfigReader.getProperty("T05AdminMail"));
        signButonsPage.passwordKutusu.sendKeys(ConfigReader.getProperty("T05AdminPassword"));
        signButonsPage.signInButtonOnay.click();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public void loginAsAdmin() {
        driver.get(BASE_URL + "/login"); // Gerekirse gerçek login path ile değiştir

        // TODO: Gerçek locator’ları uygulamadan al
        WebElement emailInput = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        WebElement passwordInput = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));

        emailInput.clear();
        emailInput.sendKeys("farkhod.admin@loyalfriendcare.com"); // gerçek admin mail
        passwordInput.clear();
        passwordInput.sendKeys("Loyal.123123"); // gerçek admin şifre
        loginButton.click();

        // Dashboard yüklendi mi kontrol
        wait.until(ExpectedConditions.urlContains("/dashboard"));
    }


    public void openBedManagersPageFromMenu() {
        // Sol açılır menüde Bed Managers
        // TODO: Gerçek locator’ı uygulamadan al
        WebElement bedManagersMenu = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Bed Managers']")));
        bedManagersMenu.click();

        // Alt menülerin açıldığını doğrula
        // Örnek: "Bed List" alt menüsü
        WebElement bedListSubMenu = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[normalize-space()='Bed List']")));
        bedListSubMenu.click();

        // Yatak listesi sayfasına yönlendirme
        wait.until(ExpectedConditions.urlContains("/bed-managers"));
    }

    // ---------- TESTLER ----------

    @Test(priority = 1)
    public void adminShouldSeeBedManagersMenuAndSubmenus() {
        loginAsAdmin();

        // Bed Managers menüsü görünür olmalı
        WebElement bedManagersMenu = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Bed Managers']")));
        Assert.assertTrue(bedManagersMenu.isDisplayed(), "Bed Managers menüsü görünmüyor.");

        bedManagersMenu.click();

        // Alt menülerin görüntülenmesi
        WebElement bedListSubMenu = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[normalize-space()='Bed List']")));
        Assert.assertTrue(bedListSubMenu.isDisplayed(), "Bed Managers alt menüsü (Bed List) görünmüyor.");
    }

    @Test(priority = 2)
    public void adminShouldSeeBedListAndSearchFilter() {
        loginAsAdmin();
        openBedManagersPageFromMenu();

        // Mevcut yatak listesi görüntülenmeli
        // TODO: Gerçek tablo locator’ı
        List<WebElement> bedRows = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("table tbody tr")));
        Assert.assertTrue(bedRows.size() > 0, "Yatak listesi boş görünüyor.");

        // Kolonlar: yatak adı/numarası, durum, işlem butonları
        WebElement firstRow = bedRows.get(0);
        WebElement bedNameOrNumber = firstRow.findElement(By.cssSelector("td:nth-child(1)"));
        WebElement status = firstRow.findElement(By.cssSelector("td:nth-child(2)"));
        WebElement viewBtn = firstRow.findElement(By.xpath(".//button[contains(.,'View')]"));
        WebElement editBtn = firstRow.findElement(By.xpath(".//button[contains(.,'Edit')]"));
        WebElement deleteBtn = firstRow.findElement(By.xpath(".//button[contains(.,'Delete')]"));

        Assert.assertTrue(bedNameOrNumber.isDisplayed(), "Yatak adı/numarası görünmüyor.");
        Assert.assertTrue(status.isDisplayed(), "Durum bilgisi görünmüyor.");
        Assert.assertTrue(viewBtn.isDisplayed(), "View butonu görünmüyor.");
        Assert.assertTrue(editBtn.isDisplayed(), "Edit butonu görünmüyor.");
        Assert.assertTrue(deleteBtn.isDisplayed(), "Delete butonu görünmüyor.");

        // Arama alanı
        // TODO: Gerçek arama input locator’ı
        WebElement searchInput = driver.findElement(By.id("bedSearch"));
        Assert.assertTrue(searchInput.isDisplayed(), "Arama alanı görünmüyor.");

        String firstBedText = bedNameOrNumber.getText().trim();
        searchInput.clear();
        searchInput.sendKeys(firstBedText);

        // Filtrelenmiş liste
        wait.until(ExpectedConditions.numberOfElementsToBeLessThan(
                By.cssSelector("table tbody tr"), bedRows.size()));

        List<WebElement> filteredRows = driver.findElements(By.cssSelector("table tbody tr"));
        Assert.assertTrue(filteredRows.size() >= 1, "Filtre sonrası satır bulunamadı.");
        Assert.assertTrue(filteredRows.get(0).getText().contains(firstBedText),
                "Filtrelenen satırda aranan yatak adı/numarası yok.");
    }

    @Test(priority = 3)
    public void adminCanViewBedDetails() {
        loginAsAdmin();
        openBedManagersPageFromMenu();

        List<WebElement> bedRows = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("table tbody tr")));
        WebElement firstRow = bedRows.get(0);
        WebElement viewBtn = firstRow.findElement(By.xpath(".//button[contains(.,'View')]"));
        viewBtn.click();

        // Detay sayfası açılmalı
        // TODO: Gerçek detay sayfası locator’ları
        WebElement bedDetailHeader = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(.,'Bed Details')]")));
        Assert.assertTrue(bedDetailHeader.isDisplayed(), "Yatak detay sayfası açılmadı.");

        WebElement bedNameField = driver.findElement(By.id("bedName"));
        WebElement bedNumberField = driver.findElement(By.id("bedNumber"));
        WebElement statusField = driver.findElement(By.id("bedStatus"));

        Assert.assertTrue(bedNameField.isDisplayed(), "Yatak adı bilgisi görünmüyor.");
        Assert.assertTrue(bedNumberField.isDisplayed(), "Yatak numarası bilgisi görünmüyor.");
        Assert.assertTrue(statusField.isDisplayed(), "Durum bilgisi görünmüyor.");
    }

    @Test(priority = 4)
    public void adminCanEditBed() {
        loginAsAdmin();
        openBedManagersPageFromMenu();

        List<WebElement> bedRows = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("table tbody tr")));
        WebElement firstRow = bedRows.get(0);
        WebElement editBtn = firstRow.findElement(By.xpath(".//button[contains(.,'Edit')]"));
        editBtn.click();

        // Düzenleme formu
        WebElement bedNameInput = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("bedName")));
        WebElement saveButton = driver.findElement(By.xpath("//button[contains(.,'Save')]"));

        String newName = "AutoTestBed_" + System.currentTimeMillis();
        bedNameInput.clear();
        bedNameInput.sendKeys(newName);
        saveButton.click();

        // Başarılı işlem mesajı
        WebElement successMsg = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alert.alert-success")));
        Assert.assertTrue(successMsg.getText().toLowerCase().contains("success"),
                "Başarılı işlem mesajı görünmedi.");

        // Listeye geri dönüp güncellenmiş mi kontrol
        openBedManagersPageFromMenu();
        List<WebElement> rowsAfterUpdate = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("table tbody tr")));
        boolean foundUpdated = rowsAfterUpdate.stream()
                .anyMatch(r -> r.getText().contains(newName));
        Assert.assertTrue(foundUpdated, "Güncellenen yatak adı listede bulunamadı.");
    }

    @Test(priority = 5)
    public void adminCanDeleteBed() {
        loginAsAdmin();
        openBedManagersPageFromMenu();

        List<WebElement> bedRows = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("table tbody tr")));
        int initialCount = bedRows.size();
        WebElement firstRow = bedRows.get(0);
        String bedToDeleteText = firstRow.getText();
        WebElement deleteBtn = firstRow.findElement(By.xpath(".//button[contains(.,'Delete')]"));
        deleteBtn.click();

        // Onay mesajı
        // TODO: Gerçek modal locator’ı
        WebElement confirmModal = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".modal-dialog")));
        WebElement confirmButton = confirmModal.findElement(By.xpath(".//button[contains(.,'Yes')]"));
        confirmButton.click();

        // Başarılı işlem mesajı
        WebElement successMsg = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alert.alert-success")));
        Assert.assertTrue(successMsg.getText().toLowerCase().contains("success"),
                "Silme sonrası başarılı işlem mesajı görünmedi.");

        // Liste güncellenmeli
        wait.until(ExpectedConditions.numberOfElementsToBeLessThan(
                By.cssSelector("table tbody tr"), initialCount));

        List<WebElement> rowsAfterDelete = driver.findElements(By.cssSelector("table tbody tr"));
        boolean stillExists = rowsAfterDelete.stream()
                .anyMatch(r -> r.getText().equals(bedToDeleteText));
        Assert.assertFalse(stillExists, "Silinen yatak listede hala görünüyor.");
    }

    @Test(priority = 6)
    public void nonAdminShouldNotSeeBedManagersMenu() {
       // loginAsNonAdmin();

        // Bed Managers menüsü görünmemeli
        List<WebElement> bedManagersMenus = driver.findElements(
                By.xpath("//span[normalize-space()='Bed Managers']"));
        Assert.assertTrue(bedManagersMenus.isEmpty() || !bedManagersMenus.get(0).isDisplayed(),
                "Admin olmayan kullanıcı Bed Managers menüsünü görüyor.");
    }

    @Test(priority = 7)
    public void unauthorizedUserCannotAccessBedManagersByUrl() {
        //loginAsNonAdmin();

        driver.get(BASE_URL + "/bed-managers"); // gerçek path ile değiştir
        // Erişim engellenmeli: 403 sayfası, hata mesajı veya login’e redirect vb.

        // Örnek: yetkisiz mesajı
        // TODO: Gerçek mesaj/redirect kontrolü
        WebElement errorMsg = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//*[contains(text(),'Unauthorized') or contains(text(),'Access denied') or contains(text(),'Permission')]")
                ));
        Assert.assertTrue(errorMsg.isDisplayed(), "Yetkisiz kullanıcı için erişim engellenmedi gibi görünüyor.");
    }
}






















