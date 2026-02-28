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

public class GoogleSearchTest {
    WebDriver driver;

    @BeforeEach
    public void setup() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        
        // Dặn con Bot: Nếu mạng chậm, hãy kiên nhẫn đợi tối đa 10 giây để web tải xong
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void testGoogleSearch() throws InterruptedException {
        System.out.println(">>> [START] Bat dau mo Google...");
        driver.get("https://www.google.com");

        // 1. Tìm cái ô nhập chữ của Google (Mã nguồn Google đặt tên ô này là 'q')
        WebElement searchBox = driver.findElement(By.name("q"));

        // 2. Tự động gõ từ khóa vào ô đó
        String keyword = "Kiểm thử tự động Jenkins";
        System.out.println(">>> [INFO] Dang go tu khoa: " + keyword);
        searchBox.sendKeys(keyword);

        // 3. Tự động bấm phím Enter
        searchBox.sendKeys(Keys.RETURN);

        // Giữ lại 3 giây NGỦ Ở CUỐI CÙNG để thầy cô trên lớp kịp nhìn thấy kết quả tìm kiếm
        // (Không có dòng này nó tắt phụt đi nhanh bằng mili-giây, không ai thấy gì)
        Thread.sleep(3000);

        // 4. Kiểm tra xem tiêu đề trang kết quả có chứa chữ mình tìm không
        String title = driver.getTitle();
        System.out.println(">>> [INFO] Tieu de trang web la: " + title);
        assertTrue(title.contains("Kiểm thử"));
        
        System.out.println(">>> [PASS] Test chuc nang tim kiem thanh cong!");
    }

    @AfterEach
    public void teardown() {
        if (driver != null) {
            driver.quit(); // Chạy xong là dọn dẹp, tắt trình duyệt
        }
    }
}