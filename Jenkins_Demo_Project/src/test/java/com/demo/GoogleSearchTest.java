package com.demo;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GoogleSearchTest {
    WebDriver driver;

    @BeforeEach
    public void setup() {
        // Tự động tải và cài đặt Driver cho Chrome
        WebDriverManager.chromedriver().setup();
        
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        
        // Mở trình duyệt lên (Nếu muốn chạy ngầm thì bỏ comment dòng dưới)
        // options.addArguments("--headless=new"); 
        
        driver = new ChromeDriver(options);
    }

    @Test
    public void testGoogleSearch() throws InterruptedException {
        System.out.println(">>> [START] Bat dau mo Google...");
        
        // 1. Vào trang Google
        driver.get("https://www.google.com");
        
        // Ngủ 2 giây để ông kịp nhìn thấy trình duyệt nó hiện lên
        Thread.sleep(2000);

        String title = driver.getTitle();
        System.out.println(">>> [INFO] Tieu de trang web la: " + title);
        
        // 2. Kiểm tra tiêu đề có chữ Google không
        assertTrue(title.contains("Google"));
        
        System.out.println(">>> [PASS] Test thanh cong! Web da san sang.");
    }

    @AfterEach
    public void teardown() {
        // Tắt trình duyệt sau khi test xong
        if (driver != null) {
            driver.quit();
        }
    }
}