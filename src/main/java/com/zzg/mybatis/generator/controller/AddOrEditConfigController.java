package com.zzg.mybatis.generator.controller;

import com.zzg.mybatis.generator.model.DatabaseConfig;
import com.zzg.mybatis.generator.model.GeneratorConfig;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.net.URL;
import java.util.ResourceBundle;

public class AddOrEditConfigController extends BaseFXController {
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void handleSubmitButtonAction() {

    }

    @FXML
    public void chooseProjectFolder(ActionEvent actionEvent) {
    }

    public void setMainUIController(MainUIController mainUIController) {
    }

    /**
     * 初始化当前页面的数据
     *
     * @param config 选择的配置或者默认配置
     */
    public void setConfig(GeneratorConfig config) {

    }
}
