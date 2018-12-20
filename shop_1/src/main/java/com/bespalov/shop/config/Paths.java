package com.bespalov.shop.config;

import com.bespalov.shop.MainClass;
import javafx.stage.Stage;

import java.io.File;
import java.nio.file.Path;
import java.util.prefs.Preferences;

public class Paths {
    private Stage stage;

    public Paths(Stage stage) {
        this.stage = stage;
    }

    public File getPathFile() {
        Preferences preferences = Preferences.systemNodeForPackage(MainClass.class);
        String filePath = preferences.get("filePath", null);
        if (filePath != null) {
            return new File(filePath);
        } else
            return null;
    }

    public void setPathFile(File pathFile) {
        Preferences preferences = Preferences.systemNodeForPackage(MainClass.class);
        if (pathFile != null) {
            preferences.put("filePath", pathFile.getPath());
            stage.setTitle("AddressApp- " + pathFile.toString());
        } else {
            preferences.remove("filePath");
            stage.setTitle("AddressApp");
        }


    }
}
