package com.demo;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WikipediaSearchTest {
    WebDriver driver;

    @BeforeEach
    public void setup() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        
        // CÚ CHỐT NẰM Ở ĐÂY: Bắt bot phóng to toàn màn hình để web không bị giấu ô tìm kiếm
        driver.manage().window().maximize();
        
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void testWikiSearch() throws InterruptedException {
        System.out.println(">>> [START] Bat dau mo Wikipedia...");
        driver.get("https://vi.wikipedia.org/");

        // Đổi cách tìm kiếm: Dùng ID (searchInput) sẽ chuẩn xác 100% hơn là dùng name
        WebElement searchBox = driver.findElement(By.id("searchInput"));

        String keyword = "Jenkins";
        System.out.println(">>> [INFO] Dang go tu khoa: " + keyword);
        searchBox.sendKeys(keyword);
        searchBox.sendKeys(Keys.RETURN);

        // Đợi hẳn 5 giây cho chắc củ
        Thread.sleep(5000);

        String title = driver.getTitle();
        System.out.println(">>> [INFO] Tieu de trang web la: " + title);
        assertTrue(title.contains("Jenkins"));
        
        System.out.println(">>> [PASS] Test Wiki thanh cong ruc ro!");
    }

    @AfterEach
    public void teardown() {
        if (driver != null) {
            driver.quit(); 
        }
    }
}