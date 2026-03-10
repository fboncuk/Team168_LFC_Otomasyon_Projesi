TEAM168
OTOMASYON PROJESİ KURALLARI/STANDARTLARI
(Automation Guidelines / QA Automation Standards)

Otomasyon projesini herhangi bir çakışma (conflict) olmadan yürütmek ve kod kalitesini korumak maksadıyla, QA Testerlerin aşağıda belirtilen kurallara ve standartlara uyması gerekmektedir.

Test websitesi: https://qa.loyalfriendcare.com/en

Proje kapsamındaki iletişim için Slack kanalı kullanılacaktır.
Bu dokümandaki bilgiler Proje sayfasında Readme dosyasına da kaydedilecektir.

Team Lead 	 : Fatih
Scrum Master : Kerim


1.	FRAMEWORK YAPISI :
      Otomasyon frameworkü, testlerin yazıldığı ortak yapı, kurallar ve araçların birleşimidir. Projedeki kod yazacak tüm kişiler aynı framework’ükullanır.

T168_Otomasyon Projesi için framework bileşenleri aşağıda yer almaktadır. Projede yer alan tüm Tester arkadaşların, yeni projeyi oluştururken bu yazılımları yüklemiş olmaları ve Github hesabı açmış olmaları gerekmektedir.
• IntelliJ IDEA 2025.3.3	→ test geliştirme ortamı
• Java JDK 17				→ Java Geliştirme Kiti
• Selenium Java 4.40		→ web otomasyonu
• TestNG 7.12 				→ test yönetimi
• Apache Maven 5.5.1		→ dependency yönetimi
• GitHub 					→ ortak çalışma alan


2.	TEAM LEAD NE YAPAR :
      a.	Framework mimarisini oluşturur.
      b.	Coding standartlarını belirler.
      c.	Driver class ve ConfigReader oluşturur.
      d.	Test görevlerini dağıtır, organize eder.
      e.	Github koordinasyonunu sağlar.
      f.	Test case’leri takipeder. (devam eden, yapılacak vb)
      g.	Teknik konularda takıma destek sağlar.

3.	SCRUM MASTER NE YAPAR :
      a.	Takımın verimli çalışmasını sağlar ve engelleri kaldırır.
      b.	Takım içi koordinasyonu sağlar.
      c.	Scrum sürecini organize eder.
      d.	Scrum kurallarının ve standartlarının (Bu doküman) uygulanmasını sağlar.
      e.	Takım toplantılarının yapmasını sağlar (Daily meeting, Retrospevtive vb)
      f.	Sprint Planning organize eder.
      g.	GitHub akışını takip eder.
      h.	Sprint sonunda rapor alır.


4.	QA TESTERLER NE YAPAR/NE YAPMAZ :

	a.	Framework ile aynı klasör yapısını kullanır.

	b.	Test kodlarını Test Yapısı Standardı ve Driver Standardına uygun yazar.

	c.	Page class’larında sadece elementler ve page methodları bulundurur. click() veya sendKeys() gibi aksiyon metodlarını test class’ların içine yazar.

	d.	Locator’ları Locator Standardına uygun belirler. Aksi taktirde bazı testler çalışır bazıları çalışmaz, kod okunamaz hale gelir, GitHub’da conflict oluşabilir.

	e.	Testlerinde Test İsimlendirme Standardına uygun isim kullanır. Bu şekilde testler daha kolay okunabilir ve izlenebilir.

	f.	Test kodları içine Url, giriş datası vb değişkenleri yazmaz. Bunun için config.properties dosyasındaki Key-Value yapısını kullanır.

	g.	Github ortak çalışma yöntemine uyar:
		- Direkt main branch'e push yapmaz.
		- Github’da branch açar, orada çalışır, main’de çalışmaz.
		- Sorumlu olduğu TC’ler için locator oluşturur.
		- Test kodlarını yazar.
		- Kodunu push etmeden önce main'den proje kodunu mutlaka pull edip conflict kontrolü yapar.
		- Kodu çalışıyorsa Pull request açar.
		- Team lead kodu inceler, uygunsa merge eder.

5.	FRAMEWORK KLASÖR YAPISI :

Projede yer alan tüm ekip üyelerinin kendi test ortamlarında aynı klasör yapısını kullanması gerekiyor. Bu yapı sayesinde projemiz dağılmaz ve herkes aynı mantıkla çalışmış olur.

Proje ismi şimdilik T168_LFC_Otomasyon_Projesi olarak yazıldı.


