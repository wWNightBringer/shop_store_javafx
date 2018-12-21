package com.bespalov.shop.config;

import java.util.ResourceBundle;

public class Languages {
    private static ResourceBundle resourceBundle;

    static {
        resourceBundle = ResourceBundle.getBundle("Language");
    }

    public static ResourceBundle getResourceBundle() {
        return resourceBundle;
    }
}
