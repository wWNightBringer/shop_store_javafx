package com.bespalov.shop.validates.validation;

import com.bespalov.shop.MainClass;
import com.bespalov.shop.config.Connect;
import com.bespalov.shop.config.Languages;
import com.bespalov.shop.model.Employer;
import com.bespalov.shop.model.Product;
import com.bespalov.shop.pane.SettingPane;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.spring_shop_store.model.Authentication;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.Locale;

public class ValidatorController {
    private Stage stage;
    private Authentication authentication;
    private String error;
    private Connect connect;
    private MainClass mainClass;
    private SettingPane settingPane;
    private Logger logger = LoggerFactory.getLogger(ValidatorController.class);

    @FXML
    private Label login;
    @FXML
    private Label usernameLabel;
    @FXML
    private Label passwordLabel;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private Button button;
    @FXML
    private Button setting;


    public ValidatorController() {
    }

    @FXML
    public void initialize() {
        login.setText(Languages.getResourceBundle().getString("login"));
        usernameLabel.setText(Languages.getResourceBundle().getString("username"));
        passwordLabel.setText(Languages.getResourceBundle().getString("password"));
        button.setText(Languages.getResourceBundle().getString("login"));
        setting.setText(Languages.getResourceBundle().getString("setting"));
        settingPane = new SettingPane();
    }

    @FXML
    public void validateUsernamePassword() throws IOException, JAXBException, NoSuchMethodException {
        logger.info(String.format("== %s ==", getClass().getName()));
        if (authentication(username.getText(), password.getText())) {
            logger.info(String.format("Response param(Method name %s, values: %s)", getClass().getMethod("validateUsernamePassword").getName(), username.getText()));
            mainClass = new MainClass();
            mainClass.start(stage);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initModality(Modality.WINDOW_MODAL);
            alert.setTitle("authentication");
            alert.setHeaderText("Incorrect data");
            alert.setContentText(error);
            alert.showAndWait();
        }

    }

    @FXML
    public void pressEnter(KeyEvent keyEvent) throws NoSuchMethodException, JAXBException, IOException {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            validateUsernamePassword();
        }
    }

    @FXML
    public void installSetting() throws IOException {
        settingPane = new SettingPane();
        settingPane.showSettingDialog();
        settingPane.setStage(stage);
    }

    private boolean authentication(String username, String password) throws IOException {
        error = "";
        boolean flag = true;
        connect = new Connect("getUsernamePassword");
        authentication = new Authentication();
        authentication.setUsername(username);
        authentication.setPassword(password);

        ObjectMapper objectMapper = new ObjectMapper();
        authentication = objectMapper.readValue(connect.inputStream(authentication), Authentication.class);

        if (username.length() == 0 || !username.equalsIgnoreCase(authentication.getUsername())) {
            error = "Incorrect username\n";
            flag = false;
        }
        if (password.length() == 0 || !password.equalsIgnoreCase(authentication.getPassword())) {
            error = "Incorrect password";
            flag = false;
        }
        return flag;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

}
