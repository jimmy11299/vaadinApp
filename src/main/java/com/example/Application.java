package com.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.vaadin.flow.component.page.AppShellConfigurator;
/**
 * 主程式入口
 * Application
 */
@SpringBootApplication
public class Application implements AppShellConfigurator {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // 當 Spring Boot 完全啟動後，會自動執行這段程式碼來開啓瀏覽器
    @Bean
    CommandLineRunner openBrowser() {
        return args -> {
            try {
                String url = "http://localhost:8080";
                // 強制使用 Windows 的命令列開啟 Chrome (或預設瀏覽器)
                Runtime.getRuntime().exec("cmd /c start " + url);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }
}