T168_LFC_Otomasyon_Projesi
│
├── src
│	├── main
│	│	└── java
│	│
│	│
│	└── test
│		 └── java
│		 │	 └── pages
│		 │	 │	 └── LfcHomePage
│		 │	 │	 └── LfcLoginPage
│		 │	 │	 └── LfcDashboardPage
│		 │	 │	 └── LfcOtherPage
│		 │	 │
│		 │	 └── tests
│		 │	 	 └── US01.java
│		 │	 	 └──  ...
│		 │	 	 └── US06.java
│		 │	 	 └──  ...
│		 │	 	 └── US44.java
│		 │
│		 └── utilities
│				 └── ConfigReader
│				 └── Driver
│				 └── ReusableMethods
│
├── config.properties
└── pom.xml


6.	TEST YAPISI STANDARDI :
      TestNG test class’ları içinde yazılacak kodlar:
      @Test annotation
      test senaryosu
      Assert

src / test / java / tests → altında her bir User Story (US) için bir class olacak şekilde kod yazılacak. Yani her QA Tester, kendisine verilen 4 US için 4 tane class oluşturacak ve yapacağı testler hangi US’ye aitse o class içine yazacak.

Diyelim ki User Story 06 içinde toplam 3 tane TC var.
Bu testler US06 adındaki tek bir class içinde standarda uygun isimle yer alacak.
                Footer Gorunurluk Kontrolu Testi
                Footer Hover Kontrolu Testi
                Footer Buton Islevsellik Kontrolu Testi

Örnek Test kodu:
///////////////////////////////////////////////
package tests;

import org......
import java.util.*;

public class US06 {

    @Test (priority = 0)
    public void US06_TC01_FooterGorunurlukKontrolu() {
        kod
    }
    @Test (priority = 1)
    public void US06_TC02_FooterHoverKontrolu() {
        kod
    }
    @Test (priority = 2)
    public void US06_TC03_FooterButonIslevsellikKontrolu() {
        kod
    }
}
///////////////////////////////////////////////


7.	DRIVER SİSTEMİ STANDARDI :
      Team lead, browser yönetimi maksadıyla Web Driver Managerı oluşturur. Tüm QA testerler kodlarında aynı driverı kullanır. Yani projede Singleton Driver Pattern konsepti gereği tek driver sistemi olacak.
      Team Lead tarafından “driver” object tanımı zaten yapılmış olacağından, tekrar oluşturulmasına gerek kalmaz. Eğer oluşturulursa hata doğurur.

Hatalı kod yazımı:
                WebDriver driver = new ChromeDriver();
                WebDriver driver = new FirefoxDriver();

Doğru kod yazımı:
                Driver.getDriver();
                Driver.quitDriver();


8.	PAGES YAPISI :
      Kod yazarken kullanacağımız yapı Page Object Model (POM) olacaktır. Page class içine sadece elementler ve page methodları yazacağız. Başlangıç için Pages klasörü altında LoyalFriendCare web sayfasındaki belli başlı sayfaları oluşturacağız. Daha sonra takım içi koordinasyon ile diğer sayfaları ekleriz.
      Örnek:
      │		 │	 └── pages
      │		 │	 	└── LfcHomepage
      │		 │	 	└── LfcLoginpage
      │		 │	 	└── LfcDashboard


9.	LOCATOR STANDARDI
      1. öncelik:	id
      2. öncelik:	name
      3. öncelik:	xpath

Hatalı locator örneği:
                @FindBy(xpath = "( /html/body/div/div/div/div[2]/div[3]/button")
                public WebElement loginButon;

Doğru locator örneği:
                @FindBy(id = "submitlogin")
                public WebElement loginSayfasiSubmitButonu;

10.	TEST İSİMLENDİRME STANDARDI :
       a.	Test isimleri senaryoyu anlatmalı.
       b.	Test ismi unik yani proje içinde tek omalı.
       c.	Hangi User Story’e ait olduğu belli olmalı.
       d.	İsimler okunur, anlaşılır ve testin maksadını açıklar nitelikte olmalı.

Hatalı örnek: 	test1
                login

Standart Format:
UserStoryNumarası + TestCaseNumarsı + camelCaseTestIsmi

Örnekler:
US01_TC01_LoginGecerliEmail
US01_TC02_LoginGecersizEmail
....
US06_TC02_FooterHoverKontrolu()
....
US12_TC01_RezervasyonGecerliTarih
US12_TC02_RezervasyonGecersizTarih
US12_TC11_RezervasyonGecersizTelNo
....
US18_TC03_DoktorEklemeSilme




