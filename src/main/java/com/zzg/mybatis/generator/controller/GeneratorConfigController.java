package com.zzg.mybatis.generator.controller;

import com.zzg.mybatis.generator.model.GeneratorConfig;
import com.zzg.mybatis.generator.util.ConfigHelper;
import com.zzg.mybatis.generator.view.AlertUtil;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.DirectoryChooser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class GeneratorConfigController extends BaseFXController {
    private static final Logger _LOG = LoggerFactory.getLogger(GeneratorConfigController.class);

    @FXML
    private TableView<GeneratorConfig> configTable;
    @FXML
    private TableColumn nameColumn;
    @FXML
    private TableColumn opsColumn;
    @FXML
    private TextField txtProjectDirectory;

    @FXML
    public void chooseProjectFolder() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedFolder = directoryChooser.showDialog(getPrimaryStage());
        if (selectedFolder != null) {
            this.txtProjectDirectory.setText(selectedFolder.getAbsolutePath());
        }
    }

    @FXML
    public void handleSubmitButtonAction(ActionEvent actionEvent) {
    }

    private MainUIController mainUIController;

    private GeneratorConfigController controller;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        controller = this;
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        // 自定义操作列
        opsColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        opsColumn.setCellFactory(cell -> {
            return new TableCell() {
                @Override
                protected void updateItem(Object item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        Button btn0 = new Button("应用");
                        Button btn1 = new Button("编辑");
                        Button btn2 = new Button("删除");
                        HBox hBox = new HBox();
                        hBox.setSpacing(10);
                        hBox.getChildren().add(btn0);
                        hBox.getChildren().add(btn1);
                        hBox.getChildren().add(btn2);
                        btn0.setOnAction(event -> {
                            try {
                                // 应用配置
                                GeneratorConfig generatorConfig = ConfigHelper.loadGeneratorConfig(item.toString());
                                mainUIController.setGeneratorConfigIntoUI(generatorConfig);
                                controller.closeDialogStage();
                            } catch (Exception e) {
                                AlertUtil.showErrorAlert(e.getMessage());
                            }
                        });
                        btn1.setOnAction(event -> {
                            try {
                                // 编辑配置
                                GeneratorConfig generatorConfig = ConfigHelper.loadGeneratorConfig(item.toString());
                                mainUIController.setGeneratorConfigIntoUI(generatorConfig);
                                controller.closeDialogStage();
                            } catch (Exception e) {
                                AlertUtil.showErrorAlert(e.getMessage());
                            }
                        });
                        btn2.setOnAction(event -> {
                            try {
                                // 删除配置
                                _LOG.debug("item: {}", item);
                                ConfigHelper.deleteGeneratorConfig(item.toString());
                                refreshTableView();
                            } catch (Exception e) {
                                AlertUtil.showErrorAlert(e.getMessage());
                            }
                        });
                        setGraphic(hBox);
                    }
                }
            };
        });
        refreshTableView();
    }

    public void refreshTableView() {
        try {
            List<GeneratorConfig> configs = ConfigHelper.loadGeneratorConfigs();
            configTable.setItems(FXCollections.observableList(configs));
        } catch (Exception e) {
            AlertUtil.showErrorAlert(e.getMessage());
        }
    }

    void setMainUIController(MainUIController mainUIController) {
        this.mainUIController = mainUIController;
    }
}
