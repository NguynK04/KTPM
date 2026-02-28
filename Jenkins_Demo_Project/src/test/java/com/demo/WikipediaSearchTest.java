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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void testWikiSearch() throws InterruptedException {
        System.out.println(">>> [START] Bat dau mo Wikipedia...");
        
        // 1. Vào trang Bách khoa toàn thư Wikipedia
        driver.get("https://vi.wikipedia.org/");

        // 2. Tìm ô tìm kiếm của Wiki (Tên thuộc tính name của nó là 'search')
        WebElement searchBox = driver.findElement(By.name("search"));

        // 3. Tự động gõ từ khóa "Jenkins"
        String keyword = "Jenkins";
        System.out.println(">>> [INFO] Dang go tu khoa: " + keyword);
        searchBox.sendKeys(keyword);

        // 4. Bấm Enter
        searchBox.sendKeys(Keys.RETURN);

        // Ngủ 4 giây để thầy cô xem kết quả tìm kiếm
        Thread.sleep(4000);

        // 5. Kiểm tra tiêu đề trang kết quả xem có chữ Jenkins không
        String title = driver.getTitle();
        System.out.println(">>> [INFO] Tieu de trang web la: " + title);
        assertTrue(title.contains("Jenkins"));
        
        System.out.println(">>> [PASS] Test Wiki thanh cong ruc ro!");
    }

    @AfterEach
    public void teardown() {
        if (driver != null) {
            driver.quit(); // Đóng trình duyệt
        }
    }
}