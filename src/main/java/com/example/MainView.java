package com.example;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("") // 代表這是根目錄 http://localhost:8080/
public class MainView extends VerticalLayout {

    public MainView() {
        // 建立文字輸入框與按鈕
        TextField nameField = new TextField("請輸入文字");
        Button button = new Button("點我測試");

        // 點擊事件（前端動作直接對應 Java）
        button.addClickListener(event -> {
            String text = nameField.getValue();
            Notification.show("你輸入了: " + text);
        });

        // 將元件加入畫面
        add(nameField, button);
    }
}