package com.bespalov.shop.calendar;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CalendatInit {
    private Integer[] years = {2000, 2001, 2002, 2003, 2004, 2005, 2006, 2007, 2008, 2009, 2010, 2011, 2012, 2013, 2014, 2015, 2016, 2017, 2018, 2019,
            2020, 2021, 2022, 2023, 2024, 2025, 2026, 2027, 2028, 2029};
    private String[] months = {"JANUARY", "FEBRUARY", "MARCH", "APRIL", "MAY", "JUNE", "JULY", "AUGUST", "SEPTEMBER", "OCTOBER",
            "NOVEMBER", "DECEMBER"};
    private Integer[] days = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31};
    private ObservableList<Integer> listYear = FXCollections.observableArrayList(years);
    private ObservableList<String> listMonth = FXCollections.observableArrayList(months);
    private ObservableList<Integer> listDay = FXCollections.observableArrayList(days);
    private ObservableList<String> condition = FXCollections.observableArrayList("Has", "Processing", "Empty");

    public ObservableList<Integer> getListYear() {
        return listYear;
    }

    public ObservableList<String> getListMonth() {
        return listMonth;
    }

    public ObservableList<Integer> getListDay() {
        return listDay;
    }

    public ObservableList<String> getCondition() {
        return condition;
    }
}
