package com.bespalov.shop.config;

import java.util.ResourceBundle;

public class Properties {
    private static ResourceBundle resourceBundle;

    static {
        resourceBundle = ResourceBundle.getBundle("application");
    }

    public static ResourceBundle getResourceBundle() {
        return resourceBundle;
    }
}
