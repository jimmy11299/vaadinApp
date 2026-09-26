package com.example.views;

import com.example.service.EmployeeService;
import com.example.vo.EmployeeVO;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("employee")
public class MainLayout extends VerticalLayout {

    private final EmployeeService employeeService;
    private final Grid<EmployeeVO> grid = new Grid<>(EmployeeVO.class);

    // 建構子注入 Service
    public MainLayout(EmployeeService employeeService) {
        this.employeeService = employeeService;

        // 1. 設定上方標題與區塊
        addClassName("main-layout");
        setSizeFull();

        // 2. 初始化表格 (Grid)
        grid.setColumns("id", "name", "department");
        refreshGrid();

        // 3. 建立右側或下方的「新增表單區塊」(Form Layout)
        TextField nameField = new TextField("姓名");
        TextField deptField = new TextField("部門");
        Button saveButton = new Button("新增員工");

        saveButton.addClickListener(e -> {
            // 封裝成 VO 物件
            EmployeeVO newVo = new EmployeeVO(
                System.currentTimeMillis() % 1000, 
                nameField.getValue(), 
                deptField.getValue()
            );
            
            // 呼叫 Service 儲存
            employeeService.save(newVo);
            
            // 重新整理表格
            refreshGrid();
            Notification.show("新增成功！");
            
            // 清空輸入框
            nameField.clear();
            deptField.clear();
        });

        // 4. 用 HorizontalLayout 與 VerticalLayout 做「巢狀排版」
        HorizontalLayout formLayout = new HorizontalLayout(nameField, deptField, saveButton);
        formLayout.setAlignItems(Alignment.BASELINE);

        // 將組件加入主畫面
        add(formLayout, grid);
    }

    private void refreshGrid() {
        grid.setItems(employeeService.findAll());
    }
}